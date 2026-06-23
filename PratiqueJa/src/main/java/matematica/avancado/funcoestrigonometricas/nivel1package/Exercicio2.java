package matematica.avancado.funcoestrigonometricas.nivel1package;

import matematica.GeradorExercicio;

public class Exercicio2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// {grau, displayRad, num, den}
		String[][] data = {
			{"30",  "\\dfrac{\\pi}{6}",   "1", "6"},
			{"45",  "\\dfrac{\\pi}{4}",   "1", "4"},
			{"60",  "\\dfrac{\\pi}{3}",   "1", "3"},
			{"90",  "\\dfrac{\\pi}{2}",   "1", "2"},
			{"120", "\\dfrac{2\\pi}{3}",  "2", "3"},
			{"150", "\\dfrac{5\\pi}{6}",  "5", "6"},
			{"180", "\\pi",               "1", "1"},
			{"270", "\\dfrac{3\\pi}{2}",  "3", "2"},
			{"360", "2\\pi",              "2", "1"},
		};

		int idx  = rand.nextInt(data.length);
		int grau = Integer.parseInt(data[idx][0]);
		String rad = data[idx][1];
		int num  = Integer.parseInt(data[idx][2]);
		int den  = Integer.parseInt(data[idx][3]);

		addParagrafo("Converta \\(" + rad + "\\) radianos para graus.");

		gerarAlternativasInteiras(grau, 4, false);

		String fracPart;
		if (num == 1 && den == 1)
			fracPart = "180";
		else if (den == 1)
			fracPart = num + " \\times 180";
		else if (num == 1)
			fracPart = "\\dfrac{180}{" + den + "}";
		else
			fracPart = "\\dfrac{" + num + " \\times 180}{" + den + "}";

		addResolucao("Usando \\(\\theta_{\\circ} = \\text{rad} \\times \\dfrac{180}{\\pi}\\):");
		addResolucao("\\(" + rad + " \\times \\dfrac{180}{\\pi} = " + fracPart + " = \\mathbf{" + grau + "°}\\)");
	}
}
