package matematica.basico.conjuntos.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.conjuntos.ResolucaoConjuntos;

public class Image13 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		DadosAB dados = new DadosAB();
		dados.bMaStr = "";

		String resultadoCorreto = "" + dados.b;
		String resolucao = ResolucaoConjuntos.uniaoB(dados);

		ConfigAB config = new ConfigAB(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se \\(|A \\cup B| = " + dados.aUb + "\\), qual o valor de \\(|B|\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao(resolucao);
	}
}
