package matematica.avancado.funcaoexponencial.nivel3package;

import matematica.GeradorExercicio;

public class Expressao1 extends GeradorExercicio
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

		gerarAlternativas("" + r);
		addResolucao("Fazemos a substituição \\(y = " + a + "^x\\):");
		addResolucao("\\(y^2 - " + soma + "y + " + aPowR + " = 0\\)");
		addResolucao("Fatorando: \\((y - 1)(y - " + aPowR + ") = 0\\)");
		addResolucao("\\(y = 1 \\quad\\) ou \\(\\quad y = " + aPowR + "\\)");
		addResolucao("Voltando para \\(x\\):");
		addResolucao("\\(" + a + "^x = 1 = " + a + "^0 \\Rightarrow x = 0\\)");
		addResolucao("\\(" + a + "^x = " + aPowR + " = " + a + "^{" + r + "} \\Rightarrow x = " + r + "\\)");
		addResolucao("Maior solução: \\(x = \\mathbf{" + r + "}\\)");
	}
}
