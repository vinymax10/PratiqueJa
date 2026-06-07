package matematica.basico.conjuntos.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.conjuntos.ResolucaoConjuntos;

public class Image4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		DadosAB dados = new DadosAB();

		String resultadoCorreto = "" + dados.aUb;
		String resolucao = ResolucaoConjuntos.uniaoAUniaoB2(dados);

		ConfigAB config = new ConfigAB(dados);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Qual o valor de \\(|A \\cup B|\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
