package Matematica.Intermediario.Potenciacao;



import javax.persistence.Entity;

import Matematica.Intermediario.Potenciacao.Nivel1Package.Potenciacao;
import Modelo.Matematica.Conta;

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
