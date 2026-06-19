package bean.exercicio;

import bean.PaiBean;
import dao.exercicio.ConfigExercicioDAO;
import filtro.exercicio.FiltroConfigExercicio;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.exercicio.ConfigExercicio;
import modelo.seguranca.PermissaoPadrao;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class ConfigExercicioBean extends PaiBean<ConfigExercicio, ConfigExercicioDAO, PermissaoPadrao<ConfigExercicio>>
{
	@Inject
	private FiltroConfigExercicio filtro;

	public ConfigExercicioBean()
	{
		super(ConfigExercicio.class, "Config Exercício");

		urlCadastro = "/administracao/conteudo/exercicio/config-exercicio/form.xhtml";
		urlLista    = "/administracao/conteudo/exercicio/config-exercicio/list.xhtml";
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
		if (tabState.hasState(FiltroConfigExercicio.class))
			filtro = tabState.getState(FiltroConfigExercicio.class);
	}
}
