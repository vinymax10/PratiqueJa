package matematica.intermediario.equacaosegundograu.expressao;

//Tentativa de representar uma expressão 		No("Y_x").igual().fracN("-\\Delta").fracD("4a");

public class No 
{
	String texto;
	int numero;
	
	public No(String texto) 
	{
		super();
		this.texto = texto;
	}
	
	public No( int numero) 
	{
		super();
		this.numero = numero;
	}
	
	public void igual()
	{
		texto+="=";
		
	}

}
