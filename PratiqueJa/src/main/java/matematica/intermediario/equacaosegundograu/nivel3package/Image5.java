package matematica.intermediario.equacaosegundograu.nivel3package;

import java.awt.image.BufferedImage;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.intermediario.equacaosegundograu.ResolucaoEq2Grau;
import matematica.intermediario.equacaosegundograu.config.ConfigEq2Grau;
import matematica.intermediario.equacaosegundograu.config.DadosEq2Grau;

public class Image5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		DadosEq2Grau dadosEq2Grau = new DadosEq2Grau();

		String resultadoCorreto = "" + dadosEq2Grau.a;
		String texto = dadosEq2Grau.toString();
		String pergunta = "Encontre \\(a\\), dado \\(f(x)="
		+ "ax^2" + Auxiliar.getNumber(dadosEq2Grau.b, "", false)
		+ "x" + Auxiliar.getNumber(dadosEq2Grau.c, "", false) + " \\)";

		String resolucao = ResolucaoEq2Grau.resolucaoAX1(dadosEq2Grau.a, dadosEq2Grau.b, dadosEq2Grau.c, dadosEq2Grau.x1);

		ConfigEq2Grau config = new ConfigEq2Grau(dadosEq2Grau);
		config.indice = 1 + rand.nextInt(10);
		config.pontoX1.label = "" + dadosEq2Grau.x1;
		config.pontoX1.mostrar = true;

		BufferedImage image = config.criarImagem();

		addParagrafo(pergunta);
		addParagrafo("\\(" + texto + "\\)");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
