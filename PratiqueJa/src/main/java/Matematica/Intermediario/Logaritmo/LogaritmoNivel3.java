package Matematica.Intermediario.Logaritmo;

import javax.persistence.Entity;

import Matematica.Racional;
import Modelo.Matematica.Conta;

@Entity
public class LogaritmoNivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public LogaritmoNivel3(int indice)
	{
		super(indice);
		int a = 1 + rand.nextInt(5);
		int c = 2 + rand.nextInt(9);
		int b = 1 + rand.nextInt(10);

		int maxBase = (int) Math.min((Math.log(1000) / Math.log(c)), 10);
		int d = 1 + rand.nextInt(Math.max(1, maxBase));

		Racional x = new Racional(d * b, a);
		x.fatoracao(2);

		if(a == 1)
		{
			textLatex = b + " \\cdot \\log_{" + c + "}" + (int) Math.pow(c, d) + "=";
			resolucaoLatex = ResolucaoLogaritmo.resolucao3A1(b, c, d, x);
		}
		else
		{
			textLatex = b + " \\cdot \\log_{" + c + "} \\sqrt[" + a + "]{" + (int) Math.pow(c, d) + "}=";
			resolucaoLatex = ResolucaoLogaritmo.resolucao3(a, b, c, d, x);
		}

		resultadoCorreto = "" + x;
	}

	public LogaritmoNivel3()
	{
	}

	public static void main(String[] args)
	{
		new LogaritmoNivel3(1);
	}

}
