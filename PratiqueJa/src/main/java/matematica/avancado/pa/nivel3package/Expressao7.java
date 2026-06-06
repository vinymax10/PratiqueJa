package matematica.avancado.pa.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pa.ResolucaoPA;

public class Expressao7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int n = 4 + rand.nextInt(20);

		Racional a1 = new Racional(1 + rand.nextInt(20), 1 + rand.nextInt(20));
		a1.fatoracao(2);

		Racional r = new Racional(1 + rand.nextInt(20), 1 + rand.nextInt(20));
		r.fatoracao(2);
		Racional an = ResolucaoPA.a(a1, r, n);

		String enunciado = "" + a1.showDfrac() + ", \\ldots, " + an.showDfrac() + "";

		String resolucao = ResolucaoPA.resolucaoSoma(a1, r, an, n);
		resolucao = resolucao.replace("(", "\\left(").replace(")", "\\right)");

		addParagrafo("Qual é a soma da PA que possui " + n + " termos?");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativas(ResolucaoPA.soma(a1, an, n).toString());
		setResolucao("\\(" + resolucao + "\\)");
	}
}
