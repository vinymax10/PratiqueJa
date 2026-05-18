package bean.teste;

import java.util.EnumSet;

import bean.PaiBean;
import dao.teste.TestePadraoDAO;
import filtro.teste.FiltroTestePadrao;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.auditoria.TipoEvento;
import modelo.seguranca.PermissaoPadrao;
import modelo.teste.TestePadrao;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class TestePadraoBean extends PaiBean<TestePadrao, TestePadraoDAO, PermissaoPadrao<TestePadrao>>
{
	@Inject
	private FiltroTestePadrao filtro;

	public TestePadraoBean()
	{
		super(TestePadrao.class, "Teste Padrão");

		urlCadastro = "/administracao/conteudo/teste-padrao/form.xhtml";
		urlLista = "/administracao/conteudo/teste-padrao/list.xhtml";

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
		if(tabState.hasState(FiltroTestePadrao.class))
			filtro = tabState.getState(FiltroTestePadrao.class);
	}
}
