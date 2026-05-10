package matematica.avancado.combinatoria.nivel3package;

import java.util.Random;

import util.Algebra;
import matematica.DefinicaoCores;
import matematica.ParCor;
import matematica.avancado.combinatoria.AuxCombinacao;

public class ProblemaCombinacaoRepeticao
{
	public String texto;

	public int n,k;

	public ProblemaCombinacaoRepeticao(String texto)
	{
		super();
		this.texto = texto;
	}

	public void gerarValores()
	{
		Random rand = new Random();
		n = 5 + rand.nextInt(4);
		k = 3 + rand.nextInt(n-4);
	}

	public String resolucao()
	{
		String resolucaoLatex = "";
		resolucaoLatex += formula() + "\\\\";
		resolucaoLatex += "n=" + n +",\\quad k="+k+"\\\\";
		int newN=n+k-1;
		resolucaoLatex += "CR("+n+","+k+")=\\dfrac{("+ n+"+"+k+"-1" + ")!}{"+k+"!\\cdot("+n+"- 1)!}\\\\";
		resolucaoLatex += "CR("+n+","+k+")=\\dfrac{"+ newN + "!}{"+k+"!\\cdot"+(n-1)+"!}\\\\";

		if((n-1)>k)
		{
			resolucaoLatex += "CR("+n+","+k+")=\\dfrac{" + AuxCombinacao.fatorialString(newN,(newN-k))+"\\cdot "+(newN-k)+"!}{"+k+"!\\cdot"+(newN-k)+"!}\\\\";
			resolucaoLatex += "CR("+n+","+k+")=\\dfrac{"+AuxCombinacao.fatorialString(newN,(newN-k))+"}{"+k+"!}\\\\";
			resolucaoLatex += "CR("+n+","+k+")=\\dfrac{"+AuxCombinacao.fatorialString(newN,(newN-k))+"}{"+AuxCombinacao.fatorialString(k)+"}\\\\";
			resolucaoLatex += "CR("+n+","+k+")=\\dfrac{"+Algebra.fatorial(newN,(newN-k))+"}{"+Algebra.fatorial(k)+"}="+resultado();
		}
		else
		{
			resolucaoLatex += "CR("+n+","+k+")=\\dfrac{" + AuxCombinacao.fatorialString(newN,k)+"\\cdot "+k+"!}{"+k+"!\\cdot"+(newN-k)+"!}\\\\";
			resolucaoLatex += "CR("+n+","+k+")=\\dfrac{"+AuxCombinacao.fatorialString(newN,k)+"}{"+(newN-k)+"!}\\\\";
			resolucaoLatex += "CR("+n+","+k+")=\\dfrac{"+AuxCombinacao.fatorialString(newN,k)+"}{"+AuxCombinacao.fatorialString(newN-k)+"}\\\\";
			resolucaoLatex += "CR("+n+","+k+")=\\dfrac{"+Algebra.fatorial(newN,k)+"}{"+Algebra.fatorial(newN-k)+"}="+resultado();
		}

		return resolucaoLatex;

	}

	private String formula()
	{
		return "\\text{Combinação com Repetição}\\\\ "
		+ParCor.formula("CR(n, k)=\\binom{n+k-1}{k}=\\dfrac{(n + k - 1)!}{k! \\cdot (n - 1)!}");
	}

	public String resultado()
	{
		return ""+Algebra.fatorial(n+k-1)/(Algebra.fatorial(k)*Algebra.fatorial(n-1));
	}

	public String getPergunta()
	{
		String pergunta = texto;
		if(pergunta.contains("$a"))
			pergunta = pergunta.replace("$a", k+"");
		
		if(pergunta.contains("$b"))
			pergunta = pergunta.replace("$b", n+"");
		
		return pergunta;
	}

	public ProblemaCombinacaoRepeticao clone()
	{
		return new ProblemaCombinacaoRepeticao(texto);
	}
}
