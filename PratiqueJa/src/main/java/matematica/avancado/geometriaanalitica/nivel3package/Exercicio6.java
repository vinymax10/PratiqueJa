package matematica.avancado.geometriaanalitica.nivel3package;

import matematica.GeradorExercicio;

public class Exercicio6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a  = 1 + rand.nextInt(4);
		int b  = 1 + rand.nextInt(4);
		int x0 = -4 + rand.nextInt(9);
		int y0 = -4 + rand.nextInt(9);
		int c  = -(a * x0 + b * y0);

		// garantir c != 0
		while (c == 0)
		{
			x0++;
			c = -(a * x0 + b * y0);
		}

		String sinalC = c >= 0 ? " + " + c : " - " + Math.abs(c);

		addParagrafo("O ponto \\(P(x;\\;" + y0 + ")\\) pertence à reta \\(" + a + "x + " + b + "y" + sinalC + " = 0\\). Encontre \\(x\\).");

		gerarAlternativasInteiras(x0, 4, false);

		int byPlusC = b * y0 + c;
		int rhs     = -byPlusC;

		String sinalByPlusC = byPlusC >= 0 ? " + " + byPlusC : " - " + Math.abs(byPlusC);

		String resolvido;
		if (a == 1)
		{
			resolvido = "x = " + rhs + " = \\mathbf{" + x0 + "}";
		}
		else
		{
			resolvido = "x = \\dfrac{" + rhs + "}{" + a + "} = \\mathbf{" + x0 + "}";
		}

		addResolucao("Substituindo \\(y = " + y0 + "\\) na equação da reta:");
		addResolucao("\\(" + a + "x" + sinalByPlusC + " = 0\\)");
		addResolucao("\\(" + a + "x = " + rhs + "\\)");
		addResolucao("\\(" + resolvido + "\\)");
	}
}
