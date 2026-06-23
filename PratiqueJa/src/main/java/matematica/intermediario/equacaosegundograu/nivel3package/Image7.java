package matematica.intermediario.equacaosegundograu.nivel3package;

import java.awt.image.BufferedImage;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.intermediario.equacaosegundograu.ResolucaoEq2Grau;
import matematica.intermediario.equacaosegundograu.config.ConfigEq2Grau;
import matematica.intermediario.equacaosegundograu.config.DadosEq2Grau;

public class Image7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		DadosEq2Grau dadosEq2Grau = new DadosEq2Grau();

		String resultadoCorreto = "" + dadosEq2Grau.yVerticeRacional;
		String texto = dadosEq2Grau.toString();
		String pergunta = "Encontre \\( y_v\\), dado \\(f(x)=" + dadosEq2Grau.a + "x^2" +
		Auxiliar.getNumber(dadosEq2Grau.b, "", false) + "x"
		+ Auxiliar.getNumber(dadosEq2Grau.c, "", false) + " \\)";

		String[] resolucao = ResolucaoEq2Grau.resolucaoYVertice(dadosEq2Grau.a, dadosEq2Grau.b, dadosEq2Grau.c);

		ConfigEq2Grau config = new ConfigEq2Grau(dadosEq2Grau);
		config.indice = 1 + rand.nextInt(10);
		config.pontoXv.mostrar = true;
		config.pontoXv.label = "x_v";

		BufferedImage image = config.criarImagem();

		addParagrafo(pergunta);
		addParagrafo("\\(" + texto + "\\)");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		for(String passo : resolucao)
			addResolucao(passo);
	}
}
