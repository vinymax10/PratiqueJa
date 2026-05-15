package bean.academico;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;
import modelo.academico.Modulo;

@Data
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
}
