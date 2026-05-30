package matematica.basico.expressaonumerica;



import modelo.matematica.Conta;

import jakarta.persistence.Entity;

import matematica.basico.expressaonumerica.nivel1package.Expressao1;

@Entity
public class ExpressaoNumericaNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public ExpressaoNumericaNivel1(int index)
	{
		super(index);

		clone(new Expressao1(index));
	}

	public ExpressaoNumericaNivel1()
	{
	}
}
