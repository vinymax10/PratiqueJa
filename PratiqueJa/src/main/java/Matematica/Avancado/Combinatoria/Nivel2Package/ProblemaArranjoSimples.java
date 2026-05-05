package Matematica.Avancado.Combinatoria.Nivel2Package;

import java.util.Random;

import Auxiliar.Algebra;
import Matematica.DefinicaoCores;
import Matematica.ParCor;
import Matematica.Avancado.Combinatoria.AuxCombinacao;

public class ProblemaArranjoSimples
{
	public String texto;

	public int n,k;

	public ProblemaArranjoSimples(String texto)
	{
		super();
		this.texto = texto;
	}

	public void gerarValores()
	{
		Random rand = new Random();
		n = 4 + rand.nextInt(4);
		k = 2 + rand.nextInt(n-2);
	}

	public String resolucao()
	{
		String resolucaoLatex = "";
		resolucaoLatex += formulaArranjoSimples() + "\\\\";
		resolucaoLatex += "n=" + n +",\\quad k="+k+"\\\\";
		resolucaoLatex += "A("+n+","+k+")=\\dfrac{"+ n + "!}{("+n+"-"+k+")!}\\\\";
		resolucaoLatex += "A("+n+","+k+")=\\dfrac{" + AuxCombinacao.fatorialString(n,(n-k))+"\\cdot "+(n-k)+"!}{"+(n-k)+"!}\\\\";
		resolucaoLatex += "A("+n+","+k+")="+AuxCombinacao.fatorialString(n,(n-k))+"="+resultado();

		return resolucaoLatex;
	}
	
	private String formulaArranjoSimples()
	{
		return "\\text{Arranjo Simples}\\\\"
		+ParCor.formula("A(n,k)=\\dfrac{n!}{(n-k)!}");
	}

	public String resultado()
	{
		return ""+Algebra.fatorial(n)/Algebra.fatorial(n-k);
	}

	public String getPergunta()
	{
		String pergunta = texto;
		if(pergunta.contains("$n"))
			pergunta = pergunta.replace("$n", n+"");
		
		if(pergunta.contains("$k"))
			pergunta = pergunta.replace("$k", k+"");
		
		return pergunta;
	}

	public ProblemaArranjoSimples clone()
	{
		return new ProblemaArranjoSimples(texto);
	}
}
