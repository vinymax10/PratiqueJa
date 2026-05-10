package matematica.basico.areaperimetro.config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;

//Retangulo com quadrado dentro
public class ConfigRetangulo3 implements Config
{
	String diagonal,sobra;
	boolean area;

	public ConfigRetangulo3(String diagonal, String sobra, boolean area)
	{
		this.diagonal = diagonal;
		this.sobra = sobra;
		this.area=area;
	}

	public BufferedImage criarImagem(int index)
	{
		int width=1250;
		int height=750;
		int raioAngulo=50;
		int ladoRetangulo=600;
		int baseRetangulo=950;

		int deslocamentoTracejada=40;
		
		ParCor parCor = ParCor.parCor(index-1);
		BufferedImage image = new BufferedImage((int) width,(int) height, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);

		if(area)
		{
			g2.setColor(Color.decode(parCor.getCorFraca()));
			g2.fill(new Rectangle2D.Double(25, 20, baseRetangulo, ladoRetangulo));
		}

		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));
		Rectangle2D retangulo = new Rectangle2D.Double(25, 20, baseRetangulo, ladoRetangulo);
		g2.draw(retangulo);
		
		Rectangle2D quadrado = new Rectangle2D.Double(25, 20, ladoRetangulo, ladoRetangulo);
		g2.draw(quadrado);
		
		Line2D diagonal = new Line2D.Double(quadrado.getMinX()+4, quadrado.getMaxY()-4, 
		quadrado.getMaxX()-4, quadrado.getMinY()+4);
		g2.draw(diagonal);
		
		Graphics.addAngleReto(g2, (int)retangulo.getMinX(), 
		(int)retangulo.getMinY(), raioAngulo);
		
		Graphics.addAngleReto(g2, (int)(retangulo.getMaxX()-raioAngulo),
		(int)(retangulo.getMaxY()-raioAngulo), raioAngulo);
		
		Graphics.addAngleReto(g2, (int)(quadrado.getMaxX()-raioAngulo),
		(int)(quadrado.getMaxY()-raioAngulo), raioAngulo);

		Graphics.setLineTracejada(g2, (int)retangulo.getMaxX()+deslocamentoTracejada, (int)retangulo.getMaxY(), 
		(int)retangulo.getMaxX()+deslocamentoTracejada, (int)retangulo.getMinY(), true, true);
		
		Graphics.setLineTracejada(g2, (int)quadrado.getMinX(), (int)quadrado.getMaxY()+deslocamentoTracejada, 
		(int)quadrado.getMaxX(), (int)quadrado.getMaxY()+deslocamentoTracejada, true, true);
		
		Graphics.setLineTracejada(g2, (int)quadrado.getMaxX(), (int)quadrado.getMaxY()+deslocamentoTracejada, 
		(int)retangulo.getMaxX(), (int)quadrado.getMaxY()+deslocamentoTracejada, true, true);

		Graphics.addLabel(g2, "l", (int)retangulo.getMaxX()+deslocamentoTracejada, 
		(int)retangulo.getCenterY(), Alinhamento.MiddleLeft);
		
		Graphics.addLabel(g2, "l", (int)quadrado.getCenterX(), 
		(int)quadrado.getMaxY()+deslocamentoTracejada, Alinhamento.TopCenter);
		
		Graphics.addLabel(g2, sobra, (int)(quadrado.getMaxX()+((retangulo.getWidth() - quadrado.getWidth())/2)), 
		(int)quadrado.getMaxY()+deslocamentoTracejada, Alinhamento.TopCenter);
		
		Graphics.addLabel(g2, this.diagonal, (int)quadrado.getCenterX(), (int)quadrado.getCenterY(), 
		Alinhamento.BottomCenter);
		
		return image;
	}


}
