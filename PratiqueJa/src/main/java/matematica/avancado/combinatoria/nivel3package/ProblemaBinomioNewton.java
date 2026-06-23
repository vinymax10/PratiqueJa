package matematica.avancado.combinatoria.nivel3package;

import java.util.Random;

import matematica.ParCor;
import matematica.avancado.combinatoria.AuxCombinacao;

public class ProblemaBinomioNewton
{
	public String texto;

	public int n,c,k,p;

	public ProblemaBinomioNewton(String texto)
	{
		super();
		this.texto = texto;
	}

	public void gerarValores()
	{
		Random rand = new Random();
		n = 3 + rand.nextInt(3);
		c = 2 + rand.nextInt(2);
		k = 1 + rand.nextInt(n-2);
		p = n - k;
	}

	public String[] resolucao()
	{
		int binom = AuxCombinacao.combinacao(n, k);
		int cPow = AuxCombinacao.potencia(c, k);
		int coef = binom * cPow;

		String passoIntroducao = "Pelo Binômio de Newton, o termo geral da expansão de \\((a+b)^n\\) é:";
		String passoFormula = "\\(" + ParCor.formula("T_{k+1} = \\binom{n}{k}\\,a^{\\,n-k}\\,b^{k}") + "\\)";
		String passoSubstituicao = "Aqui \\(a = x\\), \\(b = " + c + "\\) e \\(n = " + n + "\\). O termo em \\(x^{" + p + "}\\) exige \\(n-k = " + p + "\\), logo \\(k = " + k + "\\):";
		String passoExpansao = "\\(T_{" + (k+1) + "} = \\binom{" + n + "}{" + k + "}\\,x^{" + p + "}\\cdot " + potencia(""+c, k) + " =\\)";
		String passoResultado = "\\(" + binom + " \\cdot " + cPow + "\\,x^{" + p + "} = \\mathbf{" + coef + "}\\,x^{" + p + "}\\)";

		return new String[]{passoIntroducao, passoFormula, passoSubstituicao, passoExpansao, passoResultado};
	}

	private String potencia(String base, int exp)
	{
		return exp == 1 ? base : base + "^{" + exp + "}";
	}

	public String resultado()
	{
		return "" + (AuxCombinacao.combinacao(n, k) * AuxCombinacao.potencia(c, k));
	}

	public String getPergunta()
	{
		String pergunta = texto;
		if(pergunta.contains("$n"))
			pergunta = pergunta.replace("$n", n+"");

		if(pergunta.contains("$c"))
			pergunta = pergunta.replace("$c", c+"");

		if(pergunta.contains("$p"))
			pergunta = pergunta.replace("$p", p+"");

		return pergunta;
	}

	public ProblemaBinomioNewton clone()
	{
		return new ProblemaBinomioNewton(texto);
	}
}
