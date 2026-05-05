package Matematica.Intermediario.Dizima;



import Modelo.Matematica.Conta;

import jakarta.persistence.Entity;

import Matematica.Intermediario.Dizima.Nivel1Package.Dizima1;

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
