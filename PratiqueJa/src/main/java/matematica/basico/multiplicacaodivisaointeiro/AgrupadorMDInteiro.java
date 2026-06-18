package matematica.basico.multiplicacaodivisaointeiro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import matematica.GeradorExercicio;

// Base com helpers de exercício para multiplicação/divisão de inteiros (Fase 2).
public abstract class AgrupadorMDInteiro extends GeradorExercicio
{
	protected int inteiro(int maxAbs)
	{
		int v = 2 + rand.nextInt(maxAbs - 1);
		return rand.nextBoolean() ? -v : v;
	}

	protected String fmt(int n)
	{
		return n < 0 ? "(" + n + ")" : "" + n;
	}

	protected void multiplicacao(int maxAbs)
	{
		int a = inteiro(maxAbs), b = inteiro(maxAbs);
		addParagrafo("Calcule \\(" + fmt(a) + " \\times " + fmt(b) + "\\).");
		gerarAlternativasInteirasComNegativos(a * b);
		setResolucao(ResolucaoMDInteiro.multiplicacao(a, b));
	}

	protected void divisao(int maxAbs)
	{
		int a = inteiro(maxAbs), b = inteiro(maxAbs);
		int prod = a * b;
		addParagrafo("Calcule \\(" + fmt(prod) + " \\div " + fmt(b) + "\\).");
		gerarAlternativasInteirasComNegativos(a);
		setResolucao(ResolucaoMDInteiro.divisao(prod, b));
	}

	protected void multiFatores(int qtd, int maxAbs)
	{
		int[] f = new int[qtd];
		int produto = 1, negativos = 0;
		StringBuilder exp = new StringBuilder();
		StringBuilder abs = new StringBuilder();
		for(int i = 0; i < qtd; i++)
		{
			f[i] = inteiro(maxAbs);
			produto *= f[i];
			if(f[i] < 0) negativos++;
			if(i > 0) { exp.append(" \\times "); abs.append(" \\times "); }
			exp.append(fmt(f[i]));
			abs.append(Math.abs(f[i]));
		}
		boolean positivo = negativos % 2 == 0;
		addParagrafo("Calcule \\(" + exp + "\\).");
		gerarAlternativasInteirasComNegativos(produto);
		String res = "Multiplicamos os valores absolutos: \\(" + abs + " = " + Math.abs(produto) + "\\). \\(\\\\\\)";
		res += negativos + " fator" + (negativos == 1 ? "" : "es") + " negativo" + (negativos == 1 ? "" : "s") + " ";
		res += positivo ? "(quantidade par) → resultado positivo. \\(\\\\\\)" : "(quantidade ímpar) → resultado negativo. \\(\\\\\\)";
		res += "\\(" + exp + " = \\mathbf{" + produto + "}\\)";
		setResolucao(res);
	}

	protected void potenciaNegativo()
	{
		int a = 2 + rand.nextInt(4); // 2..5
		int n = 2 + rand.nextInt(3); // 2..4
		int abs = 1; for(int i = 0; i < n; i++) abs *= a;
		boolean par = n % 2 == 0;
		int resultado = par ? abs : -abs;
		addParagrafo("Calcule \\((-" + a + ")^{" + n + "}\\).");
		gerarAlternativasInteirasComNegativos(resultado);
		StringBuilder expand = new StringBuilder();
		for(int i = 0; i < n; i++) { if(i > 0) expand.append(" \\times "); expand.append("(-" + a + ")"); }
		String res = "Expandimos a potência em \\(" + n + "\\) fatores: \\(\\\\\\)";
		res += "\\((-" + a + ")^{" + n + "} = " + expand + "\\). \\(\\\\\\)";
		res += "\\(" + n + "\\) fatores negativos " + (par ? "(par) → positivo" : "(ímpar) → negativo") + ". \\(\\\\\\)";
		res += "\\(" + a + "^{" + n + "} = " + abs + " \\to \\mathbf{" + resultado + "}\\)";
		setResolucao(res);
	}

	protected void identificarSinal(int maxAbs)
	{
		int a = inteiro(maxAbs), b = inteiro(maxAbs);
		boolean mult = rand.nextBoolean();
		boolean positivo = (a < 0) == (b < 0);
		String correta = positivo ? "Positivo" : "Negativo";
		addParagrafo("Qual é o sinal do resultado de \\(" + fmt(a) + (mult ? " \\times " : " \\div ") + fmt(b) + "\\)?");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(positivo ? "Negativo" : "Positivo"));
		setResolucao("Sinais " + (positivo ? "iguais → resultado \\(\\mathbf{positivo}\\)" : "diferentes → resultado \\(\\mathbf{negativo}\\)") + ".");
	}

	protected void distributiva(int maxAbs)
	{
		int a = inteiro(maxAbs);
		int b = 2 + rand.nextInt(9), c = 2 + rand.nextInt(9);
		int resultado = a * (b + c);
		addParagrafo("Use a propriedade distributiva e calcule \\(" + fmt(a) + " \\times (" + b + " + " + c + ")\\).");
		gerarAlternativasInteirasComNegativos(resultado);
		setResolucao(
			"Distribuímos o fator sobre a soma: \\(\\\\\\)" +
			"\\(" + fmt(a) + " \\times " + b + " + " + fmt(a) + " \\times " + c + " = " + (a * b) + " + (" + (a * c) + ") = \\mathbf{" + resultado + "}\\)"
		);
	}

	protected void missingFator(int maxAbs)
	{
		int a = inteiro(maxAbs), x = inteiro(maxAbs);
		int c = a * x;
		addParagrafo("Qual valor de \\(\\square\\) torna a igualdade verdadeira?");
		addParagrafo("\\(" + fmt(a) + " \\times \\square = " + c + "\\)");
		gerarAlternativasInteirasComNegativos(x);
		setResolucao("Como a divisão é a operação inversa: \\(\\square = " + fmt(c) + " \\div " + fmt(a) + " = \\mathbf{" + x + "}\\).");
	}

	protected void provaReal(int maxAbs)
	{
		int a = inteiro(maxAbs), b = inteiro(maxAbs);
		int c = a * b;
		addParagrafo("Sabendo que \\(" + fmt(a) + " \\times " + fmt(b) + " = " + c + "\\), calcule \\(" + fmt(c) + " \\div " + fmt(a) + "\\).");
		gerarAlternativasInteirasComNegativos(b);
		setResolucao("A divisão é a operação inversa da multiplicação: \\(\\\\\\) \\(" + fmt(c) + " \\div " + fmt(a) + " = \\mathbf{" + b + "}\\).");
	}

	protected void problema()
	{
		int tipo = rand.nextInt(3);
		if(tipo == 0)
		{
			int queda = 2 + rand.nextInt(6);
			int horas = 2 + rand.nextInt(8);
			int variacao = -queda * horas;
			addParagrafo("A temperatura cai \\(" + queda + "\\,°C\\) por hora. Qual é a variação total da temperatura após \\(" + horas + "\\) horas?");
			gerarAlternativasInteirasComNegativos(variacao);
			setResolucao(ResolucaoMDInteiro.multiplicacao(-queda, horas));
		}
		else if(tipo == 1)
		{
			int qtd = 3 + rand.nextInt(6);
			int valor = 5 + rand.nextInt(45);
			int saldo = qtd * (-valor);
			addParagrafo("Uma pessoa tem \\(" + qtd + "\\) dívidas de R$\\," + valor + " cada. Qual é o saldo total (em reais)?");
			gerarAlternativasInteirasComNegativos(saldo);
			setResolucao(ResolucaoMDInteiro.multiplicacao(qtd, -valor));
		}
		else
		{
			int pessoas = 2 + rand.nextInt(6);
			int porPessoa = 5 + rand.nextInt(20);
			int total = -porPessoa * pessoas;
			addParagrafo("Uma dívida de R$\\," + (porPessoa * pessoas) + " (saldo \\(" + total + "\\)) é dividida igualmente entre \\(" + pessoas + "\\) pessoas. Qual é o saldo de cada uma (em reais)?");
			gerarAlternativasInteirasComNegativos(-porPessoa);
			setResolucao(ResolucaoMDInteiro.divisao(total, pessoas));
		}
	}
}
