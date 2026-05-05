package Matematica.Basico.Conjuntos.Nivel1Package;

import Matematica.Basico.Conjuntos.Conjunto;
import Modelo.Matematica.Conta;


public class Exercicio7 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Exercicio7(int index)
	{
		super(index);

		int sizeA=3+rand.nextInt(5);
		int sizeB=3+rand.nextInt(5);
		int limit=25;
		Conjunto a=new Conjunto(sizeA,limit);
		Conjunto b=new Conjunto(sizeB,limit);
		a.contruirInterseccao(b);
		
		Conjunto c=a.menos(b);
		
		pergunta="Qual o maior elemento de \\(A - B ~\\)?";
		textLatex="A="+a+"\\newline B="+b;
		
		resultadoCorreto = ""+c.maior();
		
		resolucaoLatex="A - B = "+c+"\\\\";
		resolucaoLatex+="\\text{Maior elemento de}~ A - B \\text{ é }"+resultadoCorreto;
		
	}
	
	public static void main(String[] args)
	{
		new Exercicio7(1);
	}

}
