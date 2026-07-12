package service.pagamento;

import java.time.LocalDate;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.usuario.AssinaturaDAO;
import dao.usuario.PagamentoDAO;
import dao.usuario.ProdutoDAO;
import dao.usuario.UsuarioDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import jakarta.transaction.Transactional;
import modelo.avaliacao.PerfilAvaliacao;
import modelo.publicacao.PerfilCriador;
import modelo.usuario.Assinatura;
import modelo.usuario.Pagamento;
import modelo.usuario.PerfilUsuario;
import modelo.usuario.Produto;
import modelo.usuario.StatusAssinatura;
import modelo.usuario.Usuario;
import service.email.EmailService;
import util.StringAux;

/**
 * Processa as notificações (webhook) enviadas pelo Hotmart quando uma compra é
 * aprovada, cancelada, reembolsada, sofre chargeback, ou quando o assinante
 * troca de plano/oferta dentro do mesmo produto (SWITCH_PLAN).
 */
@ApplicationScoped
public class HotmartWebhookService
{
	private static final Logger LOG = LoggerFactory.getLogger(HotmartWebhookService.class);

	private static final Set<String> EVENTOS_APROVACAO = Set.of("PURCHASE_APPROVED", "PURCHASE_COMPLETE");

	// Cancelamentos de compra/pedido (payload com purchase/buyer/product).
	private static final Set<String> EVENTOS_CANCELAMENTO_COMPRA = Set.of(
		"PURCHASE_CANCELED", "PURCHASE_REFUNDED", "PURCHASE_CHARGEBACK", "PURCHASE_EXPIRED");

	// Cancelamento de assinatura tem payload próprio: data.subscriber (não aninhado em
	// data.purchase/data.buyer) — ver doc "Evento de cancelamento de assinatura".
	private static final String EVENTO_CANCELAMENTO_ASSINATURA = "SUBSCRIPTION_CANCELLATION";

	// Troca de plano/oferta dentro do mesmo produto Hotmart (upgrade/downgrade nativo,
	// mesmo subscriber_code) — ver doc "Evento de troca de plano".
	private static final String EVENTO_TROCA_PLANO = "SWITCH_PLAN";

	@Inject
	private ProdutoDAO produtoDAO;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private PagamentoDAO pagamentoDAO;

	@Inject
	private AssinaturaDAO assinaturaDAO;

	@Inject
	private EmailService emailService;

	@Inject
	private HotmartApiClient hotmartApiClient;

	@Transactional
	public void processarNotificacao(JsonObject corpo)
	{
		String evento = corpo.getString("event", null);
		JsonObject dados = corpo.getJsonObject("data");

		if(evento == null || dados == null)
		{
			LOG.warn("Webhook Hotmart ignorado: corpo sem 'event'/'data'");
			return;
		}

		if(EVENTO_TROCA_PLANO.equals(evento))
		{
			trocarPlano(dados);
			return;
		}

		if(EVENTO_CANCELAMENTO_ASSINATURA.equals(evento))
		{
			cancelarPorAssinatura(dados);
			return;
		}

		// Demais eventos (compra aprovada/cancelada/reembolsada/chargeback/expirada) usam
		// o payload padrão de pedido: purchase/buyer/product.
		JsonObject purchase = dados.getJsonObject("purchase");
		JsonObject buyer = dados.getJsonObject("buyer");
		JsonObject product = dados.getJsonObject("product");

		if(purchase == null || buyer == null || product == null)
		{
			LOG.warn("Webhook Hotmart ignorado: evento '{}' sem purchase/buyer/product", evento);
			return;
		}

		String email = texto(buyer, "email");
		String transacao = texto(purchase, "transaction");
		String codigoOferta = purchase.containsKey("offer") ? texto(purchase.getJsonObject("offer"), "code") : null;
		String idProduto = texto(product, "id");

		// Em PURCHASE_APPROVED/COMPLETE o subscriber vem aninhado em subscription
		// (data.subscription.subscriber.code) — diferente do formato de
		// SUBSCRIPTION_CANCELLATION, que é tratado à parte em cancelarPorAssinatura().
		JsonObject subscription = dados.getJsonObject("subscription");
		JsonObject subscriber = subscription != null ? subscription.getJsonObject("subscriber") : null;
		String subscriberCode = subscriber != null ? texto(subscriber, "code") : null;

		Produto produto = buscarProduto(codigoOferta, idProduto);
		if(produto == null)
		{
			LOG.warn("Webhook Hotmart: nenhum Produto encontrado para oferta '{}' / produto '{}'", codigoOferta, idProduto);
			return;
		}

		Usuario usuario = email != null ? usuarioDAO.getUsuario(email, null) : null;
		if(usuario == null)
		{
			LOG.warn("Webhook Hotmart: nenhum usuário encontrado para o e-mail '{}'", email);
			return;
		}

		if(EVENTOS_APROVACAO.contains(evento))
			ativar(usuario, produto, transacao, subscriberCode);
		else if(EVENTOS_CANCELAMENTO_COMPRA.contains(evento))
			cancelar(usuario, produto, subscriberCode);
		else
			LOG.info("Webhook Hotmart: evento '{}' não tratado", evento);
	}

	private Produto buscarProduto(String codigoOferta, String idProduto)
	{
		Produto produto = null;

		if(codigoOferta != null && !codigoOferta.isBlank())
			produto = produtoDAO.buscarPorIdHotmart(codigoOferta);

		if(produto == null && idProduto != null && !idProduto.isBlank())
			produto = produtoDAO.buscarPorIdHotmart(idProduto);

		return produto;
	}

	/**
	 * SWITCH_PLAN: o assinante trocou de oferta dentro do mesmo produto Hotmart
	 * (upgrade/downgrade nativo — o subscriber_code não muda). Localiza a oferta atual
	 * em data.plans[current=true].offer.key e aplica como uma ativação normal, sem
	 * gerar um novo registro de pagamento (não houve uma nova transação, só a troca
	 * de valor cobrado no próximo ciclo).
	 */
	private void trocarPlano(JsonObject dados)
	{
		JsonObject subscription = dados.getJsonObject("subscription");
		if(subscription == null)
		{
			LOG.warn("Webhook Hotmart (SWITCH_PLAN) ignorado: sem 'subscription'");
			return;
		}

		String subscriberCode = texto(subscription, "subscriber_code");
		String email = texto(subscription.getJsonObject("user"), "email");

		JsonArray plans = dados.getJsonArray("plans");
		String offerKeyAtual = null;

		if(plans != null)
		{
			for(JsonValue item : plans)
			{
				JsonObject plano = item.asJsonObject();
				if(plano.getBoolean("current", false) && plano.containsKey("offer"))
				{
					offerKeyAtual = texto(plano.getJsonObject("offer"), "key");
					break;
				}
			}
		}

		if(offerKeyAtual == null)
		{
			LOG.warn("Webhook Hotmart (SWITCH_PLAN) ignorado: nenhum plano 'current' com oferta");
			return;
		}

		Produto produto = produtoDAO.buscarPorIdHotmart(offerKeyAtual);
		if(produto == null)
		{
			LOG.warn("Webhook Hotmart (SWITCH_PLAN): nenhum Produto encontrado para oferta '{}'", offerKeyAtual);
			return;
		}

		Usuario usuario = email != null ? usuarioDAO.getUsuario(email, null) : null;
		if(usuario == null)
		{
			LOG.warn("Webhook Hotmart (SWITCH_PLAN): nenhum usuário encontrado para o e-mail '{}'", email);
			return;
		}

		ativar(usuario, produto, null, subscriberCode);
	}

	private void ativar(Usuario usuario, Produto produto, String transacao, String subscriberCode)
	{
		if(transacao != null && pagamentoDAO.buscarPorCodigoTransacaoHotmart(transacao) != null)
		{
			LOG.info("Webhook Hotmart: transação '{}' já processada, ignorando", transacao);
			return;
		}

		LocalDate hoje = LocalDate.now();
		LocalDate validade = produto.getDiasValididade() > 0 ? hoje.plusDays(produto.getDiasValididade()) : null;

		boolean trocouDePlano = false;

		if(produto.getPerfilUsuario() != null)
		{
			trocouDePlano |= cancelarAssinaturaAnteriorSeTrocaDePlano(usuario.getSubscriberCodeHotmart(), subscriberCode, hoje);
			usuario.setPerfil(produto.getPerfilUsuario());
			usuario.setValidadePlano(validade);
			usuario.setSubscriberCodeHotmart(subscriberCode);
		}

		if(produto.getPerfilCriador() != null)
		{
			trocouDePlano |= cancelarAssinaturaAnteriorSeTrocaDePlano(usuario.getSubscriberCodeHotmartCriador(), subscriberCode, hoje);
			usuario.setPerfilCriador(produto.getPerfilCriador());
			usuario.setValidadePlanoCriador(validade);
			usuario.setSubscriberCodeHotmartCriador(subscriberCode);
		}

		if(produto.getPerfilAvaliacao() != null)
		{
			trocouDePlano |= cancelarAssinaturaAnteriorSeTrocaDePlano(usuario.getSubscriberCodeHotmartAvaliacao(), subscriberCode, hoje);
			usuario.setPerfilAvaliacao(produto.getPerfilAvaliacao());
			usuario.setValidadePlanoAvaliacao(validade);
			usuario.setSubscriberCodeHotmartAvaliacao(subscriberCode);
		}

		usuarioDAO.salvar(usuario);

		Assinatura assinatura = registrarAssinatura(usuario, produto, subscriberCode, hoje, validade);

		// Só registra pagamento/envia recibo quando há uma transação real por trás
		// (uma troca de plano/oferta dentro do mesmo produto não gera transação nova).
		if(transacao != null)
		{
			Pagamento pagamento = new Pagamento();
			pagamento.setUsuario(usuario);
			pagamento.setProduto(produto);
			pagamento.setValor(produto.getValor());
			pagamento.setData(hoje);
			pagamento.setPago(true);
			pagamento.setCodigoTransacaoHotmart(transacao);
			pagamento.setAssinatura(assinatura);
			pagamentoDAO.salvar(pagamento);

			String assunto = "Pagamento realizado (Hotmart)";
			String html = montarHtmlPagamento(usuario.getNome(), hoje, produto.getValor(), produto.getNome(),
				transacao, trocouDePlano);

			emailService.adicionar("vinymax10@gmail.com", assunto, html);
		}

		if(trocouDePlano && usuario.getEmail() != null && !usuario.getEmail().isBlank())
		{
			String html = montarHtmlTrocaPlano(usuario.getFirstNome(), produto.getNome());
			emailService.adicionar(usuario.getEmail(), "Troca de plano confirmada", html);
		}
	}

	/**
	 * Corpo HTML do e-mail de notificação de pagamento (admin), no mesmo padrão visual dos
	 * demais e-mails do Pratique Já. Inicia com "&lt;" para o e-mail ser reconhecido como HTML.
	 */
	private String montarHtmlPagamento(String nome, LocalDate dia, double valor, String nomeProduto,
		String transacao, boolean trocouDePlano)
	{
		nome = escapeHtml(nome);
		String produto = escapeHtml(nomeProduto);
		String transacaoEsc = escapeHtml(transacao);
		String valorStr = "R$ " + String.format(new java.util.Locale("pt", "BR"), "%,.2f", valor);

		String obs = trocouDePlano
			? "<tr><td style=\"padding:14px 28px 0;\">"
				+ "<div style=\"background:#fff7ed;border:1px solid #fde3c8;border-radius:10px;padding:12px 16px;font-size:13px;color:#9a5b13;line-height:1.5;\">"
				+ "⚠️ Assinatura anterior do mesmo tipo foi cancelada automaticamente."
				+ "</div></td></tr>"
			: "";

		return "<!DOCTYPE html><html><body style=\"margin:0;padding:0;background:#eef1f8;\">"
		+ "<table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"background:#eef1f8;padding:24px 12px;font-family:Arial,Helvetica,sans-serif;\"><tr><td align=\"center\">"
		+ "<table role=\"presentation\" width=\"600\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;width:100%;background:#ffffff;border-radius:16px;overflow:hidden;border:1px solid #e2e8f4;\">"
		+ "<tr><td style=\"padding:20px 28px;border-bottom:3px solid #059669;\">"
		+ "<span style=\"font-size:22px;font-weight:bold;color:#059669;\">Pratique<span style=\"color:#de7b40;\">Já</span></span>"
		+ "<span style=\"font-size:12px;color:#8a93a6;float:right;padding-top:9px;\">Pagamento Hotmart</span>"
		+ "</td></tr>"
		+ "<tr><td style=\"padding:26px 28px 6px;\">"
		+ "<p style=\"margin:0 0 6px;font-size:17px;color:#2b3445;\">💰 Pagamento realizado via Hotmart</p>"
		+ "<p style=\"margin:0 0 16px;font-size:14px;color:#6b7689;line-height:1.5;\">Um novo pagamento foi confirmado e o plano já foi ativado automaticamente.</p>"
		+ "</td></tr>"
		+ "<tr><td style=\"padding:0 28px 6px;\">"
		+ "<table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"background:#f6f8fc;border:1px solid #e2e8f4;border-radius:12px;font-size:14px;color:#2b3445;\">"
		+ linhaTabela("Nome", nome)
		+ linhaTabela("Dia", StringAux.getDataStr(dia))
		+ linhaTabela("Valor", valorStr)
		+ linhaTabela("Produto", produto)
		+ linhaTabela("Transação", transacaoEsc)
		+ "</table>"
		+ "</td></tr>"
		+ obs
		+ "<tr><td style=\"padding:18px 28px 8px;\"></td></tr>"
		+ "<tr><td style=\"padding:8px 28px 24px;border-top:1px solid #eef1f8;\">"
		+ "<p style=\"margin:14px 0 0;font-size:13px;color:#059669;font-weight:bold;\">Pratique Já</p>"
		+ "<p style=\"margin:2px 0 0;font-size:12px;color:#8a93a6;\">pratiqueja.com</p>"
		+ "</td></tr>"
		+ "</table></td></tr></table></body></html>";
	}

	/** Uma linha "rótulo: valor" da tabela de detalhes do e-mail de pagamento. */
	private String linhaTabela(String rotulo, String valor)
	{
		return "<tr>"
		+ "<td style=\"padding:10px 16px;border-bottom:1px solid #e2e8f4;font-weight:bold;color:#6b7689;width:110px;\">" + rotulo + "</td>"
		+ "<td style=\"padding:10px 16px;border-bottom:1px solid #e2e8f4;\">" + valor + "</td>"
		+ "</tr>";
	}

	/**
	 * Corpo HTML do e-mail de troca de plano automática, no mesmo padrão visual dos demais
	 * e-mails do Pratique Já (MontadorPostService/MontadorPedidoAvaliacaoService). Inicia com
	 * "&lt;" para o e-mail ser reconhecido como HTML.
	 */
	private String montarHtmlTrocaPlano(String nome, String nomeProduto)
	{
		nome = escapeHtml(nome);
		String produto = escapeHtml(nomeProduto);

		return "<!DOCTYPE html><html><body style=\"margin:0;padding:0;background:#eef1f8;\">"
		+ "<table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"background:#eef1f8;padding:24px 12px;font-family:Arial,Helvetica,sans-serif;\"><tr><td align=\"center\">"
		+ "<table role=\"presentation\" width=\"600\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;width:100%;background:#ffffff;border-radius:16px;overflow:hidden;border:1px solid #e2e8f4;\">"
		+ "<tr><td style=\"padding:20px 28px;border-bottom:3px solid #2563eb;\">"
		+ "<span style=\"font-size:22px;font-weight:bold;color:#2563eb;\">Pratique<span style=\"color:#de7b40;\">Já</span></span>"
		+ "<span style=\"font-size:12px;color:#8a93a6;float:right;padding-top:9px;\">Gestão de Assinatura</span>"
		+ "</td></tr>"
		+ "<tr><td style=\"padding:26px 28px 6px;\">"
		+ "<p style=\"margin:0 0 6px;font-size:17px;color:#2b3445;\">Olá, <b>" + nome + "</b>! 👋</p>"
		+ "<p style=\"margin:0 0 16px;font-size:14px;color:#6b7689;line-height:1.5;\">Identificamos a assinatura do plano abaixo e cancelamos automaticamente a assinatura anterior do mesmo tipo, para evitar cobrança duplicada.</p>"
		+ "</td></tr>"
		+ "<tr><td align=\"center\" style=\"padding:14px 28px 6px;\">"
		+ "<div style=\"background:#f6f8fc;border:1px dashed #c2cce0;border-radius:12px;padding:22px 18px;\">"
		+ "<div style=\"font-size:34px;line-height:1;margin-bottom:8px;\">🔄</div>"
		+ "<p style=\"margin:0;font-size:15px;font-weight:bold;color:#2b3445;\">" + produto + "</p>"
		+ "<p style=\"margin:6px 0 0;font-size:13px;color:#6b7689;line-height:1.5;\">Assinatura anterior do mesmo tipo cancelada automaticamente.</p>"
		+ "</div>"
		+ "</td></tr>"
		+ "<tr><td style=\"padding:18px 28px 8px;\">"
		+ "<p style=\"margin:0;font-size:13px;color:#6b7689;line-height:1.5;\">Se isso não era o esperado, entre em contato com a gente.</p>"
		+ "</td></tr>"
		+ "<tr><td style=\"padding:8px 28px 24px;border-top:1px solid #eef1f8;\">"
		+ "<p style=\"margin:14px 0 0;font-size:13px;color:#2563eb;font-weight:bold;\">Equipe do Pratique Já</p>"
		+ "<p style=\"margin:2px 0 0;font-size:12px;color:#8a93a6;\">pratiqueja.com</p>"
		+ "</td></tr>"
		+ "</table></td></tr></table></body></html>";
	}

	/** Escapa os caracteres que quebrariam o HTML (conteúdo vem do banco/usuário). */
	private String escapeHtml(String texto)
	{
		if(texto == null)
			return "";
		return texto.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
	}

	/**
	 * Se já havia uma assinatura ativa (subscriberCode) diferente da nova neste eixo, cancela
	 * a antiga na Hotmart via API antes de sobrescrever — evita cobrança duplicada num upgrade/downgrade
	 * feito fora do fluxo nativo de troca de plano (SWITCH_PLAN), por exemplo uma nova compra avulsa.
	 * Best-effort: falha na chamada não impede a ativação do novo plano.
	 */
	private boolean cancelarAssinaturaAnteriorSeTrocaDePlano(String subscriberCodeAtual, String subscriberCodeNovo, LocalDate hoje)
	{
		if(subscriberCodeAtual == null || subscriberCodeAtual.isBlank())
			return false;

		if(subscriberCodeNovo != null && subscriberCodeNovo.equals(subscriberCodeAtual))
			return false;

		boolean cancelado = hotmartApiClient.cancelarAssinatura(subscriberCodeAtual);
		if(!cancelado)
			LOG.warn("Não foi possível cancelar automaticamente a assinatura Hotmart anterior '{}'", subscriberCodeAtual);

		fecharAssinatura(assinaturaDAO.buscarAtivaPorSubscriberCode(subscriberCodeAtual), hoje);

		return true;
	}

	/**
	 * Localiza (por subscriber_code, ou por usuário+produto quando não há subscriber_code)
	 * a Assinatura ativa e a atualiza; se ela existir mas para um produto diferente (troca de
	 * plano/oferta sob o mesmo subscriber_code), fecha a antiga e abre uma nova. Cria do zero
	 * se nenhuma assinatura ativa for encontrada.
	 */
	private Assinatura registrarAssinatura(Usuario usuario, Produto produto, String subscriberCode, LocalDate hoje, LocalDate validade)
	{
		Assinatura assinatura = subscriberCode != null
			? assinaturaDAO.buscarAtivaPorSubscriberCode(subscriberCode)
			: assinaturaDAO.buscarAtivaPorUsuarioEProduto(usuario, produto);

		if(assinatura != null && !assinatura.getProduto().getId().equals(produto.getId()))
		{
			fecharAssinatura(assinatura, hoje);
			assinatura = null;
		}

		if(assinatura == null)
		{
			assinatura = new Assinatura();
			assinatura.setUsuario(usuario);
			assinatura.setProduto(produto);
			assinatura.setSubscriberCodeHotmart(subscriberCode);
			assinatura.setDataInicio(hoje);
		}

		assinatura.setStatus(StatusAssinatura.Ativa);
		assinatura.setDataValidade(validade);
		assinatura.setDataCancelamento(null);

		return assinaturaDAO.salvar(assinatura);
	}

	private void fecharAssinatura(Usuario usuario, Produto produto, String subscriberCodeAtual, LocalDate hoje)
	{
		Assinatura assinatura = subscriberCodeAtual != null
			? assinaturaDAO.buscarAtivaPorSubscriberCode(subscriberCodeAtual)
			: assinaturaDAO.buscarAtivaPorUsuarioEProduto(usuario, produto);

		fecharAssinatura(assinatura, hoje);
	}

	private void fecharAssinatura(Assinatura assinatura, LocalDate hoje)
	{
		if(assinatura == null)
			return;

		assinatura.setStatus(StatusAssinatura.Cancelada);
		assinatura.setDataCancelamento(hoje);
		assinaturaDAO.salvar(assinatura);
	}

	private void cancelar(Usuario usuario, Produto produto, String subscriberCode)
	{
		LocalDate hoje = LocalDate.now();

		if(produto.getPerfilUsuario() != null && aindaAtivoNesteEixo(usuario.getSubscriberCodeHotmart(), subscriberCode))
		{
			fecharAssinatura(usuario, produto, usuario.getSubscriberCodeHotmart(), hoje);
			usuario.setPerfil(PerfilUsuario.Basico);
			usuario.setValidadePlano(null);
			usuario.setSubscriberCodeHotmart(null);
		}

		if(produto.getPerfilCriador() != null && aindaAtivoNesteEixo(usuario.getSubscriberCodeHotmartCriador(), subscriberCode))
		{
			fecharAssinatura(usuario, produto, usuario.getSubscriberCodeHotmartCriador(), hoje);
			usuario.setPerfilCriador(PerfilCriador.Teste);
			usuario.setValidadePlanoCriador(null);
			usuario.setSubscriberCodeHotmartCriador(null);
		}

		if(produto.getPerfilAvaliacao() != null && aindaAtivoNesteEixo(usuario.getSubscriberCodeHotmartAvaliacao(), subscriberCode))
		{
			fecharAssinatura(usuario, produto, usuario.getSubscriberCodeHotmartAvaliacao(), hoje);
			usuario.setPerfilAvaliacao(PerfilAvaliacao.Teste);
			usuario.setValidadePlanoAvaliacao(null);
			usuario.setSubscriberCodeHotmartAvaliacao(null);
		}

		usuarioDAO.salvar(usuario);
	}

	/**
	 * SUBSCRIPTION_CANCELLATION tem payload próprio (data.subscriber, sem purchase/buyer)
	 * e não informa a oferta cancelada — só o produto e o subscriber_code. Por isso a
	 * reversão é feita comparando o subscriber_code diretamente contra o que está
	 * armazenado em cada eixo do usuário, sem precisar resolver um Produto.
	 */
	private void cancelarPorAssinatura(JsonObject dados)
	{
		JsonObject subscriber = dados.getJsonObject("subscriber");
		if(subscriber == null)
		{
			LOG.warn("Webhook Hotmart (SUBSCRIPTION_CANCELLATION) ignorado: sem 'subscriber'");
			return;
		}

		String subscriberCode = texto(subscriber, "code");
		String email = texto(subscriber, "email");

		Usuario usuario = email != null ? usuarioDAO.getUsuario(email, null) : null;
		if(usuario == null)
		{
			LOG.warn("Webhook Hotmart (SUBSCRIPTION_CANCELLATION): nenhum usuário encontrado para o e-mail '{}'", email);
			return;
		}

		if(subscriberCode == null || subscriberCode.isBlank())
		{
			LOG.warn("Webhook Hotmart (SUBSCRIPTION_CANCELLATION) ignorado: sem subscriber_code");
			return;
		}

		boolean revertido = false;

		if(subscriberCode.equals(usuario.getSubscriberCodeHotmart()))
		{
			usuario.setPerfil(PerfilUsuario.Basico);
			usuario.setValidadePlano(null);
			usuario.setSubscriberCodeHotmart(null);
			revertido = true;
		}

		if(subscriberCode.equals(usuario.getSubscriberCodeHotmartCriador()))
		{
			usuario.setPerfilCriador(PerfilCriador.Teste);
			usuario.setValidadePlanoCriador(null);
			usuario.setSubscriberCodeHotmartCriador(null);
			revertido = true;
		}

		if(subscriberCode.equals(usuario.getSubscriberCodeHotmartAvaliacao()))
		{
			usuario.setPerfilAvaliacao(PerfilAvaliacao.Teste);
			usuario.setValidadePlanoAvaliacao(null);
			usuario.setSubscriberCodeHotmartAvaliacao(null);
			revertido = true;
		}

		if(revertido)
		{
			usuarioDAO.salvar(usuario);
			fecharAssinatura(assinaturaDAO.buscarAtivaPorSubscriberCode(subscriberCode), LocalDate.now());
		}
		else
			LOG.info("Webhook Hotmart (SUBSCRIPTION_CANCELLATION): subscriber_code '{}' não corresponde a nenhum eixo ativo do usuário '{}' (provavelmente já substituído por um upgrade)",
				subscriberCode, usuario.getEmail());
	}

	/**
	 * Só reverte o eixo se o evento de cancelamento ainda corresponder à assinatura
	 * atualmente ativa nele. Sem isso, um cancelamento atrasado da assinatura ANTIGA
	 * (já substituída por um upgrade) derrubaria por engano o plano novo.
	 */
	private boolean aindaAtivoNesteEixo(String subscriberCodeArmazenado, String subscriberCodeEvento)
	{
		if(subscriberCodeArmazenado == null || subscriberCodeEvento == null)
			return true;

		return subscriberCodeArmazenado.equals(subscriberCodeEvento);
	}

	private static String texto(JsonObject objeto, String chave)
	{
		if(objeto == null || !objeto.containsKey(chave) || objeto.isNull(chave))
			return null;

		JsonValue valor = objeto.get(chave);

		return switch(valor.getValueType())
		{
			case STRING -> objeto.getString(chave);
			case NUMBER -> objeto.getJsonNumber(chave).toString();
			default -> valor.toString();
		};
	}
}
