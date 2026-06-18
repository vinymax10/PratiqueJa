package matematica.basico.somaangulostriangulo.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.somaangulostriangulo.ResolucaoSAT2;

public class Image6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 60 + rand.nextInt(20);
		int e = 40 + rand.nextInt(20);
		int d = 90 - e;
		int c = d;
		int a = 180 - b - d;

		ConfigTrapezio config = new ConfigTrapezio(a, b, c, d, e);
		config.c.mostrar();
		config.d.mostrar();
		config.d.nome = "x";

		String resultadoCorreto = "" + e + "°";
		String resolucao = resolucao(a, b, c, d, e);

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}

	private String resolucao(int a, int b, int c, int d, int e)
	{
		String resolucao = ResolucaoSAT2.complemento180("y", c, 90);
		resolucao += ResolucaoSAT2.complemento90("x", d);

		return resolucao;
	}
}
