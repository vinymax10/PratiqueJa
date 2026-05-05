package Bean.Instagram;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import Modelo.Instagram.FinalidadeCta;

@Named
@SessionScoped
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
