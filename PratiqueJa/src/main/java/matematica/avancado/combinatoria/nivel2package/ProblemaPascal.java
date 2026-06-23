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

	public String[] resolucao()
	{
		switch(tipo)
		{
			case Subconjuntos: return resolucaoSubconjuntos();
			case Binomial: return resolucaoBinomial();
		}
		return new String[]{};
	}

	private String[] resolucaoSubconjuntos()
	{
		String passoIntroducao = "Cada um dos \\(n\\) elementos pode estar ou não no subconjunto, gerando \\(2^n\\) subconjuntos (a soma da linha \\(n\\) do Triângulo de Pascal):";
		String passoFormula = "\\(" + ParCor.formula("\\text{subconjuntos} = 2^n") + "\\)";
		String passoSubstituicao = "Com \\(n = " + n + "\\):";
		String passoResultado = "\\(2^{" + n + "} = \\mathbf{" + resultado() + "}\\)";
		return new String[]{passoIntroducao, passoFormula, passoSubstituicao, passoResultado};
	}

	private String[] resolucaoBinomial()
	{
		String passoIntroducao = "O coeficiente binomial conta de quantas formas escolher \\(k\\) elementos entre \\(n\\) (valor de uma posição no Triângulo de Pascal):";
		String passoFormula = "\\(" + ParCor.formula("\\binom{n}{k} = \\dfrac{n!}{k!\\,(n-k)!}") + "\\)";
		String passoSubstituicao = "Com \\(n = " + n + "\\) e \\(k = " + k + "\\):";
		String passoExpansao = "\\(\\binom{" + n + "}{" + k + "} = \\dfrac{" + n + "!}{" + k + "!\\,(" + n + "-" + k + ")!} =\\)";
		String passoResultado = "\\(\\dfrac{" + Algebra.fatorial(n) + "}{" + Algebra.fatorial(k) + " \\cdot " + Algebra.fatorial(n-k) + "} = \\mathbf{" + resultado() + "}\\)";
		return new String[]{passoIntroducao, passoFormula, passoSubstituicao, passoExpansao, passoResultado};
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
