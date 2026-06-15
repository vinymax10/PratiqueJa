package matematica.intermediario.funcoes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;

public class ImagemDiagrama
{
	// Dimensões da tela
	private static final int W  = 1250;
	private static final int H  = 750;
	// Ovals
	private static final int OY = 50;
	private static final int OW = 380;
	private static final int OH = 600;
	private static final int OX_A = 50;
	private static final int OX_B = 770;

	public static BufferedImage criar(DiagramaFuncao d, int corIndex)
	{
		BufferedImage img = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = img.createGraphics();
		Graphics.setHint(g2);

		ParCor cor = ParCor.parCorAleatorio();
		g2.setColor(Color.decode(cor.getCorForte()));
		g2.setStroke(new BasicStroke(8));

		// Desenha os dois ovais
		Ellipse2D ovalA = new Ellipse2D.Double(OX_A, OY, OW, OH);
		Ellipse2D ovalB = new Ellipse2D.Double(OX_B, OY, OW, OH);
		g2.draw(ovalA);
		g2.draw(ovalB);

		int cx_A = OX_A + OW / 2;   // 240
		int cx_B = OX_B + OW / 2;   // 960

		// Centralizados no topo de cada oval, 65px abaixo do ponto mais alto (claramente dentro da elipse)
		Graphics.addLabel(g2, "A", 10, OY + 10, Alinhamento.TopLeft);
		Graphics.addLabel(g2, "B", W-10, OY + 10, Alinhamento.TopRight);

		// Posições Y dos elementos de A e B
		int nA = d.dominio.length;
		int[] yA = new int[nA];
		for(int i = 0; i < nA; i++)
			yA[i] = OY + OH * (i + 1) / (nA + 1);

		int nB = d.contradominio.length;
		int[] yB = new int[nB];
		for(int j = 0; j < nB; j++)
			yB[j] = OY + OH * (j + 1) / (nB + 1);

		// Rótulos dos elementos
		for(int i = 0; i < nA; i++)
			Graphics.addLabel(g2, "" + d.dominio[i], cx_A, yA[i], Alinhamento.MiddleCenter);
		for(int j = 0; j < nB; j++)
			Graphics.addLabel(g2, "" + d.contradominio[j], cx_B, yB[j], Alinhamento.MiddleCenter);

		// Setas: partem de imediatamente após o rótulo do elemento em A e terminam
		// imediatamente antes do rótulo em B, usando offset fixo calculado pela
		// largura real do label (size 90 = 5× o size 18 usado por widthLabel).
		for(int i = 0; i < nA; i++)
		{
			int labelWA = Graphics.widthLabel("" + d.dominio[i]) * 5;
			int xStart  = cx_A + labelWA / 2 + 8;

			for(int t : d.mapeamento[i])
			{
				int labelWB = Graphics.widthLabel("" + d.contradominio[t]) * 5;
				int xEnd    = cx_B - labelWB / 2 - 8;
				Graphics.arrow(g2, xStart, yA[i], xEnd, yB[t], false, true);
			}
		}

		g2.dispose();
		return img;
	}
}
