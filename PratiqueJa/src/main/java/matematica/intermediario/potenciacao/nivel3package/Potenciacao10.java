package matematica.intermediario.potenciacao.nivel3package;

import matematica.GeradorExercicio;
import matematica.intermediario.potenciacao.ResolucaoPotencia;

public class Potenciacao10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 2 + rand.nextInt(19);

		int potenciaMaxima = 8;
		int p = 2 + rand.nextInt(potenciaMaxima - 1);

		String texto = ResolucaoPotencia.strFatores(a, p) + "=" + a + "^{x}";

		String resolucao = a + "^{" + p + "}=" + a + "^{x}\\\\";
		resolucao += "x=" + p;

		addParagrafo("Qual o valor de \\(x\\)?");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas("" + p);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
