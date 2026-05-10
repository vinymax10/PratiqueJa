package bean.questao.configuracao;

import java.util.EnumSet;

import bean.ConfigBean;
import dao.questao.configuracao.AssuntoDAO;
import exceptions.RelacaoException;
import filtro.questao.FiltroAssunto;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.auditoria.TipoEvento;
import modelo.questao.configuracao.Assunto;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class AssuntoBean extends ConfigBean<Assunto, AssuntoDAO>
{
	@Inject
	private FiltroAssunto filtro;

	public AssuntoBean()
	{
		super(Assunto.class, "Assunto");
		auditoriasAtivas = EnumSet.allOf(TipoEvento.class);
	}

	public void filtrarInit()
	{
		filtro.limpar();
		filtrar();
	}
	
	public void filtrar()
	{
		this.opcoes = entidadeDAO.buscar(filtro);
	}

	protected void podeRemover(Assunto entidade) throws RelacaoException
	{
		if(entidade.getQuestoes().size() > 0)
		throw new RelacaoException("Não foi possível remover o(a) " + nome + ". "
		+ "Existem Questões relacionadas.");
	}

}
