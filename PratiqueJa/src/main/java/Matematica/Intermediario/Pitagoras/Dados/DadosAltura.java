package Matematica.Intermediario.Pitagoras.Dados;

public class DadosAltura extends Dados
{
	public DadosAltura(int limite)
	{
		super();
		int a = 10 + rand.nextInt(limite);
		int maxB=(int)Math.floor(a * Math.cos(Math.toRadians(15)));
		int minB=(int)Math.ceil(a / Math.sqrt(2));
		
		int b =  (int) minB + rand.nextInt((int)(maxB-minB));
		int c = a * a - b * b;
		
		hipotenusa=new NoPitagoras(a, false);
		base=new NoPitagoras(b, false);
		
		if(Math.sqrt(c) % 1 == 0)
			altura=new NoPitagoras((int) Math.sqrt(c), false);			
		else
			altura=new NoPitagoras(c, true);
		
		porcent=altura.magnitude()/base.magnitude();
	}

}
