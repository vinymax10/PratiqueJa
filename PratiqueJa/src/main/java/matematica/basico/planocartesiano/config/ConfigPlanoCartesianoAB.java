package matematica.basico.planocartesiano.config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;
import matematica.basico.somaangulostriangulo.Config;

public class ConfigPlanoCartesianoAB extends Config
{
	private static final int INI_X_IMG = 50;
	private static final int INI_Y_IMG = 50;
	private static final int FIM_X_IMG = 1200;
	private static final int FIM_Y_IMG = 700;

	public final int ax, ay, bx, by;
	private final int range;

	public ConfigPlanoCartesianoAB(int ax, int ay, int bx, int by)
	{
		this(ax, ay, bx, by, 8);
	}

	public ConfigPlanoCartesianoAB(int ax, int ay, int bx, int by, int range)
	{
		super();
		this.ax = ax; this.ay = ay;
		this.bx = bx; this.by = by;
		this.range = range;
	}

	public BufferedImage criarImagem()
	{
		BufferedImage image = new BufferedImage(IMG_W, IMG_H, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);
		g2.setStroke(new BasicStroke(10));
		g2.setColor(Color.decode(ParCor.parCorAleatorio().getCorForte()));
		inserirEixos(g2);
		inserirSegmento(g2);
		inserirPonto(g2, ax, ay, "A");
		inserirPonto(g2, bx, by, "B");
		return image;
	}

	private void inserirEixos(Graphics2D g2)
	{
		int meioY = INI_Y_IMG + (FIM_Y_IMG - INI_Y_IMG) / 2;
		int meioX = INI_X_IMG + (FIM_X_IMG - INI_X_IMG) / 2;
		Graphics.arrow(g2, INI_X_IMG, meioY, FIM_X_IMG, meioY, false, true);
		Graphics.arrow(g2, meioX, FIM_Y_IMG, meioX, INI_Y_IMG, false, true);
		g2.setColor(Color.decode(ParCor.parCorAleatorio().getCorForte()));
		Graphics.addLabel(g2, "x", 1200, 400, Alinhamento.TopRight, 70);
		Graphics.addLabel(g2, "y", 625, 0, Alinhamento.TopRight, 70);
	}

	private void inserirSegmento(Graphics2D g2)
	{
		g2.setColor(Color.decode(ParCor.parCorAleatorio().getCorForte()));
		g2.setStroke(new BasicStroke(6));
		g2.draw(new Line2D.Double(tx(ax), ty(ay), tx(bx), ty(by)));
		g2.setStroke(new BasicStroke(10));
	}

	private void inserirPonto(Graphics2D g2, int px, int py, String label)
	{
		int size = 30;

		g2.setColor(Color.decode(ParCor.parCorAleatorio().getCorForte()));
		Graphics.setLineTracejada(g2, tx(px), ty(py), tx(px), ty(0), false, false);
		if (py != 0)
			Graphics.setLineTracejada(g2, tx(px), ty(py), tx(0), ty(py), false, false);

		g2.setColor(Color.decode(ParCor.parCorAleatorio().getCorForte()));
		Ellipse2D dot = new Ellipse2D.Double(tx(px) - size / 2.0, ty(py) - size / 2.0, size, size);
		g2.fill(dot);

		if (py >= 0)
			Graphics.addLabel(g2, label, (int) tx(px), (int) (ty(py) - 40), Alinhamento.BottomCenter, 80);
		else
			Graphics.addLabel(g2, label, (int) tx(px), (int) (ty(py) + 40), Alinhamento.TopCenter, 80);

		if (py > 0)
			Graphics.addLabel(g2, "" + px, (int) tx(px), (int) ty(0), Alinhamento.TopCenter, 80);
		else
			Graphics.addLabel(g2, "" + px, (int) tx(px), (int) ty(0), Alinhamento.BottomCenter, 80);

		if (py != 0)
		{
			if (px > 0)
				Graphics.addLabel(g2, "" + py, (int) tx(0), (int) ty(py), Alinhamento.MiddleRight, 80);
			else
				Graphics.addLabel(g2, "" + py, (int) tx(0), (int) ty(py), Alinhamento.MiddleLeft, 80);
		}
	}

	private double tx(double x)
	{
		return INI_X_IMG + (x - (-range)) / (double) (2 * range) * (FIM_X_IMG - INI_X_IMG);
	}

	private double ty(double y)
	{
		return FIM_Y_IMG - (y - (-range)) / (double) (2 * range) * (FIM_Y_IMG - INI_Y_IMG);
	}
}
