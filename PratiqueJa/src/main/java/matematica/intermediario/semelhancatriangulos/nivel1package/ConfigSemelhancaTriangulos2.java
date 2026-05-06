package matematica.intermediario.semelhancatriangulos.nivel1package;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;
import matematica.intermediario.semelhancatriangulos.ConfigSemelhancaAngulos;
import matematica.intermediario.semelhancatriangulos.ConfigValores;


public class ConfigSemelhancaTriangulos2 extends ConfigSemelhancaAngulos
{
	public ConfigSemelhancaTriangulos2(ConfigValores configValores)
	{
		super(configValores);
	}

	public BufferedImage criarImagem(int index)
	{
		int width=1250;
		int height=750;

		ParCor parCor = ParCor.parCor(index-1);
		BufferedImage image = new BufferedImage((int) width,(int) height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);
		
		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));

		Graphics.arrow(g2, 50, 50, 150, 700, true, true);

		Graphics.arrow(g2, 375, 50, 500, 700, true, true);

		Graphics.arrow(g2, 825, 50, 950, 700, true, true);

		Graphics.arrow(g2, 97, 350, 1200, 100, false, true);

		Graphics.arrow(g2, 97, 350, 1200, 650, false, true);

		Graphics.addLabel(g2, a.nome, 245 , 290, Alinhamento.BottomCenter);
//
		Graphics.addLabel(g2, b.nome, 290 , 450, Alinhamento.TopCenter);
//
		Graphics.addLabel(g2, c.nome, 625 , 215, Alinhamento.BottomCenter);

		Graphics.addLabel(g2, d.nome, 700 , 575, Alinhamento.TopCenter);
//
		Graphics.addLabel(g2, "r", 150, 700, Alinhamento.BottomRight);
		Graphics.addLabel(g2, "s", 500, 700, Alinhamento.BottomRight);

		Graphics.addLabel(g2, "t", 950, 700, Alinhamento.BottomRight);

		Graphics.addLabel(g2, "r \\parallel s \\parallel t", 1200, 0, Alinhamento.TopRight);

		return image;
	}

}
