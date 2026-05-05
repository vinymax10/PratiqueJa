package Matematica.Basico.AreaPerimetro.Config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Alinhamento;
import Matematica.ParCor;


public class ConfigQuadrado implements Config
{
	String lado, diagonal;
	boolean area;

	public ConfigQuadrado(String lado, String diagonal, boolean area)
	{
		this.lado = lado;
		this.diagonal = diagonal;
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
		
		if(!diagonal.equals(""))
			g2.draw(new Line2D.Double(quadrado.getMinX()+4,quadrado.getMaxY()-4,
			quadrado.getMaxX()-4,quadrado.getMinY()+4));
		
		Graphics.addAngleReto(g2, (int)quadrado.getMinX(), 
		(int)quadrado.getMinY(), raioAngulo);
		
		Graphics.addAngleReto(g2, (int)(quadrado.getMaxX()-raioAngulo),
		(int)(quadrado.getMaxY()-raioAngulo), raioAngulo);

		Graphics.setLineTracejada(g2, (int)quadrado.getMinX(), (int)quadrado.getMaxY()+deslocamentoTracejada, 
		 (int)quadrado.getMaxX(), (int)quadrado.getMaxY()+deslocamentoTracejada, true, true);
		
		if(!diagonal.equals(""))
			Graphics.addLabel(g2, diagonal, (int)quadrado.getCenterX(), 
			(int)quadrado.getCenterY(), Alinhamento.BottomCenter);
		
		Graphics.addLabel(g2, lado, (int)quadrado.getCenterX(), 
		(int)quadrado.getMaxY()+deslocamentoTracejada, Alinhamento.TopCenter);
		
		return image;
	}


}
