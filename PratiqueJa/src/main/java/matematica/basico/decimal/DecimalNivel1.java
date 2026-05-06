package matematica.basico.decimal;



import modelo.matematica.Conta;

import jakarta.persistence.Entity;

import matematica.basico.decimal.nivel1package.Decimal;

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
