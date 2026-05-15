package bean.publicacao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.Data;
import modelo.publicacao.PerfilCriador;

@Data
@Named
@ApplicationScoped
public class PerfilCriadorBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<PerfilCriador> opcoes;

	@PostConstruct
	public void init()
	{
		opcoes = Arrays.asList(PerfilCriador.values());
	}
}
