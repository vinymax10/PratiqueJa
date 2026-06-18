package matematica.avancado.funcaoquadratica.nivel2package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.funcaoquadratica.ResolucaoFuncaoQuadratica;

public class Expressao10 extends GeradorExercicio
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
		funcao += Auxiliar.getNumber(c, "", false);

		Racional resultado = new Racional(-b, 2 * a);
		resultado.fatoracao(2);

		addParagrafo("Encontre a coordenada \\(X_v\\) do vértice");
		addParagrafo("\\(" + funcao + "\\)");
		gerarAlternativas(resultado);
		setResolucao(ResolucaoFuncaoQuadratica.resolucaoXv(a, b, c));
	}
}
