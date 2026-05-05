package Matematica.Intermediario.SomaAngulosTriangulo.Intermediario;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import Auxiliar.Graphics;
import Matematica.ParCor;
import Matematica.Intermediario.SomaAngulosTriangulo.ResolucaoSomaAngulosTriangulo1;
import Modelo.Matematica.Conta;


public class Image4 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image4(int index)
	{
		super(index);

		int size = 100;
		int a = 50 + rand.nextInt(30);

		int x = 90 + a;

		String strA = a + "°";

		resultadoCorreto = "" + x + "°";
		resolucaoLatex = ResolucaoSomaAngulosTriangulo1.resolucaoRetoSuplemento4(a, x);

		textLatex = strA;

		ParCor parCor = ParCor.parCor(index-1);
		BufferedImage image = new BufferedImage((int) (2.5 * size), (size + 50), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);

		int minX = 10, minY = 10;
		int maxX = minX + (int) (1.5 * size), maxY = minY + (int) (size);

		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(2));

		int raio = 50;
		Graphics.setAngleSemBorda(g2, minX - raio / 2, minY - raio / 2, raio, -90, 57, ParCor.parCor(index),
		ParCor.parCor(index-1));
//		-------------------

		Graphics.setAngleSemBorda(g2, maxX - raio / 2, maxY - raio / 2, raio, 0, 147, ParCor.parCor(index +1),
		ParCor.parCor(index-1));

		Graphics.arrow(g2, minX, maxY, maxX + 50, maxY, false, true);

		Line2D line2 = new Line2D.Double(minX, minY, minX, maxY);
		g2.draw(line2);

		Line2D line3 = new Line2D.Double(minX, minY, maxX, maxY);
		g2.draw(line3);

		Graphics.addAngleReto(g2, minX, maxY - (size / 10), 10);

		TeXFormula formula = new TeXFormula(strA);
		TeXIcon icon = formula.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(18).build();
		icon.setInsets(new Insets(0, 0, 0, 0));
		icon.paintIcon(null, g2, minX + 5, minY + raio / 2);

		formula = new TeXFormula("x");
		icon = formula.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(18).build();
		icon.setInsets(new Insets(2, 2, 2, 2));
		icon.paintIcon(null, g2, maxX + raio / 2 - icon.getIconWidth(), maxY - raio / 2 - (icon.getIconHeight()));

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image4(1);
	}
}
