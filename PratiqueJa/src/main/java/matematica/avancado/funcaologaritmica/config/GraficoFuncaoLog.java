package matematica.avancado.funcaologaritmica.config;

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

public class GraficoFuncaoLog extends Config
{
	private static final int INI_X_IMG = 50;
	private static final int INI_Y_IMG = 50;
	private static final int FIM_X_IMG = 1200;
	private static final int FIM_Y_IMG = 1200;

	// Logaritmo só existe para x > 0; eixo y (x=0) é a assíntota vertical
	private static final double INI_X_GRF = -0.5;
	private static final double FIM_X_GRF = 10.5;
	private static final double INI_Y_GRF = -3.5;
	private static final double FIM_Y_GRF = 3.5;

	private final DadosFuncaoLog dados;
	private final int            indice;

	public GraficoFuncaoLog(DadosFuncaoLog dados, int indice)
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

		// eixo X
		Graphics.arrow(g2,
			(int) tx(INI_X_GRF), (int) ty(0),
			(int) tx(FIM_X_GRF), (int) ty(0), false, true);

		// eixo Y (também é a assíntota vertical da função logarítmica)
		Graphics.arrow(g2,
			(int) tx(0), (int) ty(INI_Y_GRF),
			(int) tx(0), (int) ty(FIM_Y_GRF), false, true);

		Graphics.addLabel(g2, "x", (int) tx(FIM_X_GRF), (int) ty(0), Alinhamento.TopRight, 70);
		Graphics.addLabel(g2, "y", (int) tx(0), INI_Y_IMG, Alinhamento.TopRight, 70);
	}

	public void inserirCurva(Graphics2D g2)
	{
		g2.setColor(Color.decode(ParCor.parCor(indice + 1).getCorForte()));
		g2.setStroke(new BasicStroke(12));

		Path2D p = new Path2D.Double();
		double passo  = (FIM_X_GRF - 0.05) / 300;
		boolean primeira = true;

		// Curva apenas para x > 0 (domínio do logaritmo)
		for (double x = 0.05; x <= FIM_X_GRF; x += passo)
		{
			double y = Math.log(x) / Math.log(dados.base);
			if (y < INI_Y_GRF || y > FIM_Y_GRF) continue;
			if (primeira) { p.moveTo(tx(x), ty(y)); primeira = false; }
			else          { p.lineTo(tx(x), ty(y)); }
		}
		g2.draw(p);
	}

	public void inserirPontoDestacado(Graphics2D g2, String label)
	{
		double px = dados.pontoX;
		double py = dados.pontoY;
		int raio  = 30;

		g2.setColor(Color.decode(ParCor.parCor(indice - 1).getCorForte()));

		// linha tracejada vertical até eixo x
		Graphics.setLineTracejada(g2, tx(px), ty(py), tx(px), ty(0), false, false);

		// linha tracejada horizontal até eixo y
		Graphics.setLineTracejada(g2, tx(px), ty(py), tx(0), ty(py), false, false);

		// ponto
		Ellipse2D ponto = new Ellipse2D.Double(tx(px) - raio / 2.0, ty(py) - raio / 2.0, raio, raio);
		g2.fill(ponto);

		// label no eixo x
		if (dados.crescente)
			Graphics.addLabel(g2, "" + dados.pontoX, (int) tx(px), (int) ty(0), Alinhamento.BottomCenter, 70);
		else
			Graphics.addLabel(g2, "" + dados.pontoX, (int) tx(px), (int) ty(0), Alinhamento.TopCenter, 70);

		// label no eixo y (valor do ponto) — pontoX sempre positivo no logaritmo
		Graphics.addLabel(g2, label, (int) tx(0), (int) ty(py), Alinhamento.MiddleLeft, 70);
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
