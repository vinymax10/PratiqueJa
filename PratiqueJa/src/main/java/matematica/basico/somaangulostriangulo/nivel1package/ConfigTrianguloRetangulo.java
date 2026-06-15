package matematica.basico.somaangulostriangulo.nivel1package;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;
import matematica.basico.somaangulostriangulo.Angulo;
import matematica.basico.somaangulostriangulo.Config;


public class ConfigTrianguloRetangulo extends Config
{
	public Angulo a, b;

	public ConfigTrianguloRetangulo(int a, int b)
	{
		this.a = new Angulo(this, a, false, a + "°");
		this.b = new Angulo(this, b, false, b + "°");
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

		int raioAngulo=50;
		int raio = 200;
		int minX = 50, minY = 50;
		int maxX = 1200, maxY = 700;

		if (a.mostrar)
			Graphics.setAngleSemBorda(g2, minX - raio / 2, minY - raio / 2, raio, -90, 57,
			ParCor.parCorAleatorio(), ParCor.parCorAleatorio());

		if (b.mostrar)
			Graphics.setAngleSemBorda(g2, maxX - raio / 2, maxY - raio / 2, raio, 147, 33,
			ParCor.parCorAleatorio(), ParCor.parCorAleatorio());

		Line2D line1 = new Line2D.Double(minX, maxY, maxX, maxY);
		g2.draw(line1);

		Line2D line2 = new Line2D.Double(minX, minY, minX, maxY);
		g2.draw(line2);

		Line2D line3 = new Line2D.Double(minX, minY, maxX, maxY);
		g2.draw(line3);

		Graphics.addAngleReto(g2, minX, maxY - raioAngulo, raioAngulo);

		if (a.mostrar)
			Graphics.addLabel(g2, a.nome, minX + 5, minY + raio / 2, Alinhamento.TopLeft);

		if (b.mostrar)
			Graphics.addLabel(g2, b.nome, maxX - raio / 2, maxY, Alinhamento.BottomRight);

		return image;
	}

	public String getTextLatex()
	{
		return a.nome + "-" + b.nome;
	}

	@Override
	public String toString()
	{
		return "Config:\n" + (a != null ? "a: " + a + "\n" : "") + (b != null ? "b: " + b + "\n" : "");
	}

}
