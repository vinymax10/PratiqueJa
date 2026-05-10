package matematica.basico.conjuntos.nivel2package;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;

public class ConfigAB
{
	DadosAB dados;
	
	public ConfigAB(DadosAB dados) {
		this.dados=dados;
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
		
		int raio=350;
		int diametro=raio*2;
		
		Ellipse2D circuloA = new Ellipse2D.Double(25, 25, diametro, diametro);
		g2.draw(circuloA);
		
		Ellipse2D circuloB = new Ellipse2D.Double(500, 25, diametro, diametro);
		g2.draw(circuloB);		
		
		Graphics.addLabel(g2, "A", (int) (circuloA.getCenterX()-(raio*Math.cos(Math.toRadians(45)))),
		(int) (circuloA.getCenterY()-(raio*Math.cos(Math.toRadians(45)))), Alinhamento.TopLeft);
		
		Graphics.addLabel(g2, "B", (int) (circuloB.getCenterX()+(raio*Math.cos(Math.toRadians(45)))),
		(int) (circuloB.getCenterY()-(raio*Math.cos(Math.toRadians(45)))), Alinhamento.TopRight);
		
		if(!dados.aMbStr.equals(""))
			Graphics.addLabel(g2, dados.aMbStr, (int) circuloA.getCenterX(),
			(int) circuloA.getCenterY(), Alinhamento.MiddleRight);

		if(!dados.bMaStr.equals(""))
			Graphics.addLabel(g2, dados.bMaStr, (int) circuloB.getCenterX(),
			(int) circuloA.getCenterY(), Alinhamento.MiddleLeft);
		
		if(!dados.aIbStr.equals(""))
			Graphics.addLabel(g2, dados.aIbStr, 612,
			(int) circuloA.getCenterY(), Alinhamento.MiddleCenter);
		
		return image;
	}
	

}
