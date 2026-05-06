package matematica;


public class Calc
{
	public static String fatoracao(int a, int b, int fator)
	{
		while(fator <= Math.abs(a) && fator <= Math.abs(b))
		{
			if(a % fator == 0 && b % fator == 0)
			{
				a /= fator;
				b /= fator;
			}
			else
				fator++;
		}

		if((a < 0 && b < 0) || (a >= 0 && b < 0))
		{
			a *= -1;
			b *= -1;
		}

		if(a == 0 || b == 1 || b == -1)
			return "" + a;
		else
			return "" + a + "/" + b;
	}
}
