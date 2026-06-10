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
		String resolucao = ResolucaoConjuntos.menosA(dados);

		ConfigAB config = new ConfigAB(dados);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Qual o valor de \\(|A|\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao(resolucao);
	}
}
