package Matematica.Basico.RegraTres;

import java.util.Random;

import Auxiliar.Algebra;
import Matematica.DefinicaoCores;
import Matematica.ParCor;
import Matematica.Avancado.Combinatoria.AuxCombinacao;

public class ProblemaPermutacaoSimples
{
	public String texto;

	public int n;

	public ProblemaPermutacaoSimples(String texto)
	{
		super();
		this.texto = texto;
	}

	public void gerarValores()
	{
		Random rand = new Random();
		n = 3 + rand.nextInt(5);
	}

	public String resolucao()
	{
		String resolucaoLatex = "";
		resolucaoLatex += formulaPermutacaoSimples() + "\\\\";
		resolucaoLatex += "n=" + n + "\\\\";
		resolucaoLatex += "P("+n+")=" + n + "!\\\\";
		resolucaoLatex += "P("+n+")=" + AuxCombinacao.fatorialString(n)+"="+resultado();
		
		return resolucaoLatex;
	}

	private String formulaPermutacaoSimples()
	{
		return ParCor.formula("\\text{Permutação Simples}\\\\P(n)=n!");
	}

	public String resultado()
	{
		return ""+Algebra.fatorial(n);
	}

	public String getPergunta()
	{
		String pergunta = texto;
		if(pergunta.contains("$n"))
			pergunta = pergunta.replace("$n", n+"");

		return pergunta;
	}

	public ProblemaPermutacaoSimples clone()
	{
		return new ProblemaPermutacaoSimples(texto);
	}
}
