package matematica.intermediario.semelhancatriangulos.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.semelhancatriangulos.ConfigValores1;
import matematica.intermediario.semelhancatriangulos.nivel1package.ConfigSemelhancaTriangulos1;

public class Exercicio1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int pos = rand.nextInt(4);
		ConfigValores1 configValores = new ConfigValores1(pos, true);

		ConfigSemelhancaTriangulos1 config = new ConfigSemelhancaTriangulos1(configValores);

		String resultadoCorreto = configValores.resultado.toString() + "";
		String resolucao = configValores.resolucaoLatex;
		String texto = config.getTextLatex();

		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafo("\\(" + texto + "\\)");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
