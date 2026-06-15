package matematica.intermediario.razoestrigonometricas.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;
import matematica.intermediario.razoestrigonometricas.config.Config;
import matematica.intermediario.razoestrigonometricas.dados.Dados;
import matematica.intermediario.razoestrigonometricas.dados.DadosBase;

public class Image7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Dados dados = new DadosBase();
		String angle = "60°";
		dados.strAngleBase = angle;
		dados.strBase = "";
		dados.strAltura = "x";
		dados.altura = rand.nextInt(100);
		dados.hipotenusa = dados.altura * 2;
		dados.strHipotenusa = dados.hipotenusa + "";

		String resultadoCorreto = "" + dados.altura;
		String resolucao = ResolucaoRazoesTrigonometricas.cos60COX(dados.hipotenusa);

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual o valor de x?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
