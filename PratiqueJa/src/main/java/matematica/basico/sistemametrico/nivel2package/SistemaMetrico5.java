package matematica.basico.sistemametrico.nivel2package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Massa com contexto: mercado, culinária, logística
public class SistemaMetrico5 extends GeradorExercicio
{
	private static final String[] CTX_G_PARA_KG = {
		"Uma embalagem de arroz tem %d g. Quantos quilogramas representa?",
		"Um pacote de farinha contém %d g. Quantos kg é isso?",
		"Um atleta carrega uma mochila de %d g. Quantos kg pesa?"
	};

	private static final String[] CTX_KG_PARA_G = {
		"Uma receita de bolo pede %d kg de açúcar. Quantos gramas são?",
		"Um saco de cimento pesa %d kg. Quantos gramas equivalem?",
		"Uma criança pesa %d kg. Quantos gramas é isso?"
	};

	@Override
	protected void construir()
	{
		boolean gParaKg = rand.nextBoolean();

		if (gParaKg)
		{
			int intPart = 1 + rand.nextInt(9);
			boolean meio = rand.nextBoolean();
			int g = intPart * 1000 + (meio ? 500 : 0);
			String kgStr = intPart + (meio ? "{,}5" : "");

			String ctx = CTX_G_PARA_KG[rand.nextInt(CTX_G_PARA_KG.length)];
			addParagrafo(String.format(ctx, g));
			embaralharEAdicionarAlternativas(
				"\\(" + kgStr + "\\,\\text{kg}\\)",
				Arrays.asList(
					"\\(" + (intPart * 10) + "\\,\\text{kg}\\)",
					"\\(0{,}0" + intPart + "\\,\\text{kg}\\)",
					"\\(" + intPart + (meio ? "{,}05" : "{,}5") + "\\,\\text{kg}\\)"
				)
			);
			addResolucao("\\(1\\,\\text{kg} = 1000\\,\\text{g}\\), logo dividir por 1000:");
			addResolucao("\\(" + g + " \\div 1000 = \\mathbf{" + kgStr + "}\\,\\text{kg}\\)");
		}
		else
		{
			int kg = 1 + rand.nextInt(9);
			int g  = kg * 1000;

			String ctx = CTX_KG_PARA_G[rand.nextInt(CTX_KG_PARA_G.length)];
			addParagrafo(String.format(ctx, kg));
			embaralharEAdicionarAlternativas(
				"\\(" + g + "\\,\\text{g}\\)",
				Arrays.asList(
					"\\(" + (kg * 100) + "\\,\\text{g}\\)",
					"\\(" + (kg * 10) + "\\,\\text{g}\\)",
					"\\(" + (g * 10) + "\\,\\text{g}\\)"
				)
			);
			addResolucao("\\(1\\,\\text{kg} = 1000\\,\\text{g}\\), logo multiplicar por 1000:");
			addResolucao("\\(" + kg + " \\times 1000 = \\mathbf{" + g + "}\\,\\text{g}\\)");
		}
	}
}
