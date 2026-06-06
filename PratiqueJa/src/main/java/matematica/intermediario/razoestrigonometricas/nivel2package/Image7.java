package matematica.intermediario.razoestrigonometricas.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;
import matematica.intermediario.razoestrigonometricas.config.Config;
import matematica.intermediario.razoestrigonometricas.dados.Dados;
import matematica.intermediario.razoestrigonometricas.dados.DadosAltura;
import matematica.intermediario.razoestrigonometricas.dados.LetrasGregas;

public class Image7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Dados dados = new DadosAltura();
		String angle = LetrasGregas.getLetra();
		dados.strAngleBase = angle;
		dados.strBase = "x";
		dados.strAltura = "";

		String pergunta = "Se o \\(sen~" + angle + "=" + dados.senAngleBase.showDfrac() + "\\), qual o valor de x?";

		String resultadoCorreto = "" + dados.base;
		String resolucao = ResolucaoRazoesTrigonometricas.senCOX(angle, dados.senAngleBase, dados.hipotenusa);
		String texto = dados.toString();

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo(pergunta);
		addParagrafo("\\(" + texto + "\\)");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
