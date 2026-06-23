package matematica.avancado.funcaoexponencial;

import java.util.Random;

import matematica.Nomes;

// Lógica única dos problemas de modelagem exponencial. Conforme o tipo:
//   CRESCIMENTO: N0 · fator^t  (fator 2 ou 3, definido no template);
//   MEIAVIDA:    M0 · (1/2)^k, com k = tempoTotal / meiaVida;
//   JUROS:       C · (1 + i)^t, com casos pré-calculados de potência inteira.
// Os enunciados (com $n0, $t, $h, $p) vêm da biblioteca TextoFuncaoExponencial.
public class ProblemaFuncaoExponencial
{
	// Casos de juros compostos que produzem montante inteiro: {C, taxa%, t, M}.
	private static final int[][]  CASOS_JUROS = {
		{1000, 10, 2, 1210},
		{1000, 10, 3, 1331},
		{2000, 10, 2, 2420},
		{1000, 20, 2, 1440},
		{2000, 20, 3, 3456},
		{1600, 25, 2, 2500},
	};
	private static final String[] BASE_JUROS = {"1{,}10","1{,}10","1{,}10","1{,}20","1{,}20","1{,}25"};
	private static final String[] DEC_JUROS  = {"1{,}21","1{,}331","1{,}21","1{,}44","1{,}728","1{,}5625"};

	public String texto;
	public TipoFuncaoExponencial tipo;
	public int fator;               // CRESCIMENTO: 2 ou 3

	// valores gerados
	public int n0, t, resultado;
	public int meiaVida, k;         // MEIAVIDA
	public int taxa;                // JUROS
	public String baseStr, decStr;  // JUROS
	public String nomeM, nomeF;

	public ProblemaFuncaoExponencial(String texto, TipoFuncaoExponencial tipo)
	{
		this(texto, tipo, 2);
	}

	public ProblemaFuncaoExponencial(String texto, TipoFuncaoExponencial tipo, int fator)
	{
		super();
		this.texto = texto;
		this.tipo = tipo;
		this.fator = fator;
	}

	public void gerarValores(int nivel)
	{
		Random rand = new Random();
		switch(tipo)
		{
			case CRESCIMENTO:
			{
				if(fator == 3)
				{
					int[] opcoes = {10, 20, 50, 100};
					n0 = opcoes[rand.nextInt(opcoes.length)];
					t  = 2 + rand.nextInt(3);            // 2..4
				}
				else
				{
					int[] opcoes = {100, 200, 500, 1000};
					n0 = opcoes[rand.nextInt(opcoes.length)];
					t  = 3 + rand.nextInt(3);            // 3..5
				}
				resultado = n0 * (int) Math.pow(fator, t);
				break;
			}
			case MEIAVIDA:
			{
				int[] opcoes = {200, 400, 800, 1600};
				n0 = opcoes[rand.nextInt(opcoes.length)];
				k = 2 + rand.nextInt(2);                 // 2 ou 3 meias-vidas
				meiaVida = 5 * (1 + rand.nextInt(5));    // 5,10,15,20,25
				t = k * meiaVida;
				resultado = n0 / (int) Math.pow(2, k);
				break;
			}
			default: // JUROS
			{
				int idx = rand.nextInt(CASOS_JUROS.length);
				n0   = CASOS_JUROS[idx][0];
				taxa = CASOS_JUROS[idx][1];
				t    = CASOS_JUROS[idx][2];
				resultado = CASOS_JUROS[idx][3];
				baseStr = BASE_JUROS[idx];
				decStr  = DEC_JUROS[idx];
				break;
			}
		}
		nomeM = Nomes.masculino(rand);
		nomeF = Nomes.feminino(rand);
	}

	public ProblemaFuncaoExponencial clone()
	{
		return new ProblemaFuncaoExponencial(texto, tipo, fator);
	}

	public String getPergunta()
	{
		String pergunta = texto;
		pergunta = pergunta.replace("$n0", "" + n0);
		pergunta = pergunta.replace("$h", "" + meiaVida);
		pergunta = pergunta.replace("$p", "" + taxa);
		pergunta = pergunta.replace("$t", "" + t);
		if(pergunta.contains("$nomeM"))
			pergunta = pergunta.replace("$nomeM", nomeM);
		if(pergunta.contains("$nomeF"))
			pergunta = pergunta.replace("$nomeF", nomeF);
		if(tipo == TipoFuncaoExponencial.JUROS)
			pergunta += " Use \\((" + baseStr + ")^{" + t + "} = " + decStr + "\\).";
		return pergunta;
	}

	public int resultado()
	{
		return resultado;
	}

	public String[] resolucao()
	{
		switch(tipo)
		{
			case CRESCIMENTO:
			{
				int potencia = (int) Math.pow(fator, t);
				return new String[]{
					"O modelo de crescimento é \\(N(t) = N_0 \\cdot " + fator + "^t\\).",
					"Com \\(N_0 = " + n0 + "\\) e \\(t = " + t + "\\):",
					"\\(N(" + t + ") = " + n0 + " \\cdot " + fator + "^{" + t + "} = " + n0 + " \\cdot " + potencia + "\\)",
					"\\(N(" + t + ") = \\mathbf{" + resultado + "}\\)",
				};
			}
			case MEIAVIDA:
			{
				int denom = (int) Math.pow(2, k);
				return new String[]{
					"O tempo corresponde a \\(k = " + t + " \\div " + meiaVida + " = " + k + "\\) meias-vidas.",
					"O modelo de decaimento é \\(M = M_0 \\cdot \\left(\\dfrac{1}{2}\\right)^k\\):",
					"\\(M = " + n0 + " \\cdot \\left(\\dfrac{1}{2}\\right)^{" + k + "}"
						+ " = \\dfrac{" + n0 + "}{" + denom + "}"
						+ " = \\mathbf{" + resultado + "}\\,\\text{g}\\)",
				};
			}
			default: // JUROS
			{
				return new String[]{
					"O montante é dado por \\(M = C \\cdot (1 + i)^t\\):",
					"\\(M = " + n0 + " \\cdot (1 + 0{,}" + (taxa < 10 ? "0" : "") + taxa + ")^{" + t + "}\\)",
					"\\(M = " + n0 + " \\cdot (" + baseStr + ")^{" + t + "}\\)",
					"\\(M = " + n0 + " \\cdot " + decStr + " = \\mathbf{R\\$\\," + resultado + "{,}00}\\)",
				};
			}
		}
	}
}
