package matematica.basico.divisaonatural.nivel1package;

import matematica.GeradorExercicio;

public class Tabuada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 + rand.nextInt(9);
		int q = 2 + rand.nextInt(9);
		int a = b * q;

		int tipo = rand.nextInt(4);
		switch (tipo)
		{
			case 0:
				addParagrafo("Sabendo que \\(" + b + " \\times " + q + " = " + a + "\\), qual é o valor de \\(" + a + " \\div " + b + "\\)?");
				break;
			case 1:
				addParagrafo("A multiplicação \\(" + q + " \\times " + b + " = " + a + "\\) implica que \\(" + a + " \\div " + b + " =\\) ?");
				break;
			case 2:
				addParagrafo("Se \\(" + b + "\\) grupos de \\(" + q + "\\) itens formam \\(" + a + "\\) itens no total, quantos itens há em cada grupo se dividirmos \\(" + a + "\\) em \\(" + b + "\\) partes?");
				break;
			default:
				addParagrafo("A divisão é a operação inversa da multiplicação. Se \\(" + b + " \\times " + q + " = " + a + "\\), então \\(" + a + " \\div " + b + " =\\) ?");
				break;
		}

		gerarAlternativasInteiras(q);

		String res = "A divisão é a operação inversa da multiplicação: \\(\\\\\\)";
		res += "\\(" + b + " \\times " + q + " = " + a + " \\implies " + a + " \\div " + b + " = \\mathbf{" + q + "}\\)";
		setResolucao(res);
	}
}
