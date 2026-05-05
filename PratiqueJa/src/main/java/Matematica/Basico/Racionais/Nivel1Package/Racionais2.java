package Matematica.Basico.Racionais.Nivel1Package;

import Matematica.Racional;
import Matematica.Basico.Racionais.ResolucaoRacionais;
import Modelo.Matematica.Conta;

public class Racionais2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Racionais2(int indice)
	{
		super(indice);

		int a = 2 + rand.nextInt(20);
		int b = 2 + rand.nextInt(20);

		int d = 2 + rand.nextInt(20);

		Racional aRacional = new Racional(a, d);
		Racional bRacional = new Racional(b, d);

		textLatex = aRacional.showDfrac() + "-" + bRacional.showDfrac() + "=";

		Racional resultado = aRacional.minus(bRacional);
		resultado.fatoracao(2);

		resultadoCorreto = resultado.toString();

		resolucaoLatex = ResolucaoRacionais.simplesMenos(aRacional, bRacional);
		
	}

	public Racionais2()
	{
	}

	public static void main(String[] args)
	{
		new Racionais2(1);

	}

}
