package matematica.basico.adicaonatural.nivel1package;

import matematica.GeradorExercicio;

public class Verificacao extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 10 + rand.nextInt(30);
		int b = 10 + rand.nextInt(30);
		int c = a + b;

		addParagrafo("Marina calculou que \\(" + a + " + " + b + " = " + c + "\\). Para verificar o resultado, ela subtraiu \\(" + c + " - " + b + "\\). Qual deve ser o resultado dessa subtração?");

		gerarAlternativasInteiras(a);

		String res = "Para verificar que \\(" + a + " + " + b + " = " + c + "\\), calculamos \\(" + c + " - " + b + "\\): \\(\\\\\\)";
		res += "\\(" + c + " - " + b + " = \\mathbf{" + a + "}\\)";
		setResolucao(res);
	}
}
