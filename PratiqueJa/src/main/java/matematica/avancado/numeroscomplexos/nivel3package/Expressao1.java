package matematica.avancado.numeroscomplexos.nivel3package;

import matematica.avancado.numeroscomplexos.NumeroComplexo;
import modelo.matematica.Conta;

public class Expressao1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao1(int index)
	{
		super(index);
		
		NumeroComplexo a=NumeroComplexo.contruirModulo(30);
		
//		a.real.numerador=0;
		pergunta="Calcule |z|";
		textLatex="z="+a;
		
		resultadoCorreto = "" + a.modulo;
		
		resolucaoLatex=a.resolucaoModulo();
		
	}

	public static void main(String[] args)
	{
		new Expressao1(1);
	}
}
