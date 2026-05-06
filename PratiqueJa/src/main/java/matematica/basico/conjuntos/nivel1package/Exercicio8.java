package matematica.basico.conjuntos.nivel1package;

import matematica.basico.conjuntos.Conjunto;
import modelo.matematica.Conta;


public class Exercicio8 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Exercicio8(int index)
	{
		super(index);

		int sizeA=3+rand.nextInt(5);
		int sizeB=3+rand.nextInt(5);
		int limit=25;
		Conjunto a=new Conjunto(sizeA,limit);
		Conjunto b=new Conjunto(sizeB,limit);
		a.contruirInterseccao(b);
		
		Conjunto c=a.menos(b);
		
		pergunta="Qual o menor elemento de \\(A - B ~\\)?";
		textLatex="A="+a+"\\newline B="+b;
		
		resultadoCorreto = ""+c.menor();
		
		resolucaoLatex="A - B = "+c+"\\\\";
		resolucaoLatex+="\\text{Menor elemento de}~ A - B \\text{ é }"+resultadoCorreto;
		
	}
	
	public static void main(String[] args)
	{
		new Exercicio8(1);
	}

}
