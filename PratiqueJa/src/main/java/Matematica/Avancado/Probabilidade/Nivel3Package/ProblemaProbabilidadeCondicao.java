package Matematica.Avancado.Probabilidade.Nivel3Package;

import java.util.Random;

import Matematica.ParCor;
import Matematica.Racional;

public class ProblemaProbabilidadeCondicao
{
	public String texto;
	public String eventoA;
	public String eventoB;
	public String eventoC;

	public TipoProbabilidadeCondicao tipoProbabilidade;
	public int a,b,c;
	
	public ProblemaProbabilidadeCondicao(String texto, String eventoA, String eventoB, TipoProbabilidadeCondicao tipoProbabilidade)
	{
		super();
		this.texto = texto;
		this.eventoA = eventoA;
		this.eventoB = eventoB;
		this.tipoProbabilidade=tipoProbabilidade;
	}
	
	public ProblemaProbabilidadeCondicao(String texto, String eventoA, String eventoB, String eventoC, TipoProbabilidadeCondicao tipoProbabilidade)
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
		a=3 + rand.nextInt(20);
		b=3 + rand.nextInt(20);
		c=3 + rand.nextInt(20);
	}
	
	public ProblemaProbabilidadeCondicao clone()
	{
		return new ProblemaProbabilidadeCondicao(texto,eventoA,eventoB,eventoC,tipoProbabilidade);
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
		String resolucaoLatex = formulaCondicao()+"\\\\";
		Racional pa,pb,result;
		switch(tipoProbabilidade)
		{
			case AB:
				resolucaoLatex += "A: \\text{1º "+eventoA.toLowerCase()+"}\\\\";
				resolucaoLatex += "B: \\text{2º "+eventoB.toLowerCase()+" dado A}\\\\";

				resolucaoLatex += "n(A)="+a+",\\quad "+"n(\\Omega)="+a+"+"+b+"="+(a+b)+"\\\\";
				pa=new Racional(a,a+b);
				resolucaoLatex += "P(A)="+pa.showDfrac();
				pa.fatoracao(2);
				if(pa.isSimplificou())
					resolucaoLatex += "="+pa.showDfrac();

				resolucaoLatex += "\\\\n(B)="+b+",\\quad "+"n(\\Omega)="+(a-1)+"+"+b+"="+(a+b-1)+"\\\\";
				pb=new Racional(b,a+b-1);
				resolucaoLatex += "P(B|A)="+pb.showDfrac();
				pb.fatoracao(2);
				if(pb.isSimplificou())
					resolucaoLatex += "="+pb.showDfrac();
				
				resolucaoLatex += "\\\\P(A \\cap B)="+pa.showDfrac()+"\\cdot"+pb.showDfrac()+"=";
				result=pa.mult(pb);
				resolucaoLatex += result.showDfrac();
				result.fatoracao(2);
				if(result.isSimplificou())
					resolucaoLatex += "="+result.showDfrac();

				break;
				
			case BA:
				resolucaoLatex += "A: \\text{1º "+eventoB.toLowerCase()+"}\\\\";
				resolucaoLatex += "B: \\text{2º "+eventoA.toLowerCase()+" dado A}\\\\";

				resolucaoLatex += "n(A)="+b+",\\quad "+"n(\\Omega)="+a+"+"+b+"="+(a+b)+"\\\\";
				pa=new Racional(b,a+b);
				resolucaoLatex += "P(A)="+pa.showDfrac();
				pa.fatoracao(2);
				if(pa.isSimplificou())
					resolucaoLatex += "="+pa.showDfrac();

				resolucaoLatex += "\\\\n(B)="+a+",\\quad "+"n(\\Omega)="+(a)+"+"+(b-1)+"="+(a+b-1)+"\\\\";
				pb=new Racional(a,a+b-1);
				resolucaoLatex += "P(B|A)="+pb.showDfrac();
				pb.fatoracao(2);
				if(pb.isSimplificou())
					resolucaoLatex += "="+pb.showDfrac();
				
				resolucaoLatex += "\\\\P(A \\cap B)="+pa.showDfrac()+"\\cdot"+pb.showDfrac()+"=";
				result=pa.mult(pb);
				resolucaoLatex += result.showDfrac();
				result.fatoracao(2);
				if(result.isSimplificou())
					resolucaoLatex += "="+result.showDfrac();

				break;
				
			case AA:
				resolucaoLatex += "A: \\text{1º "+eventoA.toLowerCase()+"}\\\\";
				resolucaoLatex += "B: \\text{2º "+eventoA.toLowerCase()+" dado A}\\\\";

				resolucaoLatex += "n(A)="+a+",\\quad "+"n(\\Omega)="+a+"+"+b+"="+(a+b)+"\\\\";
				pa=new Racional(a,a+b);
				resolucaoLatex += "P(A)="+pa.showDfrac();
				pa.fatoracao(2);
				if(pa.isSimplificou())
					resolucaoLatex += "="+pa.showDfrac();

				resolucaoLatex += "\\\\n(B)="+(a-1)+",\\quad "+"n(\\Omega)="+(a-1)+"+"+b+"="+(a+b-1)+"\\\\";
				pb=new Racional(a-1,a+b-1);
				resolucaoLatex += "P(B|A)="+pb.showDfrac();
				pb.fatoracao(2);
				if(pb.isSimplificou())
					resolucaoLatex += "="+pb.showDfrac();
				
				resolucaoLatex += "\\\\P(A \\cap B)="+pa.showDfrac()+"\\cdot"+pb.showDfrac()+"=";
				result=pa.mult(pb);
				resolucaoLatex += result.showDfrac();
				result.fatoracao(2);
				if(result.isSimplificou())
					resolucaoLatex += "="+result.showDfrac();

				break;
				
			case BB:
				resolucaoLatex += "A: \\text{1º "+eventoB.toLowerCase()+"}\\\\";
				resolucaoLatex += "B: \\text{2º "+eventoB.toLowerCase()+" dado A}\\\\";

				resolucaoLatex += "n(A)="+b+",\\quad "+"n(\\Omega)="+a+"+"+b+"="+(a+b)+"\\\\";
				pa=new Racional(b,a+b);
				resolucaoLatex += "P(A)="+pa.showDfrac();
				pa.fatoracao(2);
				if(pa.isSimplificou())
					resolucaoLatex += "="+pa.showDfrac();

				resolucaoLatex += "\\\\n(B)="+(b-1)+",\\quad "+"n(\\Omega)="+(a)+"+"+(b-1)+"="+(a+b-1)+"\\\\";
				pb=new Racional(b-1,a+b-1);
				resolucaoLatex += "P(B|A)="+pb.showDfrac();
				pb.fatoracao(2);
				if(pb.isSimplificou())
					resolucaoLatex += "="+pb.showDfrac();
				
				resolucaoLatex += "\\\\P(A \\cap B)="+pa.showDfrac()+"\\cdot"+pb.showDfrac()+"=";
				result=pa.mult(pb);
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
		return ParCor.formula("P(A \\cap B)=P(A) \\cdot P(B|A)");
	}
	
	public Racional resultado()
	{
		Racional x=null;
		switch(tipoProbabilidade)
		{
			case AB:
				x=new Racional(a,a+b);
				x=x.mult(new Racional(b,a+b-1));
				break;
			case BA:
				x=new Racional(b,a+b);
				x=x.mult(new Racional(a,a+b-1));
				break;
				
			case AA:
				x=new Racional(a,a+b);
				x=x.mult(new Racional(a-1,a+b-1));
				break;
			case BB:
				x=new Racional(b,a+b);
				x=x.mult(new Racional(b-1,a+b-1));
				break;
		}
		
		x.fatoracao(2);
		return x;
	}
}
