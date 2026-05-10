package matematica.intermediario.anguloinscritocircunferencia.config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.Alinhamento;
import matematica.ParCor;
import matematica.intermediario.somaangulostriangulo.Config;

public class Config5 extends Config
{
	DadosConfig5 dados;

	public Config5(DadosConfig5 dados)
	{
		super();
		this.dados = dados;
	}

	public BufferedImage criarImagem(int index)
	{
		int width=1250;
		int height=750;
		int raio = 200;
		
		ParCor parCor = ParCor.parCor(index-1);
		BufferedImage image = new BufferedImage((int) width,(int) height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);

		if(!dados.superiorEsq.equals(""))
			Graphics.setAngleSemBorda(g2, 310 - raio / 2, 150 - raio / 2, raio, -30, 25, ParCor.parCor(index + 2), parCor);

		if(!dados.superiorDir.equals(""))
			Graphics.setAngleSemBorda(g2, 860 - raio / 2, 180 - raio / 2, raio, 180, 30, ParCor.parCor(index + 1), parCor);
		
		if(!dados.superiorCentro.equals(""))
			Graphics.setAngleSemBorda(g2, 600 - raio / 2, (int) 330 - raio / 2, raio, 30, 118, 
			ParCor.parCor(index+3), parCor);

		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));

		Ellipse2D circulo = new Ellipse2D.Double(225, 25, 700, 700);
		g2.draw(circulo);

		Ellipse2D ponto = new Ellipse2D.Double(circulo.getCenterX() - 10, circulo.getCenterY() - 10, 20, 20);
		g2.fill(ponto);
		
		if(!dados.centroDir.equals(""))
			Graphics.setAngleSemBorda(g2, 600 - raio / 2, (int) 330 - raio / 2, raio, -32, 58, 
			ParCor.parCor(index), parCor);
		
		if(!dados.centroEsq.equals(""))
			Graphics.setAngleSemBorda(g2, 600 - raio / 2, (int) 330 - raio / 2, raio, -209, 60, 
			ParCor.parCor(index), parCor);
		
		Line2D line1 = new Line2D.Double(860, 180, 270, 530);
		g2.draw(line1);

		Line2D line2 = new Line2D.Double(310, 150, 890, 520);
		g2.draw(line2);

		if(!dados.superiorDir.equals("") || !dados.superiorEsq.equals(""))
		{
			Line2D line3 = new Line2D.Double(860, 180, 310, 150);
			g2.draw(line3);
		}
		
		Graphics.setArco(g2, (int)(circulo.getCenterX() - circulo.getWidth() / 2), 
		(int) (circulo.getCenterY() - circulo.getWidth() / 2), (int) circulo.getWidth(), 
		-23, 56, ParCor.parCor(index + 1), parCor);
		
		Graphics.setArco(g2, (int)(circulo.getCenterX() - circulo.getWidth() / 2), 
		(int)(circulo.getCenterY() - circulo.getWidth() / 2),(int) circulo.getWidth(),
		-155, -64,ParCor.parCor(index + 2),ParCor.parCor(index -1));
		
		if(!dados.lateralDir.equals(""))
			Graphics.addLabel(g2, dados.lateralDir, 925, 330, Alinhamento.MiddleLeft);

		if(!dados.centroDir.equals(""))
			Graphics.addLabel(g2, dados.centroDir, 600 + raio / 2, (int) 330, Alinhamento.MiddleLeft);
		
		if(!dados.centroEsq.equals(""))
			Graphics.addLabel(g2, dados.centroEsq, 600 - raio / 2 , (int) 330, Alinhamento.MiddleRight);
		
		if(!dados.lateralEsq.equals(""))
			Graphics.addLabel(g2, dados.lateralEsq, 225, 330, Alinhamento.MiddleRight);
		
		if(!dados.superiorEsq.equals(""))
			Graphics.addLabel(g2, dados.superiorEsq, 290+raio/2, 185, Alinhamento.MiddleLeft);
		
		if(!dados.superiorCentro.equals(""))
			Graphics.addLabel(g2, dados.superiorCentro, 600, 340-raio/2, Alinhamento.BottomCenter);

		if(!dados.superiorDir.equals(""))
			Graphics.addLabel(g2, dados.superiorDir, 875-raio/2, 200, Alinhamento.MiddleRight);

		return image;
	}

	public static void main(String[] args)
	{
//		DadosConfig5 dados = new DadosConfig5();
//		dados.lateralEsq = "a";
//		dados.centroEsq = "b";
//		dados.centroDir = "c";
//		dados.lateralDir = "d";
//		dados.superiorDir = "e";
//		dados.superiorEsq = "f";
//		dados.superiorCentro = "x";
//		
//		Config5 config = new Config5(dados);
//		BufferedImage image = config.criarImagem(1);
//
//		Graphics.salvar(image, true, "areaPoligono.PNG");
	}

}
