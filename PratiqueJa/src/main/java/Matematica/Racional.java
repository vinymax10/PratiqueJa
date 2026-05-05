package Matematica;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;

public class Racional
{
	public long numerador;
	public long denominador;
	boolean simplificou;

	public Racional(long numerador, long denominador)
	{
		this.numerador = numerador;
		this.denominador = denominador;
	}

	public Racional(long numerador)
	{
		this.numerador = numerador;
		this.denominador = 1;
	}

	public Racional add(Racional x)
	{
		return new Racional((numerador * x.denominador) + (x.numerador * denominador), (denominador * x.denominador));
	}

	public Racional minus(Racional x)
	{
		return add(x.trocaSinal());
	}

	public boolean positivo()
	{
		if((numerador >= 0 && denominador > 0) || (numerador < 0 && denominador < 0))
			return true;
		else
			return false;
	}

	public Racional trocaSinal()
	{
		return new Racional(numerador * -1, denominador);
	}

	public Racional mult(Racional x)
	{
		return new Racional((numerador * x.numerador), (denominador * x.denominador));
	}

	public Racional div(Racional x)
	{
		return mult(x.inverter());
	}

	public Racional inverter()
	{
		return new Racional((denominador), (numerador));
	}

	public boolean proporcional(Racional x)
	{
		Racional menor, maior;
		if(numerador < x.numerador)
		{
			menor = new Racional(numerador, denominador);
			maior = new Racional(x.numerador, x.denominador);
		}
		else
		{
			maior = new Racional(numerador, denominador);
			menor = new Racional(x.numerador, x.denominador);
		}

		if(maior.numerador == 0 || menor.numerador == 0 || maior.denominador == 0 || menor.denominador == 0)
		{
			if(maior.numerador == 0 && menor.numerador == 0)
				return true;

			return false;
		}
		else
		{
			if(maior.numerador % menor.numerador == 0 && maior.denominador % menor.denominador == 0
			&& (int) (maior.numerador / menor.numerador) == (int) (maior.denominador / menor.denominador))
				return true;
		}

		return false;
	}

	public boolean isZero()
	{
		return numerador == 0;
	}

	public Racional pow(Racional x)
	{
		Racional num = new Racional(numerador, denominador);
		if(x.numerador < 0)
		{
			x.trocaSinal();
			num = inverter();
		}

		if(x.denominador == 1)
			return new Racional((long) Math.pow(num.numerador, x.numerador), (long) Math.pow(num.denominador, x.numerador));
		else
			System.out.println("Não sei fazer essa conta.");
		return num;
	}

	public void fatoracao(int fator)
	{
		long max=Math.max(Math.abs(numerador), Math.abs(denominador));
		while(fator <= max)
		{
			if(numerador % fator == 0 && denominador % fator == 0)
			{
				numerador /= fator;
				denominador /= fator;
				simplificou = true;
				max=Math.max(Math.abs(numerador), Math.abs(denominador));
			}
			else
				fator++;
		}
		if(denominador < 0)
		{
			numerador *= -1;
			denominador *= -1;
			simplificou = true;
		}
	}
	
	public Racional numeroProximo(int variacao)
	{
		long newNumerador=1,newDenominador=1;
		Random rand=new Random();
		do
		{
			newNumerador=numerador+(rand.nextBoolean()? (1+rand.nextInt(variacao)): -(1+rand.nextInt(variacao)));
		}
		while(newNumerador == 0 );

		if(denominador!=1)
		{
			do
				newDenominador=denominador+(rand.nextBoolean()? (1+rand.nextInt(variacao)): -(1+rand.nextInt(variacao)));
			while(newDenominador == 1|| newDenominador == 0);
		}		

		Racional num = new Racional(newNumerador, newDenominador);
		num.fatoracao(2);
		
		if(positivo()&&!num.positivo())
			num=num.trocaSinal();
		
		return num;
	}
	
	public static Racional toConvert(String fracao)
	{
		fracao=fracao.replaceAll("°", "");
		if(fracao.contains("/"))
		{
			String list[]=fracao.split("/");
			if(list.length==2)
				return new Racional(Integer.parseInt(list[0]),Integer.parseInt(list[1]));
		}
		
		return new Racional(Integer.parseInt(fracao));
	}
	
	public boolean igual(Racional outro)
	{
		if(numerador==outro.numerador&&denominador==outro.denominador)
			return true;
		return false;
	}
	
	public String toStringSinal()
	{
		String str="";
		if(positivo())
			str+="+";
		str+=toString();
		return str;
	}
	
	@Override
	public String toString()
	{
		if(numerador == 0 || denominador == 1)
			return "" + numerador;
		else
			return "" + numerador + "/" + denominador;
	}
	
	public String porcentagem()
	{
		if(denominador==100)
			return numerador+"";
		else
			return "";
	}

	public double decimal()
	{
		return (double)numerador/denominador;
	}

	public String decimalStr()
	{
		DecimalFormat deci = new DecimalFormat("#.#######", new DecimalFormatSymbols(Locale.FRANCE));
		return deci.format(decimal());
	}
	
	public String decimalMoeda()
	{
		DecimalFormat deci = new DecimalFormat("#.00", new DecimalFormatSymbols(Locale.FRANCE));
		return deci.format(decimal());
	}
	
	public String showDfrac()
	{
		if(numerador == 0 || denominador == 1)
			return "" + numerador;
		else
			return "\\dfrac{" + numerador + "}{" + denominador + "}";
	}
	
	public String sinal(boolean primeiro)
	{
		if(positivo())
		{
			if(!primeiro)
				return "+";
		}
		else
			return "-";
		
		return "";
	}
	
	public String showDfracSS()
	{
		if(numerador == 0 || denominador == 1)
			return "" + Math.abs(numerador);
		else
			return "\\dfrac{" +  Math.abs(numerador) + "}{" + denominador + "}";
	}
	
	public String dFracSinal(boolean primeiro, boolean icognita)
	{
		if(icognita&&denominador == 1&&(numerador == 1||numerador == -1))
			return sinal(primeiro);
		else if(numerador == 0 || denominador == 1)
			return sinal(primeiro) + Math.abs(numerador);
		else
			return sinal(primeiro)+"\\dfrac{" +  Math.abs(numerador) + "}{" + Math.abs(denominador) + "}";
	}

	public String showFrac()
	{
		if(numerador == 0 || denominador == 1)
			return "" + numerador;
		else
			return "\\frac{" + numerador + "}{" + denominador + "}";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		long result = 1;
		result = prime * result + denominador;
		result = prime * result + numerador;
		return (int)result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Racional other = (Racional) obj;
		if(denominador != other.denominador)
			return false;
		if(numerador != other.numerador)
			return false;
		return true;
	}

	public String toStringLatex()
	{
		if(numerador == 0 || denominador == 1)
			return "" + numerador;
		else
			return "\\dfrac{" + numerador + "}{" + denominador + "}";
	}

	public boolean isSimplificou()
	{
		return simplificou;
	}

	public void setSimplificou(boolean simplificou)
	{
		this.simplificou = simplificou;
	}

	public static void main(String[] args)
	{
		Racional racional = new Racional(100000, -30000);
		racional.fatoracao(2);
		System.out.println(racional);
	}

}
