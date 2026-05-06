package matematica.intermediario.pitagoras.dados;

public class DadosBase extends Dados
{
	public DadosBase(int limite)
	{
		super();
		
		int a = 10 + rand.nextInt(limite);
		int minC=(int)Math.floor(a * Math.sin(Math.toRadians(15)));
		int maxC=(int)Math.ceil(a / Math.sqrt(2));
		
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
