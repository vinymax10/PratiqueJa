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

public class Image5 extends GeradorExercicio
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
		dados.strLadoC = "";

		String pergunta = "Se o \\(sen~" + dados.strAngleA + "=" + dados.senAngleA.showDfrac() + "\\), qual o valor de \\(sen~" + dados.strAngleB + "\\)?";

		String resultadoCorreto = "" + dados.senAngleB.toString();
		String resolucao = Resolucao.leiSenoDenominador(angleB, angleA, dados.ladoB, dados.ladoA, dados.senAngleA);

		BufferedImage image = config.criarImagem();

		addParagrafo(pergunta);
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + resolucao + "\\)");
	}
}
