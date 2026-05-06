package matematica;

import java.text.MessageFormat;

/**
 * Calcula o resultado de uma dada expressão, de acordo com os coeficientes
 * definidos.
 * 
 * A expressão é uma fórmula matemática válida, podendo conter os coeficiente
 * (variáveis), operadores ("+", "-", "*", "/" e "^" -> adição, subtração,
 * multiplicação, divisão e potenciação) e parêntesis.
 * 
 * Os coeficientes podem ser refenciados na fórmula pelo índice do vetor entre
 * chaves {n} ou pelas letras de "A" a "Z", sendo "A" equivalente ao índice 0
 * (zero) do vetor.
 * 
 * Exemplos: "({0} + {1} + {2}) - {3}" ou "(A + B) / (C - D) ^ 2"
 * 
 * Precedência de Operadores (quando não há parêntesis): 1. Potenciação 2.
 * Multiplicação e Divisão 3. Adição e subtração
 */

public class ExpressaoExt
{

	private static int TIPO_NUMERO = 1;
	private static int TIPO_OPERADOR = 2;
	private static int TIPO_PONTO = 3;
	private static int TIPO_LETRA_AZ = 4;
	private static int TIPO_CHAVE_ABRIR = 5;
	private static int TIPO_CHAVE_FECHAR = 6;
	private static int TIPO_PARENTESIS_ABRIR = 7;
	private static int TIPO_PARENTESIS_FECHAR = 8;

	private static String parentesisFaltando = "Parêntesis faltando a partir da posição {0}!";
	private static String valorEsperado = "Coeficiente ou número esperado na posição {0}!";
	private static String numeroEsperado = "Número esperado na posição {0}!";
	private static String indiceEsperado = "Índice de coeficiente esperado na posição {0}!";
	private static String chaveEsperada = "Chave de fechamento esperada na posição {0}!";
	private static String divisaoPorZero = "Divisão por zero na posição {0}!";
	private static String operadorEsperado = "Operador esperado na posição {0}!";
	private static String indiceInvalido = "Índice de coeficiente inválido na posição {0}!";

	private int posExpressao;
	private int tipoElemento;
	private char letra;
	private String expressao;
	private Racional[] coeficientes;

	/**
	 * Atalho para execução alternativa
	 */
	public static Racional calcular(String expressao, Racional[] coeficientes)
	{

		try
		{

			ExpressaoExt exp = new ExpressaoExt(expressao, coeficientes);
			return exp.calcular();

		}
		catch(Exception e)
		{

			return new Racional(0);
		}
	}

	/**
	 * Atalho para execução alternativa
	 */
	public static Racional calcular(String expressao, String[] coeficientes)
	{
		try
		{
			ExpressaoExt exp = new ExpressaoExt(expressao, coeficientes);
			return exp.calcular();
		}
		catch(Exception e)
		{
			return new Racional(0);
		}
	}

	/**
	 * Atalho para execução alternativa
	 */
	public static Racional calcular(String expressao, Object[] coeficientes)
	{

		try
		{

			ExpressaoExt exp = new ExpressaoExt(expressao, coeficientes);
			return exp.calcular();

		}
		catch(Exception e)
		{

			return new Racional(0);

		}

	}

	/**
	 * Constrói um avaliador para a expressão e respectivos coeficientes (variáveis)
	 * 
	 * Exemplo: new Expressao("(A + B + C) - D", new Racional[] {v1, v2, v3, v4}
	 */
	public ExpressaoExt(String expressao, Racional[] coeficientes) throws Exception
	{
		this.expressao = expressao.replaceAll("\\s", "").toUpperCase();
		this.coeficientes = coeficientes;
		this.posExpressao = -1;

	}

	/**
	 * Constrói um avaliador para a expressão e respectivos coeficientes (variáveis)
	 * 
	 * Exemplo: new Expressao("({0} + {1} + {2}) - {3}", new String[] {s1, s2, s3,
	 * s4}
	 */
	public ExpressaoExt(String expressao, String[] coeficientes) throws Exception
	{

		this.expressao = expressao.replaceAll("\\s", "").toUpperCase();
		this.coeficientes = new Racional[coeficientes.length];
		for(int i = 0; i < coeficientes.length; i++)
		{
			this.coeficientes[i] = new Racional(Integer.valueOf(coeficientes[i]));
		}
		this.posExpressao = -1;

	}

	/**
	 * Constrói um avaliador para a expressão e respectivos coeficientes (variáveis)
	 * Os coeficientes podem ser String, Racional, Integer ou Double
	 * 
	 * Exemplo: new Expressao("({0} + {1} + {2}) - {3}", new Object[] {o1, o2, o3,
	 * o4}
	 */
	public ExpressaoExt(String expressao, Object[] coeficientes) throws Exception
	{

		this.expressao = expressao.replaceAll("\\s", "").toUpperCase();
		this.coeficientes = new Racional[coeficientes.length];
		for(int i = 0; i < coeficientes.length; i++)
		{
			if(coeficientes[i] == null)
			{
				this.coeficientes[i] = new Racional(0);
			}
			else if(coeficientes[i] instanceof String)
			{
				this.coeficientes[i] = new Racional(Integer.valueOf((String) coeficientes[i]));
			}
			else if(coeficientes[i] instanceof Racional)
			{
				this.coeficientes[i] = (Racional) coeficientes[i];
			}
			else if(coeficientes[i] instanceof Integer)
			{
				this.coeficientes[i] = new Racional((Integer) coeficientes[i]);
			}
			else
			{
				// tenta converter o objeto para String e depois para Racional
				this.coeficientes[i] = new Racional(Integer.valueOf((String) coeficientes[i].toString()));
			}
		}
		this.posExpressao = -1;

	}

	// retorna verdadeiro se o próximo caracter for o início de um valor válido com
	// ou sem sinal
	private boolean ehValorSinal()
	{

		return tipoElemento == TIPO_NUMERO || tipoElemento == TIPO_CHAVE_ABRIR || tipoElemento == TIPO_PARENTESIS_ABRIR
		|| (tipoElemento == TIPO_OPERADOR && (letra == '+' || letra == '-') || tipoElemento == TIPO_LETRA_AZ);

	}

	/**
	 * Avalia a expressão de acordo com os coeficientes definidos e retorna o
	 * resultado
	 */
	public Racional calcular() throws Exception
	{

		Racional resposta = new Racional(0);
		proximoElemento();

		if(!EOF())
		{
			if(!ehValorSinal())
			{
				Erro(valorEsperado);
			}
			resposta = expressaoPrecedencia();
		}

		while(!EOF())
		{

			if(tipoElemento == TIPO_OPERADOR)
			{
				char operador = letra;
				proximoElemento();

				if(!ehValorSinal())
				{
					Erro(valorEsperado);
				}
				Racional outroValor = expressaoPrecedencia();

				if(operador == '+')
				{
					resposta = resposta.add(outroValor);
				}
				else if(operador == '-')
				{
					resposta = resposta.minus(outroValor);
				}
			}
			else
			{
				Erro(operadorEsperado);
			}

		}
		return resposta;
	}

	// avalia uma expressão com precedência 1, atualmente multiplicação e divisão
	// (analisador sintático)
	private Racional expressaoPrecedencia() throws Exception
	{

		Racional resposta = expressaoPrecedencia2();
		while(!EOF() && (tipoElemento == TIPO_OPERADOR && (letra == '*' || letra == '/')))
		{

			char operador = letra;
			proximoElemento();
			if(ehValorSinal())
			{

				Racional outroValor = expressaoPrecedencia2();
				if(operador == '*')
				{
					resposta = resposta.mult(outroValor);
				}
				else if(operador == '/')
				{
					if(outroValor.isZero())
					{
						Erro(divisaoPorZero);
					}
					resposta = resposta.div(outroValor);
				}

			}

		}
		return resposta;

	}

	// avalia uma expressão com precedência 2, atualmente a potenciação (analisador
	// sintático)
	private Racional expressaoPrecedencia2() throws Exception
	{

		Racional resposta = valorSinal();
		while(!EOF() && (tipoElemento == TIPO_OPERADOR && letra == '^'))
		{

			char operador = letra;
			proximoElemento();
			if(ehValorSinal())
			{

				Racional outroValor = valorSinal();
				if(operador == '^')
				{
					resposta = resposta.pow(outroValor);
				}

			}

		}
		return resposta;

	}

	// avalia um valor válido na expressão com ou sem um operador unitário
	// (analisador sintático)
	private Racional valorSinal() throws Exception
	{

		// operador unitário
		if(tipoElemento == TIPO_OPERADOR && (letra == '+' || letra == '-'))
		{

			char operadorUnitario = letra;
			proximoElemento();
			Racional valor = valor();
			if(operadorUnitario == '-')
			{
				valor = valor.trocaSinal();
			}
			return valor;

		}
		else
		{
			return valor();
		}

	}

	// avalia um valor válido na expressão: {n}, 9.99, 9.99, (...), A (analisador
	// sintático)
	private Racional valor() throws Exception
	{

		if(tipoElemento == TIPO_PARENTESIS_ABRIR)
		{

			int numParentesis = 1;
			int posIni = posExpressao + 1;
			do
			{
				proximoElemento();
				if(letra == '(')
				{
					numParentesis++;
				}
				else if(letra == ')')
				{
					numParentesis--;
				}
			}
			while(numParentesis > 0 && posExpressao < expressao.length());

			if(posExpressao >= expressao.length())
			{
				Erro(parentesisFaltando);
			}
			else
			{
				proximoElemento();
				ExpressaoExt exp = new ExpressaoExt(expressao.substring(posIni, posExpressao - 1), coeficientes);
				return exp.calcular();
			}

		}
		else if(tipoElemento == TIPO_CHAVE_ABRIR)
		{

			// coeficiente
			proximoElemento();
			if(EOF() || tipoElemento != TIPO_NUMERO)
			{
				Erro(indiceEsperado);
			}
			int indice = numeroInteiro();
			if(EOF() || tipoElemento != TIPO_CHAVE_FECHAR)
			{
				Erro(chaveEsperada);
			}
			if(indice >= coeficientes.length || indice < 0)
			{
				Erro(indiceInvalido);
			}
			proximoElemento();
			return coeficientes[indice];

		}
		else if(tipoElemento == TIPO_NUMERO)
		{

			// número
			return numeroReal();

		}
		else if(tipoElemento == TIPO_LETRA_AZ)
		{

			int indice = letra - 'A';
			if(indice >= coeficientes.length || indice < 0)
			{
				Erro(indiceInvalido);
			}
			proximoElemento();
			return coeficientes[indice];

		}

		Erro(valorEsperado);
		return null;
	}

	// avalia um número real no formato 9.99 (analisador sintático)
	private Racional numeroReal() throws Exception
	{

		String numero = numeroTexto();
		if(!EOF() && tipoElemento == TIPO_PONTO)
		{
			proximoElemento();
			if(!EOF() && tipoElemento == TIPO_NUMERO)
			{
				numero += "," + numeroTexto();
			}
			else
			{
				Erro(numeroEsperado);
			}
		}

		return new Racional(Integer.valueOf(numero));

	}

	// avalia um número inteiro (analisador sintático)
	private int numeroInteiro()
	{

		return Integer.parseInt(numeroTexto());

	}

	// avalia uma sequência de caracteres numéricos (analisador sintático)
	private String numeroTexto()
	{

		String num = new String(new char[] { letra });
		do
		{
			proximoElemento();
			if(!EOF() && tipoElemento == TIPO_NUMERO)
			{
				num += letra;
			}
		}
		while(!EOF() && tipoElemento == TIPO_NUMERO);
		return num;

	}

	// analisador léxico
	private void proximoElemento()
	{

		if(posExpressao < expressao.length() - 1)
		{
			letra = expressao.charAt(++posExpressao);
		}
		else
		{
			posExpressao++;
			letra = 0;
		}

		tipoElemento = 0;
		switch(letra) {
		case '{':
			tipoElemento = TIPO_CHAVE_ABRIR;
			break;

		case '}':
			tipoElemento = TIPO_CHAVE_FECHAR;
			break;

		case '(':
			tipoElemento = TIPO_PARENTESIS_ABRIR;
			break;

		case ')':
			tipoElemento = TIPO_PARENTESIS_FECHAR;
			break;

		case '.':
			tipoElemento = TIPO_PONTO;
			break;

		case '+':
		case '-':
		case '*':
		case '/':
		case '^':
		case '%':
			tipoElemento = TIPO_OPERADOR;
			break;

		default:
			if(letra >= 'A' && letra <= 'Z')
			{
				tipoElemento = TIPO_LETRA_AZ;
			}
			else if(letra >= '0' && letra <= '9')
			{
				tipoElemento = TIPO_NUMERO;
			}
			break;
		}

	}

	// verifica se chegou ao final da expressão
	private boolean EOF()
	{

		return posExpressao >= expressao.length();

	}

	// lança um erro (Exception com descrição) quando encontrar qualquer problema na
	// avaliação da expressão
	private void Erro(String mensagem) throws Exception
	{

		throw new Exception(MessageFormat.format(mensagem, new Object[] { posExpressao }));

	}

	public static void main(String[] args) throws Exception
	{
		Racional[] coeficientes = new Racional[3];
		coeficientes[0] = new Racional(2);
		coeficientes[1] = new Racional(2);
		coeficientes[2] = new Racional(2);

		ExpressaoExt expressao = new ExpressaoExt(" (A * B) - C ", coeficientes);
		System.out.println(expressao.calcular());

	}

}