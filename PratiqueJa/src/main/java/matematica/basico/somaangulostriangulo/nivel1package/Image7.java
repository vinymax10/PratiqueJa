package matematica.basico.somaangulostriangulo.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.somaangulostriangulo.ResolucaoSAT2;

public class Image7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 20 + rand.nextInt(10);
		int a = 20 + rand.nextInt(10);
		int c = 180 - b - a;

		ConfigTriangulo2 config = new ConfigTriangulo2(a, b, c);
		config.a.mostrar();
		config.b.mostrar();
		config.c.mostrar();
		config.b.nome = "x";

		String resultadoCorreto = "" + b + "°";
		String[] passos = ResolucaoSAT2.complemento180("x", a, c);

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		for(String passo : passos)
			addResolucao("\\(" + passo + "\\)");
	}
}
