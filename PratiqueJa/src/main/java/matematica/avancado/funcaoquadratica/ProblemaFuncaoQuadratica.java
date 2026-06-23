package matematica.avancado.funcaoquadratica;

import java.util.Random;

import matematica.Nomes;

// Lógica única dos problemas de máximo de uma parábola f(x) = -a·x² + b·x + c.
// Constrói b = 2·a·x_v para garantir vértice inteiro:
//   abscissa do vértice  x_v;
//   ordenada do vértice  y_v = a·x_v² + c.
// Conforme o tipo, a resposta é x_v (ABSCISSA) ou y_v (VALOR).
// Bandeiras aUm/cZero adequam o modelo ao contexto (área exige a=1 e c=0; projétil exige c=0).
public class ProblemaFuncaoQuadratica
{
	public String texto;
	public TipoFuncaoQuadratica tipo;
	public String letra;        // variável da função (t, x, ...)
	public String unidade;      // unidade da resposta
	public boolean aUm;         // força a = 1
	public boolean cZero;       // força c = 0

	// valores gerados
	public int a, b, c, xv, yv;
	public String nomeM, nomeF;

	public ProblemaFuncaoQuadratica(String texto, TipoFuncaoQuadratica tipo, String letra,
			String unidade, boolean aUm, boolean cZero)
	{
		super();
		this.texto = texto;
		this.tipo = tipo;
		this.letra = letra;
		this.unidade = unidade;
		this.aUm = aUm;
		this.cZero = cZero;
	}

	public void gerarValores(int nivel)
	{
		Random rand = new Random();
		a  = aUm ? 1 : (1 + rand.nextInt(3));        // 1..3
		xv = 2 + rand.nextInt(11);                   // 2..12
		c  = cZero ? 0 : 10 * (1 + rand.nextInt(10)); // 10..100
		b  = 2 * a * xv;
		yv = a * xv * xv + c;
		nomeM = Nomes.masculino(rand);
		nomeF = Nomes.feminino(rand);
	}

	public ProblemaFuncaoQuadratica clone()
	{
		return new ProblemaFuncaoQuadratica(texto, tipo, letra, unidade, aUm, cZero);
	}

	private String funcao()
	{
		String f = (a == 1 ? "-" + letra + "^2" : "-" + a + letra + "^2");
		f += " + " + b + letra;
		if(c > 0)
			f += " + " + c;
		return f;
	}

	public String getPergunta()
	{
		String pergunta = texto.replace("$f", funcao());
		if(pergunta.contains("$nomeM"))
			pergunta = pergunta.replace("$nomeM", nomeM);
		if(pergunta.contains("$nomeF"))
			pergunta = pergunta.replace("$nomeF", nomeF);
		return pergunta;
	}

	public int resultado()
	{
		return tipo == TipoFuncaoQuadratica.ABSCISSA ? xv : yv;
	}

	public String[] resolucao()
	{
		if(tipo == TipoFuncaoQuadratica.ABSCISSA)
		{
			return new String[] {
				"O ponto ótimo é a abscissa do vértice da parábola, dada por \\(x_v = \\dfrac{-b}{2a}\\).",
				"Com \\(a = -" + a + "\\) e \\(b = " + b + "\\):",
				"\\(x_v = \\dfrac{-" + b + "}{2 \\cdot (-" + a + ")} = \\dfrac{-" + b + "}{-" + (2 * a) + "} = \\mathbf{" + xv + "}\\) " + unidade
			};
		}
		else
		{
			int t1 = -a * xv * xv;
			int t2 = b * xv;
			String cNum = (c > 0 ? " + " + c : "");
			String aFat = (a == 1 ? "" : a + " \\cdot ");
			return new String[] {
				"O valor máximo é a ordenada do vértice. Primeiro achamos a abscissa \\(x_v = \\dfrac{-b}{2a}\\):",
				"\\(x_v = \\dfrac{-" + b + "}{2 \\cdot (-" + a + ")} = " + xv + "\\)",
				"\\(f(" + xv + ") = -" + aFat + xv + "^2 + " + b + " \\cdot " + xv + cNum + "\\)",
				"\\(f(" + xv + ") = " + t1 + " + " + t2 + cNum + " = \\mathbf{" + yv + "}\\) " + unidade
			};
		}
	}
}
