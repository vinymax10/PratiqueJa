package matematica.avancado.geometriaanalitica.nivel2package;

import matematica.GeradorExercicio;
import matematica.Racional;

public class Exercicio9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int m = -3 + rand.nextInt(7);
		if (m == 0) m = 1;
		int b = -5 + rand.nextInt(11);

		Racional mPerp = new Racional(-1, m);
		mPerp.fatoracao(2);

		String sinalB = b >= 0 ? " + " + b : " - " + Math.abs(b);
		String equacao = "y = " + m + "x" + sinalB;

		addParagrafo("A reta \\(r\\) tem equação \\(" + equacao + "\\). "
				+ "Qual é o coeficiente angular de uma reta perpendicular a \\(r\\)?");

		gerarAlternativas(mPerp);

		String res = "Retas perpendiculares satisfazem \\(m_1 \\cdot m_2 = -1\\):"
				+ "\\(\\\\\\)"
				+ "\\(m_\\perp \\cdot " + m + " = -1\\\\"
				+ "m_\\perp = \\dfrac{-1}{" + m + "} = \\mathbf{" + mPerp.showDfrac() + "}\\)";
		setResolucao(res);
	}
}
