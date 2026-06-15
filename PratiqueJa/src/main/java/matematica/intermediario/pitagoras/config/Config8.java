package matematica.intermediario.pitagoras.config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;
import matematica.intermediario.pitagoras.dados.Dados;

public class Config8 extends Config
{
	public Config8(Dados dados) {
		super(dados);
	}

	public BufferedImage criarImagem()
	{
		int width = IMG_W;
		int height = IMG_H;
		int deslocamentoTracejada=40;

		ParCor parCor = ParCor.parCorAleatorio();
		BufferedImage image = new BufferedImage((int) width,(int) height, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);
		
		int xIni=25;
		int xFim=1200;
		int yIni=175;
		int yFim=725;
		
		int altura=yFim-yIni;
		int base=(int) (altura/dados.porcent);
		
		int centroX=xFim-base;
		int centroY=yIni+(yFim-yIni)/2;

		a=new Point(xIni, yIni);
		c=new Point(xFim, yIni);
		b=new Point(centroX, yFim);
		
		Polygon triangulo = new Polygon();

		triangulo.addPoint(a.x,a.y);
		triangulo.addPoint(b.x, b.y);
		triangulo.addPoint(c.x, c.y);

		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));
		g2.draw(triangulo);
		
		Graphics.setLineTracejada(g2, centroX, yIni, centroX, yFim, false, false);
		Graphics.addAngleReto(g2, centroX, yIni, 50);
		if(centroX>(xIni+(xFim-xIni)/2))
			Graphics.addLabel(g2, dados.altura.str, centroX, centroY, Alinhamento.MiddleRight);
		else
			Graphics.addLabel(g2, dados.altura.str, centroX, centroY, Alinhamento.MiddleLeft);
		
		Graphics.addLabel(g2, dados.hipotenusa.str, a, b,c);

		Graphics.setLineTracejada(g2, centroX, yIni-deslocamentoTracejada, 
		xFim, yIni-deslocamentoTracejada, true, true);
		Graphics.addLabel(g2, dados.base.str, centroX+(xFim-centroX)/2, yIni-deslocamentoTracejada, Alinhamento.BottomCenter);

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
