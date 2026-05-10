package bean.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.PrimeFaces;

import filtro.usuario.FiltroControleAcesso;
import dao.usuario.ControleAcessoDAO;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.usuario.ControleAcesso;
import modelo.usuario.PerfilUsuario;
import modelo.usuario.Usuario;
import web.session.SessionContext;

@Named
@SessionScoped
public class ControleAcessoBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int numDownloadQuestao=5;
	
	private int numDownloadExercicio=5;
	
	private int numResolucaoExercicio=50;
	
	private int numResolucaoQuestao=50;

	private int numQuestaoFeita=50;
	
	@Inject
	private ControleAcessoDAO controleAcessoDAO;

	private Usuario usuario;
	
	private ControleAcesso controleAcesso;

	private String mensagem;
	
	@Inject
	private FiltroControleAcesso filtroControleAcesso;
	
	private List<ControleAcesso> controlesAcessos = new ArrayList<ControleAcesso>();

	public boolean estaLogado()
	{
		Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("UsuarioLogado");
		return usuario!=null;
	}
	
	public boolean verificaEstaLogado()
	{
		if(estaLogado())
		{
			return true;
		}
		else
		{
			PrimeFaces.current().executeScript("PF('loginWidget').show()");
			return false;
		}
	}
	
	public void filtrar()
	{
		this.controlesAcessos = controleAcessoDAO.buscar(filtroControleAcesso);
	}
	
	public void showUpgrade(String mensagem)
	{
		this.mensagem=mensagem;
		PrimeFaces.current().executeScript("PF('upgradeWidget').show()");
	}
	
	public boolean podeFazerDownloadMassa()
	{
		if(usuario==null)
			return false;
			
		if(usuario.getPerfil()==PerfilUsuario.Bronze)
			return false;
			
		return true;
	}
	
	public boolean podeFazerDownloadExercicio()
	{
		if(usuario==null||controleAcesso==null)
			return false;
		
		if(usuario.getPerfil()!=PerfilUsuario.Bronze)
			return true;
		else
		{
			if(controleAcesso.getNumDownloadExercicio()<numDownloadExercicio)
				return true;
		}
		return false;
	}
	
	public boolean podeFazerDownloadQuestao()
	{
		if(usuario==null||controleAcesso==null)
			return false;
		
		if(usuario.getPerfil()!=PerfilUsuario.Bronze)
			return true;
		else
		{
			if(controleAcesso.getNumDownloadQuestao()<numDownloadQuestao)
				return true;
		}
		return false;
	}
	
	public boolean podeResolucaoExercicio()
	{
		if(usuario==null||controleAcesso==null)
			return false;
		
		if(usuario.getPerfil()!=PerfilUsuario.Bronze)
			return true;
		else
		{
			if(controleAcesso.getNumResolucaoExercicio()<numResolucaoExercicio)
				return true;
		}
		return false;
	}
	
	public boolean podeResolucaoQuestao()
	{
		if(usuario==null||controleAcesso==null)
			return false;
		
		if(usuario.getPerfil()!=PerfilUsuario.Bronze)
			return true;
		else
		{
			if(controleAcesso.getNumResolucaoQuestao()<numResolucaoQuestao)
				return true;
		}
		return false;
	}
	
	public boolean podeFazerQuestao()
	{
		if(usuario==null||controleAcesso==null)
			return false;
		
		if(usuario.getPerfil()!=PerfilUsuario.Bronze)
			return true;
		else
		{
			if(controleAcesso.getNumQuestaoFeita()<numQuestaoFeita)
				return true;
		}
		return false;
	}
	
	public void registrarDownloadExercicio()
	{
		if(controleAcesso!=null)
		{
			controleAcesso.incrementaDownloadExercicio();
			controleAcessoDAO.salvar(controleAcesso);
		}
	}
	
	public void carrega()
	{
		usuario = (Usuario) SessionContext.getInstance().getAttribute("UsuarioLogado");
		controleAcesso = (ControleAcesso) SessionContext.getInstance().getAttribute("controleAcesso");
	}

	public void registrarDownloadQuestao(boolean massa)
	{
		if(controleAcesso!=null)
		{
			if(massa)
				controleAcesso.incrementaDownloadQuestaoMassa();
			else
				controleAcesso.incrementaDownloadQuestao();
			
			controleAcessoDAO.salvar(controleAcesso);
		}
	}
	
	public void registrarDownloadMassa()
	{
		if(controleAcesso!=null)
		{
			controleAcesso.incrementaDownloadMassa();
			controleAcessoDAO.salvar(controleAcesso);
		}
	}
	
	public void registrarResolucaoExercicio()
	{
		if(controleAcesso!=null)
		{
			controleAcesso.incrementaResolucaoExercicio();
			controleAcessoDAO.salvar(controleAcesso);
		}
	}
	
	public void registrarResolucaoQuestao()
	{
		if(controleAcesso!=null)
		{
			controleAcesso.incrementaResolucaoQuestao();
			controleAcessoDAO.salvar(controleAcesso);
		}
	}
	
	public void registrarQuestaoFeita()
	{
		if(controleAcesso!=null)
		{
			controleAcesso.incrementaQuestaoFeita();
			controleAcessoDAO.salvar(controleAcesso);
		}
	}
	
	public String getMensagem()
	{
		return mensagem;
	}

	public void setMensagem(String mensagem)
	{
		this.mensagem = mensagem;
	}

	public FiltroControleAcesso getFiltroControleAcesso()
	{
		return filtroControleAcesso;
	}

	public void setFiltroControleAcesso(FiltroControleAcesso filtroControleAcesso)
	{
		this.filtroControleAcesso = filtroControleAcesso;
	}

	public List<ControleAcesso> getControlesAcessos()
	{
		return controlesAcessos;
	}

	public void setControlesAcessos(List<ControleAcesso> controlesAcessos)
	{
		this.controlesAcessos = controlesAcessos;
	}
	
}
