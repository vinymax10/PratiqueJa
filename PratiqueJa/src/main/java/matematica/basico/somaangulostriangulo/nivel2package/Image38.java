package matematica.basico.somaangulostriangulo.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.somaangulostriangulo.ResolucaoSAT2;

public class Image38 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 40 + rand.nextInt(10);
		int b = 50 + rand.nextInt(10);
		int e = 40 + rand.nextInt(10);
		int c = 180 - a - b;
		int d = 180 - c;
		int f = 180 - b - e - a;

		ConfigTrianguloBipartido2 config = new ConfigTrianguloBipartido2(a, b, c, d, e, f);
		config.c.mostrar();
		config.e.mostrar();
		config.f.mostrar();
		config.f.nome = "x";

		String resultadoCorreto = "" + f + "°";
		String resolucao = resolucao(a, b, c, d, e, f);

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}

	private String resolucao(int a, int b, int c, int d, int e, int f)
	{
		String resolucaoLatex = ResolucaoSAT2.complemento180("y", c);
		resolucaoLatex += ResolucaoSAT2.complemento180("x", e, d);

		return resolucaoLatex;
	}
}
