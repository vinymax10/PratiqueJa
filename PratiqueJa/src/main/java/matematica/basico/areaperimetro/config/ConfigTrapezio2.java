package matematica.basico.areaperimetro.config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;

public class ConfigTrapezio2 implements Config
{
	String baseMaior,baseMenor,altura,lateralDir;
	boolean area;

	public ConfigTrapezio2(String baseMaior, String baseMenor, String altura, String lateralDir, boolean area)
	{
		this.baseMaior = baseMaior;
		this.baseMenor = baseMenor;
		this.altura = altura;
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

		paralelogramo.addPoint(200, 620);
		paralelogramo.addPoint(1200, 620);
		paralelogramo.addPoint(850, 100);
		paralelogramo.addPoint(200, 100);

		if(area)
		{
			g2.setColor(Color.decode(parCor.getCorFraca()));
			g2.fill(paralelogramo);
		}
		
		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));
		g2.draw(paralelogramo);

		Graphics.addAngleReto(g2, 200, 570, 50);

		if(!baseMaior.equals(""))
		{
			Graphics.addLabel(g2, baseMaior,  700, 625, Alinhamento.TopCenter);
		}
		
//		if(!altura.equals(""))
//		{
//			Graphics.setLineTracejada(g2, 750, 100, 750, 620, false, false);
//			Graphics.addAngleReto(g2, 750, 570, 50);
//			Graphics.addLabel(g2, altura, 750, 370, Alinhamento.MiddleRight);
//		}
		
		if(!baseMenor.equals(""))
			Graphics.addLabel(g2, baseMenor, 525, 100, Alinhamento.BottomCenter);
		
		if(!altura.equals(""))
			Graphics.addLabel(g2, altura, 200, 360, Alinhamento.MiddleRight);
		
		if(!lateralDir.equals(""))
			Graphics.addLabel(g2, lateralDir, 1010, 310, Alinhamento.MiddleLeft);
			
		return image;
	}


}
