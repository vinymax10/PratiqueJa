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
				String res0 = "O zero é o elemento neutro da adição: qualquer inteiro somado a zero permanece igual. \\(\\\\\\)";
				res0 += "\\(" + Auxiliar.getNumber(a, "", true) + " + 0 = \\mathbf{" + a + "}\\)";
				setResolucao(res0);
				break;
			case 1:
				addParagrafo("Qual é o resultado de \\(0 " + Auxiliar.getNumber(a, "", false) + "\\)?");
				gerarAlternativasInteirasComNegativos(a);
				String res1 = "O zero é o elemento neutro da adição: qualquer inteiro somado a zero permanece igual. \\(\\\\\\)";
				res1 += "\\(0 " + Auxiliar.getNumber(a, "", false) + " = \\mathbf{" + a + "}\\)";
				setResolucao(res1);
				break;
			default:
				addParagrafo("Qual é o valor de \\(\\square\\) para que \\(" + Auxiliar.getNumber(a, "", true) + " + \\square = " + a + "\\)?");
				gerarAlternativasInteiras(0);
				String res2 = "O elemento neutro da adição é o zero: somando zero a qualquer inteiro, ele não muda. \\(\\\\\\)";
				res2 += "\\(\\square = \\mathbf{0}\\)";
				setResolucao(res2);
				break;
		}
	}
}
