package matematica.intermediario.circulo.config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import matematica.ConfigImagem;

import infra.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;

public class ConfigCoroa implements ConfigImagem
{
	String R;
	String r;

	public ConfigCoroa(String R, String r)
	{
		this.R = R;
		this.r = r;
	}

	public BufferedImage criarImagem()
	{
		int width = IMG_W;
		int height = IMG_H;

		ParCor parCor = ParCor.parCorAleatorio();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);

		int cx = 550;
		int cy = 375;
		int outerR = 280;
		int innerR = 150;

		Ellipse2D outer = new Ellipse2D.Double(cx - outerR, cy - outerR, 2 * outerR, 2 * outerR);
		Ellipse2D inner = new Ellipse2D.Double(cx - innerR, cy - innerR, 2 * innerR, 2 * innerR);

		// Fill only the ring area
		Area ring = new Area(outer);
		ring.subtract(new Area(inner));
		g2.setColor(Color.decode(parCor.getCorFraca()));
		g2.fill(ring);

		// Draw both circle borders
		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(8));
		g2.draw(outer);
		g2.draw(inner);

		// Center dot
		int dotR = 10;
		g2.fill(new Ellipse2D.Double(cx - dotR, cy - dotR, 2 * dotR, 2 * dotR));

		// R: horizontal right line from center to outer edge
		g2.setStroke(new BasicStroke(5));
		g2.draw(new Line2D.Double(cx, cy, cx + outerR, cy));
		// Tick mark at inner radius
		g2.draw(new Line2D.Double(cx + innerR, cy - 14, cx + innerR, cy + 14));

		// r: vertical upward line from center to inner edge
		g2.draw(new Line2D.Double(cx, cy, cx, cy - innerR));

		// Label R between inner and outer radii (on horizontal line)
		Graphics.addLabel(g2, R, cx + innerR + (outerR - innerR) / 2, cy, Alinhamento.BottomCenter);
		// Label r to the right of the vertical inner radius
		Graphics.addLabel(g2, r, cx + 10, cy - innerR / 2, Alinhamento.MiddleLeft);

		return image;
	}
}
