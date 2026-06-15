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

	public String resolucao()
	{
		int total = a + b;
		int cTotal = AuxCombinacao.combinacao(total, p);
		int cExcluido = AuxCombinacao.combinacao(a, p);
		int resultado = cTotal - cExcluido;

		String res = "Estratégia do complementar: do total de grupos possíveis, subtraímos aqueles que não satisfazem a restrição.";
		res += "\\(\\\\\\)";
		res += "Total de grupos de \\(" + p + "\\) entre \\(" + total + "\\) pessoas:";
		res += "\\(\\\\\\)";
		res += "\\(C(" + total + "," + p + ") = \\dfrac{" + total + "!}{" + p + "!\\,(" + total + "-" + p + ")!} = \\\\ ";
		res += "\\dfrac{" + Algebra.fatorial(total) + "}{" + Algebra.fatorial(p) + " \\cdot " + Algebra.fatorial(total-p) + "} = " + cTotal + "\\)";
		res += "\\(\\\\\\)";
		res += "Grupos que violam a restrição (escolhidos só entre os \\(" + a + "\\) que não a satisfazem):";
		res += "\\(\\\\\\)";
		res += "\\(C(" + a + "," + p + ") = \\dfrac{" + a + "!}{" + p + "!\\,(" + a + "-" + p + ")!} = \\\\ ";
		res += "\\dfrac{" + Algebra.fatorial(a) + "}{" + Algebra.fatorial(p) + " \\cdot " + Algebra.fatorial(a-p) + "} = " + cExcluido + "\\)";
		res += "\\(\\\\\\)";
		res += "Aplicando o complementar:";
		res += "\\(\\\\\\)";
		res += "\\(" + cTotal + " - " + cExcluido + " = \\mathbf{" + resultado + "}\\)";

		return res;
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
