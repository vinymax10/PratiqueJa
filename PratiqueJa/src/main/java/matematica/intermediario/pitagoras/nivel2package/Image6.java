package matematica.intermediario.pitagoras.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.pitagoras.ResolucaoPitagoras;
import matematica.intermediario.pitagoras.config.Config;
import matematica.intermediario.pitagoras.dados.Dados;
import matematica.intermediario.pitagoras.dados.DadosBase;

public class Image6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Dados dados = new DadosBase(20);
		dados.altura.str = "c";

		String resultadoCorreto = "" + dados.altura.show();
		String resolucao = ResolucaoPitagoras.resolucaoABX(dados);
		String texto = dados.toString();

		Config config = Config.build3(dados);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Qual o valor de \\(c\\)?");
		addParagrafo("\\(" + texto + "\\)");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
