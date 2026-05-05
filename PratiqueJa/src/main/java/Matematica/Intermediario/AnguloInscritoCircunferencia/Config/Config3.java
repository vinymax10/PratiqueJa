package Matematica.Intermediario.AnguloInscritoCircunferencia.Config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Alinhamento;
import Matematica.ParCor;
import Matematica.Intermediario.SomaAngulosTriangulo.Config;


public class Config3 extends Config
{
	String superior,lateralDir;

	public Config3(String superior, String lateralDir)
	{
		super();
		this.superior = superior;
		this.lateralDir = lateralDir;
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
		arc = new Arc2D.Double(600 - raio / 2, (int) 112 - raio / 2, raio, raio, -130, 40, Arc2D.PIE);
		g2.setColor(Color.decode(ParCor.parCor(index).getCorFraca()));
		g2.fill(arc);

		g2.setStroke(new BasicStroke(10));
		g2.setColor(Color.decode(ParCor.parCor(index-1).getCorForte()));

		arc = new Arc2D.Double(circulo.getCenterX() - circulo.getWidth() / 2,
		circulo.getCenterY() - circulo.getWidth() / 2, circulo.getWidth(), circulo.getWidth(), -48, 96, Arc2D.OPEN);
		g2.setColor(Color.decode(ParCor.parCor(index +1).getCorForte()));
		g2.setStroke(new BasicStroke(20));
		g2.draw(arc);

		g2.setStroke(new BasicStroke(10));
		g2.setColor(Color.decode(ParCor.parCor(index-1).getCorForte()));

		Line2D line1 = new Line2D.Double(600, 112, 600, 640);
		g2.draw(line1);

		Line2D line2 = new Line2D.Double(600, 112, 150, 640);
		g2.draw(line2);

		Graphics.addLabel(g2, superior, 560, 112 + raio / 2, Alinhamento.TopCenter);
		
		Graphics.addLabel(g2, lateralDir, 725, (int) circulo.getCenterY() , Alinhamento.MiddleLeft);
	
		return image;
	}


}
