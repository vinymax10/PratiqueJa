package matematica.intermediario.razoestrigonometricas.config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;
import matematica.intermediario.razoestrigonometricas.dados.Dados;

public class Config3 extends Config
{
	public Config3(Dados dados)
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
		
		int raio = 200;

		if(angleAltura!=null&&!angleAltura.equals(""))
			Graphics.setAngleSemBorda(g2, 1200-raio/2, 125-raio/2,
			raio, 180, 32,	ParCor.parCor(index + 1), parCor);
		
		if(angleBase!=null&&!angleBase.equals(""))
			Graphics.setAngleSemBorda(g2, 225-raio/2, 720-raio/2,
			raio, 90, -58,	ParCor.parCor(index + 2), parCor);
		
		Polygon triangulo = new Polygon();

		triangulo.addPoint(225, 125);
		triangulo.addPoint(225, 720);
		triangulo.addPoint(1200, 125);

		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));
		g2.draw(triangulo);
		
		Graphics.addAngleReto(g2, 225, 125, 50);

		if(angleAltura!=null&&!angleAltura.equals(""))
			Graphics.addLabel(g2, angleAltura, 1200-raio/2,
			120, Alinhamento.TopRight);
		
		if(angleBase!=null&&!angleBase.equals(""))
			Graphics.addLabel(g2, angleBase, 235,
			720-raio/2, Alinhamento.BottomLeft);
		
		if(!base.equals(""))
			Graphics.addLabel(g2, base, (int) triangulo.getBounds2D().getCenterX(),
			(int) triangulo.getBounds2D().getMinY(), Alinhamento.BottomCenter);

		if(!altura.equals(""))
			Graphics.addLabel(g2, altura, (int) triangulo.getBounds2D().getMinX(),
			(int) triangulo.getBounds2D().getCenterY() , Alinhamento.MiddleRight);

		if(!hipotenusa.equals(""))
			Graphics.addLabel(g2, hipotenusa, (int) triangulo.getBounds2D().getCenterX(),
			(int) triangulo.getBounds2D().getCenterY(), Alinhamento.TopLeft);
		
		return image;
	}


}
