package Matematica.Intermediario.Radiciacao;



import Modelo.Matematica.Conta;

import javax.persistence.Entity;

import Matematica.Intermediario.Radiciacao.Nivel1Package.Radiciacao;

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
