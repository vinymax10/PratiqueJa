package Bean.Enum;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import Modelo.AssuntoCurso.Enum.Modulo;

@Named
@SessionScoped
public class ModuloBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<Modulo> opcoes;

	@PostConstruct
	public void init()
	{
		opcoes = Arrays.asList(Modulo.values());
	}

	public List<Modulo> getOpcoes()
	{
		return opcoes;
	}

	public void setOpcoes(List<Modulo> opcoes)
	{
		this.opcoes = opcoes;
	}
}
