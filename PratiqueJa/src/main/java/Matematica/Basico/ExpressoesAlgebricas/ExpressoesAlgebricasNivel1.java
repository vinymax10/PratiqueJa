package Matematica.Basico.ExpressoesAlgebricas;



import Modelo.Matematica.Conta;

import javax.persistence.Entity;

import Matematica.Basico.ExpressoesAlgebricas.Nivel1Package.Expressao1;

@Entity
public class ExpressoesAlgebricasNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public ExpressoesAlgebricasNivel1(int index)
	{
		super(index);

		clone(new Expressao1(index));
	}

	public ExpressoesAlgebricasNivel1()
	{

	}

}
