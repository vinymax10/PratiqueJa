package Bean.Usuario.Enum;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import Modelo.Usuario.Enum.PerfilUsuario;

@Named
@SessionScoped
public class PerfilUsuarioBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<PerfilUsuario> opcoes;

	@PostConstruct
	public void init()
	{
		opcoes = Arrays.asList(PerfilUsuario.values());
	}

	public List<PerfilUsuario> getOpcoes()
	{
		return opcoes;
	}

	public void setOpcoes(List<PerfilUsuario> opcoes)
	{
		this.opcoes = opcoes;
	}
}
