package matematica.basico.subtracaonatural.nivel3package;

import matematica.GeradorExercicio;

public class TresEtapas extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 100 + rand.nextInt(200);
		int c = 50 + rand.nextInt(150);
		int a = b + c + 100 + rand.nextInt(300);
		int ab = a - b;
		int resultado = ab - c;

		int tipo = rand.nextInt(3);
		switch (tipo)
		{
			case 0:
				addParagrafo("Um depósito tinha \\(" + a + "\\) unidades em estoque. Foram entregues \\(" + b + "\\) na segunda-feira e \\(" + c + "\\) na quarta-feira. Quantas unidades restaram?");
				break;
			case 1:
				addParagrafo("Uma empresa tinha \\(" + a + "\\) reais em caixa. Pagou \\(" + b + "\\) reais de fornecedores e \\(" + c + "\\) reais de aluguel. Quanto restou em caixa?");
				break;
			default:
				addParagrafo("Uma biblioteca tinha \\(" + a + "\\) livros. Foram emprestados \\(" + b + "\\) livros na primeira semana e \\(" + c + "\\) na segunda. Quantos livros restaram na prateleira?");
				break;
		}

		gerarAlternativasInteiras(resultado);

		String res = "Subtraímos em dois passos: \\(\\\\\\)";
		res += "\\(" + a + " - " + b + " = " + ab + "\\) \\(\\\\\\)";
		res += "\\(" + ab + " - " + c + " = \\mathbf{" + resultado + "}\\)";
		setResolucao(res);
	}
}
