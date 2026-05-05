package Bean.Usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import DAO.Usuario.AcessoDAO;
import Modelo.Usuario.Acesso;

@Named
@ViewScoped
public class UsuariosLogadosBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private AcessoDAO acessoDAO;

	private List<Acesso> acessos = new ArrayList<Acesso>();
	
	@PostConstruct
	public void init()
	{
		acessos=acessoDAO.acessosAtivos();
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