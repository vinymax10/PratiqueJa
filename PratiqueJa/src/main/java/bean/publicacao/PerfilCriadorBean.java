package bean.publicacao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.Getter;
import modelo.publicacao.PerfilCriador;

@Named
@ApplicationScoped
public class PerfilCriadorBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Getter
	private final List<PerfilCriador> opcoes = Arrays.asList(PerfilCriador.values());
}
