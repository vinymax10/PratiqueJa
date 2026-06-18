package matematica.avancado.numeroscomplexos.nivel2package;

import matematica.GeradorExercicio;
import matematica.avancado.numeroscomplexos.NumeroComplexo;

public class Expressao14 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		NumeroComplexo z = NumeroComplexo.contruir(10);
		int a = (int) z.real.numerador;
		int b = (int) z.imaginaria.numerador; // guaranteed non-zero
		NumeroComplexo conj = z.conjugado();
		long produto = (long) a * a + (long) b * b;

		addParagrafo("Calcule \\(z \\cdot \\bar{z}\\).");
		addParagrafo("\\(z = " + z + "\\)");
		gerarAlternativas("" + produto);

		String res = "O conjugado é \\(\\bar{z} = " + conj + "\\). \\(\\\\\\)";
		res += "Usando a propriedade \\(z \\cdot \\bar{z} = a^2 + b^2\\): \\(\\\\\\)";
		res += "\\(z \\cdot \\bar{z} = " + ((long) a * a) + " + " + ((long) b * b) + " = \\mathbf{" + produto + "}\\)";
		setResolucao(res);
	}
}
