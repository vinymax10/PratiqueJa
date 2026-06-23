package matematica.basico.somaangulostriangulo.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.somaangulostriangulo.ResolucaoSAT2;

public class Image8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 55 + rand.nextInt(15);
		int e = 10 + rand.nextInt(15);
		int f = 25 + rand.nextInt(15);
		int c = e + f;
		int a = 180 - b - c;
		int d = 180 - c;

		ConfigTrianguloBipartido1 config = new ConfigTrianguloBipartido1(a, b, c, d, e, f);
		config.a.mostrar();
		config.b.mostrar();
		config.d.mostrar();
		config.d.nome = "x";

		String resultadoCorreto = "" + d + "°";
		String[] passos = resolucao(a, b, c, d, e, f);

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		for(String passo : passos)
			addResolucao("\\(" + passo + "\\)");
	}

	private String[] resolucao(int a, int b, int c, int d, int e, int f)
	{
		java.util.List<String> passosLista = new java.util.ArrayList<>();
		java.util.Collections.addAll(passosLista, ResolucaoSAT2.complemento180("y", a, b));
		java.util.Collections.addAll(passosLista, ResolucaoSAT2.complemento180("x", c));

		return passosLista.toArray(new String[0]);
	}
}
