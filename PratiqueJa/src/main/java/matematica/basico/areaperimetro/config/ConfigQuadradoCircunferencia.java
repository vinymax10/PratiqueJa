package matematica.basico.areaperimetro.config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Alinhamento;
import matematica.ConfigImagem;
import matematica.ParCor;

//Quadrado inscrito na circunferência
public class ConfigQuadradoCircunferencia implements ConfigImagem
{
	String raio,lado;
	boolean area;

	public ConfigQuadradoCircunferencia(String raio, String lado, boolean area)
	{
		this.raio = raio;
		this.lado=lado;
		this.area=area;
	}

	public BufferedImage criarImagem()
	{
		int width = IMG_W;
		int height = IMG_H;

		ParCor parCor = ParCor.parCorAleatorio();
		BufferedImage image = new BufferedImage((int) width,(int) height, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);

		int ladoSize= (int) (694 / 2 * Math.sqrt(2) - 1);
		
		if(area)
		{
			g2.setColor(Color.decode(parCor.getCorFraca()));
			g2.fill(new Rectangle2D.Double(330, 125, ladoSize, ladoSize));
		}

		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));

		Ellipse2D circulo = new Ellipse2D.Double(225, 20, 700, 700);
		g2.draw(circulo);
		
		Rectangle2D quadrado = new Rectangle2D.Double(330, 125, ladoSize, ladoSize);
		g2.draw(quadrado);

		Ellipse2D ponto = new Ellipse2D.Double(circulo.getCenterX() - 10, circulo.getCenterY() - 10, 20, 20);
		g2.fill(ponto);

		Line2D diagonal = new Line2D.Double(ponto.getCenterX(), ponto.getCenterY(), quadrado.getMaxX(),
		quadrado.getMinY());
		g2.draw(diagonal);

		Graphics.addAngleReto(g2, (int) quadrado.getMinX(), (int) quadrado.getMinY(), 50);
		Graphics.addAngleReto(g2, (int) quadrado.getMaxX() - 50, (int) quadrado.getMaxY() - 50, 50);
//		
		Graphics.setLineTracejada(g2, quadrado.getMaxX()+140,quadrado.getMaxY(), 
		quadrado.getMaxX()+140, quadrado.getMinY(), true, true);
//
		Graphics.addLabel(g2, raio,(int) (quadrado.getCenterX()+quadrado.getWidth()/4), 
		(int) (quadrado.getCenterY()-quadrado.getHeight()/4), Alinhamento.MiddleRight);
		Graphics.addLabel(g2, lado, (int) quadrado.getMaxX()+140, (int)quadrado.getCenterY(), Alinhamento.MiddleLeft);

		return image;
	}
}
