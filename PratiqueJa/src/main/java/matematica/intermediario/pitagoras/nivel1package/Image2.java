package matematica.intermediario.pitagoras.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.pitagoras.ResolucaoPitagoras;
import matematica.intermediario.pitagoras.config.Config;
import matematica.intermediario.pitagoras.dados.Dados;
import matematica.intermediario.pitagoras.dados.DadosHipotenusa;

public class Image2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Dados dados = new DadosHipotenusa(20);
		dados.base.str = "b";

		String resultadoCorreto = "" + dados.base.show();
		String[] resolucao = ResolucaoPitagoras.resolucaoAXC(dados);

		Config config = Config.build1(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual o valor de \\(b\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		for(String passo : resolucao)
			addResolucao("\\(" + passo + "\\)");
	}
}
