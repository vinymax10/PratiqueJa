package matematica.avancado.pa.nivel2package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pa.ResolucaoPA;

public class Expressao5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int k = 2 + rand.nextInt(5);
		int aVal = 1 + rand.nextInt(10);
		int rVal = 1 + rand.nextInt(8);
		Racional a = new Racional(aVal);
		Racional r = new Racional(rVal);
		int n = k + 2;
		Racional b = ResolucaoPA.a(a, r, n);
		int diff = (k + 1) * rVal;

		addParagrafo("Inserindo " + k + " meios aritméticos entre \\(" + aVal + "\\) e \\(" + b.showDfrac()
				+ "\\), qual é a razão da PA resultante?");
		gerarAlternativas("" + rVal);

		String res = "A PA resultante tem \\(" + n + "\\) termos, com \\(" + aVal + "\\) no início e \\("
				+ b.showDfrac() + "\\) no final. \\(\\\\\\)";
		res += "Fórmula da interpolação aritmética: \\(\\\\\\)";
		res += "\\(r = \\dfrac{b - a}{k + 1} \\\\";
		res += "r = \\dfrac{" + b.showDfrac() + " - " + aVal + "}{" + k + " + 1} \\\\";
		res += "r = \\dfrac{" + diff + "}{" + (k + 1) + "} = \\mathbf{" + rVal + "}\\)";
		setResolucao(res);
	}
}
