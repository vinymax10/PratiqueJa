package matematica.basico.mmcmdc.nivel2package;

import matematica.MMC;
import matematica.GeradorExercicio;

// Múltiplos comuns são os múltiplos do MMC: pede o terceiro múltiplo comum
public class MmcMdc16 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b;
		do
		{
			a = 3 + rand.nextInt(15); // 3..17
			b = 3 + rand.nextInt(15);
		}
		while(a == b || MMC.mmc(a, b) > 150);

		int mmc = (int) MMC.mmc(a, b);
		int resultado = 3 * mmc;

		addParagrafo("Os múltiplos comuns de \\(" + a + "\\) e \\(" + b + "\\) aparecem em sequência. Qual é o terceiro deles?");
		gerarAlternativasInteiras(resultado);

		String res = "Os múltiplos comuns de dois números são os múltiplos do MMC: \\(\\text{MMC},\\; 2\\,\\text{MMC},\\; 3\\,\\text{MMC},\\dots\\) \\(\\\\\\)";
		res += "\\(\\text{MMC}(" + a + ",\\," + b + ") = " + mmc + "\\). \\(\\\\\\)";
		res += "O terceiro múltiplo comum é \\(3 \\times " + mmc + " = \\mathbf{" + resultado + "}\\).";
		setResolucao(res);
	}
}
