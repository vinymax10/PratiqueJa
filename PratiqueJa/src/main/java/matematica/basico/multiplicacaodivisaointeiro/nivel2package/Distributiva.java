package matematica.basico.multiplicacaodivisaointeiro.nivel2package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.basico.multiplicacaodivisaointeiro.ResolucaoMDInteiro;

public class Distributiva extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 2 + rand.nextInt(9); if (rand.nextBoolean()) a = -a;
		int b = 2 + rand.nextInt(9); if (rand.nextBoolean()) b = -b;
		int c = 2 + rand.nextInt(9); if (rand.nextBoolean()) c = -c;
		int resultado = a * (b + c);

		String aFmt  = a < 0 ? "(" + a + ")" : "" + a;
		String bFmt  = b < 0 ? "(" + b + ")" : "" + b;
		String bcSum = Auxiliar.getNumber(c, "", false);

		addParagrafo("Aplique a propriedade distributiva e calcule \\(" + aFmt + " \\times (" + bFmt + " " + bcSum + ")\\).");
		gerarAlternativasInteirasComNegativos(resultado);

		int ab = a * b;
		int ac = a * c;
		String abStr = ab >= 0 ? "+" + ab : "" + ab;
		String acStr = Auxiliar.getNumber(ac, "", false);

		addResolucao("Distribuímos o fator \\(" + aFmt + "\\) sobre os termos do parêntese:");
		addResolucao("\\(" + aFmt + " \\times " + bFmt + " = " + abStr + "\\)");
		addResolucao("\\(" + aFmt + " \\times (" + (c >= 0 ? "+" + c : "" + c) + ") = " + acStr + "\\)");
		addResolucao("\\(" + abStr + " " + acStr + " = \\mathbf{" + resultado + "}\\)");
	}
}
