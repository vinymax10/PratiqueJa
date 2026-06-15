package matematica.avancado.inequacoessegundograu.config;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import static matematica.ConfigImagem.IMG_H;
import static matematica.ConfigImagem.IMG_W;

import matematica.intermediario.equacaosegundograu.config.DadosEq2Grau;
import matematica.intermediario.equacaosegundograu.config.Grafico;

public class ConfigInequacao
{
	public int indice;

	private final DadosEq2Grau dados;
	private final String labelAtX1;
	private final String labelAtX2;

	/**
	 * Builds a parabola config with two guaranteed integer roots r1 < r2.
	 * Sets DadosEq2Grau fields to match the Grafico convention:
	 *   a > 0: dados.x1 = r2 (larger), dados.x2 = r1 (smaller)
	 *   a < 0: dados.x1 = r1 (smaller), dados.x2 = r2 (larger)
	 */
	public ConfigInequacao(int a, int b, int c, int r1, int r2)
	{
		dados = new DadosEq2Grau();
		dados.a = a;
		dados.b = b;
		dados.c = c;
		dados.xVertice = (r1 + r2) / 2.0;
		dados.yVertice = -(double)(b * b - 4 * a * c) / (4.0 * a);

		if (a > 0)
		{
			dados.x1 = r2;
			dados.x2 = r1;
			labelAtX1 = "" + r2;
			labelAtX2 = "" + r1;
		}
		else
		{
			dados.x1 = r1;
			dados.x2 = r2;
			labelAtX1 = "" + r1;
			labelAtX2 = "" + r2;
		}
	}

	public BufferedImage criarImagem()
	{
		BufferedImage image = new BufferedImage(IMG_W, IMG_H, BufferedImage.TYPE_INT_ARGB);
		Grafico grafico = new Grafico(dados);
		Graphics2D g2 = grafico.carregamentoInicial(image);
		grafico.inserirEixoCartesiano(g2);
		grafico.inserirCurva(g2);
		grafico.inserirPontoX1(g2, labelAtX1);
		grafico.inserirPontoX2(g2, labelAtX2);
		return image;
	}
}
