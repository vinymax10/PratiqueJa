package matematica.avancado.leisenocosseno.config;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.ParCor;

public class Config
{
	Dados dados;
	Point a, b, c;
	
	public Config(TipoDado tipoDado,Triangulo triangulo)
	{
		this.a=triangulo.a;
		this.b=triangulo.b;
		this.c=triangulo.c;
		dados=new Dados(tipoDado,a,b,c);		
	}

	public BufferedImage criarImagem(int index)
	{
		int width=1250;
		int height=750;

		ParCor parCor = ParCor.parCor(index-1);
		BufferedImage image = new BufferedImage((int) width,(int) height, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2 = image.createGraphics();
		Graphics.setHint(g2);
		
		int raio = 200;
		
		if(dados.strAngleA!=null&&!dados.strAngleA.equals(""))
			Graphics.setAngleSemBorda(g2, a, b, c,
			raio, ParCor.parCor(index + 1), parCor);
		
		if(dados.strAngleB!=null&&!dados.strAngleB.equals(""))
			Graphics.setAngleSemBorda(g2, b, a, c, 
			raio, ParCor.parCor(index + 2), parCor);

		if(dados.strAngleC!=null&&!dados.strAngleC.equals(""))
			Graphics.setAngleSemBorda(g2, c, b, a, 
			raio, ParCor.parCor(index + 3), parCor);
		
		Polygon triangulo = new Polygon();

		triangulo.addPoint(a.x,a.y);
		triangulo.addPoint(b.x, b.y);
		triangulo.addPoint(c.x, c.y);

		g2.setColor(Color.decode(parCor.getCorForte()));
		g2.setStroke(new BasicStroke(10));
		g2.draw(triangulo);
		
		if(dados.strAngleA!=null&&!dados.strAngleA.equals(""))
			Graphics.addLabel(g2, dados.strAngleA, a, b, c, raio);
		
		if(dados.strAngleB!=null&&!dados.strAngleB.equals(""))
			Graphics.addLabel(g2, dados.strAngleB, b, a, c, raio);	
		
		if(dados.strAngleC!=null&&!dados.strAngleC.equals(""))
			Graphics.addLabel(g2, dados.strAngleC,  c, a, b, raio);	
			
		if(!dados.strLadoA.equals(""))
			Graphics.addLabel(g2, dados.strLadoA, a, b,c);

		if(!dados.strLadoB.equals(""))
			Graphics.addLabel(g2, dados.strLadoB, b,a,c);

		if(!dados.strLadoC.equals(""))
			Graphics.addLabel(g2, dados.strLadoC, c, a,b);
		
		return image;
	}
	
	public Dados getDados()
	{
		return dados;
	}

	public void setDados(Dados dados)
	{
		this.dados = dados;
	}

}
