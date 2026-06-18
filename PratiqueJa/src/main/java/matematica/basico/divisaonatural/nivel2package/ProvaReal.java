package matematica.basico.divisaonatural.nivel2package;

import matematica.GeradorExercicio;
import matematica.Nomes;

public class ProvaReal extends GeradorExercicio
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
			addParagrafo(Nomes.masculino(rand) + " calculou \\(" + a + " \\div " + b + " = " + q + "\\). Para verificar, ele aplicou a prova real. Qual deve ser o resultado?");
			gerarAlternativasInteiras(a);

			String res = "A prova real da divisão exata: divisor \\(\\times\\) quociente \\(=\\) dividendo. \\(\\\\\\)";
			res += "\\(" + b + " \\times " + q + " = \\mathbf{" + a + "}\\)";
			setResolucao(res);
		}
		else
		{
			addParagrafo(Nomes.feminino(rand) + " calculou \\(" + a + " \\div " + b + "\\) e encontrou quociente \\(" + q + "\\) e resto \\(" + r + "\\). Qual é a prova real desta divisão?");
			int provaCorreta = b * q + r;
			gerarAlternativasInteiras(provaCorreta);

			String res = "A prova real da divisão com resto: divisor \\(\\times\\) quociente \\(+\\) resto \\(=\\) dividendo. \\(\\\\\\)";
			res += "\\(" + b + " \\times " + q + " + " + r + " = " + (b * q) + " + " + r + " = \\mathbf{" + provaCorreta + "}\\)";
			setResolucao(res);
		}
	}
}
