package matematica.basico.somaangulostriangulo.nivel2package;

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


public class ConfigTrianguloBipartido1 extends Config
{
	public Angulo a, b, c, d, e, f;

	public ConfigTrianguloBipartido1(int a, int b, int c, int d, int e, int f)
	{
		this.a = new Angulo(this, a, false, a + "°");
		this.b = new Angulo(this, b, false, b + "°");
		this.c = new Angulo(this, c, false, c + "°");
		this.d = new Angulo(this, d, false, d + "°");
		this.e = new Angulo(this, e, false, e + "°");
		this.f = new Angulo(this, f, false, f + "°");
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
			Graphics.setAngleSemBorda(g2, 50 - raio / 2, 700 - raio / 2, raio, 0, 70,
			ParCor.parCor(index + a.ordemInsercao), ParCor.parCor(index-1));

		if (b.mostrar)
			Graphics.setAngleSemBorda(g2, 275 - raio / 2, 50 - raio / 2, raio, 251, 47,
			ParCor.parCor(index + b.ordemInsercao), ParCor.parCor(index-1));

		if (c.mostrar)
			Graphics.setAngleSemBorda(g2, 650 - raio / 2, 700 - raio / 2, raio, 180, -60,
			ParCor.parCor(index + c.ordemInsercao), ParCor.parCor(index-1));

		if (d.mostrar)
			Graphics.setAngleSemBorda(g2, 650 - raio / 2, 700 - raio / 2, raio, 120, -120,
			ParCor.parCor(index + d.ordemInsercao), ParCor.parCor(index-1));

		if (e.mostrar)
			Graphics.setAngleSemBorda(g2, 275 - raio / 2, 50 - raio / 2, raio, 298, 27,
			ParCor.parCor(index + e.ordemInsercao), ParCor.parCor(index-1));

		if (f.mostrar)
			Graphics.setAngleSemBorda(g2, 1200 - raio / 2, 700 - raio / 2, raio, 180, -35,
			ParCor.parCor(index + f.ordemInsercao), ParCor.parCor(index-1));

			Polygon paralelogramo = new Polygon();
			paralelogramo.addPoint(50, 700);
			paralelogramo.addPoint(1200, 700);
			paralelogramo.addPoint(275, 50);
			g2.draw(paralelogramo);
			
			Graphics.arrow(g2, 275, 54, 648, 698, false, false);

		if (a.mostrar)
			Graphics.addLabel(g2, a.nome, 30 + raio / 2, 650, Alinhamento.BottomLeft);

		if (b.mostrar)
			Graphics.addLabel(g2, b.nome, 285, 50 + raio / 2, Alinhamento.TopCenter,75);

		if (c.mostrar)
			Graphics.addLabel(g2, c.nome, 670 - raio / 2, 670, Alinhamento.BottomRight);

		if (d.mostrar)
			Graphics.addLabel(g2, d.nome, 650, 700- raio / 2, Alinhamento.BottomLeft);

		if (e.mostrar)
			Graphics.addLabel(g2, e.nome, 235 + raio / 2, 20 + raio / 2, Alinhamento.TopLeft,60);

		if (f.mostrar)
			Graphics.addLabel(g2, f.nome, 1200 - raio / 2, 700, Alinhamento.BottomRight,70);

		return image;
	}

	public String getTextLatex()
	{
		return a.nome + "-" + b.nome + "-" + c.nome + "-" + d.nome + "-" + e.nome + "-" + f.nome;
	}

	@Override
	public String toString()
	{
		return "Config:\n" + (a != null ? "a: " + a + "\n" : "") + (b != null ? "b: " + b + "\n" : "")
		+ (c != null ? "c: " + c + "\n" : "") + (d != null ? "d: " + d + "\n" : "")
		+ (e != null ? "e: " + e + "\n" : "") + (f != null ? "f: " + f + "\n" : "");
	}

}
