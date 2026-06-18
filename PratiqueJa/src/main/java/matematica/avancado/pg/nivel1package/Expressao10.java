package matematica.avancado.pg.nivel1package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pg.ResolucaoPG;

public class Expressao10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Racional a1 = new Racional(1 + rand.nextInt(10));
		Racional q = new Racional(2 + rand.nextInt(6));

		String enunciado = "" + a1.showDfrac() + ", " + ResolucaoPG.a(a1, q, 2).showDfrac() + ", x, " + ResolucaoPG.a(a1, q, 4).showDfrac() + "";

		addParagrafo("Qual o valor de \\(x\\)?");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas(ResolucaoPG.a(a1, q, 3).toString());
		setResolucao("\\(" + ResolucaoPG.x3(a1, q) + "\\)");
	}
}
