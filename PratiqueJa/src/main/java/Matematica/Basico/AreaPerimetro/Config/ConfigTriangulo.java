package Matematica.Basico.AreaPerimetro.Config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Alinhamento;
import Matematica.ParCor;

public class ConfigTriangulo implements Config
{
	String base,altura,lateralEsq,lateralDir;
	boolean area;

	public ConfigTriangulo(String base, String altura, String lateralEsq, String lateralDir, boolean area)
	{
		this.base = base;
		this.altura = altura;
		this.lateralEsq=lateralEsq;
		this.lateralDir=lateralDir;
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

		Polygon paralelogramo = new Polygon();

		paralelogramo.addPoint(75, 620);
		paralelogramo.addPoint(1150, 620);
		paralelogramo.addPoint(400, 25);

		if(area)
		{
			g2.setColor(Color.decode(parCor.getCorFraca()));
			g2.fill(paralelogramo);
		}
		
		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));
		g2.draw(paralelogramo);
		
		if(!base.equals(""))
		{
			if(!altura.equals(""))
			{
				Graphics.setLineTracejada(g2, 75, 655, 1150, 655, true, true);
				Graphics.addLabel(g2, base, 612, 660, Alinhamento.TopCenter);
			}
			else
			{
				Graphics.addLabel(g2, base, 612, 625, Alinhamento.TopCenter);
			}
		}
		
		if(!altura.equals(""))
		{
			Graphics.setLineTracejada(g2, 400, 25, 400, 620, false, false);
			Graphics.addAngleReto(g2, 400, 570, 50);
			Graphics.addLabel(g2, altura, 400, 320, Alinhamento.MiddleLeft);
		}
		
		if(!lateralEsq.equals(""))
		{
//			Graphics.setLineTracejada(g2, 40, 600, 365, 35, true, true);
			Graphics.addLabel(g2, lateralEsq, 235, 290, Alinhamento.MiddleRight);
		}
		
		if(!lateralDir.equals(""))
		{
//			Graphics.setLineTracejada(g2, 1175, 590, 425, 25, true, true);
			Graphics.addLabel(g2, lateralDir, 750, 270, Alinhamento.MiddleLeft);	
		}
		
		return image;
	}


}
