package matematica.avancado.leisenocosseno.config;

import java.awt.Point;
import java.util.Random;

import infra.Graphics;
import matematica.Racional;


public class Dados 
{
	public Racional ladoA,ladoB,ladoC;
	public Racional senAngleA,senAngleB,senAngleC;
	public Racional cosAngleA,cosAngleB,cosAngleC;

	public String strLadoA = "", strLadoB = "", strLadoC = "";
	public String strAngleA,strAngleB,strAngleC;
	Random rand=new Random();
	public TipoDado tipoDado;
	int size;
	double porcB,porcA,porcC;
	double variacao=0.08;
	double senA,senB,senC,cosA,cosB,cosC;
	
	public Dados(TipoDado tipoDado, Point a, Point b, Point c)
	{
		this.tipoDado=tipoDado; 
		double distA=Graphics.calcularDistancia(b,c);
		double distB=Graphics.calcularDistancia(a,c);
		double distC=Graphics.calcularDistancia(a,b);
        double maior = Math.max(distA, Math.max(distB, distC));
        
        this.porcA=distA/maior;
		this.porcB=distB/maior;
		this.porcC=distC/maior;
		
		this.variacao=0.08;
		this.senA=Math.sin(Math.toRadians(Graphics.calculaAngle(a,b,c)));
		this.senB=Math.sin(Math.toRadians(Graphics.calculaAngle(b,a,c)));
		this.senC=Math.sin(Math.toRadians(Graphics.calculaAngle(c,a,b)));
		this.cosA=Math.cos(Math.toRadians(Graphics.calculaAngle(a,b,c)));
		this.cosB=Math.cos(Math.toRadians(Graphics.calculaAngle(b,a,c)));
		this.cosC=Math.cos(Math.toRadians(Graphics.calculaAngle(c,a,b)));
		
		gerarValores();
	}
	
	public void gerarValores()
	{
		this.size=20+rand.nextInt(20);
		
		ladoC = new Racional((int)((double)size*porcC));

		ladoB = new Racional((int)((double)size*porcB));
		
		ladoA = new Racional((int)((double)size*porcA));
		
		cosAngleA=new Racional((ladoB.numerador*ladoB.numerador)
		+(ladoC.numerador*ladoC.numerador)
		-(ladoA.numerador*ladoA.numerador),(2*ladoB.numerador*ladoC.numerador));
		
		cosAngleB=new Racional((ladoA.numerador*ladoA.numerador)
		+(ladoC.numerador*ladoC.numerador)
		-(ladoB.numerador*ladoB.numerador),(2*ladoA.numerador*ladoC.numerador));
		
		cosAngleC=new Racional((ladoB.numerador*ladoB.numerador)
		+(ladoA.numerador*ladoA.numerador)
		-(ladoC.numerador*ladoC.numerador),(2*ladoA.numerador*ladoB.numerador));
		
		cosAngleA.fatoracao(2);
		cosAngleB.fatoracao(2);
		cosAngleC.fatoracao(2);
		
		switch(tipoDado)
		{
			case AB:
				senAngleB= new Racional((int)((double)ladoA.numerador*senB),ladoA.numerador);
				senAngleA= senAngleB.mult(ladoA).div(ladoB);
				senAngleC= senAngleB.mult(ladoC).div(ladoB);
				break;
			case AC:
				senAngleC= new Racional((int)((double)ladoA.numerador*senC),ladoA.numerador);
				senAngleA= senAngleC.mult(ladoA).div(ladoC);
				senAngleB= senAngleC.mult(ladoB).div(ladoC);
				break;
			case BC:
				senAngleC= new Racional((int)((double)ladoB.numerador*senC),ladoB.numerador);
				senAngleB= senAngleC.mult(ladoB).div(ladoC);
				senAngleA= senAngleB.mult(ladoA).div(ladoB);
				break;
		}
		
		senAngleA.fatoracao(2);
		senAngleB.fatoracao(2);
		senAngleC.fatoracao(2);
		
		strLadoA=""+ladoA.numerador;
		strLadoB=""+ladoB.numerador;
		strLadoC=""+ladoC.numerador;
	}

	@Override
	public String toString()
	{
		return "Dados [ladoA=" + ladoA + ", ladoB=" + ladoB + ", ladoC=" + ladoC + ", senAngleA=" + senAngleA + ", senAngleB=" + senAngleB + ", senAngleC="
		+ senAngleC + ", strLadoA=" + strLadoA + ", strLadoB=" + strLadoB + ", strLadoC=" + strLadoC + ", strAngleA=" + strAngleA + ", strAngleB=" + strAngleB
		+ ", strAngleC=" + strAngleC + ", rand=" + rand + "]";
	}

}
