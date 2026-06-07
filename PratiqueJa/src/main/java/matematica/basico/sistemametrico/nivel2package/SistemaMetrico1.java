package matematica.basico.sistemametrico.nivel2package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Capacidade: conversão entre L, mL e kL
public class SistemaMetrico1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = rand.nextInt(4);

		if (tipo == 0)
		{
			// L → mL: × 1000
			int l  = 1 + rand.nextInt(10);
			int ml = l * 1000;

			addParagrafo("Converta para mililitros \\((\\text{mL})\\):");
			addParagrafo("\\(" + l + "\\,\\text{L}\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + ml + "\\,\\text{mL}\\)",
				Arrays.asList(
					"\\(" + (l * 100) + "\\,\\text{mL}\\)",
					"\\(" + (l * 10) + "\\,\\text{mL}\\)",
					"\\(" + (ml * 10) + "\\,\\text{mL}\\)"
				)
			);
			setResolucao(
				"1 L = 1000 mL → multiplicar por 1000:" +
				"\\(\\\\\\)" +
				"\\(" + l + " \\times 1000 = \\mathbf{" + ml + "}\\,\\text{mL}\\)"
			);
		}
		else if (tipo == 1)
		{
			// kL → L: × 1000
			int kl = 1 + rand.nextInt(9);
			int l  = kl * 1000;

			addParagrafo("Converta para litros \\((\\text{L})\\):");
			addParagrafo("\\(" + kl + "\\,\\text{kL}\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + l + "\\,\\text{L}\\)",
				Arrays.asList(
					"\\(" + (kl * 100) + "\\,\\text{L}\\)",
					"\\(" + (kl * 10) + "\\,\\text{L}\\)",
					"\\(" + (l * 10) + "\\,\\text{L}\\)"
				)
			);
			setResolucao(
				"1 kL = 1000 L → multiplicar por 1000:" +
				"\\(\\\\\\)" +
				"\\(" + kl + " \\times 1000 = \\mathbf{" + l + "}\\,\\text{L}\\)"
			);
		}
		else if (tipo == 2)
		{
			// mL → L: ÷ 1000 (múltiplos de 500)
			int intPart = 1 + rand.nextInt(9);
			boolean meio = rand.nextBoolean();
			int ml = intPart * 1000 + (meio ? 500 : 0);
			String lStr = intPart + (meio ? "{,}5" : "");

			addParagrafo("Converta para litros \\((\\text{L})\\):");
			addParagrafo("\\(" + ml + "\\,\\text{mL}\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + lStr + "\\,\\text{L}\\)",
				Arrays.asList(
					"\\(" + (intPart * 10) + "\\,\\text{L}\\)",
					"\\(" + intPart + (meio ? "{,}05" : "{,}5") + "\\,\\text{L}\\)",
					"\\(0{,}" + intPart + "\\,\\text{L}\\)"
				)
			);
			setResolucao(
				"1 L = 1000 mL → dividir por 1000:" +
				"\\(\\\\\\)" +
				"\\(" + ml + " \\div 1000 = \\mathbf{" + lStr + "}\\,\\text{L}\\)"
			);
		}
		else
		{
			// L → kL: ÷ 1000
			int kl = 1 + rand.nextInt(9);
			int l  = kl * 1000;

			addParagrafo("Converta para quilolitros \\((\\text{kL})\\):");
			addParagrafo("\\(" + l + "\\,\\text{L}\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + kl + "\\,\\text{kL}\\)",
				Arrays.asList(
					"\\(" + (kl * 10) + "\\,\\text{kL}\\)",
					"\\(0{,}" + kl + "\\,\\text{kL}\\)",
					"\\(" + kl + "00\\,\\text{kL}\\)"
				)
			);
			setResolucao(
				"1 kL = 1000 L → dividir por 1000:" +
				"\\(\\\\\\)" +
				"\\(" + l + " \\div 1000 = \\mathbf{" + kl + "}\\,\\text{kL}\\)"
			);
		}
	}
}
