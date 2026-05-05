package Matematica.Basico.Decimal;



import Modelo.Matematica.Conta;

import javax.persistence.Entity;

import Matematica.Basico.Decimal.Nivel1Package.Decimal;

@Entity
public class DecimalNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public DecimalNivel1(int index)
	{
		super(index);

		clone(new Decimal(index));
	}

	public DecimalNivel1()
	{

	}
}
