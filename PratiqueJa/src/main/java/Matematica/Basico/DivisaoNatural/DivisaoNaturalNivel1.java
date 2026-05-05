package Matematica.Basico.DivisaoNatural;

import javax.persistence.Entity;

import Modelo.Matematica.Conta;

@Entity
public class DivisaoNaturalNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public DivisaoNaturalNivel1(int indice)
	{
		super(indice);

		int a = 1 + rand.nextInt(10);
		int b = 1 + rand.nextInt(10);

		textLatex = (a * b) + "\\div" + b + "=";
		resultadoCorreto = "" + a;
		resolucaoLatex = (a * b) + "\\div" + b + "=" + a;
	}

	public DivisaoNaturalNivel1()
	{
	}

	public static void main(String[] args)
	{
		new DivisaoNaturalNivel1(1);
	}

}
