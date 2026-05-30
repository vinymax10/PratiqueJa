package matematica.basico.divisibilidade;



import jakarta.persistence.Entity;

import matematica.basico.divisibilidade.nivel2package.Divisibilidade4;
import matematica.basico.divisibilidade.nivel2package.Divisibilidade6;
import matematica.basico.divisibilidade.nivel2package.Divisibilidade7;
import matematica.basico.divisibilidade.nivel2package.Divisibilidade8;
import matematica.basico.divisibilidade.nivel2package.Divisibilidade9;
import modelo.matematica.Conta;

@Entity
public class DivisibilidadeNivel2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public DivisibilidadeNivel2(int index)
	{
		super(index);

		switch(rand.nextInt(5)) {
		case 0:
			clone(new Divisibilidade6(index));
			break;
		case 1:
			clone(new Divisibilidade9(index));
			break;
		case 2:
			clone(new Divisibilidade4(index));
			break;
		case 3:
			clone(new Divisibilidade7(index));
			break;
		case 4:
			clone(new Divisibilidade8(index));
			break;
		}
	}

	public boolean isCorreta()
	{
		return respostaAlunoBol == resultadoCorretoBol;
	}

	public DivisibilidadeNivel2()
	{
	}
	
	public static void main(String[] args)
	{
		new DivisibilidadeNivel2(1);
	}
}
