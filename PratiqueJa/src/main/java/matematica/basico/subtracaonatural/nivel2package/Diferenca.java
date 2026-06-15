package matematica.basico.subtracaonatural.nivel2package;

import matematica.GeradorExercicio;

public class Diferenca extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 20 + rand.nextInt(40);
		int a = b + 10 + rand.nextInt(50);
		int dif = a - b;

		int tipo = rand.nextInt(4);
		switch (tipo)
		{
			case 0:
				addParagrafo("A turma A tem \\(" + a + "\\) alunos e a turma B tem \\(" + b + "\\). Quantos alunos a mais há na turma A?");
				break;
			case 1:
				addParagrafo("Uma loja vendeu \\(" + a + "\\) peças no mês e outra vendeu \\(" + b + "\\). Qual é a diferença de vendas?");
				break;
			case 2:
				addParagrafo("Pedro marcou \\(" + a + "\\) pontos num jogo e Carlos marcou \\(" + b + "\\). Quantos pontos de diferença houve entre eles?");
				break;
			default:
				addParagrafo("Uma fazenda colheu \\(" + a + "\\) kg de laranja e \\(" + b + "\\) kg de limão. Quantos kg a mais de laranja foram colhidos?");
				break;
		}

		gerarAlternativasInteiras(dif);

		String res = "Para encontrar a diferença entre duas quantidades, subtraímos a menor da maior: \\(\\\\\\)";
		res += "\\(" + a + " - " + b + " = \\mathbf{" + dif + "}\\)";
		setResolucao(res);
	}
}
