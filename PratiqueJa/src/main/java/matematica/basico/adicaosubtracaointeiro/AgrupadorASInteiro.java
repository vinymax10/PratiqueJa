package matematica.basico.adicaosubtracaointeiro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import matematica.Auxiliar;
import matematica.GeradorExercicio;

// Base com helpers de exercício para adição/subtração de inteiros (Fase 2).
public abstract class AgrupadorASInteiro extends GeradorExercicio
{
	protected int inteiroNaoNulo(int maxAbs)
	{
		int v = 1 + rand.nextInt(maxAbs);
		return rand.nextBoolean() ? -v : v;
	}

	private String resolucaoAgrupada(int[] termos, int correto)
	{
		int somaPos = 0, somaNeg = 0;
		for(int t : termos) { if(t > 0) somaPos += t; else somaNeg += t; }
		String res = "Agrupamos os termos positivos e os negativos e somamos. \\(\\\\\\)";
		res += "Positivos: \\(" + somaPos + "\\); negativos: \\(" + somaNeg + "\\). \\(\\\\\\)";
		res += "\\(" + somaPos + " + (" + somaNeg + ") = \\mathbf{" + correto + "}\\)";
		return res;
	}

	private String expressao(int[] termos)
	{
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < termos.length; i++)
			sb.append(Auxiliar.getNumber(termos[i], "", i == 0));
		return sb.toString();
	}

	protected void somaDoisTermos(int maxAbs)
	{
		int a = inteiroNaoNulo(maxAbs), b = inteiroNaoNulo(maxAbs);
		addParagrafo("Calcule o valor da seguinte expressão:");
		addParagrafo("\\(" + expressao(new int[]{a, b}) + " = \\,?\\)");
		gerarAlternativasInteirasComNegativos(a + b);
		setResolucao(ResolucaoASInteiro.soma(a, b));
	}

	protected void tresTermos(int maxAbs)
	{
		int a = inteiroNaoNulo(maxAbs), b = inteiroNaoNulo(maxAbs), c = inteiroNaoNulo(maxAbs);
		addParagrafo("Calcule o valor da expressão:");
		addParagrafo("\\(" + expressao(new int[]{a, b, c}) + " = \\,?\\)");
		gerarAlternativasInteirasComNegativos(a + b + c);
		setResolucao(resolucaoAgrupada(new int[]{a, b, c}, a + b + c));
	}

	protected void quatroTermos(int maxAbs)
	{
		int a = inteiroNaoNulo(maxAbs), b = inteiroNaoNulo(maxAbs), c = inteiroNaoNulo(maxAbs), d = inteiroNaoNulo(maxAbs);
		addParagrafo("Calcule o valor da expressão:");
		addParagrafo("\\(" + expressao(new int[]{a, b, c, d}) + " = \\,?\\)");
		gerarAlternativasInteirasComNegativos(a + b + c + d);
		setResolucao(resolucaoAgrupada(new int[]{a, b, c, d}, a + b + c + d));
	}

	protected void cincoTermos(int maxAbs)
	{
		int[] t = {inteiroNaoNulo(maxAbs), inteiroNaoNulo(maxAbs), inteiroNaoNulo(maxAbs), inteiroNaoNulo(maxAbs), inteiroNaoNulo(maxAbs)};
		int soma = 0; for(int x : t) soma += x;
		addParagrafo("Calcule o valor da expressão:");
		addParagrafo("\\(" + expressao(t) + " = \\,?\\)");
		gerarAlternativasInteirasComNegativos(soma);
		setResolucao(resolucaoAgrupada(t, soma));
	}

	// a ± (±b) — sinal antes do parêntese
	protected void parenteses(int maxAbs)
	{
		int a = inteiroNaoNulo(maxAbs), b = inteiroNaoNulo(maxAbs);
		boolean menos = rand.nextBoolean();
		int t2 = menos ? -b : b;
		int correto = a + t2;

		String aStr = a > 0 ? "+" + a : "" + a;
		String bPar = "(" + (b > 0 ? "+" + b : "" + b) + ")";
		addParagrafo("Calcule:");
		addParagrafo("\\(" + aStr + (menos ? " - " : " + ") + bPar + " = \\,?\\)");
		gerarAlternativasInteirasComNegativos(correto);

		String res = "Eliminamos o parêntese aplicando a regra de sinais: \\(\\\\\\)";
		res += "\\(" + (menos ? "-" : "+") + bPar + " = " + Auxiliar.getNumber(t2, "", false) + "\\). \\(\\\\\\)";
		res += ResolucaoASInteiro.soma(a, t2);
		setResolucao(res);
	}

	protected void valorAbsoluto()
	{
		int a = 1 + rand.nextInt(29);
		int x = rand.nextBoolean() ? -a : a;
		addParagrafo("Qual é o valor de \\(|" + x + "|\\)?");
		gerarAlternativasInteiras(a);
		setResolucao("O módulo é a distância ao zero na reta numérica, sempre positivo ou nulo. \\(\\\\\\) \\(|" + x + "| = \\mathbf{" + a + "}\\)");
	}

	protected void oposto()
	{
		int a = 1 + rand.nextInt(29);
		int x = rand.nextBoolean() ? -a : a;
		addParagrafo("Qual é o oposto do número \\(" + x + "\\)?");
		gerarAlternativasInteirasComNegativos(-x);
		setResolucao("O oposto de um número é o que somado a ele dá zero (troca o sinal). \\(\\\\\\) Oposto de \\(" + x + "\\) é \\(\\mathbf{" + (-x) + "}\\).");
	}

	protected void comparar(int maxAbs, int qtd)
	{
		Set<Integer> vals = new HashSet<>();
		while(vals.size() < qtd) vals.add(inteiroNaoNulo(maxAbs));

		List<Integer> lista = new ArrayList<>(vals);
		Collections.shuffle(lista, rand);

		int maior = Integer.MIN_VALUE;
		for(int v : lista) if(v > maior) maior = v;
		List<String> distratores = new ArrayList<>();
		for(int v : lista) if(v != maior) distratores.add("\\(" + v + "\\)");

		StringBuilder numeros = new StringBuilder();
		for(int i = 0; i < lista.size(); i++)
		{
			if(i > 0) numeros.append(i == lista.size() - 1 ? " e " : ", ");
			numeros.append("\\(").append(lista.get(i)).append("\\)");
		}

		addParagrafo("Qual é o maior número inteiro entre " + numeros + "?");
		embaralharEAdicionarAlternativas("\\(" + maior + "\\)", distratores);
		setResolucao("Na reta numérica, o maior número é o que fica mais à direita (um negativo é maior quanto mais perto de zero). \\(\\\\\\) O maior é \\(\\mathbf{" + maior + "}\\).");
	}

	protected void missingTermo(int maxAbs)
	{
		int a = inteiroNaoNulo(maxAbs), x = inteiroNaoNulo(maxAbs);
		int c = a + x;
		String aStr = a > 0 ? "+" + a : "" + a;
		addParagrafo("Qual valor de \\(\\square\\) torna a igualdade verdadeira?");
		addParagrafo("\\(" + aStr + " + \\square = " + c + "\\)");
		gerarAlternativasInteirasComNegativos(x);
		setResolucao("Isolando \\(\\square\\): \\(\\square = " + c + " - (" + a + ") = \\mathbf{" + x + "}\\).");
	}

	protected void problema()
	{
		int tipo = rand.nextInt(3);
		if(tipo == 0)
		{
			int t0 = inteiroNaoNulo(12);
			int delta = 1 + rand.nextInt(18);
			int fim = t0 + delta;
			addParagrafo("O termômetro marca \\(" + t0 + "\\,°C\\). A temperatura sobe \\(" + delta + "\\,°C\\). Qual é a temperatura final?");
			gerarAlternativasInteirasComNegativos(fim);
			setResolucao(ResolucaoASInteiro.soma(t0, delta));
		}
		else if(tipo == 1)
		{
			int sub = 1 + rand.nextInt(5);
			int sobe = 1 + rand.nextInt(12);
			int fim = -sub + sobe;
			addParagrafo("Um elevador está no \\(" + sub + "°\\) subsolo (andar \\(" + (-sub) + "\\)) e sobe \\(" + sobe + "\\) andares. Em que andar ele para?");
			gerarAlternativasInteirasComNegativos(fim);
			setResolucao(ResolucaoASInteiro.soma(-sub, sobe));
		}
		else
		{
			int divida = 50 + rand.nextInt(200);
			int deposito = 50 + rand.nextInt(300);
			int saldo = -divida + deposito;
			addParagrafo("Uma conta tem uma dívida de R$\\," + divida + " (saldo \\(" + (-divida) + "\\)). É feito um depósito de R$\\," + deposito + ". Qual é o saldo final?");
			gerarAlternativasInteirasComNegativos(saldo);
			setResolucao(ResolucaoASInteiro.soma(-divida, deposito));
		}
	}
}
