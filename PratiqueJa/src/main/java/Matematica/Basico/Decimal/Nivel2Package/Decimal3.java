package Matematica.Basico.Decimal.Nivel2Package;

import Auxiliar.Algebra;
import Modelo.Matematica.Conta;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;


public class Decimal3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Decimal3(int index)
	{
		super(index);
		this.binding   = new Binding();
		this.shell = new GroovyShell(binding);
		
		String a=1+rand.nextInt(10)+"."+(1+rand.nextInt(9));
		String b=1+rand.nextInt(10)+"."+(1+rand.nextInt(9));
		
		textGroovy="";
		textGroovy+=Algebra.sinalMinus()+a;
		textGroovy+=Algebra.sinalMultDiv()+b;
		
		textLatex=textGroovy.replace("*", "\\cdot").replace("/","\\div");

		textLatex=textLatex.replaceAll("\\.", ",")+"=";
		
		resultadoCorreto=""+shell.evaluate(textGroovy).toString().replaceAll("\\.", ",");
		String str[]=resultadoCorreto.split(",");
		
		resultadoCorreto=str[0]+","+str[1].substring(0,Math.min(str[1].length(), 3));
		
	}
	
	public static void main(String[] args) 
	{
//		new Decimal3(1);
		
	}
	
	
}
