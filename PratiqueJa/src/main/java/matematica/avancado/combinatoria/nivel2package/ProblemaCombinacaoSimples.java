package matematica.avancado.combinatoria.nivel2package;

import java.util.ArrayList;
import java.util.List;
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
		n = 6 + rand.nextInt(7);
		k = 3 + rand.nextInt(n-4);
	}

	public String[] resolucao()
	{
		List<String> passos = new ArrayList<String>();
		passos.add("A combinação simples de \\(n\\) elementos tomados \\(k\\) a \\(k\\) é dada por:");
		passos.add("\\(" + ParCor.formula("C(n,k) = \\dfrac{n!}{k!\\cdot(n-k)!}") + "\\)");
		passos.add("Com \\(n = " + n + "\\) e \\(k = " + k + "\\):");
		passos.add("\\(C(" + n + "," + k + ") = \\dfrac{" + n + "!}{" + k + "!\\cdot(" + n + "-" + k + ")!} =\\)");
		if((n-k)>k)
		{
			passos.add("\\(\\dfrac{" + AuxCombinacao.fatorialString(n,(n-k)) + "\\cdot " + (n-k) + "!}{" + k + "!\\cdot" + (n-k) + "!} =\\)");
			passos.add("\\(\\dfrac{" + AuxCombinacao.fatorialString(n,(n-k)) + "}{" + k + "!} =\\)");
			passos.add("\\(\\dfrac{" + AuxCombinacao.fatorialString(n,(n-k)) + "}{" + AuxCombinacao.fatorialString(k) + "} =\\)");
			passos.add("\\(\\dfrac{" + Algebra.fatorial(n,(n-k)) + "}{" + Algebra.fatorial(k) + "} = \\mathbf{" + resultado() + "}\\)");
		}
		else
		{
			passos.add("\\(\\dfrac{" + AuxCombinacao.fatorialString(n,k) + "\\cdot " + k + "!}{" + k + "!\\cdot" + (n-k) + "!} =\\)");
			passos.add("\\(\\dfrac{" + AuxCombinacao.fatorialString(n,k) + "}{" + (n-k) + "!} =\\)");
			passos.add("\\(\\dfrac{" + AuxCombinacao.fatorialString(n,k) + "}{" + AuxCombinacao.fatorialString(n-k) + "} =\\)");
			passos.add("\\(\\dfrac{" + Algebra.fatorial(n,k) + "}{" + Algebra.fatorial(n-k) + "} = \\mathbf{" + resultado() + "}\\)");
		}

		return passos.toArray(new String[0]);
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
