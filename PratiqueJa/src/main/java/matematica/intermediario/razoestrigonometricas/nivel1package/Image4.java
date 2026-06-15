package matematica.intermediario.razoestrigonometricas.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;
import matematica.intermediario.razoestrigonometricas.config.Config;
import matematica.intermediario.razoestrigonometricas.dados.Dados;
import matematica.intermediario.razoestrigonometricas.dados.DadosAltura;
import matematica.intermediario.razoestrigonometricas.dados.LetrasGregas;

public class Image4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Dados dados = new DadosAltura();
		String angle = LetrasGregas.getLetra();
		dados.strAngleBase = angle;
		dados.strAltura = "";

		String resultadoCorreto = "" + dados.senAngleBase;
		String resolucao = ResolucaoRazoesTrigonometricas.sen(angle, dados.base, dados.hipotenusa);

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual o \\(sen~" + angle + "\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
