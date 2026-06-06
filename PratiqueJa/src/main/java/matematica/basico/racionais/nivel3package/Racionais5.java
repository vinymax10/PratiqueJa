package matematica.basico.racionais.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.basico.racionais.ResolucaoRacionais;

public class Racionais5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int max = 20;

		int a = 1 + rand.nextInt(max);
		if(rand.nextBoolean())
			a *= -1;

		int b = 2 + rand.nextInt(max);
		while(a == b)
			b = 2 + rand.nextInt(max);

		int c = 1 + rand.nextInt(max);
		if(rand.nextBoolean())
			c *= -1;

		int d = 2 + rand.nextInt(max);
		while(c == d)
			d = 2 + rand.nextInt(max);

		Racional aRacional = new Racional(a, b);
		Racional bRacional = new Racional(c, d);

		String enunciado = aRacional.showDfrac() + " \\cdot (" + bRacional.showDfrac() + ")=";
		enunciado = enunciado.replace("(", "\\left(").replace(")", "\\right)");

		Racional resultado = aRacional.mult(bRacional);
		resultado.fatoracao(2);

		String resolucao = ResolucaoRacionais.Multiplicacao(a, b, c, d);

		addParagrafo("Calcule:");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas(resultado.toString());
		setResolucao("\\(" + resolucao + "\\)");
	}
}
