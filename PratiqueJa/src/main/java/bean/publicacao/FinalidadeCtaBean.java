package bean.publicacao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import modelo.publicacao.FinalidadeCta;

@Named
@ApplicationScoped
public class FinalidadeCtaBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<FinalidadeCta> opcoes;

	@PostConstruct
	public void init()
	{
		opcoes = Arrays.asList(FinalidadeCta.values());
	}

	public List<FinalidadeCta> getOpcoes()
	{
		return opcoes;
	}

	public void setOpcoes(List<FinalidadeCta> opcoes)
	{
		this.opcoes = opcoes;
	}
}
