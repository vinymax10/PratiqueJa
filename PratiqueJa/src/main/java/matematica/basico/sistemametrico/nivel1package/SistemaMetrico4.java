package matematica.basico.sistemametrico.nivel1package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Massa: conversão de unidade menor para maior (g→kg, kg→t) com decimais
public class SistemaMetrico4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		boolean kgParaT = rand.nextBoolean();

		if (kgParaT)
		{
			// X000 kg → X t  ou  X500 kg → X,5 t
			int intPart = 1 + rand.nextInt(9);
			boolean meio = rand.nextBoolean();
			int kg = intPart * 1000 + (meio ? 500 : 0);
			String tStr = intPart + (meio ? "{,}5" : "");

			addParagrafo("Converta para toneladas \\((\\text{t})\\):");
			addParagrafo("\\(" + kg + "\\,\\text{kg}\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + tStr + "\\,\\text{t}\\)",
				Arrays.asList(
					"\\(" + (intPart * 10) + "\\,\\text{t}\\)",
					"\\(" + intPart + (meio ? "{,}05" : "{,}5") + "\\,\\text{t}\\)",
					"\\(0{,}0" + intPart + "\\,\\text{t}\\)"
				)
			);
			setResolucao(
				"1 t = 1000 kg → dividir por 1000:" +
				"\\(\\\\\\)" +
				"\\(" + kg + " \\div 1000 = \\mathbf{" + tStr + "}\\,\\text{t}\\)"
			);
		}
		else
		{
			// X000 g → X kg  ou  X500 g → X,5 kg
			int intPart = 1 + rand.nextInt(9);
			boolean meio = rand.nextBoolean();
			int g = intPart * 1000 + (meio ? 500 : 0);
			String kgStr = intPart + (meio ? "{,}5" : "");

			addParagrafo("Converta para quilogramas \\((\\text{kg})\\):");
			addParagrafo("\\(" + g + "\\,\\text{g}\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + kgStr + "\\,\\text{kg}\\)",
				Arrays.asList(
					"\\(" + (intPart * 10) + "\\,\\text{kg}\\)",
					"\\(0{,}" + intPart + "\\,\\text{kg}\\)",
					"\\(" + intPart + (meio ? "{,}05" : "{,}5") + "\\,\\text{kg}\\)"
				)
			);
			setResolucao(
				"1 kg = 1000 g → dividir por 1000:" +
				"\\(\\\\\\)" +
				"\\(" + g + " \\div 1000 = \\mathbf{" + kgStr + "}\\,\\text{kg}\\)"
			);
		}
	}
}
