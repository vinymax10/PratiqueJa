package matematica.intermediario.potenciacao;



import jakarta.persistence.Entity;

import matematica.intermediario.potenciacao.nivel1package.Potenciacao;
import modelo.matematica.Conta;

@Entity
public class PotenciacaoNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public PotenciacaoNivel1(int index)
	{
		super(index);

		clone(new Potenciacao(index));
	}

	public PotenciacaoNivel1()
	{
	}
}
