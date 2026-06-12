package matematica.intermediario.circulo.config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;

public class ConfigCirculoDiametro
{
	String diametro;
	boolean area;

	public ConfigCirculoDiametro(String diametro, boolean area)
	{
		this.diametro = diametro;
		this.area = area;
	}

	public BufferedImage criarImagem(int index)
	{
		int width = 1250;
		int height = 750;

		ParCor parCor = ParCor.parCor(index - 1);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);

		int cx = 625;
		int cy = 375;
		int r = 280;

		Ellipse2D circle = new Ellipse2D.Double(cx - r, cy - r, 2 * r, 2 * r);

		if(area)
		{
			g2.setColor(Color.decode(parCor.getCorFraca()));
			g2.fill(circle);
		}

		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(8));
		g2.draw(circle);

		// Draw full diameter line (left edge to right edge)
		g2.setStroke(new BasicStroke(6));
		g2.draw(new Line2D.Double(cx - r, cy, cx + r, cy));

		// Endpoints dots
		Ellipse2D dot1 = new Ellipse2D.Double(cx - r - 8, cy - 8, 16, 16);
		Ellipse2D dot2 = new Ellipse2D.Double(cx + r - 8, cy - 8, 16, 16);
		g2.fill(dot1);
		g2.fill(dot2);

		// Label d above the diameter line (centered)
		Graphics.addLabel(g2, diametro, cx, cy, Alinhamento.BottomCenter);

		return image;
	}
}
