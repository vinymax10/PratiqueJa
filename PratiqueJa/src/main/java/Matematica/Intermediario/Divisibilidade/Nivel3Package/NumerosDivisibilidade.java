package Matematica.Intermediario.Divisibilidade.Nivel3Package;

import java.util.Random;

import Matematica.Intermediario.Divisibilidade.ResolucaoDivisores;

public class NumerosDivisibilidade
{
	static int numerosImpares[]= {
	45,63,75,81,99,105,117,135,147,153,
	165,171,175,189,195,207,225,231,243,245,
	255,261,273,275,279,285,297,315,325,333,
	345,351,357,363,369,375,385,387,399,405,
	423,425,429,435,441,455,459,465,475,477,
	483,495
	};
	
	static int numerosPares[]= {
	12,16,18,20,24,28,30,32,36,40,
	42,44,48,50,52,54,56,60,64,66,
	68,70,72,76,78,80,84,88,90,92,
	96,98,100,102,104,108,110,112,114,116,
	120,124,126,128,130,132,136,138,140,144,
	148,150,152,154,156,160,162,164,168,170,
	172,174,176,180,182,184,186,188,190,192,
	196,198,200,204,208,210,212,216,220,222,
	224,228,230,232,234,236,238,240,242,244,
	246,248,250,252,256,258,260,264,266,268,
	270,272,276,280,282,284,286,288,290,292,
	294,296,300
	};
	
	public static int getNumeroPar()
	{
		Random rand=new Random();
		int index=rand.nextInt(numerosPares.length);
		return numerosPares[index];
	}
	
	public static int getNumeroImpares()
	{
		Random rand=new Random();
		int index=rand.nextInt(numerosImpares.length);
		return numerosImpares[index];
	}
	
	public static int getNumero()
	{
		Random rand=new Random();
		if(rand.nextBoolean())
			return getNumeroImpares();
		else
			return getNumeroPar();
	}
	
	public static void main(String[] args)
	{
//		ResolucaoDivisores resolucaoDivisores=new ResolucaoDivisores();
//
//		int cont=0;
//		for(int i = 1; i <= 500; i++)
//		{
//			if(i%2==1&&resolucaoDivisores.numerosDividoresResultado(i)>4)
//			{
//				System.out.print(i+",");
//				if(++cont%10==0)
//					System.out.println();
//			}
//		}
		
	}
	
}
