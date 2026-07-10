package service.pagamento;

import java.time.LocalDate;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.usuario.PagamentoDAO;
import dao.usuario.ProdutoDAO;
import dao.usuario.UsuarioDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import jakarta.transaction.Transactional;
import modelo.avaliacao.PerfilAvaliacao;
import modelo.publicacao.PerfilCriador;
import modelo.usuario.Pagamento;
import modelo.usuario.PerfilUsuario;
import modelo.usuario.Produto;
import modelo.usuario.Usuario;
import service.email.EmailService;
import util.StringAux;

/**
 * Processa as notificações (webhook) enviadas pelo Hotmart quando uma compra é
 * aprovada, cancelada, reembolsada ou sofre chargeback.
 */
@ApplicationScoped
public class HotmartWebhookService
{
	private static final Logger LOG = LoggerFactory.getLogger(HotmartWebhookService.class);

	private static final Set<String> EVENTOS_APROVACAO = Set.of("PURCHASE_APPROVED", "PURCHASE_COMPLETE");

	private static final Set<String> EVENTOS_CANCELAMENTO = Set.of(
		"PURCHASE_CANCELED", "PURCHASE_REFUNDED", "PURCHASE_CHARGEBACK",
		"PURCHASE_EXPIRED", "SUBSCRIPTION_CANCELLATION");

	@Inject
	private ProdutoDAO produtoDAO;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private PagamentoDAO pagamentoDAO;

	@Inject
	private EmailService emailService;

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
			ativar(usuario, produto, transacao);
		else if(EVENTOS_CANCELAMENTO.contains(evento))
			cancelar(usuario, produto);
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

	private void ativar(Usuario usuario, Produto produto, String transacao)
	{
		if(transacao != null && pagamentoDAO.buscarPorCodigoTransacaoHotmart(transacao) != null)
		{
			LOG.info("Webhook Hotmart: transação '{}' já processada, ignorando", transacao);
			return;
		}

		if(produto.getPerfilUsuario() != null)
			usuario.setPerfil(produto.getPerfilUsuario());

		if(produto.getPerfilCriador() != null)
			usuario.setPerfilCriador(produto.getPerfilCriador());

		if(produto.getPerfilAvaliacao() != null)
			usuario.setPerfilAvaliacao(produto.getPerfilAvaliacao());

		LocalDate hoje = LocalDate.now();
		LocalDate validade = produto.getDiasValididade() > 0 ? hoje.plusDays(produto.getDiasValididade()) : null;
		usuario.setValidadePlano(validade);

		usuarioDAO.salvar(usuario);

		Pagamento pagamento = new Pagamento();
		pagamento.setUsuario(usuario);
		pagamento.setProduto(produto);
		pagamento.setValor(produto.getValor());
		pagamento.setData(hoje);
		pagamento.setPago(true);
		pagamento.setCodigoTransacaoHotmart(transacao);
		pagamentoDAO.salvar(pagamento);

		String assunto = "Pagamento realizado (Hotmart)";
		String mensagem = "Pagamento realizado via Hotmart\n\n"
			+ "Nome: " + usuario.getNome() + "\n"
			+ "Dia: " + StringAux.getDataStr(hoje) + "\n"
			+ "Valor: " + produto.getValor() + "\n"
			+ "Produto: " + produto.getNome() + "\n"
			+ "Transação: " + transacao + "\n";

		emailService.adicionar("vinymax10@gmail.com", assunto, mensagem);
	}

	private void cancelar(Usuario usuario, Produto produto)
	{
		if(produto.getPerfilUsuario() != null)
			usuario.setPerfil(PerfilUsuario.Basico);

		if(produto.getPerfilCriador() != null)
			usuario.setPerfilCriador(PerfilCriador.Teste);

		if(produto.getPerfilAvaliacao() != null)
			usuario.setPerfilAvaliacao(PerfilAvaliacao.Teste);

		usuario.setValidadePlano(null);
		usuarioDAO.salvar(usuario);
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
