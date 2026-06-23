package matematica.avancado.numeroscomplexos.nivel2package;

import matematica.GeradorExercicio;
import matematica.avancado.numeroscomplexos.NumeroComplexo;

public class Expressao4 extends GeradorExercicio
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

		addResolucao("O conjugado é \\(\\bar{z} = " + conj + "\\).");
		addResolucao("Usando a propriedade \\(z \\cdot \\bar{z} = a^2 + b^2\\):");
		addResolucao("\\(z \\cdot \\bar{z} = " + ((long) a * a) + " + " + ((long) b * b) + " = \\mathbf{" + produto + "}\\)");
	}
}
