package matematica.basico.racionais.nivel1package;

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

		int d = 2 + rand.nextInt(20);

		Racional aRacional = new Racional(a, d);
		Racional bRacional = new Racional(b, d);

		textLatex = aRacional.showDfrac() + "+" + bRacional.showDfrac() + "=";

		Racional resultado = aRacional.add(bRacional);
		resultado.fatoracao(2);

		resultadoCorreto = resultado.toString();

		resolucaoLatex = ResolucaoRacionais.simplesSoma(aRacional, bRacional);
		
	}

	public Racionais1()
	{
	}

	public static void main(String[] args)
	{
		new Racionais1(1);

	}

}
