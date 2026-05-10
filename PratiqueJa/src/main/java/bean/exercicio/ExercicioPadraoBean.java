package bean.exercicio;

import java.util.EnumSet;

import bean.PaiBean;
import dao.exercicio.ExercicioPadraoDAO;
import filtro.exercicio.FiltroExercicioPadrao;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.auditoria.TipoEvento;
import modelo.exercicio.ExercicioPadrao;
import modelo.permissao.PermissaoPadrao;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class ExercicioPadraoBean extends PaiBean<ExercicioPadrao, ExercicioPadraoDAO, PermissaoPadrao<ExercicioPadrao>>
{
	@Inject
	private FiltroExercicioPadrao filtro;

	public ExercicioPadraoBean()
	{
		super(ExercicioPadrao.class, "Exercício Padrão");

		urlCadastro = "/administracao/exercicio-padrao/form.xhtml";
		urlLista = "/administracao/exercicio-padrao/list.xhtml";

		auditoriasAtivas = EnumSet.allOf(TipoEvento.class);
	}

	public void filtrar()
	{
		this.lista = entidadeDAO.buscar(filtro);
		tabState.putState(filtro);
	}

	public void filtrarInit()
	{
		filtro.limpar();
		filtrar();
	}

	@PostConstruct
	public void init()
	{
		if(tabState.hasState(FiltroExercicioPadrao.class))
			filtro = tabState.getState(FiltroExercicioPadrao.class);
	}

}
