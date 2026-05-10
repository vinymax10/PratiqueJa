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


public class ConfigSemelhancaTriangulos1 extends ConfigSemelhancaAngulos
{
	public ConfigSemelhancaTriangulos1(ConfigValores configValores)
	{
		super(configValores);
	}

	public BufferedImage criarImagem(int index)
	{
		int width=1250;
		int height=750;

		ParCor parCor = ParCor.parCor(index-1);
		BufferedImage image = new BufferedImage((int) width,(int) height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);
		
		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));

		Graphics.arrow(g2, 200, 100, 1050, 100, true, true);

		Graphics.arrow(g2, 200, 300, 1050, 300, true, true);

		Graphics.arrow(g2, 200, 600, 1050, 600, true, true);

		Graphics.arrow(g2, 600, 50, 300, 700, true, true);

		Graphics.arrow(g2, 750, 50, 850, 700, true, true);

		Graphics.addLabel(g2, a.nome, 500 , 200, Alinhamento.MiddleRight);

		Graphics.addLabel(g2, b.nome, 800, 200 , Alinhamento.MiddleLeft);

		Graphics.addLabel(g2, c.nome, 400 , 425 , Alinhamento.MiddleRight);

		Graphics.addLabel(g2, d.nome, 810, 425 , Alinhamento.MiddleLeft);

		Graphics.addLabel(g2, "r", 1050 , 100 , Alinhamento.BottomRight);
		Graphics.addLabel(g2, "s", 1050 , 300 , Alinhamento.BottomRight);

		Graphics.addLabel(g2, "t", 1050 , 600  , Alinhamento.BottomRight);

		Graphics.addLabel(g2, "r \\parallel s \\parallel t", 1250 , 725 , Alinhamento.BottomRight);

		return image;
	}

}
