package matematica.intermediario.semelhancatriangulos.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.semelhancatriangulos.ConfigValores2;
import matematica.intermediario.semelhancatriangulos.nivel2package.ConfigSemelhancaTriangulos6;

public class Exercicio6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int pos = rand.nextInt(4);
		ConfigValores2 configValores = new ConfigValores2(pos, true);

		ConfigSemelhancaTriangulos6 config = new ConfigSemelhancaTriangulos6(configValores);

		String resultadoCorreto = configValores.resultado.toString() + "";

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		for(String passo : configValores.resolucaoPassos)
			addResolucao("\\(" + passo + "\\)");
	}
}
