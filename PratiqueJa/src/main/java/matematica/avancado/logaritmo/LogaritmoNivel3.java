package matematica.avancado.logaritmo;

import matematica.GeradorExercicio;
import matematica.Racional;

public class LogaritmoNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(5);
		int c = 2 + rand.nextInt(9);
		int b = 1 + rand.nextInt(10);

		int maxBase = (int) Math.min((Math.log(1000) / Math.log(c)), 10);
		int d = 1 + rand.nextInt(Math.max(1, maxBase));

		Racional x = new Racional(d * b, a);
		x.fatoracao(2);

		String enunciado, resolucao;
		if(a == 1)
		{
			enunciado = b + " \\cdot \\log_{" + c + "}" + (int) Math.pow(c, d) + "=";
			resolucao = ResolucaoLogaritmo.resolucao3A1(b, c, d, x);
		}
		else
		{
			enunciado = b + " \\cdot \\log_{" + c + "} \\sqrt[" + a + "]{" + (int) Math.pow(c, d) + "}=";
			resolucao = ResolucaoLogaritmo.resolucao3(a, b, c, d, x);
		}

		addParagrafo("Calcule o valor da expressão:");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas("" + x);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
