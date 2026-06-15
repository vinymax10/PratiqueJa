package matematica.basico.subtracaonatural.nivel3package;

import matematica.GeradorExercicio;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 300 + rand.nextInt(700);
		int b = 50 + rand.nextInt(a / 2);
		int c = a - b;

		int tipo = rand.nextInt(5);
		switch (tipo)
		{
			case 0:
				addParagrafo("Uma cidade tem \\(" + a + "\\) habitantes. \\(" + b + "\\) moradores se mudaram para outra cidade. Quantos habitantes restaram?");
				break;
			case 1:
				addParagrafo("Um armazém tinha \\(" + a + "\\) kg de arroz. Foram distribuídos \\(" + b + "\\) kg para doações. Quantos kg restaram no armazém?");
				break;
			case 2:
				addParagrafo("Uma empresa produziu \\(" + a + "\\) peças no mês. \\(" + b + "\\) peças foram devolvidas por defeito. Quantas peças ficaram em estoque?");
				break;
			case 3:
				addParagrafo("Uma biblioteca tinha \\(" + a + "\\) livros. \\(" + b + "\\) livros foram emprestados. Quantos livros restaram na prateleira?");
				break;
			default:
				addParagrafo("Um ônibus saiu com \\(" + a + "\\) passageiros. Ao longo do trajeto, \\(" + b + "\\) desembarcaram. Quantos passageiros chegaram ao destino final?");
				break;
		}

		gerarAlternativasInteiras(c);

		String res = "Para encontrar o que resta, subtraímos: \\(\\\\\\)";
		res += "\\(" + a + " - " + b + " = \\mathbf{" + c + "}\\)";
		setResolucao(res);
	}
}
