package matematica.avancado.pa.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pa.ResolucaoPA;

public class Expressao14 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int n = 3 + 2 * rand.nextInt(7);

		Racional a1 = new Racional(1 + rand.nextInt(10), 1 + rand.nextInt(5));
		a1.fatoracao(2);

		Racional r = new Racional(1 + rand.nextInt(5), 1 + rand.nextInt(5));
		r.fatoracao(2);

		Racional an = ResolucaoPA.a(a1, r, n);
		int centralPos = (n + 1) / 2;
		Racional central = ResolucaoPA.a(a1, r, centralPos);

		addParagrafo("Em uma PA de " + n + " termos (número ímpar) com \\(a_1 = " + a1.showDfrac()
				+ "\\) e \\(a_{" + n + "} = " + an.showDfrac()
				+ "\\), qual é o " + centralPos + "º termo (termo central)?");
		gerarAlternativas(central.toString());

		addResolucao("O termo central de uma PA com número ímpar de termos é a média dos extremos:");
		addResolucao("\\(a_{" + centralPos + "} = \\dfrac{a_1 + a_n}{2}\\)");
		addResolucao("\\(a_{" + centralPos + "} = \\dfrac{" + a1.showDfrac() + " + " + an.showDfrac()
				+ "}{2} = \\mathbf{" + central.showDfrac() + "}\\)");
	}
}
