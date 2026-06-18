package matematica.avancado.pg.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pg.ResolucaoPG;

public class Expressao12 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Racional um = new Racional(1);

		Racional a1;
		do
		{
			a1 = new Racional(1 + rand.nextInt(10), 1 + rand.nextInt(10));
			a1.fatoracao(2);
		}
		while(a1.igual(um) || a1.denominador == 1);

		Racional q;
		do
		{
			q = new Racional(1 + rand.nextInt(6), 2 + rand.nextInt(6));
			q.fatoracao(2);
		}
		while(q.igual(um) || q.denominador == 1);

		double nMaximo = (Math.log(50000 / a1.numerador) / Math.log(q.numerador)) + 1;
		double dMaximo = (Math.log(50000 / a1.denominador) / Math.log(q.denominador)) + 1;
		double maximo = Math.min(nMaximo, dMaximo);

		int n = 4 + rand.nextInt(Math.min(Math.max(1, (int) (maximo - 4)), 20));

		Racional an = ResolucaoPG.a(a1, q, n);

		String enunciado = "" + a1.showDfrac() + ", " + (ResolucaoPG.a(a1, q, 2).showDfrac()) + ", \\ldots, " + an.showDfrac() + "";

		String resolucao = ResolucaoPG.numeroTermosFrac(a1, q, an, n);
		resolucao = resolucao.replace("(", "\\left(").replace(")", "\\right)");

		addParagrafo("Quantos termos tem a PG?");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas("" + n);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
