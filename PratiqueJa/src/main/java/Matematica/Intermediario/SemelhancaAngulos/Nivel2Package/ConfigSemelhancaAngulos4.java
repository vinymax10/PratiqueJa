package Matematica.Intermediario.SemelhancaAngulos.Nivel2Package;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Alinhamento;
import Matematica.ParCor;
import Matematica.Intermediario.SomaAngulosTriangulo.Angulo;
import Matematica.Intermediario.SomaAngulosTriangulo.Config;


public class ConfigSemelhancaAngulos4 extends Config
{
	Angulo a, b, c, d, e;

	public ConfigSemelhancaAngulos4(int a, int b, int c, int d, int e)
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

		int x1 = 850;
		int y1 = 375;

		int x2 = 600;
		int y2 = 250;

		int x3 = 600;
		int y3 = 500;
		
		if(a.mostrar)
			Graphics.setAngleSemBorda(g2, x1 - raio / 2, y1 - raio / 2, raio, 154, 52,
			ParCor.parCor(index + a.ordemInsercao), ParCor.parCor(index-1));

		if(b.mostrar)
			Graphics.setAngleSemBorda(g2, x2 - raio / 2, y2 - raio / 2, raio, 155, 26,
			ParCor.parCor(index + b.ordemInsercao), ParCor.parCor(index-1));

		if(c.mostrar)
			Graphics.setAngleSemBorda(g2, x3 - raio / 2, y3 - raio / 2, raio, 180, 26,
			ParCor.parCor(index + c.ordemInsercao), ParCor.parCor(index-1));

		if(d.mostrar)
			Graphics.setAngleSemBorda(g2, x2 - raio / 2, y2 - raio / 2, raio, 0, 154,
			ParCor.parCor(index + d.ordemInsercao), ParCor.parCor(index-1));

		if(e.mostrar)
			Graphics.setAngleSemBorda(g2, x3 - raio / 2, y3 - raio / 2, raio, 206, 154,
			ParCor.parCor(index + e.ordemInsercao), ParCor.parCor(index-1));

			Graphics.arrow(g2, 50, 250, 1200, 250, true, true);

			Graphics.arrow(g2, 50, 500, 1200, 500, true, true);

			Graphics.arrow(g2, 200, 50, 850, 375, true, false);

			Graphics.arrow(g2, 200, 700, 850, 375, true, false);

		if(a.mostrar)
			Graphics.addLabel(g2, a.nome, x1 - raio / 2, y1, Alinhamento.MiddleRight);

		if(b.mostrar)
			Graphics.addLabel(g2, b.nome, x2 - 25 - raio / 2, y2, Alinhamento.BottomRight);

		if(c.mostrar)
			Graphics.addLabel(g2, c.nome, x3 - 25 - raio / 2, y3, Alinhamento.TopRight);

		if(d.mostrar)
			Graphics.addLabel(g2, d.nome, x2 +50, y2 - raio / 2, Alinhamento.BottomCenter);

		if(e.mostrar)
			Graphics.addLabel(g2, e.nome, x3+50 , y3 + raio / 2, Alinhamento.TopCenter);

		Graphics.addLabel(g2, "r", 1200, 250, Alinhamento.BottomRight);
		Graphics.addLabel(g2, "s", 1200, 500, Alinhamento.BottomRight);
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
		return "ConfigTriangulo1:\n" + (a != null ? "a: " + a + "\n" : "") + (b != null ? "b: " + b + "\n" : "")
		+ (c != null ? "c: " + c : "");
	}

}
