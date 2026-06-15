package matematica.basico.divisaonatural.nivel2package;

import matematica.GeradorExercicio;

public class MissingDividendo extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 3 + rand.nextInt(7);
		int q = 10 + rand.nextInt(20);
		int r = rand.nextInt(b);
		int a = b * q + r;

		if (r == 0)
		{
			addParagrafo("Uma divisão tem divisor \\(" + b + "\\) e quociente \\(" + q + "\\), com resto zero. Qual é o dividendo?");
			gerarAlternativasInteiras(a);

			String res = "Na divisão exata: dividendo \\(=\\) divisor \\(\\times\\) quociente. \\(\\\\\\)";
			res += "\\(\\square = " + b + " \\times " + q + " = \\mathbf{" + a + "}\\)";
			setResolucao(res);
		}
		else
		{
			addParagrafo("Uma divisão tem divisor \\(" + b + "\\), quociente \\(" + q + "\\) e resto \\(" + r + "\\). Qual é o dividendo?");
			gerarAlternativasInteiras(a);

			String res = "Pela relação fundamental da divisão: dividendo \\(=\\) divisor \\(\\times\\) quociente \\(+\\) resto. \\(\\\\\\)";
			res += "\\(\\square = " + b + " \\times " + q + " + " + r + " = " + (b * q) + " + " + r + " = \\mathbf{" + a + "}\\)";
			setResolucao(res);
		}
	}
}
