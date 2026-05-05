package Matematica.Intermediario.Pitagoras.Dados;

public class DadosBase2 extends Dados
{
	public DadosBase2(int limite)
	{
		super();
		
		int a = 10 + rand.nextInt(limite);
		int minC=(int)Math.floor(a * 0.5);
		int maxC=(int)Math.floor(a * 0.93);
		int c =  (int) minC + rand.nextInt((int)(maxC-minC));
		int b = a * a - c * c;
		
		hipotenusa=new NoPitagoras(a, false);
		altura=new NoPitagoras(c, false);
		
		if(Math.sqrt(b) % 1 == 0)
			base=new NoPitagoras((int) Math.sqrt(b), false);			
		else
			base=new NoPitagoras(b, true);
		
		porcent=altura.magnitude()/base.magnitude();
		
	}

}
