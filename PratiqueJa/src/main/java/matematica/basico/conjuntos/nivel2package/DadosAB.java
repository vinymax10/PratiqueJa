package matematica.basico.conjuntos.nivel2package;

import java.util.Random;

public class DadosAB 
{
	public int a, b, aMb, bMa, aIb, aUb, u;
	public String aStr, bStr, aMbStr, bMaStr, aIbStr, aUbStr, uStr;

	public DadosAB()
	{
		super();
		Random rand = new Random();
		a = 15 + rand.nextInt(10);
		b = 15 + rand.nextInt(10);
		aIb = 3 + rand.nextInt(7);
		aUb=a+b-aIb;
		aMb=a-aIb;
		bMa=b-aIb;
		u=aUb+3 + rand.nextInt(7);
		
		aStr=a+"";
		bStr=b+"";
		aMbStr=aMb+"";
		bMaStr=bMa+"";
		aIbStr=aIb+"";
		aUbStr=aUb+""; 
		uStr=u+"";
	}

}
