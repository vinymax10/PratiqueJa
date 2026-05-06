package matematica.basico.expressoesnumericas;



import modelo.matematica.Conta;

import jakarta.persistence.Entity;

import matematica.basico.expressoesnumericas.nivel1package.Expressao1;

@Entity
public class ExpressoesNumericasNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public ExpressoesNumericasNivel1(int index)
	{
		super(index);

		clone(new Expressao1(index));
	}

	public ExpressoesNumericasNivel1()
	{
	}
}
