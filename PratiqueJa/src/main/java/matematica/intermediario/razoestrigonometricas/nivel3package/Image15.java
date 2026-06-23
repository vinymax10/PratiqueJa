package matematica.intermediario.razoestrigonometricas.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;
import matematica.intermediario.razoestrigonometricas.config.Config;
import matematica.intermediario.razoestrigonometricas.dados.Dados;
import matematica.intermediario.razoestrigonometricas.dados.DadosBase;

public class Image15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Dados dados = new DadosBase();
		// sen(45°) = √2/2 = x/√(2y²) → x = y
		int y    = 2 + rand.nextInt(15);
		int hyp2 = 2 * y * y;

		dados.strAngleAltura = "45°";
		dados.strHipotenusa  = "\\sqrt{" + hyp2 + "}";
		dados.strAltura      = "x";
		dados.strBase        = "";

		String resultadoCorreto = "" + y;
		String resolucao = ResolucaoRazoesTrigonometricas.sen45COX(hyp2);

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual o valor de x?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + resolucao + "\\)");
	}
}
