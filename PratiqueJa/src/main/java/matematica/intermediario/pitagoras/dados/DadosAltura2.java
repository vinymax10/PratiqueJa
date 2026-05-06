package matematica.intermediario.pitagoras.dados;

public class DadosAltura2 extends Dados
{
	public DadosAltura2(int limite)
	{
		super();
		int a = 10 + rand.nextInt(limite);
		int minB=(int)Math.floor(a * 0.38);
		int maxB=(int)Math.floor(a * 0.88);
		
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
