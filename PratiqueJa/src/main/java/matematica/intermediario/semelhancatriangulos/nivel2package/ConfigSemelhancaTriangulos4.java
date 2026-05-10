package matematica.intermediario.semelhancatriangulos.nivel2package;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;
import matematica.intermediario.semelhancatriangulos.ConfigSemelhancaAngulos;
import matematica.intermediario.semelhancatriangulos.ConfigValores;


public class ConfigSemelhancaTriangulos4 extends ConfigSemelhancaAngulos
{
	public ConfigSemelhancaTriangulos4(ConfigValores configValores)
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

		Line2D line1 = new Line2D.Double(250, 600, 400, 100);
		g2.draw(line1);

		Line2D line2 = new Line2D.Double(250, 600, 1200, 600);
		g2.draw(line2);

		Line2D line3 = new Line2D.Double(400, 100, 1200, 600);
		g2.draw(line3);

		Line2D line4 = new Line2D.Double(700, 600, 765, 335);
		g2.draw(line4);

		Graphics.addLabel(g2, a.nome, 950, 600, Alinhamento.TopCenter);

		Graphics.addLabel(g2, b.nome, 725, 450, Alinhamento.MiddleRight);

		Graphics.addLabel(g2, c.nome, 500, 600, Alinhamento.TopCenter);

		Graphics.addLabel(g2, d.nome, 325, 350, Alinhamento.MiddleRight);

		Graphics.addLabel(g2, "A", 400, 100, Alinhamento.BottomCenter);

		Graphics.addLabel(g2, "B", 250, 600, Alinhamento.TopCenter);

		Graphics.addLabel(g2, "C", 700, 600, Alinhamento.TopCenter);

		Graphics.addLabel(g2, "D", 785, 310, Alinhamento.BottomCenter);
//
		Graphics.addLabel(g2, "E", 1200, 600, Alinhamento.TopCenter);

		Graphics.addLabel(g2, "\\overline{AB} \\parallel \\overline{CD}", 1250, 0, Alinhamento.TopRight);

		return image;
	}

}
