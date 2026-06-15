package matematica.basico.adicaonatural.nivel2package;

import matematica.GeradorExercicio;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 20 + rand.nextInt(70);
		int b = 10 + rand.nextInt(70);
		int soma = a + b;

		int tipo = rand.nextInt(5);
		switch (tipo)
		{
			case 0:
				addParagrafo("Uma livraria vendeu \\(" + a + "\\) livros pela manhã e \\(" + b + "\\) livros à tarde. Quantos livros foram vendidos no dia?");
				break;
			case 1:
				addParagrafo("Pedro andou \\(" + a + "\\) km de bicicleta na segunda-feira e \\(" + b + "\\) km na terça-feira. Quantos km ele andou ao todo?");
				break;
			case 2:
				addParagrafo("A turma coletou \\(" + a + "\\) tampinhas de garrafa na primeira semana e \\(" + b + "\\) na segunda semana. Quantas tampinhas foram coletadas no total?");
				break;
			case 3:
				addParagrafo("Uma fazenda colheu \\(" + a + "\\) laranjas pela manhã e \\(" + b + "\\) laranjas à tarde. Quantas laranjas foram colhidas no total?");
				break;
			default:
				addParagrafo("Numa competição, Carlos obteve \\(" + a + "\\) pontos na primeira prova e \\(" + b + "\\) pontos na segunda prova. Quantos pontos ele acumulou no total?");
				break;
		}

		gerarAlternativasInteiras(soma);

		String res = "Para encontrar o total, somamos as duas quantidades: \\(\\\\\\)";
		res += "\\(" + a + " + " + b + " = \\mathbf{" + soma + "}\\)";
		setResolucao(res);
	}
}
