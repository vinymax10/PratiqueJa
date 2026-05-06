package matematica.intermediario.dizima;



import modelo.matematica.Conta;

import jakarta.persistence.Entity;

import matematica.intermediario.dizima.nivel1package.Dizima1;

@Entity
public class DizimaNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public DizimaNivel1(int index)
	{
		super(index);

		clone(new Dizima1(index));
	}

	public DizimaNivel1()
	{
	}
}
