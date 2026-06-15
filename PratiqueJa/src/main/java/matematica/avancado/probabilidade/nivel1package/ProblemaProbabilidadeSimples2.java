package matematica.avancado.probabilidade.nivel1package;

import java.util.Random;

import matematica.ParCor;
import matematica.Racional;

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
		String resolucaoLatex = "\\(" + formula() + "\\)" + "\\(\\\\\\)";
		Racional resultado;
		switch(tipoProbabilidade)
		{
			case XB: {
				resolucaoLatex += "\\(A =\\) " + eventoA + "\\(\\\\\\)";
				String fraPA = pa.showDfrac();
				resultado = pa.mult(new Racional(t));
				String fraRes = resultado.showDfrac();
				resultado.fatoracao(2);
				resolucaoLatex += "\\(P(A)=" + fraPA + ",\\quad n(\\Omega)=" + t + " \\\\";
				resolucaoLatex += fraPA + "=\\dfrac{n(A)}{" + t + "} \\\\";
				resolucaoLatex += "n(A)=" + fraPA + "\\cdot " + t + "=";
				resolucaoLatex += (resultado.isSimplificou() ? fraRes + "=\\mathbf{" + resultado.showDfrac() + "}" : "\\mathbf{" + fraRes + "}") + "\\)";
				break;
			}
			case AX: {
				resolucaoLatex += "\\(A =\\) " + eventoB + "\\(\\\\\\)";
				String fraPB = pb.showDfrac();
				resultado = pb.mult(new Racional(t));
				String fraRes = resultado.showDfrac();
				resultado.fatoracao(2);
				resolucaoLatex += "\\(P(A)=" + fraPB + ",\\quad n(\\Omega)=" + t + " \\\\";
				resolucaoLatex += fraPB + "=\\dfrac{n(A)}{" + t + "} \\\\";
				resolucaoLatex += "n(A)=" + fraPB + "\\cdot " + t + "=";
				resolucaoLatex += (resultado.isSimplificou() ? fraRes + "=\\mathbf{" + resultado.showDfrac() + "}" : "\\mathbf{" + fraRes + "}") + "\\)";
				break;
			}
			case XBT: {
				resolucaoLatex += "\\(A =\\) " + eventoA + "\\(\\\\\\)";
				String fraPA = pa.showDfrac();
				resolucaoLatex += "\\(P(A)=" + fraPA + ",\\quad n(A)=" + a + " \\\\";
				resolucaoLatex += fraPA + "=\\dfrac{" + a + "}{n(\\Omega)} \\\\";
				resolucaoLatex += "n(\\Omega)=\\dfrac{" + a + "}{" + fraPA + "} \\\\";
				resultado = new Racional(a).div(pa);
				String fraRes = resultado.showDfrac();
				resultado.fatoracao(2);
				resolucaoLatex += "n(\\Omega)=" + a + "\\cdot " + pa.inverter().showDfrac() + "=";
				resolucaoLatex += (resultado.isSimplificou() ? fraRes + "=\\mathbf{" + resultado.showDfrac() + "}" : "\\mathbf{" + fraRes + "}") + "\\)";
				break;
			}
			case AXT: {
				resolucaoLatex += "\\(A =\\) " + eventoB + "\\(\\\\\\)";
				String fraPB = pb.showDfrac();
				resolucaoLatex += "\\(P(A)=" + fraPB + ",\\quad n(A)=" + b + " \\\\";
				resolucaoLatex += fraPB + "=\\dfrac{" + b + "}{n(\\Omega)} \\\\";
				resolucaoLatex += "n(\\Omega)=\\dfrac{" + b + "}{" + fraPB + "} \\\\";
				resultado = new Racional(b).div(pb);
				String fraRes = resultado.showDfrac();
				resultado.fatoracao(2);
				resolucaoLatex += "n(\\Omega)=" + b + "\\cdot " + pb.inverter().showDfrac() + "=";
				resolucaoLatex += (resultado.isSimplificou() ? fraRes + "=\\mathbf{" + resultado.showDfrac() + "}" : "\\mathbf{" + fraRes + "}") + "\\)";
				break;
			}
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
