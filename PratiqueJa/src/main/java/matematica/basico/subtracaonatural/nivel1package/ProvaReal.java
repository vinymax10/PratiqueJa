package matematica.basico.subtracaonatural.nivel1package;

import matematica.GeradorExercicio;

public class ProvaReal extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 25 + rand.nextInt(35);
		int b = 5 + rand.nextInt(15);
		int c = a - b;

		addParagrafo("Pedro calculou que \\(" + a + " - " + b + " = " + c + "\\). Para verificar pela prova real, ele somou \\(" + c + " + " + b + "\\). Qual deve ser o resultado?");

		gerarAlternativasInteiras(a);

		String res = "A prova real da subtração usa a operação inversa: se \\(a - b = c\\), então \\(c + b = a\\). \\(\\\\\\)";
		res += "Verificando: \\(\\\\\\)";
		res += "\\(" + c + " + " + b + " = \\mathbf{" + a + "}\\)";
		setResolucao(res);
	}
}
