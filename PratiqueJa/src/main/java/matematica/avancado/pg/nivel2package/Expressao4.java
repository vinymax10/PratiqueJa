package matematica.avancado.pg.nivel2package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pg.ResolucaoPG;

public class Expressao4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Racional a1 = new Racional(1 + rand.nextInt(12));

		Racional q;
		Racional um = new Racional(1);
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

		addParagrafo("Qual é a soma de todos os termos da PG infinita?");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas(sInf.toString());
		addResolucao("\\(" + ResolucaoPG.resolucaoSomaInfinita(a1, q) + "\\)");
	}
}
