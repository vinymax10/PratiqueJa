package matematica.intermediario.razoestrigonometricas.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;
import matematica.intermediario.razoestrigonometricas.config.Config;
import matematica.intermediario.razoestrigonometricas.dados.Dados;
import matematica.intermediario.razoestrigonometricas.dados.DadosBase;

public class Image10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Dados dados = new DadosBase();
		String angle = "30°";
		dados.strAngleAltura = angle;

		int y = 1 + rand.nextInt(10);
		dados.altura = y * y * 3;

		dados.strAltura = "";
		dados.strBase = "x";
		dados.strAltura = "\\sqrt{" + dados.altura + "}";
		dados.strHipotenusa = "";

		String resultadoCorreto = "" + y * 3;
		String resolucao = ResolucaoRazoesTrigonometricas.tag30CAX(dados.altura);

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual o valor de x?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + resolucao + "\\)");
	}
}
