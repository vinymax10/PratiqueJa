package matematica.avancado.geometriaanalitica.nivel2package;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;

public class Exercicio6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int caseType = rand.nextInt(3);

		String correto;
		String enunciado;
		String[] res;

		if (caseType == 0)
		{
			// Paralelas distintas: mesma inclinação, interceptos diferentes
			int m  = -3 + rand.nextInt(7);
			if (m == 0) m = 2;
			int b1 = -5 + rand.nextInt(11);
			int b2 = b1;
			while (b2 == b1) b2 = -5 + rand.nextInt(11);

			String sinalB1 = b1 >= 0 ? " + " + b1 : " - " + Math.abs(b1);
			String sinalB2 = b2 >= 0 ? " + " + b2 : " - " + Math.abs(b2);

			enunciado = "Determine a posição relativa das retas "
					+ "\\(r: y = " + m + "x" + sinalB1 + "\\) e "
					+ "\\(s: y = " + m + "x" + sinalB2 + "\\).";

			correto = "Paralelas distintas";
			res = new String[] {
					"As duas retas têm o mesmo coeficiente angular \\(m = " + m + "\\) e coeficientes lineares diferentes "
							+ "\\(" + b1 + " \\neq " + b2 + "\\).",
					"Retas com mesmo \\(m\\) e \\(b\\) diferentes são \\(\\mathbf{paralelas\\;distintas}\\)."
			};
		}
		else if (caseType == 1)
		{
			// Perpendiculares: m1 * m2 = -1
			int m1 = 1 + rand.nextInt(3);
			int m2num = -1;
			int m2den = m1;

			String m2str = m1 == 1 ? "-1" : "-\\dfrac{1}{" + m1 + "}";

			int b1 = -4 + rand.nextInt(9);
			int b2 = -4 + rand.nextInt(9);

			String sinalB1 = b1 >= 0 ? " + " + b1 : " - " + Math.abs(b1);
			String sinalB2 = b2 >= 0 ? " + " + b2 : " - " + Math.abs(b2);

			enunciado = "Determine a posição relativa das retas "
					+ "\\(r: y = " + m1 + "x" + sinalB1 + "\\) e "
					+ "\\(s: y = " + m2str + "x" + sinalB2 + "\\).";

			correto = "Perpendiculares";
			res = new String[] {
					"O produto dos coeficientes angulares é \\(m_1 \\cdot m_2 = " + m1 + " \\cdot \\left(" + m2str + "\\right) = -1\\).",
					"Quando \\(m_1 \\cdot m_2 = -1\\), as retas são \\(\\mathbf{perpendiculares}\\)."
			};
		}
		else
		{
			// Concorrentes: inclinações diferentes
			int m1 = -3 + rand.nextInt(7);
			if (m1 == 0) m1 = 2;
			int m2 = m1;
			while (m2 == m1) { m2 = -3 + rand.nextInt(7); if (m2 == 0) m2 = 1; }

			int b1 = -4 + rand.nextInt(9);
			int b2 = -4 + rand.nextInt(9);

			String sinalB1 = b1 >= 0 ? " + " + b1 : " - " + Math.abs(b1);
			String sinalB2 = b2 >= 0 ? " + " + b2 : " - " + Math.abs(b2);

			enunciado = "Determine a posição relativa das retas "
					+ "\\(r: y = " + m1 + "x" + sinalB1 + "\\) e "
					+ "\\(s: y = " + m2 + "x" + sinalB2 + "\\).";

			correto = "Concorrentes";
			res = new String[] {
					"As retas têm coeficientes angulares diferentes \\(" + m1 + " \\neq " + m2 + "\\).",
					"Retas com \\(m\\) diferentes se intersectam em um ponto, sendo \\(\\mathbf{concorrentes}\\)."
			};
		}

		addParagrafo(enunciado);

		List<String> distratores = new ArrayList<>();
		if (!correto.equals("Paralelas distintas")) distratores.add("Paralelas distintas");
		if (!correto.equals("Perpendiculares"))     distratores.add("Perpendiculares");
		if (!correto.equals("Concorrentes"))        distratores.add("Concorrentes");
		if (!correto.equals("Coincidentes"))        distratores.add("Coincidentes");

		embaralharEAdicionarAlternativas(correto, distratores);

		for (String passo : res)
			addResolucao(passo);
	}
}
