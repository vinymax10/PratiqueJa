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

public class Image9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Triangulo triangulo = Triangulos.getTriangulo();
		Config config = new Config(TipoDado.AC, triangulo);

		Dados dados = config.getDados();
		String angle = LetrasGregas.getLetra();
		dados.strAngleC = angle;
		dados.strLadoA = "";
		dados.strLadoB = "";

		Racional r = dados.ladoC.div(new Racional(2).mult(dados.senAngleC));
		r.fatoracao(2);
		String resultadoCorreto = r.toString();
		String resolucao = Resolucao.raioCircunscrito(angle, dados.ladoC, dados.senAngleC);

		BufferedImage image = config.criarImagem();

		addParagrafo("Sabendo que \\(\\text{sen}~" + angle + " = " + dados.senAngleC.showDfrac()
				+ "\\), qual o raio \\(R\\) da circunferência circunscrita ao triângulo?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + resolucao + "\\)");
	}
}
