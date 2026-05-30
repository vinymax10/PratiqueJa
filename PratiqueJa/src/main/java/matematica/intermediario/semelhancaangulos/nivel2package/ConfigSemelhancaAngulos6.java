package matematica.intermediario.semelhancaangulos.nivel2package;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;
import matematica.basico.somaangulostriangulo.Angulo;
import matematica.basico.somaangulostriangulo.Config;


public class ConfigSemelhancaAngulos6 extends Config
{
	Angulo a, b, c, d, e, f, g, h, i, j, l, m, n, o;

	public ConfigSemelhancaAngulos6(int a, int b, int c, int d, int e, int f, int g, int h, int i, int j, int l, int m,
	int n, int o)
	{
		this.a = new Angulo(this, a, false, a + "°");
		this.b = new Angulo(this, b, false, b + "°");
		this.c = new Angulo(this, c, false, c + "°");
		this.d = new Angulo(this, d, false, d + "°");
		this.e = new Angulo(this, e, false, e + "°");
		this.f = new Angulo(this, f, false, f + "°");
		this.g = new Angulo(this, g, false, g + "°");
		this.h = new Angulo(this, h, false, h + "°");
		this.i = new Angulo(this, i, false, i + "°");
		this.j = new Angulo(this, j, false, j + "°");
		this.l = new Angulo(this, l, false, l + "°");
		this.m = new Angulo(this, m, false, m + "°");
		this.n = new Angulo(this, n, false, n + "°");
		this.o = new Angulo(this, o, false, o + "°");
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

		int raio = 180;

		int x1 = 272;
		int y1 = 145;

		int x2 = 367;
		int y2 = 563;
		
		int x3 = 867;
		int y3 = 343;
		
		if(a.mostrar)
			Graphics.setAngleSemBorda(g2, x3 - raio / 2, y3 - raio / 2, raio, 344, 38,
			ParCor.parCor(index + a.ordemInsercao), ParCor.parCor(index-1));

		if(b.mostrar)
			Graphics.setAngleSemBorda(g2, x3 - raio / 2, y3 - raio / 2, raio, 283, 61,
			ParCor.parCor(index + b.ordemInsercao), ParCor.parCor(index-1));

		if(c.mostrar)
			Graphics.setAngleSemBorda(g2, x3 - raio / 2, y3 - raio / 2, raio, 205, 78,
			ParCor.parCor(index + c.ordemInsercao), ParCor.parCor(index-1));

		if(d.mostrar)
			Graphics.setAngleSemBorda(g2, x3 - raio / 2, y3 - raio / 2, raio, 163, 42,
			ParCor.parCor(index + d.ordemInsercao), ParCor.parCor(index-1));

		if(e.mostrar)
			Graphics.setAngleSemBorda(g2, x3 - raio / 2, y3 - raio / 2, raio, 103, 58,
			ParCor.parCor(index + e.ordemInsercao), ParCor.parCor(index-1));

		if(f.mostrar)
			Graphics.setAngleSemBorda(g2, x3 - raio / 2, y3 - raio / 2, raio, 22, 80,
			ParCor.parCor(index + f.ordemInsercao), ParCor.parCor(index-1));

		if(g.mostrar)
			Graphics.setAngleSemBorda(g2, x1 - raio / 2, y1 - raio / 2, raio, -18, 120,
			ParCor.parCor(index + g.ordemInsercao), ParCor.parCor(index-1));

		if(h.mostrar)
			Graphics.setAngleSemBorda(g2, x1 - raio / 2, y1 - raio / 2, raio, -77, 58,
			ParCor.parCor(index + h.ordemInsercao), ParCor.parCor(index-1));

		if(i.mostrar)
			Graphics.setAngleSemBorda(g2, x1 - raio / 2, y1 - raio / 2, raio, -197, 120,
			ParCor.parCor(index + i.ordemInsercao), ParCor.parCor(index-1));

		if(j.mostrar)
			Graphics.setAngleSemBorda(g2, x1 - raio / 2, y1 - raio / 2, raio, 103, 60,
			ParCor.parCor(index + j.ordemInsercao), ParCor.parCor(index-1));
		
		if(l.mostrar)
			Graphics.setAngleSemBorda(g2, x2 - raio / 2, y2 - raio / 2, raio, 24, 78,
			ParCor.parCor(index + l.ordemInsercao), ParCor.parCor(index-1));

		if(m.mostrar)
			Graphics.setAngleSemBorda(g2, x2 - raio / 2, y2 - raio / 2, raio, 283, 101,
			ParCor.parCor(index + m.ordemInsercao), ParCor.parCor(index-1));

		if(n.mostrar)
			Graphics.setAngleSemBorda(g2, x2 - raio / 2, y2 - raio / 2, raio, 203, 80,
			ParCor.parCor(index + n.ordemInsercao), ParCor.parCor(index-1));

		if(o.mostrar)
			Graphics.setAngleSemBorda(g2, x2 - raio / 2, y2 - raio / 2, raio, 103, 100,
			ParCor.parCor(index + o.ordemInsercao), ParCor.parCor(index-1));

			Graphics.arrow(g2, 244, 25, 400, 700, true, true);
			Graphics.arrow(g2, 794, 25, 950, 700, true, true);
			Graphics.arrow(g2, 50, 75, 1200, 450, true, true);
			Graphics.arrow(g2, 50, 700, 1200, 200, true, true);
		
		int sizeFont=75;
			
		if(a.mostrar)
			Graphics.addLabel(g2, a.nome, x3 + raio / 2, y3, Alinhamento.MiddleLeft,sizeFont);

		if(b.mostrar)
			Graphics.addLabel(g2, b.nome, x3 + 35, y3+60, Alinhamento.TopLeft,sizeFont);

		if(c.mostrar)
			Graphics.addLabel(g2, c.nome, x3 -20, y3+ raio / 2 -10, Alinhamento.TopRight,sizeFont);

		if(d.mostrar)
			Graphics.addLabel(g2, d.nome, x3 - raio / 2, y3+5 , Alinhamento.MiddleRight,sizeFont);

		if(e.mostrar)
			Graphics.addLabel(g2, e.nome, x3 - 60 , y3 -60 , Alinhamento.BottomRight,sizeFont);

		if(f.mostrar)
			Graphics.addLabel(g2, f.nome, x3 +20, y3 - raio / 2+10, Alinhamento.BottomLeft,sizeFont);

		if(g.mostrar)
			Graphics.addLabel(g2, g.nome, x1 + 60, y1 -50, Alinhamento.BottomLeft,sizeFont);

		if(h.mostrar)
			Graphics.addLabel(g2, h.nome, x1 + 40, y1+55, Alinhamento.TopLeft,sizeFont);

		if(i.mostrar)
			Graphics.addLabel(g2, i.nome, x1 - 50, y1+50, Alinhamento.TopRight,sizeFont);

		if(j.mostrar)
			Graphics.addLabel(g2, j.nome, x1 - 45 , y1 -30 , Alinhamento.BottomRight,sizeFont);

		if(l.mostrar)
			Graphics.addLabel(g2, l.nome, x2 +10, y2 - raio / 2 +10, Alinhamento.BottomLeft,sizeFont);

		if(m.mostrar)
			Graphics.addLabel(g2, m.nome, x2 + raio / 2 -20, y2+20, Alinhamento.TopLeft,sizeFont);

		if(n.mostrar)
			Graphics.addLabel(g2, n.nome, x2-10 , y2+ raio / 2-10, Alinhamento.TopRight,sizeFont);

		if(o.mostrar)
			Graphics.addLabel(g2, o.nome, x2 - raio / 2 +20, y2 -40, Alinhamento.MiddleRight,sizeFont);
		
		Graphics.addLabel(g2, "r", 250, 0, Alinhamento.TopLeft);
		Graphics.addLabel(g2, "s", 800, 0, Alinhamento.TopLeft);
		Graphics.addLabel(g2, "r \\parallel s", 1200, 700, Alinhamento.BottomRight);

		return image;
	}

	public String getTextLatex()
	{
		return a.nome + "-" + b.nome+"-" + c.nome+"-" + d.nome;
	}

	@Override
	public String toString()
	{
		return "ConfigSemelhancaAngulos6:\n" + (a != null ? "a: " + a + "\n" : "") + (b != null ? "b: " + b + "\n" : "")
		+ (c != null ? "c: " + c + "\n" : "") + (d != null ? "d: " + d + "\n" : "")
		+ (e != null ? "e: " + e + "\n" : "") + (f != null ? "f: " + f + "\n" : "")
		+ (g != null ? "g: " + g + "\n" : "") + (h != null ? "h: " + h + "\n" : "")
		+ (i != null ? "i: " + i + "\n" : "") + (j != null ? "j: " + j + "\n" : "")
		+ (l != null ? "l: " + l + "\n" : "") + (m != null ? "m: " + m + "\n" : "")
		+ (n != null ? "n: " + n + "\n" : "") + (o != null ? "o: " + o : "");
	}

}
