package matematica.intermediario.radiciacao.nivel3package;

import java.util.Arrays;

import matematica.GeradorExercicio;

// Equação irracional simples: √(x + k) = m → x = m² - k
public class Radiciacao9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int m, k, x;
		do
		{
			m = 3 + rand.nextInt(7); // 3..9
			x = 2 + rand.nextInt(15); // solução 2..16
			k = m * m - x;
		}
		while(k <= 0);

		int m2 = m * m;

		String e1 = "" + (m2 + k); // esqueceu de subtrair k
		String e2 = "" + (x + 1);
		String e3 = "" + (m + k);  // confundiu m² com m
		if(e3.equals("" + x)) e3 = "" + (x + 3);

		addParagrafo("Qual o valor de \\(x\\)?");
		addParagrafo("\\(\\sqrt{x + " + k + "} = " + m + "\\)");
		embaralharEAdicionarAlternativas("" + x, Arrays.asList(e1, e2, e3));
		setResolucao(
			"Elevar ambos os lados ao quadrado:" +
			"\\(\\\\\\)" +
			"\\(x + " + k + " = " + m + "^2 = " + m2 + "\\)" +
			"\\(\\\\\\)" +
			"\\(x = " + m2 + " - " + k + " = \\mathbf{" + x + "}\\)"
		);
	}
}
