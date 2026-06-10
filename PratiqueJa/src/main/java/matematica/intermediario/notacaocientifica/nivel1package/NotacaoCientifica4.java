package matematica.intermediario.notacaocientifica.nivel1package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Identificar qual opção está corretamente em notação científica (1 ≤ a < 10)
public class NotacaoCientifica4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int c = 1 + rand.nextInt(9); // mantissa válida
		int k = 3 + rand.nextInt(5); // expoente

		// mantissa inválida grande: 10..30
		int cGrande = 10 + rand.nextInt(21);
		// mantissa inválida pequena: 0,d
		int cPeq = 1 + rand.nextInt(9);
		// mesmo valor em forma não reduzida: c*10 × 10^{k-1}
		int cNaoAjustado = c * 10;

		String correta = "\\(" + c + " \\times 10^{" + k + "}\\)";
		String e1     = "\\(" + cGrande + " \\times 10^{" + (k - 1) + "}\\)";
		String e2     = "\\(0{,}" + cPeq + " \\times 10^{" + (k + 1) + "}\\)";
		String e3     = "\\(" + cNaoAjustado + " \\times 10^{" + (k - 1) + "}\\)";

		addParagrafo("Qual das opções está escrita corretamente em notação científica \\((a \\times 10^n,\\; 1 \\leq a < 10)\\)?");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		setResolucao(
			"Verificar a condição \\(1 \\leq a < 10\\) em cada opção:" +
			"\\(\\\\\\)" +
			"\\(" + cGrande + " \\times 10^{" + (k - 1) + "}\\): mantissa \\(" + cGrande + " \\geq 10\\), inválido" +
			"\\(\\\\\\)" +
			"\\(0{,}" + cPeq + " \\times 10^{" + (k + 1) + "}\\): mantissa \\(< 1\\), inválido" +
			"\\(\\\\\\)" +
			"\\(" + cNaoAjustado + " \\times 10^{" + (k - 1) + "}\\): mantissa \\(" + cNaoAjustado + " \\geq 10\\), inválido" +
			"\\(\\\\\\)" +
			"\\(\\mathbf{" + c + " \\times 10^{" + k + "}}\\): mantissa \\(1 \\leq " + c + " < 10\\), correta"
		);
	}
}
