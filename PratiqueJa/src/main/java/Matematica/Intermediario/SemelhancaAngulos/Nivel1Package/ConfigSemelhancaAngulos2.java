package Matematica.Intermediario.SemelhancaAngulos.Nivel1Package;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Alinhamento;
import Matematica.ParCor;
import Matematica.Intermediario.SomaAngulosTriangulo.Angulo;
import Matematica.Intermediario.SomaAngulosTriangulo.Config;


public class ConfigSemelhancaAngulos2 extends Config
{
	Angulo a, b, c, d, e, f, g, h;

	public ConfigSemelhancaAngulos2(int a, int b, int c, int d, int e, int f, int g, int h)
	{
		this.a = new Angulo(this, a, false, a + "°");
		this.b = new Angulo(this, b, false, b + "°");
		this.c = new Angulo(this, c, false, c + "°");
		this.d = new Angulo(this, d, false, d + "°");
		this.e = new Angulo(this, e, false, e + "°");
		this.f = new Angulo(this, f, false, f + "°");
		this.g = new Angulo(this, g, false, g + "°");
		this.h = new Angulo(this, h, false, h + "°");
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

		int x1 = 754;
		int y1 = 250;

		int x2 = 446;
		int y2 = 500;
		
		if(a.mostrar)
			Graphics.setAngleSemBorda(g2, x1 - raio / 2, y1 - raio / 2, raio, 0, 40,
			ParCor.parCor(index + a.ordemInsercao), ParCor.parCor(index-1));

		if(b.mostrar)
			Graphics.setAngleSemBorda(g2, x1 - raio / 2, y1 - raio / 2, raio, 180, 40,
			ParCor.parCor(index + b.ordemInsercao), ParCor.parCor(index-1));

		if(c.mostrar)
			Graphics.setAngleSemBorda(g2, x1 - raio / 2, y1 - raio / 2, raio, 40, 140,
			ParCor.parCor(index + c.ordemInsercao), ParCor.parCor(index-1));

		if(d.mostrar)
			Graphics.setAngleSemBorda(g2, x1 - raio / 2, y1 - raio / 2, raio, 0, -140,
			ParCor.parCor(index + d.ordemInsercao), ParCor.parCor(index-1));

		if(e.mostrar)
			Graphics.setAngleSemBorda(g2, x2 - raio / 2, y2 - raio / 2, raio, 0, 40,
			ParCor.parCor(index + e.ordemInsercao), ParCor.parCor(index-1));

		if(f.mostrar)
			Graphics.setAngleSemBorda(g2, x2 - raio / 2, y2 - raio / 2, raio, 180, 40,
			ParCor.parCor(index + f.ordemInsercao), ParCor.parCor(index-1));

		if(g.mostrar)
			Graphics.setAngleSemBorda(g2, x2 - raio / 2, y2 - raio / 2, raio, 40, 140,
			ParCor.parCor(index + g.ordemInsercao), ParCor.parCor(index-1));

		if(h.mostrar)
			Graphics.setAngleSemBorda(g2, x2 - raio / 2, y2 - raio / 2, raio, 0, -140,
			ParCor.parCor(index + h.ordemInsercao), ParCor.parCor(index-1));

		Graphics.arrow(g2, 50, 250, 1200, 250, true, true);

		Graphics.arrow(g2, 50, 500, 1200, 500, true, true);

		Graphics.arrow(g2, 200, 700, 1000, 50, true, true);

		if(a.mostrar)
			Graphics.addLabel(g2, a.nome, x1 + raio / 2, y1 - 5, Alinhamento.BottomLeft);

		if(b.mostrar)
			Graphics.addLabel(g2, b.nome, x1 - raio / 2, y1 + 5, Alinhamento.TopRight);

		if(c.mostrar)
			Graphics.addLabel(g2, c.nome, x1 - 60, y1 - raio / 2, Alinhamento.BottomCenter);

		if(d.mostrar)
			Graphics.addLabel(g2, d.nome, x1 + 60, y1 + raio / 2, Alinhamento.TopCenter);

		if(e.mostrar)
			Graphics.addLabel(g2, e.nome, x2 + raio / 2, y2 - 15, Alinhamento.BottomLeft);

		if(f.mostrar)
			Graphics.addLabel(g2, f.nome, x2 - raio / 2, y2 + 15, Alinhamento.TopRight);

		if(g.mostrar)
			Graphics.addLabel(g2, g.nome, x2 - 60, y2 - raio / 2, Alinhamento.BottomCenter);

		if(h.mostrar)
			Graphics.addLabel(g2, h.nome, x2 + 60, y2 + raio / 2, Alinhamento.TopCenter);

		Graphics.addLabel(g2, "r", 50, 250, Alinhamento.BottomLeft);
		Graphics.addLabel(g2, "s", 50, 500, Alinhamento.BottomLeft);
		Graphics.addLabel(g2, "r \\parallel s", 1250, 700, Alinhamento.BottomRight);

		return image;
	}

	public String getTextLatex()
	{
		return a.nome + "-" + b.nome+"-" + c.nome+"-" + d.nome;
	}

	@Override
	public String toString()
	{
		return "ConfigTriangulo1:\n" + (a != null ? "a: " + a + "\n" : "") + (b != null ? "b: " + b + "\n" : "")
		+ (c != null ? "c: " + c : "");
	}

}
