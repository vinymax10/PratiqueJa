package matematica.basico.somaangulostriangulo.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.somaangulostriangulo.ResolucaoSAT2;

public class Image36 extends GeradorExercicio
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
		config.b.mostrar();
		config.d.mostrar();
		config.a.mostrar();
		config.b.nome = "x";

		String resultadoCorreto = "" + b + "°";
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
		java.util.Collections.addAll(passosLista, ResolucaoSAT2.complemento180("y", d));
		java.util.Collections.addAll(passosLista, ResolucaoSAT2.complemento180("x", a, c));

		return passosLista.toArray(new String[0]);
	}
}
