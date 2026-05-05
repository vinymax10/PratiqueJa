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


public class ConfigTriangulo11 extends Config
{
	public Angulo a, b, c;

	public ConfigTriangulo11(int a, int b, int c)
	{
		this.a = new Angulo(this, a, false, a + "°");
		this.b = new Angulo(this, b, false, b + "°");
		this.c = new Angulo(this, c, false, c + "°");
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
			Graphics.setAngleSemBorda(g2, 50 - raio / 2, 50 - raio / 2, raio, 0, -69,
			ParCor.parCor(index + a.ordemInsercao), ParCor.parCor(index-1));

		if (b.mostrar)
			Graphics.setAngleSemBorda(g2, 300 - raio / 2, 700 - raio / 2, raio, 35, 76,
			ParCor.parCor(index + b.ordemInsercao), ParCor.parCor(index-1));

		if (c.mostrar)
			Graphics.setAngleSemBorda(g2, 1200 - raio / 2, 50 - raio / 2, raio, 180, 35,
			ParCor.parCor(index + c.ordemInsercao), ParCor.parCor(index-1));

		Polygon triangulo = new Polygon();
		triangulo.addPoint(50, 50);
		triangulo.addPoint(1200, 50);
		triangulo.addPoint(300, 700);
		g2.draw(triangulo);

//		if (a.mostrar)
			Graphics.addLabel(g2, a.nome, 30 + raio / 2, 70, Alinhamento.TopLeft);

//		if (b.mostrar)
			Graphics.addLabel(g2, b.nome, 350, 700 - raio / 2, Alinhamento.BottomCenter);

//		if (c.mostrar)
			Graphics.addLabel(g2, c.nome, 1200- raio / 2, 50, Alinhamento.TopRight);

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
