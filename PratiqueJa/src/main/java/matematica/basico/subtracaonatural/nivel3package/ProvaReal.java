package matematica.basico.subtracaonatural.nivel3package;

import matematica.GeradorExercicio;

public class ProvaReal extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 300 + rand.nextInt(700);
		int b = 50 + rand.nextInt(a / 2);
		int c = a - b;

		addParagrafo("O caixa registrou que \\(" + a + " - " + b + " = " + c + "\\). Para conferir pela prova real, somou \\(" + c + " + " + b + "\\). Qual deve ser o resultado para o cálculo estar correto?");

		gerarAlternativasInteiras(a);

		String res = "Pela prova real: se \\(a - b = c\\), então \\(c + b = a\\). \\(\\\\\\)";
		res += "Verificando: \\(\\\\\\)";
		res += "\\(" + c + " + " + b + " = \\mathbf{" + a + "}\\)";
		setResolucao(res);
	}
}
