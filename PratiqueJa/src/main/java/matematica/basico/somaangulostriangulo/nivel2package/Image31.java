package matematica.basico.somaangulostriangulo.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.somaangulostriangulo.ResolucaoSAT2;

public class Image31 extends GeradorExercicio
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
		config.a.mostrar();
		config.c.mostrar();
		config.d.mostrar();
		config.f.mostrar();
		config.f.nome = "x";

		String resultadoCorreto = "" + f + "°";
		String[] passos = resolucao(a, b, c, d, e, f, g);

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		for(String passo : passos)
			addResolucao("\\(" + passo + "\\)");
	}

	private String[] resolucao(int a, int b, int c, int d, int e, int f, int g)
	{
		java.util.List<String> passosLista = new java.util.ArrayList<>();
		java.util.Collections.addAll(passosLista, ResolucaoSAT2.complemento180("y", d, c));
		java.util.Collections.addAll(passosLista, ResolucaoSAT2.complemento90("x", a, e));

		return passosLista.toArray(new String[0]);
	}
}
