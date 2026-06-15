package matematica.basico.adicaonatural.nivel3package;

import matematica.GeradorExercicio;

public class ProblemaDoisPassos extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 100 + rand.nextInt(300);
		int b = 100 + rand.nextInt(300);
		int c = 50 + rand.nextInt(200);
		int ab = a + b;
		int soma = ab + c;

		int tipo = rand.nextInt(4);
		switch (tipo)
		{
			case 0:
				addParagrafo("Uma empresa vendeu \\(" + a + "\\) produtos em janeiro, \\(" + b + "\\) em fevereiro e \\(" + c + "\\) em março. Quantos produtos foram vendidos no primeiro trimestre?");
				break;
			case 1:
				addParagrafo("Um trem percorreu \\(" + a + "\\) km no primeiro dia, \\(" + b + "\\) km no segundo e \\(" + c + "\\) km no terceiro. Qual foi a distância total?");
				break;
			case 2:
				addParagrafo("Uma cidade recebeu \\(" + a + "\\) turistas na primeira semana, \\(" + b + "\\) na segunda e \\(" + c + "\\) na terceira. Quantos turistas ao total?");
				break;
			default:
				addParagrafo("Um armazém recebeu \\(" + a + "\\) kg de arroz, \\(" + b + "\\) kg de feijão e \\(" + c + "\\) kg de trigo. Quantos kg de alimentos no total?");
				break;
		}

		gerarAlternativasInteiras(soma);

		String res = "Somamos as três quantidades em dois passos: \\(\\\\\\)";
		res += "\\(" + a + " + " + b + " = " + ab + "\\) \\(\\\\\\)";
		res += "\\(" + ab + " + " + c + " = \\mathbf{" + soma + "}\\)";
		setResolucao(res);
	}
}
