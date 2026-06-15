package matematica.basico.somaangulostriangulo.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.somaangulostriangulo.ResolucaoSAT2;

public class Image9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 50 + rand.nextInt(20);
		int c = 20 + rand.nextInt(20);
		int b = 180 - a - c;

		ConfigTriangulo3 config = new ConfigTriangulo3(a, b, c);
		config.a.mostrar();
		config.b.mostrar();
		config.c.mostrar();
		config.a.nome = "x";

		String resultadoCorreto = "" + a + "°";
		String resolucao = ResolucaoSAT2.complemento180("x", b, c);

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
