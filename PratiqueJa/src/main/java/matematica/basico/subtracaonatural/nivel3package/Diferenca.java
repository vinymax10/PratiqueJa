package matematica.basico.subtracaonatural.nivel3package;

import matematica.GeradorExercicio;

public class Diferenca extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 100 + rand.nextInt(400);
		int a = b + 50 + rand.nextInt(400);
		int dif = a - b;

		int tipo = rand.nextInt(4);
		switch (tipo)
		{
			case 0:
				addParagrafo("Cidade A tem \\(" + a + "\\) habitantes e cidade B tem \\(" + b + "\\). Quantos habitantes a mais tem a cidade A?");
				break;
			case 1:
				addParagrafo("Uma empresa faturou \\(" + a + "\\) reais em janeiro e \\(" + b + "\\) reais em fevereiro. Qual foi a diferença de faturamento?");
				break;
			case 2:
				addParagrafo("Um aeroporto recebeu \\(" + a + "\\) voos em março e \\(" + b + "\\) voos em abril. Quantos voos a mais houve em março?");
				break;
			default:
				addParagrafo("Um estádio comporta \\(" + a + "\\) torcedores e \\(" + b + "\\) ingressos foram vendidos. Quantos lugares ficaram vazios?");
				break;
		}

		gerarAlternativasInteiras(dif);

		String res = "Para encontrar a diferença entre duas quantidades, subtraímos a menor da maior: \\(\\\\\\)";
		res += "\\(" + a + " - " + b + " = \\mathbf{" + dif + "}\\)";
		setResolucao(res);
	}
}
