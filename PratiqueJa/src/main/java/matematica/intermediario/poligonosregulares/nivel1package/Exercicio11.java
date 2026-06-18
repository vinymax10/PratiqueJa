package matematica.intermediario.poligonosregulares.nivel1package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;
import matematica.intermediario.poligonosregulares.ResolucaoPoligonoRegular;
import matematica.intermediario.poligonosregulares.config.ConfigPoligonoRegular;

// Hexágono regular: dado l = 2m, encontrar x em a = x√3 (resposta: m)
public class Exercicio11 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int m = 1 + rand.nextInt(8); // m = 1..8
		int l = 2 * m;               // l = 2m → a = m√3

		String res = "\\(" + ResolucaoPoligonoRegular.formulaApotemaHexagono() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(a = \\dfrac{" + l + "\\sqrt{3}}{2} = \\mathbf{" + m + "}\\sqrt{3}\\)";

		ConfigPoligonoRegular config = new ConfigPoligonoRegular(6, true, "" + l, "a");
		BufferedImage image = config.criarImagem();

		addParagrafo("Um hexágono regular tem lado \\(" + l + "\\). Sabendo que o apótema é \\(x\\sqrt{3}\\), qual é o valor de \\(x\\)?");
		addParagrafoImagem(image);
		gerarAlternativas("" + m);
		setResolucao(res);
	}
}
