package matematica.basico.divisaonatural.nivel2package;

import matematica.GeradorExercicio;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 3 + rand.nextInt(7);
		int q = 10 + rand.nextInt(20);
		int r = rand.nextInt(b);
		int a = b * q + r;

		int tipo = rand.nextInt(4);
		switch (tipo)
		{
			case 0:
				addParagrafo("Uma escola recebeu \\(" + a + "\\) livros para distribuir igualmente entre \\(" + b + "\\) turmas. Quantos livros cada turma recebe? Sobram livros?");
				break;
			case 1:
				addParagrafo("\\(" + a + "\\) chocolates precisam ser embalados em caixas com \\(" + b + "\\) unidades cada. Quantas caixas completas serão formadas e quantos chocolates sobram?");
				break;
			case 2:
				addParagrafo("Uma fábrica produziu \\(" + a + "\\) peças que devem ser organizadas em lotes de \\(" + b + "\\). Quantos lotes completos há e quantas peças ficam de fora?");
				break;
			default:
				addParagrafo("Um grupo de \\(" + a + "\\) pessoas vai fazer uma viagem em vans que comportam \\(" + b + "\\) passageiros cada. Quantas vans completas são necessárias e quantas pessoas sobram para a última van?");
				break;
		}

		String correta;
		java.util.List<String> distratores = new java.util.ArrayList<>();
		if (r == 0)
		{
			correta = "" + q + " por grupo, sem sobras";
			distratores.add("" + q + " por grupo, sobram " + b);
			distratores.add("" + (q - 1) + " por grupo, sobram " + (b - 1));
			distratores.add("" + (q + 1) + " por grupo, sem sobras");
		}
		else
		{
			correta = "" + q + " grupos completos, sobram " + r;
			distratores.add("" + (q + 1) + " grupos completos, sobram " + r);
			distratores.add("" + q + " grupos completos, sobram " + (r + 1));
			distratores.add("" + (q - 1) + " grupos completos, sobram " + (r + b));
		}
		embaralharEAdicionarAlternativas(correta, distratores);

		String res;
		if (r == 0)
		{
			res = "Dividimos o total pelo tamanho de cada grupo: \\(\\\\\\)";
			res += "\\(" + a + " \\div " + b + " = \\mathbf{" + q + "}\\) sem sobras. \\(\\\\\\)";
			res += "Prova real: \\(" + b + " \\times " + q + " = \\mathbf{" + a + "}\\)";
		}
		else
		{
			res = "Dividimos o total pelo tamanho de cada grupo: \\(\\\\\\)";
			res += "\\(" + a + " \\div " + b + " = " + q + "\\) com resto \\(" + r + "\\). \\(\\\\\\)";
			res += "Portanto, há \\(\\mathbf{" + q + "}\\) grupos completos e sobram \\(\\mathbf{" + r + "}\\). \\(\\\\\\)";
			res += "Prova real: \\(" + b + " \\times " + q + " + " + r + " = " + (b * q) + " + " + r + " = \\mathbf{" + a + "}\\)";
		}
		setResolucao(res);
	}
}
