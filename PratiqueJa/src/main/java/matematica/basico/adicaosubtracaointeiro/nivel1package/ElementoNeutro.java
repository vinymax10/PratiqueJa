package matematica.basico.adicaosubtracaointeiro.nivel1package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;

public class ElementoNeutro extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(15);
		if (rand.nextBoolean()) a = -a;

		int tipo = rand.nextInt(3);
		switch (tipo)
		{
			case 0:
				addParagrafo("Qual é o resultado de \\(" + Auxiliar.getNumber(a, "", true) + " + 0\\)?");
				gerarAlternativasInteirasComNegativos(a);
				addResolucao("O zero é o elemento neutro da adição: qualquer inteiro somado a zero permanece igual.");
				addResolucao("\\(" + Auxiliar.getNumber(a, "", true) + " + 0 = \\mathbf{" + a + "}\\)");
				break;
			case 1:
				addParagrafo("Qual é o resultado de \\(0 " + Auxiliar.getNumber(a, "", false) + "\\)?");
				gerarAlternativasInteirasComNegativos(a);
				addResolucao("O zero é o elemento neutro da adição: qualquer inteiro somado a zero permanece igual.");
				addResolucao("\\(0 " + Auxiliar.getNumber(a, "", false) + " = \\mathbf{" + a + "}\\)");
				break;
			default:
				addParagrafo("Qual é o valor de \\(\\square\\) para que \\(" + Auxiliar.getNumber(a, "", true) + " + \\square = " + a + "\\)?");
				gerarAlternativasInteiras(0);
				addResolucao("O elemento neutro da adição é o zero: somando zero a qualquer inteiro, ele não muda.");
				addResolucao("\\(\\square = \\mathbf{0}\\)");
				break;
		}
	}
}
