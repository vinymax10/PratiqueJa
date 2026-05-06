package matematica.basico.decimal;



import modelo.matematica.Conta;

import jakarta.persistence.Entity;

import matematica.basico.decimal.nivel3package.Decimal;

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
