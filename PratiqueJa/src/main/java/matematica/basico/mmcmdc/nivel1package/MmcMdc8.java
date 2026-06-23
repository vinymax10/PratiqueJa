package matematica.basico.mmcmdc.nivel1package;

import matematica.GeradorExercicio;

// Caso especial: quando um número divide o outro, o MMC é o maior
public class MmcMdc8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int menor = 2 + rand.nextInt(11);  // 2..12
		int fator = 2 + rand.nextInt(8);   // 2..9
		int maior = menor * fator;

		addParagrafo("Sabendo que \\(" + menor + "\\) é divisor de \\(" + maior + "\\), calcule \\(\\text{MMC}(" + menor + ",\\," + maior + ")\\).");
		gerarAlternativasInteiras(maior);

		addResolucao("Quando um número divide o outro, o MMC é o maior deles.");
		addResolucao("Como \\(" + menor + "\\) divide \\(" + maior + "\\):");
		addResolucao("\\(\\text{MMC}(" + menor + ",\\," + maior + ") = \\mathbf{" + maior + "}\\)");
	}
}
