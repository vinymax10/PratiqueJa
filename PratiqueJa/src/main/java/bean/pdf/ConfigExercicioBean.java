package bean.pdf;

import bean.PaiBean;
import dao.pdf.ConfigExercicioDAO;
import filtro.pdf.FiltroConfigExercicio;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.pdf.ConfigExercicio;
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

		urlCadastro = "/administracao/conteudo/pdf/exercicio/config-exercicio/form.xhtml";
		urlLista    = "/administracao/conteudo/pdf/exercicio/config-exercicio/list.xhtml";
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
