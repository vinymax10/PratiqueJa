package matematica.intermediario.pitagoras.dados;

import java.util.Random;


public abstract class Dados 
{
	public NoPitagoras base,altura,hipotenusa;
	
//	porcentagem dos catetos
	public double porcent;
	
	Random rand=new Random();
	
	public Dados()
	{
	}

	@Override
	public String toString()
	{
		return "Dados [\nbase=" + base + "\naltura=" + altura + "\nhipotenusa=" + hipotenusa + "\nporcent=" + porcent 
		+ "]";
	}

}
