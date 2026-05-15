package bean.academico;

import java.util.EnumSet;

import bean.ConfigBean;
import dao.academico.BancaDAO;
import exceptions.RelacaoException;
import filtro.questao.FiltroBanca;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.auditoria.TipoEvento;
import modelo.academico.Banca;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class BancaBean extends ConfigBean<Banca, BancaDAO>
{
	@Inject
	private FiltroBanca filtro;

	public BancaBean()
	{
		super(Banca.class, "Banca");
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

	protected void podeRemover(Banca entidade) throws RelacaoException
	{
		if(entidade.getQuestoes().size() > 0)
		throw new RelacaoException("Não foi possível remover o(a) " + nome + ". "
		+ "Existem Questões relacionadas.");
	}
}
