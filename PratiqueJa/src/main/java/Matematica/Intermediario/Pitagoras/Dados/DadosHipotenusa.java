package Matematica.Intermediario.Pitagoras.Dados;

public class DadosHipotenusa extends Dados
{
	public DadosHipotenusa(int limite)
	{
		super();
		
		int b = 10 + rand.nextInt(limite);
		int minC=(int)Math.floor(b * Math.tan(Math.toRadians(15)));
		int maxC=b;
		
		int c =  (int) minC + rand.nextInt((int)(maxC-minC));
		int a = b * b + c * c;
		
		base=new NoPitagoras(b, false);
		altura=new NoPitagoras(c, false);
		
		if(Math.sqrt(a) % 1 == 0)
			hipotenusa=new NoPitagoras((int) Math.sqrt(a), false);			
		else
			hipotenusa=new NoPitagoras(a, true);
		
		porcent=altura.magnitude()/base.magnitude();
		
	}

}
