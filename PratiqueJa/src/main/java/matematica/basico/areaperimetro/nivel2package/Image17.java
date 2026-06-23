package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.ConfigImagem;
import matematica.basico.areaperimetro.config.ConfigTrianguloIsosceles;

public class Image17 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 * (5 + rand.nextInt(13));
		int a = (int) (((double) b) * 0.7);// altura
		int c = a;

		int perimetro = (b + a + c);

		// NOTA: bug do original — pergunta pede "a" mas resultadoCorreto = b (preservado fielmente)
		String resultadoCorreto = "" + b;

		ConfigImagem config = new ConfigTrianguloIsosceles("" + b, "", "", "a", false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se o perímetro do triângulo é \\(" + perimetro + "\\), qual o valor de \\(a\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaPerimetroTrianguloIsosceles() + "\\)");
		addResolucao("\\(b=" + b + "\\)");
		addResolucao("\\(2 \\cdot a +" + b + "=" + perimetro + "\\)");
		addResolucao("\\(2 \\cdot a=" + perimetro + "-" + b + "=" + (perimetro - b) + "\\)");
		addResolucao("\\(a=\\dfrac{" + (perimetro - b) + "}{2}=" + a + "\\)");
	}
}
