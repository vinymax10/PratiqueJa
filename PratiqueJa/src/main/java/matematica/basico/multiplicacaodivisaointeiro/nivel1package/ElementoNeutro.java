package matematica.basico.multiplicacaodivisaointeiro.nivel1package;

import matematica.GeradorExercicio;

import java.util.ArrayList;
import java.util.List;

public class ElementoNeutro extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(10);
		if (rand.nextBoolean()) a = -a;
		String aFmt = a < 0 ? "(" + a + ")" : "" + a;

		int tipo = rand.nextInt(3);
		switch (tipo)
		{
			case 0:
				addParagrafo("Calcule: \\(" + aFmt + " \\times 1\\)");
				gerarAlternativasInteirasComNegativos(a);
				addResolucao("O \\(1\\) é o elemento neutro da multiplicação: qualquer inteiro multiplicado por \\(1\\) permanece igual.");
				addResolucao("\\(" + aFmt + " \\times 1 = \\mathbf{" + a + "}\\)");
				break;
			case 1:
				addParagrafo("Calcule: \\(" + aFmt + " \\times 0\\)");
				List<String> distratores1 = new ArrayList<>();
				distratores1.add("\\(" + a + "\\)");
				distratores1.add("\\(" + (-a) + "\\)");
				distratores1.add("\\(1\\)");
				embaralharEAdicionarAlternativas("\\(0\\)", distratores1);
				addResolucao("O \\(0\\) é o elemento absorvente da multiplicação: qualquer inteiro multiplicado por \\(0\\) resulta em \\(0\\).");
				addResolucao("\\(" + aFmt + " \\times 0 = \\mathbf{0}\\)");
				break;
			default:
				addParagrafo("Calcule: \\((-1) \\times " + aFmt + "\\)");
				gerarAlternativasInteirasComNegativos(-a);
				addResolucao("Multiplicar por \\(-1\\) inverte o sinal do número:");
				addResolucao("\\((-1) \\times " + aFmt + " = \\mathbf{" + (-a) + "}\\)");
				break;
		}
	}
}
