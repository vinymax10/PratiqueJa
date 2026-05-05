package Matematica.Intermediario.Potenciacao.Nivel3Package;

import Matematica.Intermediario.Potenciacao.ResolucaoPotencia;
import Modelo.Matematica.Conta;


public class Potenciacao1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Potenciacao1(int indice)
	{
		super(indice);

		int a = 2 + rand.nextInt(19);
		
		int potenciaMaxima=8;
		int p =2+ rand.nextInt(potenciaMaxima-1);
		pergunta="Qual o valor de \\(x\\)?";
		
		textLatex=ResolucaoPotencia.strFatores(a,p)+"="+a+"^{x}";
		
		resultadoCorreto = "" + p;
		resolucaoLatex = a+"^{"+p+"}="+a+"^{x}\\\\";
		resolucaoLatex+="x="+p;

	}

	public static void main(String[] args)
	{
		new Potenciacao1(1);
	}

}
