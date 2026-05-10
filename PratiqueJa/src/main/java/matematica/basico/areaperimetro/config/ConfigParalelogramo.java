package matematica.basico.areaperimetro.config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;

public class ConfigParalelogramo implements Config
{
	String base,altura, lateral;
	boolean area;

	public ConfigParalelogramo(String base, String altura,String lateral, boolean area)
	{
		this.base = base;
		this.altura = altura;
		this.lateral=lateral;
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

		paralelogramo.addPoint(125, 620);
		paralelogramo.addPoint(975, 620);
		paralelogramo.addPoint(1175, 45);
		paralelogramo.addPoint(325, 45);

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
				Graphics.setLineTracejada(g2, 125, 655, 975, 655, true, true);
				Graphics.addLabel(g2, base, 550, 660, Alinhamento.TopCenter);
			}
			else
				Graphics.addLabel(g2, base, 550, 625, Alinhamento.TopCenter);
		}

		if(!altura.equals(""))
		{
			Graphics.setLineTracejada(g2, 325, 45, 325, 620, false, false);
			Graphics.addAngleReto(g2, 325, 570, 50);
			Graphics.addLabel(g2, altura, 325, 320, Alinhamento.MiddleLeft);
		}

		if(!lateral.equals(""))
		{
//			Graphics.setLineTracejada(g2, 90, 610, 290, 35, true, true);
			Graphics.addLabel(g2, lateral, 235, 290, Alinhamento.MiddleRight);
		}
		
		return image;
	}


}
