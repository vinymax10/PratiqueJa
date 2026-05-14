package bean.exercicio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.FilterMeta;

import dao.exercicio.ResultadoExercicioDAO;
import filtro.exercicio.FiltroResultadoExercicio;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import modelo.exercicio.ResultadoExercicio;
import modelo.usuario.Usuario;
import web.session.Sessao;
import web.session.TabStateManager;

@Data
@Named
@ViewScoped
public class ResultadoExercicioBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private ResultadoExercicioDAO resultadoExercicioDAO;

	@Inject
	private FiltroResultadoExercicio filtro;

	@Inject
	private TabStateManager tabState;

	private List<ResultadoExercicio> resultadosExercicios = new ArrayList<>();

	private List<ResultadoExercicio> resultadosExerciciosUsuario = new ArrayList<>();

	private List<ResultadoExercicio> resultadosExerciciosFiltrados;

	private List<FilterMeta> filterBy;

	public void filtrar()
	{
		filtro.setUsuario(null);
		this.resultadosExercicios = resultadoExercicioDAO.buscar(filtro);
		tabState.putState(filtro);
	}

	public void filtrarInit()
	{
		filtro.limpar();
		filtrar();
	}

	public void filtrarUsuario()
	{
		Usuario usuarioLogado = Sessao.getUsuarioLogado();
		filtro.setUsuario(usuarioLogado);
		this.resultadosExercicios = resultadoExercicioDAO.buscar(filtro);
	}

	@PostConstruct
	public void init()
	{
		if(tabState.hasState(FiltroResultadoExercicio.class))
			filtro = tabState.getState(FiltroResultadoExercicio.class);

		Usuario usuario = Sessao.getUsuarioLogado();
		if(usuario != null)
		{
			FiltroResultadoExercicio filtroUsuario = new FiltroResultadoExercicio();
			filtroUsuario.setUsuario(usuario);
			this.resultadosExerciciosUsuario = resultadoExercicioDAO.buscar(filtroUsuario);
		}
	}
}
