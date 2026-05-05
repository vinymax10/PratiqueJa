package Matematica.Basico.Conjuntos.Nivel1Package;

import Matematica.Basico.Conjuntos.Conjunto;
import Modelo.Matematica.Conta;


public class Exercicio12 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Exercicio12(int index)
	{
		super(index);

		int sizeA=3+rand.nextInt(5);
		int sizeB=3+rand.nextInt(5);
		int limit=25;
		Conjunto a=new Conjunto(sizeA,limit);
		Conjunto b=new Conjunto(sizeB,limit);
		a.contruirInterseccao(b);
		
		Conjunto c=a.menos(b);
		
		pergunta="Quantos elementos tem \\(A - B ~\\)?";
		textLatex="A="+a+"\\newline B="+b;
		
		resultadoCorreto = ""+c.size();
		
		resolucaoLatex="A - B = "+c+"\\\\";
		resolucaoLatex+="|A - B|="+resultadoCorreto;
		
	}
	
	public static void main(String[] args)
	{
		new Exercicio12(1);
	}

}
