package bean.configuracao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;
import modelo.configuracao.SistemaOperacional;


@Data
@Named
@SessionScoped
public class SistemaOperacionalBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<SistemaOperacional> opcoes;

	@PostConstruct
	public void init()
	{
		opcoes = Arrays.asList(SistemaOperacional.values());
	}

}
