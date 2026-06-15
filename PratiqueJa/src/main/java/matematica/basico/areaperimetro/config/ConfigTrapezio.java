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

public class ConfigTrapezio implements ConfigImagem
{
	String baseMaior,baseMenor,altura,lateralEsq,lateralDir;
	boolean area;

	public ConfigTrapezio(String baseMaior, String baseMenor, String altura, 
	String lateralEsq, String lateralDir, boolean area)
	{
		this.baseMaior = baseMaior;
		this.baseMenor = baseMenor;
		this.altura = altura;
		this.lateralEsq=lateralEsq;
		this.lateralDir=lateralDir;
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

		paralelogramo.addPoint(75, 620);
		paralelogramo.addPoint(1150, 620);
		paralelogramo.addPoint(900, 100);
		paralelogramo.addPoint(325, 100);
		
		if(area)
		{
			g2.setColor(Color.decode(parCor.getCorFraca()));
			g2.fill(paralelogramo);
		}
		
		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));
		g2.draw(paralelogramo);

		if(!baseMaior.equals(""))
		{
			if(!altura.equals(""))
			{
				Graphics.setLineTracejada(g2, 75, 655, 1150, 655, true, true);
				Graphics.addLabel(g2, baseMaior,  600, 660, Alinhamento.TopCenter);
			}
			else
				Graphics.addLabel(g2, baseMaior,  600, 625, Alinhamento.TopCenter);
		}
		
		if(!altura.equals(""))
		{
			Graphics.setLineTracejada(g2, 325, 100, 325, 620, false, false);
			Graphics.addAngleReto(g2, 325, 570, 50);
			Graphics.addLabel(g2, altura, 325, 370, Alinhamento.MiddleLeft);
		}
		
		if(!baseMenor.equals(""))
			Graphics.addLabel(g2, baseMenor, 600, 100, Alinhamento.BottomCenter);
		
		if(!lateralEsq.equals(""))
			Graphics.addLabel(g2, lateralEsq, 220, 310, Alinhamento.MiddleRight);
		
		if(!lateralDir.equals(""))
			Graphics.addLabel(g2, lateralDir, 1010, 310, Alinhamento.MiddleLeft);
		
		return image;
	}


}
