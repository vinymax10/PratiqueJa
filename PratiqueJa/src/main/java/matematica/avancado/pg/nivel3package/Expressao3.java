package matematica.avancado.pg.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pg.ResolucaoPG;

public class Expressao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Racional a1 = new Racional(1 + rand.nextInt(10), 1 + rand.nextInt(10));
		a1.fatoracao(2);

		Racional q;
		Racional um = new Racional(1);
		do
		{
			q = new Racional(1 + rand.nextInt(9), 2 + rand.nextInt(8));
			q.fatoracao(2);
		}
		while(q.igual(um) || q.denominador == 1);

		String enunciado = "" + a1.showDfrac() + ", x, " + ResolucaoPG.a(a1, q, 3).showDfrac() + ", " + ResolucaoPG.a(a1, q, 4).showDfrac() + "";

		String resolucao = ResolucaoPG.x2Frac(a1, q);
		resolucao = resolucao.replace("(", "\\left(").replace(")", "\\right)");

		addParagrafo("Qual o valor de \\(x\\)?");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas(ResolucaoPG.a(a1, q, 2).toString());
		addResolucao("\\(" + resolucao + "\\)");
	}
}
