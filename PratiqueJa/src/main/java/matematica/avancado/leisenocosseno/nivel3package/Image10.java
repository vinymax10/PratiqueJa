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

public class Image10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Triangulo triangulo = Triangulos.getTriangulo();
		Config config = new Config(TipoDado.AC, triangulo);

		Dados dados = config.getDados();
		String angle = LetrasGregas.getLetra();
		dados.strAngleA = angle;
		dados.strLadoA = "x";

		String pergunta = "Se o \\(cos~" + dados.strAngleA + "=" + dados.cosAngleA.showDfrac() + "\\), "
				+ "qual o valor de x?";

		String resultadoCorreto = "" + dados.ladoA.toString();
		String resolucao = Resolucao.leiCosLado(angle, dados.ladoB, dados.ladoC, dados.cosAngleA);

		BufferedImage image = config.criarImagem();

		addParagrafo(pergunta);
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
