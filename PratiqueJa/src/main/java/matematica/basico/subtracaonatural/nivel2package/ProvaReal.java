package matematica.basico.subtracaonatural.nivel2package;

import matematica.GeradorExercicio;

public class ProvaReal extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 40 + rand.nextInt(60);
		int b = 10 + rand.nextInt(30);
		if (a <= b) b = a / 2;
		int c = a - b;

		addParagrafo("Laura calculou que \\(" + a + " - " + b + " = " + c + "\\). Para verificar pela prova real, ela somou \\(" + c + " + " + b + "\\). Qual deve ser o resultado?");

		gerarAlternativasInteiras(a);

		String res = "Pela prova real: se \\(a - b = c\\), então \\(c + b = a\\). \\(\\\\\\)";
		res += "Verificando: \\(\\\\\\)";
		res += "\\(" + c + " + " + b + " = \\mathbf{" + a + "}\\)";
		setResolucao(res);
	}
}
