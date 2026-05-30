package matematica.intermediario.expressaoalgebrica;



import modelo.matematica.Conta;

import jakarta.persistence.Entity;

import matematica.intermediario.expressaoalgebrica.nivel1package.Expressao1;

@Entity
public class ExpressaoAlgebricaNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public ExpressaoAlgebricaNivel1(int index)
	{
		super(index);

		clone(new Expressao1(index));
	}

	public ExpressaoAlgebricaNivel1()
	{

	}

}
