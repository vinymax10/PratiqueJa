package Matematica.Intermediario.Pitagoras.Nivel3Package;

import Matematica.Intermediario.Pitagoras.ResolucaoPitagoras;
import Matematica.Intermediario.Pitagoras.Dados.Dados;
import Matematica.Intermediario.Pitagoras.Dados.DadosInteiro;
import Matematica.Intermediario.Pitagoras.Dados.NoPitagoras;
import Modelo.Matematica.Conta;


public class Exercicio1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Exercicio1(int index)
	{
		super(index);

		Dados dados=new DadosInteiro();
		int x1=rand.nextInt(20);
		int x2=x1+(int)dados.base.magnitude();
		int y1=rand.nextInt(20);
		int y2=y1+(int)dados.altura.magnitude();
		
		pergunta="Qual a distância entre os pontos \\( ("+x1+","+y1+")\\) e \\( ("+x2+","+y2+")\\)?";
		
		NoPitagoras a=dados.hipotenusa;
		NoPitagoras b=dados.base;
		NoPitagoras c=dados.altura;
		
		resultadoCorreto = ""+a.show();
		
		resolucaoLatex=ResolucaoPitagoras.formulaDistancia()+"\\\\";
		resolucaoLatex += "d =\\sqrt{("+x2+"-"+x1+")^2 + ("+y2+"-"+y1+")^2}\\\\";
		resolucaoLatex += "d =\\sqrt{"+b.show()+"^2 + "+c.show()+"^2}\\\\";
		resolucaoLatex += "d =\\sqrt{"+b.quad()+" + "+c.quad()+"}\\\\";
		resolucaoLatex += "d =\\sqrt{"+(b.quad()+c.quad())+"} = "+a.show()+"\\\\";
		
		System.out.println(pergunta);
		System.out.println(resultadoCorreto);
		System.out.println(resolucaoLatex);
	}
	
	public static void main(String[] args)
	{
		new Exercicio1(1);
	}

}
