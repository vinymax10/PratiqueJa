package matematica.intermediario.razoestrigonometricas.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;
import matematica.intermediario.razoestrigonometricas.config.Config;
import matematica.intermediario.razoestrigonometricas.dados.Dados;
import matematica.intermediario.razoestrigonometricas.dados.DadosBase;

public class Image1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Dados dados = new DadosBase();
		String angle = "30°";
		dados.strAngleAltura = angle;
		dados.strBase = "";
		dados.strAltura = "x";
		dados.altura = rand.nextInt(100);
		dados.hipotenusa = dados.altura * 2;
		dados.strHipotenusa = dados.hipotenusa + "";

		String resultadoCorreto = "" + dados.altura;
		String resolucao = ResolucaoRazoesTrigonometricas.sen30COX(dados.hipotenusa);

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual o valor de x?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
