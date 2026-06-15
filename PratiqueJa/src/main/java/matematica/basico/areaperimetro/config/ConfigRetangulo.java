package matematica.basico.areaperimetro.config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Alinhamento;
import matematica.ConfigImagem;
import matematica.ParCor;

//retângulo
public class ConfigRetangulo implements ConfigImagem
{
	String base,altura;
	boolean area;

	public ConfigRetangulo(String base, String altura, boolean area)
	{
		this.base = base;
		this.altura = altura;
		this.area=area;
	}

	public BufferedImage criarImagem()
	{
		int width = IMG_W;
		int height = IMG_H;
		int raioAngulo=50;
		int ladoRetangulo=600;
		int baseRetangulo=850;

		int deslocamentoTracejada=40;
		
		ParCor parCor = ParCor.parCorAleatorio();
		BufferedImage image = new BufferedImage((int) width,(int) height, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);

		if(area)
		{
			g2.setColor(Color.decode(parCor.getCorFraca()));
			g2.fill(new Rectangle2D.Double(125, 25, baseRetangulo, ladoRetangulo));
		}

		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));
		Rectangle2D retangulo = new Rectangle2D.Double(125, 25, baseRetangulo, ladoRetangulo);
		g2.draw(retangulo);
		
		Graphics.addAngleReto(g2, (int)retangulo.getMinX(), 
		(int)retangulo.getMinY(), raioAngulo);
		
		Graphics.addAngleReto(g2, (int)(retangulo.getMaxX()-raioAngulo),
		(int)(retangulo.getMaxY()-raioAngulo), raioAngulo);

		Graphics.setLineTracejada(g2, (int)retangulo.getMaxX()+deslocamentoTracejada, (int)retangulo.getMaxY(), 
		(int)retangulo.getMaxX()+deslocamentoTracejada, (int)retangulo.getMinY(), true, true);
		
		Graphics.setLineTracejada(g2, (int)retangulo.getMinX(), (int)retangulo.getMaxY()+deslocamentoTracejada, 
		(int)retangulo.getMaxX(), (int)retangulo.getMaxY()+deslocamentoTracejada, true, true);

		Graphics.addLabel(g2, altura, (int)retangulo.getMaxX()+deslocamentoTracejada, 
		(int)retangulo.getCenterY(), Alinhamento.MiddleLeft);
		
		Graphics.addLabel(g2, base, (int)retangulo.getCenterX(), 
		(int)retangulo.getMaxY()+deslocamentoTracejada, Alinhamento.TopCenter);
		
		return image;
	}


}
