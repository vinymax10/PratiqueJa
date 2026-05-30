package matematica.intermediario.sistemaequacoes;

import java.util.Random;

public class SistemaEquacoes
{
	public EquacaoSE um;
	public EquacaoSE dois;
	public EquacaoSE tres;

	public int x;
	public int y;
	int limitSol=20;
	int limitCoeficiente=10;
	Random rand=new Random();

	public void construirX1X2(boolean eq3)
	{
		x=1+rand.nextInt(limitSol);
		y=1+rand.nextInt(limitSol);
		
		um=new EquacaoSE();
		dois=new EquacaoSE();
		
		um.coeficienteX=1;
		dois.coeficienteX=1;

		um.coeficienteY=rand();
		um.calculaValor(x,y);

		do
		{
			dois.coeficienteY=rand();
			dois.calculaValor(x,y);
		}
		while(um.equivalete(dois));
		
		if(eq3)
		{
			tres=new EquacaoSE();
			tres.coeficienteX=rand();
			tres.coeficienteY=rand();
			tres.calculaValor(x,y);
		}
	}
	
	public void construirY1Y2(boolean eq3)
	{
		x=1+rand.nextInt(limitSol);
		y=1+rand.nextInt(limitSol);
		
		um=new EquacaoSE();
		dois=new EquacaoSE();
		
		um.coeficienteY=1;
		dois.coeficienteY=1;

		um.coeficienteX=rand();
		um.calculaValor(x,y);

		do
		{
			dois.coeficienteX=rand();
			dois.calculaValor(x,y);
		}
		while(um.equivalete(dois));
		
		if(eq3)
		{
			tres=new EquacaoSE();
			tres.coeficienteX=rand();
			tres.coeficienteY=rand();
			tres.calculaValor(x,y);
		}
	}
	
	private int rand() 
	{
		int valor=1+rand.nextInt(limitCoeficiente);
		if(rand.nextBoolean()) 
			valor*=-1;
		
		return valor;
	}
	
	public void construirX1(boolean eq3)
	{
		Random rand=new Random();
		x=1+rand.nextInt(limitSol);
		y=1+rand.nextInt(limitSol);
		
		um=new EquacaoSE();
		dois=new EquacaoSE();
		
		um.coeficienteX=rand();

		dois.coeficienteX=rand();

		if(rand.nextBoolean())
			um.coeficienteX=1;
		else
			dois.coeficienteX=1;

		um.coeficienteY=rand();
		um.calculaValor(x,y);

		do
		{
			dois.coeficienteY=rand();
			dois.calculaValor(x,y);
		}
		while(um.equivalete(dois));
		
		um.simplicar();
		dois.simplicar();
		
		if(eq3)
		{
			tres=new EquacaoSE();
			tres.coeficienteX=rand();
			tres.coeficienteY=rand();
			tres.calculaValor(x,y);
		}
	}
	
	public void construirY1(boolean eq3)
	{
		Random rand=new Random();
		x=1+rand.nextInt(limitSol);
		y=1+rand.nextInt(limitSol);
		
		um=new EquacaoSE();
		dois=new EquacaoSE();
		
		um.coeficienteY=rand();
		
		dois.coeficienteY=rand();

		if(rand.nextBoolean())
			um.coeficienteY=1;
		else
			dois.coeficienteY=1;

		um.coeficienteX=rand();
		um.calculaValor(x,y);

		do
		{
			dois.coeficienteX=rand();
			dois.calculaValor(x,y);
		}
		while(um.equivalete(dois));
		
		um.simplicar();
		dois.simplicar();
		
		if(eq3)
		{
			tres=new EquacaoSE();
			tres.coeficienteX=rand();
			tres.coeficienteY=rand();
			tres.calculaValor(x,y);
		}
	}
	
	public String latex()
	{
		String str="";
		str+="\\begin{cases}";
		str+=um.latex()+" \\\\";
		str+=dois.latex();
		if(tres!=null)
			str+=" \\\\"+tres.latexZ();
		
		str+="\\end{cases}";
		
		return str;
	}
}
