package matematica.basico.divisibilidade.nivel3package;



import jakarta.persistence.Entity;

import matematica.basico.divisibilidade.ResolucaoDivisores;
import modelo.matematica.Conta;

@Entity
public class Divisibilidade2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Divisibilidade2(int index)
	{
		super(index);

		ResolucaoDivisores resolucaoDivisores=new ResolucaoDivisores();

		int number=NumerosDivisibilidade.getNumeroPar();
		
		pergunta="Quantos divisores naturais"
				+ " pares possui o número "+number+"?";

		resultadoCorreto = "" +resolucaoDivisores.numerosDividoresResultadoPares(number,true);
		resolucaoLatex=resolucaoDivisores.numerosDividoresParesImpares(number,true);
	}

	public Divisibilidade2()
	{
	}

	public static void main(String[] args)
	{
		new Divisibilidade2(1);
	}
}
