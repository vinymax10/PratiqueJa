package matematica.avancado.funcoestrigonometricas.nivel3package;

import matematica.GeradorExercicio;

public class Exercicio9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Expressão complexa com ângulos notáveis — resultado inteiro
		// choice 0: sen(30°)cos(60°) + cos(30°)sen(60°) = 1/4 + 3/4 = 1
		// choice 1: (sen(60°) + cos(30°))² = (√3)² = 3
		// choice 2: tg²(45°) + cos(180°) + sen(270°) = 1 - 1 - 1 = -1
		// choice 3: tg(45°)·(1 - cos(180°)) + sen(0°) = 1·2 + 0 = 2
		int choice = rand.nextInt(4);

		String expressao;
		String[] resolucaoCalc;
		int resultado;

		switch (choice) {
			case 0:
				expressao   = "\\(\\operatorname{sen}(30°)\\cos(60°) + \\cos(30°)\\operatorname{sen}(60°)\\)";
				resultado   = 1;
				resolucaoCalc = new String[] {
						"\\(\\dfrac{1}{2}\\cdot\\dfrac{1}{2} + \\dfrac{\\sqrt{3}}{2}\\cdot\\dfrac{\\sqrt{3}}{2} =\\)",
						"\\(\\dfrac{1}{4} + \\dfrac{3}{4} = \\mathbf{1}\\)"
				};
				break;
			case 1:
				expressao   = "\\((\\operatorname{sen}(60°) + \\cos(30°))^2\\)";
				resultado   = 3;
				resolucaoCalc = new String[] {
						"\\(\\left(\\dfrac{\\sqrt{3}}{2} + \\dfrac{\\sqrt{3}}{2}\\right)^2 =\\)",
						"\\((\\sqrt{3})^2 = \\mathbf{3}\\)"
				};
				break;
			case 2:
				expressao   = "\\(\\operatorname{tg}^2(45°) + \\cos(180°) + \\operatorname{sen}(270°)\\)";
				resultado   = -1;
				resolucaoCalc = new String[] {
						"\\(1^2 + (-1) + (-1) = 1 - 1 - 1 = \\mathbf{-1}\\)"
				};
				break;
			default:
				expressao   = "\\(\\operatorname{tg}(45°)\\cdot(1 - \\cos(180°)) + \\operatorname{sen}(0°)\\)";
				resultado   = 2;
				resolucaoCalc = new String[] {
						"\\(1\\cdot(1 - (-1)) + 0 =\\)",
						"\\(1\\cdot 2 = \\mathbf{2}\\)"
				};
				break;
		}

		addParagrafo("Calcule o valor da expressão " + expressao + ".");
		gerarAlternativasInteiras(resultado, 4, true);

		addResolucao("Substituindo os valores notáveis:");
		for (String passo : resolucaoCalc)
			addResolucao(passo);
	}
}
