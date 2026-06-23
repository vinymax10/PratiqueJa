package matematica.intermediario.razoestrigonometricas.nivel2package;

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
		dados.strAngleAltura = angle;
		dados.strAltura = "";
		dados.strHipotenusa = "x";

		String pergunta = "Se o \\(cos~" + angle + "=" + dados.cosAngleAltura.showDfrac() + "\\), qual o valor de x?";

		String resultadoCorreto = "" + dados.hipotenusa;
		String resolucao = ResolucaoRazoesTrigonometricas.cosHX(angle, dados.cosAngleAltura, dados.base);

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo(pergunta);
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + resolucao + "\\)");
	}
}
