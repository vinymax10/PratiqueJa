package Matematica.Intermediario.Divisibilidade.Nivel3Package;



import javax.persistence.Entity;

import Matematica.Intermediario.Divisibilidade.ResolucaoDivisores;
import Modelo.Matematica.Conta;

@Entity
public class Divisibilidade4 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Divisibilidade4(int index)
	{
		super(index);

		ResolucaoDivisores resolucaoDivisores=new ResolucaoDivisores();

		int number=NumerosDivisibilidade.getNumero();
		
		pergunta="Qual é a soma dos divisores naturais"
				+ " do número "+number+"?";

		resultadoCorreto = "" +resolucaoDivisores.somaDividoresResultado(number);
		resolucaoLatex=resolucaoDivisores.somaDividores(number);
		System.out.println("resultadoCorreto: "+resultadoCorreto);
		System.out.println("resolucaoLatex: "+resolucaoLatex);
	}

	public Divisibilidade4()
	{
	}

	public static void main(String[] args)
	{
		new Divisibilidade4(1);
	}
}
