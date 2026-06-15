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


public class ConfigTriangulo4 extends Config
{
	public Angulo a, b, c;

	public ConfigTriangulo4(int a, int b, int c)
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

		int raio = 200;

		if (a.mostrar)
			Graphics.setAngleSemBorda(g2, 50 - raio / 2, 700 - raio / 2, raio, 0, 48,
			ParCor.parCorAleatorio(), ParCor.parCorAleatorio());

		if (b.mostrar)
			Graphics.setAngleSemBorda(g2, 625 - raio / 2, 50 - raio / 2, raio, -132, 84,
			ParCor.parCorAleatorio(), ParCor.parCorAleatorio());

		if (c.mostrar)
			Graphics.setAngleSemBorda(g2, 1200 - raio / 2, 700 - raio / 2, raio, 180, -48,
			ParCor.parCorAleatorio(), ParCor.parCorAleatorio());

		Polygon triangulo = new Polygon();
		triangulo.addPoint(50, 700);
		triangulo.addPoint(1200, 700);
		triangulo.addPoint(625, 50);
		g2.draw(triangulo);

		Graphics.ladoEquivalente(g2, 50, 700, 625, 50);

		Graphics.ladoEquivalente(g2, 1200, 700, 625, 50);
		
		if (a.mostrar)
			Graphics.addLabel(g2, a.nome, 50 + raio / 2, 700 - raio / 2, Alinhamento.TopLeft);

		if (b.mostrar)
			Graphics.addLabel(g2, b.nome, 625, 50 + raio / 2, Alinhamento.TopCenter);

		if (c.mostrar)
			Graphics.addLabel(g2, c.nome, 1200 - raio / 2, 700 - raio / 2, Alinhamento.TopRight);

		return image;
	}

	public String getTextLatex()
	{
		return a.nome + "-" + b.nome;
	}

	@Override
	public String toString()
	{
		return "ConfigTriangulo2:\n" + (a != null ? "a: " + a + "\n" : "") + (b != null ? "b: " + b + "\n" : "")
		+ (c != null ? "c: " + c : "");
	}

}
