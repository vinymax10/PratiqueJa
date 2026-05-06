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


public class Config1 extends Config
{
	String lateralEsq,centro,lateralDir;
	

	public Config1(String lateralEsq, String centro, String lateralDir)
	{
		super();
		this.lateralEsq = lateralEsq;
		this.centro = centro;
		this.lateralDir = lateralDir;
	}

	public BufferedImage criarImagem(int index)
	{
//		int size = 500;
		int width=1250;
		int height=750;

		ParCor parCor = ParCor.parCor(index-1);
		BufferedImage image = new BufferedImage((int) width,(int) height, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);
//		g2.setColor(Color.red);
//		g2.fillRect(0, 0, width, height);
		
		Arc2D arc;

		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));

		Ellipse2D circulo = new Ellipse2D.Double(25, 25, 700, 700);
		g2.draw(circulo);

		int raio = 200;
		if(!centro.equals(""))
		{
			Ellipse2D ponto = new Ellipse2D.Double(circulo.getCenterX() - 10, circulo.getCenterY() - 10, 20, 20);
			g2.fill(ponto);

			arc = new Arc2D.Double((int) circulo.getCenterX() - raio / 2, (int) circulo.getCenterY() - raio / 2, raio, raio,
			-45, 90, Arc2D.PIE);
			g2.setColor(Color.decode(ParCor.parCor(index).getCorFraca()));
			g2.fill(arc);
		}

		if(!lateralEsq.equals(""))
		{
			arc = new Arc2D.Double(25 - raio / 2, (int) circulo.getCenterY() - raio / 2, raio, raio, -22, 45, Arc2D.PIE);
			g2.setColor(Color.decode(ParCor.parCor(index +1).getCorFraca()));
			g2.fill(arc);
		}

		g2.setColor(Color.decode(ParCor.parCor(index-1).getCorForte()));

		if(!centro.equals(""))
		{
			arc = new Arc2D.Double(circulo.getCenterX() - circulo.getWidth() / 2,
			circulo.getCenterY() - circulo.getWidth() / 2, circulo.getWidth(), circulo.getWidth(), -45, 90, Arc2D.PIE);
			g2.draw(arc);
		}

		if(!lateralEsq.equals(""))
		{
			Line2D line1 = new Line2D.Double(30, circulo.getCenterY(), 620, 130);
			g2.draw(line1);
			
			Line2D line2 = new Line2D.Double(30, circulo.getCenterY(), 620, 620);
			g2.draw(line2);
		}

		if(!lateralDir.equals(""))
		{
			arc = new Arc2D.Double(circulo.getCenterX() - circulo.getWidth() / 2,
			circulo.getCenterY() - circulo.getWidth() / 2, circulo.getWidth(), circulo.getWidth(), -45, 90, Arc2D.OPEN);
			g2.setColor(Color.decode(ParCor.parCor(index).getCorForte()));
			g2.setStroke(new BasicStroke(20));
			g2.draw(arc);

			g2.setStroke(new BasicStroke(10));
			g2.setColor(Color.decode(ParCor.parCor(index-1).getCorForte()));
		}
		
		if(!lateralEsq.equals(""))
			Graphics.addLabel(g2, lateralEsq, 20 + raio / 2,
			(int) circulo.getCenterY(), Alinhamento.MiddleLeft);

		if(!centro.equals(""))
			Graphics.addLabel(g2, centro, (int) circulo.getCenterX() + raio / 2,
			(int) circulo.getCenterY() , Alinhamento.MiddleLeft);

		if(!lateralDir.equals(""))
			Graphics.addLabel(g2, lateralDir, 730, 
			(int) circulo.getCenterY(), Alinhamento.MiddleLeft);
		
		return image;
	}


}
