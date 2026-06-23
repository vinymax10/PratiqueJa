package matematica.avancado.probabilidade.nivel2package;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import matematica.ParCor;
import matematica.Racional;

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
	
	public String[] resolucao()
	{
		List<String> passos = new ArrayList<>();
		passos.add("\\(" + formulaCondicao() + "\\)");
		switch(tipoProbabilidade)
		{
			case AB: {
				passos.add("\\(A =\\) " + eventoA);
				passos.add("\\(B =\\) " + eventoB);
				Racional pa = new Racional(a, t);
				String fraPA = pa.showDfrac(); pa.fatoracao(2); boolean paSimp = pa.isSimplificou();
				Racional pi = new Racional(i, t);
				String fraPI = pi.showDfrac(); pi.fatoracao(2); boolean piSimp = pi.isSimplificou();
				Racional result = pi.div(pa);
				String fraResult = result.showDfrac(); result.fatoracao(2);
				passos.add("\\(n(A)=" + a + ",\\quad n(\\Omega)=" + t + ",\\quad n(A \\cap B)=" + i + "\\)");
				passos.add("\\(P(A)=" + fraPA + (paSimp ? "=" + pa.showDfrac() : "") + "\\)");
				passos.add("\\(P(A \\cap B)=" + fraPI + (piSimp ? "=" + pi.showDfrac() : "") + "\\)");
				passos.add("\\(P(B|A)=\\dfrac{" + pi.showDfrac() + "}{" + pa.showDfrac() + "}="
						+ pi.showDfrac() + "\\cdot" + pa.inverter().showDfrac() + "="
						+ (result.isSimplificou() ? fraResult + "=\\mathbf{" + result.showDfrac() + "}" : "\\mathbf{" + fraResult + "}") + "\\)");
				break;
			}
			case BA: {
				passos.add("\\(A =\\) " + eventoB);
				passos.add("\\(B =\\) " + eventoA);
				Racional pa = new Racional(b, t);
				String fraPA = pa.showDfrac(); pa.fatoracao(2); boolean paSimp = pa.isSimplificou();
				Racional pi = new Racional(i, t);
				String fraPI = pi.showDfrac(); pi.fatoracao(2); boolean piSimp = pi.isSimplificou();
				Racional result = pi.div(pa);
				String fraResult = result.showDfrac(); result.fatoracao(2);
				passos.add("\\(n(A)=" + b + ",\\quad n(\\Omega)=" + t + ",\\quad n(A \\cap B)=" + i + "\\)");
				passos.add("\\(P(A)=" + fraPA + (paSimp ? "=" + pa.showDfrac() : "") + "\\)");
				passos.add("\\(P(A \\cap B)=" + fraPI + (piSimp ? "=" + pi.showDfrac() : "") + "\\)");
				passos.add("\\(P(B|A)=\\dfrac{" + pi.showDfrac() + "}{" + pa.showDfrac() + "}="
						+ pi.showDfrac() + "\\cdot" + pa.inverter().showDfrac() + "="
						+ (result.isSimplificou() ? fraResult + "=\\mathbf{" + result.showDfrac() + "}" : "\\mathbf{" + fraResult + "}") + "\\)");
				break;
			}
		}

		return passos.toArray(new String[0]);
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
				x=new Racional(i,a);
				break;

			case BA:
				x=new Racional(i,b);
				break;
		}
		
		x.fatoracao(2);
		return x;
	}
}
