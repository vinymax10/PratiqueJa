package matematica.basico.adicaonatural.nivel3package;

import matematica.GeradorExercicio;

public class Verificacao extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 100 + rand.nextInt(400);
		int b = 100 + rand.nextInt(400);
		int c = a + b;

		addParagrafo("Uma caixa registrou que \\(" + a + " + " + b + " = " + c + "\\). Para conferir, a supervisora subtraiu \\(" + c + " - " + b + "\\). Qual deve ser o resultado para o cálculo estar correto?");

		gerarAlternativasInteiras(a);

		String res = "Para verificar que \\(" + a + " + " + b + " = " + c + "\\), calculamos \\(" + c + " - " + b + "\\): \\(\\\\\\)";
		res += "\\(" + c + " - " + b + " = \\mathbf{" + a + "}\\)";
		setResolucao(res);
	}
}
