package matematica.intermediario.pitagoras.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.pitagoras.ResolucaoPitagoras;
import matematica.intermediario.pitagoras.config.Config;
import matematica.intermediario.pitagoras.dados.Dados;
import matematica.intermediario.pitagoras.dados.DadosAltura2;

public class Image2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Dados dados = new DadosAltura2(20);
		dados.base.str = "b";

		String resultadoCorreto = "" + dados.base.show();
		String resolucao = ResolucaoPitagoras.resolucaoAXC(dados);

		Config config = Config.build2(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual o valor de \\(b\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
