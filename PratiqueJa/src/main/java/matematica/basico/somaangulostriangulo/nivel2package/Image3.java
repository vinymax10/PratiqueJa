package matematica.basico.somaangulostriangulo.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.somaangulostriangulo.ResolucaoSAT2;

public class Image3 extends GeradorExercicio
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
		config.a.mostrar();
		config.b.mostrar();
		config.c.mostrar();
		config.c.nome = "x";

		String texto = config.getTextLatex();
		String resultadoCorreto = "" + c + "°";
		String resolucao = resolucao(a, b, c, d, e);

		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafo("\\(" + texto + "\\)");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}

	private String resolucao(int a, int b, int c, int d, int e)
	{
		String resolucaoLatex = ResolucaoSAT2.complemento180("y", b, a);
		resolucaoLatex += ResolucaoSAT2.complemento90("z", d);
		resolucaoLatex += ResolucaoSAT2.complemento180("x", e, 90);

		return resolucaoLatex;
	}
}
