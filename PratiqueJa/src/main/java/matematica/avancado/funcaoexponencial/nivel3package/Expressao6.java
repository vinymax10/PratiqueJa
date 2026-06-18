package matematica.avancado.funcaoexponencial.nivel3package;

import matematica.GeradorExercicio;

public class Expressao6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Equação: a^(2x) - (1 + a^r)*a^x + a^r = 0
		// Substituição y = a^x => (y-1)(y-a^r) = 0 => x=0 ou x=r
		int a = rand.nextBoolean() ? 2 : 3;
		int r = 1 + rand.nextInt(3); // 1, 2, 3
		int aPowR = (int) Math.pow(a, r);
		long soma = 1 + aPowR;     // coeficiente de a^x

		addParagrafo("Resolva a equação \\(" + a + "^{2x} - " + soma + " \\cdot " + a + "^x + " + aPowR + " = 0\\). "
			+ "Determine a maior solução.");

		String res = "Fazemos a substituição \\(y = " + a + "^x\\): \\(\\\\\\)";
		res += "\\(y^2 - " + soma + "y + " + aPowR + " = 0\\) \\(\\\\\\)";
		res += "Fatorando: \\((y - 1)(y - " + aPowR + ") = 0\\\\";
		res += "y = 1 \\quad\\) ou \\(\\quad y = " + aPowR + "\\) \\(\\\\\\)";
		res += "Voltando para \\(x\\): \\(\\\\\\)";
		res += "\\(" + a + "^x = 1 = " + a + "^0 \\Rightarrow x = 0\\\\";
		res += "" + a + "^x = " + aPowR + " = " + a + "^{" + r + "} \\Rightarrow x = " + r + "\\) \\(\\\\\\)";
		res += "Maior solução: \\(x = \\mathbf{" + r + "}\\)";

		gerarAlternativas("" + r);
		setResolucao(res);
	}
}
