package dao.avaliacao;

import dao.DAO;
import modelo.avaliacao.ConfigAvaliacao;

/**
 * DAO da {@link ConfigAvaliacao}. Usado, por exemplo, para carregar direto pelo id a config de
 * avaliação de um usuário (modo suporte do admin, via {@code ?configAvaliacao=ID}) — a partir dela
 * chega-se ao dono por {@code ConfigAvaliacao.getUsuario()}, sem precisar resolver o usuário à parte.
 */
public class ConfigAvaliacaoDAO extends DAO<ConfigAvaliacao>
{
	private static final long serialVersionUID = 1L;

	public ConfigAvaliacaoDAO()
	{
		super(ConfigAvaliacao.class);
	}
}
