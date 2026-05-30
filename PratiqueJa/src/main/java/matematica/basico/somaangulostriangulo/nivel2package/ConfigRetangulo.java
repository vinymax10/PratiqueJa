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


public class ConfigRetangulo extends Config
{
	public Angulo a, b, c, d, e, f, g;

	public ConfigRetangulo(int a, int b, int c, int d, int e, int f, int g)
	{
		this.a = new Angulo(this, a, false, a + "°");
		this.b = new Angulo(this, b, false, b + "°");
		this.c = new Angulo(this, c, false, c + "°");
		this.d = new Angulo(this, d, false, d + "°");
		this.e = new Angulo(this, e, false, e + "°");
		this.f = new Angulo(this, f, false, f + "°");
		this.g = new Angulo(this, g, false, g + "°");
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

		int raioAngulo=50;
		int raio = 200;
		
		if (a.mostrar)
			Graphics.setAngleSemBorda(g2, 1200 - raio / 2, 50 - raio / 2, raio, 229, 41,
			ParCor.parCor(index + a.ordemInsercao), ParCor.parCor(index-1));

		if (b.mostrar)
			Graphics.setAngleSemBorda(g2, 650 - raio / 2, 700 - raio / 2, raio, 0, 50,
			ParCor.parCor(index + b.ordemInsercao), ParCor.parCor(index-1));

		if (c.mostrar)
			Graphics.setAngleSemBorda(g2, 650 - raio / 2, 700 - raio / 2, raio, 50, 130,
			ParCor.parCor(index + c.ordemInsercao), ParCor.parCor(index-1));

		if (d.mostrar)
			Graphics.setAngleSemBorda(g2, 50 - raio / 2, 700 - raio / 2, raio, 0, 30,
			ParCor.parCor(index + d.ordemInsercao), ParCor.parCor(index-1));

		if (e.mostrar)
			Graphics.setAngleSemBorda(g2, 1200 - raio / 2, 50 - raio / 2, raio, 210, 20,
			ParCor.parCor(index + e.ordemInsercao), ParCor.parCor(index-1));

		if (f.mostrar)
			Graphics.setAngleSemBorda(g2, 1200 - raio / 2, 50 - raio / 2, raio, 180, 30,
			ParCor.parCor(index + f.ordemInsercao), ParCor.parCor(index-1));

		if (g.mostrar)
			Graphics.setAngleSemBorda(g2, 50 - raio / 2, 700 - raio / 2, raio, 30, 60,
			ParCor.parCor(index + g.ordemInsercao), ParCor.parCor(index-1));

		Polygon retangulo = new Polygon();
		retangulo.addPoint(50, 700);
		retangulo.addPoint(1200, 700);
		retangulo.addPoint(1200, 50);
		retangulo.addPoint(50, 50);
		g2.draw(retangulo);
		
		Graphics.arrow(g2, 1197, 53, 650, 697, false, false);
		Graphics.arrow(g2, 1197, 53, 53, 697, false, false);
		Graphics.addAngleReto(g2, 50, 50, raioAngulo);
		Graphics.addAngleReto(g2, 1200-raioAngulo, 700-raioAngulo, raioAngulo);

		if (a.mostrar)
			Graphics.addLabel(g2, a.nome, 1190, 50+ raio / 2, Alinhamento.TopRight,70);

		if (b.mostrar)
			Graphics.addLabel(g2, b.nome, 650 + raio / 2, 680 , Alinhamento.BottomLeft);

		if (c.mostrar)
			Graphics.addLabel(g2, c.nome, 660 , 700-raio / 2 , Alinhamento.BottomRight);

		if (d.mostrar)
			Graphics.addLabel(g2, d.nome, 50 + raio / 2, 690 , Alinhamento.BottomLeft,70);

		if (e.mostrar)
			Graphics.addLabel(g2, e.nome, 1130,  110 , Alinhamento.TopRight,65);

		if (f.mostrar)
			Graphics.addLabel(g2, f.nome, 1200-raio / 2,  50 , Alinhamento.TopRight,70);

		if (g.mostrar)
			Graphics.addLabel(g2, g.nome, 70,  700 -raio / 2, Alinhamento.BottomLeft);

		return image;
	}

	public String getTextLatex()
	{
		return a.nome + "-" + b.nome + "-" + c.nome + "-" + d.nome + "-" + e.nome + "-" + f.nome + "-" + g.nome;
	}

	@Override
	public String toString()
	{
		return "Config:\n" + (a != null ? "a: " + a + "\n" : "") + (b != null ? "b: " + b + "\n" : "")
		+ (c != null ? "c: " + c + "\n" : "") + (d != null ? "d: " + d + "\n" : "")
		+ (e != null ? "e: " + e + "\n" : "") + (f != null ? "f: " + f + "\n" : "") + (g != null ? "g: " + g : "");
	}

}
