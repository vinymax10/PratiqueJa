package matematica.avancado.funcaoquadratica.nivel1package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.avancado.funcaoquadratica.ResolucaoFuncaoQuadratica;

public class Expressao17 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(5);
		if(rand.nextBoolean()) a *= -1;
		int b = rand.nextInt(9);
		if(rand.nextBoolean()) b *= -1;
		int c = rand.nextInt(9);
		if(rand.nextBoolean()) c *= -1;

		int x = 1 + rand.nextInt(5);

		String funcao = "f(x)=";
		funcao += Auxiliar.getNumber(a, "x^2", true);
		funcao += Auxiliar.getNumber(b, "x", false);
		funcao += Auxiliar.getNumber(c, "", false);

		int resultado = a * x * x + b * x + c;

		addParagrafo("Encontre \\(f(" + x + ")\\)");
		addParagrafo("\\(" + funcao + "\\)");
		gerarAlternativas("" + resultado);
		setResolucao(ResolucaoFuncaoQuadratica.resolucaoFx(a, b, c, x));
	}
}
