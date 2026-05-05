package Matematica.Intermediario.Pitagoras.Dados;

public class DadosHipotenusa2 extends Dados
{
	public DadosHipotenusa2(int limite)
	{
		super();
		
		int b = 10 + rand.nextInt(limite);
		int minC=(int)Math.ceil(b * 0.6);
		int maxC=(int)Math.ceil(b * 2.5);
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
