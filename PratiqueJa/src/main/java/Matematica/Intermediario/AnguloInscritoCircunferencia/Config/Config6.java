package Matematica.Intermediario.AnguloInscritoCircunferencia.Config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Alinhamento;
import Matematica.ParCor;
import Matematica.Intermediario.SomaAngulosTriangulo.Config;

public class Config6 extends Config
{
	DadosConfig6 dados;

	public Config6(DadosConfig6 dados)
	{
		super();
		this.dados = dados;
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

		Ellipse2D circulo = new Ellipse2D.Double(190, 25, 500, 500);
		g2.draw(circulo);

		Ellipse2D ponto = new Ellipse2D.Double(circulo.getCenterX() - 10, circulo.getCenterY() - 10, 20, 20);
		g2.fill(ponto);

		int raio = 250;
		if(!dados.anguloExterno.equals(""))
			Graphics.setAngleSemBorda(g2, 1240 - raio / 2, (int) circulo.getCenterY() - raio / 2, raio,
			-190, 23, ParCor.parCor(index), parCor);
//
		raio = 200;
		if(!dados.superiorDir.equals(""))
			Graphics.setAngleSemBorda(g2, 655 - raio / 2, 155 - raio / 2, raio,
			-140, 130, ParCor.parCor(index+2), parCor);
		
		if(!dados.superiorEsq.equals(""))
			Graphics.setAngleSemBorda(g2, 655 - raio / 2, 155 - raio / 2, raio,
			-195, 60, ParCor.parCor(index+1), parCor);
		
		if(!dados.inferiorEsq.equals(""))
			Graphics.setAngleSemBorda(g2, 300 - raio / 2, 475 - raio / 2, raio,
			10, 30, ParCor.parCor(index+3), parCor);
		
		Line2D line1 = new Line2D.Double(300, 75, 1240, circulo.getCenterY());
		g2.draw(line1);

		Line2D line2 = new Line2D.Double(300, 475, 1240, circulo.getCenterY());
		g2.draw(line2);

		if(!dados.superiorDir.equals("") || !dados.superiorEsq.equals(""))
		{
			Line2D line3 = new Line2D.Double(300, 475, 655, 155);
			g2.draw(line3);
		}
		
		Graphics.setArco(g2, (int)(circulo.getCenterX() - circulo.getWidth() / 2),
		(int)(circulo.getCenterY() - circulo.getWidth() / 2), (int) circulo.getWidth(), 
		-27, 53, ParCor.parCor(index + 1), parCor);
		
		Graphics.setArco(g2, (int)(circulo.getCenterX() - circulo.getWidth() / 2),
		(int)(circulo.getCenterY() - circulo.getWidth() / 2), (int) circulo.getWidth(), 
		-127, -105, ParCor.parCor(index + 2), parCor);
		
		if(!dados.lateralDir.equals(""))
			Graphics.addLabel(g2, dados.lateralDir, 690, (int) circulo.getCenterY(), Alinhamento.MiddleRight);

		if(!dados.superiorEsq.equals(""))
			Graphics.addLabel(g2, dados.superiorEsq, 660 - raio / 2 , 175, Alinhamento.MiddleRight);
		
		if(!dados.superiorDir.equals(""))
			Graphics.addLabel(g2, dados.superiorDir, 590 + raio / 2 , 200, Alinhamento.TopLeft);
		
		if(!dados.lateralEsq.equals(""))
			Graphics.addLabel(g2, dados.lateralEsq, 190, 275, Alinhamento.MiddleRight);
				
		if(!dados.anguloExterno.equals(""))
			Graphics.addLabel(g2, dados.anguloExterno, 1240 - raio / 2,
			(int) circulo.getCenterY(), Alinhamento.MiddleRight);
		
		if(!dados.inferiorEsq.equals(""))
			Graphics.addLabel(g2, dados.inferiorEsq, 280 + raio / 2,
			415, Alinhamento.MiddleLeft);
			
		return image;
	}

	public static void main(String[] args)
	{
//		DadosConfig6 dados = new DadosConfig6();
//		dados.lateralEsq = "le";
//		dados.lateralDir = "ld";
//		dados.anguloExterno = "a";
//		dados.superiorDir = "sd";
//		dados.superiorEsq = "se";
//		dados.inferiorEsq = "ie";
//
//		Config6 config = new Config6(dados);
//		BufferedImage image = config.criarImagem(1);
//
	}

}
