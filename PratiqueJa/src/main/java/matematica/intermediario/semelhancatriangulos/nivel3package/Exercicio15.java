package matematica.intermediario.semelhancatriangulos.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.semelhancatriangulos.ConfigValores1;
import matematica.intermediario.semelhancatriangulos.nivel1package.ConfigSemelhancaTriangulos1;

public class Exercicio15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int pos = rand.nextInt(4);
		ConfigValores1 configValores = new ConfigValores1(pos, true);

		ConfigSemelhancaTriangulos1 config = new ConfigSemelhancaTriangulos1(configValores);

		String resultadoCorreto = configValores.resultado.toString() + "";
		String resolucao = configValores.resolucaoLatex;

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
