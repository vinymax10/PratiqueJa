package matematica.avancado.numeroscomplexos.nivel1package;

import matematica.GeradorExercicio;
import matematica.avancado.numeroscomplexos.NumeroComplexo;

public class Expressao15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		NumeroComplexo z = NumeroComplexo.contruir(10);
		int a = (int) z.real.numerador;
		int b = (int) z.imaginaria.numerador; // guaranteed non-zero

		boolean askReal = rand.nextBoolean();
		int correct = askReal ? a : b;

		String pergunta = askReal
			? "Qual a parte real de \\(z\\)?"
			: "Qual a parte imaginária de \\(z\\)?";

		addParagrafo(pergunta);
		addParagrafo("\\(z = " + z + "\\)");
		gerarAlternativas("" + correct);

		String res = "Na forma \\(z = a + bi\\), identificamos: \\(\\\\\\)";
		res += "\\(a = " + a + " \\quad b = " + b + "\\). \\(\\\\\\)";
		if (askReal)
			res += "\\(\\operatorname{Re}(z) = a = \\mathbf{" + a + "}\\)";
		else
			res += "\\(\\operatorname{Im}(z) = b = \\mathbf{" + b + "}\\)";
		setResolucao(res);
	}
}
