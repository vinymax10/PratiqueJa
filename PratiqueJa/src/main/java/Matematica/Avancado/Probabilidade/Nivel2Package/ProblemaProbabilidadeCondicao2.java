package Matematica.Avancado.Probabilidade.Nivel2Package;

import java.util.Random;

import Matematica.ParCor;
import Matematica.Racional;

public class ProblemaProbabilidadeCondicao2
{
	public String texto;
	public String eventoA;
	public String eventoB;
	public String eventoC;

	public TipoProbabilidadeCondicao2 tipoProbabilidade;
	public int a,b,i,t;
	
	public ProblemaProbabilidadeCondicao2(String texto, String eventoA, String eventoB, TipoProbabilidadeCondicao2 tipoProbabilidade)
	{
		super();
		this.texto = texto;
		this.eventoA = eventoA;
		this.eventoB = eventoB;
		this.tipoProbabilidade=tipoProbabilidade;
	}
	
	public ProblemaProbabilidadeCondicao2(String texto, String eventoA, String eventoB, String eventoC, TipoProbabilidadeCondicao2 tipoProbabilidade)
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
		a = 15 + rand.nextInt(10);
		b = 15 + rand.nextInt(10);
		i = 3 + rand.nextInt(7);
		t=a+b-i;
	}
	
	public ProblemaProbabilidadeCondicao2 clone()
	{
		return new ProblemaProbabilidadeCondicao2(texto,eventoA,eventoB,eventoC,tipoProbabilidade);
	}
	
	public String getPergunta()
	{
		String pergunta=texto;
		pergunta=pergunta.replace("$a", a+"");
		pergunta=pergunta.replace("$b", b+"");
		pergunta=pergunta.replace("$t", t+"");
		pergunta=pergunta.replace("$i", i+"");

		return pergunta;
	}
	
	public String resolucao()
	{
//		String num1, String den1, String num2, String den2
		String resolucaoLatex = formulaCondicao()+"\\\\";
		Racional pa,pi,result;
		switch(tipoProbabilidade)
		{
			case AB:
				resolucaoLatex += "A: \\text{"+eventoA+"}\\\\";
				resolucaoLatex += "B: \\text{"+eventoB+"}\\\\";

				resolucaoLatex += "n(A)="+a+",\\quad "+"n(\\Omega)="+t+", \\quad n(A \\cap B)="+i+"\\\\";
				pa=new Racional(a,t);
				resolucaoLatex += "P(A)="+pa.showDfrac();
				pa.fatoracao(2);
				if(pa.isSimplificou())
					resolucaoLatex += "="+pa.showDfrac();

				pi=new Racional(i,t);
				resolucaoLatex += "\\\\P(A \\cap B)="+pi.showDfrac();
				pi.fatoracao(2);
				if(pi.isSimplificou())
					resolucaoLatex += "="+pi.showDfrac();
				
				resolucaoLatex += "\\\\P(B|A)=\\dfrac{"+pi.showDfrac()+"}{"+pa.showDfrac()+"}=";
				resolucaoLatex += ""+pi.showDfrac()+"\\cdot"+pa.inverter().showDfrac()+"=";

				result=pi.div(pa);
				resolucaoLatex += result.showDfrac();
				result.fatoracao(2);
				if(result.isSimplificou())
					resolucaoLatex += "="+result.showDfrac();

				break;
				
			case BA:
				resolucaoLatex += "A: \\text{"+eventoB+"}\\\\";
				resolucaoLatex += "B: \\text{"+eventoA+"}\\\\";

				resolucaoLatex += "n(A)="+b+",\\quad "+"n(\\Omega)="+t+", \\quad n(A \\cap B)="+i+"\\\\";
				pa=new Racional(b,t);
				resolucaoLatex += "P(A)="+pa.showDfrac();
				pa.fatoracao(2);
				if(pa.isSimplificou())
					resolucaoLatex += "="+pa.showDfrac();

				pi=new Racional(i,t);
				resolucaoLatex += "\\\\P(A \\cap B)="+pi.showDfrac();
				pi.fatoracao(2);
				if(pi.isSimplificou())
					resolucaoLatex += "="+pi.showDfrac();
				
				resolucaoLatex += "\\\\P(B|A)=\\dfrac{"+pi.showDfrac()+"}{"+pa.showDfrac()+"}=";
				resolucaoLatex += ""+pi.showDfrac()+"\\cdot"+pa.inverter().showDfrac()+"=";

				result=pi.div(pa);
				resolucaoLatex += result.showDfrac();
				result.fatoracao(2);
				if(result.isSimplificou())
					resolucaoLatex += "="+result.showDfrac();

				break;
				
		}
		
		return resolucaoLatex;
	}
	
	private String formulaCondicao()
	{
		return ParCor.formula("P(B|A) = \\dfrac{P(A \\cap B)}{P(A)}");
	}
	
	public Racional resultado()
	{
		Racional x=null;
		switch(tipoProbabilidade)
		{
			case AB:
				x=new Racional(i,b);
				break;
				
			case BA:
				x=new Racional(i,a);
				break;
		}
		
		x.fatoracao(2);
		return x;
	}
}
