package matematica.intermediario.semelhancatriangulos.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.semelhancatriangulos.ConfigValores2;

public class Exercicio16 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int pos = rand.nextInt(4);
		ConfigValores2 configValores = new ConfigValores2(pos, false);
		ConfigSemelhancaTriangulos4 config = new ConfigSemelhancaTriangulos4(configValores);

		String resultadoCorreto = configValores.incognita.toString();
		String resolucao = configValores.resolucaoLatex;

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
