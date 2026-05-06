package bean.teste;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.primefaces.model.FilterMeta;

import dao.teste.ResultadoTesteDAO;
import modelo.teste.ResultadoTeste;
import modelo.usuario.Usuario;
import session.SessionContext;
import bean.teste.filtro.FiltroResultadoTeste;
import bean.usuario.UsuarioBean;

@Named
@ViewScoped
public class ResultadoTesteBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private ResultadoTesteDAO resultadoTesteDAO;

	@Inject
	private FiltroResultadoTeste filtroResultadoTeste;
	
	@Inject
	private UsuarioBean usuarioBean;

	private List<ResultadoTeste> resultadosTestes = new ArrayList<ResultadoTeste>();
	
	private List<ResultadoTeste> resultadosTestesUsuario = new ArrayList<ResultadoTeste>();

	private List<ResultadoTeste> resultadosTestesFiltrados;

	private List<FilterMeta> filterBy;
	
	public void filtrar()
	{
		filtroResultadoTeste.setUsuario(null);
		this.resultadosTestes = resultadoTesteDAO.buscar(filtroResultadoTeste);
	}
	
	public void filtrarUsuario()
	{
		filtroResultadoTeste.setUsuario(usuarioBean.getUsuario());
		this.resultadosTestes = resultadoTesteDAO.buscar(filtroResultadoTeste);
	}

	@PostConstruct
	public void init()
	{
		Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("UsuarioLogado");
		filtroResultadoTeste.setUsuario(usuario);
		this.resultadosTestesUsuario = resultadoTesteDAO.buscar(filtroResultadoTeste);
	}

	public FiltroResultadoTeste getFiltroResultadoTeste()
	{
		return filtroResultadoTeste;
	}

	public void setFiltroResultadoTeste(FiltroResultadoTeste filtroResultadoTeste)
	{
		this.filtroResultadoTeste = filtroResultadoTeste;
	}

	public List<ResultadoTeste> getResultadosTestes()
	{
		return resultadosTestes;
	}

	public void setResultadosTestes(List<ResultadoTeste> resultadosTestes)
	{
		this.resultadosTestes = resultadosTestes;
	}

	public List<ResultadoTeste> getResultadosTestesUsuario()
	{
		return resultadosTestesUsuario;
	}

	public void setResultadosTestesUsuario(List<ResultadoTeste> resultadosTestesUsuario)
	{
		this.resultadosTestesUsuario = resultadosTestesUsuario;
	}

	public List<ResultadoTeste> getResultadosTestesFiltrados()
	{
		return resultadosTestesFiltrados;
	}

	public void setResultadosTestesFiltrados(List<ResultadoTeste> resultadosTestesFiltrados)
	{
		this.resultadosTestesFiltrados = resultadosTestesFiltrados;
	}

	public List<FilterMeta> getFilterBy()
	{
		return filterBy;
	}

	public void setFilterBy(List<FilterMeta> filterBy)
	{
		this.filterBy = filterBy;
	}
	
}
