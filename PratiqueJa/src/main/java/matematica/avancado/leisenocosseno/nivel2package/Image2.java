package matematica.avancado.leisenocosseno.nivel2package;

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
		String angle = LetrasGregas.getLetra();
		dados.strAngleB = angle;

		String resultadoCorreto = "" + dados.cosAngleB.toString();
		String resolucao = Resolucao.leiCosCosseno(angle, dados.ladoB, dados.ladoA, dados.ladoC);

		BufferedImage image = config.criarImagem();

		addParagrafo("Qual o \\(cos~" + dados.strAngleB + "\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + resolucao + "\\)");
	}
}
