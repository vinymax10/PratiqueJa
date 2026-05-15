package bean.configuracao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import modelo.configuracao.SistemaOperacional;


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

	public List<SistemaOperacional> getOpcoes()
	{
		return opcoes;
	}

	public void setOpcoes(List<SistemaOperacional> opcoes)
	{
		this.opcoes = opcoes;
	}
}
