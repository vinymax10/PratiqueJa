package matematica.avancado.combinatoria.nivel3package;

import java.util.Random;

import util.Algebra;
import matematica.avancado.combinatoria.AuxCombinacao;

public class ProblemaCombinacaoRestricao
{
	public String texto;

	public int a,b,p;

	public ProblemaCombinacaoRestricao(String texto)
	{
		super();
		this.texto = texto;
	}

	public void gerarValores()
	{
		Random rand = new Random();
		a = 4 + rand.nextInt(3);
		b = 2 + rand.nextInt(3);
		p = 2 + rand.nextInt(2);
	}

	public String[] resolucao()
	{
		int total = a + b;
		int cTotal = AuxCombinacao.combinacao(total, p);
		int cExcluido = AuxCombinacao.combinacao(a, p);
		int resultado = cTotal - cExcluido;

		String passoIntroducao = "Estratégia do complementar: do total de grupos possíveis, subtraímos aqueles que não satisfazem a restrição.";
		String passoTotalProsa = "Total de grupos de \\(" + p + "\\) entre \\(" + total + "\\) pessoas:";
		String passoTotalFormula = "\\(C(" + total + "," + p + ") = \\dfrac{" + total + "!}{" + p + "!\\,(" + total + "-" + p + ")!} =\\)";
		String passoTotalResultado = "\\(\\dfrac{" + Algebra.fatorial(total) + "}{" + Algebra.fatorial(p) + " \\cdot " + Algebra.fatorial(total-p) + "} = " + cTotal + "\\)";
		String passoExcluidoProsa = "Grupos que violam a restrição (escolhidos só entre os \\(" + a + "\\) que não a satisfazem):";
		String passoExcluidoFormula = "\\(C(" + a + "," + p + ") = \\dfrac{" + a + "!}{" + p + "!\\,(" + a + "-" + p + ")!} =\\)";
		String passoExcluidoResultado = "\\(\\dfrac{" + Algebra.fatorial(a) + "}{" + Algebra.fatorial(p) + " \\cdot " + Algebra.fatorial(a-p) + "} = " + cExcluido + "\\)";
		String passoComplementarProsa = "Aplicando o complementar:";
		String passoComplementarResultado = "\\(" + cTotal + " - " + cExcluido + " = \\mathbf{" + resultado + "}\\)";

		return new String[]{passoIntroducao, passoTotalProsa, passoTotalFormula, passoTotalResultado, passoExcluidoProsa, passoExcluidoFormula, passoExcluidoResultado, passoComplementarProsa, passoComplementarResultado};
	}

	public String resultado()
	{
		int total = a + b;
		return "" + (AuxCombinacao.combinacao(total, p) - AuxCombinacao.combinacao(a, p));
	}

	public String getPergunta()
	{
		String pergunta = texto;
		if(pergunta.contains("$a"))
			pergunta = pergunta.replace("$a", a+"");

		if(pergunta.contains("$b"))
			pergunta = pergunta.replace("$b", b+"");

		if(pergunta.contains("$c"))
			pergunta = pergunta.replace("$c", p+"");

		return pergunta;
	}

	public ProblemaCombinacaoRestricao clone()
	{
		return new ProblemaCombinacaoRestricao(texto);
	}
}
