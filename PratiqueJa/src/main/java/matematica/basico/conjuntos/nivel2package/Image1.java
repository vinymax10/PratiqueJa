package matematica.basico.conjuntos.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.conjuntos.ResolucaoConjuntos;

public class Image1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		DadosAB dados = new DadosAB();
		dados.aIbStr = "";
		dados.bMaStr = "";

		String resultadoCorreto = "" + dados.aIb;
		String resolucao = ResolucaoConjuntos.menosAIntersecB(dados);

		ConfigAB config = new ConfigAB(dados);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Se \\(|A|=" + dados.a + "\\) qual o valor de \\(|A \\cap B|\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao(resolucao);
	}
}
