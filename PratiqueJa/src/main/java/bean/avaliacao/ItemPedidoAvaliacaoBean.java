package bean.avaliacao;

import java.util.List;

import bean.FilhoBean;
import dao.avaliacao.ItemPedidoAvaliacaoDAO;
import dao.exercicio.ExercicioPadraoDAO;
import exceptions.RelacaoException;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.academico.Assunto;
import modelo.avaliacao.FormatoAvaliacao;
import modelo.avaliacao.ItemPedidoAvaliacao;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.Nivel;

@Named
@ViewScoped
public class ItemPedidoAvaliacaoBean extends FilhoBean<ItemPedidoAvaliacao, ItemPedidoAvaliacaoDAO>
{
	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoAvaliacaoBean pedidoAvaliacaoBean;

	@Inject
	private ExercicioPadraoDAO exercicioPadraoDAO;

	/** true = o formulário inline (cadastro/edição de assunto) está aberto, substituindo a lista. */
	private boolean editandoItem = false;

	public ItemPedidoAvaliacaoBean()
	{
		super(ItemPedidoAvaliacao.class, "Assunto");
	}

	@PostConstruct
	public void init()
	{
		entidade = new ItemPedidoAvaliacao();
	}

	/** Abre o formulário inline em modo de adição, com valores-padrão (evita item incompleto). */
	public String cadastrar()
	{
		entidade = new ItemPedidoAvaliacao();
		List<Assunto> assuntos = pedidoAvaliacaoBean.getAssuntos();
		if(assuntos != null && !assuntos.isEmpty())
			entidade.setAssunto(assuntos.get(0));
		entidade.setNivel(Nivel.Nivel1);
		entidade.setFormato(FormatoAvaliacao.ALTERNATIVAS);
		entidade.setQuantidade(5);
		cadastro = true;
		editandoItem = true;
		return "";
	}

	/** Abre o formulário inline em modo de edição, carregando uma cópia do item selecionado. */
	public void editar(ItemPedidoAvaliacao item)
	{
		cadastro = false;
		entidadeOriginal = item;
		entidade = mapper.clone(item);
		editandoItem = true;
	}

	/** Fecha o formulário inline sem salvar. */
	public void cancelarItem()
	{
		editandoItem = false;
		cadastro = true;
		entidade = new ItemPedidoAvaliacao();
	}

	public String adicionar()
	{
		String resultado = adicionar(() -> {
			List<ItemPedidoAvaliacao> itens = getItens();
			entidade.setPedidoAvaliacao(pedidoAvaliacaoBean.getEntidade());
			entidade.setExercicioPadrao(buscarExercicioPadrao(entidade));
			entidade.setOrdem(itens.size());
			itens.add(entidade);
		});
		editandoItem = false;
		return resultado;
	}

	public String salvar()
	{
		String resultado = salvar(
		() -> mapper.update(entidade, entidadeOriginal),
		() -> {
			// Restaura o vínculo com o pedido real: o clone do ModelMapper traz uma cópia da back-reference.
			entidadeOriginal.setPedidoAvaliacao(pedidoAvaliacaoBean.getEntidade());
			entidadeOriginal.setExercicioPadrao(buscarExercicioPadrao(entidadeOriginal));
		});
		editandoItem = false;
		return resultado;
	}

	public boolean isEditandoItem()
	{
		return editandoItem;
	}

	/** Duplica um item da lista como um novo item independente (sujeito ao limite de exercícios). */
	public String duplicar(ItemPedidoAvaliacao item)
	{
		entidade = mapper.clone(item);
		entidade.setId(null);   // novo item: o clone do ModelMapper traz o id do original
		cadastro = true;
		return adicionar();
	}

	public String removerItem(ItemPedidoAvaliacao item)
	{
		entidade = item;
		return remover(() -> {
			List<ItemPedidoAvaliacao> itens = getItens();
			itens.removeIf(i -> i == item);
			reordenar(itens);
		});
	}

	public void subir(ItemPedidoAvaliacao item)
	{
		mover(item, -1);
	}

	public void descer(ItemPedidoAvaliacao item)
	{
		mover(item, 1);
	}

	@Override
	public void podeAdicionar() throws RelacaoException
	{
		validarLimite(0);
		validar(buscarExercicioPadrao(entidade) == null, "Não há exercício disponível para o assunto e nível selecionados.");
	}

	@Override
	public void podeEditar(ItemPedidoAvaliacao item) throws RelacaoException
	{
		validarLimite(entidadeOriginal.getQuantidade());
		validar(buscarExercicioPadrao(entidade) == null, "Não há exercício disponível para o assunto e nível selecionados.");
	}

	private void validarLimite(int quantidadeAtual) throws RelacaoException
	{
		int maxExercicios = pedidoAvaliacaoBean.getMaxExerciciosPorAvaliacao();
		int total = pedidoAvaliacaoBean.totalExercicios() - quantidadeAtual + entidade.getQuantidade();
		validar(total > maxExercicios,
			"Limite de " + maxExercicios + " exercícios por avaliação atingido.");
	}

	private ExercicioPadrao buscarExercicioPadrao(ItemPedidoAvaliacao item)
	{
		return exercicioPadraoDAO.buscar(item.getAssunto(), item.getNivel());
	}

	private void mover(ItemPedidoAvaliacao item, int direcao)
	{
		// Busca por identidade: itens em memória têm id nulo, então equals() os trata como iguais.
		List<ItemPedidoAvaliacao> itens = getItens();
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

	private void reordenar(List<ItemPedidoAvaliacao> itens)
	{
		for(int i = 0; i < itens.size(); i++)
			itens.get(i).setOrdem(i);
	}

	private List<ItemPedidoAvaliacao> getItens()
	{
		return pedidoAvaliacaoBean.getEntidade().getItens();
	}
}
