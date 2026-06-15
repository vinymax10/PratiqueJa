package matematica.intermediario.poligonosregulares.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.poligonosregulares.ResolucaoPoligonoRegular;
import matematica.intermediario.poligonosregulares.config.ConfigPoligonoRegular;

// Hexágono: dado P = 12m, calcular A = 6m²√3 → resposta x = 6m²
public class Exercicio3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int m = 1 + rand.nextInt(6); // m = 1..6
		int l = 2 * m;
		int p = 12 * m;              // P = 6l = 12m
		int x = 6 * m * m;           // A = 6m²√3

		String res = "Passo 1: encontrar \\(l\\) e o apótema \\(a\\)";
		res += "\\(\\\\\\)";
		res += "\\(l = \\dfrac{P}{6} = \\dfrac{" + p + "}{6} = " + l + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(" + ResolucaoPoligonoRegular.formulaApotemaHexagono() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(a = \\dfrac{" + l + "\\sqrt{3}}{2} = " + m + "\\sqrt{3}\\)";
		res += "\\(\\\\\\)";
		res += "Passo 2: calcular \\(A\\)";
		res += "\\(\\\\\\)";
		res += "\\(" + ResolucaoPoligonoRegular.formulaArea() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(A = \\dfrac{" + p + " \\times " + m + "\\sqrt{3}}{2} = \\mathbf{" + x + "}\\sqrt{3}\\)";

		ConfigPoligonoRegular config = new ConfigPoligonoRegular(6, true, "l", "a");
		BufferedImage image = config.criarImagem();

		addParagrafo("O perímetro de um hexágono regular é \\(" + p + "\\). Sabendo que a área é \\(x\\sqrt{3}\\), qual é o valor de \\(x\\)?");
		addParagrafoImagem(image);
		gerarAlternativas("" + x);
		setResolucao(res);
	}
}
