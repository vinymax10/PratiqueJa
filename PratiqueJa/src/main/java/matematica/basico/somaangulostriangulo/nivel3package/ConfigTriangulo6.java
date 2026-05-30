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


public class ConfigTriangulo6 extends Config
{
	public Angulo a, b, c, d, e;

	public ConfigTriangulo6(int a, int b, int c, int d, int e)
	{
		this.a = new Angulo(this, a, false, a + "°");
		this.b = new Angulo(this, b, false, b + "°");
		this.c = new Angulo(this, c, false, c + "°");
		this.d = new Angulo(this, d, false, d + "°");
		this.e = new Angulo(this, e, false, e + "°");
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
			Graphics.setAngleSemBorda(g2, 875 - raio / 2, 700 - raio / 2, raio, 118, 62,
			ParCor.parCor(index + a.ordemInsercao), ParCor.parCor(index-1));

		if (b.mostrar)
			Graphics.setAngleSemBorda(g2, 625 - raio / 2, 200 - raio / 2, raio, -118, 56,
			ParCor.parCor(index + b.ordemInsercao), ParCor.parCor(index-1));

		if (c.mostrar)
			Graphics.setAngleSemBorda(g2, 375 - raio / 2, 700 - raio / 2, raio, 0, 62,
			ParCor.parCor(index + c.ordemInsercao), ParCor.parCor(index-1));

		if (d.mostrar)
			Graphics.setAngleSemBorda(g2, 875 - raio / 2, 700 - raio / 2, raio, 0, 118,
			ParCor.parCor(index + d.ordemInsercao), ParCor.parCor(index-1));
		
		if (e.mostrar)
			Graphics.setAngleSemBorda(g2, 375 - raio / 2, 700 - raio / 2, raio, 62, 118,
			ParCor.parCor(index + e.ordemInsercao), ParCor.parCor(index-1));

		Polygon triangulo = new Polygon();
		triangulo.addPoint(375, 700);
		triangulo.addPoint(875, 700);
		triangulo.addPoint(625, 200);
		g2.draw(triangulo);

		Graphics.ladoEquivalente(g2, 375, 700, 625, 200);

		Graphics.ladoEquivalente(g2, 875, 700, 625, 200);
		
		Graphics.arrow(g2, 875, 700, 1200, 700, false, true);
		
		Graphics.arrow(g2, 375, 700, 50, 700, false, true);

		if (a.mostrar)
			Graphics.addLabel(g2, a.nome, 895 - raio / 2, 660 , Alinhamento.BottomRight,70);

		if (b.mostrar)
			Graphics.addLabel(g2, b.nome, 625, 200 + raio / 2, Alinhamento.TopCenter,70);

		if (c.mostrar)
			Graphics.addLabel(g2, c.nome, 355 + raio / 2, 660, Alinhamento.BottomLeft,70);

		if (d.mostrar)
			Graphics.addLabel(g2, d.nome, 875 , 700 - raio / 2, Alinhamento.BottomLeft,70);

		if (e.mostrar)
			Graphics.addLabel(g2, e.nome, 375, 700 - raio / 2, Alinhamento.BottomRight,70);

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
