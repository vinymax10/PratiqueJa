package matematica.avancado.pg.nivel2package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pg.ResolucaoPG;

public class Expressao6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Racional a1 = new Racional(1 + rand.nextInt(5));
		Racional q = new Racional(2 + rand.nextInt(3));

		int k = 2 + rand.nextInt(3);

		Racional prev = ResolucaoPG.a(a1, q, k - 1);
		Racional mid = ResolucaoPG.a(a1, q, k);
		Racional next = ResolucaoPG.a(a1, q, k + 1);

		addParagrafo("Em uma PG, três termos consecutivos têm o do meio desconhecido:");
		addParagrafo("\\(" + prev.showDfrac() + ", \\; x, \\; " + next.showDfrac() + "\\)");
		addParagrafo("Qual é o valor de \\(x\\)?");
		gerarAlternativas(mid.toString());
		setResolucao("\\(" + ResolucaoPG.resolucaoMediaGeometrica(prev, next, mid) + "\\)");
	}
}
