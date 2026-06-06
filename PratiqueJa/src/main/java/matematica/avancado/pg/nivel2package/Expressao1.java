package matematica.avancado.pg.nivel2package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pg.ResolucaoPG;

public class Expressao1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Racional a1 = new Racional(1 + rand.nextInt(10));
		Racional q = new Racional(2 + rand.nextInt(6));

		double nMaximo = (Math.log(50000 / a1.numerador) / Math.log(q.numerador)) + 1;
		int n = 4 + rand.nextInt(Math.min(Math.max(1, (int) (nMaximo - 4)), 20));

		String enunciado = "" + a1.showDfrac() + ", " + ResolucaoPG.a(a1, q, 2).showDfrac() + ", \\ldots ";

		addParagrafo("Qual é a soma da PG que possui " + n + " termos?");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas(ResolucaoPG.soma(a1, q, n).toString());
		setResolucao("\\(" + ResolucaoPG.resolucaoSoma(a1, q, n) + "\\)");
	}
}
