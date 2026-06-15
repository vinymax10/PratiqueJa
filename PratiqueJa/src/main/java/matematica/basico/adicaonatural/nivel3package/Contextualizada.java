package matematica.basico.adicaonatural.nivel3package;

import matematica.GeradorExercicio;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 100 + rand.nextInt(500);
		int b = 100 + rand.nextInt(500);
		int soma = a + b;

		int tipo = rand.nextInt(5);
		switch (tipo)
		{
			case 0:
				addParagrafo("Uma cidade tem \\(" + a + "\\) moradores no bairro A e \\(" + b + "\\) moradores no bairro B. Qual é a população total dos dois bairros?");
				break;
			case 1:
				addParagrafo("Um armazém recebeu \\(" + a + "\\) kg de arroz e \\(" + b + "\\) kg de feijão. Quantos kg de alimentos foram recebidos no total?");
				break;
			case 2:
				addParagrafo("No primeiro semestre, uma empresa vendeu \\(" + a + "\\) produtos. No segundo semestre, vendeu \\(" + b + "\\) produtos. Quantos produtos foram vendidos no ano?");
				break;
			case 3:
				addParagrafo("Uma escola tem \\(" + a + "\\) alunos no turno matutino e \\(" + b + "\\) alunos no turno vespertino. Quantos alunos a escola tem no total?");
				break;
			default:
				addParagrafo("Uma biblioteca recebeu uma doação de \\(" + a + "\\) livros de ficção e \\(" + b + "\\) livros de não-ficção. Quantos livros foram doados no total?");
				break;
		}

		gerarAlternativasInteiras(soma);

		String res = "Para encontrar o total, somamos as duas quantidades: \\(\\\\\\)";
		res += "\\(" + a + " + " + b + " = \\mathbf{" + soma + "}\\)";
		setResolucao(res);
	}
}
