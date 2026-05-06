package matematica.basico.multiplicacaonatural;

import jakarta.persistence.Entity;

import modelo.matematica.Conta;

@Entity
public class MultiplicacaoNaturalNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public MultiplicacaoNaturalNivel1(int indice)
	{
		super(indice);

		int a = 1 + rand.nextInt(10);
		int b = 1 + rand.nextInt(10);

		textLatex = a + "\\times" + b + "=";

		resultadoCorreto = "" + (a * b);

		resolucaoLatex = a + "\\times" + b + "=" + (a * b);

	}

	public MultiplicacaoNaturalNivel1()
	{
	}
}
