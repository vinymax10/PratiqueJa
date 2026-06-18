package matematica.intermediario.circulo.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.config.ConfigCircunferencia;
import matematica.intermediario.circulo.ResolucaoCirculo;

// Dado C = kπ, encontrar r = k/2
public class Exercicio11 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r = 2 + rand.nextInt(9); // r = 2..10
		int k = 2 * r;               // C = kπ (k sempre par)

		String res = "\\(" + ResolucaoCirculo.formulaComprimento() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(" + k + "\\pi = 2\\pi \\cdot r \\\\ ";
		res += "r = \\dfrac{" + k + "\\pi}{2\\pi} = \\mathbf{" + r + "}\\)";

		ConfigCircunferencia config = new ConfigCircunferencia("r", false);
		BufferedImage image = config.criarImagem();

		addParagrafo("O comprimento de uma circunferência é \\(" + k + "\\pi\\). Qual é o raio?");
		addParagrafoImagem(image);
		gerarAlternativas("" + r);
		setResolucao(res);
	}
}
