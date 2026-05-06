package matematica.basico.areaperimetro.config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;


public class ConfigLozango implements Config
{
	String diagonalMaior,diagonalMenor,base,altura,lado;
	boolean area;

	public ConfigLozango(String diagonalMaior, String diagonalMenor,
	String base, String altura, String lado, boolean area)
	{
		super();
		this.diagonalMaior = diagonalMaior;
		this.diagonalMenor = diagonalMenor;
		this.base = base;
		this.altura = altura;
		this.lado = lado;
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

		paralelogramo.addPoint(200, 287);
		paralelogramo.addPoint(662, 520);
		paralelogramo.addPoint(1125, 287);
		paralelogramo.addPoint(662, 20);

		if(area)
		{
			g2.setColor(Color.decode(parCor.getCorFraca()));
			g2.fill(paralelogramo);
		}

		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));
		g2.draw(paralelogramo);

		Graphics.setLineTracejada(g2, 662, 520, 662, 20, false, false);
		Graphics.setLineTracejada(g2, 200, 287, 1125, 287, false, false);
		Graphics.addAngleReto(g2, 662, 237, 50);

		
		if(!diagonalMaior.equals(""))
		{
			Graphics.addLabel(g2, diagonalMaior, 662, 550, Alinhamento.TopCenter);
			Graphics.setLineTracejada(g2, 200, 550, 1125, 550, true, true);
			Graphics.setLineTracejada(g2, 1125, 550, 1125, 287, false, false);
			Graphics.setLineTracejada(g2, 200, 550, 200, 287, false, false);
		}
		
		if(!diagonalMenor.equals(""))
		{
			Graphics.addLabel(g2, diagonalMenor, 170, 287, Alinhamento.MiddleRight);
			Graphics.setLineTracejada(g2, 170, 20, 170, 520, true, true);
			Graphics.setLineTracejada(g2, 170, 520, 662, 520, false, false);
			Graphics.setLineTracejada(g2, 170, 20, 662, 20, false, false);
		}
		
		if(!base.equals(""))
			Graphics.addLabel(g2, base, 843, 287, Alinhamento.TopCenter);
		
		if(!altura.equals(""))
			Graphics.addLabel(g2, altura, 662, 170, Alinhamento.MiddleRight);
		
		if(!lado.equals(""))
			Graphics.addLabel(g2, lado, 870, 100, Alinhamento.MiddleLeft);

		return image;
	}


}
