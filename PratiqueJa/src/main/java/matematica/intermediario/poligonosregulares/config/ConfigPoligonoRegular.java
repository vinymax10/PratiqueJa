package matematica.intermediario.poligonosregulares.config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import matematica.ConfigImagem;

import infra.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;

public class ConfigPoligonoRegular implements ConfigImagem
{
	int n;
	boolean showApotema;
	String ladoLabel;
	String apotemaLabel;

	public ConfigPoligonoRegular(int n, boolean showApotema, String ladoLabel, String apotemaLabel)
	{
		this.n = n;
		this.showApotema = showApotema;
		this.ladoLabel = ladoLabel;
		this.apotemaLabel = apotemaLabel;
	}

	public BufferedImage criarImagem()
	{
		int width = IMG_W;
		int height = IMG_H;

		ParCor parCor = ParCor.parCorAleatorio();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);

		int cx = 560;
		int cy = 370;
		int r = 260;

		// Flat-bottom polygon: startAngle places two bottom vertices symmetrically
		double startAngle = Math.PI / 2.0 - Math.PI / n;
		int[] px = new int[n];
		int[] py = new int[n];
		for(int i = 0; i < n; i++)
		{
			double angle = startAngle + 2.0 * Math.PI * i / n;
			px[i] = cx + (int)(r * Math.cos(angle));
			py[i] = cy + (int)(r * Math.sin(angle));
		}

		// Fill polygon
		Polygon poly = new Polygon(px, py, n);
		g2.setColor(Color.decode(parCor.getCorFraca()));
		g2.fill(poly);

		// Draw polygon outline
		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(8));
		g2.draw(poly);

		// Apótema foot: midpoint of bottom side (between vertex 0 and vertex 1)
		int apotemaFootX = cx;
		int apotemaFootY = cy + (int)(r * Math.cos(Math.PI / n));

		// Draw apótema line
		if(showApotema)
		{
			g2.setColor(Color.decode(parCor.getCorForte()));
			g2.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
				10f, new float[]{15f, 15f}, 0f));
			g2.drawLine(cx, cy, apotemaFootX, apotemaFootY);
			g2.setStroke(new BasicStroke(8));

			// Right-angle marker at foot
			int markerSize = 28;
			g2.setStroke(new BasicStroke(5));
			Graphics.addAngleReto(g2, apotemaFootX, apotemaFootY - markerSize, markerSize);
			g2.setStroke(new BasicStroke(8));
		}

		// Label for side (below the bottom side midpoint)
		if(ladoLabel != null && !ladoLabel.isEmpty())
		{
			int labelX = cx;
			int labelY = apotemaFootY + 20;
			Graphics.addLabel(g2, ladoLabel, labelX, labelY, Alinhamento.TopCenter);
		}

		// Label for apótema (to the right of the midpoint of the apótema)
		if(showApotema && apotemaLabel != null && !apotemaLabel.isEmpty())
		{
			int labelX = apotemaFootX + 30;
			int labelY = (cy + apotemaFootY) / 2;
			Graphics.addLabel(g2, apotemaLabel, labelX, labelY, Alinhamento.MiddleLeft);
		}

		return image;
	}
}
