package bean.avaliacao;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.PaiBean;
import bean.usuario.ControleAcessoBean;
import bean.util.Mensagem;
import dao.academico.AssuntoDAO;
import dao.avaliacao.PedidoAvaliacaoDAO;
import dao.usuario.UsuarioDAO;
import infra.Graphics;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.academico.Assunto;
import modelo.avaliacao.FormatoAvaliacao;
import modelo.avaliacao.FormatoSaida;
import modelo.avaliacao.ItemPedidoAvaliacao;
import modelo.avaliacao.PedidoAvaliacao;
import modelo.avaliacao.PlanoAvaliacao;
import modelo.avaliacao.PosicaoGabarito;
import modelo.avaliacao.StatusPedidoAvaliacao;
import modelo.avaliacao.TipoGabarito;
import modelo.exercicio.Nivel;
import modelo.seguranca.PermissaoPadrao;
import modelo.usuario.Imagem;
import modelo.usuario.PerfilUsuario;
import modelo.usuario.Usuario;
import pdf.exercicio.LayoutLista;
import service.avaliacao.MontadorPedidoAvaliacaoService;
import web.session.Sessao;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class PedidoAvaliacaoBean extends PaiBean<PedidoAvaliacao, PedidoAvaliacaoDAO, PermissaoPadrao<PedidoAvaliacao>>
{
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(PedidoAvaliacaoBean.class);

	/** Cota grátis de teste (vitalícia) para quem ainda não tem plano ativo. */
	public static final int LIMITE_TRIAL_GRATIS = 10;

	// ── Estado da view ────────────────────────────────────────────────

	private List<PedidoAvaliacao> historico;
	private PedidoAvaliacao pedidoSelecionado;

	/** Código do pedido recém-criado, para realçar a linha na list (vem do flash após o redirect). */
	private String codigoDestaque;

	private List<Assunto> assuntos;

	private int avaliacoesUsadasNoMes;

	/** Total vitalício de avaliações geradas — usado para a cota grátis de teste (sem plano). */
	private int avaliacoesUsadasTotal;

	// ── Injeções ──────────────────────────────────────────────────────

	@Inject
	private AssuntoDAO assuntoDAO;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private MontadorPedidoAvaliacaoService montadorService;

	@Inject
	private ControleAcessoBean controleAcessoBean;

	public PedidoAvaliacaoBean()
	{
		super(PedidoAvaliacao.class, "Avaliação");
		urlLista = "/avaliacao/list.xhtml";
		urlCadastro = "/avaliacao/form.xhtml";
	}

	// ── Inicialização ─────────────────────────────────────────────────

	@PostConstruct
	public void init()
	{
		historico = new ArrayList<>();
		avaliacoesUsadasNoMes = 0;
		avaliacoesUsadasTotal = 0;
		try
		{
			assuntos = assuntoDAO.listarOpcoesAtivas();
		}
		catch(Exception e)
		{
			LOG.error("Falha ao listar assuntos", e);
			assuntos = new ArrayList<>();
		}
		novoPedido();
		try
		{
			carregarHistorico();
		}
		catch(Exception e)
		{
			LOG.error("Falha ao carregar histórico de pedidos", e);
		}
		try
		{
			calcularUsoMensal();
		}
		catch(Exception e)
		{
			LOG.error("Falha ao calcular uso mensal", e);
		}
		try
		{
			calcularUsoTotal();
		}
		catch(Exception e)
		{
			LOG.error("Falha ao calcular uso total", e);
		}
		try
		{
			Object novo = FacesContext.getCurrentInstance().getExternalContext().getFlash().get("avNovoCodigo");
			if(novo != null)
				codigoDestaque = novo.toString();
		}
		catch(Exception e)
		{
			LOG.error("Falha ao ler código em destaque", e);
		}
	}

	private void novoPedido()
	{
		entidade = new PedidoAvaliacao();
		entidade.setUsuario(getUsuarioLogado());
	}

	// ── Validação e solicitação ───────────────────────────────────────

	public String solicitar()
	{
		// Não logado: abre a tela de login (igual ao fluxo de responder questão sem login).
		// Após logar, o usuário clica em Solicitar novamente.
		if(!controleAcessoBean.verificaEstaLogado())
			return null;

		if(!validar())
			return null;

		entidade.setCodigoBatch(MontadorPedidoAvaliacaoService.gerarCodigoBatch());
		entidade.setStatus(StatusPedidoAvaliacao.AGUARDANDO);
		entidade.setProgresso(0);
		entidade.setDataSolicitacao(LocalDateTime.now());

		// Retenção do PDF varia por plano (Essencial 7 / Profissional 30 / Master 60 dias).
		entidade.setDataExpiracao(LocalDateTime.now().plusDays(planoAtual().getDiasRetencao()));

		entidade.setUsuario(getUsuarioLogado());

		entidadeDAO.salvar(entidade);
		montadorService.montar(entidade.getId());

		Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO, "Solicitação registrada! Código: " + entidade.getCodigoBatch() + ". Acompanhe o progresso na lista.");

		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("avNovoCodigo", entidade.getCodigoBatch());

		// Redirect ajax-safe via outcome (mantém a mensagem no flash até a list)
		return urlLista + "?faces-redirect=true";
	}

	private boolean validar()
	{
		if(entidade.getTitulo() == null || entidade.getTitulo().isBlank())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Informe o título da avaliação.");
			return false;
		}

		if(entidade.getItens().isEmpty())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Adicione ao menos um assunto.");
			return false;
		}

		if(entidade.getQuantidade() < 1 || entidade.getQuantidade() > getMaxAvaliacoesPorSolicitacao())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Quantidade deve ser entre 1 e " + getMaxAvaliacoesPorSolicitacao() + ".");
			return false;
		}

		Usuario usuario = getUsuarioLogado();
		if(usuario.getPerfil() == PerfilUsuario.Admin)
			return true;

		PlanoAvaliacao plano = usuario.getPlanoAvaliacao();
		if(plano == null)
		{
			// Sem plano: libera a cota grátis de teste (vitalícia), sem cartão.
			int trialRestante = LIMITE_TRIAL_GRATIS - avaliacoesUsadasTotal;
			if(trialRestante <= 0)
			{
				Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Suas " + LIMITE_TRIAL_GRATIS + " avaliações grátis acabaram. Assine um plano para continuar gerando avaliações.");
				return false;
			}
			if(entidade.getQuantidade() > trialRestante)
			{
				Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Cota grátis insuficiente. Restam " + trialRestante + " avaliações de teste. Assine um plano para gerar mais.");
				return false;
			}
			return true;
		}

		// Plano vencido bloqueia a geração (e o crédito acumulado também deixa de valer).
		if(usuario.getValidadePlano() != null && usuario.getValidadePlano().isBefore(LocalDate.now()))
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Seu plano venceu em "
				+ usuario.getValidadePlano().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"))
				+ ". Renove para continuar gerando avaliações.");
			return false;
		}

		// Cota do mês = cota do plano + crédito acumulado (rollover) do mês anterior.
		int cotaDisponivel = plano.getLimiteMensal() + usuario.getCreditoRollover();
		int limiteRestante = cotaDisponivel - avaliacoesUsadasNoMes;
		if(entidade.getQuantidade() > limiteRestante)
		{
			String extra = usuario.getCreditoRollover() > 0
				? " (já incluído o crédito acumulado de " + usuario.getCreditoRollover() + ")"
				: "";
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Cota mensal insuficiente. Restam "
				+ Math.max(0, limiteRestante) + " avaliações neste mês" + extra + ".");
			return false;
		}

		return true;
	}

	// ── Progresso ─────────────────────────────────────────────────────

	public void atualizarProgresso()
	{
		if(historico == null)
			return;
		for(PedidoAvaliacao p : historico)
		{
			if(p.getStatus() == StatusPedidoAvaliacao.GERANDO
			|| p.getStatus() == StatusPedidoAvaliacao.AGUARDANDO)
			{
				PedidoAvaliacao atualizado = entidadeDAO.carrega(p.getId());
				p.setProgresso(atualizado.getProgresso());
				p.setStatus(atualizado.getStatus());
				p.setCaminhoArquivo(atualizado.getCaminhoArquivo());
				p.setNomeDownload(atualizado.getNomeDownload());
			}
		}
	}

	// ── Detalhes da solicitação ───────────────────────────────────────

	public void aoSelecionarPedido(org.primefaces.event.SelectEvent<PedidoAvaliacao> event)
	{
		pedidoSelecionado = event.getObject();
	}

	// ── Download ──────────────────────────────────────────────────────

	public org.primefaces.model.DefaultStreamedContent download(PedidoAvaliacao p) throws java.io.IOException
	{
		if(p.getCaminhoArquivo() == null)
			return null;

		java.nio.file.Path arquivo = java.nio.file.Path.of(p.getCaminhoArquivo());
		byte[] bytes = java.nio.file.Files.readAllBytes(arquivo);

		String mime = p.getNomeDownload() != null && p.getNomeDownload().endsWith(".zip") ? "application/zip" : "application/pdf";

		return org.primefaces.model.DefaultStreamedContent.builder().name(p.getNomeDownload()).contentType(mime)
		.stream(() -> new java.io.ByteArrayInputStream(bytes)).build();
	}

	// ── Cálculos auxiliares ───────────────────────────────────────────

	public int totalExercicios()
	{
		return entidade.getItens().stream().mapToInt(ItemPedidoAvaliacao::getQuantidade).sum();
	}

	public int limiteExerciciosRestante()
	{
		return getMaxExerciciosPorAvaliacao() - totalExercicios();
	}

	// ── Limites do plano do usuário ───────────────────────────────────
	// Sem plano (teste grátis) cai no Essencial, o mais restritivo.

	private PlanoAvaliacao planoAtual()
	{
		Usuario usuario = getUsuarioLogado();
		PlanoAvaliacao plano = usuario != null ? usuario.getPlanoAvaliacao() : null;
		return plano != null ? plano : PlanoAvaliacao.ESSENCIAL;
	}

	public int getMaxExerciciosPorAvaliacao()
	{
		return planoAtual().getMaxExerciciosPorAvaliacao();
	}

	public int getMaxAvaliacoesPorSolicitacao()
	{
		return planoAtual().getMaxAvaliacoesPorSolicitacao();
	}

	// ── Logo da escola no PDF (Profissional/Master) ───────────────────

	/** A logo da escola no cabeçalho é exclusiva dos planos Profissional e Master. */
	public boolean isPodeUsarLogoEscola()
	{
		PlanoAvaliacao plano = getUsuarioLogado() != null ? getUsuarioLogado().getPlanoAvaliacao() : null;
		return plano == PlanoAvaliacao.PROFISSIONAL || plano == PlanoAvaliacao.MASTER;
	}

	public boolean isTemLogoEscola()
	{
		return getUsuarioLogado() != null && getUsuarioLogado().getLogoEscola() != null;
	}

	/** Data URI (base64) da logo para o preview no formulário — embutido direto no src, sem depender
	 *  de uma segunda requisição (StreamedContent) que perderia o contexto da view. */
	public String getLogoEscolaDataUri()
	{
		Usuario usuario = getUsuarioLogado();
		if(usuario == null || usuario.getId() == null)
			return null;

		byte[] bytes = usuarioDAO.buscarLogoEscolaBytes(usuario.getId());
		if(bytes == null || bytes.length == 0)
			return null;

		return "data:image/png;base64," + Base64.getEncoder().encodeToString(bytes);
	}

	public void uploadLogoEscola(FileUploadEvent event)
	{
		Usuario usuario = getUsuarioLogado();
		if(!isPodeUsarLogoEscola())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_WARN, "A logo da escola está disponível apenas nos planos Profissional e Master.");
			return;
		}

		UploadedFile arquivo = event.getFile();
		try
		{
			SerialBlob blob = new SerialBlob(Graphics.resizeLogo(arquivo, 600, 240));
			Imagem logo = new Imagem();
			logo.setFile(blob);
			logo.setEndereco(arquivo.getFileName());
			usuario.setLogoEscola(logo);
			usuarioDAO.salvar(usuario);
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Logo da escola atualizada com sucesso.");
		}
		catch(SQLException | IOException e)
		{
			LOG.error("Falha ao processar a logo da escola", e);
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível processar a imagem enviada.");
		}
	}

	public void removerLogoEscola()
	{
		Usuario usuario = getUsuarioLogado();
		if(usuario == null || usuario.getId() == null)
			return;
		usuarioDAO.removerLogoEscola(usuario.getId());
		usuario.setLogoEscola(null); // sincroniza o objeto da sessão p/ o preview sumir na hora
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Logo da escola removida.");
	}

	/** Sequência de layouts de todas as questões, na ordem dos itens (espelha o gerador). */
	private List<LayoutLista> sequenciaLayouts()
	{
		List<LayoutLista> sequencia = new ArrayList<>();
		for(ItemPedidoAvaliacao item : entidade.getItens())
		{
			LayoutLista layout = (item.getExercicioPadrao() != null
				&& item.getExercicioPadrao().getLayoutLista() == LayoutLista.ESPACOSO)
				? LayoutLista.ESPACOSO : LayoutLista.PADRAO;
			for(int i = 0; i < item.getQuantidade(); i++)
				sequencia.add(layout);
		}
		return sequencia;
	}

	/**
	 * Distribui as questões em páginas, igual ao gerador: uma página com qualquer questão espaçosa
	 * comporta 4 (e fica toda espaçosa); uma página só com padrão comporta 6. Devolve, para cada
	 * página, o par {espaçosa? 1 : 0, quantidade de questões}.
	 */
	private List<int[]> simularPaginas()
	{
		int capEspacoso = LayoutLista.ESPACOSO.exerciciosPorPagina;
		int capPadrao = LayoutLista.PADRAO.exerciciosPorPagina;

		List<int[]> paginas = new ArrayList<>();
		int quantidade = 0;
		boolean espacoso = false;

		for(LayoutLista layout : sequenciaLayouts())
		{
			boolean questaoEspacosa = layout == LayoutLista.ESPACOSO;
			int capacidade = (espacoso || questaoEspacosa) ? capEspacoso : capPadrao;

			if(quantidade > 0 && quantidade >= capacidade)
			{
				paginas.add(new int[]{espacoso ? 1 : 0, quantidade});
				quantidade = 0;
				espacoso = false;
			}

			quantidade++;
			espacoso = espacoso || questaoEspacosa;
		}
		if(quantidade > 0)
			paginas.add(new int[]{espacoso ? 1 : 0, quantidade});

		return paginas;
	}

	/** Número de páginas só de questões em cada prova (exato, igual ao gerador). */
	public int paginasQuestoes()
	{
		return simularPaginas().size();
	}

	/** {espaçosa? 1 : 0, quantidade} da última página, ou null se não há questões. */
	private int[] ultimaPagina()
	{
		List<int[]> paginas = simularPaginas();
		return paginas.isEmpty() ? null : paginas.get(paginas.size() - 1);
	}

	/** Capacidade total da última página (4 se espaçosa, 6 se padrão). */
	private int capacidadeUltimaPagina()
	{
		int[] ultima = ultimaPagina();
		if(ultima == null)
			return 0;
		return ultima[0] == 1 ? LayoutLista.ESPACOSO.exerciciosPorPagina
		                      : LayoutLista.PADRAO.exerciciosPorPagina;
	}

	/** Quantas questões ainda cabem na última página (mantendo o layout dela). */
	public int exerciciosQueAindaCabem()
	{
		int[] ultima = ultimaPagina();
		if(ultima == null)
			return 0;
		return capacidadeUltimaPagina() - ultima[1];
	}

	/** Há espaço para ao menos mais uma questão na última página. */
	public boolean isUltimaPaginaComEspaco()
	{
		return exerciciosQueAindaCabem() > 0;
	}

	/** Última página com metade ou mais da capacidade vazia — sinaliza desperdício de papel. */
	public boolean isUltimaPaginaOciosa()
	{
		int[] ultima = ultimaPagina();
		if(ultima == null)
			return false;
		return ultima[1] <= capacidadeUltimaPagina() / 2;
	}

	public int limiteMensalRestante()
	{
		PlanoAvaliacao plano = getUsuarioLogado().getPlanoAvaliacao();
		if(plano == null)
			return 0;
		return plano.getLimiteMensal() - avaliacoesUsadasNoMes;
	}

	public String descricaoPlano()
	{
		PlanoAvaliacao plano = getUsuarioLogado().getPlanoAvaliacao();
		if(plano == null)
			return "Teste grátis (" + trialRestante() + " de " + LIMITE_TRIAL_GRATIS + " restantes)";
		return plano.getNome() + " (" + plano.getLimiteMensal() + " avaliações/mês)";
	}

	/** Usuário em modo de teste grátis: logado, sem plano e fora do perfil Admin. */
	public boolean isModoTrial()
	{
		Usuario usuario = getUsuarioLogado();
		return usuario != null && usuario.getPerfil() != PerfilUsuario.Admin && usuario.getPlanoAvaliacao() == null;
	}

	public int getLimiteTrialGratis()
	{
		return LIMITE_TRIAL_GRATIS;
	}

	public int trialRestante()
	{
		int restante = LIMITE_TRIAL_GRATIS - avaliacoesUsadasTotal;
		return restante < 0 ? 0 : restante;
	}

	public boolean temGerandoEmProgresso()
	{
		if (historico == null) return false;
		return historico.stream().anyMatch(p -> p.getStatus() == StatusPedidoAvaliacao.GERANDO
		                                    || p.getStatus() == StatusPedidoAvaliacao.AGUARDANDO);
	}

	public boolean estaExpirado(PedidoAvaliacao p)
	{
		return p.getDataExpiracao() != null && p.getDataExpiracao().isBefore(LocalDateTime.now());
	}

	// ── Dados para selects ────────────────────────────────────────────

	public FormatoAvaliacao[] getFormatosAvaliacao()
	{
		return FormatoAvaliacao.values();
	}

	public TipoGabarito[] getTiposGabarito()
	{
		return TipoGabarito.values();
	}

	public PosicaoGabarito[] getPosicoes()
	{
		return PosicaoGabarito.values();
	}

	public FormatoSaida[] getFormatosSaida()
	{
		return FormatoSaida.values();
	}

	public Nivel[] getNiveis()
	{
		return Nivel.values();
	}

	// ── Utilitários privados ──────────────────────────────────────────

	private void carregarHistorico()
	{
		historico = entidadeDAO.buscarPorUsuario(getUsuarioLogado());
	}

	private void calcularUsoMensal()
	{
		LocalDateTime inicioMes = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
		LocalDateTime inicioProximo = inicioMes.plusMonths(1);
		avaliacoesUsadasNoMes = entidadeDAO.somarAvaliacoesNoMes(getUsuarioLogado(), inicioMes, inicioProximo);
	}

	private void calcularUsoTotal()
	{
		avaliacoesUsadasTotal = entidadeDAO.somarAvaliacoesTotal(getUsuarioLogado());
	}

	private Usuario getUsuarioLogado()
	{
		return Sessao.getUsuarioLogado();
	}
}
