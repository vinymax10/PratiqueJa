package matematica.basico.somaangulostriangulo.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.somaangulostriangulo.ResolucaoSAT2;

public class Image1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 50 + rand.nextInt(30);

		int b = 90 - a;

		ConfigTrianguloRetangulo config = new ConfigTrianguloRetangulo(a, b);
		config.a.mostrar();
		config.b.mostrar();
		config.b.nome = "x";

		String resultadoCorreto = "" + b + "°";
		String resolucao = ResolucaoSAT2.complemento180("x", a, 90);

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
