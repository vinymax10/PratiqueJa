package Matematica.Basico.Conjuntos.Nivel1Package;

import Matematica.Basico.Conjuntos.Conjunto;
import Modelo.Matematica.Conta;


public class Exercicio9 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Exercicio9(int index)
	{
		super(index);

		int sizeA=3+rand.nextInt(5);
		int sizeB=3+rand.nextInt(5);
		int limit=25;
		Conjunto a=new Conjunto(sizeA,limit);
		Conjunto b=new Conjunto(sizeB,limit);
		a.contruirInterseccao(b);
		
		Conjunto c=a.menos(b);
		
		pergunta="Qual a soma dos elementos de \\(A - B ~\\)?";
		textLatex="A="+a+"\\newline B="+b;
		
		resultadoCorreto = ""+c.soma();
		
		resolucaoLatex="A - B = "+c+"\\\\";
		
		if(c.size()>1)
		{
			resolucaoLatex+="\\text{Soma dos elementos de}~ A - B \\text{ é: }\\\\";
			resolucaoLatex+=c.somaStr()+"="+resultadoCorreto;
		}
		else
			resolucaoLatex+="\\text{Soma dos elementos de}~ A - B  = "+resultadoCorreto;
	}
	
	public static void main(String[] args)
	{
		new Exercicio9(1);
	}

}
