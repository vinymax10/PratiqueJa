package matematica.basico.subtracaonatural.nivel1package;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;

public class TermosSubtracao extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 25 + rand.nextInt(40);
		int b = 5 + rand.nextInt(20);
		int c = a - b;

		int pergunta = rand.nextInt(3);

		String numero;
		String termoCorreto;
		List<String> distratores = new ArrayList<>();

		switch (pergunta)
		{
			case 0:
				numero = String.valueOf(a);
				termoCorreto = "Minuendo";
				distratores.add("Subtraendo");
				distratores.add("Diferença");
				distratores.add("Parcela");
				break;
			case 1:
				numero = String.valueOf(b);
				termoCorreto = "Subtraendo";
				distratores.add("Minuendo");
				distratores.add("Diferença");
				distratores.add("Parcela");
				break;
			default:
				numero = String.valueOf(c);
				termoCorreto = "Diferença";
				distratores.add("Minuendo");
				distratores.add("Subtraendo");
				distratores.add("Parcela");
				break;
		}

		addParagrafo("Na subtração \\(" + a + " - " + b + " = " + c + "\\), como se chama o número \\(" + numero + "\\)?");

		embaralharEAdicionarAlternativas(termoCorreto, distratores);

		addResolucao("Na subtração \\(a - b = c\\):");
		addResolucao("Minuendo \\((" + a + ")\\): o número do qual se subtrai.");
		addResolucao("Subtraendo \\((" + b + ")\\): o número que é subtraído.");
		addResolucao("Diferença \\((" + c + ")\\): o resultado da subtração.");
		addResolucao("Portanto, \\(" + numero + "\\) é o \\(\\textbf{" + termoCorreto + "}\\).");
	}
}
