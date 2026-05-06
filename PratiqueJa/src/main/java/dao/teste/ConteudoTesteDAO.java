package dao.teste;

import dao.DAO;
import modelo.teste.ConteudoTeste;

public class ConteudoTesteDAO extends DAO<ConteudoTeste>
{
	private static final long serialVersionUID = 1L;

	public ConteudoTesteDAO()
	{
		super(ConteudoTeste.class);
	}

}
