package bean.academico;

import java.util.EnumSet;

import bean.ConfigBean;
import dao.academico.OrgaoDAO;
import exceptions.RelacaoException;
import filtro.academico.FiltroOrgao;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.auditoria.TipoEvento;
import modelo.academico.Orgao;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class OrgaoBean extends ConfigBean<Orgao, OrgaoDAO>
{
	@Inject
	private FiltroOrgao filtro;

	public OrgaoBean()
	{
		super(Orgao.class, "Órgão");
		auditoriasAtivas = EnumSet.allOf(TipoEvento.class);
	}

	@Override
	public void filtrar()
	{
		this.opcoes = entidadeDAO.buscar(filtro);
	}

	public void filtrarInit()
	{
		filtro.limpar();
		filtrar();
	}

	protected void podeRemover(Orgao entidade) throws RelacaoException
	{
		if(entidade.getQuestoes().size() > 0)
		throw new RelacaoException("Não foi possível remover o(a) " + nome + ". "
		+ "Existem Questões relacionadas.");
	}
}
