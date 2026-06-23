package matematica.basico.divisaonatural.nivel3package;

import matematica.GeradorExercicio;

public class MissingDividendo extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 12 + rand.nextInt(38);
		int q = 100 + rand.nextInt(200);
		int r = rand.nextInt(b);
		int a = b * q + r;

		if (r == 0)
		{
			addParagrafo("Uma divisão apresenta divisor \\(" + b + "\\) e quociente \\(" + q + "\\), sendo exata. Qual é o dividendo?");
			gerarAlternativasInteiras(a);

			addResolucao("Na divisão exata: dividendo \\(=\\) divisor \\(\\times\\) quociente.");
			addResolucao("\\(\\square = " + b + " \\times " + q + " = \\mathbf{" + a + "}\\)");
		}
		else
		{
			addParagrafo("Uma divisão apresenta divisor \\(" + b + "\\), quociente \\(" + q + "\\) e resto \\(" + r + "\\). Qual é o dividendo?");
			gerarAlternativasInteiras(a);

			addResolucao("Pela relação fundamental da divisão: dividendo \\(=\\) divisor \\(\\times\\) quociente \\(+\\) resto.");
			addResolucao("\\(\\square = " + b + " \\times " + q + " + " + r + " = " + (b * q) + " + " + r + " = \\mathbf{" + a + "}\\)");
		}
	}
}
