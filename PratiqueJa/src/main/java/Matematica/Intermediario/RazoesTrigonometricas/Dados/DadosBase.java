package Matematica.Intermediario.RazoesTrigonometricas.Dados;

import Matematica.Racional;

public class DadosBase extends Dados
{
//	não mostra a base
	public DadosBase()
	{
		super();
		hipotenusa = 10 + rand.nextInt(20);
				
		altura = (int)(hipotenusa*(0.4 + Math.random() * 0.2));
		
		int fator= 2 + rand.nextInt(10);
		
		hipotenusa*=fator;
		altura*=fator;
		
		strHipotenusa = "" + hipotenusa;
		strAltura = "" + altura;
		
		senAngleAltura=new Racional(altura,hipotenusa);
		senAngleAltura.fatoracao(2);
		
		cosAngleBase=senAngleAltura;
	}

}
