package matematica.basico.mmcmdc.nivel1package;

import matematica.GeradorExercicio;

// Conceito de divisor (pré-requisito do MDC): um número é divisor do outro?
public class MmcMdc11 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int divisor = 2 + rand.nextInt(11);  // 2..12
		int fator   = 2 + rand.nextInt(11);  // 2..12
		int numero  = divisor * fator;

		boolean afirmacaoVerdadeira = rand.nextBoolean();
		if(!afirmacaoVerdadeira)
			numero += 1 + rand.nextInt(divisor - 1); // deixa de ser divisível

		boolean correta = numero % divisor == 0;

		addParagrafo("\\(" + divisor + "\\) é divisor de \\(" + numero + "\\)?");
		gerarAlternativasBoolean(correta);

		String res = "\\(" + divisor + "\\) é divisor de \\(" + numero + "\\) quando a divisão é exata. \\(\\\\\\)";
		if(correta)
			res += "\\(" + numero + " \\div " + divisor + " = " + (numero / divisor) + "\\) (divisão exata). \\(\\\\\\)Logo, é divisor: \\(\\mathbf{Sim}\\)";
		else
			res += "\\(" + numero + " \\div " + divisor + " = " + (numero / divisor) + "\\) e sobra resto \\(" + (numero % divisor) + "\\). \\(\\\\\\)Logo, não é divisor: \\(\\mathbf{Não}\\)";
		setResolucao(res);
	}
}
