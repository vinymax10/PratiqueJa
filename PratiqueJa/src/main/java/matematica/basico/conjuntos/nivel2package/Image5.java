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
		String[] resolucao = ResolucaoConjuntos.uniaoA(dados);

		ConfigAB config = new ConfigAB(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se \\(|A \\cup B| = " + dados.aUb + "\\), qual o valor de \\(|A|\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		for(String passo : resolucao)
			addResolucao(passo);
	}
}
