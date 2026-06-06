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
		String resolucao = ResolucaoConjuntos.uniaoAIntersecB2(dados);
		String texto = dados.toString();

		ConfigAB config = new ConfigAB(dados);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Se \\(|A \\cup B| = " + dados.aUb + "\\), qual o valor de \\(|A \\cap B|\\)?");
		addParagrafo("\\(" + texto + "\\)");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
