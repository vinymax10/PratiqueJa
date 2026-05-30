package matematica.intermediario.equacaosegundograu.config;

import java.util.Random;

import matematica.Racional;

public class DadosEq2Grau 
{
	public int a,b,c;
	public int x1 = 0;
	public double x2 = 0;
	public double yVertice;
	public double xVertice;
	public Racional yVerticeRacional;
	public Racional xVerticeRacional;
	
	Random rand=new Random();

	public DadosEq2Grau()
	{
		x1 = 1 + rand.nextInt(5);

		int delta = 2 + rand.nextInt(19);

		a = 1 + rand.nextInt(4);
		if (rand.nextBoolean())
			a *= -1;
		b = delta - (2 * a * x1);

		c = (-x1 * delta) + (a * x1 * x1);

		x2 = (double) (-b - delta) / (2 * a);

		yVertice = -(double) (delta * delta) / (double) (4 * a);
		xVertice = (double) -b / (double) (2 * a);
		
		yVerticeRacional = new Racional(-delta * delta).div(new Racional(4 * a));
		yVerticeRacional.fatoracao(2);
		xVerticeRacional = new Racional(-b).div(new Racional(2 * a));
		xVerticeRacional.fatoracao(2);
	}

	@Override
	public String toString()
	{
		return "a=" + a + ", b=" + b + ", c=" + c;
	}
	
	
}
