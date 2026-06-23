package matematica.intermediario.razoestrigonometricas.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;
import matematica.intermediario.razoestrigonometricas.config.Config;
import matematica.intermediario.razoestrigonometricas.dados.Dados;
import matematica.intermediario.razoestrigonometricas.dados.DadosHipotenusa;
import matematica.intermediario.razoestrigonometricas.dados.LetrasGregas;

public class Image5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Dados dados = new DadosHipotenusa();
		String angle = LetrasGregas.getLetra();
		dados.strAngleAltura = angle;
		dados.strHipotenusa = "";
		dados.strAltura = "x";

		String pergunta = "Se a \\(tan~" + angle + "=" + dados.tagAngleAltura.showDfrac() + "\\), qual o valor de x?";

		String resultadoCorreto = "" + dados.altura;
		String resolucao = ResolucaoRazoesTrigonometricas.tagCOX(angle, dados.tagAngleAltura, dados.base);

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo(pergunta);
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + resolucao + "\\)");
	}
}
