package bean.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bean.usuario.filtro.FiltroAcesso;
import dao.seguranca.AcessoDAO;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.seguranca.Acesso;

@Named
@SessionScoped
public class AcessoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private AcessoDAO acessoDAO;

	@Inject
	private Acesso acesso;

	@Inject
	FiltroAcesso filtroAcesso;

	private List<Acesso> acessos = new ArrayList<Acesso>();

	public void filtrar()
	{
		this.acessos = acessoDAO.buscar(filtroAcesso);
	}

	public String finalizadoToggle(Acesso acesso)
	{
//		acesso.set();
//		acessoDAO.salvar(acesso);
		return "";
	}
	
	public AcessoDAO getAcessoDAO()
	{
		return acessoDAO;
	}

	public void setAcessoDAO(AcessoDAO acessoDAO)
	{
		this.acessoDAO = acessoDAO;
	}

	public Acesso getAcesso()
	{
		return acesso;
	}

	public void setAcesso(Acesso acesso)
	{
		this.acesso = acesso;
	}

	public FiltroAcesso getFiltroAcesso()
	{
		return filtroAcesso;
	}

	public void setFiltroAcesso(FiltroAcesso filtroAcesso)
	{
		this.filtroAcesso = filtroAcesso;
	}

	public List<Acesso> getAcessos()
	{
		return acessos;
	}

	public void setAcessos(List<Acesso> acessos)
	{
		this.acessos = acessos;
	}

}
