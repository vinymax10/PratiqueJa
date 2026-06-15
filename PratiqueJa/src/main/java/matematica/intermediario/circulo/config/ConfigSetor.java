package matematica.intermediario.circulo.config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import matematica.ConfigImagem;

import infra.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;

public class ConfigSetor implements ConfigImagem
{
	String raio;
	int theta;

	public ConfigSetor(String raio, int theta)
	{
		this.raio = raio;
		this.theta = theta;
	}

	public BufferedImage criarImagem()
	{
		int width = IMG_W;
		int height = IMG_H;

		ParCor parCor = ParCor.parCorAleatorio();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);

		int cx = 530;
		int cy = 375;
		int r = 280;
		int diam = 2 * r;

		// Fill and draw sector (setAngle uses bounding-box top-left corner)
		Graphics.setAngle(g2, cx - r, cy - r, diam, 0, theta, parCor, parCor);

		// Redraw full circle border on top
		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(8));
		g2.draw(new Ellipse2D.Double(cx - r, cy - r, diam, diam));

		// Draw sector radii
		g2.setStroke(new BasicStroke(6));
		// Radius at 0° (horizontal right)
		g2.draw(new Line2D.Double(cx, cy, cx + r, cy));
		// Radius at theta° (clockwise on screen — Java2D y-axis points down)
		double rad = Math.toRadians(theta);
		int rx2 = (int)(cx + r * Math.cos(rad));
		int ry2 = (int)(cy + r * Math.sin(rad));
		g2.draw(new Line2D.Double(cx, cy, rx2, ry2));

		// Center dot
		int dotR = 10;
		g2.fill(new Ellipse2D.Double(cx - dotR, cy - dotR, 2 * dotR, 2 * dotR));

		// Label r on the horizontal radius (above the midpoint)
		Graphics.addLabel(g2, raio, cx + r / 2, cy, Alinhamento.BottomCenter);

		// Angle indicator: right-angle marker for 90°, text label otherwise
		if(theta == 90)
		{
			int markerSize = 42;
			g2.setStroke(new BasicStroke(5));
			Graphics.addAngleReto(g2, cx, cy, markerSize);
			g2.setStroke(new BasicStroke(6));
		}
		else
		{
			double bisRad = Math.toRadians(theta / 2.0);
			int tx = (int)(cx + 140 * Math.cos(bisRad));
			int ty = (int)(cy + 140 * Math.sin(bisRad));
			Graphics.addLabel(g2, theta + "^\\circ", tx, ty, Alinhamento.MiddleCenter);
		}

		return image;
	}
}
