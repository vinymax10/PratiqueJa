package matematica.basico.areaperimetro.config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;

public class ConfigTrianguloEquilatero implements Config
{
	String base;
	boolean area;

	public ConfigTrianguloEquilatero(String base, boolean area)
	{
		this.base = base;
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

		paralelogramo.addPoint(225, 620);
		paralelogramo.addPoint(911, 620);
		paralelogramo.addPoint(568, 25);

		if(area)
		{
			g2.setColor(Color.decode(parCor.getCorFraca()));
			g2.fill(paralelogramo);
		}
		
		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));
		g2.draw(paralelogramo);

		Graphics.ladoEquivalente(g2, 225, 620, 568, 25);

		Graphics.ladoEquivalente(g2, 911, 620, 568, 25);
		
		Graphics.ladoEquivalente(g2, 225, 620, 911, 620);

		if(!base.equals(""))
		{
			Graphics.setLineTracejada(g2, 225, 655, 911, 655, true, true);
			Graphics.addLabel(g2, base, 568, 660, Alinhamento.TopCenter);
		}
		
		return image;
	}


}
