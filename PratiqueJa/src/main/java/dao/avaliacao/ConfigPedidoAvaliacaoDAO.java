package dao.avaliacao;

import java.util.List;

import dao.DAO;
import jakarta.transaction.Transactional;
import modelo.avaliacao.ConfigPedidoAvaliacao;

public class ConfigPedidoAvaliacaoDAO extends DAO<ConfigPedidoAvaliacao>
{
	private static final long serialVersionUID = 1L;

	public ConfigPedidoAvaliacaoDAO()
	{
		super(ConfigPedidoAvaliacao.class);
	}

	public ConfigPedidoAvaliacao buscarConfig()
	{
		List<ConfigPedidoAvaliacao> lista = listarTudo();
		return lista.isEmpty() ? null : lista.get(0);
	}

	@Transactional
	public ConfigPedidoAvaliacao buscarOuCriarConfig()
	{
		ConfigPedidoAvaliacao config = buscarConfig();
		if (config == null)
		{
			config = new ConfigPedidoAvaliacao();
			adicionar(config);
		}
		return config;
	}
}
