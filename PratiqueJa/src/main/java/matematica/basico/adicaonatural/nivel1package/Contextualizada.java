package matematica.basico.adicaonatural.nivel1package;

import matematica.GeradorExercicio;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 10 + rand.nextInt(30);
		int b = 10 + rand.nextInt(30);
		int soma = a + b;

		int tipo = rand.nextInt(5);
		switch (tipo)
		{
			case 0:
				addParagrafo("Ana leu \\(" + a + "\\) livros de ficção e \\(" + b + "\\) livros de aventura. Quantos livros ela leu no total?");
				break;
			case 1:
				addParagrafo("Uma frutaria vendeu \\(" + a + "\\) laranjas pela manhã e \\(" + b + "\\) laranjas à tarde. Quantas laranjas foram vendidas no dia?");
				break;
			case 2:
				addParagrafo("Carlos marcou \\(" + a + "\\) pontos na primeira fase e \\(" + b + "\\) pontos na segunda fase de um jogo. Quantos pontos ele marcou no total?");
				break;
			case 3:
				addParagrafo("Uma escola tem \\(" + a + "\\) alunos no turno da manhã e \\(" + b + "\\) alunos no turno da tarde. Quantos alunos a escola tem no total?");
				break;
			default:
				addParagrafo("Em uma loja, \\(" + a + "\\) produtos foram vendidos pela manhã e \\(" + b + "\\) produtos à tarde. Quantos produtos foram vendidos no total?");
				break;
		}

		gerarAlternativasInteiras(soma);

		String res = "Para encontrar o total, somamos as duas quantidades: \\(\\\\\\)";
		res += "\\(" + a + " + " + b + " = \\mathbf{" + soma + "}\\)";
		setResolucao(res);
	}
}
