package matematica.basico.divisaonatural;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;

public class ConfigDivisaoNatural
{
	String dividendo,divisor,quociente;
	
	public ConfigDivisaoNatural(String dividendo, String divisor, String quociente)
	{
		super();
		this.dividendo = dividendo;
		this.divisor = divisor;
		this.quociente = quociente;
	}

	public BufferedImage criarImagem()
	{
		int size = 100;
		
		int width=Graphics.widthLabel(dividendo);
		int height=Graphics.HeightLabel(dividendo);
		
		ParCor parCor = ParCor.parCorAleatorio();
		BufferedImage image = new BufferedImage((int) (300), (Math.max(size, height) + 50), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);

		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(2));
		
		Line2D line = new Line2D.Double(width+5, 0, width+5, 25);
		g2.draw(line);
		
		line = new Line2D.Double(width+5, 25, width+105, 25);
		g2.draw(line);

		Graphics.addLabel(g2, dividendo, width, 0, Alinhamento.TopRight);
		
		Graphics.addLabel(g2, divisor, width+10, 0, Alinhamento.TopLeft);
	
		Graphics.addLabel(g2, quociente, width+10, 30, Alinhamento.TopLeft);

		return image;
	}
}
