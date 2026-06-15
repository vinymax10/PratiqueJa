package matematica.basico.divisaonatural.nivel3package;

import matematica.GeradorExercicio;

public class QuantasVezes extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 12 + rand.nextInt(28);
		int q = 100 + rand.nextInt(150);
		int r = rand.nextInt(b);
		int a = b * q + r;

		int tipo = rand.nextInt(4);
		switch (tipo)
		{
			case 0:
				addParagrafo("Um reservatório tem capacidade para \\(" + b + "\\) litros por bombeamento. Quantas vezes a bomba precisa operar para encher \\(" + a + "\\) litros? Sobram litros?");
				break;
			case 1:
				addParagrafo("Uma transportadora carrega \\(" + b + "\\) caixas por viagem. Quantas viagens completas são necessárias para transportar \\(" + a + "\\) caixas? Quantas ficam para outra viagem?");
				break;
			case 2:
				addParagrafo("Uma máquina produz \\(" + b + "\\) peças por hora. Quantas horas completas leva para produzir \\(" + a + "\\) peças? Quantas peças são feitas na hora incompleta?");
				break;
			default:
				addParagrafo("Um armazém comporta \\(" + b + "\\) toneladas de carga por compartimento. Quantos compartimentos completos são preenchidos com \\(" + a + "\\) toneladas? Sobra alguma?");
				break;
		}

		gerarAlternativasInteiras(q);

		String res;
		if (r == 0)
		{
			res = "Dividimos para encontrar quantas vezes \\(" + b + "\\) cabe em \\(" + a + "\\): \\(\\\\\\)";
			res += "\\(" + a + " \\div " + b + " = \\mathbf{" + q + "}\\) sem resto.";
		}
		else
		{
			res = "Dividimos para encontrar quantas vezes \\(" + b + "\\) cabe em \\(" + a + "\\): \\(\\\\\\)";
			res += "\\(" + a + " \\div " + b + " = \\mathbf{" + q + "}\\) com resto \\(" + r + "\\). \\(\\\\\\)";
			res += "Prova real: \\(" + b + " \\times " + q + " + " + r + " = " + (b * q) + " + " + r + " = \\mathbf{" + a + "}\\)";
		}
		setResolucao(res);
	}
}
