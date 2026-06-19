package bean.pdf;

import bean.PaiBean;
import dao.pdf.ConfigPdfExercicioDAO;
import filtro.pdf.FiltroConfigPdfExercicio;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.pdf.ConfigPdfExercicio;
import modelo.seguranca.PermissaoPadrao;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class ConfigPdfExercicioBean extends PaiBean<ConfigPdfExercicio, ConfigPdfExercicioDAO, PermissaoPadrao<ConfigPdfExercicio>>
{
	@Inject
	private FiltroConfigPdfExercicio filtro;

	public ConfigPdfExercicioBean()
	{
		super(ConfigPdfExercicio.class, "Config PDF Exercício");

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
		if (tabState.hasState(FiltroConfigPdfExercicio.class))
			filtro = tabState.getState(FiltroConfigPdfExercicio.class);
	}
}
