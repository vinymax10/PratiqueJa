package matematica.intermediario.radiciacao;

public class Fator
{
	public int termo;
	public int potencia;
	
	public Fator(int termo, int potencia)
	{
		this.termo = termo;
		this.potencia = potencia;
	}

	@Override
	public String toString()
	{
		if(potencia>1)
			return "" + termo + "^{" + potencia+"}";
		else
			return "" + termo;
	}

	
}
