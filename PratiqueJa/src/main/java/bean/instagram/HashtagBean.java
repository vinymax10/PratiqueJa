package bean.instagram;

import java.util.EnumSet;

import bean.ConfigBean;
import dao.instagram.HashtagDAO;
import exceptions.RelacaoException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import modelo.auditoria.TipoEvento;
import modelo.publicacao.Hashtag;
import modelo.academico.Ano;

@Named
@ViewScoped
public class HashtagBean extends ConfigBean<Hashtag, HashtagDAO>
{
	public HashtagBean()
	{
		super(Hashtag.class, "Hashtag");
		auditoriasAtivas = EnumSet.allOf(TipoEvento.class);
	}

	protected void podeRemover(Ano entidade) throws RelacaoException
	{
//		if(entidade.getQuestoes().size() > 0)
//		throw new RelacaoException("Não foi possível remover o(a) " + nome + ". "
//		+ "Existem Questões relacionadas.");
	}
}
