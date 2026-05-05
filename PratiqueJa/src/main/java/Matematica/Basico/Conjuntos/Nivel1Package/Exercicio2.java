package Matematica.Basico.Conjuntos.Nivel1Package;

import Matematica.Basico.Conjuntos.Conjunto;
import Modelo.Matematica.Conta;


public class Exercicio2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Exercicio2(int index)
	{
		super(index);

		int sizeA=3+rand.nextInt(5);
		int sizeB=3+rand.nextInt(5);
		int limit=25;
		Conjunto a=new Conjunto(sizeA,limit);
		Conjunto b=new Conjunto(sizeB,limit);
		a.contruirInterseccao(b);
		
		Conjunto c=a.interseccao(b);
		
		pergunta="Qual o menor elemento da intersecção \\(A \\cap B ~\\)?";
		textLatex="A="+a+"\\newline B="+b;
		
		resultadoCorreto = ""+c.menor();
		
		resolucaoLatex="A \\cap B = "+c+"\\\\";
		resolucaoLatex+="\\text{Menor elemento de}~ A \\cap B \\text{ é }"+resultadoCorreto;
		
	}
	
	public static void main(String[] args)
	{
		new Exercicio2(1);
	}

}
