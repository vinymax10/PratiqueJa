package Matematica.Intermediario.Dizima;



import Modelo.Matematica.Conta;

import javax.persistence.Entity;

import Matematica.Intermediario.Dizima.Nivel3Package.Dizima;

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
