package matematica.basico.subtracaonatural.nivel1package;

import matematica.GeradorExercicio;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 20 + rand.nextInt(40);
		int b = 5 + rand.nextInt(a / 3);
		int c = a - b;

		int tipo = rand.nextInt(5);
		switch (tipo)
		{
			case 0:
				addParagrafo("Numa caixa havia \\(" + a + "\\) balas. Maria comeu \\(" + b + "\\) balas. Quantas balas restaram?");
				break;
			case 1:
				addParagrafo("Carlos tinha \\(" + a + "\\) figurinhas e deu \\(" + b + "\\) para seu amigo. Com quantas figurinhas ele ficou?");
				break;
			case 2:
				addParagrafo("Uma livraria tinha \\(" + a + "\\) livros e vendeu \\(" + b + "\\) durante o dia. Quantos livros restaram?");
				break;
			case 3:
				addParagrafo("Numa turma de \\(" + a + "\\) alunos, \\(" + b + "\\) faltaram à aula. Quantos alunos estavam presentes?");
				break;
			default:
				addParagrafo("O trem partiu com \\(" + a + "\\) passageiros. Na próxima estação, \\(" + b + "\\) desceram. Quantos passageiros restaram?");
				break;
		}

		gerarAlternativasInteiras(c);

		String res = "Para encontrar o que resta, subtraímos: \\(\\\\\\)";
		res += "\\(" + a + " - " + b + " = \\mathbf{" + c + "}\\)";
		setResolucao(res);
	}
}
