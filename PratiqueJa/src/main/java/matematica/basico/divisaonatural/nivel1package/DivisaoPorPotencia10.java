package matematica.basico.divisaonatural.nivel1package;

import matematica.GeradorExercicio;

public class DivisaoPorPotencia10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = rand.nextInt(2);

		if (tipo == 0)
		{
			int q = 1 + rand.nextInt(9);
			int a = q * 10;
			addParagrafo("Qual é o resultado de \\(" + a + " \\div 10\\)?");
			gerarAlternativasInteiras(q);
			String res = "Dividir por \\(10\\) equivale a remover um zero à direita do número: \\(\\\\\\)";
			res += "\\(" + a + " \\div 10 = \\mathbf{" + q + "}\\)";
			setResolucao(res);
		}
		else
		{
			int q = 1 + rand.nextInt(9);
			int a = q * 100;
			addParagrafo("Qual é o resultado de \\(" + a + " \\div 100\\)?");
			gerarAlternativasInteiras(q);
			String res = "Dividir por \\(100\\) equivale a remover dois zeros à direita do número: \\(\\\\\\)";
			res += "\\(" + a + " \\div 100 = \\mathbf{" + q + "}\\)";
			setResolucao(res);
		}
	}
}
