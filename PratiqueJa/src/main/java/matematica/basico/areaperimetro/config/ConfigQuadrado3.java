package matematica.basico.areaperimetro.config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;

//Quadrado com circunferencia dentro
public class ConfigQuadrado3 implements Config
{
	String lado, raio;
	boolean area;

	public ConfigQuadrado3(String lado, String raio, boolean area)
	{
		this.lado = lado;
		this.raio = raio;
		this.area=area;
	}

	public BufferedImage criarImagem(int index)
	{
		int width=1250;
		int height=750;
		int raioAngulo=50;
		int ladoQuadrado=600;
		int deslocamentoTracejada=40;
		
		ParCor parCor = ParCor.parCor(index-1);
		BufferedImage image = new BufferedImage((int) width,(int) height, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);
		
		if(area)
		{
			g2.setColor(Color.decode(parCor.getCorFraca()));
			g2.fill(new Rectangle2D.Double(225, 20, ladoQuadrado, ladoQuadrado));
		}

		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));
		Rectangle2D quadrado = new Rectangle2D.Double(225, 20, ladoQuadrado, ladoQuadrado);
		g2.draw(quadrado);
		
		Ellipse2D circulo = new Ellipse2D.Double(quadrado.getX(), quadrado.getY(), quadrado.getWidth(),
		quadrado.getWidth());
		g2.draw(circulo);

		Ellipse2D ponto = new Ellipse2D.Double(circulo.getCenterX() - 10, circulo.getCenterY() - 10, 20, 20);
		g2.fill(ponto);

		Line2D raio = new Line2D.Double(ponto.getCenterX(), ponto.getCenterY(), 
		quadrado.getMaxX(), ponto.getCenterY());
		g2.draw(raio);

		Graphics.addAngleReto(g2, (int)quadrado.getMinX(), 
		(int)quadrado.getMinY(), raioAngulo);
		
		Graphics.addAngleReto(g2, (int)(quadrado.getMaxX()-raioAngulo),
		(int)(quadrado.getMaxY()-raioAngulo), raioAngulo);
//
		Graphics.setLineTracejada(g2, (int)quadrado.getMaxX()+deslocamentoTracejada, (int)quadrado.getMaxY(), 
		(int)quadrado.getMaxX()+deslocamentoTracejada, (int)quadrado.getMinY(), true, true);

		Graphics.addLabel(g2, this.raio, (int)(quadrado.getCenterX()+quadrado.getWidth()/4), 
		(int)quadrado.getCenterY(), Alinhamento.BottomCenter);
		
		Graphics.addLabel(g2, lado, (int)quadrado.getMaxX()+deslocamentoTracejada, 
		(int)quadrado.getCenterY(), Alinhamento.MiddleLeft);
		
		return image;
	}


}
