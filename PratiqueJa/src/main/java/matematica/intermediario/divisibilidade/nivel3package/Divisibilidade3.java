package matematica.intermediario.divisibilidade.nivel3package;



import jakarta.persistence.Entity;

import matematica.intermediario.divisibilidade.ResolucaoDivisores;
import modelo.matematica.Conta;

@Entity
public class Divisibilidade3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Divisibilidade3(int index)
	{
		super(index);

		ResolucaoDivisores resolucaoDivisores=new ResolucaoDivisores();

		int number=NumerosDivisibilidade.getNumero();
		
		pergunta="Quantos divisores naturais"
				+ " ímpares possui o número "+number+"?";

		resultadoCorreto = "" +resolucaoDivisores.numerosDividoresResultadoPares(number,false);
		resolucaoLatex=resolucaoDivisores.numerosDividoresParesImpares(number,false);
	}

	public Divisibilidade3()
	{
	}

	public static void main(String[] args)
	{
		new Divisibilidade3(1);
	}
}
