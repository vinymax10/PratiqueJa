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


public class ConfigTrapezio extends Config
{
	Angulo a, b, c, d, e;

	public ConfigTrapezio(int a, int b, int c, int d, int e)
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
		int raioAngulo=50;
		
		if (a.mostrar)
			Graphics.setAngleSemBorda(g2, 50 - raio / 2, 700 - raio / 2, raio, 0, 70,
			ParCor.parCor(index + a.ordemInsercao), ParCor.parCor(index-1));

		if (b.mostrar)
			Graphics.setAngleSemBorda(g2, 275 - raio / 2, 50 - raio / 2, raio, 251, 71,
			ParCor.parCor(index + b.ordemInsercao), ParCor.parCor(index-1));

		if (c.mostrar)
			Graphics.setAngleSemBorda(g2, 275 - raio / 2, 50 - raio / 2, raio, 316, 44,
			ParCor.parCor(index + c.ordemInsercao), ParCor.parCor(index-1));

		if (d.mostrar)
			Graphics.setAngleSemBorda(g2, 925 - raio / 2, 700 - raio / 2, raio, 180, -45,
			ParCor.parCor(index + d.ordemInsercao), ParCor.parCor(index-1));

		if (e.mostrar)
			Graphics.setAngleSemBorda(g2, 925 - raio / 2, 700 - raio / 2, raio, 135, -46,
			ParCor.parCor(index + e.ordemInsercao), ParCor.parCor(index-1));

		Polygon paralelogramo = new Polygon();
		paralelogramo.addPoint(50, 700);
		paralelogramo.addPoint(925, 700);
		paralelogramo.addPoint(925, 50);
		paralelogramo.addPoint(275, 50);
		g2.draw(paralelogramo);
		
		Graphics.addAngleReto(g2, 925 -raioAngulo, 50, raioAngulo);
		Graphics.arrow(g2, 277, 52, 923, 698, false, false);
		Graphics.arrow(g2, 925, 700, 1200, 700, false, true);
		Graphics.addAngleReto(g2, 925 , 700-raioAngulo, raioAngulo);

		if (a.mostrar)
			Graphics.addLabel(g2, a.nome, 30 +raio / 2, 650, Alinhamento.BottomLeft);

		if (b.mostrar)
			Graphics.addLabel(g2, b.nome, 310 , 50 + raio / 2, Alinhamento.TopCenter);

		if (c.mostrar)
			Graphics.addLabel(g2, c.nome, 275 + raio / 2, 50 , Alinhamento.TopLeft);

		if (d.mostrar)
			Graphics.addLabel(g2, d.nome, 925 - raio / 2, 700 , Alinhamento.BottomRight);

		if (e.mostrar)
			Graphics.addLabel(g2, e.nome, 925 , 700 - raio / 2, Alinhamento.BottomRight,75);

		return image;
	}

	public String getTextLatex()
	{
		return a.nome + "-" + b.nome + "-" + c.nome + "-" + d.nome + "-" + e.nome;
	}

	@Override
	public String toString()
	{
		return "Config:\n" + (a != null ? "a: " + a + "\n" : "") + (b != null ? "b: " + b + "\n" : "")
		+ (c != null ? "c: " + c + "\n" : "") + (d != null ? "d: " + d + "\n" : "")
		+ (e != null ? "e: " + e + "\n" : "");
	}

}
