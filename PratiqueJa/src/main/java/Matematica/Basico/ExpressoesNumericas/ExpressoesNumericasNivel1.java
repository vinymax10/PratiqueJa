package Matematica.Basico.ExpressoesNumericas;



import Modelo.Matematica.Conta;

import javax.persistence.Entity;

import Matematica.Basico.ExpressoesNumericas.Nivel1Package.Expressao1;

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
