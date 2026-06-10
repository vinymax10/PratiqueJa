package matematica.basico.sistemametrico.nivel1package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Comprimento: conversão de unidade menor para maior (cm→m, m→km, mm→cm) com decimais
public class SistemaMetrico2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = rand.nextInt(3);

		if (tipo == 0)
		{
			// X00 cm → X,Y m  (múltiplos de 50)
			int intPart = 1 + rand.nextInt(9);
			boolean meio = rand.nextBoolean();
			int cm = intPart * 100 + (meio ? 50 : 0);
			String mStr = intPart + (meio ? "{,}5" : "");

			addParagrafo("Converta para metros \\((\\text{m})\\):");
			addParagrafo("\\(" + cm + "\\,\\text{cm}\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + mStr + "\\,\\text{m}\\)",
				Arrays.asList(
					"\\(" + intPart + (meio ? "{,}05" : "{,}5") + "\\,\\text{m}\\)",
					"\\(" + (intPart * 10 + (meio ? 5 : 0)) + "\\,\\text{m}\\)",
					"\\(" + (cm * 100) + "\\,\\text{m}\\)"
				)
			);
			setResolucao(
				"\\(1\\,\\text{m} = 100\\,\\text{cm}\\), logo dividir por 100:" +
				"\\(\\\\\\)" +
				"\\(" + cm + " \\div 100 = \\mathbf{" + mStr + "}\\,\\text{m}\\)"
			);
		}
		else if (tipo == 1)
		{
			// X000 m → X km  (inteiro)
			int km = 1 + rand.nextInt(9);
			int m  = km * 1000;

			addParagrafo("Converta para quilômetros \\((\\text{km})\\):");
			addParagrafo("\\(" + m + "\\,\\text{m}\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + km + "\\,\\text{km}\\)",
				Arrays.asList(
					"\\(" + (km * 10) + "\\,\\text{km}\\)",
					"\\(" + km + "{,}0\\,\\text{km}\\)",   // ambiguous trap
					"\\(0{,}" + km + "\\,\\text{km}\\)"
				)
			);
			setResolucao(
				"\\(1\\,\\text{km} = 1000\\,\\text{m}\\), logo dividir por 1000:" +
				"\\(\\\\\\)" +
				"\\(" + m + " \\div 1000 = \\mathbf{" + km + "}\\,\\text{km}\\)"
			);
		}
		else
		{
			// X0 mm → X cm  (inteiro)
			int cm = 1 + rand.nextInt(30);
			int mm = cm * 10;

			addParagrafo("Converta para centímetros \\((\\text{cm})\\):");
			addParagrafo("\\(" + mm + "\\,\\text{mm}\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + cm + "\\,\\text{cm}\\)",
				Arrays.asList(
					"\\(" + (cm * 10) + "\\,\\text{cm}\\)",
					"\\(" + (cm * 100) + "\\,\\text{cm}\\)",
					"\\(0{,}" + cm + "\\,\\text{cm}\\)"
				)
			);
			setResolucao(
				"\\(1\\,\\text{cm} = 10\\,\\text{mm}\\), logo dividir por 10:" +
				"\\(\\\\\\)" +
				"\\(" + mm + " \\div 10 = \\mathbf{" + cm + "}\\,\\text{cm}\\)"
			);
		}
	}
}
