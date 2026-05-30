package matematica.basico.somaangulostriangulo.nivel1package;

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


public class ConfigTriangulo1 extends Config
{
	Angulo a, b, c;

	public ConfigTriangulo1(int a, int b, int c)
	{
		this.a = new Angulo(this, a, false, a + "°");
		this.b = new Angulo(this, b, false, b + "°");
		this.c = new Angulo(this, c, false, c + "°");
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

		int raio = 200;

		if (a.mostrar)
			Graphics.setAngleSemBorda(g2, 275 - raio / 2, 50 - raio / 2, raio, 251, 75,
			ParCor.parCor(index + a.ordemInsercao), ParCor.parCor(index-1));

		if (b.mostrar)
			Graphics.setAngleSemBorda(g2, 50 - raio / 2, 700 - raio / 2, raio, 0, 71,
			ParCor.parCor(index + b.ordemInsercao), ParCor.parCor(index-1));

		if (c.mostrar)
			Graphics.setAngleSemBorda(g2, 1200 - raio / 2, 700 - raio / 2, raio, 180, -33,
			ParCor.parCor(index + c.ordemInsercao), ParCor.parCor(index-1));

		Polygon triangulo = new Polygon();
		triangulo.addPoint(50, 700);
		triangulo.addPoint(1200, 700);
		triangulo.addPoint(275, 50);
		g2.draw(triangulo);

		if (a.mostrar)
			Graphics.addLabel(g2, a.nome, 300, 50+raio / 2, Alinhamento.TopCenter);

		if (b.mostrar)
			Graphics.addLabel(g2, b.nome, 30 +raio / 2, 650, Alinhamento.BottomLeft);

		if (c.mostrar)
			Graphics.addLabel(g2, c.nome, 1200 -raio / 2, 700, Alinhamento.BottomRight);

		return image;
	}

	public String getTextLatex()
	{
		return a.nome + "-" + b.nome;
	}

	@Override
	public String toString()
	{
		return "ConfigTriangulo1:\n" + (a != null ? "a: " + a + "\n" : "") + (b != null ? "b: " + b + "\n" : "")
		+ (c != null ? "c: " + c : "");
	}

}
