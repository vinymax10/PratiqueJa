package Matematica.Intermediario.Divisibilidade.Nivel3Package;



import jakarta.persistence.Entity;

import Matematica.Intermediario.Divisibilidade.ResolucaoDivisores;
import Modelo.Matematica.Conta;

@Entity
public class Divisibilidade1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Divisibilidade1(int index)
	{
		super(index);
		ResolucaoDivisores resolucaoDivisores=new ResolucaoDivisores();

		int number=NumerosDivisibilidade.getNumero();
		pergunta="Quantos divisores naturais"
		+ " possui o número "+number+"?";

		resultadoCorreto = "" +resolucaoDivisores.numerosDividoresResultado(number);
		resolucaoLatex=resolucaoDivisores.numerosDividores(number);
	}

	public Divisibilidade1()
	{
	}

	public static void main(String[] args)
	{
		new Divisibilidade1(1);
	}
}
