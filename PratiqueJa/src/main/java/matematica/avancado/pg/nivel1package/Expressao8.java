package matematica.avancado.pg.nivel1package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pg.ResolucaoPG;

public class Expressao8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Racional a1 = new Racional(1 + rand.nextInt(10));
		Racional q = new Racional(2 + rand.nextInt(6));

		double nMaximo = (Math.log(50000 / a1.numerador) / Math.log(q.numerador)) + 1;
		int n = 4 + rand.nextInt(Math.min(Math.max(1, (int) (nMaximo - 4)), 20));

		Racional an = ResolucaoPG.a(a1, q, n);

		String enunciado = "" + a1.showDfrac() + ", " + (ResolucaoPG.a(a1, q, 2).showDfrac()) + ", \\ldots, " + an.showDfrac() + "";

		addParagrafo("Quantos termos tem a PG?");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas("" + n);
		setResolucao("\\(" + ResolucaoPG.numeroTermos(a1, q, an, n) + "\\)");
	}
}
