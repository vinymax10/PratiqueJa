package Matematica.Basico.Decimal;



import Modelo.Matematica.Conta;

import jakarta.persistence.Entity;

import Matematica.Basico.Decimal.Nivel3Package.Decimal;

@Entity
public class DecimalNivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public DecimalNivel3(int index)
	{
		super(index);

		clone(new Decimal(index));
	}

	public DecimalNivel3()
	{

	}
}
