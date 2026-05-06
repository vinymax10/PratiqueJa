package matematica.intermediario.razoestrigonometricas.dados;

import java.util.Random;

import matematica.Racional;


public abstract class Dados 
{
	public int base,altura,hipotenusa;
	public Racional senAngleBase,senAngleAltura;
	public Racional cosAngleBase,cosAngleAltura;
	public Racional tagAngleBase,tagAngleAltura;
	
	Random rand=new Random();
	public String strHipotenusa = "", strAltura = "", strBase = "";
	public String strAngleBase,strAngleAltura;

	public Dados()
	{
	}
	
	@Override
	public String toString()
	{
		return "Dados: " + (strHipotenusa != null ? "strHipotenusa=" + strHipotenusa + ", " : "") + (strAltura != null ? "strAltura=" + strAltura + ", " : "")
		+ (strBase != null ? "strBase=" + strBase : "");
	}

}
