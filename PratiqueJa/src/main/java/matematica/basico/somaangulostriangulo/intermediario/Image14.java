package matematica.basico.somaangulostriangulo.intermediario;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import infra.Graphics;
import matematica.ParCor;
import matematica.basico.somaangulostriangulo.ResolucaoSomaAngulosTriangulo1;
import modelo.matematica.Conta;


public class Image14 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image14(int indice)
	{
		super(indice);

		int size = 100;
		int b = 20 + rand.nextInt(10);
		int a = 20 + rand.nextInt(10);

		int c = b + a;

		String strA = a + "°";
		String strB = b + "°";
		String strC = c + "°";

		resultadoCorreto = "" + b + "°";
		resolucaoLatex = ResolucaoSomaAngulosTriangulo1.resolucaoSuplemento13(a, b, c);

		textLatex = strA + "-" + strB + "-" + strC;

		ParCor parCor = ParCor.parCor(indice -1);
		BufferedImage image = new BufferedImage((int) (2.5 * size), (size + 50), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);

		int raio = 50;
		Graphics.setAngleSemBorda(g2, 5 - raio / 2, 110 - raio / 2, raio, 0, 26, ParCor.parCor(indice),
		ParCor.parCor(indice -1));
//		-------------------

		Graphics.setAngleSemBorda(g2, 125 - raio / 2, 110 - raio / 2, raio, 0, 51, ParCor.parCor(indice + 1),
		ParCor.parCor(indice -1));
//		
		Graphics.setAngleSemBorda(g2, 205 - raio / 2, 10 - raio / 2, raio, 207, 24, ParCor.parCor(indice + 2),
		ParCor.parCor(indice -1));

		Polygon triangulo = new Polygon();

		triangulo.addPoint(5, 110);
		triangulo.addPoint(125, 110);
		triangulo.addPoint(205, 10);

		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(2));
		g2.draw(triangulo);

		Graphics.arrow(g2, 125, 110, 205, 110, false, true);

		TeXFormula formula;
		TeXIcon icon;

		formula = new TeXFormula(strA);
		icon = formula.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(18).build();
		icon.setInsets(new Insets(0, 0, 0, 0));
		icon.paintIcon(null, g2, 173 - icon.getIconWidth() / 2, 27);
//		
		formula = new TeXFormula("x");
		icon = formula.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(18).build();
		icon.setInsets(new Insets(0, 0, 0, 0));
		icon.paintIcon(null, g2, 30, 110 - icon.getIconHeight());

		formula = new TeXFormula(strC);
		icon = formula.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(18).build();
		icon.setInsets(new Insets(0, 0, 0, 0));
		icon.paintIcon(null, g2, 147, 105 - icon.getIconHeight());

		baos = Graphics.salvar(image, false, "");
		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image14(1);
	}
}
