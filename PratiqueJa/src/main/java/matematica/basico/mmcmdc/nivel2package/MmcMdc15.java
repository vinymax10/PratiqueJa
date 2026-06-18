package matematica.basico.mmcmdc.nivel2package;

import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ProblemaMmcMdc;

// Problema contextualizado de MDC (divisão em partes iguais), segunda variação
public class MmcMdc15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaMmcMdc problema = TextoMDC.getProblemaMMC();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao("\\(" + problema.resolucao() + "\\)");
	}
}
