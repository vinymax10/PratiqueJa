package Matematica.Intermediario.Divisibilidade;



import jakarta.persistence.Entity;

import Matematica.Intermediario.Divisibilidade.Nivel1Package.Divisibilidade10;
import Matematica.Intermediario.Divisibilidade.Nivel1Package.Divisibilidade2;
import Matematica.Intermediario.Divisibilidade.Nivel1Package.Divisibilidade3;
import Matematica.Intermediario.Divisibilidade.Nivel1Package.Divisibilidade5;
import Modelo.Matematica.Conta;

@Entity
public class DivisibilidadeNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public DivisibilidadeNivel1(int index)
	{
		super(index);

		switch(rand.nextInt(4)) {
		case 0:
			clone(new Divisibilidade10(index));
			break;
		case 1:
			clone(new Divisibilidade2(index));
			break;
		case 2:
			clone(new Divisibilidade5(index));
		case 3:
			clone(new Divisibilidade3(index));
			break;
		}
	}

	public boolean isCorreta()
	{
		return respostaAlunoBol == resultadoCorretoBol;
	}

	public DivisibilidadeNivel1()
	{
	}

}
