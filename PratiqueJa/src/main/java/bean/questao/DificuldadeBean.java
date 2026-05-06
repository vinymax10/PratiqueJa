package bean.questao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import modelo.questao.Dificuldade;


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
