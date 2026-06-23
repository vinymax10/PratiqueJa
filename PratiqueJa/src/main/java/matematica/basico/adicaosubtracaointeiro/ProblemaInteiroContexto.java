package matematica.basico.adicaosubtracaointeiro;

import java.util.Random;

import matematica.Nomes;

// Inteiro em contexto: valor inicial $x (com sinal) e variação de magnitude $y no sentido
// indicado pelo template (+1 = sobe/ganha; -1 = desce/perde). resultado = x + sentido*y.
public class ProblemaInteiroContexto
{
	public String texto;
	public int sentido;          // +1 ou -1
	public TipoInteiroContexto tipo;

	public int x, y;
	public String nomeM, nomeF;

	public ProblemaInteiroContexto(String texto, int sentido)
	{
		this(texto, sentido, TipoInteiroContexto.VARIACAO);
	}

	public ProblemaInteiroContexto(String texto, int sentido, TipoInteiroContexto tipo)
	{
		super();
		this.texto = texto;
		this.sentido = sentido;
		this.tipo = tipo;
	}

	public void gerarValores(int nivel)
	{
		Random rand = new Random();
		int range;
		switch(nivel)
		{
			case 1:  range = 12;  break;
			case 2:  range = 40;  break;
			default: range = 300; break;
		}
		// x costuma ser negativo (caso mais interessante), mas pode ser positivo
		x = (rand.nextInt(4) == 0) ? (1 + rand.nextInt(range)) : -(1 + rand.nextInt(range));
		y = 1 + rand.nextInt(range);
		nomeM = Nomes.masculino(rand);
		nomeF = Nomes.feminino(rand);
	}

	public ProblemaInteiroContexto clone()
	{
		return new ProblemaInteiroContexto(texto, sentido, tipo);
	}

	public String getPergunta()
	{
		String pergunta = texto;
		pergunta = pergunta.replace("$x", "" + x);
		pergunta = pergunta.replace("$y", "" + y);
		if(pergunta.contains("$nomeM"))
			pergunta = pergunta.replace("$nomeM", nomeM);
		if(pergunta.contains("$nomeF"))
			pergunta = pergunta.replace("$nomeF", nomeF);
		return pergunta;
	}

	public int resultado()
	{
		return x + sentido * y;
	}

	public String[] resolucao()
	{
		int r = resultado();
		String op = sentido > 0 ? " + " : " - ";
		return new String[]
		{
			"Partimos do valor inicial e aplicamos a variação com seu sinal:",
			"\\(" + x + op + y + " = \\mathbf{" + r + "}\\)"
		};
	}
}
