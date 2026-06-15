package matematica.intermediario.semelhancatriangulos.nivel1package;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;
import matematica.intermediario.semelhancatriangulos.ConfigSemelhancaAngulos;
import matematica.intermediario.semelhancatriangulos.ConfigValores;


public class ConfigSemelhancaTriangulos3 extends ConfigSemelhancaAngulos
{
	public ConfigSemelhancaTriangulos3(ConfigValores configValores)
	{
		super(configValores);
	}

	public BufferedImage criarImagem()
	{
		int width = IMG_W;
		int height = IMG_H;

		ParCor parCor = ParCor.parCorAleatorio();
		BufferedImage image = new BufferedImage((int) width,(int) height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);
		
		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));

		Graphics.arrow(g2, 200, 50, 75, 700, true, true);

		Graphics.arrow(g2, 575, 50, 450, 700, true, true);

		Graphics.arrow(g2, 1050, 50, 925, 700, true, true);

		Graphics.arrow(g2, 50, 150, 1200, 300, true, true);

		Graphics.arrow(g2, 50, 550, 1200, 500, true, true);

		Graphics.addLabel(g2, a.nome, 345 , 185 , Alinhamento.BottomCenter);

		Graphics.addLabel(g2, b.nome,  260 , 540, Alinhamento.TopCenter);

		Graphics.addLabel(g2, c.nome, 765 , 230 , Alinhamento.BottomCenter);

		Graphics.addLabel(g2, d.nome, 685 , 525, Alinhamento.TopCenter);

		Graphics.addLabel(g2, "r", 200 , 0, Alinhamento.TopRight);
		
		Graphics.addLabel(g2, "s", 575 , 0, Alinhamento.TopRight);

		Graphics.addLabel(g2, "t", 1050 , 0, Alinhamento.TopRight);

		Graphics.addLabel(g2, "r \\parallel s \\parallel t", 1250 , 725 , Alinhamento.BottomRight,80);

		return image;
	}

}
