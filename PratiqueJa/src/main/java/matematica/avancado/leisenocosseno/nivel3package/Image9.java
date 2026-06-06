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

public class Image9 extends GeradorExercicio
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
		dados.strLadoA = "";

		String pergunta = "Se o \\(sen~" + dados.strAngleB + "=" + dados.senAngleB.showDfrac() + "\\), qual o valor de \\(sen~" + dados.strAngleC + "\\)?";

		String resultadoCorreto = "" + dados.senAngleC.toString();
		String resolucao = Resolucao.leiSenoDenominador(angleC, angleB, dados.ladoC, dados.ladoB, dados.senAngleB);

		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo(pergunta);
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
