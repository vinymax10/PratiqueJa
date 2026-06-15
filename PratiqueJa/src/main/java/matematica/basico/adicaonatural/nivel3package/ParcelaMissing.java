package matematica.basico.adicaonatural.nivel3package;

import matematica.GeradorExercicio;

public class ParcelaMissing extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 100 + rand.nextInt(400);
		int soma = b + 100 + rand.nextInt(500);
		int a = soma - b;

		if (rand.nextBoolean())
			addParagrafo("Qual é o valor de \\(\\square\\) na equação \\(\\square + " + b + " = " + soma + "\\)?");
		else
			addParagrafo("Qual é o valor de \\(\\square\\) na equação \\(" + b + " + \\square = " + soma + "\\)?");

		gerarAlternativasInteiras(a);

		String res = "Para encontrar a parcela desconhecida, subtraímos a parcela conhecida da soma: \\(\\\\\\)";
		res += "\\(\\square = " + soma + " - " + b + " = \\mathbf{" + a + "}\\)";
		setResolucao(res);
	}
}
