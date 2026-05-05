package Bean.Questao.Enum;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import Modelo.Questao.Enum.Dificuldade;

@Named
@SessionScoped
public class DificuldadeBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<Dificuldade> opcoes;

	@PostConstruct
	public void init()
	{
		opcoes = Arrays.asList(Dificuldade.values());
	}

	public List<Dificuldade> getOpcoes()
	{
		return opcoes;
	}

	public void setOpcoes(List<Dificuldade> opcoes)
	{
		this.opcoes = opcoes;
	}
}
