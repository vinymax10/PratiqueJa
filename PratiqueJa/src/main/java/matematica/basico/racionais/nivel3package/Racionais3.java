package matematica.basico.racionais.nivel3package;



import jakarta.persistence.Entity;

import matematica.Racional;
import matematica.basico.racionais.ResolucaoRacionais;
import modelo.matematica.Conta;

@Entity
public class Racionais3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Racionais3(int indice)
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
		
		textLatex = aRacional.showDfrac() +"+("+ bRacional.showDfrac()+")=";
		textLatex = textLatex.replace("(", "\\left(").replace(")", "\\right)");

		Racional resultado=aRacional.add(bRacional);
		resultado.fatoracao(2);
		
		resultadoCorreto = resultado.toString();
		
		resolucaoLatex = ResolucaoRacionais.resolucaoCompleta(a, b, c, d, true);
	}

	public Racionais3()
	{
	}

	public static void main(String[] args)
	{
		new Racionais3(1);
	}

}
