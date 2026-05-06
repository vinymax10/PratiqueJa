package matematica.basico.conjuntos.nivel1package;

import matematica.basico.conjuntos.Conjunto;
import modelo.matematica.Conta;


public class Exercicio10 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Exercicio10(int index)
	{
		super(index);

		int sizeA=3+rand.nextInt(5);
		int sizeB=3+rand.nextInt(5);
		int limit=25;
		Conjunto a=new Conjunto(sizeA,limit);
		Conjunto b=new Conjunto(sizeB,limit);
		a.contruirInterseccao(b);
		
		Conjunto c=a.interseccao(b);
		
		pergunta="Quantos elementos tem a intersecção \\(A \\cap B ~\\)?";
		textLatex="A="+a+"\\newline B="+b;
		
		resultadoCorreto = ""+c.size();
		
		resolucaoLatex="A \\cap B = "+c+"\\\\";
		resolucaoLatex+="|A \\cap B|="+resultadoCorreto;
		
	}
	
	public static void main(String[] args)
	{
		new Exercicio10(1);
	}

}
