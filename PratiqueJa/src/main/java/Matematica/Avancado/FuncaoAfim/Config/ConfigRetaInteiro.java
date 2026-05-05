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


public class ConfigRetaInteiro extends Config
{
	private static int iniXImagem = 50;
	private static int iniYImagem = 50;
	private static int fimXImagem = 1200;
	private static int fimYImagem = 1200;

	private static int iniXGrafico = -10;
	private static int fimXGrafico = 10;
	private static int iniYGrafico = -10;
	private static int fimYGrafico = 10;
	
	int a,b;
	ParCor cores[];
	int index;
	Random rand=new Random();
	
	public double pontoAx,pontoAy,pontoBx,pontoBy;
	
	public ConfigRetaInteiro(int a, int b)
	{
		super();
		this.a = a;
		this.b = b;
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

		inserirPontoXNegativo(g2);

		inserirPontoXPositivo(g2);

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

	private void inserirPontoXPositivo(Graphics2D g2)
	{
		double limiteInferior = 2;
		double limiteSuperior = 9;
		double limiteX;
		int size=30;

		if (a == 0)
			limiteX = limiteSuperior;
		else
		{
			if (a > 0)
				limiteX = Math.min(((fimYGrafico - 1) - b) / a, limiteSuperior);
			else
				limiteX = Math.min(((iniYGrafico + 1) - b) / a, limiteSuperior);
		}

		pontoBx = limiteInferior + rand.nextInt((int) (Math.abs(limiteX - limiteInferior) + 1));
		if (a == 0 && pontoBx == 2)
			pontoBx++;

		pontoBy = a * pontoBx + b;

		g2.setColor(Color.decode(ParCor.parCor(index +1).getCorForte()));

		Graphics.setLineTracejada(g2, transladaX(pontoBx), transladaY(pontoBy), transladaX(pontoBx), transladaY(0),
		false, false);
//		
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

		if (pontoBy != 0 && a != 0)
		{
			Graphics.addLabel(g2, "" + (int) pontoBy, (int) (transladaX(0)), 
			(int) (transladaY(pontoBy)), Alinhamento.MiddleRight,80);
		}
	}

	private void inserirPontoXNegativo(Graphics2D g2)
	{
		double limiteInferior = -9;
		double limiteSuperior = -1;
		double limiteX;

		int size=30;
		
		if (a == 0)
			limiteX = limiteInferior;
		else
		{
			if (a > 0)
				limiteX = Math.max(((iniYGrafico + 1) - b) / a, limiteInferior);
			else
				limiteX = Math.max(((fimYGrafico - 2) - b) / a, limiteInferior);
		}
		pontoAx = limiteX + rand.nextInt((int) (Math.abs(limiteX - limiteSuperior) + 1));

		pontoAy = a * pontoAx + b;

		g2.setColor(Color.decode(ParCor.parCor(index +1).getCorForte()));

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
