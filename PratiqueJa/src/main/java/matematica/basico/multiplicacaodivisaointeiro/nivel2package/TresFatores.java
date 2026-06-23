package matematica.basico.multiplicacaodivisaointeiro.nivel2package;

import matematica.GeradorExercicio;
import matematica.basico.multiplicacaodivisaointeiro.ResolucaoMDInteiro;

public class TresFatores extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 2 + rand.nextInt(9); if (rand.nextBoolean()) a = -a;
		int b = 2 + rand.nextInt(9); if (rand.nextBoolean()) b = -b;
		int c = 2 + rand.nextInt(9); if (rand.nextBoolean()) c = -c;
		int resultado = a * b * c;

		String aFmt = a < 0 ? "(" + a + ")" : "" + a;
		String bFmt = b < 0 ? "(" + b + ")" : "" + b;
		String cFmt = c < 0 ? "(" + c + ")" : "" + c;

		addParagrafo("Calcule \\(" + aFmt + " \\times " + bFmt + " \\times " + cFmt + "\\).");
		gerarAlternativasInteirasComNegativos(resultado);

		int negativos = (a < 0 ? 1 : 0) + (b < 0 ? 1 : 0) + (c < 0 ? 1 : 0);
		boolean positivo = (negativos % 2 == 0);
		int ab = a * b;
		String abFmt = ab < 0 ? "(" + ab + ")" : "" + ab;

		addResolucao("\\(" + Math.abs(a) + " \\times " + Math.abs(b) + " = " + Math.abs(ab) + "\\).");
		addResolucao(negativos + " fator" + (negativos == 1 ? "" : "es") + " negativo" + (negativos == 1 ? "" : "s") + " "
			+ (positivo ? "(par) → produto positivo." : "(ímpar) → produto negativo."));
		for(String passo : ResolucaoMDInteiro.multiplicacao(ab, c))
			addResolucao(passo);
	}
}
