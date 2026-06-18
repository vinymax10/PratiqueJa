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

public class Image17 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Triangulo triangulo = Triangulos.getTriangulo();
		Config config = new Config(TipoDado.AC, triangulo);

		Dados dados = config.getDados();
		String angleA = LetrasGregas.getLetra();
		String angleC = LetrasGregas.getLetra(angleA);
		dados.strAngleA = angleA;
		dados.strAngleC = angleC;
		dados.strLadoA = "x";
		dados.strLadoB = "";

		String pergunta = "Se o \\(sen~" + dados.strAngleA + "=" + dados.senAngleA.showDfrac() + "\\) "
		+ "e \\(sen~" + dados.strAngleC + "=" + dados.senAngleC.showDfrac() + "\\), qual o valor de x?";

		String resultadoCorreto = "" + dados.ladoA.toString();
		String resolucao = Resolucao.leiSenoNumerador(angleA, angleC, dados.ladoC, dados.senAngleA, dados.senAngleC);

		BufferedImage image = config.criarImagem();

		addParagrafo(pergunta);
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
