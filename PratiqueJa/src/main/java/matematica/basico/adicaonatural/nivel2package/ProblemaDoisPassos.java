package matematica.basico.adicaonatural.nivel2package;

import matematica.GeradorExercicio;

public class ProblemaDoisPassos extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 15 + rand.nextInt(35);
		int b = 10 + rand.nextInt(30);
		int c = 5 + rand.nextInt(25);
		int ab = a + b;
		int soma = ab + c;

		int tipo = rand.nextInt(4);
		switch (tipo)
		{
			case 0:
				addParagrafo("Uma padaria vendeu \\(" + a + "\\) pães pela manhã, \\(" + b + "\\) à tarde e \\(" + c + "\\) à noite. Quantos pães foram vendidos no total?");
				break;
			case 1:
				addParagrafo("Pedro caminhou \\(" + a + "\\) km na segunda-feira, \\(" + b + "\\) km na terça e \\(" + c + "\\) km na quarta. Quantos km caminhou ao todo?");
				break;
			case 2:
				addParagrafo("Numa gincana, o time azul marcou \\(" + a + "\\) pontos na primeira rodada, \\(" + b + "\\) na segunda e \\(" + c + "\\) na terceira. Qual foi a pontuação total?");
				break;
			default:
				addParagrafo("Uma loja recebeu \\(" + a + "\\) produtos na segunda-feira, \\(" + b + "\\) na terça e \\(" + c + "\\) na quarta. Quantos produtos recebeu ao todo?");
				break;
		}

		gerarAlternativasInteiras(soma);

		String res = "Somamos as três quantidades em dois passos: \\(\\\\\\)";
		res += "\\(" + a + " + " + b + " = " + ab + "\\) \\(\\\\\\)";
		res += "\\(" + ab + " + " + c + " = \\mathbf{" + soma + "}\\)";
		setResolucao(res);
	}
}
