package matematica.basico.racionais.nivel3package;

import matematica.Racional;
import matematica.basico.racionais.ResolucaoRacionais;
import modelo.matematica.Conta;

public class Racionais1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Racionais1(int indice)
	{
		super(indice);

		int a = 2 + rand.nextInt(20);
		int b = 2 + rand.nextInt(20);
		while(a==b)	b = 2 + rand.nextInt(20);
		
		int c = 2 + rand.nextInt(20);
		int d = 2 + rand.nextInt(20);
		while(c==d)	d = 2 + rand.nextInt(20);
		
		Racional aRacional = new Racional(a, b);
		Racional bRacional = new Racional(c, d);

		textLatex = aRacional.showDfrac() + "\\cdot" + bRacional.showDfrac() + "=";

		Racional resultado = aRacional.mult(bRacional);
		resultado.fatoracao(2);

		resultadoCorreto = resultado.toString();

		resolucaoLatex = ResolucaoRacionais.Multiplicacao(a, b, c, d);

	}

	public Racionais1()
	{
	}

	public static void main(String[] args)
	{
		new Racionais1(1);
	}

}
