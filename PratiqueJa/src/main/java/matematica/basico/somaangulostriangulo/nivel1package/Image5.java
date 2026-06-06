package matematica.basico.somaangulostriangulo.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.somaangulostriangulo.ResolucaoSAT2;

public class Image5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 50 + rand.nextInt(20);
		int c = 20 + rand.nextInt(20);
		int a = 180 - b - c;

		ConfigTriangulo1 config = new ConfigTriangulo1(a, b, c);
		config.a.mostrar();
		config.b.mostrar();
		config.c.mostrar();
		config.c.nome = "x";

		String texto = config.getTextLatex();
		String resultadoCorreto = "" + c + "°";
		String resolucao = ResolucaoSAT2.complemento180("x", a, b);

		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafo("\\(" + texto + "\\)");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
