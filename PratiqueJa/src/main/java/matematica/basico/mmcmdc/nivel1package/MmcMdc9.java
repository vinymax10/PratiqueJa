package matematica.basico.mmcmdc.nivel1package;

import matematica.GeradorExercicio;

// Caso especial: quando um número divide o outro, o MDC é o menor
public class MmcMdc9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int menor = 2 + rand.nextInt(11);  // 2..12
		int fator = 2 + rand.nextInt(8);   // 2..9
		int maior = menor * fator;

		addParagrafo("Sabendo que \\(" + menor + "\\) é divisor de \\(" + maior + "\\), calcule \\(\\text{MDC}(" + menor + ",\\," + maior + ")\\).");
		gerarAlternativasInteiras(menor);

		String res = "Quando um número divide o outro, o MDC é o menor deles. \\(\\\\\\)";
		res += "Como \\(" + menor + "\\) divide \\(" + maior + "\\): \\(\\\\\\)";
		res += "\\(\\text{MDC}(" + menor + ",\\," + maior + ") = \\mathbf{" + menor + "}\\)";
		setResolucao(res);
	}
}
