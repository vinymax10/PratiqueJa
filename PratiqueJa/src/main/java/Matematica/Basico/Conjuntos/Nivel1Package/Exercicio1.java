package Matematica.Basico.Conjuntos.Nivel1Package;

import Matematica.Basico.Conjuntos.Conjunto;
import Modelo.Matematica.Conta;


public class Exercicio1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Exercicio1(int index)
	{
		super(index);

		int sizeA=3+rand.nextInt(5);
		int sizeB=3+rand.nextInt(5);
		int limit=25;
		Conjunto a=new Conjunto(sizeA,limit);
		Conjunto b=new Conjunto(sizeB,limit);
		a.contruirInterseccao(b);
		
		Conjunto c=a.interseccao(b);
		
		pergunta="Qual o maior elemento da intersecção \\(A \\cap B ~\\)?";
		textLatex="A="+a+"\\newline B="+b;
		
		resultadoCorreto = ""+c.maior();
		
		resolucaoLatex="A \\cap B = "+c+"\\\\";
		resolucaoLatex+="\\text{Maior elemento de}~ A \\cap B \\text{ é }"+resultadoCorreto;
		
	}
	
	public static void main(String[] args)
	{
		new Exercicio1(1);
	}

}
