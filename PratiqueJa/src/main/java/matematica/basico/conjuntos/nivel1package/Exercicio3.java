package matematica.basico.conjuntos.nivel1package;

import matematica.basico.conjuntos.Conjunto;
import modelo.matematica.Conta;


public class Exercicio3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Exercicio3(int index)
	{
		super(index);

		int sizeA=3+rand.nextInt(5);
		int sizeB=3+rand.nextInt(5);
		int limit=25;
		Conjunto a=new Conjunto(sizeA,limit);
		Conjunto b=new Conjunto(sizeB,limit);
		a.contruirInterseccao(b);
		
		Conjunto c=a.interseccao(b);
		
		pergunta="Qual a soma dos elementos da intersecção \\(A \\cap B ~\\)?";
		textLatex="A="+a+"\\newline B="+b;
		
		resultadoCorreto = ""+c.soma();
		
		resolucaoLatex="A \\cap B = "+c+"\\\\";
		if(c.size()>1)
		{
			resolucaoLatex+="\\text{Soma dos elementos de}~ A \\cap B \\text{ é: }\\\\";
			resolucaoLatex+=c.somaStr()+"="+resultadoCorreto;
		}
		else
			resolucaoLatex+="\\text{Soma dos elementos de}~ A \\cap B  = "+resultadoCorreto;
	}
	
	public static void main(String[] args)
	{
		new Exercicio3(1);
	}

}
