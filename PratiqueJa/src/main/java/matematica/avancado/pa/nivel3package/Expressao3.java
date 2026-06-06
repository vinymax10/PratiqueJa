package matematica.avancado.pa.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pa.ResolucaoPA;

public class Expressao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Racional a1 = new Racional(1 + rand.nextInt(20), 1 + rand.nextInt(20));
		a1.fatoracao(2);

		Racional r = new Racional(1 + rand.nextInt(20), 1 + rand.nextInt(20));
		r.fatoracao(2);

		String enunciado = "" + a1.showDfrac() + ", x, " + ResolucaoPA.a(a1, r, 3).showDfrac() + ", " + ResolucaoPA.a(a1, r, 4).showDfrac() + "";

		addParagrafo("Qual o valor de \\(x\\)?");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas(ResolucaoPA.a(a1, r, 2).toString());
		setResolucao("\\(" + ResolucaoPA.x2(a1, r) + "\\)");
	}
}
