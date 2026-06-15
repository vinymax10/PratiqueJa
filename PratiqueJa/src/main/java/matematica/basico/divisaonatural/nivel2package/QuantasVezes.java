package matematica.basico.divisaonatural.nivel2package;

import matematica.GeradorExercicio;

public class QuantasVezes extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 3 + rand.nextInt(7);
		int q = 10 + rand.nextInt(20);
		int r = rand.nextInt(b);
		int a = b * q + r;

		int tipo = rand.nextInt(4);
		switch (tipo)
		{
			case 0:
				addParagrafo("Um tanque comporta \\(" + b + "\\) litros por vasilha. Quantas vasilhas cheias cabem em \\(" + a + "\\) litros? Sobra algum litro?");
				break;
			case 1:
				addParagrafo("Um ônibus comporta \\(" + b + "\\) passageiros. Quantos ônibus cheios são necessários para \\(" + a + "\\) pessoas? Quantas ficam no último ônibus incompleto?");
				break;
			case 2:
				addParagrafo("Uma caixa armazena \\(" + b + "\\) garrafas. Quantas caixas completas formam \\(" + a + "\\) garrafas e quantas garrafas sobram?");
				break;
			default:
				addParagrafo("Quantas vezes o número \\(" + b + "\\) cabe em \\(" + a + "\\)? Há algum resto?");
				break;
		}

		gerarAlternativasInteiras(q);

		String res;
		if (r == 0)
		{
			res = "Dividimos para descobrir quantas vezes \\(" + b + "\\) cabe em \\(" + a + "\\): \\(\\\\\\)";
			res += "\\(" + a + " \\div " + b + " = \\mathbf{" + q + "}\\) sem resto.";
		}
		else
		{
			res = "Dividimos para descobrir quantas vezes \\(" + b + "\\) cabe em \\(" + a + "\\): \\(\\\\\\)";
			res += "\\(" + a + " \\div " + b + " = \\mathbf{" + q + "}\\) com resto \\(" + r + "\\). \\(\\\\\\)";
			res += "Prova real: \\(" + b + " \\times " + q + " + " + r + " = " + (b * q) + " + " + r + " = \\mathbf{" + a + "}\\)";
		}
		setResolucao(res);
	}
}
