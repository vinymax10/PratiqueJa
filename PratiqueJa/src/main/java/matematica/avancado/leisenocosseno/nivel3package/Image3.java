package matematica.avancado.leisenocosseno.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.avancado.leisenocosseno.Resolucao;
import matematica.avancado.leisenocosseno.config.Config;
import matematica.avancado.leisenocosseno.config.Dados;
import matematica.avancado.leisenocosseno.config.TipoDado;
import matematica.avancado.leisenocosseno.config.Triangulo;
import matematica.avancado.leisenocosseno.config.Triangulos;
import matematica.intermediario.razoestrigonometricas.dados.LetrasGregas;

public class Image3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Triangulo triangulo = Triangulos.getTriangulo();
		Config config = new Config(TipoDado.AB, triangulo);

		Dados dados = config.getDados();
		String angle = LetrasGregas.getLetra();
		dados.strAngleC = angle;
		dados.strLadoC = "x";

		String pergunta = "Se o \\(cos~" + dados.strAngleC + "=" + dados.cosAngleC.showDfrac() + "\\), "
		+ "qual o valor de x?";

		String resultadoCorreto = "" + dados.ladoC.toString();
		String resolucao = Resolucao.leiCosLado(angle, dados.ladoA, dados.ladoB, dados.cosAngleC);

		BufferedImage image = config.criarImagem();

		addParagrafo(pergunta);
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
