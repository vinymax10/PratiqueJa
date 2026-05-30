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


public class ConfigTriangulo2 extends Config
{
	Angulo a, b, c;

	public ConfigTriangulo2(int a, int b, int c)
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
			Graphics.setAngleSemBorda(g2, 1200 - raio / 2, 50 - raio / 2, raio, 207, 24,
			ParCor.parCor(index + a.ordemInsercao), ParCor.parCor(index-1));

		if (b.mostrar)
			Graphics.setAngleSemBorda(g2, 50 - raio / 2, 700 - raio / 2, raio, 0, 26,
			ParCor.parCor(index + b.ordemInsercao), ParCor.parCor(index-1));

		if (c.mostrar)
			Graphics.setAngleSemBorda(g2, 700 - raio / 2, 700 - raio / 2, raio, 180, -128,
			ParCor.parCor(index + c.ordemInsercao), ParCor.parCor(index-1));

		Polygon triangulo = new Polygon();
		triangulo.addPoint(50, 700);
		triangulo.addPoint(700, 700);
		triangulo.addPoint(1200, 50);
		g2.draw(triangulo);

		if (a.mostrar)
			Graphics.addLabel(g2, a.nome, 1170 - raio / 2, 130, Alinhamento.TopCenter,80);

		if (b.mostrar)
			Graphics.addLabel(g2, b.nome, 60 + raio / 2, 700, Alinhamento.BottomLeft,80);

		if (c.mostrar)
			Graphics.addLabel(g2, c.nome, 650, 700 - raio / 2, Alinhamento.BottomCenter);

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
