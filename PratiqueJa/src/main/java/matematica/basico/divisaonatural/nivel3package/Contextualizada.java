package matematica.basico.divisaonatural.nivel3package;

import matematica.GeradorExercicio;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 12 + rand.nextInt(28);
		int q = 100 + rand.nextInt(150);
		int r = rand.nextInt(b);
		int a = b * q + r;

		int tipo = rand.nextInt(4);
		switch (tipo)
		{
			case 0:
				addParagrafo("Uma cooperativa colheu \\(" + a + "\\) kg de grãos que serão distribuídos igualmente entre \\(" + b + "\\) produtores. Quantos kg cada produtor recebe e quantos kg sobram?");
				break;
			case 1:
				addParagrafo("Uma gráfica imprimiu \\(" + a + "\\) folhetos que devem ser organizados em pacotes de \\(" + b + "\\) unidades. Quantos pacotes completos são formados e quantos folhetos sobram?");
				break;
			case 2:
				addParagrafo("Um armazém tem \\(" + a + "\\) caixas que serão transportadas em caminhões com capacidade para \\(" + b + "\\) caixas cada. Quantos caminhões completos partem e quantas caixas ficam para o próximo?");
				break;
			default:
				addParagrafo("Uma escola comprou \\(" + a + "\\) lápis para distribuir igualmente entre \\(" + b + "\\) turmas. Quantos lápis cada turma recebe e quantos sobram?");
				break;
		}

		String correta;
		java.util.List<String> distratores = new java.util.ArrayList<>();
		if (r == 0)
		{
			correta = "" + q + " por parte, sem sobras";
			distratores.add("" + q + " por parte, sobram " + b);
			distratores.add("" + (q - 1) + " por parte, sobram " + (b - 1));
			distratores.add("" + (q + 1) + " por parte, sem sobras");
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
			res = "Dividimos o total pelo número de partes: \\(\\\\\\)";
			res += "\\(" + a + " \\div " + b + " = \\mathbf{" + q + "}\\) sem sobras. \\(\\\\\\)";
			res += "Prova real: \\(" + b + " \\times " + q + " = \\mathbf{" + a + "}\\)";
		}
		else
		{
			res = "Dividimos o total pelo número de partes: \\(\\\\\\)";
			res += "\\(" + a + " \\div " + b + " = " + q + "\\) com resto \\(" + r + "\\). \\(\\\\\\)";
			res += "Portanto, há \\(\\mathbf{" + q + "}\\) partes completas e sobram \\(\\mathbf{" + r + "}\\). \\(\\\\\\)";
			res += "Prova real: \\(" + b + " \\times " + q + " + " + r + " = " + (b * q) + " + " + r + " = \\mathbf{" + a + "}\\)";
		}
		setResolucao(res);
	}
}
