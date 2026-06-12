package matematica.avancado.pg.nivel2package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pg.ResolucaoPG;

public class Expressao5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int k = 1 + rand.nextInt(3);
		Racional q = new Racional(2 + rand.nextInt(3));
		Racional a = new Racional(1 + rand.nextInt(5));
		Racional b = ResolucaoPG.a(a, q, k + 2);

		String[] ordenais = {"um meio geométrico", "dois meios geométricos", "três meios geométricos"};

		addParagrafo("Insira " + ordenais[k - 1] + " entre \\(" + a.showDfrac() + "\\) e \\("
				+ b.showDfrac() + "\\). Qual é a razão \\(q\\) da PG formada?");
		gerarAlternativas(q.toString());
		setResolucao("\\(" + ResolucaoPG.resolucaoInterpolacao(a, b, k, q) + "\\)");
	}
}
