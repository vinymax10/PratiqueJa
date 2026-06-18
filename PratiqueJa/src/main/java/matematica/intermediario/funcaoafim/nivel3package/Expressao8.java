package matematica.intermediario.funcaoafim.nivel3package;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;

public class Expressao8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(5);
		if(rand.nextBoolean()) a *= -1;
		int x0 = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(8));
		int b = -a * x0;

		boolean pedirPositivo = rand.nextBoolean();
		String sinal = pedirPositivo ? ">" : "<";

		String funcao = afimStr(a, b);
		addParagrafo("Determine os valores de \\(x\\) para os quais "
			+ "\\(f(x) = " + funcao + " " + sinal + " 0\\).");

		String x0Str = "" + x0;
		String correta, errada;

		if(pedirPositivo)
		{
			correta = a > 0 ? "\\(x > " + x0Str + "\\)" : "\\(x < " + x0Str + "\\)";
			errada  = a > 0 ? "\\(x < " + x0Str + "\\)" : "\\(x > " + x0Str + "\\)";
		}
		else
		{
			correta = a > 0 ? "\\(x < " + x0Str + "\\)" : "\\(x > " + x0Str + "\\)";
			errada  = a > 0 ? "\\(x > " + x0Str + "\\)" : "\\(x < " + x0Str + "\\)";
		}

		String corretaInc = correta.replace("> ", "\\geq ").replace("< ", "\\leq ");
		String erradaInc  = errada.replace("> ", "\\geq ").replace("< ", "\\leq ");

		List<String> distratores = new ArrayList<>();
		distratores.add(errada);
		distratores.add(corretaInc);
		distratores.add(erradaInc);

		embaralharEAdicionarAlternativas(correta, distratores);

		int menosB = -b;
		String crescente = a > 0 ? "crescente (\\(a > 0\\))" : "decrescente (\\(a < 0\\))";

		String res = "Primeiro encontramos o zero \\(x_0\\): \\(\\\\\\)";
		res += "\\(" + funcao + " = 0\\) \\(\\\\\\)";

		if(a == 1)
		{
			res += "\\(x_0 = \\mathbf{" + x0Str + "}\\) \\(\\\\\\)";
		}
		else if(a == -1)
		{
			res += "\\(-x = " + menosB + " \\Rightarrow x_0 = \\mathbf{" + x0Str + "}\\) \\(\\\\\\)";
		}
		else
		{
			int num = menosB, den = a;
			if(den < 0) { num = -num; den = -den; }
			if(num < 0)
				res += "\\(x_0 = -\\dfrac{" + (-num) + "}{" + den + "} = \\mathbf{" + x0Str + "}\\) \\(\\\\\\)";
			else
				res += "\\(x_0 = \\dfrac{" + num + "}{" + den + "} = \\mathbf{" + x0Str + "}\\) \\(\\\\\\)";
		}

		res += "A função é " + crescente + ", portanto: \\(\\\\\\)";

		if(pedirPositivo)
		{
			if(a > 0)
				res += "\\(f(x) > 0\\) quando \\(\\mathbf{x > " + x0Str + "}\\)";
			else
				res += "\\(f(x) > 0\\) quando \\(\\mathbf{x < " + x0Str + "}\\)";
		}
		else
		{
			if(a > 0)
				res += "\\(f(x) < 0\\) quando \\(\\mathbf{x < " + x0Str + "}\\)";
			else
				res += "\\(f(x) < 0\\) quando \\(\\mathbf{x > " + x0Str + "}\\)";
		}

		setResolucao(res);
	}

	private static String afimStr(int a, int b)
	{
		String sa = a == 1 ? "" : a == -1 ? "-" : "" + a;
		String sb = b > 0 ? " + " + b : b < 0 ? " - " + Math.abs(b) : "";
		return sa + "x" + sb;
	}
}
