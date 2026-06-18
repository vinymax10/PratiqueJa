package matematica.avancado.funcaoquadratica.nivel3package;

import java.util.ArrayList;
import java.util.List;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.funcaoquadratica.ResolucaoFuncaoQuadratica;

public class Expressao9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int xv = 1 + rand.nextInt(9);
		int delta = 1 + rand.nextInt(9);
		int a = 1 + rand.nextInt(9);
		if(rand.nextBoolean()) a *= -1;

		int b = delta - (2 * a * xv);
		int c = (-xv * delta) + (a * xv * xv);

		String funcao = Auxiliar.getNumber(a, "x^2", true)
			+ Auxiliar.getNumber(b, "x", false)
			+ Auxiliar.getNumber(c, "", false);

		Racional yv = new Racional(-(delta * delta), 4 * a);
		yv.fatoracao(2);
		String yvStr = yv.toStringLatex();

		addParagrafo("Determine a imagem \\(\\text{Im}(f)\\) de \\(f(x) = " + funcao + "\\)");

		// Alternativas como intervalos
		String correta, d1, d2, d3;
		if(a > 0)
		{
			correta = "\\([" + yvStr + ",\\ +\\infty)\\)";
			d1 = "\\((-\\infty,\\ " + yvStr + "]\\)";
			d2 = "\\((" + yvStr + ",\\ +\\infty)\\)";
			d3 = "\\((-\\infty,\\ " + yvStr + ")\\)";
		}
		else
		{
			correta = "\\((-\\infty,\\ " + yvStr + "]\\)";
			d1 = "\\([" + yvStr + ",\\ +\\infty)\\)";
			d2 = "\\((-\\infty,\\ " + yvStr + ")\\)";
			d3 = "\\((" + yvStr + ",\\ +\\infty)\\)";
		}

		List<String> distratores = new ArrayList<>();
		distratores.add(d1);
		distratores.add(d2);
		distratores.add(d3);
		embaralharEAdicionarAlternativas(correta, distratores);

		String concav = a > 0 ? "cima (\\(a > 0\\))" : "baixo (\\(a < 0\\))";
		String imStr  = a > 0 ? "[y_v,\\ +\\infty)" : "(-\\infty,\\ y_v]";

		String res = ResolucaoFuncaoQuadratica.resolucaoYv(a, b, c);
		res += "\\(\\\\\\)";
		res += "A parábola abre para " + concav + ", logo \\(\\text{Im}(f) = " + imStr + "\\): \\(\\\\\\)";
		res += "\\(\\text{Im}(f) = \\mathbf{" + (a > 0 ? "[" : "(-\\infty,\\ ") + yvStr
			+ (a > 0 ? ",\\ +\\infty)" : "]") + "}\\)";

		setResolucao(res);
	}
}
