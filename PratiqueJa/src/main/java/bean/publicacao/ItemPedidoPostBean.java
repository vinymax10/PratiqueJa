package bean.publicacao;

import java.util.List;

import bean.FilhoBean;
import dao.publicacao.ItemPedidoPostDAO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.academico.Assunto;
import modelo.exercicio.Nivel;
import modelo.publicacao.Background;
import modelo.publicacao.FormatoPost;
import modelo.publicacao.ImagemPost;
import modelo.publicacao.ItemPedidoPost;

/**
 * Bean da entidade FILHA {@link ItemPedidoPost} (assunto + nível + formato + fundo + quantidade de um
 * pedido de post). Espelha o {@code ItemPedidoAvaliacaoBean}: o CRUD do item mora aqui, não no bean do
 * pai ({@code PedidoPostBean}). Manipula a lista em memória de {@code pedidoPostBean.getEntidade().getItens()};
 * a persistência acontece quando o pedido é salvo (cascade).
 */
@Named
@ViewScoped
public class ItemPedidoPostBean extends FilhoBean<ItemPedidoPost, ItemPedidoPostDAO>
{
	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoPostBean pedidoPostBean;

	@Inject
	private ImagemPostBean imagemPostBean;

	/** true = o formulário inline (cadastro/edição de assunto) está aberto, substituindo a lista. */
	private boolean editandoItem = false;

	public ItemPedidoPostBean()
	{
		super(ItemPedidoPost.class, "Assunto");
	}

	@PostConstruct
	public void init()
	{
		entidade = novoItem();
	}

	/** Novo item com valores-padrão (evita adicionar item incompleto). */
	private ItemPedidoPost novoItem()
	{
		ItemPedidoPost item = new ItemPedidoPost();
		item.setFormato(FormatoPost.Feed);
		item.setQuantidade(1);
		item.getNiveis().add(Nivel.Nivel1);
		List<Assunto> assuntos = pedidoPostBean.getAssuntos();
		if(assuntos != null && !assuntos.isEmpty())
			item.setAssunto(assuntos.get(0));
		return item;
	}

	// ── Formulário inline ─────────────────────────────────────────────

	/** Abre o formulário inline em modo de adição. */
	public void cadastrar()
	{
		entidade = novoItem();
		entidadeOriginal = null;
		cadastro = true;
		editandoItem = true;
	}

	/** Abre o formulário inline em modo de edição, carregando uma cópia do item selecionado. */
	public void editar(ItemPedidoPost alvo)
	{
		cadastro = false;
		entidadeOriginal = alvo;
		entidade = alvo.copia();
		editandoItem = true;
	}

	/** Fecha o formulário inline sem salvar. */
	public void cancelarItem()
	{
		editandoItem = false;
		entidadeOriginal = null;
		entidade = novoItem();
	}

	/** Confirma: adiciona um novo item ou aplica a edição, conforme o modo, e fecha o formulário. */
	public void salvarItem()
	{
		if(cadastro)
			adicionar();
		else
			salvar();
	}

	public String adicionar()
	{
		String resultado = adicionar(() -> {
			List<ItemPedidoPost> itens = getItens();
			entidade.setPedidoPost(pedidoPostBean.getEntidade());
			entidade.setOrdem(itens.size());
			itens.add(entidade);
		});
		editandoItem = false;
		return resultado;
	}

	public String salvar()
	{
		String resultado = salvar(
			() -> entidadeOriginal.aplicarEdicao(entidade),
			null);
		editandoItem = false;
		return resultado;
	}

	/** Duplica um item da lista como um novo item independente. */
	public void duplicar(ItemPedidoPost alvo)
	{
		entidade = alvo.copia();
		cadastro = true;
		adicionar();
	}

	public void removerItem(ItemPedidoPost alvo)
	{
		entidade = alvo;
		remover(() -> {
			List<ItemPedidoPost> itens = getItens();
			itens.removeIf(i -> i == alvo);
			reordenar(itens);
		});
	}

	public void subir(ItemPedidoPost item)
	{
		mover(item, -1);
	}

	public void descer(ItemPedidoPost item)
	{
		mover(item, 1);
	}

	public boolean isEditandoItem()
	{
		return editandoItem;
	}

	// ── Fundo / formato do item ───────────────────────────────────────

	/** Qtd de imagens personalizadas do formato em edição (habilita a opção "Personalizada"). */
	public int getQtdImagensPersonalizadas()
	{
		boolean feed = entidade != null && entidade.getFormato() == FormatoPost.Feed;
		return imagemPostBean.imagemPost(feed).size();
	}

	/**
	 * Em "Específica", garante que haja sempre uma imagem do pool atual selecionada (evita item sem
	 * imagem). Em "Aleatória", a imagem é sorteada na geração.
	 */
	public void ajustarImagemFundo()
	{
		if(entidade == null || entidade.isBackgroundAleatorio())
			return;

		boolean feed = entidade.getFormato() == FormatoPost.Feed;
		if(entidade.isBasePadrao())
		{
			if(entidade.getPadrao() == null)
			{
				List<Background> padroes = feed ? imagemPostBean.getImagensFeed() : imagemPostBean.getImagensReel();
				if(padroes != null && !padroes.isEmpty())
					entidade.setPadrao(padroes.get(0));
			}
		}
		else
		{
			if(entidade.getBackground() == null)
			{
				List<ImagemPost> personalizadas = imagemPostBean.imagemPost(feed);
				if(personalizadas != null && !personalizadas.isEmpty())
					entidade.setBackground(personalizadas.get(0));
			}
		}
	}

	/** Ao trocar o formato, a imagem específica é de outro pool (Feed x Reel): limpa e re-seleciona. */
	public void mudarFormatoItem()
	{
		if(entidade == null)
			return;
		entidade.setBackground(null);
		entidade.setPadrao(null);
		ajustarImagemFundo();
	}

	// ── Selects ───────────────────────────────────────────────────────

	public FormatoPost[] getFormatos()
	{
		return FormatoPost.values();
	}

	public Nivel[] getNiveis()
	{
		return Nivel.values();
	}

	// ── Helpers de lista/ordem ────────────────────────────────────────

	private void mover(ItemPedidoPost item, int direcao)
	{
		// Busca por identidade: itens em memória têm id nulo, então equals() os trataria como iguais.
		List<ItemPedidoPost> itens = getItens();
		int idx = -1;
		for(int i = 0; i < itens.size(); i++)
			if(itens.get(i) == item)
				idx = i;

		int destino = idx + direcao;
		if(idx >= 0 && destino >= 0 && destino < itens.size())
		{
			itens.remove(idx);
			itens.add(destino, item);
			reordenar(itens);
		}
	}

	private void reordenar(List<ItemPedidoPost> itens)
	{
		for(int i = 0; i < itens.size(); i++)
			itens.get(i).setOrdem(i);
	}

	private List<ItemPedidoPost> getItens()
	{
		return pedidoPostBean.getEntidade().getItens();
	}
}
