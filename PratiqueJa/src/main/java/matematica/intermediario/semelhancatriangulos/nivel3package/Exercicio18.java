package matematica.intermediario.semelhancatriangulos.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.semelhancatriangulos.ConfigValores2;
import matematica.intermediario.semelhancatriangulos.nivel2package.ConfigSemelhancaTriangulos4;

public class Exercicio18 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int pos = rand.nextInt(4);
		ConfigValores2 configValores = new ConfigValores2(pos, true);

		ConfigSemelhancaTriangulos4 config = new ConfigSemelhancaTriangulos4(configValores);

		String resultadoCorreto = configValores.resultado.toString() + "";
		String resolucao = configValores.resolucaoLatex;

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
