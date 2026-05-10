package matematica.avancado.combinatoria.nivel2package;

import java.util.Random;

import util.Algebra;
import matematica.DefinicaoCores;
import matematica.ParCor;
import matematica.avancado.combinatoria.AuxCombinacao;

public class ProblemaCombinacaoSimples
{
	public String texto;

	public int n,k;

	public ProblemaCombinacaoSimples(String texto)
	{
		super();
		this.texto = texto;
	}

	public void gerarValores()
	{
		Random rand = new Random();
		n = 6 + rand.nextInt(5);
		k = 3 + rand.nextInt(n-4);
	}

	public String resolucao()
	{
		String resolucaoLatex = "";
		resolucaoLatex += formulaArranjoSimples() + "\\\\";
		resolucaoLatex += "n=" + n +",\\quad k="+k+"\\\\";
		resolucaoLatex += "C("+n+","+k+")=\\dfrac{"+ n + "!}{"+k+"!\\cdot("+n+"-"+k+")!}\\\\";
		if((n-k)>k)
		{
			resolucaoLatex += "C("+n+","+k+")=\\dfrac{" + AuxCombinacao.fatorialString(n,(n-k))+"\\cdot "+(n-k)+"!}{"+k+"!\\cdot"+(n-k)+"!}\\\\";
			resolucaoLatex += "C("+n+","+k+")=\\dfrac{"+AuxCombinacao.fatorialString(n,(n-k))+"}{"+k+"!}\\\\";
			resolucaoLatex += "C("+n+","+k+")=\\dfrac{"+AuxCombinacao.fatorialString(n,(n-k))+"}{"+AuxCombinacao.fatorialString(k)+"}=";
			resolucaoLatex += "\\dfrac{"+Algebra.fatorial(n,(n-k))+"}{"+Algebra.fatorial(k)+"}="+resultado();
		}
		else
		{
			resolucaoLatex += "C("+n+","+k+")=\\dfrac{" + AuxCombinacao.fatorialString(n,k)+"\\cdot "+k+"!}{"+k+"!\\cdot"+(n-k)+"!}\\\\";
			resolucaoLatex += "C("+n+","+k+")=\\dfrac{"+AuxCombinacao.fatorialString(n,k)+"}{"+(n-k)+"!}\\\\";
			resolucaoLatex += "C("+n+","+k+")=\\dfrac{"+AuxCombinacao.fatorialString(n,k)+"}{"+AuxCombinacao.fatorialString(n-k)+"}=";
			resolucaoLatex += "\\dfrac{"+Algebra.fatorial(n,k)+"}{"+Algebra.fatorial(n-k)+"}="+resultado();
		}

		return resolucaoLatex;

	}

	private String formulaArranjoSimples()
	{
		return "\\text{Combinação Simples}\\\\"
		+ParCor.formula("C(n,k)=\\dfrac{n!}{k!\\cdot(n-k)!}");
	}

	public String resultado()
	{
		return ""+Algebra.fatorial(n)/(Algebra.fatorial(k)*Algebra.fatorial(n-k));
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

	public ProblemaCombinacaoSimples clone()
	{
		return new ProblemaCombinacaoSimples(texto);
	}
}
