package matematica.intermediario.pitagoras.config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.ParCor;
import matematica.intermediario.pitagoras.dados.Dados;

public class Config10 extends Config
{

	public Config10(Dados dados) {
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
			Dy=575;
			Dx=(int)((double)Dy/dados.porcent);
		}
		else
		{
			Dx=975;
			Dy=(int)(dados.porcent*Dx);
		}
		
		int centroX=Math.max(250+(Dx/2), 625);
		int xIni=centroX-(Dx/2);
		int xFim=centroX+(Dx/2);
		int yIni=425-(Dy/2);
		int yFim=425+(Dy/2);
		
		a=new Point(xIni, yIni);
		c=new Point(xFim, yIni);
		b=new Point(xIni, yFim);
		d=new Point(xFim, yFim);

		Polygon retangulo = new Polygon();

		retangulo.addPoint(a.x,a.y);
		retangulo.addPoint(b.x, b.y);
		retangulo.addPoint(d.x, d.y);
		retangulo.addPoint(c.x, c.y);

		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));
		g2.draw(retangulo);
		
		Graphics.addAngleReto(g2, xIni, yIni, 50);
		Graphics.addAngleReto(g2, xFim-50, yFim-50, 50);

		Graphics.arrow(g2, xIni+5, yFim-5, xFim-5, yIni+5, false,false);

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
