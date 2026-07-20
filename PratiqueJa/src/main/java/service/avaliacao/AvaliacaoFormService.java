package service.avaliacao;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.file.UploadedFile;

import dao.avaliacao.PedidoAvaliacaoDAO;
import dao.usuario.UsuarioDAO;
import infra.Graphics;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import modelo.avaliacao.ConfigAvaliacao;
import modelo.avaliacao.ItemPedidoAvaliacao;
import modelo.avaliacao.PedidoAvaliacao;
import modelo.avaliacao.StatusPedidoAvaliacao;
import modelo.usuario.Imagem;
import modelo.usuario.Usuario;
import pdf.exercicio.LayoutLista;
import util.FileAux;

/**
 * Regras de negócio e persistência da tela de Avaliação (usadas pelo {@code PedidoAvaliacaoBean} e
 * pelo {@code ConfigAvaliacaoBean}, que cuidam só de view/navegação/mensagens). Concentra: mapeamento
 * config↔pedido, persistência de pedido/rascunho/config/logo e os cálculos de layout/paginação
 * (que espelham o gerador de PDF).
 */
@ApplicationScoped
public class AvaliacaoFormService implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoAvaliacaoDAO pedidoDAO;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private FilaGeracaoAvaliacaoService filaGeracaoService;

	// ── Cabeçalho padrão (config → pedido) ──

	/** Pré-carrega no pedido os valores-padrão da config (cabeçalho e formato). */
	public void aplicarPadrao(PedidoAvaliacao pedido, ConfigAvaliacao config)
	{
		if(config == null)
			return;

		pedido.setTitulo(config.getTitulo());
		pedido.setEscola(config.getEscola());
		pedido.setNomeProfessor(config.getNomeProfessor());
		pedido.setTipoGabarito(config.getTipoGabarito());
		pedido.setPosicaoGabarito(config.getPosicaoGabarito());
		pedido.setFormatoSaida(config.getFormatoSaida());
		pedido.setQuantidade(config.getQuantidade());
	}

	/** Grava os dados atuais do pedido (cabeçalho e formato) como o padrão do usuário. */
	public void salvarPadrao(Usuario usuario, PedidoAvaliacao dados)
	{
		ConfigAvaliacao config = obterOuCriarConfig(usuario);
		config.setTitulo(dados.getTitulo());
		config.setEscola(dados.getEscola());
		config.setNomeProfessor(dados.getNomeProfessor());
		config.setTipoGabarito(dados.getTipoGabarito());
		config.setPosicaoGabarito(dados.getPosicaoGabarito());
		config.setFormatoSaida(dados.getFormatoSaida());
		config.setQuantidade(dados.getQuantidade());
		usuarioDAO.salvar(usuario);
	}

	/** Devolve a config do usuário, criando-a (e mantendo a back-reference) se ainda não existir. */
	private ConfigAvaliacao obterOuCriarConfig(Usuario usuario)
	{
		ConfigAvaliacao config = usuario.getConfigAvaliacao();
		if(config == null)
		{
			config = new ConfigAvaliacao();
			usuario.setConfigAvaliacao(config);
		}
		config.setUsuario(usuario);
		return config;
	}

	// ── Persistência do pedido ──

	/** Prepara e persiste o pedido para geração (status AGUARDANDO) e o coloca na fila. */
	public void solicitarGeracao(PedidoAvaliacao pedido, Usuario usuario, int diasRetencao)
	{
		prepararPedido(pedido, usuario);
		pedido.setStatus(StatusPedidoAvaliacao.AGUARDANDO);
		pedido.setDataExpiracao(LocalDateTime.now().plusDays(diasRetencao));

		pedidoDAO.salvar(pedido);
		filaGeracaoService.enfileirar(pedido.getId());
	}

	/** Prepara e persiste o pedido como rascunho (sem gerar). */
	public PedidoAvaliacao salvarRascunho(PedidoAvaliacao pedido, Usuario usuario)
	{
		prepararPedido(pedido, usuario);
		pedido.setStatus(StatusPedidoAvaliacao.RASCUNHO);
		return pedidoDAO.salvar(pedido);
	}

	/** Campos comuns antes de salvar/solicitar: usuário, progresso, codigoBatch e dataSolicitacao. */
	private void prepararPedido(PedidoAvaliacao pedido, Usuario usuario)
	{
		pedido.setUsuario(usuario);
		pedido.setProgresso(0);
		if(pedido.getCodigoBatch() == null)
			pedido.setCodigoBatch(MontadorPedidoAvaliacaoService.gerarCodigoBatch());
		if(pedido.getDataSolicitacao() == null)
			pedido.setDataSolicitacao(LocalDateTime.now());
	}

	// ── Logo da escola ──

	/** Redimensiona, grava em disco e vincula a logo à config do usuário. */
	public void salvarLogo(Usuario usuario, UploadedFile arquivo, String endBase) throws IOException
	{
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
	}

	/** Remove o arquivo e a vinculação da logo da escola da config do usuário. */
	public void removerLogo(Usuario usuario, String endBase)
	{
		if(usuario == null || usuario.getId() == null)
			return;

		ConfigAvaliacao config = usuario.getConfigAvaliacao();
		if(config != null && config.getLogoEscola() != null && config.getLogoEscola().getEndereco() != null)
		{
			File arquivo = new File(endBase + config.getLogoEscola().getEndereco());
			if(arquivo.exists())
				arquivo.delete();
		}

		if(config != null)
			config.setLogoEscola(null);
		usuarioDAO.salvar(usuario);
	}

	// ── Layout / paginação (espelha o gerador de PDF) ──

	/** Sequência de layouts de todas as questões, na ordem dos itens. */
	private List<LayoutLista> sequenciaLayouts(PedidoAvaliacao pedido)
	{
		List<LayoutLista> sequencia = new ArrayList<>();
		for(ItemPedidoAvaliacao item : pedido.getItens())
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
	 * comporta 4 (e fica toda espaçosa); uma página só com padrão comporta 6. Devolve, por página,
	 * o par {espaçosa? 1 : 0, quantidade de questões}.
	 */
	public List<int[]> simularPaginas(PedidoAvaliacao pedido)
	{
		int capEspacoso = LayoutLista.ESPACOSO.exerciciosPorPagina;
		int capPadrao = LayoutLista.PADRAO.exerciciosPorPagina;

		List<int[]> paginas = new ArrayList<>();
		int quantidade = 0;
		boolean espacoso = false;

		for(LayoutLista layout : sequenciaLayouts(pedido))
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

	/** {espaçosa? 1 : 0, quantidade} da última página, ou null se não há questões. */
	private int[] ultimaPagina(PedidoAvaliacao pedido)
	{
		List<int[]> paginas = simularPaginas(pedido);
		return paginas.isEmpty() ? null : paginas.get(paginas.size() - 1);
	}

	/** Capacidade total da última página (4 se espaçosa, 6 se padrão). */
	private int capacidadeUltimaPagina(PedidoAvaliacao pedido)
	{
		int[] ultima = ultimaPagina(pedido);
		if(ultima == null)
			return 0;
		return ultima[0] == 1 ? LayoutLista.ESPACOSO.exerciciosPorPagina
		                      : LayoutLista.PADRAO.exerciciosPorPagina;
	}

	/** Total de questões do pedido (soma das quantidades dos itens). */
	public int totalExercicios(PedidoAvaliacao pedido)
	{
		return pedido.getItens().stream().mapToInt(ItemPedidoAvaliacao::getQuantidade).sum();
	}

	/** Número de páginas só de questões em cada prova (exato, igual ao gerador). */
	public int paginasQuestoes(PedidoAvaliacao pedido)
	{
		return simularPaginas(pedido).size();
	}

	/** Quantas questões ainda cabem na última página (mantendo o layout dela). */
	public int exerciciosQueAindaCabem(PedidoAvaliacao pedido)
	{
		int[] ultima = ultimaPagina(pedido);
		if(ultima == null)
			return 0;
		return capacidadeUltimaPagina(pedido) - ultima[1];
	}

	/** Há espaço para ao menos mais uma questão na última página. */
	public boolean ultimaPaginaComEspaco(PedidoAvaliacao pedido)
	{
		return exerciciosQueAindaCabem(pedido) > 0;
	}

	/** Última página com metade ou mais da capacidade vazia — sinaliza desperdício de papel. */
	public boolean ultimaPaginaOciosa(PedidoAvaliacao pedido)
	{
		int[] ultima = ultimaPagina(pedido);
		if(ultima == null)
			return false;
		return ultima[1] <= capacidadeUltimaPagina(pedido) / 2;
	}
}
