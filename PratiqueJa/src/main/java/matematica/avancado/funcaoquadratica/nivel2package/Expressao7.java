package matematica.avancado.funcaoquadratica.nivel2package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import matematica.Auxiliar;
import matematica.GeradorExercicio;

public class Expressao7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// a=1 garante raízes inteiras limpas via Bhaskara
		int x1 = -(2 + rand.nextInt(5));  // -6..-2 (menor raiz)
		int x2 = 1 + rand.nextInt(5);     // 1..5  (maior raiz)
		int a  = 1;
		int b  = -(x1 + x2);
		int c  = x1 * x2;

		String funcao = Auxiliar.getNumber(a, "x^2", true)
			+ Auxiliar.getNumber(b, "x", false)
			+ Auxiliar.getNumber(c, "", false);

		addParagrafo("Determine a menor raiz de \\(f(x) = " + funcao + "\\)");

		Set<String> usados = new HashSet<>();
		usados.add("" + x1);
		List<String> distratores = new ArrayList<>();
		String[] candidatos = {"" + x2, "" + (x1 + x2), "" + (x1 * x2), "" + (x1 - 1), "" + (x2 + 1)};
		for(String cand : candidatos)
		{
			if(distratores.size() >= 3) break;
			if(usados.add(cand)) distratores.add(cand);
		}

		embaralharEAdicionarAlternativas("" + x1, distratores);

		int delta = b * b - 4 * c;           // = (x2-x1)² (com a=1)
		int sqrtDelta = x2 - x1;             // sempre positivo
		int negB = -b;
		int num1 = negB - sqrtDelta;          // = 2*x1
		int num2 = negB + sqrtDelta;          // = 2*x2

		String res = "Calculamos \\(\\Delta\\): \\(\\\\\\)";
		res += "\\(\\Delta = (" + b + ")^2 - 4 \\cdot 1 \\cdot (" + c + ") = "
			+ (b * b) + Auxiliar.getNumber(-4 * c, "", false) + " = " + delta + "\\\\";
		res += "\\sqrt{\\Delta} = " + sqrtDelta + "\\\\";
		res += "x_1 = \\dfrac{" + negB + " - " + sqrtDelta + "}{2} = \\dfrac{" + num1 + "}{2} = \\mathbf{" + x1 + "}\\\\";
		res += "x_2 = \\dfrac{" + negB + " + " + sqrtDelta + "}{2} = \\dfrac{" + num2 + "}{2} = " + x2 + "\\)";

		setResolucao(res);
	}
}
