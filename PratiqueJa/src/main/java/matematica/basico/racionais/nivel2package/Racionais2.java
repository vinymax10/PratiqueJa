package matematica.basico.racionais.nivel2package;

import matematica.Racional;
import matematica.basico.racionais.ResolucaoRacionais;
import modelo.matematica.Conta;

public class Racionais2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Racionais2(int indice)
	{
		super(indice);

		int a = 2 + rand.nextInt(20);
		int b = 2 + rand.nextInt(20);
		while(a==b)	b = 2 + rand.nextInt(20);

		int c = 2 + rand.nextInt(20);
		int d = 2 + rand.nextInt(20);

		while(b == d)
			d = 2 + rand.nextInt(20);

		Racional aRacional = new Racional(a, b);
		Racional bRacional = new Racional(c, d);

		textLatex = aRacional.showDfrac() + "-" + bRacional.showDfrac() + "=";

		Racional resultado = aRacional.minus(bRacional);
		resultado.fatoracao(2);

		resultadoCorreto = resultado.toString();

		resolucaoLatex = ResolucaoRacionais.resolucaoCompleta(a, b, c, d, false);
	}

	public Racionais2()
	{
	}

	public static void main(String[] args)
	{
		new Racionais2(1);

	}

}
