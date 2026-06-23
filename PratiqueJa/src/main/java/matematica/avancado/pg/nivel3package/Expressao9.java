package matematica.avancado.pg.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pg.ResolucaoPG;

public class Expressao9 extends GeradorExercicio
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

		Racional q = new Racional(2 + rand.nextInt(6));

		double nMaximo = (Math.log(50000 / a1.numerador) / Math.log(q.numerador)) + 1;
		double dMaximo = (Math.log(50000 / a1.denominador) / Math.log(q.denominador)) + 1;
		double maximo = Math.min(nMaximo, dMaximo);

		int n = 4 + rand.nextInt(Math.min(Math.max(1, (int) (maximo - 4)), 20));

		Racional an = ResolucaoPG.a(a1, q, n);

		String enunciado = "x" + "+ \\ldots + " + ResolucaoPG.a(a1, q, n - 1).showDfrac() + "+" + an.showDfrac() + "=" + ResolucaoPG.soma(a1, q, n).showDfrac();

		String resolucao = ResolucaoPG.resolucaoSoma3Frac(a1, q, n);
		resolucao = resolucao.replace("(", "\\left(").replace(")", "\\right)");

		addParagrafo("Qual o valor de \\(x\\) na PG que possui " + n + " termos?");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas(a1.toString());
		addResolucao("\\(" + resolucao + "\\)");
	}
}
