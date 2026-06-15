package matematica.avancado.probabilidade.nivel3package;

import java.util.Random;

import matematica.ParCor;
import matematica.Racional;

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
		String resolucaoLatex = "\\(" + formulaCondicao() + "\\)" + "\\(\\\\\\)";
		switch(tipoProbabilidade)
		{
			case AB: {
				resolucaoLatex += "\\(A:\\) 1.º " + eventoA.toLowerCase();
				resolucaoLatex += "\\(\\\\\\)";
				resolucaoLatex += "\\(B:\\) 2.º " + eventoB.toLowerCase() + " dado A";
				resolucaoLatex += "\\(\\\\\\)";
				Racional pa = new Racional(a, a+b);
				String fraPA = pa.showDfrac(); pa.fatoracao(2); boolean paSimp = pa.isSimplificou();
				Racional pb = new Racional(b, a+b-1);
				String fraPB = pb.showDfrac(); pb.fatoracao(2); boolean pbSimp = pb.isSimplificou();
				Racional result = pa.mult(pb);
				String fraResult = result.showDfrac(); result.fatoracao(2);
				resolucaoLatex += "\\(n(A)=" + a + ",\\quad n(\\Omega)=" + a + "+" + b + "=" + (a+b) + " \\\\";
				resolucaoLatex += "P(A)=" + fraPA + (paSimp ? "=" + pa.showDfrac() : "") + " \\\\";
				resolucaoLatex += "n(B)=" + b + ",\\quad n(\\Omega)=" + (a-1) + "+" + b + "=" + (a+b-1) + " \\\\";
				resolucaoLatex += "P(B|A)=" + fraPB + (pbSimp ? "=" + pb.showDfrac() : "") + " \\\\";
				resolucaoLatex += "P(A \\cap B)=" + pa.showDfrac() + "\\cdot" + pb.showDfrac() + "=";
				resolucaoLatex += (result.isSimplificou() ? fraResult + "=\\mathbf{" + result.showDfrac() + "}" : "\\mathbf{" + fraResult + "}") + "\\)";
				break;
			}
			case BA: {
				resolucaoLatex += "\\(A:\\) 1.º " + eventoB.toLowerCase();
				resolucaoLatex += "\\(\\\\\\)";
				resolucaoLatex += "\\(B:\\) 2.º " + eventoA.toLowerCase() + " dado A";
				resolucaoLatex += "\\(\\\\\\)";
				Racional pa = new Racional(b, a+b);
				String fraPA = pa.showDfrac(); pa.fatoracao(2); boolean paSimp = pa.isSimplificou();
				Racional pb = new Racional(a, a+b-1);
				String fraPB = pb.showDfrac(); pb.fatoracao(2); boolean pbSimp = pb.isSimplificou();
				Racional result = pa.mult(pb);
				String fraResult = result.showDfrac(); result.fatoracao(2);
				resolucaoLatex += "\\(n(A)=" + b + ",\\quad n(\\Omega)=" + a + "+" + b + "=" + (a+b) + " \\\\";
				resolucaoLatex += "P(A)=" + fraPA + (paSimp ? "=" + pa.showDfrac() : "") + " \\\\";
				resolucaoLatex += "n(B)=" + a + ",\\quad n(\\Omega)=" + a + "+" + (b-1) + "=" + (a+b-1) + " \\\\";
				resolucaoLatex += "P(B|A)=" + fraPB + (pbSimp ? "=" + pb.showDfrac() : "") + " \\\\";
				resolucaoLatex += "P(A \\cap B)=" + pa.showDfrac() + "\\cdot" + pb.showDfrac() + "=";
				resolucaoLatex += (result.isSimplificou() ? fraResult + "=\\mathbf{" + result.showDfrac() + "}" : "\\mathbf{" + fraResult + "}") + "\\)";
				break;
			}
			case AA: {
				resolucaoLatex += "\\(A:\\) 1.º " + eventoA.toLowerCase();
				resolucaoLatex += "\\(\\\\\\)";
				resolucaoLatex += "\\(B:\\) 2.º " + eventoA.toLowerCase() + " dado A";
				resolucaoLatex += "\\(\\\\\\)";
				Racional pa = new Racional(a, a+b);
				String fraPA = pa.showDfrac(); pa.fatoracao(2); boolean paSimp = pa.isSimplificou();
				Racional pb = new Racional(a-1, a+b-1);
				String fraPB = pb.showDfrac(); pb.fatoracao(2); boolean pbSimp = pb.isSimplificou();
				Racional result = pa.mult(pb);
				String fraResult = result.showDfrac(); result.fatoracao(2);
				resolucaoLatex += "\\(n(A)=" + a + ",\\quad n(\\Omega)=" + a + "+" + b + "=" + (a+b) + " \\\\";
				resolucaoLatex += "P(A)=" + fraPA + (paSimp ? "=" + pa.showDfrac() : "") + " \\\\";
				resolucaoLatex += "n(B)=" + (a-1) + ",\\quad n(\\Omega)=" + (a-1) + "+" + b + "=" + (a+b-1) + " \\\\";
				resolucaoLatex += "P(B|A)=" + fraPB + (pbSimp ? "=" + pb.showDfrac() : "") + " \\\\";
				resolucaoLatex += "P(A \\cap B)=" + pa.showDfrac() + "\\cdot" + pb.showDfrac() + "=";
				resolucaoLatex += (result.isSimplificou() ? fraResult + "=\\mathbf{" + result.showDfrac() + "}" : "\\mathbf{" + fraResult + "}") + "\\)";
				break;
			}
			case BB: {
				resolucaoLatex += "\\(A:\\) 1.º " + eventoB.toLowerCase();
				resolucaoLatex += "\\(\\\\\\)";
				resolucaoLatex += "\\(B:\\) 2.º " + eventoB.toLowerCase() + " dado A";
				resolucaoLatex += "\\(\\\\\\)";
				Racional pa = new Racional(b, a+b);
				String fraPA = pa.showDfrac(); pa.fatoracao(2); boolean paSimp = pa.isSimplificou();
				Racional pb = new Racional(b-1, a+b-1);
				String fraPB = pb.showDfrac(); pb.fatoracao(2); boolean pbSimp = pb.isSimplificou();
				Racional result = pa.mult(pb);
				String fraResult = result.showDfrac(); result.fatoracao(2);
				resolucaoLatex += "\\(n(A)=" + b + ",\\quad n(\\Omega)=" + a + "+" + b + "=" + (a+b) + " \\\\";
				resolucaoLatex += "P(A)=" + fraPA + (paSimp ? "=" + pa.showDfrac() : "") + " \\\\";
				resolucaoLatex += "n(B)=" + (b-1) + ",\\quad n(\\Omega)=" + a + "+" + (b-1) + "=" + (a+b-1) + " \\\\";
				resolucaoLatex += "P(B|A)=" + fraPB + (pbSimp ? "=" + pb.showDfrac() : "") + " \\\\";
				resolucaoLatex += "P(A \\cap B)=" + pa.showDfrac() + "\\cdot" + pb.showDfrac() + "=";
				resolucaoLatex += (result.isSimplificou() ? fraResult + "=\\mathbf{" + result.showDfrac() + "}" : "\\mathbf{" + fraResult + "}") + "\\)";
				break;
			}
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
