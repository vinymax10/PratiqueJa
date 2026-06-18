package matematica.avancado.logaritmo.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = rand.nextInt(3);
		switch (tipo)
		{
			case 0: richter(); break;
			case 1: pH();     break;
			default: decibeis(); break;
		}
	}

	private void richter()
	{
		// Escala Richter: M = log(A/A0). Diferença de k magnitudes → 10^k vezes mais intenso.
		int k = 1 + rand.nextInt(3); // 1, 2 ou 3
		int resultado = (int) Math.pow(10, k);
		int m1 = 4 + rand.nextInt(4); // M1: 4..7
		int m2 = m1 - k;              // M2 = M1 - k

		addParagrafo("Um sismo de magnitude \\(" + m1 + "\\) é quantas vezes mais intenso "
			+ "que um de magnitude \\(" + m2 + "\\)? Use \\(M = \\log\\!\\left(\\dfrac{A}{A_0}\\right)\\).");

		String res = "Sendo \\(M_1 = " + m1 + "\\) e \\(M_2 = " + m2 + "\\): \\(\\\\\\)";
		res += "\\(M_1 - M_2 = \\log A_1 - \\log A_2 = \\log\\!\\left(\\dfrac{A_1}{A_2}\\right) = " + k + "\\\\";
		res += "\\dfrac{A_1}{A_2} = 10^{" + k + "} = \\mathbf{" + resultado + "}\\) vezes mais intenso";

		gerarAlternativas("" + resultado);
		setResolucao(res);
	}

	private void pH()
	{
		// pH = -log[H+]. Dado [H+] = 10^(-n), pH = n.
		int n = 4 + rand.nextInt(7); // pH entre 4 e 10
		// Mostrar [H+] como 10^(-n)
		addParagrafo("Calcule o pH de uma solução com concentração "
			+ "\\([\\text{H}^+] = 10^{-" + n + "}\\,\\text{mol/L}\\). "
			+ "Use \\(\\text{pH} = -\\log[\\text{H}^+]\\).");

		String res = "Substituindo na fórmula: \\(\\\\\\)";
		res += "\\(\\text{pH} = -\\log(10^{-" + n + "})\\\\";
		res += "= -(-" + n + ") \\cdot \\log 10 = " + n + " \\cdot 1\\\\";
		res += "\\text{pH} = \\mathbf{" + n + "}\\)";

		gerarAlternativas("" + n);
		setResolucao(res);
	}

	private void decibeis()
	{
		// dB = 10·log(I/I0). Dado I/I0 = 10^n, dB = 10n.
		int n  = 1 + rand.nextInt(6); // 1..6
		int db = 10 * n;

		List<String> dist = new ArrayList<>();
		dist.add("" + n);
		dist.add("" + (db + 10));
		dist.add("" + (db - 10 > 0 ? db - 10 : db + 5));

		addParagrafo("Uma fonte sonora tem intensidade \\(10^{" + n + "}\\) vezes "
			+ "maior que o limiar de audição. "
			+ "Qual é o nível sonoro em dB? Use \\(\\text{dB} = 10 \\cdot \\log\\!\\left(\\dfrac{I}{I_0}\\right)\\).");

		String res = "Substituindo \\(I/I_0 = 10^{" + n + "}\\): \\(\\\\\\)";
		res += "\\(\\text{dB} = 10 \\cdot \\log(10^{" + n + "}) = 10 \\cdot " + n + " \\cdot \\log 10 = 10 \\cdot " + n + "\\\\";
		res += "\\text{dB} = \\mathbf{" + db + "}\\,\\text{dB}\\)";

		embaralharEAdicionarAlternativas("" + db, dist);
		setResolucao(res);
	}
}
