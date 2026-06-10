package matematica.basico.conjuntos.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.conjuntos.ResolucaoConjuntos;

public class Image5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		DadosAB dados = new DadosAB();
		dados.aMbStr = "";

		String resultadoCorreto = "" + dados.a;
		String resolucao = ResolucaoConjuntos.uniaoA(dados);

		ConfigAB config = new ConfigAB(dados);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Se \\(|A \\cup B| = " + dados.aUb + "\\), qual o valor de \\(|A|\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao(resolucao);
	}
}
