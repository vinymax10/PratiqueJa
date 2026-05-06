package matematica.basico.expressoesalgebricas;



import modelo.matematica.Conta;

import jakarta.persistence.Entity;

import matematica.basico.expressoesalgebricas.nivel1package.Expressao1;

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
