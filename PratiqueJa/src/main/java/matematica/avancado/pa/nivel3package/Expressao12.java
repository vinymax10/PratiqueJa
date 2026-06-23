package matematica.avancado.pa.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pa.ResolucaoPA;

public class Expressao12 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int k = 2 + rand.nextInt(4);

		Racional a = new Racional(1 + rand.nextInt(10));

		Racional r = new Racional(1 + rand.nextInt(6), 2 + rand.nextInt(4));
		r.fatoracao(2);

		int n = k + 2;
		Racional b = ResolucaoPA.a(a, r, n);

		addParagrafo("Inserindo " + k + " meios aritméticos entre \\(" + a.showDfrac()
				+ "\\) e \\(" + b.showDfrac() + "\\), qual é a razão da PA resultante?");
		gerarAlternativas(r.toString());

		addResolucao("A PA resultante tem \\(" + n + "\\) termos no total.");
		addResolucao("Fórmula da interpolação aritmética:");
		addResolucao("\\(r = \\dfrac{b - a}{k + 1}\\)");
		addResolucao("\\(r = \\dfrac{" + b.showDfrac() + " - " + a.showDfrac() + "}{" + k + " + 1}"
				+ " = \\mathbf{" + r.showDfrac() + "}\\)");
	}
}
