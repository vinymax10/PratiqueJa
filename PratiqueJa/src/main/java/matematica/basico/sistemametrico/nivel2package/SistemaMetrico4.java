package matematica.basico.sistemametrico.nivel2package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Capacidade com contexto: receitas, embalagens, reservatórios
public class SistemaMetrico4 extends GeradorExercicio
{
	private static final String[] CTX_ML_PARA_L = {
		"Uma lata de refrigerante contém %d mL. Quantos litros é isso?",
		"Uma embalagem de suco tem %d mL. Quantos litros representa?",
		"Um frasco de remédio tem %d mL. Quantos litros equivalem?"
	};

	private static final String[] CTX_L_PARA_ML = {
		"Uma receita pede %d L de leite. Quantos mililitros são necessários?",
		"Um galão de água tem %d L. Quantos mililitros há nele?",
		"Uma piscina infantil leva %d L. Quantos mL são?"
	};

	@Override
	protected void construir()
	{
		boolean mlParaL = rand.nextBoolean();

		if (mlParaL)
		{
			// mL → L: múltiplos de 500
			int intPart = 1 + rand.nextInt(5);
			boolean meio = rand.nextBoolean();
			int ml = intPart * 1000 + (meio ? 500 : 0);
			String lStr = intPart + (meio ? "{,}5" : "");

			String ctx = CTX_ML_PARA_L[rand.nextInt(CTX_ML_PARA_L.length)];
			addParagrafo(String.format(ctx, ml));
			embaralharEAdicionarAlternativas(
				"\\(" + lStr + "\\,\\text{L}\\)",
				Arrays.asList(
					"\\(" + (intPart * 10) + "\\,\\text{L}\\)",
					"\\(" + intPart + (meio ? "{,}05" : "{,}5") + "\\,\\text{L}\\)",
					"\\(" + (ml / 100) + "\\,\\text{L}\\)"
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
			// L → mL
			int l  = 1 + rand.nextInt(10);
			int ml = l * 1000;

			String ctx = CTX_L_PARA_ML[rand.nextInt(CTX_L_PARA_ML.length)];
			addParagrafo(String.format(ctx, l));
			embaralharEAdicionarAlternativas(
				"\\(" + ml + "\\,\\text{mL}\\)",
				Arrays.asList(
					"\\(" + (l * 100) + "\\,\\text{mL}\\)",
					"\\(" + (l * 10) + "\\,\\text{mL}\\)",
					"\\(" + (ml + 1000) + "\\,\\text{mL}\\)"
				)
			);
			setResolucao(
				"1 L = 1000 mL → multiplicar por 1000:" +
				"\\(\\\\\\)" +
				"\\(" + l + " \\times 1000 = \\mathbf{" + ml + "}\\,\\text{mL}\\)"
			);
		}
	}
}
