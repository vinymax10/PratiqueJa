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
		int newN=n+k-1;
		String res = "A combinação com repetição de \\(n\\) elementos tomados \\(k\\) a \\(k\\) é dada por:";
		res += "\\(\\\\\\)";
		res += "\\(" + ParCor.formula("CR(n,k) = \\binom{n+k-1}{k} = \\dfrac{(n+k-1)!}{k!\\cdot(n-1)!}") + "\\)";
		res += "\\(\\\\\\)";
		res += "Com \\(n = " + n + "\\) e \\(k = " + k + "\\):";
		res += "\\(\\\\\\)";
		res += "\\(CR(" + n + "," + k + ") = \\dfrac{(" + n + "+" + k + "-1)!}{" + k + "!\\cdot(" + n + "-1)!} = \\\\ ";
		res += "\\dfrac{" + newN + "!}{" + k + "!\\cdot" + (n-1) + "!} = \\\\ ";
		if((n-1)>k)
		{
			res += "\\dfrac{" + AuxCombinacao.fatorialString(newN,(newN-k)) + "\\cdot " + (newN-k) + "!}{" + k + "!\\cdot" + (newN-k) + "!} = \\\\ ";
			res += "\\dfrac{" + AuxCombinacao.fatorialString(newN,(newN-k)) + "}{" + k + "!} = \\\\ ";
			res += "\\dfrac{" + AuxCombinacao.fatorialString(newN,(newN-k)) + "}{" + AuxCombinacao.fatorialString(k) + "} = \\\\ ";
			res += "\\dfrac{" + Algebra.fatorial(newN,(newN-k)) + "}{" + Algebra.fatorial(k) + "} = \\mathbf{" + resultado() + "}\\)";
		}
		else
		{
			res += "\\dfrac{" + AuxCombinacao.fatorialString(newN,k) + "\\cdot " + k + "!}{" + k + "!\\cdot" + (newN-k) + "!} = \\\\ ";
			res += "\\dfrac{" + AuxCombinacao.fatorialString(newN,k) + "}{" + (newN-k) + "!} = \\\\ ";
			res += "\\dfrac{" + AuxCombinacao.fatorialString(newN,k) + "}{" + AuxCombinacao.fatorialString(newN-k) + "} = \\\\ ";
			res += "\\dfrac{" + Algebra.fatorial(newN,k) + "}{" + Algebra.fatorial(newN-k) + "} = \\mathbf{" + resultado() + "}\\)";
		}

		return res;
	}

	public String resultado()
	{
		return ""+(Algebra.fatorial(n+k-1, n-1)/Algebra.fatorial(k));
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
