package matematica.intermediario.equacaosegundograu.nivel3package;

import java.awt.image.BufferedImage;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.intermediario.equacaosegundograu.ResolucaoEq2Grau;
import matematica.intermediario.equacaosegundograu.config.ConfigEq2Grau;
import matematica.intermediario.equacaosegundograu.config.DadosEq2Grau;

public class Image9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		DadosEq2Grau dadosEq2Grau = new DadosEq2Grau();
		while(dadosEq2Grau.b == 0)
			dadosEq2Grau = new DadosEq2Grau();

		String resultadoCorreto = "" + dadosEq2Grau.a;
		String texto = dadosEq2Grau.toString();
		String pergunta = "Encontre \\(a\\), dado \\( f(x)=" + "ax^2" + Auxiliar.getNumber(dadosEq2Grau.b, "x", false)
		+ Auxiliar.getNumber(dadosEq2Grau.c, "", false) + " \\)";

		String resolucao = ResolucaoEq2Grau.resolucaoAXv(dadosEq2Grau.a, dadosEq2Grau.b, dadosEq2Grau.c, dadosEq2Grau.xVerticeRacional);

		ConfigEq2Grau config = new ConfigEq2Grau(dadosEq2Grau);
		config.indice = 1 + rand.nextInt(10);
		config.pontoXv.mostrar = true;
		config.pontoXv.label = dadosEq2Grau.xVerticeRacional.showFrac();

		BufferedImage image = config.criarImagem();

		addParagrafo(pergunta);
		addParagrafo("\\(" + texto + "\\)");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
