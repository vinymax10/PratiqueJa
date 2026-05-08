package bean.questao.configuracao;

import java.util.EnumSet;

import bean.ConfigBean;
import dao.questao.configuracao.AnoDAO;
import exceptions.RelacaoException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import modelo.auditoria.TipoEvento;
import modelo.questao.configuracao.Ano;

@Named
@ViewScoped
public class AnoBean extends ConfigBean<Ano, AnoDAO>
{
	public AnoBean()
	{
		super(Ano.class, "Ano");
		auditoriasAtivas = EnumSet.allOf(TipoEvento.class);
	}

	protected void podeRemover(Ano entidade) throws RelacaoException
	{
		if(entidade.getQuestoes().size() > 0)
		throw new RelacaoException("Não foi possível remover o(a) " + nome + ". "
		+ "Existem Questões relacionadas.");
	}
}
