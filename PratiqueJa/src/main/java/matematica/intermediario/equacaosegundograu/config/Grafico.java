package matematica.intermediario.equacaosegundograu.config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import infra.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;
import matematica.basico.somaangulostriangulo.Config;


public class Grafico extends Config
{
	private int iniXImagem = 50;
	private int iniYImagem = 50;
	private int fimXImagem = 1200;
	private int fimYImagem = 700;
	double fimXGrafico, iniXGrafico;
	double iniYGrafico, fimYGrafico;
	Random rand=new Random();

//	ax^2+bx+c
	DadosEq2Grau dadosEq2Grau;

	public Grafico(DadosEq2Grau dadosEq2Grau)
	{
		super();
		this.dadosEq2Grau=dadosEq2Grau;
	}

	public Graphics2D carregamentoInicial(BufferedImage image)
	{
		ParCor parCor = ParCor.parCorAleatorio();
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);

		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));
		
		double lastro;
		if (dadosEq2Grau.x2 > 0)
			lastro = dadosEq2Grau.x2 + 4;
		else
			lastro = Math.abs(dadosEq2Grau.x1 - dadosEq2Grau.x2);

		fimXGrafico = dadosEq2Grau.x1 + lastro;
		iniXGrafico = dadosEq2Grau.x2 - lastro;

		if (dadosEq2Grau.a > 0)
		{
			fimYGrafico = f(fimXGrafico);
			iniYGrafico = dadosEq2Grau.yVertice - (Math.abs(fimYGrafico) * 0.1);
		}
		else
		{
			iniYGrafico = f(fimXGrafico);
			fimYGrafico = dadosEq2Grau.yVertice + (Math.abs(iniYGrafico) * 0.1);
		}
		
		return g2;
	}
	
	public double f(double x)
	{
		return (dadosEq2Grau.a * x * x) + (dadosEq2Grau.b * x) + dadosEq2Grau.c;
	}

	public void inserirCurva(Graphics2D g2)
	{
		Path2D p = new Path2D.Double();

		p.moveTo(transladaX(iniXGrafico), transladaY(f(iniXGrafico)));

		double passo = (fimXGrafico - iniXGrafico) / 100;
		for (double i = iniXGrafico; i < fimXGrafico; i += passo)
		{
			p.lineTo(transladaX(i), transladaY(f(i)));
		}

		p.lineTo(transladaX(fimXGrafico), transladaY(f(fimXGrafico)));

		g2.setColor(Color.decode(ParCor.parCorAleatorio().getCorForte()));

		g2.draw(p);
	}

	public void inserirEixoCartesiano(Graphics2D g2)
	{
		Graphics.arrow(g2, (int) transladaX(iniXGrafico), (int) transladaY(0), (int) transladaX(fimXGrafico),
		(int) transladaY(0), false, true); // eixo X

		Graphics.arrow(g2, (int) transladaX(0), (int) transladaY(iniYGrafico), (int) transladaX(0),
		(int) transladaY(fimYGrafico), false, true); // eixo y

		g2.setColor(Color.decode(ParCor.parCorAleatorio().getCorForte()));

		Graphics.addLabel(g2, "x", 1200, (int) transladaY(0), Alinhamento.TopRight);
		Graphics.addLabel(g2, "y", (int) transladaX(0), 0, Alinhamento.TopRight);
	}
	
	public void inserirPontoXVertice(Graphics2D g2, String label)
	{

		int raio = 30;
		g2.setColor(Color.decode(ParCor.parCorAleatorio().getCorForte()));

		Ellipse2D ponto = new Ellipse2D.Double((int) transladaX(dadosEq2Grau.xVertice) - raio / 2,
		(int) transladaY(dadosEq2Grau.yVertice) - (raio / 2), raio, raio);
		g2.fill(ponto);

		Graphics.setLineTracejada(g2, transladaX(dadosEq2Grau.xVertice), 
		transladaY(dadosEq2Grau.yVertice), transladaX(dadosEq2Grau.xVertice), transladaY(0),
		false, false);

		if (dadosEq2Grau.a > 0)
			Graphics.addLabel(g2, label,  (int) (transladaX(dadosEq2Grau.xVertice)), (int) (transladaY(0)), Alinhamento.BottomCenter);
		else
			Graphics.addLabel(g2, label,  (int) (transladaX(dadosEq2Grau.xVertice)), (int) (transladaY(0)), Alinhamento.TopCenter);
	}
	
	public void inserirPontoYVertice(Graphics2D g2, String label)
	{
		int raio = 30;
		g2.setColor(Color.decode(ParCor.parCorAleatorio().getCorForte()));

		Ellipse2D ponto = new Ellipse2D.Double((int) transladaX(dadosEq2Grau.xVertice) - raio / 2,
		(int) transladaY(dadosEq2Grau.yVertice) - (raio / 2), raio, raio);
		g2.fill(ponto);

		Graphics.setLineTracejada(g2, transladaX(dadosEq2Grau.xVertice), 
		transladaY(dadosEq2Grau.yVertice), transladaX(0), transladaY(dadosEq2Grau.yVertice),
		false, false);

		if (dadosEq2Grau.xVertice > 0)
			Graphics.addLabel(g2, label,  (int) (transladaX(0)), (int) (transladaY(dadosEq2Grau.yVertice)), 
			Alinhamento.MiddleRight);
		else
			Graphics.addLabel(g2, label,  (int) (transladaX(0)), (int) (transladaY(dadosEq2Grau.yVertice)), 
			Alinhamento.MiddleLeft);
	}
	
	public void inserirPontoX1(Graphics2D g2, String label)
	{
		int raio = 30;
		g2.setColor(Color.decode(ParCor.parCorAleatorio().getCorForte()));
		Ellipse2D ponto = new Ellipse2D.Double((int) transladaX(dadosEq2Grau.x1) - raio / 2, (int) transladaY(0) - (raio / 2), raio,
		raio);
		g2.fill(ponto);

		if (dadosEq2Grau.a > 0)
			Graphics.addLabel(g2, label,  (int) (transladaX(dadosEq2Grau.x1)), (int) (transladaY(0)), Alinhamento.BottomCenter);
		else
			Graphics.addLabel(g2, label,  (int) (transladaX(dadosEq2Grau.x1)), (int) (transladaY(0)), Alinhamento.TopCenter);
	}

	public void inserirPontoX2(Graphics2D g2, String label)
	{
		int raio = 30;
		g2.setColor(Color.decode(ParCor.parCorAleatorio().getCorForte()));
		Ellipse2D ponto = new Ellipse2D.Double((int) transladaX(dadosEq2Grau.x2) - raio / 2, (int) transladaY(0) - (raio / 2), raio,
		raio);
		g2.fill(ponto);

		if (dadosEq2Grau.a > 0)
			Graphics.addLabel(g2, label,  (int) (transladaX(dadosEq2Grau.x2)), (int) (transladaY(0)), Alinhamento.BottomCenter);
		else
			Graphics.addLabel(g2, label,  (int) (transladaX(dadosEq2Grau.x2)), (int) (transladaY(0)), Alinhamento.TopCenter);
	}

	public void inserirPontoC(Graphics2D g2, String label)
	{
		int raio = 30;
		g2.setColor(Color.decode(ParCor.parCorAleatorio().getCorForte()));
		Ellipse2D ponto = new Ellipse2D.Double((int) transladaX(0) - raio / 2, (int) transladaY(dadosEq2Grau.c) - (raio / 2), raio,
		raio);
		g2.fill(ponto);

		Graphics.addLabel(g2, label,  (int) (transladaX(0)), (int) (transladaY(dadosEq2Grau.c)), Alinhamento.MiddleRight);
	}

	public double transladaX(double x)
	{
		return iniXImagem
		+ ((double) (x - iniXGrafico) / (double) (fimXGrafico - iniXGrafico) * (fimXImagem - iniXImagem));
	}

	public double transladaY(double y)
	{
		return fimYImagem
		- ((double) (y - iniYGrafico) / (double) (fimYGrafico - iniYGrafico) * (fimYImagem - iniYImagem));
	}

}
