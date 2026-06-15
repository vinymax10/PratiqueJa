package matematica.intermediario.semelhancaangulos.nivel1package;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;
import matematica.basico.somaangulostriangulo.Angulo;
import matematica.basico.somaangulostriangulo.Config;


public class ConfigSemelhancaAngulos3 extends Config
{
	Angulo a, b, c, d, e, f, g, h;

	public ConfigSemelhancaAngulos3(int a, int b, int c, int d, int e, int f, int g, int h)
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

		int x1 = 408;
		int y1 = 253;

		int x2 = 842;
		int y2 = 498;

		if(a.mostrar)
			Graphics.setAngleSemBorda(g2, x1 - raio / 2, y1 - raio / 2, raio, 150, 52,
			ParCor.parCorAleatorio(), ParCor.parCorAleatorio());

		if(b.mostrar)
			Graphics.setAngleSemBorda(g2, x1 - raio / 2, y1 - raio / 2, raio, -30, 52,
			ParCor.parCorAleatorio(), ParCor.parCorAleatorio());

		if(c.mostrar)
			Graphics.setAngleSemBorda(g2, x1 - raio / 2, y1 - raio / 2, raio, 22, 128,
			ParCor.parCorAleatorio(), ParCor.parCorAleatorio());

		if(d.mostrar)
			Graphics.setAngleSemBorda(g2, x1 - raio / 2, y1 - raio / 2, raio, 202, 128,
			ParCor.parCorAleatorio(), ParCor.parCorAleatorio());

		if(e.mostrar)
			Graphics.setAngleSemBorda(g2, x2 - raio / 2, y2 - raio / 2, raio, 150, 52,
			ParCor.parCorAleatorio(), ParCor.parCorAleatorio());

		if(f.mostrar)
			Graphics.setAngleSemBorda(g2, x2 - raio / 2, y2 - raio / 2, raio, -30, 52,
			ParCor.parCorAleatorio(), ParCor.parCorAleatorio());

		if(g.mostrar)
			Graphics.setAngleSemBorda(g2, x2 - raio / 2, y2 - raio / 2, raio, 22, 128,
			ParCor.parCorAleatorio(), ParCor.parCorAleatorio());

		if(h.mostrar)
			Graphics.setAngleSemBorda(g2, x2 - raio / 2, y2 - raio / 2, raio, 202, 128,
			ParCor.parCorAleatorio(), ParCor.parCorAleatorio());

			Graphics.arrow(g2, 50, 400, 900, 50, true, true);

			Graphics.arrow(g2, 350, 700, 1200, 350, true, true);

			Graphics.arrow(g2, 50, 50, 1200, 700, true, true);

		if(a.mostrar)
			Graphics.addLabel(g2, a.nome, x1 - raio / 2, y1, Alinhamento.MiddleRight);

		if(b.mostrar)
			Graphics.addLabel(g2, b.nome, x1 + raio / 2, y1, Alinhamento.MiddleLeft);

		if(c.mostrar)
			Graphics.addLabel(g2, c.nome, x1, y1 - raio / 2, Alinhamento.BottomCenter);

		if(d.mostrar)
			Graphics.addLabel(g2, d.nome, x1, y1 + raio / 2, Alinhamento.TopCenter);

		if(e.mostrar)
			Graphics.addLabel(g2, e.nome, x2 - raio / 2, y2, Alinhamento.MiddleRight);

		if(f.mostrar)
			Graphics.addLabel(g2, f.nome, x2 + raio / 2, y2, Alinhamento.MiddleLeft);

		if(g.mostrar)
			Graphics.addLabel(g2, g.nome, x2, y2 - raio / 2, Alinhamento.BottomCenter);

		if(h.mostrar)
			Graphics.addLabel(g2, h.nome, x2, y2 + raio / 2, Alinhamento.TopCenter);
//
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
		return "ConfigTriangulo1:\n" + (a != null ? "a: " + a + "\n" : "") + (b != null ? "b: " + b + "\n" : "")
		+ (c != null ? "c: " + c : "");
	}

}
