package matematica.basico.mmcmdc.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ProblemaMmcMdc;

// Problema contextualizado com três números (MMC ou MDC), quarta variação
public class MmcMdc14 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaMmcMdc problema = TextoMmcMdc.getProblemaMMC();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao("\\(" + problema.resolucao() + "\\)");
	}
}
