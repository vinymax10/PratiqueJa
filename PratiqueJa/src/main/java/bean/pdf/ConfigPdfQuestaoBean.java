package bean.pdf;

import bean.PaiBean;
import dao.pdf.ConfigPdfQuestaoDAO;
import filtro.pdf.FiltroConfigPdfQuestao;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.pdf.ConfigPdfQuestao;
import modelo.seguranca.PermissaoPadrao;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class ConfigPdfQuestaoBean extends PaiBean<ConfigPdfQuestao, ConfigPdfQuestaoDAO, PermissaoPadrao<ConfigPdfQuestao>>
{
	@Inject
	private FiltroConfigPdfQuestao filtro;

	public ConfigPdfQuestaoBean()
	{
		super(ConfigPdfQuestao.class, "Config PDF Questão");

		urlCadastro = "/administracao/conteudo/pdf/questao/config-questao/form.xhtml";
		urlLista    = "/administracao/conteudo/pdf/questao/config-questao/list.xhtml";
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
		if (tabState.hasState(FiltroConfigPdfQuestao.class))
			filtro = tabState.getState(FiltroConfigPdfQuestao.class);
	}
}
