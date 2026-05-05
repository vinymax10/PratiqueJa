package Bean.Instagram;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import Modelo.Instagram.PerfilCriador;

@Named
@SessionScoped
public class PerfilCriadorBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<PerfilCriador> opcoes;

	@PostConstruct
	public void init()
	{
		opcoes = Arrays.asList(PerfilCriador.values());
	}

	public List<PerfilCriador> getOpcoes()
	{
		return opcoes;
	}

	public void setOpcoes(List<PerfilCriador> opcoes)
	{
		this.opcoes = opcoes;
	}
}
