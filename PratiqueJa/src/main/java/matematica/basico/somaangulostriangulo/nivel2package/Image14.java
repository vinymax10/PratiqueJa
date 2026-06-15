package matematica.basico.somaangulostriangulo.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.somaangulostriangulo.ResolucaoSAT2;

public class Image14 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 30 + rand.nextInt(10);
		int d = 30 + rand.nextInt(10);
		int b = 90 - a;
		int c = 180 - b;
		int g = 90 - d;
		int e = b - d;
		int f = 90 - e - a;

		ConfigRetangulo config = new ConfigRetangulo(a, b, c, d, e, f, g);
		config.c.mostrar();
		config.e.mostrar();
		config.g.mostrar();
		config.c.nome = "x";

		String resultadoCorreto = "" + c + "°";
		String resolucao = resolucao(a, b, c, d, e, f, g);

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}

	private String resolucao(int a, int b, int c, int d, int e, int f, int g)
	{
		String resolucaoLatex = ResolucaoSAT2.complemento90("y", g);
		resolucaoLatex += ResolucaoSAT2.complemento180("x", d, e);

		return resolucaoLatex;
	}
}
