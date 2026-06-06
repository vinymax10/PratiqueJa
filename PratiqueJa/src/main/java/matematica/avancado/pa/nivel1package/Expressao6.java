package matematica.avancado.pa.nivel1package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pa.ResolucaoPA;

public class Expressao6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Racional a1 = new Racional(1 + rand.nextInt(20));
		Racional r = new Racional(1 + rand.nextInt(20));

		String enunciado = "x, " + ResolucaoPA.a(a1, r, 2).showDfrac() + ", " + ResolucaoPA.a(a1, r, 3).showDfrac() + ", " + ResolucaoPA.a(a1, r, 4).showDfrac();

		addParagrafo("Qual o valor de \\(x\\)?");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas(a1.toString());
		setResolucao("\\(" + ResolucaoPA.x1(a1, r) + "\\)");
	}
}
