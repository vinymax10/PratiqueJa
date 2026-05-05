package Matematica.Intermediario.SemelhancaTriangulos.Nivel2Package;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Alinhamento;
import Matematica.ParCor;
import Matematica.Intermediario.SemelhancaTriangulos.ConfigSemelhancaAngulos;
import Matematica.Intermediario.SemelhancaTriangulos.ConfigValores;


public class ConfigSemelhancaTriangulos5 extends ConfigSemelhancaAngulos
{
	public ConfigSemelhancaTriangulos5(ConfigValores configValores)
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
		
		Line2D line1 = new Line2D.Double(300, 600, 600, 100);
		g2.draw(line1);

		Line2D line2 = new Line2D.Double(600, 100, 1200, 600);
		g2.draw(line2);

		Line2D line3 = new Line2D.Double(300, 600, 1200, 600);
		g2.draw(line3);

		Line2D line4 = new Line2D.Double(450, 350, 895, 350);
		g2.draw(line4);

		Graphics.addLabel(g2, a.nome, 515 , 190 , Alinhamento.MiddleRight);

		Graphics.addLabel(g2, b.nome,675 , 350, Alinhamento.TopCenter);

		Graphics.addLabel(g2, c.nome,  365 , 450 , Alinhamento.MiddleRight);

		Graphics.addLabel(g2, d.nome, 750 , 600, Alinhamento.TopCenter);

		Graphics.addLabel(g2, "A", 600 , 100 , Alinhamento.BottomCenter);

		Graphics.addLabel(g2, "B", 450 , 340 , Alinhamento.MiddleRight);

		Graphics.addLabel(g2, "C", 300 , 600, Alinhamento.TopCenter);

		Graphics.addLabel(g2, "D", 1200 , 600, Alinhamento.TopCenter);

		Graphics.addLabel(g2, "E", 910, 340 , Alinhamento.MiddleLeft);
//
		Graphics.addLabel(g2, "\\overline{BE} \\parallel \\overline{CD}", 1250 , 0, Alinhamento.TopRight,80);

		return image;
	}

}
