package bean.avaliacao;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.file.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.PaiBean;
import bean.download.Diretorio;
import bean.seguranca.SessaoBean;
import bean.usuario.ControleAcessoBean;
import bean.util.Mensagem;
import dao.academico.AssuntoDAO;
import dao.avaliacao.PedidoAvaliacaoDAO;
import dao.usuario.UsuarioDAO;
import infra.Graphics;
import service.avaliacao.CreditoAvaliacaoService;
import service.avaliacao.MontadorPedidoAvaliacaoService;
import service.configuracao.DiretorioService;
import util.FileAux;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.academico.Assunto;
import modelo.avaliacao.ConfigAvaliacao;
import modelo.avaliacao.FormatoAvaliacao;
import modelo.avaliacao.FormatoSaida;
import modelo.avaliacao.ItemPedidoAvaliacao;
import modelo.avaliacao.PedidoAvaliacao;
import modelo.avaliacao.PerfilAvaliacao;
import modelo.avaliacao.PosicaoGabarito;
import modelo.avaliacao.StatusPedidoAvaliacao;
import modelo.avaliacao.TipoGabarito;
import modelo.exercicio.Nivel;
import modelo.seguranca.PermissaoPadrao;
import modelo.usuario.Imagem;
import modelo.usuario.PerfilUsuario;
import modelo.usuario.Usuario;
import pdf.exercicio.LayoutLista;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class PedidoAvaliacaoBean extends PaiBean<PedidoAvaliacao, PedidoAvaliacaoDAO, PermissaoPadrao<PedidoAvaliacao>>
{
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(PedidoAvaliacaoBean.class);

	// ── Estado da view ────────────────────────────────────────────────

	private List<PedidoAvaliacao> historico;
	private PedidoAvaliacao pedidoSelecionado;

	/** Código do pedido recém-criado, para realçar a linha na list (vem do flash após o redirect). */
	private String codigoDestaque;

	private List<Assunto> assuntos;

	/** Créditos disponíveis no período vigente (calculado uma vez por @PostConstruct via CreditoAvaliacaoService). */
	private int creditosRestantes;

	/** Avaliações já geradas no período vigente (mensal para renovável, total para Teste). */
	private int avaliacoesUsadas;

	/** Dialog de cota esgotada/plano vencido — título e mensagem definidos em validar(). */
	private String dialogCotaTitulo;
	private String dialogCotaMensagem;

	// ── Injeções ──────────────────────────────────────────────────────

	@Inject
	private SessaoBean sessaoBean;

	@Inject
	private AssuntoDAO assuntoDAO;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private CreditoAvaliacaoService creditoAvaliacaoService;

	@Inject
	private MontadorPedidoAvaliacaoService montadorService;

	@Inject
	private ControleAcessoBean controleAcessoBean;

	@Inject
	private DiretorioService diretorioService;

	private Diretorio diretorio;

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
		diretorio = diretorioService.criarDiretorioSemReserva();
		historico = new ArrayList<>();
		creditosRestantes = 0;
		avaliacoesUsadas = 0;
		try
		{
			assuntos = assuntoDAO.listarOpcoesAtivas();
		}
		catch(Exception e)
		{
			LOG.error("Falha ao listar assuntos", e);
			assuntos = new ArrayList<>();
		}
		carregarOuNovo();
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
			calcularUso();
		}
		catch(Exception e)
		{
			LOG.error("Falha ao calcular uso de avaliações", e);
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
		Usuario usuario = sessaoBean.getUsuario();
		entidade.setUsuario(usuario);
		aplicarConfigPadrao(usuario);
	}

	/** Abre um rascunho existente (parâmetro {@code rascunho}) para continuar a configuração, ou um novo pedido. */
	private void carregarOuNovo()
	{
		String idParam = FacesContext.getCurrentInstance().getExternalContext()
			.getRequestParameterMap().get("rascunho");
		if(idParam != null && !idParam.isBlank())
		{
			try
			{
				PedidoAvaliacao rascunho = entidadeDAO.carrega(Long.valueOf(idParam));
				Usuario usuario = sessaoBean.getUsuario();
				if(rascunho != null && usuario != null && rascunho.getUsuario() != null
					&& usuario.getId().equals(rascunho.getUsuario().getId())
					&& rascunho.getStatus() == StatusPedidoAvaliacao.RASCUNHO)
				{
					entidade = rascunho;
					return;
				}
			}
			catch(Exception e)
			{
				LOG.error("Falha ao carregar rascunho", e);
			}
		}
		novoPedido();
	}

	/** Pré-carrega na nova avaliação os valores-padrão salvos pelo usuário (cabeçalho e formato). */
	private void aplicarConfigPadrao(Usuario usuario)
	{
		if(usuario == null || usuario.getConfigAvaliacao() == null)
			return;

		ConfigAvaliacao config = usuario.getConfigAvaliacao();
		entidade.setTitulo(config.getTitulo());
		entidade.setEscola(config.getEscola());
		entidade.setNomeProfessor(config.getNomeProfessor());
		entidade.setTipoGabarito(config.getTipoGabarito());
		entidade.setPosicaoGabarito(config.getPosicaoGabarito());
		entidade.setFormatoSaida(config.getFormatoSaida());
		entidade.setQuantidade(config.getQuantidade());
	}

	/** Salva os dados atuais (cabeçalho e formato) como o padrão do usuário, reaproveitado nas próximas avaliações. */
	public void salvarComoPadrao()
	{
		Usuario usuario = sessaoBean.getUsuario();
		if(usuario == null)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_WARN, "Faça login para salvar um padrão.");
			return;
		}

		ConfigAvaliacao config = obterOuCriarConfig(usuario);
		config.setTitulo(entidade.getTitulo());
		config.setEscola(entidade.getEscola());
		config.setNomeProfessor(entidade.getNomeProfessor());
		config.setTipoGabarito(entidade.getTipoGabarito());
		config.setPosicaoGabarito(entidade.getPosicaoGabarito());
		config.setFormatoSaida(entidade.getFormatoSaida());
		config.setQuantidade(entidade.getQuantidade());

		usuarioDAO.salvar(usuario);
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Padrão salvo! As próximas avaliações já virão com esses dados.");
	}

	/** Devolve a config de avaliação do usuário, criando-a (e vinculando-a) se ainda não existir. */
	private ConfigAvaliacao obterOuCriarConfig(Usuario usuario)
	{
		ConfigAvaliacao config = usuario.getConfigAvaliacao();
		if(config == null)
		{
			config = new ConfigAvaliacao();
			usuario.setConfigAvaliacao(config);
		}
		return config;
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

		prepararPedido();
		entidade.setCodigoBatch(MontadorPedidoAvaliacaoService.gerarCodigoBatch());
		entidade.setDataSolicitacao(LocalDateTime.now());
		entidade.setStatus(StatusPedidoAvaliacao.AGUARDANDO);

		// Retenção do PDF varia por plano (Essencial 7 / Profissional 30 / Master 60 dias).
		entidade.setDataExpiracao(LocalDateTime.now().plusDays(planoAtual().getDiasRetencao()));

		entidadeDAO.salvar(entidade);
		montadorService.montar(entidade.getId());

		Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO, "Solicitação registrada! Código: " + entidade.getCodigoBatch() + ". Acompanhe o progresso na lista.");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("avNovoCodigo", entidade.getCodigoBatch());

		return urlLista + "?faces-redirect=true";
	}

	/** Salva o pedido como rascunho (sem gerar), para o usuário continuar a configuração depois. */
	public String salvarRascunho()
	{
		if(!controleAcessoBean.verificaEstaLogado())
			return null;

		prepararPedido();
		entidade.setStatus(StatusPedidoAvaliacao.RASCUNHO);

		entidade = entidadeDAO.salvar(entidade);

		Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO,
			"Rascunho salvo. Gere quando terminar de configurar.");
		return urlLista + "?faces-redirect=true";
	}

	/** Gera um rascunho salvo direto do histórico (valida cota e dispara a geração). */
	public String gerarRascunho(PedidoAvaliacao rascunho)
	{
		entidade = rascunho;
		return solicitar();
	}

	/**
	 * Preenche os campos comuns antes de salvar ou solicitar: usuário, progresso, e garante
	 * codigoBatch e dataSolicitacao quando ainda não definidos.
	 */
	private void prepararPedido()
	{
		entidade.setUsuario(sessaoBean.getUsuario());
		entidade.setProgresso(0);
		if(entidade.getCodigoBatch() == null)
			entidade.setCodigoBatch(MontadorPedidoAvaliacaoService.gerarCodigoBatch());
		if(entidade.getDataSolicitacao() == null)
			entidade.setDataSolicitacao(LocalDateTime.now());
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

		Usuario usuario = sessaoBean.getUsuario();
		if(usuario.getPerfil() == PerfilUsuario.Admin && usuario.getPerfilAvaliacao() == null)
			return true;

		PerfilAvaliacao plano = usuario.getPerfilAvaliacao();
		if(plano == null || !plano.isRenovavel())
		{
			// Teste ou trial legado (null): cota total fixa, não renova mensalmente.
			PerfilAvaliacao efetivo = plano != null ? plano : PerfilAvaliacao.Teste;
			if(creditosRestantes <= 0)
			{
				abrirDialogCota(
					"Avaliações gratuitas esgotadas",
					"Você já utilizou todas as " + efetivo.getLimiteMensal() + " avaliações do plano " + efetivo.getNome() + ". Assine um plano para continuar gerando avaliações personalizadas."
				);
				return false;
			}
			if(entidade.getQuantidade() > creditosRestantes)
			{
				abrirDialogCota(
					"Cota insuficiente",
					"Você solicitou " + entidade.getQuantidade() + " exemplares, mas restam apenas " + creditosRestantes + " no plano " + efetivo.getNome() + ". Reduza a quantidade ou assine um plano com mais avaliações."
				);
				return false;
			}
			return true;
		}

		// Plano vencido bloqueia a geração (e o crédito acumulado também deixa de valer).
		if(usuario.getValidadePlano() != null && usuario.getValidadePlano().isBefore(LocalDate.now()))
		{
			abrirDialogCota(
				"Plano vencido",
				"Seu plano venceu em " + usuario.getValidadePlano().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"))
				+ ". Renove para continuar gerando avaliações personalizadas."
			);
			return false;
		}

		// creditosRestantes já inclui rollover (calculado via CreditoAvaliacaoService).
		if(entidade.getQuantidade() > creditosRestantes)
		{
			String extra = usuario.getCreditoRollover() > 0
				? " Isso já inclui o crédito acumulado de " + usuario.getCreditoRollover() + " avaliação(ões)."
				: "";
			abrirDialogCota(
				"Cota mensal insuficiente",
				"Restam " + creditosRestantes + " avaliações neste mês." + extra
				+ " Você pode reduzir a quantidade ou fazer upgrade para um plano maior."
			);
			return false;
		}

		return true;
	}

	private void abrirDialogCota(String titulo, String mensagem)
	{
		dialogCotaTitulo = titulo;
		dialogCotaMensagem = mensagem;
		PrimeFaces.current().executeScript("PF('dlgCota').show()");
	}

	// ── Progresso ─────────────────────────────────────────────────────

	public void atualizarProgresso()
	{
		if(historico == null)
			return;
		for(PedidoAvaliacao pedido : historico)
		{
			if(pedido.getStatus() == StatusPedidoAvaliacao.GERANDO
			|| pedido.getStatus() == StatusPedidoAvaliacao.AGUARDANDO)
			{
				PedidoAvaliacao atualizado = entidadeDAO.carrega(pedido.getId());
				pedido.sincronizarProgresso(atualizado);
			}
		}
	}

	// ── Detalhes da solicitação ───────────────────────────────────────

	/** Carrega o pedido (com os itens) para exibição read-only no detalhe. */
	public void verPedido(PedidoAvaliacao pedido)
	{
		pedidoSelecionado = entidadeDAO.carrega(pedido.getId());
	}

	/** Envia por e-mail o arquivo de uma avaliação já gerada (anexo). */
	public void enviarEmail(PedidoAvaliacao pedido)
	{
		if(pedido == null || pedido.getCaminhoArquivo() == null)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_WARN, "Esta avaliação não está mais disponível para envio.");
			return;
		}
		try
		{
			montadorService.reenviarPorEmail(pedido.getId());
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Avaliação enviada para o seu e-mail.");
		}
		catch(Exception e)
		{
			LOG.error("Falha ao enviar avaliação por e-mail", e);
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível enviar o e-mail.");
		}
	}

	// ── Download ──────────────────────────────────────────────────────

	public DefaultStreamedContent download(PedidoAvaliacao p) throws IOException
	{
		if(p.getCaminhoArquivo() == null)
			return null;

		byte[] bytes = Files.readAllBytes(Path.of(p.getCaminhoArquivo()));
		String mime = p.getNomeDownload() != null && p.getNomeDownload().endsWith(".zip") ? "application/zip" : "application/pdf";

		return DefaultStreamedContent.builder()
			.name(p.getNomeDownload()).contentType(mime)
			.stream(() -> new ByteArrayInputStream(bytes)).build();
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

	private PerfilAvaliacao planoAtual()
	{
		Usuario usuario = sessaoBean.getUsuario();
		PerfilAvaliacao plano = usuario != null ? usuario.getPerfilAvaliacao() : null;
		return plano != null ? plano : PerfilAvaliacao.Essencial;
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
		Usuario usuario = sessaoBean.getUsuario();
		PerfilAvaliacao plano = usuario != null ? usuario.getPerfilAvaliacao() : null;
		return plano == PerfilAvaliacao.Profissional || plano == PerfilAvaliacao.Master;
	}

	public boolean isTemLogoEscola()
	{
		Usuario usuario = sessaoBean.getUsuario();
		return usuario != null && usuario.getConfigAvaliacao() != null
			&& usuario.getConfigAvaliacao().getLogoEscola() != null
			&& usuario.getConfigAvaliacao().getLogoEscola().getEndereco() != null;
	}

	public String getLogoEscolaEndereco()
	{
		Usuario usuario = sessaoBean.getUsuario();
		if(usuario == null || usuario.getConfigAvaliacao() == null
			|| usuario.getConfigAvaliacao().getLogoEscola() == null)
			return null;
		return usuario.getConfigAvaliacao().getLogoEscola().getEndereco();
	}

	public void uploadLogoEscola(FileUploadEvent event)
	{
		Usuario usuario = sessaoBean.getUsuario();
		if(!isPodeUsarLogoEscola())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_WARN, "A logo da escola está disponível apenas nos planos Profissional e Master.");
			return;
		}

		UploadedFile arquivo = event.getFile();
		try
		{
			String endBase = diretorio.getConfig().getEndereco();
			String endRel = "/images/logo-escola/" + usuario.getId() + "/";

			ConfigAvaliacao config = obterOuCriarConfig(usuario);
			Imagem logo = config.getLogoEscola() != null ? config.getLogoEscola() : new Imagem();

			if(logo.getEndereco() != null)
			{
				File antigo = new File(endBase + logo.getEndereco());
				if(antigo.exists())
					antigo.delete();
			}

			byte[] bytes = Graphics.resizeLogo(arquivo, 600, 240);
			FileAux.gravarFile(endBase + endRel, arquivo.getFileName(), bytes);
			logo.setEndereco(endRel + arquivo.getFileName());

			config.setLogoEscola(logo);
			usuarioDAO.salvar(usuario);
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Logo da escola atualizada com sucesso.");
		}
		catch(IOException e)
		{
			LOG.error("Falha ao processar a logo da escola", e);
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível processar a imagem enviada.");
		}
	}

	public void removerLogoEscola()
	{
		Usuario usuario = sessaoBean.getUsuario();
		if(usuario == null || usuario.getId() == null)
			return;

		ConfigAvaliacao config = usuario.getConfigAvaliacao();
		if(config != null && config.getLogoEscola() != null && config.getLogoEscola().getEndereco() != null)
		{
			File arquivo = new File(diretorio.getConfig().getEndereco() + config.getLogoEscola().getEndereco());
			if(arquivo.exists())
				arquivo.delete();
		}

		if(config != null)
			config.setLogoEscola(null);
		usuarioDAO.salvar(usuario);
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

	/** Créditos disponíveis no período vigente (delegado ao CreditoAvaliacaoService no @PostConstruct). */
	public int limiteMensalRestante()
	{
		return creditosRestantes;
	}

	public String descricaoPlano()
	{
		PerfilAvaliacao plano = sessaoBean.getUsuario().getPerfilAvaliacao();
		if(plano == null)
			return "Teste grátis (" + creditosRestantes + " de " + PerfilAvaliacao.Teste.getLimiteMensal() + " restantes)";
		if(!plano.isRenovavel())
			return plano.getNome() + " (" + creditosRestantes + " de " + plano.getLimiteMensal() + " restantes)";
		return plano.getNome() + " (" + plano.getLimiteMensal() + " avaliações/mês)";
	}

	/** Usuário em modo de teste grátis legado: logado, sem plano atribuído e fora do perfil Admin. */
	public boolean isModoTrial()
	{
		Usuario usuario = sessaoBean.getUsuario();
		return usuario != null && usuario.getPerfil() != PerfilUsuario.Admin && usuario.getPerfilAvaliacao() == null;
	}

	public int getLimiteTrialGratis()
	{
		return PerfilAvaliacao.Teste.getLimiteMensal();
	}

	/** Rótulo do stat "usadas" no hero: "no mês" para renovável, "total" para Teste. */
	public String getLabelUsadas()
	{
		Usuario usuario = sessaoBean.getUsuario();
		PerfilAvaliacao plano = usuario != null ? usuario.getPerfilAvaliacao() : null;
		return (plano != null && plano.isRenovavel()) ? "Usadas no mês" : "Usadas no total";
	}

	public boolean temGerandoEmProgresso()
	{
		if(historico == null)
			return false;
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
		historico = entidadeDAO.buscarPorUsuario(sessaoBean.getUsuario());
	}

	private void calcularUso()
	{
		Usuario usuario = sessaoBean.getUsuario();
		if(usuario == null) return;
		creditosRestantes = creditoAvaliacaoService.creditosRestantes(usuario);
		avaliacoesUsadas = creditoAvaliacaoService.avaliacoesUsadas(usuario);
	}

}
