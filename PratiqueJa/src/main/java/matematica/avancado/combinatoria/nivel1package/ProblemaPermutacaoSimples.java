package matematica.avancado.combinatoria.nivel1package;

import java.util.Random;

import util.Algebra;
import matematica.DefinicaoCores;
import matematica.ParCor;
import matematica.avancado.combinatoria.AuxCombinacao;

public class ProblemaPermutacaoSimples
{
	public String texto;

	public int n;

	public ProblemaPermutacaoSimples(String texto)
	{
		super();
		this.texto = texto;
	}

	public void gerarValores()
	{
		Random rand = new Random();
		n = 3 + rand.nextInt(7);
	}

	public String[] resolucao()
	{
		String passoIntroducao = "A permutação simples de \\(n\\) elementos distintos é dada por:";
		String passoFormula = "\\(" + ParCor.formula("P(n) = n!") + "\\)";
		String passoSubstituicao = "Com \\(n = " + n + "\\):";
		String passoExpansao = "\\(P(" + n + ") = " + n + "! =\\)";
		String passoResultado = "\\(" + AuxCombinacao.fatorialString(n) + " = \\mathbf{" + resultado() + "}\\)";

		return new String[]{passoIntroducao, passoFormula, passoSubstituicao, passoExpansao, passoResultado};
	}

	public String resultado()
	{
		return ""+Algebra.fatorial(n);
	}

	public String getPergunta()
	{
		String pergunta = texto;
		if(pergunta.contains("$n"))
			pergunta = pergunta.replace("$n", n+"");

		return pergunta;
	}

	public ProblemaPermutacaoSimples clone()
	{
		return new ProblemaPermutacaoSimples(texto);
	}
}
