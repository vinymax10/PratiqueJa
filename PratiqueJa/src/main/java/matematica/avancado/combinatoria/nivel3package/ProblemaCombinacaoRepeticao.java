package matematica.avancado.combinatoria.nivel3package;

import java.util.ArrayList;
import java.util.List;
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

	public String[] resolucao()
	{
		int newN=n+k-1;
		List<String> passos = new ArrayList<String>();
		passos.add("A combinação com repetição de \\(n\\) elementos tomados \\(k\\) a \\(k\\) é dada por:");
		passos.add("\\(" + ParCor.formula("CR(n,k) = \\binom{n+k-1}{k} = \\dfrac{(n+k-1)!}{k!\\cdot(n-1)!}") + "\\)");
		passos.add("Com \\(n = " + n + "\\) e \\(k = " + k + "\\):");
		passos.add("\\(CR(" + n + "," + k + ") = \\dfrac{(" + n + "+" + k + "-1)!}{" + k + "!\\cdot(" + n + "-1)!} =\\)");
		passos.add("\\(\\dfrac{" + newN + "!}{" + k + "!\\cdot" + (n-1) + "!} =\\)");
		if((n-1)>k)
		{
			passos.add("\\(\\dfrac{" + AuxCombinacao.fatorialString(newN,(newN-k)) + "\\cdot " + (newN-k) + "!}{" + k + "!\\cdot" + (newN-k) + "!} =\\)");
			passos.add("\\(\\dfrac{" + AuxCombinacao.fatorialString(newN,(newN-k)) + "}{" + k + "!} =\\)");
			passos.add("\\(\\dfrac{" + AuxCombinacao.fatorialString(newN,(newN-k)) + "}{" + AuxCombinacao.fatorialString(k) + "} =\\)");
			passos.add("\\(\\dfrac{" + Algebra.fatorial(newN,(newN-k)) + "}{" + Algebra.fatorial(k) + "} = \\mathbf{" + resultado() + "}\\)");
		}
		else
		{
			passos.add("\\(\\dfrac{" + AuxCombinacao.fatorialString(newN,k) + "\\cdot " + k + "!}{" + k + "!\\cdot" + (newN-k) + "!} =\\)");
			passos.add("\\(\\dfrac{" + AuxCombinacao.fatorialString(newN,k) + "}{" + (newN-k) + "!} =\\)");
			passos.add("\\(\\dfrac{" + AuxCombinacao.fatorialString(newN,k) + "}{" + AuxCombinacao.fatorialString(newN-k) + "} =\\)");
			passos.add("\\(\\dfrac{" + Algebra.fatorial(newN,k) + "}{" + Algebra.fatorial(newN-k) + "} = \\mathbf{" + resultado() + "}\\)");
		}

		return passos.toArray(new String[0]);
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
