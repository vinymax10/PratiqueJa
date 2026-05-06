package matematica.avancado.probabilidade.nivel2package;

import java.util.Random;

import matematica.ParCor;
import matematica.Racional;

public class ProblemaProbabilidadeUniao
{
	public String texto;
	public String eventoA;
	public String eventoB;

	public TipoProbabilidadeUniao tipoProbabilidade;
	public int a,b,i,t,u,nn;
	
	public ProblemaProbabilidadeUniao(String texto, String eventoA, String eventoB, TipoProbabilidadeUniao tipoProbabilidade)
	{
		super();
		this.texto = texto;
		this.eventoA = eventoA;
		this.eventoB = eventoB;
		this.tipoProbabilidade=tipoProbabilidade;
	}
	
	public void gerarValores()
	{
		Random rand=new Random();
		a = 15 + rand.nextInt(10);
		b = 15 + rand.nextInt(10);
		i = 3 + rand.nextInt(7);
		u=a+b-i;
		nn=3 +rand.nextInt(7);
		t=u+nn ;
	}
	
	public ProblemaProbabilidadeUniao clone()
	{
		return new ProblemaProbabilidadeUniao(texto,eventoA,eventoB,tipoProbabilidade);
	}
	
	public String getPergunta()
	{
		String pergunta=texto;
		pergunta=pergunta.replace("$a", a+"");
		pergunta=pergunta.replace("$b", b+"");
		pergunta=pergunta.replace("$t", t+"");
		pergunta=pergunta.replace("$i", i+"");
		pergunta=pergunta.replace("$u", u+"");

		return pergunta;
	}
	
	public String resolucao()
	{
//		String num1, String den1, String num2, String den2
		String resolucaoLatex = "";
		Racional pa,pi,result;
		switch(tipoProbabilidade)
		{
			case AUB:
				resolucaoLatex = formulaUnicao()+"\\\\";
				resolucaoLatex += "A: \\text{"+eventoA+"}\\\\";
				resolucaoLatex += "B: \\text{"+eventoB+"}\\\\";

				resolucaoLatex += "n(A)="+a+"\\quad n(B)="+b+"\\\\"
				+"n(\\Omega)="+t+", \\quad n(A \\cap B)="+i+"\\\\";
				
				resolucaoLatex += "n(A \\cup B) ="+a+"+"+b+"-"+i+"="+u+"\\\\";
				
				pa=new Racional(u,t);
				resolucaoLatex += "P(A \\cup B)="+pa.showDfrac();
				pa.fatoracao(2);
				if(pa.isSimplificou())
					resolucaoLatex += "="+pa.showDfrac();

				break;
				
			case AUBC:
				resolucaoLatex = formulaUnicao()+"\\\\";
				resolucaoLatex += "A: \\text{"+eventoA+"}\\\\";
				resolucaoLatex += "B: \\text{"+eventoB+"}\\\\";

				resolucaoLatex += "n(A)="+a+"\\quad n(B)="+b+"\\\\"
				+"n(\\Omega)="+t+", \\quad n(A \\cap B)="+i+"\\\\";
				
				resolucaoLatex += "n(A \\cup B) ="+a+"+"+b+"-"+i+"="+u+"\\\\";
				resolucaoLatex += "n((A \\cup B)^c) ="+t+"-"+u+"="+nn+"\\\\";

				pa=new Racional(nn,t);
				resolucaoLatex += "P((A \\cup B)^c)="+pa.showDfrac();
				pa.fatoracao(2);
				if(pa.isSimplificou())
					resolucaoLatex += "="+pa.showDfrac();

				break;
				
			case AMB:
				resolucaoLatex = formulaAMenosB()+"\\\\";
				resolucaoLatex += "A: \\text{"+eventoA+"}\\\\";
				resolucaoLatex += "B: \\text{"+eventoB+"}\\\\";

				resolucaoLatex += "n(A)="+a+", \\quad n(\\Omega)="+t+", \\quad n(A \\cap B)="+i+"\\\\";
				resolucaoLatex += "n(A - B) ="+a+"-"+i+"="+(a-i)+"\\\\";

				pa=new Racional(a-i,t);
				resolucaoLatex += "P(A - B)="+pa.showDfrac();
				pa.fatoracao(2);
				if(pa.isSimplificou())
					resolucaoLatex += "="+pa.showDfrac();

				break;
				
		}
		
		return resolucaoLatex;
	}
	
	private String formulaUnicao()
	{
		return ParCor.formula("n(A \\cup B) = n(A) + n(B) - n(A \\cap B)");
	}
	
	private String formulaAMenosB()
	{
		return ParCor.formula("n(A - B) = n(A) - n(A \\cap B)");
	}
	
	public Racional resultado()
	{
		Racional x=null;
		switch(tipoProbabilidade)
		{
			case AUB:
				x=new Racional(u,t);
				break;
				
			case AUBC:
				x=new Racional(nn,t);
				break;
				
			case AMB:
				x=new Racional(a-i,t);
				break;
		}
		
		x.fatoracao(2);
		return x;
	}
}
