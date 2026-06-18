package matematica.basico.adicaonatural.nivel2package;

import matematica.GeradorExercicio;

// Soma mental de dois números de dois dígitos (nível 2, apresentação inline).
public class SomaMental extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 11 + rand.nextInt(89); // 11..99
		int b = 11 + rand.nextInt(89); // 11..99
		addParagrafo("Calcule mentalmente: \\(" + a + " + " + b + " = \\,?\\)");
		gerarAlternativasInteiras(a + b);
		setResolucao("\\(" + a + " + " + b + " = \\mathbf{" + (a + b) + "}\\)");
	}
}
