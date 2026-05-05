package Matematica.Avancado.Probabilidade.Nivel1Package;

import java.util.Random;

import Matematica.ParCor;
import Matematica.Racional;

public class ProblemaProbabilidadeSimples2
{
	public String texto;
	public String eventoA;
	public String eventoB;
	public String eventoC;

	public TipoProbabilidadeSimples2 tipoProbabilidade;
	public int a,b,t;
	public Racional pa,pb;
	
	public ProblemaProbabilidadeSimples2(String texto, String eventoA, String eventoB, TipoProbabilidadeSimples2 tipoProbabilidade)
	{
		super();
		this.texto = texto;
		this.eventoA = eventoA;
		this.eventoB = eventoB;
		this.tipoProbabilidade=tipoProbabilidade;
	}
	
	public ProblemaProbabilidadeSimples2(String texto, String eventoA, String eventoB, String eventoC, TipoProbabilidadeSimples2 tipoProbabilidade)
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
		a=2*(2 + rand.nextInt(20));
		b=2*(2 + rand.nextInt(20));
		t=a+b;
		pa=new Racional(a,t);
		pa.fatoracao(2);
		pb=new Racional(b,t);
		pb.fatoracao(2);
	}
	
	public ProblemaProbabilidadeSimples2 clone()
	{
		return new ProblemaProbabilidadeSimples2(texto,eventoA,eventoB,eventoC,tipoProbabilidade);
	}
	
	public String getPergunta()
	{
		String pergunta=texto;
		pergunta=pergunta.replace("$a", a+"");
		pergunta=pergunta.replace("$b", b+"");
		pergunta=pergunta.replace("$t", t+"");
		switch(tipoProbabilidade)
		{
			case XB,XBT: pergunta=pergunta.replace("$p", "\\("+pa.showDfrac()+"\\)");
			case AX,AXT: pergunta=pergunta.replace("$p", "\\("+pb.showDfrac()+"\\)");
		}

		return pergunta;
	}
	
	public String resolucao()
	{
//		String num1, String den1, String num2, String den2
		String resolucaoLatex = formula()+"\\\\";
		Racional resultado;
		switch(tipoProbabilidade)
		{
			case XB:
				resolucaoLatex += "A: \\text{"+eventoA+"}\\\\";
				resolucaoLatex += "P(A)="+pa.showDfrac()+",\\quad "+"n(\\Omega)="+t+"\\\\";
				
				resolucaoLatex += pa.showDfrac()+"=\\dfrac{n(A)}{"+t+"}\\\\";
				resultado=pa.mult(new Racional(t));
				resolucaoLatex += "n(A)="+pa.showDfrac()+"\\cdot "+t+"=";
				resolucaoLatex += resultado.showDfrac();
				resultado.fatoracao(2);
				if(resultado.isSimplificou())
					resolucaoLatex += "="+resultado.showDfrac();

				break;
				
			case AX:
				resolucaoLatex += "A: \\text{"+eventoB+"}\\\\";
				resolucaoLatex += "P(A)="+pb.showDfrac()+",\\quad "+"n(\\Omega)="+t+"\\\\";
				
				resolucaoLatex += pb.showDfrac()+"=\\dfrac{n(A)}{"+t+"}\\\\";
				resultado=pb.mult(new Racional(t));
				resolucaoLatex += "n(A)="+pb.showDfrac()+"\\cdot "+t+"=";
				resolucaoLatex += resultado.showDfrac();
				resultado.fatoracao(2);
				if(resultado.isSimplificou())
					resolucaoLatex += "="+resultado.showDfrac();

				break;
				
			case XBT:
				resolucaoLatex += "A: \\text{"+eventoA+"}\\\\";
				resolucaoLatex += "P(A)="+pa.showDfrac()+",\\quad "+"n(A)="+a+"\\\\";
				
				resolucaoLatex += pa.showDfrac()+"=\\dfrac{"+a+"}{n(\\Omega)}\\\\";
				resolucaoLatex += "n(\\Omega)=\\dfrac{"+a+"}{"+pa.showDfrac()+"}\\\\";

				resultado=new Racional(a).div(pa);
				resolucaoLatex += "n(\\Omega)="+a+"\\cdot "+pa.inverter().showDfrac()+"=";
				resolucaoLatex += resultado.showDfrac();
				resultado.fatoracao(2);
				if(resultado.isSimplificou())
					resolucaoLatex += "="+resultado.showDfrac();

				break;
				
			case AXT:
				resolucaoLatex += "A: \\text{"+eventoB+"}\\\\";
				resolucaoLatex += "P(A)="+pb.showDfrac()+",\\quad "+"n(A)="+b+"\\\\";
				
				resolucaoLatex += pb.showDfrac()+"=\\dfrac{"+b+"}{n(\\Omega)}\\\\";
				resolucaoLatex += "n(\\Omega)=\\dfrac{"+b+"}{"+pb.showDfrac()+"}\\\\";

				resultado=new Racional(b).div(pb);
				resolucaoLatex += "n(\\Omega)="+b+"\\cdot "+pb.inverter().showDfrac()+"=";
				resolucaoLatex += resultado.showDfrac();
				resultado.fatoracao(2);
				if(resultado.isSimplificou())
					resolucaoLatex += "="+resultado.showDfrac();

				break;
		}
		
		return resolucaoLatex;
	}
	
	private String formula()
	{
		return ParCor.formula("P(A)=\\dfrac{n(A)}{n(\\Omega)}");
	}
	
	public String resultado()
	{
		switch(tipoProbabilidade)
		{
			case XB:
				return a+"";
			
			case AX:
				return b+"";
				
			case XBT,AXT: 
				return t+"";
		}
		
		return "";
	}
}
