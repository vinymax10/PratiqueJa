package matematica.basico.areaperimetro.config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Alinhamento;
import matematica.ConfigImagem;
import matematica.ParCor;

public class ConfigTrianguloRetangulo implements ConfigImagem
{
	String base,altura,hipotenusa;
	boolean area;

	public ConfigTrianguloRetangulo(String base, String altura,String hipotenusa, boolean area)
	{
		this.base = base;
		this.altura = altura;
		this.hipotenusa=hipotenusa;
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

		Polygon paralelogramo = new Polygon();

		paralelogramo.addPoint(175, 20);
		paralelogramo.addPoint(175, 620);
		paralelogramo.addPoint(1125, 620);

		if(area)
		{
			g2.setColor(Color.decode(parCor.getCorFraca()));
			g2.fill(paralelogramo);
		}

		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));
		g2.draw(paralelogramo);

		Graphics.addAngleReto(g2, 175, 570, 50);
		
		Graphics.addLabel(g2, base, 637, 620, Alinhamento.TopCenter);
		Graphics.addLabel(g2, altura, 175, 320, Alinhamento.MiddleRight);
		
		if(!hipotenusa.equals(""))
		{
			Graphics.addLabel(g2, this.hipotenusa, 580, 250, Alinhamento.MiddleLeft);
		}
		
		return image;
	}


}
