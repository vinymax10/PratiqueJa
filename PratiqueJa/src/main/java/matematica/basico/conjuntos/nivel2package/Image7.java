package matematica.basico.conjuntos.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.conjuntos.ResolucaoConjuntos;

public class Image7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		DadosAB dados = new DadosAB();
		dados.aIbStr = "";

		String resultadoCorreto = "" + dados.aIb;
		String[] resolucao = ResolucaoConjuntos.uniaoAIntersecB2(dados);

		ConfigAB config = new ConfigAB(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se \\(|A \\cup B| = " + dados.aUb + "\\), qual o valor de \\(|A \\cap B|\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		for(String passo : resolucao)
			addResolucao(passo);
	}
}
