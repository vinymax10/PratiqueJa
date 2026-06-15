package matematica.intermediario.razoestrigonometricas.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;
import matematica.intermediario.razoestrigonometricas.config.Config;
import matematica.intermediario.razoestrigonometricas.dados.Dados;
import matematica.intermediario.razoestrigonometricas.dados.DadosHipotenusa;
import matematica.intermediario.razoestrigonometricas.dados.LetrasGregas;

public class Image3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Dados dados = new DadosHipotenusa();
		String angle = LetrasGregas.getLetra();
		dados.strAngleAltura = angle;
		dados.strHipotenusa = "";

		String resultadoCorreto = "" + dados.tagAngleAltura;
		String resolucao = ResolucaoRazoesTrigonometricas.tag(angle, dados.altura, dados.base);

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual a \\(tan~" + angle + "\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
