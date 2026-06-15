package matematica.avancado.funcoestrigonometricas.nivel3package;

import matematica.GeradorExercicio;

public class Exercicio3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Expressão combinada com ângulos notáveis
		// choice 0: sen(30°)+cos(60°)+tg(45°) = 1/2+1/2+1 = 2
		// choice 1: sen(90°)-cos(180°) = 1-(-1) = 2
		// choice 2: sen(0°)+cos(180°)+tg(45°) = 0+(-1)+1 = 0
		// choice 3: cos(90°)+tg(0°)+sen(270°) = 0+0+(-1) = -1
		int choice = rand.nextInt(4);

		String expressao, resolucaoCalc;
		int resultado;

		switch (choice) {
			case 0:
				expressao   = "\\(\\operatorname{sen}(30°) + \\cos(60°) + \\operatorname{tg}(45°)\\)";
				resultado   = 2;
				resolucaoCalc = "\\(\\dfrac{1}{2} + \\dfrac{1}{2} + 1 = \\mathbf{2}\\)";
				break;
			case 1:
				expressao   = "\\(\\operatorname{sen}(90°) - \\cos(180°)\\)";
				resultado   = 2;
				resolucaoCalc = "\\(1 - (-1) = 1 + 1 = \\mathbf{2}\\)";
				break;
			case 2:
				expressao   = "\\(\\operatorname{sen}(0°) + \\cos(180°) + \\operatorname{tg}(45°)\\)";
				resultado   = 0;
				resolucaoCalc = "\\(0 + (-1) + 1 = \\mathbf{0}\\)";
				break;
			default:
				expressao   = "\\(\\cos(90°) + \\operatorname{tg}(0°) + \\operatorname{sen}(270°)\\)";
				resultado   = -1;
				resolucaoCalc = "\\(0 + 0 + (-1) = \\mathbf{-1}\\)";
				break;
		}

		addParagrafo("Calcule o valor da expressão " + expressao + ".");
		gerarAlternativasInteiras(resultado, 4, true);

		String res = "Substituindo os valores notáveis:"
				+ "\\(\\\\\\)"
				+ resolucaoCalc;
		setResolucao(res);
	}
}
