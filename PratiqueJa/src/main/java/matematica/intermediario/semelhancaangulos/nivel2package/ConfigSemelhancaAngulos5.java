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


public class ConfigSemelhancaAngulos5 extends Config
{
	Angulo a, b, c, d, e, f, g, h, i, j;

	public ConfigSemelhancaAngulos5(int a, int b, int c, int d, int e, int f, int g, int h, int i, int j)
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

		int x1 = 408;
		int y1 = 253;

		int x2 = 842;
		int y2 = 498;

		if(a.mostrar)
			Graphics.setAngleSemBorda(g2, x1 - raio / 2, y1 - raio / 2, raio, 23, 48,
			ParCor.parCor(index + a.ordemInsercao), ParCor.parCor(index-1));

		if(b.mostrar)
			Graphics.setAngleSemBorda(g2, x1 - raio / 2, y1 - raio / 2, raio, -28, 51,
			ParCor.parCor(index + b.ordemInsercao), ParCor.parCor(index-1));

		if(c.mostrar)
			Graphics.setAngleSemBorda(g2, x1 - raio / 2, y1 - raio / 2, raio, -110, 80,
			ParCor.parCor(index + c.ordemInsercao), ParCor.parCor(index-1));

		if(d.mostrar)
			Graphics.setAngleSemBorda(g2, x1 - raio / 2, y1 - raio / 2, raio, 202, 48,
			ParCor.parCor(index + d.ordemInsercao), ParCor.parCor(index-1));

		if(e.mostrar)
			Graphics.setAngleSemBorda(g2, x1 - raio / 2, y1 - raio / 2, raio, 151, 50,
			ParCor.parCor(index + e.ordemInsercao), ParCor.parCor(index-1));

		if(f.mostrar)
			Graphics.setAngleSemBorda(g2, x1 - raio / 2, y1 - raio / 2, raio, 71, 80,
			ParCor.parCor(index + f.ordemInsercao), ParCor.parCor(index-1));

		if(g.mostrar)
			Graphics.setAngleSemBorda(g2, x2 - raio / 2, y2 - raio / 2, raio, 22, 128,
			ParCor.parCor(index + g.ordemInsercao), ParCor.parCor(index-1));

		if(h.mostrar)
			Graphics.setAngleSemBorda(g2, x2 - raio / 2, y2 - raio / 2, raio, -30, 52,
			ParCor.parCor(index + h.ordemInsercao), ParCor.parCor(index-1));

		if(i.mostrar)
			Graphics.setAngleSemBorda(g2, x2 - raio / 2, y2 - raio / 2, raio, 202, 128,
			ParCor.parCor(index + i.ordemInsercao), ParCor.parCor(index-1));

		if(j.mostrar)
			Graphics.setAngleSemBorda(g2, x2 - raio / 2, y2 - raio / 2, raio, 150, 52,
			ParCor.parCor(index + j.ordemInsercao), ParCor.parCor(index-1));

			Graphics.arrow(g2, 50, 400, 900, 50, true, true);

			Graphics.arrow(g2, 350, 700, 1200, 350, true, true);

			Graphics.arrow(g2, 50, 50, 1200, 700, true, true);
			
			Graphics.arrow(g2, 490, 25, 250, 700, true, true);

		if(a.mostrar)
			Graphics.addLabel(g2, a.nome, x1 + 60, y1 - raio / 2 + 35, Alinhamento.BottomLeft);

		if(b.mostrar)
			Graphics.addLabel(g2, b.nome, x1 + raio / 2, y1, Alinhamento.MiddleLeft);

		if(c.mostrar)
			Graphics.addLabel(g2, c.nome, x1 + 50, y1 + raio / 2, Alinhamento.TopCenter);

		if(d.mostrar)
			Graphics.addLabel(g2, d.nome, x1 - 50, y1 + raio / 2 - 45, Alinhamento.TopRight);

		if(e.mostrar)
			Graphics.addLabel(g2, e.nome, x1 - raio / 2, y1, Alinhamento.MiddleRight);

		if(f.mostrar)
			Graphics.addLabel(g2, f.nome, x1 + 5, y1 - raio / 2, Alinhamento.BottomRight);

		if(g.mostrar)
			Graphics.addLabel(g2, g.nome, x2, y2 - raio / 2, Alinhamento.BottomCenter);

		if(h.mostrar)
			Graphics.addLabel(g2, h.nome, x2 + raio / 2, y2, Alinhamento.MiddleLeft);

		if(i.mostrar)
			Graphics.addLabel(g2, i.nome, x2, y2 + raio / 2, Alinhamento.TopCenter);

		if(j.mostrar)
			Graphics.addLabel(g2, j.nome, x2- raio / 2, y2 , Alinhamento.MiddleRight);

			Graphics.addLabel(g2, "r", 50, 390, Alinhamento.BottomLeft);
			Graphics.addLabel(g2, "s", 350, 690, Alinhamento.BottomLeft);
			Graphics.addLabel(g2, "r \\parallel s", 0, 700, Alinhamento.BottomLeft);

		return image;
	}

	public String getTextLatex()
	{
		return a.nome + "-" + b.nome+"-" + c.nome+"-" + d.nome;
	}

	@Override
	public String toString()
	{
		return "ConfigSemelhancaAngulos5:\n" + (a != null ? "a: " + a + "\n" : "") + (b != null ? "b: " + b + "\n" : "")
		+ (c != null ? "c: " + c + "\n" : "") + (d != null ? "d: " + d + "\n" : "")
		+ (e != null ? "e: " + e + "\n" : "") + (f != null ? "f: " + f + "\n" : "")
		+ (g != null ? "g: " + g + "\n" : "") + (h != null ? "h: " + h + "\n" : "")
		+ (i != null ? "i: " + i + "\n" : "") + (j != null ? "j: " + j : "");
	}

}
