package matematica.basico.adicaonatural.nivel1package;

import matematica.GeradorExercicio;

public class Multiplos10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		if (rand.nextBoolean())
		{
			int ad = 1 + rand.nextInt(9);
			int bd = 1 + rand.nextInt(9);
			int a = ad * 10;
			int b = bd * 10;
			int soma = a + b;

			addParagrafo("Calcule: \\(" + a + " + " + b + "\\)");
			gerarAlternativasInteiras(soma);

			addResolucao("Somamos apenas as dezenas:");
			addResolucao("\\(" + ad + " + " + bd + " = " + (ad + bd) + "\\)");
			addResolucao("Portanto: \\(" + a + " + " + b + " = \\mathbf{" + soma + "}\\)");
		}
		else
		{
			int ac = 1 + rand.nextInt(9);
			int bc = 1 + rand.nextInt(9);
			int a = ac * 100;
			int b = bc * 100;
			int soma = a + b;

			addParagrafo("Calcule: \\(" + a + " + " + b + "\\)");
			gerarAlternativasInteiras(soma);

			addResolucao("Somamos apenas as centenas:");
			addResolucao("\\(" + ac + " + " + bc + " = " + (ac + bc) + "\\)");
			addResolucao("Portanto: \\(" + a + " + " + b + " = \\mathbf{" + soma + "}\\)");
		}
	}
}
