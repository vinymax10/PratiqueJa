package matematica.intermediario.semelhancatriangulos.nivel2package;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;
import matematica.intermediario.semelhancatriangulos.ConfigSemelhancaAngulos;
import matematica.intermediario.semelhancatriangulos.ConfigValores;


public class ConfigSemelhancaTriangulos6 extends ConfigSemelhancaAngulos
{
	public ConfigSemelhancaTriangulos6(ConfigValores configValores)
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

		Line2D line1 = new Line2D.Double(100, 100, 800, 100);
		g2.draw(line1);

		Line2D line2 = new Line2D.Double(800, 100, 1000, 600);
		g2.draw(line2);

		Line2D line3 = new Line2D.Double(100, 100, 1000, 600);
		g2.draw(line3);

		Line2D line4 = new Line2D.Double(450, 100, 550, 350);
		g2.draw(line4);

		Graphics.addLabel(g2, a.nome, 325 , 250 , Alinhamento.TopCenter);

		Graphics.addLabel(g2, b.nome,  500, 210  , Alinhamento.MiddleLeft);

		Graphics.addLabel(g2, c.nome,775 , 500, Alinhamento.TopCenter);

		Graphics.addLabel(g2, d.nome, 900, 300 , Alinhamento.MiddleLeft);

		Graphics.addLabel(g2, "A", 100 , 100  , Alinhamento.BottomCenter);

		Graphics.addLabel(g2, "B", 450 , 100 , Alinhamento.BottomCenter);

		Graphics.addLabel(g2, "C",800 , 100 , Alinhamento.BottomCenter);

		Graphics.addLabel(g2, "D", 1000 , 600, Alinhamento.TopCenter);

		Graphics.addLabel(g2, "E", 550 , 350 , Alinhamento.TopCenter);

		Graphics.addLabel(g2, "\\overline{BE} \\parallel \\overline{CD}", 0, 725 , Alinhamento.BottomLeft,80);

		return image;
	}

}
