package matematica.resolucaonatural;

public class Termo 
{
	public int digito;
	public int novoDigito;
	public boolean temNovoDigito;
	
	public Termo(int digito) 
	{
		this.digito = digito;
	}
	
	public int digitoValido()
	{
		if(temNovoDigito)
			return novoDigito;
		else
			return digito;
	}
	
	public String latex()
	{
		String str="";

		if(temNovoDigito)
			str+="\\overset{\\textstyle\\textcolor{iris}{"+getNumero(novoDigito)+"}}{\\textcolor{babypink}{\\cancel{"+getNumero(digito)+"}}}";
		else
			str+=getNumero(digito);

		return str;
	}
	
	private String getNumero(int numero)
	{
		String str="";
		if(numero>=10)
			str+="\\textcolor{iris}{^"+(numero/10)+"}"+(numero%10);
		else
			str+=numero;

		return str;
	}
}
