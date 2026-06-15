package matematica.intermediario.razoestrigonometricas.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;
import matematica.intermediario.razoestrigonometricas.config.Config;
import matematica.intermediario.razoestrigonometricas.dados.Dados;
import matematica.intermediario.razoestrigonometricas.dados.DadosBase;

public class Image3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Dados dados = new DadosBase();
		String angle = "60°";
		dados.strAngleBase = angle;

		int y = 1 + rand.nextInt(10);
		dados.hipotenusa = y * y * 3;

		dados.strAltura = "";
		dados.strBase = "x";
		dados.strHipotenusa = "\\sqrt{" + dados.hipotenusa + "}";

		Racional resultado = new Racional(3 * y, 2);
		resultado.fatoracao(2);

		String resultadoCorreto = resultado.toString();
		String resolucao = ResolucaoRazoesTrigonometricas.sen60COX(dados.hipotenusa);

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual o valor de x?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
