package matematica.intermediario.pitagoras.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.pitagoras.ResolucaoPitagoras;
import matematica.intermediario.pitagoras.config.Config;
import matematica.intermediario.pitagoras.dados.Dados;
import matematica.intermediario.pitagoras.dados.DadosBase2;

public class Image1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Dados dados = new DadosBase2(20);
		dados.hipotenusa.str = "a";

		String resultadoCorreto = "" + dados.hipotenusa.show();
		String resolucao = ResolucaoPitagoras.resolucaoXBC(dados);
		String texto = dados.toString();

		Config config = Config.build2(dados);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Qual o valor de \\(a\\)?");
		addParagrafo("\\(" + texto + "\\)");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
