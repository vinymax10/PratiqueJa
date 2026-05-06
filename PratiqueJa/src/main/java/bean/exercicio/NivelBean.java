package bean.exercicio;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import modelo.exercicio.Nivel;


@Named
@SessionScoped
public class NivelBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<Nivel> opcoes;

	@PostConstruct
	public void init()
	{
		opcoes = Arrays.asList(Nivel.values());
	}

	public List<Nivel> getOpcoes()
	{
		return opcoes;
	}

	public void setOpcoes(List<Nivel> opcoes)
	{
		this.opcoes = opcoes;
	}
}
