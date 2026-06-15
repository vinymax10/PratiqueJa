package matematica.basico.somaangulostriangulo.nivel3package;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;
import matematica.basico.somaangulostriangulo.Angulo;
import matematica.basico.somaangulostriangulo.Config;


public class ConfigTriangulo8 extends Config
{
	public Angulo a, b, c;

	public ConfigTriangulo8(int a, int b, int c)
	{
		this.a = new Angulo(this, a, false, a + "°");
		this.b = new Angulo(this, b, false, b + "°");
		this.c = new Angulo(this, c, false, c + "°");
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

		if (a.mostrar)
			Graphics.setAngleSemBorda(g2, 50 - raio / 2, 50 - raio / 2, raio, -90, 50,
			ParCor.parCorAleatorio(), ParCor.parCorAleatorio());

		if (b.mostrar)
			Graphics.setAngleSemBorda(g2, 825 - raio / 2, 700 - raio / 2, raio, 140, 40,
			ParCor.parCorAleatorio(), ParCor.parCorAleatorio());

		if (c.mostrar)
			Graphics.setAngleSemBorda(g2, 825 - raio / 2, 700 - raio / 2, raio, 0, 140,
			ParCor.parCorAleatorio(), ParCor.parCorAleatorio());

		Polygon triangulo = new Polygon();
		triangulo.addPoint(50, 700);
		triangulo.addPoint(825, 700);
		triangulo.addPoint(50, 50);
		g2.draw(triangulo);
		
		Graphics.addAngleReto(g2, 50, 700 - raioAngulo, raioAngulo);

		Graphics.arrow(g2, 825, 700, 1200, 700, false, true);

		if (a.mostrar)
			Graphics.addLabel(g2, a.nome, 50, 50 + raio / 2, Alinhamento.TopLeft);

		if (b.mostrar)
			Graphics.addLabel(g2, b.nome, 825 - raio / 2, 700, Alinhamento.BottomRight);

		if (c.mostrar)
			Graphics.addLabel(g2, c.nome, 825, 700 - raio / 2, Alinhamento.BottomLeft);

		return image;
	}

	public String getTextLatex()
	{
		return a.nome + "-" + b.nome;
	}

	@Override
	public String toString()
	{
		return "ConfigTriangulo8:\n" + (a != null ? "a: " + a + "\n" : "") + (b != null ? "b: " + b + "\n" : "")
		+ (c != null ? "c: " + c : "");
	}

}
