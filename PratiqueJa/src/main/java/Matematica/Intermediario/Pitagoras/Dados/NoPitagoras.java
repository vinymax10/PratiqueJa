package Matematica.Intermediario.Pitagoras.Dados;

public class NoPitagoras
{
	int valor;
	boolean raiz;
	public String str;
	
	public NoPitagoras(int valor, boolean raiz)
	{
		super();
		this.valor = valor;
		this.raiz = raiz;
		this.str=show();
	}

	public double magnitude()
	{
		if(raiz)
			return Math.sqrt(valor);
		else
			return valor;
	}
	
	public String show()
	{
		if(raiz)
			return "\\sqrt{"+valor+"}";
		else
			return ""+valor;
	}
	
	public String showEleQuad()
	{
		if(raiz)
			return "(\\sqrt{"+valor+"})^2";
		else
			return ""+valor+"^2";
	}
	
	
	public String showQuad()
	{
		if(raiz)
			return ""+valor;
		else
			return ""+(valor*valor);
	}
	
	public int quad()
	{
		if(raiz)
			return valor;
		else
			return valor*valor;
	}

	@Override
	public String toString()
	{
		return "NoPitagoras: valor=" + valor + ", raiz=" + raiz + ", " + (str != null ? "str=" + str : "");
	}
	
}
