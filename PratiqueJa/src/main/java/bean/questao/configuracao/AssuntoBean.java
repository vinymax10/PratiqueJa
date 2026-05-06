package bean.questao.configuracao;

import java.util.EnumSet;

import bean.ConfigBean;
import dao.questao.configuracao.AssuntoDAO;
import exceptions.RelacaoException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import modelo.auditoria.TipoEvento;
import modelo.questao.configuracao.Assunto;

@Named
@ViewScoped
public class AssuntoBean extends ConfigBean<Assunto, AssuntoDAO>
{
	public AssuntoBean()
	{
		super(Assunto.class, "Assunto");
		auditoriasAtivas=EnumSet.allOf(TipoEvento.class);
	}

	protected void podeRemover(Assunto entidade) throws RelacaoException
	{
		if(entidade.getQuestoes().size() > 0)
		throw new RelacaoException("Não foi possível remover o(a) " + nome + ". " 
		+ "Existem Questões relacionadas.");
	}
}