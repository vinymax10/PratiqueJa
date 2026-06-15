package matematica.avancado.combinatoria.nivel2package;

import java.util.Random;

import util.Algebra;
import matematica.ParCor;
import matematica.avancado.combinatoria.AuxCombinacao;

public class ProblemaPascal
{
	public String texto;

	public int n,k;
	public TipoPascal tipo;

	public ProblemaPascal(String texto, TipoPascal tipo)
	{
		super();
		this.texto = texto;
		this.tipo = tipo;
	}

	public void gerarValores()
	{
		Random rand = new Random();
		switch(tipo)
		{
			case Subconjuntos:
				n = 3 + rand.nextInt(6);
				break;
			case Binomial:
				n = 5 + rand.nextInt(4);
				k = 2 + rand.nextInt(n-3);
				break;
		}
	}

	public String resolucao()
	{
		switch(tipo)
		{
			case Subconjuntos: return resolucaoSubconjuntos();
			case Binomial: return resolucaoBinomial();
		}
		return "";
	}

	private String resolucaoSubconjuntos()
	{
		String res = "Cada um dos \\(n\\) elementos pode estar ou não no subconjunto, gerando \\(2^n\\) subconjuntos (a soma da linha \\(n\\) do Triângulo de Pascal):";
		res += "\\(\\\\\\)";
		res += "\\(" + ParCor.formula("\\text{subconjuntos} = 2^n") + "\\)";
		res += "\\(\\\\\\)";
		res += "Com \\(n = " + n + "\\):";
		res += "\\(\\\\\\)";
		res += "\\(2^{" + n + "} = \\mathbf{" + resultado() + "}\\)";
		return res;
	}

	private String resolucaoBinomial()
	{
		String res = "O coeficiente binomial conta de quantas formas escolher \\(k\\) elementos entre \\(n\\) (valor de uma posição no Triângulo de Pascal):";
		res += "\\(\\\\\\)";
		res += "\\(" + ParCor.formula("\\binom{n}{k} = \\dfrac{n!}{k!\\,(n-k)!}") + "\\)";
		res += "\\(\\\\\\)";
		res += "Com \\(n = " + n + "\\) e \\(k = " + k + "\\):";
		res += "\\(\\\\\\)";
		res += "\\(\\binom{" + n + "}{" + k + "} = \\dfrac{" + n + "!}{" + k + "!\\,(" + n + "-" + k + ")!} = \\\\ ";
		res += "\\dfrac{" + Algebra.fatorial(n) + "}{" + Algebra.fatorial(k) + " \\cdot " + Algebra.fatorial(n-k) + "} = \\mathbf{" + resultado() + "}\\)";
		return res;
	}

	public String resultado()
	{
		switch(tipo)
		{
			case Subconjuntos: return "" + AuxCombinacao.potencia(2, n);
			case Binomial: return "" + AuxCombinacao.combinacao(n, k);
		}
		return "";
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

	public ProblemaPascal clone()
	{
		return new ProblemaPascal(texto,tipo);
	}
}
