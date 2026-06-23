package matematica.intermediario.semelhancatriangulos.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.semelhancatriangulos.ConfigValores1;
import matematica.intermediario.semelhancatriangulos.nivel1package.ConfigSemelhancaTriangulos3;

public class Exercicio3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int pos = rand.nextInt(4);
		ConfigValores1 configValores = new ConfigValores1(pos, true);

		ConfigSemelhancaTriangulos3 config = new ConfigSemelhancaTriangulos3(configValores);

		String resultadoCorreto = configValores.resultado.toString() + "";

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		for(String passo : configValores.resolucaoPassos)
			addResolucao("\\(" + passo + "\\)");
	}
}
