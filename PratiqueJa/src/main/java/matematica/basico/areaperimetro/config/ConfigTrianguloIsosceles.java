package matematica.basico.areaperimetro.config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;

public class ConfigTrianguloIsosceles implements Config
{
	String base,altura,meiaBase,lateralEsq;
	boolean area;

	public ConfigTrianguloIsosceles(String base, String altura,String meiaBase, String lateralEsq, boolean area)
	{
		this.base = base;
		this.altura = altura;
		this.meiaBase=meiaBase;
		this.lateralEsq=lateralEsq;
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

		paralelogramo.addPoint(25, 620);
		paralelogramo.addPoint(1150, 620);
		paralelogramo.addPoint(587, 25);

		if(area)
		{
			g2.setColor(Color.decode(parCor.getCorFraca()));
			g2.fill(paralelogramo);
		}
		
		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));
		g2.draw(paralelogramo);

		Graphics.ladoEquivalente(g2, 25, 620, 587, 25);

		Graphics.ladoEquivalente(g2, 1150, 620, 587, 25);
		
		if(!base.equals(""))
		{
			if(!altura.equals(""))
			{
				Graphics.setLineTracejada(g2, 25, 655, 1150, 655, true, true);
				Graphics.addLabel(g2, base, 600, 660, Alinhamento.TopCenter);
			}
			else
			{
				Graphics.addLabel(g2, base, 600, 625, Alinhamento.TopCenter);
			}
		}
		
		if(!meiaBase.equals(""))
		{
			Graphics.setLineTracejada(g2, 25, 655, 587, 655, true, true);
			Graphics.addLabel(g2, meiaBase, 306, 660, Alinhamento.TopCenter);
		}
		
		if(!altura.equals(""))
		{
			Graphics.setLineTracejada(g2, 587, 25, 587, 620, false, false);
			Graphics.addAngleReto(g2, 587, 570, 50);
			Graphics.addLabel(g2, altura, 587, 320, Alinhamento.MiddleLeft);
		}
		
		if(!lateralEsq.equals(""))
		{
			Graphics.addLabel(g2, lateralEsq, 290, 270, Alinhamento.MiddleRight);
		}
		
		return image;
	}


}
