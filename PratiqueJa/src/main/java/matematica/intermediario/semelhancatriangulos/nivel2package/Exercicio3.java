package matematica.intermediario.semelhancatriangulos.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.semelhancatriangulos.ConfigValores2;

public class Exercicio3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int pos = rand.nextInt(4);
		ConfigValores2 configValores = new ConfigValores2(pos, false);
		ConfigSemelhancaTriangulos6 config = new ConfigSemelhancaTriangulos6(configValores);

		String resultadoCorreto = configValores.incognita.toString();

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		for(String passo : configValores.resolucaoPassos)
			addResolucao("\\(" + passo + "\\)");
	}
}
