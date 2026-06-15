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
		n = 4 + rand.nextInt(5);
	}

	public String resolucao()
	{
		String res = "Em disposições circulares, fixamos um elemento e permutamos os demais, pois rotações geram o mesmo arranjo:";
		res += "\\(\\\\\\)";
		res += "\\(" + ParCor.formula("P_c(n) = (n-1)!") + "\\)";
		res += "\\(\\\\\\)";
		res += "Com \\(n = " + n + "\\):";
		res += "\\(\\\\\\)";
		res += "\\(P_c(" + n + ") = (" + n + "-1)! = " + (n-1) + "! = \\\\ ";
		res += AuxCombinacao.fatorialString(n-1) + " = \\mathbf{" + resultado() + "}\\)";

		return res;
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
