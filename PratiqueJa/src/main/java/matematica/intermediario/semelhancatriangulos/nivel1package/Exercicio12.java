package matematica.intermediario.semelhancatriangulos.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.semelhancatriangulos.ConfigValores1;

public class Exercicio12 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int pos = rand.nextInt(4);
		ConfigValores1 configValores = new ConfigValores1(pos, false);

		ConfigSemelhancaTriangulos2 config = new ConfigSemelhancaTriangulos2(configValores);

		String resultadoCorreto = configValores.incognita.toString();
		String resolucao = configValores.resolucaoLatex;

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
