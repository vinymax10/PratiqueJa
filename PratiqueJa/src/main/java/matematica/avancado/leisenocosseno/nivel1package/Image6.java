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

public class Image6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Triangulo triangulo = Triangulos.getTriangulo();
		Config config = new Config(TipoDado.BC, triangulo);

		Dados dados = config.getDados();
		String angleB = LetrasGregas.getLetra();
		String angleC = LetrasGregas.getLetra(angleB);
		dados.strAngleB = angleB;
		dados.strAngleC = angleC;
		dados.strLadoC = "x";
		dados.strLadoA = "";

		String pergunta = "Se o \\(sen~" + dados.strAngleB + "=" + dados.senAngleB.showDfrac() + "\\) "
		+ "e \\(sen~" + dados.strAngleC + "=" + dados.senAngleC.showDfrac() + "\\), qual o valor de x?";

		String resultadoCorreto = "" + dados.ladoC.toString();
		String resolucao = Resolucao.leiSenoNumerador(angleC, angleB, dados.ladoB, dados.senAngleC, dados.senAngleB);

		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo(pergunta);
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
