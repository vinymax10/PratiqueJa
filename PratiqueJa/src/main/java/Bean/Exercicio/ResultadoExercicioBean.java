package Bean.Exercicio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.FilterMeta;

import Bean.Exercicio.Filtro.FiltroResultadoExercicio;
import Bean.Usuario.UsuarioBean;
import DAO.Exercicio.ResultadoExercicioDAO;
import Modelo.Exercicio.ResultadoExercicio;
import Modelo.Usuario.Usuario;
import Session.SessionContext;

@Named
@ViewScoped
public class ResultadoExercicioBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private ResultadoExercicioDAO resultadoExercicioDAO;

	@Inject
	private ResultadoExercicio resultadoExercicio;

	@Inject
	private FiltroResultadoExercicio filtroResultadoExercicio;
	
	@Inject
	private UsuarioBean usuarioBean;

	private List<ResultadoExercicio> resultadosExercicios = new ArrayList<ResultadoExercicio>();
	
	private List<ResultadoExercicio> resultadosExerciciosUsuario = new ArrayList<ResultadoExercicio>();

	private List<ResultadoExercicio> resultadosExerciciosFiltrados;

	private List<FilterMeta> filterBy;
	
	public void filtrar()
	{
		filtroResultadoExercicio.setUsuario(null);
		this.resultadosExercicios = resultadoExercicioDAO.buscar(filtroResultadoExercicio);
	}
	
	public void filtrarUsuario()
	{
		filtroResultadoExercicio.setUsuario(usuarioBean.getUsuario());
		this.resultadosExercicios = resultadoExercicioDAO.buscar(filtroResultadoExercicio);
	}

	@PostConstruct
	public void init()
	{
		Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("UsuarioLogado");
		filtroResultadoExercicio.setUsuario(usuario);
		this.resultadosExerciciosUsuario = resultadoExercicioDAO.buscar(filtroResultadoExercicio);
	}
	
	public ResultadoExercicioDAO getResultadoExercicioDAO()
	{
		return resultadoExercicioDAO;
	}

	public void setResultadoExercicioDAO(ResultadoExercicioDAO resultadoExercicioDAO)
	{
		this.resultadoExercicioDAO = resultadoExercicioDAO;
	}

	public ResultadoExercicio getResultadoExercicio()
	{
		return resultadoExercicio;
	}

	public void setResultadoExercicio(ResultadoExercicio resultadoExercicio)
	{
		this.resultadoExercicio = resultadoExercicio;
	}

	public FiltroResultadoExercicio getFiltroResultadoExercicio()
	{
		return filtroResultadoExercicio;
	}

	public void setFiltroResultadoExercicio(FiltroResultadoExercicio filtroResultadoExercicio)
	{
		this.filtroResultadoExercicio = filtroResultadoExercicio;
	}

	public List<ResultadoExercicio> getResultadosExercicios()
	{
		return resultadosExercicios;
	}

	public void setResultadosExercicios(List<ResultadoExercicio> resultadosExercicios)
	{
		this.resultadosExercicios = resultadosExercicios;
	}

	public List<ResultadoExercicio> getResultadosExerciciosFiltrados()
	{
		return resultadosExerciciosFiltrados;
	}

	public void setResultadosExerciciosFiltrados(List<ResultadoExercicio> resultadosExerciciosFiltrados)
	{
		this.resultadosExerciciosFiltrados = resultadosExerciciosFiltrados;
	}

	public List<FilterMeta> getFilterBy()
	{
		return filterBy;
	}

	public void setFilterBy(List<FilterMeta> filterBy)
	{
		this.filterBy = filterBy;
	}

	public List<ResultadoExercicio> getResultadosExerciciosUsuario()
	{
		return resultadosExerciciosUsuario;
	}

	public void setResultadosExerciciosUsuario(List<ResultadoExercicio> resultadosExerciciosUsuario)
	{
		this.resultadosExerciciosUsuario = resultadosExerciciosUsuario;
	}
}
