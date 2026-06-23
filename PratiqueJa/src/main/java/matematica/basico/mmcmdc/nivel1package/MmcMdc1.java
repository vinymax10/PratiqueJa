package matematica.basico.mmcmdc.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ProblemaMmcMdc;

public class MmcMdc1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaMmcMdc problema = TextoMMC.getProblemaMMC();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		for(String passo : problema.resolucao())
			addResolucao("\\(" + passo + "\\)");
	}
}
