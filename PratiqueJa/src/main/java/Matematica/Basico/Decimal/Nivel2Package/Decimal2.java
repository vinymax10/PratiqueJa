package Matematica.Basico.Decimal.Nivel2Package;

import Auxiliar.Algebra;
import Modelo.Matematica.Conta;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;


public class Decimal2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Decimal2(int index)
	{
		super(index);
		this.binding   = new Binding();
		this.shell = new GroovyShell(binding);
		
		String a=1+rand.nextInt(10)+"."+(1+rand.nextInt(9));
		String b=1+rand.nextInt(10)+"."+(1+rand.nextInt(9));
		
		textGroovy="";
		textGroovy+=Algebra.sinalMinus()+a;
		textGroovy+=Algebra.sinalPlusMinus();
		textGroovy+="("+Algebra.sinalMinus()+b+")";
		textLatex=textGroovy.replaceAll("\\.", ",")+"=";
		
		resultadoCorreto=""+shell.evaluate(textGroovy).toString().replaceAll("\\.", ",");
		
	}
	
	public static void main(String[] args) 
	{
//		new Decimal2(1);
	}
	
	
}
