package bean.exercicio;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import modelo.exercicio.TipoExercicio;


@Named
@SessionScoped
public class TipoExercicioBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<TipoExercicio> opcoes;

	@PostConstruct
	public void init()
	{
		opcoes = Arrays.asList(TipoExercicio.values());
	}

	public List<TipoExercicio> getOpcoes()
	{
		return opcoes;
	}

	public void setOpcoes(List<TipoExercicio> opcoes)
	{
		this.opcoes = opcoes;
	}
}
