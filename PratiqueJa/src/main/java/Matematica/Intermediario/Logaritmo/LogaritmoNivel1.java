package Matematica.Intermediario.Logaritmo;



import jakarta.persistence.Entity;

import Modelo.Matematica.Conta;

@Entity
public class LogaritmoNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public LogaritmoNivel1(int indice)
	{
		super(indice);

		int c = 2 + rand.nextInt(9);
		int maxBase = (int) Math.min((Math.log(1000) / Math.log(c)), 10);
		int x = 1+ rand.nextInt(maxBase + 1);

		int a;
		a = (int) Math.pow(c, x);
		textLatex = "\\log_{" + c + "}" + a + "=";
		resolucaoLatex = ResolucaoLogaritmo.resolucao1(a, c, x);

		resultadoCorreto = "" + x;
	}

	public LogaritmoNivel1()
	{
	}

	public static void main(String[] args)
	{
		new LogaritmoNivel1(1);
//		String resolucaoLatex = ResolucaoLogaritmo.resolucao1(27, 3, 7);
//		System.out.println(resolucaoLatex);
	}
}
