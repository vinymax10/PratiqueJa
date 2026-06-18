package matematica.intermediario.pitagoras.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.pitagoras.ResolucaoPitagoras;
import matematica.intermediario.pitagoras.config.Config;
import matematica.intermediario.pitagoras.dados.Dados;
import matematica.intermediario.pitagoras.dados.DadosBase;

public class Image9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Dados dados = new DadosBase(20);
		dados.altura.str = "c";

		String resultadoCorreto = "" + dados.altura.show();
		String resolucao = ResolucaoPitagoras.resolucaoABX(dados);

		Config config = Config.build1(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual o valor de \\(c\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
