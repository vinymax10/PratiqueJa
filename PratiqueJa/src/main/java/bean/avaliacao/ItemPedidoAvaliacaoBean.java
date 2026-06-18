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
import modelo.avaliacao.ConfigPedidoAvaliacao;
import modelo.avaliacao.ItemPedidoAvaliacao;
import modelo.exercicio.ExercicioPadrao;

@Named
@ViewScoped
public class ItemPedidoAvaliacaoBean extends FilhoBean<ItemPedidoAvaliacao, ItemPedidoAvaliacaoDAO>
{
	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoAvaliacaoBean pedidoAvaliacaoBean;

	@Inject
	private ExercicioPadraoDAO exercicioPadraoDAO;

	public ItemPedidoAvaliacaoBean()
	{
		super(ItemPedidoAvaliacao.class, "Assunto");
	}

	@PostConstruct
	public void init()
	{
		entidade = new ItemPedidoAvaliacao();
	}

	public String cadastrar()
	{
		entidade = new ItemPedidoAvaliacao();
		cadastro = true;
		return "";
	}

	public void editar(ItemPedidoAvaliacao item)
	{
		cadastro = false;
		entidadeOriginal = item;
		entidade = mapper.clone(item);
	}

	public String adicionar()
	{
		return adicionar(() -> {
			List<ItemPedidoAvaliacao> itens = getItens();
			entidade.setPedidoAvaliacao(pedidoAvaliacaoBean.getEntidade());
			entidade.setExercicioPadrao(buscarExercicioPadrao(entidade));
			entidade.setOrdem(itens.size());
			itens.add(entidade);
		});
	}

	public String salvar()
	{
		return salvar(
		() -> mapper.update(entidade, entidadeOriginal),
		() -> {
			// Restaura o vínculo com o pedido real: o clone do ModelMapper traz uma cópia da back-reference.
			entidadeOriginal.setPedidoAvaliacao(pedidoAvaliacaoBean.getEntidade());
			entidadeOriginal.setExercicioPadrao(buscarExercicioPadrao(entidadeOriginal));
		});
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
		ConfigPedidoAvaliacao config = pedidoAvaliacaoBean.getConfig();
		int total = pedidoAvaliacaoBean.totalExercicios() - quantidadeAtual + entidade.getQuantidade();
		validar(total > config.getMaxExerciciosPorAvaliacao(),
			"Limite de " + config.getMaxExerciciosPorAvaliacao() + " exercícios por avaliação atingido.");
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
