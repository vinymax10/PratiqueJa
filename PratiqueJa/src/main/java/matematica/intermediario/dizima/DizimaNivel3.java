package matematica.intermediario.dizima;



import modelo.matematica.Conta;

import jakarta.persistence.Entity;

import matematica.intermediario.dizima.nivel3package.Dizima;

@Entity
public class DizimaNivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public DizimaNivel3(int index)
	{
		super(index);

		clone(new Dizima(index));

	}

	public DizimaNivel3()
	{
	}
}
