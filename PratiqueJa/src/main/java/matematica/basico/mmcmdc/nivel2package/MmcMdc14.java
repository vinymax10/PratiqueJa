package matematica.basico.mmcmdc.nivel2package;

import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ProblemaMmcMdc;
import matematica.basico.mmcmdc.nivel1package.TextoMMC;

// Problema contextualizado de MMC (encontro de ciclos), segunda variação
public class MmcMdc14 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaMmcMdc problema = TextoMMC.getProblemaMMC();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao("\\(" + problema.resolucao() + "\\)");
	}
}
