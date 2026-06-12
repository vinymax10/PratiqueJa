package matematica.avancado.funcaoquadratica.nivel1package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.avancado.funcaoquadratica.ResolucaoFuncaoQuadratica;

public class Expressao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Garante Xv inteiro: escolhe xv, depois b = -2*a*xv
		int a = 1 + rand.nextInt(3);
		if(rand.nextBoolean()) a *= -1;
		int xv = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(5));
		int b = -2 * a * xv;
		int c = rand.nextInt(7) - 3;

		String funcao = Auxiliar.getNumber(a, "x^2", true)
			+ Auxiliar.getNumber(b, "x", false)
			+ Auxiliar.getNumber(c, "", false);

		addParagrafo("Determine a coordenada \\(X_v\\) do vértice de \\(f(x) = " + funcao + "\\)");

		gerarAlternativas("" + xv);
		setResolucao(ResolucaoFuncaoQuadratica.resolucaoXv(a, b, c));
	}
}
