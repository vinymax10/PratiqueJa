package matematica.intermediario.funcaoafim;

import java.util.Random;

import matematica.Nomes;

// Lógica única do problema de modelagem por função do 1º grau f(x) = ±a·x + b.
// Gera os valores (por nível) garantindo solução x inteira e positiva, substitui os
// placeholders do enunciado, calcula a resposta (x) e monta a resolução em LaTeX.
// Os enunciados vêm da biblioteca TextoFuncaoAfim.
public class ProblemaFuncaoAfim
{
	public String texto;            // template com $a (taxa), $b (inicial), $k (alvo), $nomeM/$nomeF
	public TipoFuncaoAfim tipo;
	public String unidade;          // unidade da resposta (horas, meses, km, ...)

	public int a, b, k, x;
	public String nomeM, nomeF;

	public ProblemaFuncaoAfim(String texto, TipoFuncaoAfim tipo, String unidade)
	{
		super();
		this.texto = texto;
		this.tipo = tipo;
		this.unidade = unidade;
	}

	// O nível controla a faixa de valores. Em todos os casos x é a resposta (inteira),
	// e o alvo k é construído a partir de x para garantir divisão exata.
	public void gerarValores(int nivel)
	{
		Random rand = new Random();
		int aMax, xMax, baseMul;
		switch(nivel)
		{
			case 1:  aMax = 5;  xMax = 8;  baseMul = 8;  break;   // a:2..6,  x:2..9,  base 5..40
			case 2:  aMax = 8;  xMax = 11; baseMul = 16; break;   // a:2..9,  x:2..12, base 5..80
			default: aMax = 11; xMax = 13; baseMul = 30; break;   // a:2..12, x:2..14, base 5..150
		}
		a = 2 + rand.nextInt(aMax);
		x = 2 + rand.nextInt(xMax);
		int base = 5 * (1 + rand.nextInt(baseMul));

		if(tipo == TipoFuncaoAfim.DECRESCENTE)
		{
			k = base;            // valor final (positivo)
			b = a * x + k;       // valor inicial (maior que o final)
		}
		else
		{
			b = base;            // valor inicial
			k = a * x + b;       // valor alvo (total)
		}

		nomeM = Nomes.masculino(rand);
		nomeF = Nomes.feminino(rand);
	}

	public ProblemaFuncaoAfim clone()
	{
		return new ProblemaFuncaoAfim(texto, tipo, unidade);
	}

	public String getPergunta()
	{
		String pergunta = texto;
		pergunta = pergunta.replace("$a", "" + a);
		pergunta = pergunta.replace("$b", "" + b);
		pergunta = pergunta.replace("$k", "" + k);
		if(pergunta.contains("$nomeM"))
			pergunta = pergunta.replace("$nomeM", nomeM);
		if(pergunta.contains("$nomeF"))
			pergunta = pergunta.replace("$nomeF", nomeF);
		return pergunta;
	}

	public int resultado()
	{
		return x;
	}

	// Retorna a resolução como passos (um parágrafo por elemento); o chamador adiciona
	// cada passo via addResolucao, na ordem.
	public String[] resolucao()
	{
		if(tipo == TipoFuncaoAfim.DECRESCENTE)
		{
			// f(x) = -a·x + b ; resolvemos -a·x + b = k  =>  x = (b - k)/a
			return new String[] {
				"Modelamos a situação por uma função do 1º grau decrescente: \\(f(x) = -ax + b\\), em que \\(a\\) é a taxa de variação e \\(b\\) o valor inicial.",
				"Com \\(a = " + a + "\\) e \\(b = " + b + "\\), queremos \\(f(x) = " + k + "\\):",
				"\\(-" + a + "x + " + b + " = " + k + "\\)",
				"\\(-" + a + "x = " + (k - b) + "\\)",
				"\\(" + a + "x = " + (b - k) + "\\)",
				"\\(x = \\dfrac{" + (b - k) + "}{" + a + "} = \\mathbf{" + x + "}\\) " + unidade
			};
		}
		// f(x) = a·x + b ; resolvemos a·x + b = k  =>  x = (k - b)/a
		return new String[] {
			"Modelamos a situação por uma função do 1º grau: \\(f(x) = ax + b\\), em que \\(a\\) é a taxa de variação e \\(b\\) o valor inicial.",
			"Com \\(a = " + a + "\\) e \\(b = " + b + "\\), queremos \\(f(x) = " + k + "\\):",
			"\\(" + a + "x + " + b + " = " + k + "\\)",
			"\\(" + a + "x = " + (k - b) + "\\)",
			"\\(x = \\dfrac{" + (k - b) + "}{" + a + "} = \\mathbf{" + x + "}\\) " + unidade
		};
	}
}
