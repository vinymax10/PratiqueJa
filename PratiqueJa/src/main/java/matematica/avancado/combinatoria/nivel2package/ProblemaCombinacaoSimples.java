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
		String res = "A combinação simples de \\(n\\) elementos tomados \\(k\\) a \\(k\\) é dada por:";
		res += "\\(\\\\\\)";
		res += "\\(" + ParCor.formula("C(n,k) = \\dfrac{n!}{k!\\cdot(n-k)!}") + "\\)";
		res += "\\(\\\\\\)";
		res += "Com \\(n = " + n + "\\) e \\(k = " + k + "\\):";
		res += "\\(\\\\\\)";
		res += "\\(C(" + n + "," + k + ") = \\dfrac{" + n + "!}{" + k + "!\\cdot(" + n + "-" + k + ")!} = \\\\ ";
		if((n-k)>k)
		{
			res += "\\dfrac{" + AuxCombinacao.fatorialString(n,(n-k)) + "\\cdot " + (n-k) + "!}{" + k + "!\\cdot" + (n-k) + "!} = \\\\ ";
			res += "\\dfrac{" + AuxCombinacao.fatorialString(n,(n-k)) + "}{" + k + "!} = \\\\ ";
			res += "\\dfrac{" + AuxCombinacao.fatorialString(n,(n-k)) + "}{" + AuxCombinacao.fatorialString(k) + "} = \\\\ ";
			res += "\\dfrac{" + Algebra.fatorial(n,(n-k)) + "}{" + Algebra.fatorial(k) + "} = \\mathbf{" + resultado() + "}\\)";
		}
		else
		{
			res += "\\dfrac{" + AuxCombinacao.fatorialString(n,k) + "\\cdot " + k + "!}{" + k + "!\\cdot" + (n-k) + "!} = \\\\ ";
			res += "\\dfrac{" + AuxCombinacao.fatorialString(n,k) + "}{" + (n-k) + "!} = \\\\ ";
			res += "\\dfrac{" + AuxCombinacao.fatorialString(n,k) + "}{" + AuxCombinacao.fatorialString(n-k) + "} = \\\\ ";
			res += "\\dfrac{" + Algebra.fatorial(n,k) + "}{" + Algebra.fatorial(n-k) + "} = \\mathbf{" + resultado() + "}\\)";
		}

		return res;
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
