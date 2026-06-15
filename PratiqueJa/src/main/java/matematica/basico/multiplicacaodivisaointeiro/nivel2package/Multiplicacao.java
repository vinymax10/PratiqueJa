package matematica.basico.multiplicacaodivisaointeiro.nivel2package;

import matematica.GeradorExercicio;
import matematica.basico.multiplicacaodivisaointeiro.ResolucaoMDInteiro;

public class Multiplicacao extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 10 + rand.nextInt(91); if (rand.nextBoolean()) a = -a;
		int b = 10 + rand.nextInt(91); if (rand.nextBoolean()) b = -b;

		String aFmt = a < 0 ? "(" + a + ")" : "" + a;
		String bFmt = b < 0 ? "(" + b + ")" : "" + b;

		addParagrafo("Calcule \\(" + aFmt + " \\times " + bFmt + "\\).");
		gerarAlternativasInteirasComNegativos(a * b);
		setResolucao(ResolucaoMDInteiro.multiplicacao(a, b));
	}
}
