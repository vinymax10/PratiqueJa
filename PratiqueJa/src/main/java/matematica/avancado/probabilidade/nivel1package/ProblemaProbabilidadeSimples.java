package matematica.avancado.probabilidade.nivel1package;

import java.util.Random;

import matematica.ParCor;
import matematica.Racional;

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
		String resolucaoLatex = "\\(" + formula() + "\\)" + "\\(\\\\\\)";
		Racional p;
		switch(tipoProbabilidade)
		{
			case AX: {
				resolucaoLatex += "\\(A =\\) " + eventoB + "\\(\\\\\\)";
				p = new Racional(b, a+b);
				String fr = p.showDfrac(); p.fatoracao(2);
				resolucaoLatex += "\\(n(A)=" + b + ",\\quad n(\\Omega)=" + a + "+" + b + "=" + (a+b) + " \\\\";
				resolucaoLatex += "P(A)=" + (p.isSimplificou() ? fr + "=\\mathbf{" + p.showDfrac() + "}" : "\\mathbf{" + fr + "}") + "\\)";
				break;
			}
			case XB: {
				resolucaoLatex += "\\(A =\\) " + eventoA + "\\(\\\\\\)";
				p = new Racional(a, a+b);
				String fr = p.showDfrac(); p.fatoracao(2);
				resolucaoLatex += "\\(n(A)=" + a + ",\\quad n(\\Omega)=" + a + "+" + b + "=" + (a+b) + " \\\\";
				resolucaoLatex += "P(A)=" + (p.isSimplificou() ? fr + "=\\mathbf{" + p.showDfrac() + "}" : "\\mathbf{" + fr + "}") + "\\)";
				break;
			}
			case XBC: {
				resolucaoLatex += "\\(A =\\) " + eventoA + "\\(\\\\\\)";
				p = new Racional(a, a+b+c);
				String fr = p.showDfrac(); p.fatoracao(2);
				resolucaoLatex += "\\(n(A)=" + a + ",\\quad n(\\Omega)=" + a + "+" + b + "+" + c + "=" + (a+b+c) + " \\\\";
				resolucaoLatex += "P(A)=" + (p.isSimplificou() ? fr + "=\\mathbf{" + p.showDfrac() + "}" : "\\mathbf{" + fr + "}") + "\\)";
				break;
			}
			case AXC: {
				resolucaoLatex += "\\(A =\\) " + eventoB + "\\(\\\\\\)";
				p = new Racional(b, a+b+c);
				String fr = p.showDfrac(); p.fatoracao(2);
				resolucaoLatex += "\\(n(A)=" + b + ",\\quad n(\\Omega)=" + a + "+" + b + "+" + c + "=" + (a+b+c) + " \\\\";
				resolucaoLatex += "P(A)=" + (p.isSimplificou() ? fr + "=\\mathbf{" + p.showDfrac() + "}" : "\\mathbf{" + fr + "}") + "\\)";
				break;
			}
			case ABX: {
				resolucaoLatex += "\\(A =\\) " + eventoC + "\\(\\\\\\)";
				p = new Racional(c, a+b+c);
				String fr = p.showDfrac(); p.fatoracao(2);
				resolucaoLatex += "\\(n(A)=" + c + ",\\quad n(\\Omega)=" + a + "+" + b + "+" + c + "=" + (a+b+c) + " \\\\";
				resolucaoLatex += "P(A)=" + (p.isSimplificou() ? fr + "=\\mathbf{" + p.showDfrac() + "}" : "\\mathbf{" + fr + "}") + "\\)";
				break;
			}
			case XXC: {
				resolucaoLatex += "\\(A =\\) " + eventoA + " ou " + eventoB.toLowerCase() + "\\(\\\\\\)";
				p = new Racional(a+b, a+b+c);
				String fr = p.showDfrac(); p.fatoracao(2);
				resolucaoLatex += "\\(n(A)=" + a + "+" + b + "=" + (a+b) + ",\\quad n(\\Omega)=" + a + "+" + b + "+" + c + "=" + (a+b+c) + " \\\\";
				resolucaoLatex += "P(A)=" + (p.isSimplificou() ? fr + "=\\mathbf{" + p.showDfrac() + "}" : "\\mathbf{" + fr + "}") + "\\)";
				break;
			}
			case XBX: {
				resolucaoLatex += "\\(A =\\) " + eventoA + " ou " + eventoC.toLowerCase() + "\\(\\\\\\)";
				p = new Racional(a+c, a+b+c);
				String fr = p.showDfrac(); p.fatoracao(2);
				resolucaoLatex += "\\(n(A)=" + a + "+" + c + "=" + (a+c) + ",\\quad n(\\Omega)=" + a + "+" + b + "+" + c + "=" + (a+b+c) + " \\\\";
				resolucaoLatex += "P(A)=" + (p.isSimplificou() ? fr + "=\\mathbf{" + p.showDfrac() + "}" : "\\mathbf{" + fr + "}") + "\\)";
				break;
			}
			case AXX: {
				resolucaoLatex += "\\(A =\\) " + eventoB + " ou " + eventoC.toLowerCase() + "\\(\\\\\\)";
				p = new Racional(b+c, a+b+c);
				String fr = p.showDfrac(); p.fatoracao(2);
				resolucaoLatex += "\\(n(A)=" + b + "+" + c + "=" + (b+c) + ",\\quad n(\\Omega)=" + a + "+" + b + "+" + c + "=" + (a+b+c) + " \\\\";
				resolucaoLatex += "P(A)=" + (p.isSimplificou() ? fr + "=\\mathbf{" + p.showDfrac() + "}" : "\\mathbf{" + fr + "}") + "\\)";
				break;
			}
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
