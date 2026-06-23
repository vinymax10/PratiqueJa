package matematica.basico.somaangulostriangulo.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.somaangulostriangulo.ResolucaoSAT2;
import matematica.basico.somaangulostriangulo.nivel2package.ConfigTrianguloBipartido2;

public class Image19 extends GeradorExercicio
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
		config.f.mostrar();
		config.e.mostrar();
		config.b.mostrar();
		config.a.mostrar();
		config.a.nome = "x";

		String resultadoCorreto = "" + a + "°";
		String[] passos = ResolucaoSAT2.complemento180("x", b, e, f);

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		for(String passo : passos)
			addResolucao("\\(" + passo + "\\)");
	}
}
