package matematica.basico.divisaonatural;

import jakarta.persistence.Entity;

import matematica.basico.resolucaonatural.ResolucaoNatural;
import modelo.matematica.Conta;

@Entity
public class DivisaoNaturalNivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public DivisaoNaturalNivel3(int indice)
	{
		super(indice);
		int a, b;
		
		a = 100 + rand.nextInt(900);
		b = 10 + rand.nextInt(90);

		textLatex = ResolucaoNatural.divisao((a * b), b, false);
		resultadoCorreto = "" + a;
		resolucaoLatex = ResolucaoNatural.divisao((a * b), b, true);
	}

	public DivisaoNaturalNivel3()
	{
	}
}
