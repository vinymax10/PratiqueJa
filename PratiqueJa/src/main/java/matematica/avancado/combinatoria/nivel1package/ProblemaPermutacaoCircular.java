package matematica.avancado.combinatoria.nivel1package;

import java.util.Random;

import util.Algebra;
import matematica.ParCor;
import matematica.avancado.combinatoria.AuxCombinacao;

public class ProblemaPermutacaoCircular
{
	public String texto;

	public int n;

	public ProblemaPermutacaoCircular(String texto)
	{
		super();
		this.texto = texto;
	}

	public void gerarValores()
	{
		Random rand = new Random();
		n = 4 + rand.nextInt(7);
	}

	public String[] resolucao()
	{
		String passoIntroducao = "Em disposições circulares, fixamos um elemento e permutamos os demais, pois rotações geram o mesmo arranjo:";
		String passoFormula = "\\(" + ParCor.formula("P_c(n) = (n-1)!") + "\\)";
		String passoSubstituicao = "Com \\(n = " + n + "\\):";
		String passoExpansao = "\\(P_c(" + n + ") = (" + n + "-1)! = " + (n-1) + "! =\\)";
		String passoResultado = "\\(" + AuxCombinacao.fatorialString(n-1) + " = \\mathbf{" + resultado() + "}\\)";

		return new String[]{passoIntroducao, passoFormula, passoSubstituicao, passoExpansao, passoResultado};
	}

	public String resultado()
	{
		return "" + Algebra.fatorial(n-1);
	}

	public String getPergunta()
	{
		String pergunta = texto;
		if(pergunta.contains("$n"))
			pergunta = pergunta.replace("$n", n+"");

		return pergunta;
	}

	public ProblemaPermutacaoCircular clone()
	{
		return new ProblemaPermutacaoCircular(texto);
	}
}
