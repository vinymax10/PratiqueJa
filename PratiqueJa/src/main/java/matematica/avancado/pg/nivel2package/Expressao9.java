package matematica.avancado.pg.nivel2package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pg.ResolucaoPG;

public class Expressao9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Racional a1 = new Racional(1 + rand.nextInt(10));
		Racional q = new Racional(2 + rand.nextInt(6));

		double nMaximo = (Math.log(50000 / a1.numerador) / Math.log(q.numerador)) + 1;
		int n = 4 + rand.nextInt(Math.min(Math.max(1, (int) (nMaximo - 4)), 20));
		Racional an = ResolucaoPG.a(a1, q, n);

		String enunciado = "x" + "+ \\ldots + " + ResolucaoPG.a(a1, q, n - 1).showDfrac() + "+" + an.showDfrac() + "=" + ResolucaoPG.soma(a1, q, n).showDfrac();

		addParagrafo("Qual o valor de \\(x\\) na PG que possui " + n + " termos?");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas(a1.toString());
		setResolucao("\\(" + ResolucaoPG.resolucaoSoma3(a1, q, n) + "\\)");
	}
}
