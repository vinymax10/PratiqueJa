package matematica.basico.divisaonatural.nivel2package;

import matematica.GeradorExercicio;

public class DivisaoPor10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = rand.nextInt(3);

		if (tipo == 0)
		{
			int q = 10 + rand.nextInt(90);
			int a = q * 10;
			addParagrafo("Qual é o resultado de \\(" + a + " \\div 10\\)?");
			gerarAlternativasInteiras(q);
			addResolucao("Dividir por \\(10\\) equivale a remover um zero à direita:");
			addResolucao("\\(" + a + " \\div 10 = \\mathbf{" + q + "}\\)");
		}
		else if (tipo == 1)
		{
			int q = 10 + rand.nextInt(90);
			int a = q * 100;
			addParagrafo("Qual é o resultado de \\(" + a + " \\div 100\\)?");
			gerarAlternativasInteiras(q);
			addResolucao("Dividir por \\(100\\) equivale a remover dois zeros à direita:");
			addResolucao("\\(" + a + " \\div 100 = \\mathbf{" + q + "}\\)");
		}
		else
		{
			int q = 10 + rand.nextInt(90);
			int a = q * 10;
			int contexto = rand.nextInt(3);
			if (contexto == 0)
				addParagrafo("Uma empresa produziu \\(" + a + "\\) peças divididas igualmente entre \\(10\\) filiais. Quantas peças cada filial recebeu?");
			else if (contexto == 1)
				addParagrafo("\\(" + a + "\\) reais foram distribuídos igualmente entre \\(10\\) pessoas. Quanto cada pessoa recebeu?");
			else
				addParagrafo("Um caminhão percorreu \\(" + a + "\\) km em \\(10\\) dias, andando o mesmo tanto por dia. Quantos km percorreu por dia?");
			gerarAlternativasInteiras(q);
			addResolucao("Dividir por \\(10\\) remove um zero à direita:");
			addResolucao("\\(" + a + " \\div 10 = \\mathbf{" + q + "}\\)");
		}
	}
}
