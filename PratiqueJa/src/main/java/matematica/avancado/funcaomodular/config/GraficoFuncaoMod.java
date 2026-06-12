package matematica.avancado.funcaomodular.config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;
import matematica.basico.somaangulostriangulo.Config;

public class GraficoFuncaoMod extends Config
{
	private static final int INI_X_IMG = 50;
	private static final int INI_Y_IMG = 50;
	private static final int FIM_X_IMG = 1200;
	private static final int FIM_Y_IMG = 1200;

	private static final double INI_X_GRF = -5.5;
	private static final double FIM_X_GRF = 5.5;
	private static final double INI_Y_GRF = -0.5;
	private static final double FIM_Y_GRF = 9.0;

	private final DadosFuncaoMod dados;
	private final int            indice;

	public GraficoFuncaoMod(DadosFuncaoMod dados, int indice)
	{
		this.dados  = dados;
		this.indice = indice;
	}

	public Graphics2D carregamentoInicial(BufferedImage image)
	{
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);
		g2.setColor(Color.decode(ParCor.parCor(indice - 1).getCorForte()));
		g2.setStroke(new BasicStroke(10));
		return g2;
	}

	public void inserirEixos(Graphics2D g2)
	{
		g2.setColor(Color.decode(ParCor.parCor(indice).getCorForte()));
		g2.setStroke(new BasicStroke(10));

		Graphics.arrow(g2,
			(int) tx(INI_X_GRF), (int) ty(0),
			(int) tx(FIM_X_GRF), (int) ty(0), false, true);

		Graphics.arrow(g2,
			(int) tx(0), (int) ty(INI_Y_GRF),
			(int) tx(0), (int) ty(FIM_Y_GRF), false, true);

		Graphics.addLabel(g2, "x", (int) tx(FIM_X_GRF), (int) ty(0), Alinhamento.TopRight, 70);
		Graphics.addLabel(g2, "y", (int) tx(0), INI_Y_IMG, Alinhamento.TopRight, 70);
	}

	/** Desenha o V: dois segmentos de reta convergindo no vértice (h, k). */
	public void inserirCurva(Graphics2D g2)
	{
		g2.setColor(Color.decode(ParCor.parCor(indice + 1).getCorForte()));
		g2.setStroke(new BasicStroke(12));

		// Braço esquerdo: inclina de (xLeft, yLeft) até vértice
		double xLeft  = INI_X_GRF;
		double yLeft  = Math.abs(INI_X_GRF - dados.h) + dados.k;
		// Braço direito: sai do vértice até (xRight, yRight)
		double xRight = FIM_X_GRF;
		double yRight = Math.abs(FIM_X_GRF - dados.h) + dados.k;

		// Clamp ao viewport vertical
		if (yLeft > FIM_Y_GRF)
		{
			xLeft = dados.h - (FIM_Y_GRF - dados.k);
			yLeft = FIM_Y_GRF;
		}
		if (yRight > FIM_Y_GRF)
		{
			xRight = dados.h + (FIM_Y_GRF - dados.k);
			yRight = FIM_Y_GRF;
		}

		Path2D path = new Path2D.Double();
		path.moveTo(tx(xLeft),   ty(yLeft));
		path.lineTo(tx(dados.h), ty(dados.k));
		path.lineTo(tx(xRight),  ty(yRight));
		g2.draw(path);
	}

	/**
	 * Desenha uma linha horizontal contínua em y=yVal com rótulo no eixo y.
	 * Use para representar o limiar k em exercícios de inequação modular.
	 */
	public void inserirLinhaHorizontal(Graphics2D g2, int yVal, String label)
	{
		g2.setColor(Color.decode(ParCor.parCor(indice + 2).getCorForte()));
		float[] dash = {25.0f, 12.0f};
		g2.setStroke(new BasicStroke(8, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));

		Path2D line = new Path2D.Double();
		line.moveTo(tx(INI_X_GRF), ty(yVal));
		line.lineTo(tx(FIM_X_GRF), ty(yVal));
		g2.draw(line);

		// Rótulo do valor no eixo y (lado esquerdo do eixo)
		g2.setStroke(new BasicStroke(10));
		Graphics.addLabel(g2, label, (int) tx(0), (int) ty(yVal), Alinhamento.MiddleRight, 70);
	}

	/** Destaca o ponto (pontoX, pontoY) com linhas tracejadas e rótulo no eixo y. */
	public void inserirPontoDestacado(Graphics2D g2, String labelY)
	{
		double px  = dados.pontoX;
		double py  = dados.pontoY;
		int raio   = 30;

		g2.setColor(Color.decode(ParCor.parCor(indice - 1).getCorForte()));

		// linha tracejada vertical ao eixo x
		Graphics.setLineTracejada(g2, tx(px), ty(py), tx(px), ty(0), false, false);

		// linha tracejada horizontal ao eixo y
		Graphics.setLineTracejada(g2, tx(px), ty(py), tx(0), ty(py), false, false);

		// ponto
		Ellipse2D ponto = new Ellipse2D.Double(tx(px) - raio / 2.0, ty(py) - raio / 2.0, raio, raio);
		g2.fill(ponto);

		// rótulo no eixo x (sempre abaixo do eixo, pois f(x) >= 0)
		Graphics.addLabel(g2, "" + dados.pontoX, (int) tx(px), (int) ty(0), Alinhamento.BottomCenter, 70);

		// rótulo no eixo y
		if (px > 0)
			Graphics.addLabel(g2, labelY, (int) tx(0), (int) ty(py), Alinhamento.MiddleLeft, 70);
		else
			Graphics.addLabel(g2, labelY, (int) tx(0), (int) ty(py), Alinhamento.MiddleRight, 70);
	}

	private double tx(double x)
	{
		return INI_X_IMG + (x - INI_X_GRF) / (FIM_X_GRF - INI_X_GRF) * (FIM_X_IMG - INI_X_IMG);
	}

	private double ty(double y)
	{
		return FIM_Y_IMG - (y - INI_Y_GRF) / (FIM_Y_GRF - INI_Y_GRF) * (FIM_Y_IMG - INI_Y_IMG);
	}
}
