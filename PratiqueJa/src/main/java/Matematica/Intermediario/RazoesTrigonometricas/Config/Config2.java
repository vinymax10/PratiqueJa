package Matematica.Intermediario.RazoesTrigonometricas.Config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Alinhamento;
import Matematica.ParCor;
import Matematica.Intermediario.RazoesTrigonometricas.Dados.Dados;


public class Config2  extends Config
{
	public Config2(Dados dados)
	{
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
//		g2.setColor(Color.red);
//		g2.fillRect(0, 0, width, height);
		
		int raio = 200;

		if(angleAltura!=null&&!angleAltura.equals(""))
			Graphics.setAngleSemBorda(g2, 25-raio/2, 620-raio/2,
			raio, 0, 32,	ParCor.parCor(index + 1), parCor);
		
		
		if(angleBase!=null&&!angleBase.equals(""))
			Graphics.setAngleSemBorda(g2, 1000-raio/2, 25-raio/2,
			raio, 270, -58,	ParCor.parCor(index + 2), parCor);
		
		Polygon triangulo = new Polygon();

		triangulo.addPoint(25, 620);
		triangulo.addPoint(1000, 620);
		triangulo.addPoint(1000, 25);

		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));
		g2.draw(triangulo);
		
		Graphics.addAngleReto(g2, 950, 570, 50);

		if(angleAltura!=null&&!angleAltura.equals(""))
			Graphics.addLabel(g2, angleAltura, 25+raio/2,
			620, Alinhamento.BottomLeft);
		
		if(angleBase!=null&&!angleBase.equals(""))
			Graphics.addLabel(g2, angleBase, 1000-50,
			25+raio/2, Alinhamento.TopRight);
		
		if(!base.equals(""))
			Graphics.addLabel(g2, base, (int) triangulo.getBounds2D().getCenterX(),
			(int) triangulo.getBounds2D().getMaxY(), Alinhamento.TopCenter);

		if(!altura.equals(""))
			Graphics.addLabel(g2, altura, (int) triangulo.getBounds2D().getMaxX(),
			(int) triangulo.getBounds2D().getCenterY() , Alinhamento.MiddleLeft);

		if(!hipotenusa.equals(""))
			Graphics.addLabel(g2, hipotenusa, (int) triangulo.getBounds2D().getCenterX(),
			(int) triangulo.getBounds2D().getCenterY(), Alinhamento.BottomRight);
		
		return image;
	}


}
