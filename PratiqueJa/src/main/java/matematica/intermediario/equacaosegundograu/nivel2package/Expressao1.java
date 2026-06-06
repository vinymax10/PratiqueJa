package matematica.intermediario.equacaosegundograu.nivel2package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.intermediario.equacaosegundograu.ResolucaoEq2Grau;

public class Expressao1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int x = 1 + rand.nextInt(9);
		int delta = 1 + rand.nextInt(9);
		int a = 1 + rand.nextInt(9);
		if(rand.nextBoolean())
			a *= -1;

		int b = delta - (2 * a * x);
		int c = (-x * delta) + (a * x * x);

		String texto = "";
		texto += Auxiliar.getNumber(a, "x^2", true);
		texto += Auxiliar.getNumber(b, "x", false);
		texto += Auxiliar.getNumber(c, "", false);

		Racional resultado = new Racional(-b).div(new Racional(2 * a));
		resultado.fatoracao(2);

		addParagrafo("Encontre o \\( x \\) do vértice");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas("" + resultado);
		setResolucao("\\(" + ResolucaoEq2Grau.resolucaoXVertice(a, b, c) + "\\)");
	}
}
