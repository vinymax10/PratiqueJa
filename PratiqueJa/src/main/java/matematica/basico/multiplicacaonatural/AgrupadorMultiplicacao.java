package matematica.basico.multiplicacaonatural;

import matematica.GeradorExercicio;
import matematica.resolucaonatural.ResolucaoNatural;

// Base com helpers de exercício para multiplicação de naturais (Fase 2).
public abstract class AgrupadorMultiplicacao extends GeradorExercicio
{
	protected void calculo(int aMin, int aRange, int bMin, int bRange)
	{
		int a = aMin + rand.nextInt(aRange);
		int b = bMin + rand.nextInt(bRange);
		if(a < b) { int t = a; a = b; b = t; }
		addParagrafo("Calcule a seguinte multiplicação:");
		addParagrafo("\\(" + ResolucaoNatural.multiplicacao(a, b, false) + "\\)");
		gerarAlternativasInteiras(a * b);
		setResolucao("\\(" + ResolucaoNatural.multiplicacao(a, b, true) + "\\)");
	}

	protected void multiplicaPor10()
	{
		int[] fatores = {10, 100, 1000};
		int f = fatores[rand.nextInt(3)];
		int a = 2 + rand.nextInt(98); // 2..99
		int zeros = f == 10 ? 1 : (f == 100 ? 2 : 3);
		addParagrafo("Calcule:");
		addParagrafo("\\(" + a + " \\times " + f + " = \\,?\\)");
		gerarAlternativasInteiras(a * f);
		setResolucao("Multiplicar por \\(" + f + "\\) acrescenta \\(" + zeros + "\\) zero(s) ao final: \\(\\\\\\) \\(" + a + " \\times " + f + " = \\mathbf{" + (a * f) + "}\\)");
	}

	protected void tresFatores(int min, int range)
	{
		int a = min + rand.nextInt(range), b = min + rand.nextInt(range), c = min + rand.nextInt(range);
		int ab = a * b;
		addParagrafo("Calcule \\(" + a + " \\times " + b + " \\times " + c + "\\).");
		gerarAlternativasInteiras(ab * c);
		setResolucao(
			"Multiplicamos dois fatores de cada vez: \\(\\\\\\)" +
			"\\(" + a + " \\times " + b + " = " + ab + "\\); \\(" + ab + " \\times " + c + " = \\mathbf{" + (ab * c) + "}\\)"
		);
	}

	protected void fatorFaltante(int min, int range)
	{
		int a = min + rand.nextInt(range), x = min + rand.nextInt(range);
		int c = a * x;
		addParagrafo("Qual é o valor de \\(\\square\\) em \\(" + a + " \\times \\square = " + c + "\\)?");
		gerarAlternativasInteiras(x);
		setResolucao(
			"Como a divisão é a operação inversa: \\(\\\\\\)" +
			"\\(\\square = " + c + " \\div " + a + " = \\mathbf{" + x + "}\\)"
		);
	}

	protected void distributiva(int min, int range)
	{
		int a = min + rand.nextInt(range), b = 2 + rand.nextInt(9), c = 2 + rand.nextInt(9);
		addParagrafo("Use a propriedade distributiva e calcule \\(" + a + " \\times (" + b + " + " + c + ")\\).");
		gerarAlternativasInteiras(a * (b + c));
		setResolucao(
			"Distribuímos o fator sobre a soma: \\(\\\\\\)" +
			"\\(" + a + " \\times " + b + " + " + a + " \\times " + c + " = " + (a * b) + " + " + (a * c) + " = \\mathbf{" + (a * (b + c)) + "}\\)"
		);
	}

	protected void expressao()
	{
		int a = 2 + rand.nextInt(11), b = 2 + rand.nextInt(11), c = 2 + rand.nextInt(11), d = 2 + rand.nextInt(11);
		addParagrafo("Calcule, respeitando a ordem das operações:");
		addParagrafo("\\(" + a + " \\times " + b + " + " + c + " \\times " + d + " = \\,?\\)");
		gerarAlternativasInteiras(a * b + c * d);
		setResolucao(
			"Primeiro as multiplicações: \\(" + a + " \\times " + b + " = " + (a * b) + "\\) e \\(" + c + " \\times " + d + " = " + (c * d) + "\\). \\(\\\\\\)" +
			"Depois a soma: \\(" + (a * b) + " + " + (c * d) + " = \\mathbf{" + (a * b + c * d) + "}\\)"
		);
	}

	protected void tabuada()
	{
		int a = 1 + rand.nextInt(10), b = 1 + rand.nextInt(10);
		addParagrafo("Calcule:");
		addParagrafo("\\(" + a + " \\times " + b + " = \\,?\\)");
		gerarAlternativasInteiras(a * b);
		setResolucao("\\(" + a + " \\times " + b + " = \\mathbf{" + (a * b) + "}\\)");
	}

	protected void problema(int min, int range)
	{
		int a = min + rand.nextInt(range), b = 2 + rand.nextInt(9);
		int r = a * b;
		int tipo = rand.nextInt(3);
		if(tipo == 0)
			addParagrafo("Uma caixa contém \\(" + a + "\\) unidades. Quantas unidades há em \\(" + b + "\\) caixas iguais?");
		else if(tipo == 1)
			addParagrafo("Um ônibus transporta \\(" + a + "\\) passageiros por viagem. Quantos passageiros em \\(" + b + "\\) viagens lotadas?");
		else
			addParagrafo("Cada pacote tem \\(" + a + "\\) figurinhas. Quantas figurinhas há em \\(" + b + "\\) pacotes?");
		gerarAlternativasInteiras(r);
		setResolucao("Multiplicamos a quantidade por unidade pelo número de grupos: \\(\\\\\\) \\(" + a + " \\times " + b + " = \\mathbf{" + r + "}\\)");
	}
}
