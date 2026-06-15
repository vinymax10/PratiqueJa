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


public class ConfigSemelhancaAngulos1 extends Config
{
	Angulo a, b, c, d;

	public ConfigSemelhancaAngulos1(int a, int b, int c, int d)
	{
		this.a = new Angulo(this, a, false, a + "°");
		this.b = new Angulo(this, b, false, b + "°");
		this.c = new Angulo(this, c, false, c + "°");
		this.d = new Angulo(this, d, false, d + "°");
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

		int iniX = 50, iniY = 50, endX = iniX + 1150, endY = iniY + 650;
		int iniX2 = 50, endY2 = 50, endX2 = iniX2 + 1150, iniY2 = endY2 + 650;
		int raio = 200;

		int x = (iniX + (endX - iniX) / 2) - raio / 2;
		int y = (iniY + (endY - iniY) / 2) - raio / 2;

		if(a.mostrar)
			Graphics.setAngleSemBorda(g2, x, y, raio, 150, 60, 
			ParCor.parCorAleatorio(),	ParCor.parCorAleatorio());

		if(b.mostrar)
			Graphics.setAngleSemBorda(g2, x, y, raio, -30, 60, 
			ParCor.parCorAleatorio(),	ParCor.parCorAleatorio());

		if(c.mostrar)
			Graphics.setAngleSemBorda(g2, x, y, raio, 30, 120, 
			ParCor.parCorAleatorio(),	ParCor.parCorAleatorio());

		if(d.mostrar)
			Graphics.setAngleSemBorda(g2, x, y, raio, 210, 120, 
			ParCor.parCorAleatorio(),	ParCor.parCorAleatorio());
		
		Graphics.arrow(g2, iniX, iniY, endX, endY, true, true);

		Graphics.arrow(g2, iniX2, iniY2, endX2, endY2, true, true);
		
		x = (iniX + (endX - iniX) / 2);
		y = (iniY + (endY - iniY) / 2);

		if(a.mostrar)
			Graphics.addLabel(g2, a.nome, x - raio / 2, y, Alinhamento.MiddleRight);

		if(b.mostrar)
			Graphics.addLabel(g2, b.nome, x + raio / 2, y, Alinhamento.MiddleLeft);

		if(c.mostrar)
			Graphics.addLabel(g2, c.nome, x, y - raio / 2, Alinhamento.BottomCenter);

		if(d.mostrar)
			Graphics.addLabel(g2, d.nome, x, y + raio / 2, Alinhamento.TopCenter);

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
