package matematica.intermediario.potenciacao.nivel1package;

import matematica.intermediario.potenciacao.ResolucaoPotencia;
import modelo.matematica.Conta;


public class Potenciacao extends Conta
{
	private static final long serialVersionUID = 1L;

//	Potencias base positiva e expoente positivo
	public Potenciacao(int indice)
	{
		super(indice);

		int a = 1 + rand.nextInt(10);
		
		int potenciaMaxima=7;
		int maxBase = (int) Math.min((Math.log(1000) / Math.log(a)), potenciaMaxima);
		int p = rand.nextInt(maxBase + 1);
		if (p == 0 || p == 1)
			p = rand.nextInt(maxBase + 1);
		
		textLatex = "" + a + "^{" + p + "}" + "=";

		resultadoCorreto = "" + Integer.valueOf((int) Math.pow(a, p));
		resolucaoLatex = ResolucaoPotencia.resolucao(a, p);
	}

	public static void main(String[] args)
	{
		new Potenciacao(1);
	}

}
