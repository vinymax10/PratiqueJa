package matematica.avancado.probabilidade.nivel1package;

import java.util.ArrayList;
import java.util.List;
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
	
	public String[] resolucao()
	{
		List<String> passos = new ArrayList<>();
		passos.add("\\(" + formula() + "\\)");
		Racional resultado;
		switch(tipoProbabilidade)
		{
			case XB: {
				passos.add("\\(A =\\) " + eventoA);
				String fraPA = pa.showDfrac();
				resultado = pa.mult(new Racional(t));
				String fraRes = resultado.showDfrac();
				resultado.fatoracao(2);
				passos.add("\\(P(A)=" + fraPA + ",\\quad n(\\Omega)=" + t + "\\)");
				passos.add("\\(" + fraPA + "=\\dfrac{n(A)}{" + t + "}\\)");
				passos.add("\\(n(A)=" + fraPA + "\\cdot " + t + "="
						+ (resultado.isSimplificou() ? fraRes + "=\\mathbf{" + resultado.showDfrac() + "}" : "\\mathbf{" + fraRes + "}") + "\\)");
				break;
			}
			case AX: {
				passos.add("\\(A =\\) " + eventoB);
				String fraPB = pb.showDfrac();
				resultado = pb.mult(new Racional(t));
				String fraRes = resultado.showDfrac();
				resultado.fatoracao(2);
				passos.add("\\(P(A)=" + fraPB + ",\\quad n(\\Omega)=" + t + "\\)");
				passos.add("\\(" + fraPB + "=\\dfrac{n(A)}{" + t + "}\\)");
				passos.add("\\(n(A)=" + fraPB + "\\cdot " + t + "="
						+ (resultado.isSimplificou() ? fraRes + "=\\mathbf{" + resultado.showDfrac() + "}" : "\\mathbf{" + fraRes + "}") + "\\)");
				break;
			}
			case XBT: {
				passos.add("\\(A =\\) " + eventoA);
				String fraPA = pa.showDfrac();
				passos.add("\\(P(A)=" + fraPA + ",\\quad n(A)=" + a + "\\)");
				passos.add("\\(" + fraPA + "=\\dfrac{" + a + "}{n(\\Omega)}\\)");
				passos.add("\\(n(\\Omega)=\\dfrac{" + a + "}{" + fraPA + "}\\)");
				resultado = new Racional(a).div(pa);
				String fraRes = resultado.showDfrac();
				resultado.fatoracao(2);
				passos.add("\\(n(\\Omega)=" + a + "\\cdot " + pa.inverter().showDfrac() + "="
						+ (resultado.isSimplificou() ? fraRes + "=\\mathbf{" + resultado.showDfrac() + "}" : "\\mathbf{" + fraRes + "}") + "\\)");
				break;
			}
			case AXT: {
				passos.add("\\(A =\\) " + eventoB);
				String fraPB = pb.showDfrac();
				passos.add("\\(P(A)=" + fraPB + ",\\quad n(A)=" + b + "\\)");
				passos.add("\\(" + fraPB + "=\\dfrac{" + b + "}{n(\\Omega)}\\)");
				passos.add("\\(n(\\Omega)=\\dfrac{" + b + "}{" + fraPB + "}\\)");
				resultado = new Racional(b).div(pb);
				String fraRes = resultado.showDfrac();
				resultado.fatoracao(2);
				passos.add("\\(n(\\Omega)=" + b + "\\cdot " + pb.inverter().showDfrac() + "="
						+ (resultado.isSimplificou() ? fraRes + "=\\mathbf{" + resultado.showDfrac() + "}" : "\\mathbf{" + fraRes + "}") + "\\)");
				break;
			}
		}

		return passos.toArray(new String[0]);
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
