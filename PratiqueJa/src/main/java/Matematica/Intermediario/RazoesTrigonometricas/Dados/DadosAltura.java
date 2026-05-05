package Matematica.Intermediario.RazoesTrigonometricas.Dados;

import Matematica.Racional;

public class DadosAltura extends Dados
{
//	não mostra a altura
	public DadosAltura()
	{
		super();
		hipotenusa = 10 + rand.nextInt(20);
		base = (int)(hipotenusa*(0.7 + Math.random() * 0.2));

		int fator= 2 + rand.nextInt(10);
		
		hipotenusa*=fator;
		base*=fator;
		
		strHipotenusa = "" + hipotenusa;
		strBase = "" + base;
		
		cosAngleAltura=new Racional(base,hipotenusa);
		cosAngleAltura.fatoracao(2);
		
		senAngleBase=cosAngleAltura;
	}

	@Override
	public String toString()
	{
		return "DadosB: " + (strHipotenusa != null ? "strHipotenusa=" + strHipotenusa + ", " : "") + (strAltura != null ? "strBase=" + strAltura + ", " : "")
		+ (strBase != null ? "strAltura=" + strBase : "");
	}
	
    public static void main(String[] args) {
    }


}
