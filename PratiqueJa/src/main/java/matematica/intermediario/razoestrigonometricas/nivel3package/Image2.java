package matematica.intermediario.razoestrigonometricas.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;
import matematica.intermediario.razoestrigonometricas.config.Config;
import matematica.intermediario.razoestrigonometricas.dados.Dados;
import matematica.intermediario.razoestrigonometricas.dados.DadosBase;

public class Image2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Dados dados = new DadosBase();
		String angle = "30°";
		dados.strAngleAltura = angle;

		dados.altura = rand.nextInt(100);
		dados.hipotenusa = dados.altura * 2;

		dados.strBase = "";
		dados.strAltura = dados.altura + "";
		dados.strHipotenusa = "x";

		String resultadoCorreto = "" + dados.hipotenusa;
		String resolucao = ResolucaoRazoesTrigonometricas.sen30HX(dados.altura);

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual o valor de x?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + resolucao + "\\)");
	}
}
