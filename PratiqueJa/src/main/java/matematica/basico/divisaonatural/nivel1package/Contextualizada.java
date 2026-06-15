package matematica.basico.divisaonatural.nivel1package;

import matematica.GeradorExercicio;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int q = 2 + rand.nextInt(9);
		int b = 2 + rand.nextInt(9);
		int a = q * b;

		int tipo = rand.nextInt(5);
		switch (tipo)
		{
			case 0:
				addParagrafo("Uma professora tem \\(" + a + "\\) balas para distribuir igualmente entre \\(" + b + "\\) alunos. Quantas balas cada aluno recebe?");
				break;
			case 1:
				addParagrafo("\\(" + a + "\\) laranjas foram colocadas em \\(" + b + "\\) cestas com a mesma quantidade. Quantas laranjas há em cada cesta?");
				break;
			case 2:
				addParagrafo("Um pacote tem \\(" + a + "\\) biscoitos divididos em \\(" + b + "\\) grupos iguais. Quantos biscoitos há em cada grupo?");
				break;
			case 3:
				addParagrafo("\\(" + a + "\\) estudantes foram organizados em \\(" + b + "\\) filas com o mesmo número de pessoas. Quantos estudantes há em cada fila?");
				break;
			default:
				addParagrafo("Pedro tem \\(" + a + "\\) figurinhas e quer dividi-las igualmente em \\(" + b + "\\) álbuns. Quantas figurinhas irão para cada álbum?");
				break;
		}

		gerarAlternativasInteiras(q);

		String res = "Dividimos o total pelo número de partes iguais: \\(\\\\\\)";
		res += "\\(" + a + " \\div " + b + " = \\mathbf{" + q + "}\\)";
		setResolucao(res);
	}
}
