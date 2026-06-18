package matematica.basico.adicaonatural.nivel2package;

import matematica.GeradorExercicio;
import matematica.Nomes;

public class Verificacao extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 20 + rand.nextInt(60);
		int b = 20 + rand.nextInt(60);
		int c = a + b;

		addParagrafo(Nomes.masculino(rand) + " calculou que \\(" + a + " + " + b + " = " + c + "\\). Para conferir o resultado, ele subtraiu \\(" + c + " - " + b + "\\). Qual deve ser o resultado dessa subtração?");

		gerarAlternativasInteiras(a);

		String res = "Para verificar que \\(" + a + " + " + b + " = " + c + "\\), calculamos \\(" + c + " - " + b + "\\): \\(\\\\\\)";
		res += "\\(" + c + " - " + b + " = \\mathbf{" + a + "}\\)";
		setResolucao(res);
	}
}
