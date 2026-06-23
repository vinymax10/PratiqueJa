package matematica.avancado.leisenocosseno.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.avancado.leisenocosseno.Resolucao;
import matematica.avancado.leisenocosseno.config.Config;
import matematica.avancado.leisenocosseno.config.Dados;
import matematica.avancado.leisenocosseno.config.TipoDado;
import matematica.avancado.leisenocosseno.config.Triangulo;
import matematica.avancado.leisenocosseno.config.Triangulos;
import matematica.intermediario.razoestrigonometricas.dados.LetrasGregas;

public class Image2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Triangulo triangulo = Triangulos.getTriangulo();
		Config config = new Config(TipoDado.AB, triangulo);

		Dados dados = config.getDados();
		String angleA = LetrasGregas.getLetra();
		String angleB = LetrasGregas.getLetra(angleA);
		dados.strAngleA = angleA;
		dados.strAngleB = angleB;
		dados.strLadoB = "x";
		dados.strLadoC = "";

		String pergunta = "Se o \\(sen~" + dados.strAngleA + "=" + dados.senAngleA.showDfrac() + "\\) "
		+ "e \\(sen~" + dados.strAngleB + "=" + dados.senAngleB.showDfrac() + "\\), qual o valor de x?";

		String resultadoCorreto = "" + dados.ladoB.toString();
		String resolucao = Resolucao.leiSenoNumerador(angleB, angleA, dados.ladoA, dados.senAngleB, dados.senAngleA);

		BufferedImage image = config.criarImagem();

		addParagrafo(pergunta);
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + resolucao + "\\)");
	}
}
