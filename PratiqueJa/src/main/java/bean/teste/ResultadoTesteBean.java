package bean.teste;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.FilterMeta;

import dao.teste.ResultadoTesteDAO;
import filtro.teste.FiltroResultadoTeste;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import modelo.teste.ResultadoTeste;
import modelo.usuario.Usuario;
import web.session.Sessao;
import web.session.TabStateManager;

@Data
@Named
@ViewScoped
public class ResultadoTesteBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private ResultadoTesteDAO resultadoTesteDAO;

	@Inject
	private FiltroResultadoTeste filtro;

	@Inject
	private TabStateManager tabState;

	private List<ResultadoTeste> resultadosTestes = new ArrayList<>();

	private List<ResultadoTeste> resultadosTestesUsuario = new ArrayList<>();

	private List<ResultadoTeste> resultadosTestesFiltrados;

	private List<FilterMeta> filterBy;

	public void filtrar()
	{
		filtro.setUsuario(null);
		this.resultadosTestes = resultadoTesteDAO.buscar(filtro);
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
		this.resultadosTestes = resultadoTesteDAO.buscar(filtro);
	}

	@PostConstruct
	public void init()
	{
		if(tabState.hasState(FiltroResultadoTeste.class))
			filtro = tabState.getState(FiltroResultadoTeste.class);

		Usuario usuario = Sessao.getUsuarioLogado();
		if(usuario != null)
		{
			FiltroResultadoTeste filtroUsuario = new FiltroResultadoTeste();
			filtroUsuario.setUsuario(usuario);
			this.resultadosTestesUsuario = resultadoTesteDAO.buscar(filtroUsuario);
		}
	}
}
