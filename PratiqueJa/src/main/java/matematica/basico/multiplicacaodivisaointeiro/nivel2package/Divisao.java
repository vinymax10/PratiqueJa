package matematica.basico.multiplicacaodivisaointeiro.nivel2package;

import matematica.GeradorExercicio;
import matematica.basico.multiplicacaodivisaointeiro.ResolucaoMDInteiro;

public class Divisao extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int q = 10 + rand.nextInt(91); if (rand.nextBoolean()) q = -q;
		int b = 2 + rand.nextInt(19);  if (rand.nextBoolean()) b = -b;
		int a = q * b;

		String aFmt = a < 0 ? "(" + a + ")" : "" + a;
		String bFmt = b < 0 ? "(" + b + ")" : "" + b;

		addParagrafo("Calcule \\(" + aFmt + " \\div " + bFmt + "\\).");
		gerarAlternativasInteirasComNegativos(q);
		setResolucao(ResolucaoMDInteiro.divisao(a, b));
	}
}
