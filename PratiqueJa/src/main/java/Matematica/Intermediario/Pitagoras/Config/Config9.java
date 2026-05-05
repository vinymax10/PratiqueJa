package Matematica.Intermediario.Pitagoras.Config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.ParCor;
import Matematica.Intermediario.Pitagoras.Dados.Dados;

public class Config9 extends Config
{
	public Config9(Dados dados) {
		super(dados);
	}

	public BufferedImage criarImagem(int index)
	{
		int width=1250;
		int height=750;

		ParCor parCor = ParCor.parCor(index-1);
		BufferedImage image = new BufferedImage((int) width,(int) height, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);
		
		int Dx,Dy;
		int DxMax=975;
		int dYMax=575;
		if(dados.porcent>=((double)dYMax/DxMax))
		{
			Dy=dYMax;
			Dx=(int)((double)Dy/dados.porcent);
		}
		else
		{
			Dx=DxMax;
			Dy=(int)(dados.porcent*Dx);
		}
		
		int centroX=Math.max(250+(Dx/2), 625);
		int xIni=centroX-(Dx/2);
		int xFim=centroX+(Dx/2);
		int yIni=320-(Dy/2);
		int yFim=320+(Dy/2);
		
		a=new Point(xIni, yFim);
		c=new Point(xFim, yFim);
		b=new Point(xIni, yIni);
		d=new Point(xFim, yIni);

		Polygon retangulo = new Polygon();

		retangulo.addPoint(a.x,a.y);
		retangulo.addPoint(b.x, b.y);
		retangulo.addPoint(d.x, d.y);
		retangulo.addPoint(c.x, c.y);

		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));
		g2.draw(retangulo);
		
		Graphics.addAngleReto(g2, xIni, yFim-50, 50);
		Graphics.addAngleReto(g2, xFim-50, yIni, 50);
		Graphics.arrow(g2, xIni+5, yIni+5, xFim-5, yFim-5, false,false);

		Graphics.addLabel(g2, dados.hipotenusa.str, a, b,c);

		Graphics.addLabel(g2, dados.base.str, b,a,c);

		Graphics.addLabel(g2, dados.altura.str, c, a,b);
		
		return image;
	}
	
	public Dados getDados()
	{
		return dados;
	}

	public void setDados(Dados dados)
	{
		this.dados = dados;
	}

}
