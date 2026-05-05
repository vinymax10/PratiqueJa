package Matematica.Intermediario.SomaAngulosTriangulo.Nivel3Package;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Alinhamento;
import Matematica.ParCor;
import Matematica.Intermediario.SomaAngulosTriangulo.Angulo;
import Matematica.Intermediario.SomaAngulosTriangulo.Config;


public class ConfigTriangulo10 extends Config
{
	public Angulo a, b, c, d;

	public ConfigTriangulo10(int a, int b, int c, int d)
	{
		this.a = new Angulo(this, a, false, a + "°");
		this.b = new Angulo(this, b, false, b + "°");
		this.c = new Angulo(this, c, false, c + "°");
		this.d = new Angulo(this, d, false, d + "°");
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
			Graphics.setAngleSemBorda(g2, 50 - raio / 2, 700 - raio / 2, raio, 0, 30,
			ParCor.parCor(index + a.ordemInsercao), ParCor.parCor(index-1));

		if (b.mostrar)
			Graphics.setAngleSemBorda(g2, 1200 - raio / 2, 50 - raio / 2, raio, 209, 20,
			ParCor.parCor(index + b.ordemInsercao), ParCor.parCor(index-1));

		if (c.mostrar)
			Graphics.setAngleSemBorda(g2, 650 - raio / 2, 700 - raio / 2, raio, 50, 130,
			ParCor.parCor(index + c.ordemInsercao), ParCor.parCor(index-1));

		if (d.mostrar)
			Graphics.setAngleSemBorda(g2, 650 - raio / 2, 700 - raio / 2, raio, 0, 50,
			ParCor.parCor(index + d.ordemInsercao), ParCor.parCor(index-1));

		Polygon triangulo = new Polygon();
		triangulo.addPoint(50, 700);
		triangulo.addPoint(650, 700);
		triangulo.addPoint(1200, 50);
		g2.draw(triangulo);

		Graphics.arrow(g2, 650, 700, 1200, 700, false, true);
		
		if (a.mostrar)
			Graphics.addLabel(g2, a.nome, 50 + raio / 2, 700, Alinhamento.BottomLeft,70);

		if (b.mostrar)
			Graphics.addLabel(g2, b.nome, 1250 - raio / 2, 120, Alinhamento.TopRight,70);

		if (c.mostrar)
			Graphics.addLabel(g2, c.nome, 650, 700 - raio / 2, Alinhamento.BottomRight,70);

		if (d.mostrar)
			Graphics.addLabel(g2, d.nome, 650 + raio / 2, 700, Alinhamento.BottomLeft,70);

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
