package bean.teste;

import java.util.EnumSet;

import bean.FilhoBean;
import dao.teste.ConteudoTesteDAO;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.auditoria.TipoEvento;
import modelo.teste.ConteudoTeste;
import modelo.teste.TestePadrao;

@Named
@ViewScoped
public class ConteudoTesteBean extends FilhoBean<ConteudoTeste, ConteudoTesteDAO>
{
	@Inject
	private TestePadraoBean testePadraoBean;

	public ConteudoTesteBean()
	{
		super(ConteudoTeste.class, "Conteúdo do Teste");
		auditoriasAtivas = EnumSet.allOf(TipoEvento.class);
	}

	public String cadastrar()
	{
		entidade = new ConteudoTeste();
		entidade.setTestePadrao(testePadraoBean.getEntidade());
		cadastro = true;
		return "";
	}
	
	public String adicionar()
	{
		return adicionar(
		() -> {
			TestePadrao testePadrao = testePadraoBean.getEntidade();
			entidade.setOrdem(testePadrao.getConteudosTeste().size());
			testePadrao.getConteudosTeste().add(entidade);
			if(testePadrao.getId()!=null)
				testePadrao=testePadraoBean.somenteSalvar();
			
			entidade=testePadrao.getConteudosTeste().get(entidade.getOrdem());
		});
	}

	public String salvar()
	{
		TestePadrao testePadrao = testePadraoBean.getEntidade();
		return salvar(
		() -> {
		    mapper.update(entidade, entidadeOriginal);
		},
		() -> {
			if(testePadrao.getId()!=null)
				testePadraoBean.somenteSalvar();
		});
	}
	
	public String remover()
	{
		return remover(
		() -> {
			TestePadrao testePadrao = testePadraoBean.getEntidade();
			testePadrao.getConteudosTeste().remove(entidade);
			if(testePadrao.getId()!=null)
				testePadrao=testePadraoBean.somenteSalvar();
			onRowReorder(testePadrao.getConteudosTeste());
		});
	}
	
}