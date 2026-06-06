package matematica.intermediario.razoestrigonometricas.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;
import matematica.intermediario.razoestrigonometricas.config.Config;
import matematica.intermediario.razoestrigonometricas.dados.Dados;
import matematica.intermediario.razoestrigonometricas.dados.DadosBase;
import matematica.intermediario.razoestrigonometricas.dados.LetrasGregas;

public class Image9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Dados dados = new DadosBase();
		String angle = LetrasGregas.getLetra();
		dados.strAngleBase = angle;
		dados.strAltura = "x";
		dados.strBase = "";

		String pergunta = "Se o \\(cos~" + angle + "=" + dados.cosAngleBase.showDfrac() + "\\), qual o valor de x?";

		String resultadoCorreto = "" + dados.altura;
		String resolucao = ResolucaoRazoesTrigonometricas.cosCAX(angle, dados.cosAngleBase, dados.hipotenusa);
		String texto = dados.toString();

		Config config = Config.buildConfig(dados);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo(pergunta);
		addParagrafo("\\(" + texto + "\\)");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
