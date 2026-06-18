package matematica.basico.mmcmdc.nivel1package;

import matematica.GeradorExercicio;

// Conceito de múltiplo (pré-requisito do MMC): o número é múltiplo do outro?
public class MmcMdc10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int base  = 2 + rand.nextInt(11);  // 2..12
		int fator = 2 + rand.nextInt(11);  // 2..12
		int numero = base * fator;

		boolean afirmacaoVerdadeira = rand.nextBoolean();
		if(!afirmacaoVerdadeira)
			numero += 1 + rand.nextInt(base - 1); // deixa de ser múltiplo

		boolean correta = numero % base == 0;

		addParagrafo("O número \\(" + numero + "\\) é múltiplo de \\(" + base + "\\)?");
		gerarAlternativasBoolean(correta);

		String res = "Um número é múltiplo de \\(" + base + "\\) quando a divisão por \\(" + base + "\\) é exata. \\(\\\\\\)";
		if(correta)
			res += "\\(" + numero + " \\div " + base + " = " + (numero / base) + "\\) (divisão exata). \\(\\\\\\)Logo, é múltiplo: \\(\\mathbf{Sim}\\)";
		else
			res += "\\(" + numero + " \\div " + base + " = " + (numero / base) + "\\) e sobra resto \\(" + (numero % base) + "\\). \\(\\\\\\)Logo, não é múltiplo: \\(\\mathbf{Não}\\)";
		setResolucao(res);
	}
}
