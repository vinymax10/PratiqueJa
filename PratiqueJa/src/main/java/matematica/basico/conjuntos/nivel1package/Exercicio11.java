package matematica.basico.conjuntos.nivel1package;

import matematica.basico.conjuntos.Conjunto;
import modelo.matematica.Conta;


public class Exercicio11 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Exercicio11(int index)
	{
		super(index);

		int sizeA=3+rand.nextInt(5);
		int sizeB=3+rand.nextInt(5);
		int limit=25;
		Conjunto a=new Conjunto(sizeA,limit);
		Conjunto b=new Conjunto(sizeB,limit);
		a.contruirInterseccao(b);
		
		Conjunto c=a.uniao(b);
		
		pergunta="Quantos elementos tem a união \\(A \\cup B ~\\)?";
		textLatex="A="+a+"\\newline B="+b;
		
		resultadoCorreto = ""+c.size();
		
		resolucaoLatex="A \\cup B = "+c+"\\\\";
		resolucaoLatex+="|A \\cup B|="+resultadoCorreto;
		
	}
	
	public static void main(String[] args)
	{
		new Exercicio11(1);
	}

}
