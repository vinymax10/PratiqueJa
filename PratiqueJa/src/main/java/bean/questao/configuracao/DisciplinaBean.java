package bean.questao.configuracao;

import java.util.EnumSet;

import bean.ConfigBean;
import dao.questao.configuracao.DisciplinaDAO;
import exceptions.RelacaoException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.auditoria.TipoEvento;
import modelo.questao.configuracao.Disciplina;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class DisciplinaBean extends ConfigBean<Disciplina, DisciplinaDAO>
{
	public DisciplinaBean()
	{
		super(Disciplina.class, "Disciplina");
		auditoriasAtivas = EnumSet.allOf(TipoEvento.class);
	}

	protected void podeRemover(Disciplina entidade) throws RelacaoException
	{
		if(entidade.getQuestoes().size() > 0)
		throw new RelacaoException("Não foi possível remover o(a) " + nome + ". "
		+ "Existem Questões relacionadas.");
	}
}
