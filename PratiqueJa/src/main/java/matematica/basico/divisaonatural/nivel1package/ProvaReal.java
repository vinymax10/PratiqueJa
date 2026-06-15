package matematica.basico.divisaonatural.nivel1package;

import matematica.GeradorExercicio;

public class ProvaReal extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int q = 2 + rand.nextInt(8);
		int b = 2 + rand.nextInt(8);
		int a = q * b;

		addParagrafo("Ana calculou \\(" + a + " \\div " + b + " = " + q + "\\). Para verificar, ela multiplicou o quociente pelo divisor. Qual deve ser o resultado?");
		gerarAlternativasInteiras(a);

		String res = "A prova real da divisão é: divisor \\(\\times\\) quociente \\(+\\) resto \\(=\\) dividendo. \\(\\\\\\)";
		res += "Como o resto é zero: \\(\\\\\\)";
		res += "\\(" + b + " \\times " + q + " = \\mathbf{" + a + "}\\)";
		setResolucao(res);
	}
}
