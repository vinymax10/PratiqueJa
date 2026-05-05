package Matematica.Basico.Racionais.Nivel3Package;



import jakarta.persistence.Entity;

import Matematica.Racional;
import Matematica.Basico.Racionais.ResolucaoRacionais;
import Modelo.Matematica.Conta;

@Entity
public class Racionais5 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Racionais5(int indice)
	{
		super(indice);
		int max = 20;
		
		int a = 1 + rand.nextInt(max);
		
		if(rand.nextBoolean()) a*=-1;
		
		int b = 2 + rand.nextInt(max);
		while(a==b)	b = 2 + rand.nextInt(max);

		int c = 1 + rand.nextInt(max);
		
		if(rand.nextBoolean())	c*=-1;
		
		int d = 2 + rand.nextInt(max);
		while(c==d)	d = 2 + rand.nextInt(max);

		Racional aRacional=new Racional(a,b);
		Racional bRacional=new Racional(c,d);
		
		textLatex = aRacional.showDfrac() +" \\cdot ("+ bRacional.showDfrac()+")=";
		textLatex = textLatex.replace("(", "\\left(").replace(")", "\\right)");

		Racional resultado=aRacional.mult(bRacional);
		resultado.fatoracao(2);
		
		resultadoCorreto = resultado.toString();
		
		resolucaoLatex = ResolucaoRacionais.Multiplicacao(a, b, c, d);
	}

	public Racionais5()
	{
	}

	public static void main(String[] args)
	{
		new Racionais5(1);
	}

}
