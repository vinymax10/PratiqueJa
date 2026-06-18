package matematica.intermediario.circulo.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.config.ConfigCircunferencia;
import matematica.intermediario.circulo.ResolucaoCirculo;

// Dado A = kπ (k = r²), encontrar r = √k
public class Exercicio7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r = 2 + rand.nextInt(7); // r = 2..8
		int k = r * r;               // A = kπ

		String res = "\\(" + ResolucaoCirculo.formulaArea() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(" + k + "\\pi = \\pi \\cdot r^2 \\\\ ";
		res += "r^2 = " + k + " \\\\ ";
		res += "r = \\sqrt{" + k + "} = \\mathbf{" + r + "}\\)";

		ConfigCircunferencia config = new ConfigCircunferencia("r", true);
		BufferedImage image = config.criarImagem();

		addParagrafo("A área de um círculo é \\(" + k + "\\pi\\). Qual é o raio?");
		addParagrafoImagem(image);
		gerarAlternativas("" + r);
		setResolucao(res);
	}
}
