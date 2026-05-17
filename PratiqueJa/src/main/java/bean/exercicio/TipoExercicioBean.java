package bean.exercicio;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.Getter;
import modelo.exercicio.TipoExercicio;

@Named
@ApplicationScoped
public class TipoExercicioBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Getter
	private final List<TipoExercicio> opcoes = Arrays.asList(TipoExercicio.values());
}
