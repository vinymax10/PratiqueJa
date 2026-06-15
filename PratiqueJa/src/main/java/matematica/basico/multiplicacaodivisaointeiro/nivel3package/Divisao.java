package matematica.basico.multiplicacaodivisaointeiro.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.multiplicacaodivisaointeiro.ResolucaoMDInteiro;

public class Divisao extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int q = 100 + rand.nextInt(901); if (rand.nextBoolean()) q = -q;
		int b = 2 + rand.nextInt(49);    if (rand.nextBoolean()) b = -b;
		int a = q * b;

		String aFmt = a < 0 ? "(" + a + ")" : "" + a;
		String bFmt = b < 0 ? "(" + b + ")" : "" + b;

		addParagrafo("Calcule \\(" + aFmt + " \\div " + bFmt + "\\).");
		gerarAlternativasInteirasComNegativos(q);
		setResolucao(ResolucaoMDInteiro.divisao(a, b));
	}
}
