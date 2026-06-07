package matematica.intermediario.notacaocientifica.nivel2package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Conversão de unidades usando NC: metros ↔ mm, μm ou km
public class NotacaoCientifica7 extends GeradorExercicio
{
	// {descrição da medida, expoente do comprimento, unidade destino, expoente do fator}
	private static final Object[][] CONTEXTOS = {
		{"O comprimento de uma bactéria é", -6, "μm (1 m = 10^6 μm)", 6},
		{"O diâmetro de um fio de cabelo é", -5, "mm (1 m = 10^3 mm)", 3},
		{"A largura de uma célula é",        -5, "μm (1 m = 10^6 μm)", 6},
		{"O raio de um átomo é",             -10, "nm (1 m = 10^9 nm)", 9},
		{"O comprimento de um vírus é",      -8,  "nm (1 m = 10^9 nm)", 9},
		{"A altitude de um satélite é",       5,  "km (1 m = 10^{-3} km)", -3}
	};

	@Override
	protected void construir()
	{
		Object[] ctx = CONTEXTOS[rand.nextInt(CONTEXTOS.length)];
		String desc    = (String) ctx[0];
		int mBase      = (int) ctx[1];
		String unidade = (String) ctx[2];
		int fator      = (int) ctx[3];

		int a = 1 + rand.nextInt(9);
		int expResult = mBase + fator;

		String correta = "\\(" + a + " \\times 10^{" + expResult + "}\\)";
		String e1     = "\\(" + a + " \\times 10^{" + (expResult + 1) + "}\\)";
		String e2     = "\\(" + a + " \\times 10^{" + (expResult - 1) + "}\\)";
		// erro: usou o fator inverso (dividiu em vez de multiplicar)
		String e3     = "\\(" + a + " \\times 10^{" + (mBase - fator) + "}\\)";

		addParagrafo(desc + " \\(" + a + " \\times 10^{" + mBase + "}\\) m.");
		addParagrafo("Expresse em " + unidade + ".");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		setResolucao(
			"Multiplicar pelo fator de conversão \\(10^{" + fator + "}\\):" +
			"\\(\\\\\\)" +
			"\\(" + a + " \\times 10^{" + mBase + "} \\times 10^{" + fator + "}\\)" +
			"\\(\\\\\\)" +
			"\\(= " + a + " \\times 10^{" + mBase + " + (" + fator + ")} = \\mathbf{" + a + " \\times 10^{" + expResult + "}}\\)"
		);
	}
}
