package matematica.basico.subtracaonatural.nivel2package;

import matematica.GeradorExercicio;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 40 + rand.nextInt(60);
		int b = 10 + rand.nextInt(30);
		if (a <= b) b = a / 2;
		int c = a - b;

		int tipo = rand.nextInt(5);
		switch (tipo)
		{
			case 0:
				addParagrafo("Um mercado tinha \\(" + a + "\\) caixas de leite e vendeu \\(" + b + "\\) no dia. Quantas caixas restaram?");
				break;
			case 1:
				addParagrafo("Ana tinha \\(" + a + "\\) reais e gastou \\(" + b + "\\) reais numa livraria. Com quanto ela ficou?");
				break;
			case 2:
				addParagrafo("Uma fazenda colheu \\(" + a + "\\) kg de laranja. Foram entregues \\(" + b + "\\) kg ao mercado. Quantos kg restaram?");
				break;
			case 3:
				addParagrafo("Numa corrida, João percorreu \\(" + a + "\\) km. Após \\(" + b + "\\) km, quantos km ainda faltavam para chegar?");
				break;
			default:
				addParagrafo("Uma escola tinha \\(" + a + "\\) alunos matriculados. \\(" + b + "\\) alunos se transferiram. Quantos alunos restaram?");
				break;
		}

		gerarAlternativasInteiras(c);

		String res = "Para encontrar o que resta, subtraímos: \\(\\\\\\)";
		res += "\\(" + a + " - " + b + " = \\mathbf{" + c + "}\\)";
		setResolucao(res);
	}
}
