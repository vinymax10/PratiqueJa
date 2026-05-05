package Matematica.Intermediario.RazoesTrigonometricas.Dados;

import Matematica.Racional;

public class DadosHipotenusa extends Dados
{
//	não mostra a Hipotenusa
	public DadosHipotenusa()
	{
		super();
		base = 10 + rand.nextInt(20);
		
		altura = (int)(base*(0.7 + Math.random() * 0.2));
		
		int fator= 2 + rand.nextInt(10);
		
		altura*=fator;
		base*=fator;
		
		strAltura = "" + altura;
		strBase = "" + base;

		tagAngleAltura=new Racional(altura,base);
		tagAngleAltura.fatoracao(2);
		
		tagAngleBase=new Racional(base,altura);
		tagAngleBase.fatoracao(2);
	}

	@Override
	public String toString()
	{
		return "DadosB: " + (strHipotenusa != null ? "strHipotenusa=" + strHipotenusa + ", " : "") + (strAltura != null ? "strBase=" + strAltura + ", " : "")
		+ (strBase != null ? "strAltura=" + strBase : "");
	}
	
	

}
