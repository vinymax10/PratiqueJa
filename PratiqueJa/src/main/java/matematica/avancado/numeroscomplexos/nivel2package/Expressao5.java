package matematica.avancado.numeroscomplexos.nivel2package;

import matematica.GeradorExercicio;

public class Expressao5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int n = 2 + rand.nextInt(3); // coeficiente: 2, 3 ou 4
		int x = 1 + rand.nextInt(8); // incógnita: 1..8
		int c = rand.nextInt(6);     // constante aditiva: 0..5
		int a = n * x + c;           // parte real do lado direito
		int b = 1 + rand.nextInt(9); // parte imaginária (igual em ambos os lados)

		String lhsReal = (c == 0) ? n + "x" : n + "x + " + c;

		addParagrafo("Encontre o valor de \\(x\\).");
		addParagrafo("\\((" + lhsReal + ") + " + b + "i = " + a + " + " + b + "i\\)");
		gerarAlternativas("" + x);

		String res = "Por igualdade de complexos, as partes reais são iguais: \\(\\\\\\)";
		res += "\\(" + lhsReal + " = " + a + "\\). \\(\\\\\\)";
		if (c == 0)
			res += "\\(x = \\dfrac{" + a + "}{" + n + "} = \\mathbf{" + x + "}\\)";
		else
			res += "\\(" + n + "x = " + a + " - " + c + " = " + (a - c) + " \\Rightarrow x = \\dfrac{" + (a - c) + "}{" + n + "} = \\mathbf{" + x + "}\\)";
		setResolucao(res);
	}
}
