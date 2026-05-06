package matematica.intermediario.potenciacao.nivel2package;

import matematica.intermediario.potenciacao.ResolucaoPotencia;
import modelo.matematica.Conta;


public class Potenciacao2 extends Conta
{
	private static final long serialVersionUID = 1L;

//	Potencias base negativa e expoente positivo
	public Potenciacao2(int indice)
	{
		super(indice);
		
		int potenciaMaxima=7;
		int a = -1 - rand.nextInt(10);

		int maxBase = (int) Math.min((Math.log(1000) / Math.log(Math.abs(a))), potenciaMaxima);
		int p = rand.nextInt(maxBase + 1);
		if (p == 0 || p == 1)
			p = rand.nextInt(maxBase + 1);

		textLatex = "" + a + "^{" + p + "}" + "=";

		resultadoCorreto = "" + (int) -Math.pow(Math.abs(a), p);
		resolucaoLatex = ResolucaoPotencia.resolucaoNegativo(a, p);
		
	}

	public static void main(String[] args)
	{
		new Potenciacao2(1);
	}

}
