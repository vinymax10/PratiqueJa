package Matematica.Avancado.FuncaoAfim.Config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import Auxiliar.Graphics;
import Matematica.Alinhamento;
import Matematica.ParCor;
import Matematica.Intermediario.SomaAngulosTriangulo.Config;


public class ConfigRetaReal extends Config
{
	private static int iniXImagem = 50;
	private static int iniYImagem = 50;
	private static int fimXImagem = 1200;
	private static int fimYImagem = 1200;

	private static int iniXGrafico = -10;
	private static int fimXGrafico = 10;
	private static int iniYGrafico = -10;
	private static int fimYGrafico = 10;
	
	double a,b;
	ParCor cores[];
	int index;
	Random rand=new Random();
	
	public int pontoAx,pontoAy,pontoBx,pontoBy;
	
	public ConfigRetaReal(double a, double b, int pontoAx, int pontoAy, int pontoBx, int pontoBy)
	{
		this.a = a;
		this.b = b;
		this.pontoAx = pontoAx;
		this.pontoAy = pontoAy;
		this.pontoBx = pontoBx;
		this.pontoBy = pontoBy;
	}

	public BufferedImage criarImagem(int index)
	{
		this.index=index;
		
		int sizeX = 1250;
		int sizeY = 1250;

		ParCor parCor = ParCor.parCor(index-1);
		BufferedImage image = new BufferedImage((int) sizeX, sizeY, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);

		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));

		inserirEixoCartesiano(g2);

		inserirReta(g2);

		inserirPontoXNegativo(pontoAx, pontoAy, g2);

		inserirPontoXPositivo(pontoBx, pontoBy, pontoAy, g2);
		
		return image;
	}
	
	private void inserirReta(Graphics2D g2)
	{
		int axImagem = (int) transladaX(iniXGrafico);
		int bxImagem = (int) transladaX(fimXGrafico);
		int ayImagem = (int) transladaY(a * iniXGrafico + b);
		int byImagem = (int) transladaY(a * fimXGrafico + b);

		Graphics.arrow(g2, axImagem, ayImagem, bxImagem, byImagem, false, false);

		g2.setColor(Color.decode(ParCor.parCor(index).getCorForte()));
	}

	private void inserirEixoCartesiano(Graphics2D g2)
	{
		Graphics.arrow(g2, iniXImagem, iniYImagem + (fimYImagem - iniYImagem) / 2, fimXImagem,
		iniYImagem + (fimYImagem - iniYImagem) / 2, false, true); // eixo X

		Graphics.arrow(g2, iniXImagem + (fimXImagem - iniXImagem) / 2, fimYImagem,
		iniXImagem + (fimXImagem - iniXImagem) / 2, iniYImagem, false, true); // eixo y

		g2.setColor(Color.decode(ParCor.parCor(index).getCorForte()));

		Graphics.addLabel(g2, "x", 1200, 650, Alinhamento.TopRight,70);
		Graphics.addLabel(g2, "y", 625, 0, Alinhamento.TopRight,70);
	}

	private void inserirPontoXPositivo(int pontoBx, int pontoBy, int pontoAy, Graphics2D g2)
	{
		g2.setColor(Color.decode(ParCor.parCor(index +1).getCorForte()));
		int size=30;

		Graphics.setLineTracejada(g2, transladaX(pontoBx), transladaY(pontoBy), transladaX(pontoBx), transladaY(0),
		false, false);

		if (pontoBy != 0)
			Graphics.setLineTracejada(g2, transladaX(pontoBx), transladaY(pontoBy), transladaX(0), transladaY(pontoBy),
			false, false);

		g2.setColor(Color.decode(ParCor.parCor(index-1).getCorForte()));
		Ellipse2D ponto = new Ellipse2D.Double(transladaX(pontoBx) - size/2, transladaY(pontoBy) - size/2, size, size);
		g2.fill(ponto);

		if (pontoBy > 0)
			Graphics.addLabel(g2, "" + (int) pontoBx, (int) (transladaX(pontoBx)), 
			(int) (transladaY(0)), Alinhamento.TopCenter,80);
		else
			Graphics.addLabel(g2, "" + (int) pontoBx, (int) (transladaX(pontoBx)), 
			(int) (transladaY(0)), Alinhamento.BottomCenter,80);

		if (pontoBy != pontoAy)
		{
			Graphics.addLabel(g2, "" + (int) pontoBy, (int) (transladaX(0)), 
			(int) (transladaY(pontoBy)), Alinhamento.MiddleRight,80);
		}
	}

	private void inserirPontoXNegativo(int pontoAx, int pontoAy, Graphics2D g2)
	{
		g2.setColor(Color.decode(ParCor.parCor(index +1).getCorForte()));

		int size=30;

		Graphics.setLineTracejada(g2, transladaX(pontoAx), transladaY(pontoAy), transladaX(pontoAx), transladaY(0),
		false, false);

		if (pontoAy != 0)
			Graphics.setLineTracejada(g2, transladaX(pontoAx), transladaY(pontoAy), transladaX(0), transladaY(pontoAy),
			false, false);

		g2.setColor(Color.decode(ParCor.parCor(index-1).getCorForte()));
		Ellipse2D ponto = new Ellipse2D.Double(transladaX(pontoAx) - size/2, transladaY(pontoAy) - size/2, size, size);
		g2.fill(ponto);

		if (pontoAy > 0)
			Graphics.addLabel(g2, "" + (int) pontoAx, (int) (transladaX(pontoAx)), 
			(int) (transladaY(0)), Alinhamento.TopCenter,80);
		else
			Graphics.addLabel(g2, "" + (int) pontoAx, (int) (transladaX(pontoAx)), 
			(int) (transladaY(0)), Alinhamento.BottomCenter,80);

		if (pontoAy != 0)
		{
			Graphics.addLabel(g2, "" + (int) pontoAy, (int) (transladaX(0)), 
			(int) (transladaY(pontoAy)), Alinhamento.MiddleLeft,80);
		}
	}

	private double transladaX(double x)
	{
		return iniXImagem
		+ ((double) (x - iniXGrafico) / (double) (fimXGrafico - iniXGrafico) * (fimXImagem - iniXImagem));
	}

	private double transladaY(double y)
	{
		return fimYImagem
		- ((double) (y - iniYGrafico) / (double) (fimYGrafico - iniYGrafico) * (fimYImagem - iniYImagem));
	}


}
