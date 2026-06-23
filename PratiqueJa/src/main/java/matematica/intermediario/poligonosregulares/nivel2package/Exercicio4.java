package matematica.intermediario.poligonosregulares.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.poligonosregulares.ResolucaoPoligonoRegular;
import matematica.intermediario.poligonosregulares.config.ConfigPoligonoRegular;

// Hexágono: dado a = m√3, encontrar l = 2m
public class Exercicio4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int m = 1 + rand.nextInt(8); // m = 1..8
		int l = 2 * m;               // l = 2m

		ConfigPoligonoRegular config = new ConfigPoligonoRegular(6, true, "l", "" + m + "\\sqrt{3}");
		BufferedImage image = config.criarImagem();

		addParagrafo("O apótema de um hexágono regular mede \\(" + m + "\\sqrt{3}\\). Qual é o comprimento do lado?");
		addParagrafoImagem(image);
		gerarAlternativas("" + l);
		addResolucao("\\(" + ResolucaoPoligonoRegular.formulaApotemaHexagono() + "\\)");
		addResolucao("\\(" + m + "\\sqrt{3} = \\dfrac{l\\sqrt{3}}{2}\\)");
		addResolucao("\\(l = 2 \\times " + m + " = \\mathbf{" + l + "}\\)");
	}
}
