package matematica.intermediario.razoestrigonometricas.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;
import matematica.intermediario.razoestrigonometricas.config.Config;
import matematica.intermediario.razoestrigonometricas.dados.Dados;
import matematica.intermediario.razoestrigonometricas.dados.DadosBase;

public class Image4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Dados dados = new DadosBase();
		String angle = "60°";
		dados.strAngleBase = angle;

		int y = 1 + rand.nextInt(10);
		dados.base = y * y * 3;

		dados.strAltura = "";
		dados.strBase = "\\sqrt{" + dados.base + "}";
		dados.strHipotenusa = "x";

		String resultadoCorreto = "" + y * 2;
		String resolucao = ResolucaoRazoesTrigonometricas.sen60HX(dados.base);

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual o valor de x?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + resolucao + "\\)");
	}
}
