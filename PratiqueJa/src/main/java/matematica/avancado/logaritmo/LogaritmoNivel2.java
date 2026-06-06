package matematica.avancado.logaritmo;

import matematica.GeradorExercicio;
import matematica.Racional;

public class LogaritmoNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int c = 2 + rand.nextInt(9);
		int b = 2 + rand.nextInt(10);

		int maxBase = (int) Math.min((Math.log(1000) / Math.log(c)), 10);
		int x = 1 + rand.nextInt(maxBase + 1);

		Racional a;
		String enunciado, resolucao;
		boolean soma = rand.nextBoolean();
		if(soma)
		{
			a = new Racional((int) Math.pow(c, x), b);
			a.fatoracao(2);
			enunciado = "\\log_{" + c + "}\\left(" + a.showDfrac() + "\\right)+" + "\\log_{" + c + "}" + b + "=";
			resolucao = ResolucaoLogaritmo.resolucaoSoma(a, b, c, x);
		}
		else
		{
			a = new Racional((int) Math.pow(c, x) * b, 1);
			enunciado = "\\log_{" + c + "}" + a + "-" + "\\log_{" + c + "}" + b + "=";
			resolucao = ResolucaoLogaritmo.resolucaoSubtracao(a, b, c, x);
		}

		addParagrafo("Calcule o valor da expressão:");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas("" + x);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
