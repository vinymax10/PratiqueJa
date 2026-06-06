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

public class Image8 extends GeradorExercicio
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

		String pergunta = "Se o \\(sen~" + dados.strAngleC + "=" + dados.senAngleC.showDfrac() + "\\), qual o valor de \\(sen~" + dados.strAngleB + "\\)?";

		String resultadoCorreto = "" + dados.senAngleB.toString();
		String resolucao = Resolucao.leiSenoDenominador(angleB, angleC, dados.ladoB, dados.ladoC, dados.senAngleC);

		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo(pergunta);
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
