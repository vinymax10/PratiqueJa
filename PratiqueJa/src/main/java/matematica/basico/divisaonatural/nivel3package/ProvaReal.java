package matematica.basico.divisaonatural.nivel3package;

import matematica.GeradorExercicio;

public class ProvaReal extends GeradorExercicio
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
			addParagrafo("Um sistema calculou \\(" + a + " \\div " + b + " = " + q + "\\). Qual é o resultado da prova real?");
			gerarAlternativasInteiras(a);

			String res = "A prova real da divisão exata: divisor \\(\\times\\) quociente \\(=\\) dividendo. \\(\\\\\\)";
			res += "\\(" + b + " \\times " + q + " = \\mathbf{" + a + "}\\)";
			setResolucao(res);
		}
		else
		{
			addParagrafo("Uma calculadora obteve quociente \\(" + q + "\\) e resto \\(" + r + "\\) ao dividir \\(" + a + "\\) por \\(" + b + "\\). Qual é o resultado da prova real?");
			int provaCorreta = b * q + r;
			gerarAlternativasInteiras(provaCorreta);

			String res = "A prova real: divisor \\(\\times\\) quociente \\(+\\) resto \\(=\\) dividendo. \\(\\\\\\)";
			res += "\\(" + b + " \\times " + q + " + " + r + " = " + (b * q) + " + " + r + " = \\mathbf{" + provaCorreta + "}\\)";
			setResolucao(res);
		}
	}
}
