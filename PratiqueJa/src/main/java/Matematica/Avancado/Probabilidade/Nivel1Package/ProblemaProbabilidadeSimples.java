package Matematica.Avancado.Probabilidade.Nivel1Package;

import java.util.Random;

import Matematica.ParCor;
import Matematica.Racional;

public class ProblemaProbabilidadeSimples
{
	public String texto;
	public String eventoA;
	public String eventoB;
	public String eventoC;

	public TipoProbabilidadeSimples tipoProbabilidade;
	public int a,b,c;
	
	public ProblemaProbabilidadeSimples(String texto, String eventoA, String eventoB, TipoProbabilidadeSimples tipoProbabilidade)
	{
		super();
		this.texto = texto;
		this.eventoA = eventoA;
		this.eventoB = eventoB;
		this.tipoProbabilidade=tipoProbabilidade;
	}
	
	public ProblemaProbabilidadeSimples(String texto, String eventoA, String eventoB, String eventoC, TipoProbabilidadeSimples tipoProbabilidade)
	{
		super();
		this.texto = texto;
		this.eventoA = eventoA;
		this.eventoB = eventoB;
		this.eventoC = eventoC;
		this.tipoProbabilidade=tipoProbabilidade;
	}
	
	public void gerarValores()
	{
		Random rand=new Random();
		a=2 + rand.nextInt(20);
		b=2 + rand.nextInt(20);
		c=2 + rand.nextInt(20);
	}
	
	public ProblemaProbabilidadeSimples clone()
	{
		return new ProblemaProbabilidadeSimples(texto,eventoA,eventoB,eventoC,tipoProbabilidade);
	}
	
	public String getPergunta()
	{
		String pergunta=texto;
		pergunta=pergunta.replace("$a", a+"");
		pergunta=pergunta.replace("$b", b+"");
		pergunta=pergunta.replace("$c", c+"");

		return pergunta;
	}
	
	public String resolucao()
	{
//		String num1, String den1, String num2, String den2
		String resolucaoLatex = formula()+"\\\\";
		Racional p;
		switch(tipoProbabilidade)
		{
			case AX:
				resolucaoLatex += "A: \\text{"+eventoB+"}\\\\";
				resolucaoLatex += "n(A)="+b+",\\quad "+"n(\\Omega)="+a+"+"+b+"="+(a+b)+"\\\\";
				p=new Racional(b,a+b);
				resolucaoLatex += "P(A)="+p.showDfrac();
				p.fatoracao(2);
				if(p.isSimplificou())
					resolucaoLatex += "="+p.showDfrac();

				break;
				
			case XB:
				resolucaoLatex += "A: \\text{"+eventoA+"}\\\\";
				resolucaoLatex += "n(A)="+a+",\\quad "+"n(\\Omega)="+a+"+"+b+"="+(a+b)+"\\\\";
				p=new Racional(a,a+b);
				resolucaoLatex += "P(A)="+p.showDfrac();
				p.fatoracao(2);
				if(p.isSimplificou())
					resolucaoLatex += "="+p.showDfrac();

				break;
				
			case XBC:
				resolucaoLatex += "A: \\text{"+eventoA+"}\\\\";
				resolucaoLatex += "n(A)="+a+",\\quad "+"n(\\Omega)="+a+"+"+b+"+"+c+"="+(a+b+c)+"\\\\";
				p=new Racional(a,a+b+c);
				resolucaoLatex += "P(A)="+p.showDfrac();
				p.fatoracao(2);
				if(p.isSimplificou())
					resolucaoLatex += "="+p.showDfrac();
				break;
			case AXC:
				resolucaoLatex += "A: \\text{"+eventoB+"}\\\\";
				resolucaoLatex += "n(A)="+b+",\\quad "+"n(\\Omega)="+a+"+"+b+"+"+c+"="+(a+b+c)+"\\\\";
				p=new Racional(b,a+b+c);
				resolucaoLatex += "P(A)="+p.showDfrac();
				p.fatoracao(2);
				if(p.isSimplificou())
					resolucaoLatex += "="+p.showDfrac();

				break;
			case ABX:
				resolucaoLatex += "A: \\text{"+eventoC+"}\\\\";
				resolucaoLatex += "n(A)="+c+",\\quad "+"n(\\Omega)="+a+"+"+b+"+"+c+"="+(a+b+c)+"\\\\";
				p=new Racional(c,a+b+c);
				resolucaoLatex += "P(A)="+p.showDfrac();
				p.fatoracao(2);
				if(p.isSimplificou())
					resolucaoLatex += "="+p.showDfrac();
				break;
				
			case XXC:
				resolucaoLatex += "A: \\text{"+eventoA+" ou "+eventoB.toLowerCase()+"}\\\\";
				resolucaoLatex += "n(A)="+a+"+"+b+"="+(a+b)+",\\quad "+"n(\\Omega)="+a+"+"+b+"+"+c+"="+(a+b+c)+"\\\\";
				p=new Racional(a+b,a+b+c);
				resolucaoLatex += "P(A)="+p.showDfrac();
				p.fatoracao(2);
				if(p.isSimplificou())
					resolucaoLatex += "="+p.showDfrac();
				break;
				
			case XBX:
				resolucaoLatex += "A: \\text{"+eventoA+" ou "+eventoC.toLowerCase()+"}\\\\";
				resolucaoLatex += "n(A)="+a+"+"+c+"="+(a+c)+",\\quad "+"n(\\Omega)="+a+"+"+b+"+"+c+"="+(a+b+c)+"\\\\";
				p=new Racional(a+c,a+b+c);
				resolucaoLatex += "P(A)="+p.showDfrac();
				p.fatoracao(2);
				if(p.isSimplificou())
					resolucaoLatex += "="+p.showDfrac();
				break;
				
			case AXX:
				resolucaoLatex += "A: \\text{"+eventoB+" ou "+eventoC.toLowerCase()+"}\\\\";
				resolucaoLatex += "n(A)="+b+"+"+c+"="+(b+c)+",\\quad "+"n(\\Omega)="+a+"+"+b+"+"+c+"="+(a+b+c)+"\\\\";
				p=new Racional(b+c,a+b+c);
				resolucaoLatex += "P(A)="+p.showDfrac();
				p.fatoracao(2);
				if(p.isSimplificou())
					resolucaoLatex += "="+p.showDfrac();
				break;
		}
		
		return resolucaoLatex;
	}
	
	private String formula()
	{
		return ParCor.formula("P(A)=\\dfrac{n(A)}{n(\\Omega)}");
	}
	
	public Racional resultado()
	{
		Racional x=null;
		switch(tipoProbabilidade)
		{
			case AX:
				x=new Racional(b,a+b);
				break;
			case XB:
				x=new Racional(a,a+b);
				break;
				
			case XBC:
				x=new Racional(a,a+b+c);
				break;
			case AXC:
				x=new Racional(b,a+b+c);
				break;
			case ABX:
				x=new Racional(c,a+b+c);
				break;
			case XXC:
				x=new Racional(a+b,a+b+c);
				break;
			case XBX:
				x=new Racional(a+c,a+b+c);
				break;
			case AXX:
				x=new Racional(b+c,a+b+c);
				break;
		}
		
		x.fatoracao(2);
		return x;
	}
}
