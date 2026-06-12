package matematica.intermediario.poligonosregulares.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.poligonosregulares.ResolucaoPoligonoRegular;
import matematica.intermediario.poligonosregulares.config.ConfigPoligonoRegular;

// Hexágono regular: dado l = 2m, encontrar x em A = x√3 (resposta: 6m²)
public class Exercicio5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int m = 1 + rand.nextInt(5); // m = 1..5 → x = 6m² (manageable values)
		int l = 2 * m;
		int x = 6 * m * m;          // A = (3√3/2)·l² = (3√3/2)·4m² = 6m²√3

		String res = "\\(" + ResolucaoPoligonoRegular.formulaAreaHexagono() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(A = \\dfrac{3\\sqrt{3}}{2} \\cdot " + l + "^2 = \\dfrac{3\\sqrt{3}}{2} \\cdot " + (l * l) + " = \\mathbf{" + x + "}\\sqrt{3}\\)";

		ConfigPoligonoRegular config = new ConfigPoligonoRegular(6, true, "" + l, "a");
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Um hexágono regular tem lado \\(" + l + "\\). Sabendo que sua área é \\(x\\sqrt{3}\\), qual é o valor de \\(x\\)?");
		addParagrafoImagem(image);
		gerarAlternativas("" + x);
		setResolucao(res);
	}
}
