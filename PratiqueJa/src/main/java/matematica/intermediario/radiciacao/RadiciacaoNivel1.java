package matematica.intermediario.radiciacao;



import modelo.matematica.Conta;

import jakarta.persistence.Entity;

import matematica.intermediario.radiciacao.nivel1package.Radiciacao;

@Entity
public class RadiciacaoNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public RadiciacaoNivel1(int index)
	{
		super(index);

		clone(new Radiciacao(index));
	}

	public RadiciacaoNivel1()
	{
	}

}
