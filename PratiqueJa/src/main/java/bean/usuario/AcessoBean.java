package bean.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dao.seguranca.AcessoDAO;
import filtro.seguranca.FiltroAcesso;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import modelo.seguranca.Acesso;

@Data
@Named
@ViewScoped
public class AcessoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private AcessoDAO acessoDAO;

	@Inject
	private FiltroAcesso filtro;

	private List<Acesso> acessos = new ArrayList<>();

	@PostConstruct
	public void init()
	{
		filtrar();
	}

	public void filtrar()
	{
		this.acessos = acessoDAO.buscar(filtro);
	}

	public void filtrarInit()
	{
		filtro.limpar();
		filtrar();
	}
}
