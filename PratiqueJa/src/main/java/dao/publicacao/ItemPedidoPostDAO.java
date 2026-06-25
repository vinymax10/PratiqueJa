package dao.publicacao;

import dao.DAO;
import modelo.publicacao.ItemPedidoPost;

public class ItemPedidoPostDAO extends DAO<ItemPedidoPost>
{
	private static final long serialVersionUID = 1L;

	public ItemPedidoPostDAO()
	{
		super(ItemPedidoPost.class);
	}
}
