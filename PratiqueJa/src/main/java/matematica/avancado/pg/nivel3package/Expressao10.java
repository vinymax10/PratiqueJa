package matematica.avancado.pg.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pg.ResolucaoPG;

public class Expressao10 extends GeradorExercicio
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
			int den = 2 + rand.nextInt(4);
			int num = 1 + rand.nextInt(den - 1);
			q = new Racional(num, den);
			q.fatoracao(2);
		}
		while(q.igual(um) || q.denominador == 1);

		Racional sInf = ResolucaoPG.somaInfinita(a1, q);

		String enunciado = a1.showDfrac() + ", " + ResolucaoPG.a(a1, q, 2).showDfrac() + ", "
				+ ResolucaoPG.a(a1, q, 3).showDfrac() + ", \\ldots";

		String resolucao = ResolucaoPG.resolucaoSomaInfinitaFrac(a1, q);
		resolucao = resolucao.replace("(", "\\left(").replace(")", "\\right)");

		addParagrafo("Qual é a soma de todos os termos da PG infinita?");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas(sInf.toString());
		addResolucao("\\(" + resolucao + "\\)");
	}
}
