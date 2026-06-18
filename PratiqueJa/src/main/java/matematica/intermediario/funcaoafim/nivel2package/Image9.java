package matematica.intermediario.funcaoafim.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.funcaoafim.ResolucaoFuncaoAfim;
import matematica.intermediario.funcaoafim.config.ConfigRetaInteiro;

public class Image9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = -3 + rand.nextInt(7);
		int b = -3 + rand.nextInt(7);

		while(a == 0 && b == 0)
		{
			a = -3 + rand.nextInt(7);
			b = -3 + rand.nextInt(7);
		}

		ConfigRetaInteiro config = new ConfigRetaInteiro(a, b);
		BufferedImage image = config.criarImagem();

		int pontoAx = (int) config.pontoAx;
		int pontoAy = (int) config.pontoAy;
		int pontoBx = (int) config.pontoBx;
		int pontoBy = (int) config.pontoBy;

		String resolucao = "";
		resolucao += "Dado os pontos \\( A=(" + pontoAx + "," + pontoAy + ") \\)  e ";
		resolucao += "\\(B=(" + pontoBx + "," + pontoBy + ")\\), ";
		resolucao += "temos que o coeficiente angular \\(a\\) é calculado por: \\(\\\\ \\)";
		resolucao += "\\("+ResolucaoFuncaoAfim.resolucao(pontoAx, pontoAy, pontoBx, pontoBy)+"\\)";

		addParagrafo("Encontre o coeficiente angular da reta:");
		addParagrafoImagem(image);
		gerarAlternativas("" + a);
		setResolucao( resolucao );
	}
}
