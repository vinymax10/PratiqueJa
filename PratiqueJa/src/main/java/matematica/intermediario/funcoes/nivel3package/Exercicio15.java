package matematica.intermediario.funcoes.nivel3package;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;

public class Exercicio15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// f(x) = af·x + bf,  g(x) = ag·x + bg
		// (g∘f)(x) = ag·(af·x + bf) + bg = (ag·af)x + (ag·bf + bg)
		int af = 2 + rand.nextInt(3);  // 2..4
		int bf = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(5));
		int ag = 2 + rand.nextInt(3);
		int bg = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(5));

		int coef     = ag * af;
		int constante = ag * bf + bg;

		String ef      = equacaoStr(af, bf);
		String eg      = equacaoStr(ag, bg);
		String correto = "\\(" + equacaoStr(coef, constante) + "\\)";

		addParagrafo("Sendo \\(f(x) = " + ef + "\\) e \\(g(x) = " + eg
				+ "\\), determine a expressão de \\((g \\circ f)(x)\\)");

		// Distratores pedagogicamente relevantes
		// D1: (f∘g)(x) = af·(ag·x + bg) + bf = (af·ag)x + (af·bg + bf)
		int constD1 = af * bg + bf;
		String d1 = "\\(" + equacaoStr(coef, constD1) + "\\)";

		// D2: g(af·x) — esquece o bf dentro da composição
		int constD2 = bg;
		String d2 = "\\(" + equacaoStr(coef, constD2) + "\\)";

		// D3: apenas g(x) — não aplicou f antes
		String d3 = "\\(" + equacaoStr(ag, bg) + "\\)";

		List<String> distratores = new ArrayList<>();
		if(!d1.equals(correto)) distratores.add(d1);
		if(!d2.equals(correto) && !distratores.contains(d2)) distratores.add(d2);
		if(!d3.equals(correto) && !distratores.contains(d3)) distratores.add(d3);
		// Fallback: varia coeficiente
		if(distratores.size() < 3)
			distratores.add("\\(" + equacaoStr(coef + 1, constante) + "\\)");

		embaralharEAdicionarAlternativas(correto, distratores.subList(0, 3));

		// Resolução
		String res = "Pela definição, \\((g \\circ f)(x) = g(f(x))\\). "
				+ "Substituímos \\(f(x) = " + ef + "\\) em \\(g\\): \\(\\\\\\)";
		res += "\\((g \\circ f)(x) = " + ag + "\\left(" + ef + "\\right)" + bLatex(bg) + "\\). \\(\\\\\\)";
		res += "Aplicando a distributiva: \\(\\\\\\)";
		res += "\\((g \\circ f)(x) = " + coef + "x" + bLatex(ag * bf) + bLatex(bg) + "\\). \\(\\\\\\)";
		if(ag * bf + bg != ag * bf)
		{
			res += "Simplificando a constante: \\(\\\\\\)";
			res += "\\((g \\circ f)(x) = " + equacaoStr(coef, constante) + "\\).";
		}
		else
			res += "\\((g \\circ f)(x) = " + equacaoStr(coef, constante) + "\\).";

		setResolucao(res);
	}

	private static String equacaoStr(int a, int b)
	{
		return (a == 1 ? "" : "" + a) + "x" + bLatex(b);
	}

	private static String bLatex(int b)
	{
		if(b > 0) return " + " + b;
		if(b < 0) return " - " + Math.abs(b);
		return "";
	}
}
