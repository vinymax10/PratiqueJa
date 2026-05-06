package matematica.intermediario.pitagoras.dados;

public class ValorPitagoras
{
	int a,b,c;

	public ValorPitagoras(int c, int b, int a)
	{
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public ValorPitagoras clone()
	{
		return new ValorPitagoras(c,b,a);
	}
}
