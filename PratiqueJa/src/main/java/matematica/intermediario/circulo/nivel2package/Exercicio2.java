package matematica.intermediario.circulo.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.config.ConfigCircunferencia;
import matematica.intermediario.circulo.ResolucaoCirculo;

// Dado A = kπ (k = r²), encontrar r = √k
public class Exercicio2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r = 2 + rand.nextInt(7); // r = 2..8
		int k = r * r;               // A = kπ

		ConfigCircunferencia config = new ConfigCircunferencia("r", true);
		BufferedImage image = config.criarImagem();

		addParagrafo("A área de um círculo é \\(" + k + "\\pi\\). Qual é o raio?");
		addParagrafoImagem(image);
		gerarAlternativas("" + r);
		addResolucao("\\(" + ResolucaoCirculo.formulaArea() + "\\)");
		addResolucao("\\(" + k + "\\pi = \\pi \\cdot r^2\\)");
		addResolucao("\\(r^2 = " + k + "\\)");
		addResolucao("\\(r = \\sqrt{" + k + "} = \\mathbf{" + r + "}\\)");
	}
}
