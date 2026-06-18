package matematica.avancado.geometriaanalitica.nivel3package;

import matematica.GeradorExercicio;

public class Exercicio9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int m1 = -2 + rand.nextInt(5);
		if (m1 == 0) m1 = 1;
		int m2 = m1 + 1 + rand.nextInt(3);

		int xInt = -3 + rand.nextInt(7);
		int b1 = -4 + rand.nextInt(9);
		int yInt = m1 * xInt + b1;
		int b2 = yInt - m2 * xInt;

		boolean askX = rand.nextBoolean();
		int answer = askX ? xInt : yInt;

		String sinalB1 = b1 >= 0 ? " + " + b1 : " - " + Math.abs(b1);
		String sinalB2 = b2 >= 0 ? " + " + b2 : " - " + Math.abs(b2);
		int diffM = m1 - m2;
		int diffB = b2 - b1;

		addParagrafo("Determine a " + (askX ? "abscissa" : "ordenada") + " do ponto de interseção das retas "
				+ "\\(r_1: y = " + m1 + "x" + sinalB1 + "\\) e "
				+ "\\(r_2: y = " + m2 + "x" + sinalB2 + "\\).");

		gerarAlternativasInteiras(answer, 4, false);

		String res;
		if (askX)
		{
			res = "Igualando as equações das retas:"
					+ "\\(\\\\\\)"
					+ "\\(" + m1 + "x" + sinalB1 + " = " + m2 + "x" + sinalB2 + "\\\\"
					+ "(" + m1 + " - " + m2 + ")x = " + b2 + " - (" + b1 + ")\\\\"
					+ diffM + "x = " + diffB + "\\\\"
					+ "x = \\dfrac{" + diffB + "}{" + diffM + "} = \\mathbf{" + xInt + "}\\)";
		}
		else
		{
			res = "Igualando as equações para encontrar \\(x\\), depois substituindo:"
					+ "\\(\\\\\\)"
					+ "\\(" + m1 + "x" + sinalB1 + " = " + m2 + "x" + sinalB2 + "\\\\"
					+ diffM + "x = " + diffB + " \\Rightarrow x = " + xInt + "\\\\"
					+ "y = " + m1 + " \\cdot " + xInt + sinalB1 + " = \\mathbf{" + yInt + "}\\)";
		}
		setResolucao(res);
	}
}
