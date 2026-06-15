package matematica.intermediario.razoestrigonometricas.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;
import matematica.intermediario.razoestrigonometricas.config.Config;
import matematica.intermediario.razoestrigonometricas.dados.Dados;
import matematica.intermediario.razoestrigonometricas.dados.DadosBase;

public class Image14 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Dados dados = new DadosBase();
		int y = 2 + rand.nextInt(20);

		dados.strAngleAltura = "45°";
		dados.strAltura = y + "";
		dados.strBase = "x";
		dados.strHipotenusa = "";

		String resultadoCorreto = "" + y;
		String resolucao = ResolucaoRazoesTrigonometricas.tan45CAX(y);

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual o valor de x?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
