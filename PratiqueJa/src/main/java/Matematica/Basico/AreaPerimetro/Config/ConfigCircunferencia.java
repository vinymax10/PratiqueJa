package Matematica.Basico.AreaPerimetro.Config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Alinhamento;
import Matematica.ParCor;

//Quadrado inscrito na circunferência
public class ConfigCircunferencia implements Config
{
	String raio;
	boolean area;
	
	public ConfigCircunferencia(String raio, boolean area)
	{
		this.raio = raio;
		this.area=area;
	}

	public BufferedImage criarImagem(int index)
	{
		int width=1250;
		int height=750;
		
		ParCor parCor = ParCor.parCor(index-1);
		BufferedImage image = new BufferedImage((int) width,(int) height, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);
		Ellipse2D circulo = new Ellipse2D.Double(225, 20, 700, 700);
		
		if(area)
		{
			g2.setColor(Color.decode(parCor.getCorFraca()));
			g2.fill(circulo);
		}
		
		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));

		g2.draw(circulo);
		
		Ellipse2D ponto = new Ellipse2D.Double(circulo.getCenterX() - 10, circulo.getCenterY() - 10, 20, 20);
		g2.fill(ponto);

		Line2D diagonal = new Line2D.Double(ponto.getCenterX(), ponto.getCenterY(), 925,
		ponto.getCenterY());
		g2.draw(diagonal);

		Graphics.addLabel(g2, raio,(int) (circulo.getCenterX()+circulo.getWidth()/4), 
		(int) ponto.getCenterY(), Alinhamento.BottomCenter);

		return image;
	}
}
