package matematica.avancado.funcaoquadratica.nivel2package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.avancado.funcaoquadratica.ResolucaoFuncaoQuadratica;

public class Expressao6 extends GeradorExercicio
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

		String funcao = "f(x)=";
		funcao += Auxiliar.getNumber(a, "x^2", true);
		funcao += Auxiliar.getNumber(b, "x", false);
		funcao += "+c";

		int x = 1 + rand.nextInt(10);
		int fx = a * x * x + b * x + c;

		addParagrafo("Encontre \\(c\\) tendo \\(f(" + x + ")=" + fx + "\\)");
		addParagrafo("\\(" + funcao + "\\)");
		gerarAlternativas("" + c);
		setResolucao(ResolucaoFuncaoQuadratica.resolucaoAcharC(a, b, c, x, fx));
	}
}
