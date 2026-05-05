package Matematica.Basico.Conjuntos.Nivel1Package;

import Matematica.Basico.Conjuntos.Conjunto;
import Modelo.Matematica.Conta;


public class Exercicio4 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Exercicio4(int index)
	{
		super(index);

		int sizeA=3+rand.nextInt(5);
		int sizeB=3+rand.nextInt(5);
		int limit=25;
		Conjunto a=new Conjunto(sizeA,limit);
		Conjunto b=new Conjunto(sizeB,limit);
		a.contruirInterseccao(b);
		
		Conjunto c=a.uniao(b);
		
		pergunta="Qual o maior elemento da união \\(A \\cup B ~\\)?";
		textLatex="A="+a+"\\newline B="+b;
		
		resultadoCorreto = ""+c.maior();
		
		resolucaoLatex="A \\cup B = "+c+"\\\\";
		resolucaoLatex+="\\text{Maior elemento de}~ A \\cup B \\text{ é }"+resultadoCorreto;
		
	}
	
	public static void main(String[] args)
	{
		new Exercicio4(1);
	}

}
