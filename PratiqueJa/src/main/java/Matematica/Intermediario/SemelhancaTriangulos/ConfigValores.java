package Matematica.Intermediario.SemelhancaTriangulos;

import java.util.Random;

import Matematica.Racional;
import Matematica.Expressao.MyExpression;

public abstract class ConfigValores 
{
	public Racional a,b,c,d;
	public Racional incognita;
	protected Random rand = new Random();
	public String resolucaoLatex;
	String nome="";
	public Racional resultado;
	int aExp=0;
	int bExp=0;
	boolean hasExpressao;
	
	public ConfigValores() 
	{
	}

	public void choiceNumbers(Racional arco)
	{
		if(hasExpressao)
		{
			aExp = 1 + rand.nextInt(9);
			bExp = 1 + rand.nextInt(9);
			resultado = arco.minus(new Racional(bExp)).div(new Racional(aExp));
			resultado.fatoracao(2);
			
			MyExpression miniExpressao = new MyExpression(aExp + "x+" + bExp);
			nome = miniExpressao.imprimir();
			
		}
		else
			resultado=arco;
		
	}
	
	@Override
	public String toString()
	{
		return "ConfigValores:\n" + (a != null ? "a: " + a + "\n" : "") + (b != null ? "b: " + b + "\n" : "")
		+ (c != null ? "c: " + c + "\n" : "") + (d != null ? "d: " + d + "\n" : "")
		+ (incognita != null ? "incognita: " + incognita + "\n" : "") + (rand != null ? "rand: " + rand + "\n" : "")
		+ (resolucaoLatex != null ? "resolucaoLatex: " + resolucaoLatex : "");
	}
	
}
