package matematica.intermediario.razoestrigonometricas.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;
import matematica.intermediario.razoestrigonometricas.config.Config;
import matematica.intermediario.razoestrigonometricas.dados.Dados;
import matematica.intermediario.razoestrigonometricas.dados.DadosBase;
import matematica.intermediario.razoestrigonometricas.dados.LetrasGregas;

public class Image1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Dados dados = new DadosBase();
		String angle = LetrasGregas.getLetra();
		dados.strAngleAltura = angle;
		dados.strBase = "";
		dados.strAltura = "x";

		String pergunta = "Se o \\(sen~" + angle + "=" + dados.senAngleAltura.showDfrac() + "\\), qual o valor de x?";

		String resultadoCorreto = "" + dados.altura;
		String resolucao = ResolucaoRazoesTrigonometricas.senCOX(angle, dados.senAngleAltura, dados.hipotenusa);

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo(pergunta);
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
