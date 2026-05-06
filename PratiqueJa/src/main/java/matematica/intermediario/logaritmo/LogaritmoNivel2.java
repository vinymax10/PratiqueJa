package matematica.intermediario.logaritmo;



import jakarta.persistence.Entity;

import matematica.Racional;
import modelo.matematica.Conta;

@Entity
public class LogaritmoNivel2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public LogaritmoNivel2(int indice)
	{
		super(indice);

		int c = 2 + rand.nextInt(9);
		int b = 2 + rand.nextInt(10);

		int maxBase = (int) Math.min((Math.log(1000) / Math.log(c)), 10);
		int x = 1+rand.nextInt(maxBase + 1);

		Racional a;
		boolean soma = rand.nextBoolean();
		if(soma)
		{
			a = new Racional((int) Math.pow(c, x), b);
			a.fatoracao(2);
			textLatex = "\\log_{" + c + "}\\left(" + a.showDfrac() + "\\right)+" + "\\log_{" + c + "}" + b + "=";
			resolucaoLatex = ResolucaoLogaritmo.resolucaoSoma(a, b, c, x);

		}
		else
		{
			a = new Racional((int) Math.pow(c, x) * b, 1);
			
			textLatex = "\\log_{" + c + "}" + a + "-" + "\\log_{" + c + "}" + b + "=";
			resolucaoLatex = ResolucaoLogaritmo.resolucaoSubtracao(a, b, c, x);

		}

		resultadoCorreto = "" + x;
	}

	public LogaritmoNivel2()
	{
	}

}
