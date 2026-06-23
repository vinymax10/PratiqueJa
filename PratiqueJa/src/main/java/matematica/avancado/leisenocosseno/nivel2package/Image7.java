package matematica.avancado.leisenocosseno.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.leisenocosseno.Resolucao;
import matematica.avancado.leisenocosseno.config.Config;
import matematica.avancado.leisenocosseno.config.Dados;
import matematica.avancado.leisenocosseno.config.TipoDado;
import matematica.avancado.leisenocosseno.config.Triangulo;
import matematica.avancado.leisenocosseno.config.Triangulos;
import matematica.intermediario.razoestrigonometricas.dados.LetrasGregas;

public class Image7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Triangulo triangulo = Triangulos.getTriangulo();
		Config config = new Config(TipoDado.AB, triangulo);

		Dados dados = config.getDados();
		String angle = LetrasGregas.getLetra();
		dados.strAngleA = angle;
		dados.strLadoB = "";
		dados.strLadoC = "";

		Racional r = dados.ladoA.div(new Racional(2).mult(dados.senAngleA));
		r.fatoracao(2);
		String resultadoCorreto = r.toString();
		String resolucao = Resolucao.raioCircunscrito(angle, dados.ladoA, dados.senAngleA);

		BufferedImage image = config.criarImagem();

		addParagrafo("Sabendo que \\(\\text{sen}~" + angle + " = " + dados.senAngleA.showDfrac()
				+ "\\), qual o raio \\(R\\) da circunferência circunscrita ao triângulo?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + resolucao + "\\)");
	}
}
