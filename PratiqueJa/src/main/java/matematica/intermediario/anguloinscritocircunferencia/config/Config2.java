package matematica.intermediario.anguloinscritocircunferencia.config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;
import matematica.intermediario.somaangulostriangulo.Config;


public class Config2 extends Config
{
	String superior,inferior;

	public Config2(String superior, String inferior)
	{
		super();
		this.superior = superior;
		this.inferior = inferior;
	}

	public BufferedImage criarImagem(int index)
	{
		int width=1250;
		int height=750;

		ParCor parCor = ParCor.parCor(index-1);
		BufferedImage image = new BufferedImage((int) width,(int) height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);

		Arc2D arc;

		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));

		Ellipse2D circulo = new Ellipse2D.Double(25, 25, 700, 700);
		g2.draw(circulo);

		Ellipse2D ponto = new Ellipse2D.Double(circulo.getCenterX() - 10, circulo.getCenterY() - 10, 20, 20);
		g2.fill(ponto);

		int raio = 200;
		arc = new Arc2D.Double(55 - raio / 2, 245 - raio / 2, raio, raio, -32, 45, Arc2D.PIE);
		g2.setColor(Color.decode(ParCor.parCor(index).getCorFraca()));
		g2.fill(arc);

		arc = new Arc2D.Double(55 - raio / 2, 505 - raio / 2, raio, raio, -10, 45, Arc2D.PIE);
		g2.setColor(Color.decode(ParCor.parCor(index +1).getCorFraca()));
		g2.fill(arc);

		g2.setStroke(new BasicStroke(10));
		g2.setColor(Color.decode(ParCor.parCor(index-1).getCorForte()));

		Line2D line1 = new Line2D.Double(55, 245, 630, 140);
		g2.draw(line1);

		Line2D line2 = new Line2D.Double(55, 245, 630, 610);
		g2.draw(line2);

		Line2D line3 = new Line2D.Double(55, 505, 630, 610);
		g2.draw(line3);

		Line2D line4 = new Line2D.Double(55, 505, 630, 140);
		g2.draw(line4);

		Graphics.addLabel(g2, superior, 55 + raio / 2, 265, Alinhamento.MiddleLeft);
		
		Graphics.addLabel(g2, inferior, 55 + raio / 2, 485, Alinhamento.MiddleLeft);
	
		return image;
	}


}
