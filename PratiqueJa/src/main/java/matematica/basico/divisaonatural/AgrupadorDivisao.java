package matematica.basico.divisaonatural;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;
import matematica.Nomes;
import matematica.basico.resolucaonatural.ResolucaoNatural;

// Base com helpers de exercício para divisão de naturais (Fase 2).
public abstract class AgrupadorDivisao extends GeradorExercicio
{
	// Divisão exata em coluna: dividendo = q*b; resultado q (tableau).
	protected void colunaDiv(int qMin, int qRange, int bMin, int bRange)
	{
		int q = qMin + rand.nextInt(qRange);
		int b = bMin + rand.nextInt(bRange);
		int dividendo = q * b;
		addParagrafo("Calcule a seguinte divisão:");
		addParagrafo("\\(" + ResolucaoNatural.divisao(dividendo, b, false) + "\\)");
		gerarAlternativasInteiras(q);
		setResolucao("\\(" + ResolucaoNatural.divisao(dividendo, b, true) + "\\)");
	}

	protected void comResto(int bMin, int bRange, int qMin, int qRange)
	{
		int b = bMin + rand.nextInt(bRange);
		int q = qMin + rand.nextInt(qRange);
		int r = 1 + rand.nextInt(b - 1);
		int a = b * q + r;
		addParagrafo("Calcule \\(" + a + " \\div " + b + "\\) e indique o quociente e o resto.");
		String correta = "Quociente \\(" + q + "\\), resto \\(" + r + "\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("Quociente \\(" + (q + 1) + "\\), resto \\(" + (r == 0 ? 1 : r - 1) + "\\)");
		distratores.add("Quociente \\(" + (q - 1) + "\\), resto \\(" + (r + b) + "\\)");
		distratores.add("Quociente \\(" + q + "\\), resto \\(0\\)");
		embaralharEAdicionarAlternativas(correta, distratores);
		String res = "Estimamos o quociente: \\(" + b + " \\times " + q + " = " + (b * q) + "\\). \\(\\\\\\)";
		res += "Calculamos o resto: \\(" + a + " - " + (b * q) + " = " + r + "\\) (e \\(" + r + " < " + b + "\\)). \\(\\\\\\)";
		res += "Prova real: \\(" + b + " \\times " + q + " + " + r + " = \\mathbf{" + a + "}\\)";
		setResolucao(res);
	}

	protected void divisaoPor10()
	{
		int[] pots = {10, 100, 1000};
		int p = pots[rand.nextInt(3)];
		int zeros = p == 10 ? 1 : (p == 100 ? 2 : 3);
		int q = 2 + rand.nextInt(98);
		int dividendo = q * p;
		addParagrafo("Calcule:");
		addParagrafo("\\(" + dividendo + " \\div " + p + " = \\,?\\)");
		gerarAlternativasInteiras(q);
		setResolucao("Dividir por \\(" + p + "\\) remove \\(" + zeros + "\\) zero(s) do final: \\(\\\\\\) \\(" + dividendo + " \\div " + p + " = \\mathbf{" + q + "}\\)");
	}

	protected void tabuada()
	{
		int b = 1 + rand.nextInt(10), q = 1 + rand.nextInt(10);
		int a = b * q;
		addParagrafo("Calcule:");
		addParagrafo("\\(" + a + " \\div " + b + " = \\,?\\)");
		gerarAlternativasInteiras(q);
		setResolucao("\\(" + a + " \\div " + b + " = \\mathbf{" + q + "}\\) (pois \\(" + b + " \\times " + q + " = " + a + "\\)).");
	}

	protected void provaReal(int bMin, int bRange, int qMin, int qRange)
	{
		int b = bMin + rand.nextInt(bRange), q = qMin + rand.nextInt(qRange);
		int a = b * q;
		addParagrafo(Nomes.feminino(rand) + " calculou que \\(" + a + " \\div " + b + " = " + q + "\\). Para verificar pela prova real, multiplicou \\(" + q + " \\times " + b + "\\). Qual deve ser o resultado?");
		gerarAlternativasInteiras(a);
		setResolucao(
			"A prova real da divisão exata usa a multiplicação: se \\(a \\div b = q\\), então \\(q \\times b = a\\). \\(\\\\\\)" +
			"\\(" + q + " \\times " + b + " = \\mathbf{" + a + "}\\)"
		);
	}

	protected void missingDividendo(int bMin, int bRange, int qMin, int qRange)
	{
		int b = bMin + rand.nextInt(bRange), q = qMin + rand.nextInt(qRange);
		int x = b * q;
		addParagrafo("Uma divisão exata tem divisor \\(" + b + "\\) e quociente \\(" + q + "\\). Qual é o dividendo (\\(\\square \\div " + b + " = " + q + "\\))?");
		gerarAlternativasInteiras(x);
		setResolucao(
			"O dividendo é o produto do divisor pelo quociente: \\(\\\\\\)" +
			"\\(\\square = " + b + " \\times " + q + " = \\mathbf{" + x + "}\\)"
		);
	}

	protected void quantasVezes(int bMin, int bRange, int qMin, int qRange)
	{
		int b = bMin + rand.nextInt(bRange), q = qMin + rand.nextInt(qRange);
		int a = b * q;
		addParagrafo("Quantas vezes o número \\(" + b + "\\) cabe exatamente em \\(" + a + "\\)?");
		gerarAlternativasInteiras(q);
		setResolucao("Procuramos quantas vezes \\(" + b + "\\) cabe em \\(" + a + "\\): \\(\\\\\\) \\(" + a + " \\div " + b + " = \\mathbf{" + q + "}\\).");
	}

	protected void exataVsInexata()
	{
		int b = 2 + rand.nextInt(8);
		int q = 2 + rand.nextInt(20);
		boolean exata = rand.nextBoolean();
		int a = exata ? b * q : b * q + (1 + rand.nextInt(b - 1));
		boolean ehExata = a % b == 0;
		addParagrafo("A divisão \\(" + a + " \\div " + b + "\\) é exata (resto zero)?");
		gerarAlternativasBoolean(ehExata);
		int quo = a / b, res = a % b;
		if(ehExata)
			setResolucao("\\(" + a + " \\div " + b + " = " + quo + "\\) com resto \\(0\\). \\(\\\\\\) Logo, é exata: \\(\\mathbf{Sim}\\).");
		else
			setResolucao("\\(" + a + " \\div " + b + " = " + quo + "\\) com resto \\(" + res + " \\neq 0\\). \\(\\\\\\) Logo, não é exata: \\(\\mathbf{Não}\\).");
	}

	protected void tresEtapas(int min, int range)
	{
		int q = 2 + rand.nextInt(range);
		int b = 2 + rand.nextInt(6), c = 2 + rand.nextInt(6);
		int a = q * b * c;
		int p1 = a / b;
		addParagrafo("Calcule \\(" + a + " \\div " + b + " \\div " + c + "\\).");
		gerarAlternativasInteiras(q);
		setResolucao(
			"Dividimos da esquerda para a direita: \\(\\\\\\)" +
			"\\(" + a + " \\div " + b + " = " + p1 + "\\); \\(" + p1 + " \\div " + c + " = \\mathbf{" + q + "}\\)"
		);
	}

	protected void problema(int bMin, int bRange, int qMin, int qRange)
	{
		int b = bMin + rand.nextInt(bRange), q = qMin + rand.nextInt(qRange);
		int a = b * q;
		int tipo = rand.nextInt(3);
		if(tipo == 0)
			addParagrafo("\\(" + a + "\\) lápis serão distribuídos igualmente entre \\(" + b + "\\) alunos. Quantos lápis cada aluno recebe?");
		else if(tipo == 1)
			addParagrafo("Uma fábrica embala \\(" + a + "\\) produtos em caixas de \\(" + b + "\\) unidades. Quantas caixas são necessárias?");
		else
			addParagrafo("Um total de \\(" + a + "\\) reais foi dividido igualmente entre \\(" + b + "\\) pessoas. Quanto cada uma recebeu?");
		gerarAlternativasInteiras(q);
		setResolucao("Dividimos o total pelo número de grupos: \\(\\\\\\) \\(" + a + " \\div " + b + " = \\mathbf{" + q + "}\\)");
	}
}
