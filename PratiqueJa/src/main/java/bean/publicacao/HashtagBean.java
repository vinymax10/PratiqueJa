package bean.publicacao;

import java.util.EnumSet;

import bean.ConfigBean;
import dao.publicacao.HashtagDAO;
import exceptions.RelacaoException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import modelo.auditoria.TipoEvento;
import modelo.publicacao.Hashtag;

@Named
@ViewScoped
public class HashtagBean extends ConfigBean<Hashtag, HashtagDAO>
{
	public HashtagBean()
	{
		super(Hashtag.class, "Hashtag");
		auditoriasAtivas = EnumSet.allOf(TipoEvento.class);
	}

	protected void podeRemover(Hashtag entidade) throws RelacaoException {}
}
