package Matematica.Basico.Decimal.Nivel3Package;

import java.text.DecimalFormat;

import Matematica.Calc;
import Modelo.Matematica.Conta;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class Decimal extends Conta
{
	private static final long serialVersionUID = 1L;

	public Decimal(int index)
	{
		super(index);
		this.binding   = new Binding();
		this.shell = new GroovyShell(binding);
		int digitos=1+rand.nextInt(3);
		int base=(int) Math.pow(10,digitos);
		int inteiro=1+rand.nextInt(base-1);
		double valor = (double)inteiro/base;
		DecimalFormat deci=new DecimalFormat();
		
		textLatex=deci.format(valor)+"=";
		
		resultadoCorreto=""+Calc.fatoracao(inteiro,base,2);
		
	}
	
	public static void main(String[] args) 
	{
//		new Decimal(1);
		
	}
	
}
