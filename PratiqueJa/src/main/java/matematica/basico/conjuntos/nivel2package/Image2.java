package matematica.basico.conjuntos.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.conjuntos.ResolucaoConjuntos;

public class Image2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		DadosAB dados = new DadosAB();
		dados.bMaStr = "";

		String resultadoCorreto = "" + dados.a;
		String[] resolucao = ResolucaoConjuntos.menosA(dados);

		ConfigAB config = new ConfigAB(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual o valor de \\(|A|\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		for(String passo : resolucao)
			addResolucao(passo);
	}
}
