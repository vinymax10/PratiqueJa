package matematica.expressao;


public class Posicao
{
	int inicio=-1;
	int fim=-1;
	int numeroElementos=0;
	
	public int getInicio()
	{
		return inicio;
	}

	public void setInicio(int inicio)
	{
		this.inicio = inicio;
	}

	public int getFim()
	{
		return fim;
	}

	public void setFim(int fim)
	{
		this.fim = fim;
		numeroElementos=fim-inicio-1;
	}

	public int getNumeroElementos()
	{
		return numeroElementos;
	}

	public void setNumeroElementos(int numeroElementos)
	{
		this.numeroElementos = numeroElementos;
	}

	@Override
	public String toString()
	{
		return "Posicao2:\ninicio: " + inicio + "\nfim: " + fim + "\nnumeroElementos: " + numeroElementos;
	}	
	
}
