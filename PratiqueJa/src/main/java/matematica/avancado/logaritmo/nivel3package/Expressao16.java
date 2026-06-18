package matematica.avancado.logaritmo.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;

public class Expressao16 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(5);
		int c = 2 + rand.nextInt(9);
		int b = 1 + rand.nextInt(10);
		int maxBase = (int) Math.min(Math.log(1000) / Math.log(c), 10);
		int d = 1 + rand.nextInt(Math.max(1, maxBase));
		int val = (int) Math.pow(c, d);

		Racional x = new Racional(d * b, a);
		x.fatoracao(2);

		String enunciado, res;
		if (a == 1)
		{
			enunciado = "\\(" + b + " \\cdot \\log_{" + c + "} " + val + "\\)";
			res = "Propriedade da potência: \\(k \\cdot \\log_b a = \\log_b a^k\\\\";
			res += "\\log_{" + c + "} " + val + "^{" + b + "} = \\log_{" + c + "} " + c + "^{" + (d * b) + "}\\\\";
			res += "= \\mathbf{" + x.toStringLatex() + "}\\)";
		}
		else
		{
			enunciado = "\\(" + b + " \\cdot \\log_{" + c + "} \\sqrt[" + a + "]{" + val + "}\\)";
			Racional potRac = new Racional(b, a);
			potRac.fatoracao(2);
			res = "Reescrevendo a raiz como potência e aplicando a propriedade: \\(\\\\\\)";
			res += "\\(" + b + " \\cdot \\log_{" + c + "} " + val + "^{1/" + a + "} = \\log_{" + c + "} " + val + "^{" + potRac.showFrac() + "}\\\\";
			res += "= \\log_{" + c + "} " + c + "^{" + x.showFrac() + "} = \\mathbf{" + x.toStringLatex() + "}\\)";
		}

		addParagrafo("Calcule o valor da expressão:");
		addParagrafo(enunciado);
		gerarAlternativas(x);
		setResolucao(res);
	}
}
