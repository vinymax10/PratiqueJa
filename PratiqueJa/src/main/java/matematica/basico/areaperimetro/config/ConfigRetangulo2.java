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

//Retangulo com circunferencia dentro
public class ConfigRetangulo2 implements Config
{
	String base,altura,raio;
	boolean area;

	public ConfigRetangulo2(String base, String altura, String raio, boolean area)
	{
		this.base = base;
		this.altura = altura;
		this.raio = raio;
		this.area=area;
	}

	public BufferedImage criarImagem(int index)
	{
		int width=1250;
		int height=750;
		int raioAngulo=50;
		int ladoRetangulo=600;
		int baseRetangulo=950;

		int deslocamentoTracejada=40;
		
		ParCor parCor = ParCor.parCor(index-1);
		BufferedImage image = new BufferedImage((int) width,(int) height, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);
		
		if(area)
		{
			g2.setColor(Color.decode(parCor.getCorFraca()));
			g2.fill(new Rectangle2D.Double(125, 20, baseRetangulo, ladoRetangulo));
		}

		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));
		Rectangle2D retangulo = new Rectangle2D.Double(125, 20, baseRetangulo, ladoRetangulo);
		g2.draw(retangulo);
		
		Ellipse2D circulo = new Ellipse2D.Double(retangulo.getX(), retangulo.getY(), retangulo.getHeight(),
		retangulo.getHeight());
		g2.draw(circulo);

		Ellipse2D ponto = new Ellipse2D.Double(circulo.getCenterX() - 10, circulo.getCenterY() - 10, 20, 20);
		g2.fill(ponto);

		Line2D raio = new Line2D.Double(ponto.getCenterX(), ponto.getCenterY(), 
		circulo.getMaxX(), ponto.getCenterY());
		g2.draw(raio);

		Graphics.addAngleReto(g2, (int)retangulo.getMinX(), 
		(int)retangulo.getMinY(), raioAngulo);
		
		Graphics.addAngleReto(g2, (int)(retangulo.getMaxX()-raioAngulo),
		(int)(retangulo.getMaxY()-raioAngulo), raioAngulo);
//
		Graphics.setLineTracejada(g2, (int)retangulo.getMaxX()+deslocamentoTracejada, (int)retangulo.getMaxY(), 
		(int)retangulo.getMaxX()+deslocamentoTracejada, (int)retangulo.getMinY(), true, true);
		
		Graphics.setLineTracejada(g2, (int)retangulo.getMinX(), (int)retangulo.getMaxY()+deslocamentoTracejada, 
		(int)retangulo.getMaxX(), (int)retangulo.getMaxY()+deslocamentoTracejada, true, true);

		Graphics.addLabel(g2, this.raio, (int)(circulo.getCenterX()+circulo.getWidth()/4), 
		(int)circulo.getCenterY(), Alinhamento.BottomCenter);
		
		Graphics.addLabel(g2, altura, (int)retangulo.getMaxX()+deslocamentoTracejada, 
		(int)retangulo.getCenterY(), Alinhamento.MiddleLeft);
		
		Graphics.addLabel(g2, base, (int)retangulo.getCenterX(), 
		(int)retangulo.getMaxY()+deslocamentoTracejada, Alinhamento.TopCenter);
		
		return image;
	}


}
