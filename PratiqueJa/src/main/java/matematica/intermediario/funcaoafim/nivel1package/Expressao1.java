package matematica.intermediario.funcaoafim.nivel1package;

import matematica.GeradorExercicio;
import matematica.Racional;

public class Expressao1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(9);
		int b = 1 + rand.nextInt(20);

		if(rand.nextBoolean())
			a *= -1;

		if(rand.nextBoolean())
			b *= -1;

		int num = 1 + rand.nextInt(20);
		String nomeA = "";
		if(a != 1 && a != -1)
			nomeA = "" + a;
		else if(a == -1)
			nomeA = "-";

		String nomeB = "";
		if(b > 0)
			nomeB = "+";

		nomeB += b;

		String texto = "f(x)=" + nomeA + "x" + nomeB;

		Racional resultado = new Racional(a * num + b);

		texto = texto.replace("(", "\\left(").replace(")", "\\right)");

		String resolucao = "f(" + num + ")=";
		if(a != -1 && a != 1)
			resolucao += +a + "\\cdot " + num + nomeB + " = \\\\";
		resolucao += (a * num) + nomeB + " = " + resultado;

		addParagrafo("Encontre \\( f(" + num + ") \\)");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultado.toString());
		setResolucao("\\(" + resolucao + "\\)");
	}
}
