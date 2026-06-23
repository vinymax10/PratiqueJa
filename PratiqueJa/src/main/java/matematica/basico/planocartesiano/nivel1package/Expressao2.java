package matematica.basico.planocartesiano.nivel1package;

import matematica.GeradorExercicio;

public class Expressao2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int px, py, dist;
		switch (rand.nextInt(4))
		{
			case 0:  px = 3;  py = 4;  dist = 5;  break;
			case 1:  px = 4;  py = 3;  dist = 5;  break;
			case 2:  px = 6;  py = 8;  dist = 10; break;
			default: px = 5;  py = 12; dist = 13; break;
		}
		if (rand.nextBoolean()) px = -px;
		if (rand.nextBoolean()) py = -py;

		int px2 = px * px;
		int py2 = py * py;
		int soma = px2 + py2;
		String strPx = px < 0 ? "(" + px + ")" : "" + px;
		String strPy = py < 0 ? "(" + py + ")" : "" + py;

		addParagrafo("Calcule a distância do ponto \\( P(" + px + ",\\;" + py + ") \\) à origem \\( O(0,\\;0) \\).");

		gerarAlternativasInteiras(dist);

		addResolucao("A distância de um ponto \\(P(x,\\;y)\\) à origem \\(O(0,\\;0)\\) é a hipotenusa do triângulo retângulo cujos catetos são \\(|x|\\) e \\(|y|\\). Pelo Teorema de Pitágoras:");
		addResolucao("\\(d(P,\\;O) = \\sqrt{x^2 + y^2}\\).");
		addResolucao("Substituindo \\(x = " + px + "\\) e \\(y = " + py + "\\):");
		addResolucao("\\(d = \\sqrt{" + strPx + "^2 + " + strPy + "^2} =\\)");
		addResolucao("\\(\\sqrt{" + px2 + " + " + py2 + "} = \\sqrt{" + soma + "} = " + dist + "\\)");
	}
}
