package matematica.basico.numerosdecimais;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;

public abstract class AgrupadorDecimal extends GeradorExercicio
{
	/** Formata n décimos como "X{,}Y" para LaTeX */
	protected String fmtT(int decimos)
	{
		return (decimos / 10) + "{,}" + (decimos % 10);
	}

	/** Formata n centésimos como "X{,}YZ" para LaTeX */
	protected String fmtH(int centesimos)
	{
		return (centesimos / 100) + "{,}" + String.format("%02d", centesimos % 100);
	}

	/** Formata n milésimos como "X{,}YZW" para LaTeX */
	protected String fmtM(int milesimos)
	{
		return (milesimos / 1000) + "{,}" + String.format("%03d", milesimos % 1000);
	}

	/**
	 * Resolução em conta vertical: op é "+" ou "-".
	 * Gera \(\begin{array}{r} a \\ op b \\ \hline result \end{array}\)
	 */
	protected String vertical(String op, String a, String b, String result)
	{
		return "\\(\\begin{array}{r}" +
			a + " \\\\" +
			op + "\\;" + b + " \\\\" +
			"\\hline " + result +
			"\\end{array}\\)";
	}

	/** Conta vertical com 3 parcelas (todos com sinal op nas 2ª e 3ª linhas) */
	protected String vertical3(String op, String a, String b, String c, String result)
	{
		return "\\(\\begin{array}{r}" +
			a + " \\\\" +
			op + "\\;" + b + " \\\\" +
			op + "\\;" + c + " \\\\" +
			"\\hline " + result +
			"\\end{array}\\)";
	}

	/** 4 alternativas embaralhadas para resultado em décimos */
	protected void gerarAltT(int correto)
	{
		String correctStr = "\\(" + fmtT(correto) + "\\)";
		List<String> distratores = new ArrayList<>();
		List<Integer> usados = new ArrayList<>();
		usados.add(correto);

		while(distratores.size() < 3)
		{
			int delta = 1 + rand.nextInt(4);
			int cand = rand.nextBoolean() ? correto + delta : correto - delta;
			if(cand > 0 && !usados.contains(cand))
			{
				usados.add(cand);
				distratores.add("\\(" + fmtT(cand) + "\\)");
			}
		}
		embaralharEAdicionarAlternativas(correctStr, distratores);
	}

	/** 4 alternativas embaralhadas para resultado em centésimos */
	protected void gerarAltH(int correto)
	{
		String correctStr = "\\(" + fmtH(correto) + "\\)";
		List<String> distratores = new ArrayList<>();
		List<Integer> usados = new ArrayList<>();
		usados.add(correto);

		while(distratores.size() < 3)
		{
			int delta = 1 + rand.nextInt(6);
			int cand = rand.nextBoolean() ? correto + delta : correto - delta;
			if(cand > 0 && !usados.contains(cand))
			{
				usados.add(cand);
				distratores.add("\\(" + fmtH(cand) + "\\)");
			}
		}
		embaralharEAdicionarAlternativas(correctStr, distratores);
	}

	// ===== Helpers de exercício (Fase 2 — adequação) =====

	protected void somaDecimos(int min, int range)
	{
		int aT = min + rand.nextInt(range);
		int bT = min + rand.nextInt(range);
		int s = aT + bT;
		addParagrafo("Calcule a soma dos números decimais:");
		addParagrafo("\\(" + fmtT(aT) + " + " + fmtT(bT) + " = \\,?\\)");
		gerarAltT(s);
		setResolucao(vertical("+", fmtT(aT), fmtT(bT), fmtT(s)));
	}

	protected void subtracaoDecimos(int min, int range)
	{
		int aT, bT;
		do { aT = min + rand.nextInt(range); bT = min + rand.nextInt(range); }
		while(aT <= bT);
		int d = aT - bT;
		addParagrafo("Calcule a subtração dos números decimais:");
		addParagrafo("\\(" + fmtT(aT) + " - " + fmtT(bT) + " = \\,?\\)");
		gerarAltT(d);
		setResolucao(vertical("-", fmtT(aT), fmtT(bT), fmtT(d)));
	}

	protected void somaCentesimos(int min, int range)
	{
		int aH = min + rand.nextInt(range);
		int bH = min + rand.nextInt(range);
		int s = aH + bH;
		addParagrafo("Calcule a soma dos números decimais:");
		addParagrafo("\\(" + fmtH(aH) + " + " + fmtH(bH) + " = \\,?\\)");
		gerarAltH(s);
		setResolucao(vertical("+", fmtH(aH), fmtH(bH), fmtH(s)));
	}

	protected void subtracaoCentesimos(int min, int range)
	{
		int aH, bH;
		do { aH = min + rand.nextInt(range); bH = min + rand.nextInt(range); }
		while(aH <= bH);
		int d = aH - bH;
		addParagrafo("Calcule a subtração dos números decimais:");
		addParagrafo("\\(" + fmtH(aH) + " - " + fmtH(bH) + " = \\,?\\)");
		gerarAltH(d);
		setResolucao(vertical("-", fmtH(aH), fmtH(bH), fmtH(d)));
	}

	// (a décimos) × (b décimos) → centésimos
	protected void produtoDecimalDecimal()
	{
		int aT = 2 + rand.nextInt(28); // 0,2 .. 3,0
		int bT = 2 + rand.nextInt(8);  // 0,2 .. 0,9
		int prodH = aT * bT;
		addParagrafo("Calcule o produto dos números decimais:");
		addParagrafo("\\(" + fmtT(aT) + " \\times " + fmtT(bT) + " = \\,?\\)");
		gerarAltH(prodH);
		setResolucao(
			"Multiplicamos sem a vírgula e contamos as casas decimais (1 + 1 = 2): \\(\\\\\\)" +
			"\\(" + aT + " \\times " + bT + " = " + prodH + " \\Rightarrow " + fmtH(prodH) + "\\)"
		);
	}

	// (a centésimos) × inteiro pequeno → centésimos
	protected void multiplicaDecimalInteiro()
	{
		int aH = 100 + rand.nextInt(800); // 1,00 .. 8,99
		int k = 2 + rand.nextInt(7);      // 2..8
		int prodH = aH * k;
		addParagrafo("Calcule:");
		addParagrafo("\\(" + fmtH(aH) + " \\times " + k + " = \\,?\\)");
		gerarAltH(prodH);
		setResolucao(
			"Multiplicamos sem a vírgula e recolocamos as 2 casas decimais: \\(\\\\\\)" +
			"\\(" + aH + " \\times " + k + " = " + prodH + " \\Rightarrow " + fmtH(prodH) + "\\)"
		);
	}

	// a,bc × 10 (→ décimos) ou × 100 (→ inteiro)
	protected void multiplicaPotencia10()
	{
		int aH;
		do { aH = 100 + rand.nextInt(900); } while(aH % 100 == 0);
		boolean por10 = rand.nextBoolean();
		addParagrafo("Calcule:");
		addParagrafo("\\(" + fmtH(aH) + " \\times " + (por10 ? 10 : 100) + " = \\,?\\)");
		if(por10)
		{
			gerarAltT(aH);
			setResolucao("A vírgula anda 1 casa à direita: \\(\\\\\\) \\(" + fmtH(aH) + " \\times 10 = " + fmtT(aH) + "\\)");
		}
		else
		{
			gerarAlternativasInteiras(aH);
			setResolucao("A vírgula anda 2 casas à direita: \\(\\\\\\) \\(" + fmtH(aH) + " \\times 100 = " + aH + "\\)");
		}
	}

	// (a,b ÷ 0,c) → inteiro (multiplicar ambos por 10)
	protected void divisaoDecimalInteira()
	{
		int c = 2 + rand.nextInt(8);  // divisor 0,2..0,9 (décimos)
		int q = 2 + rand.nextInt(14); // 2..15
		int dividendoT = q * c;
		addParagrafo("Calcule a divisão dos números decimais:");
		addParagrafo("\\(" + fmtT(dividendoT) + " \\div " + fmtT(c) + " = \\,?\\)");
		gerarAlternativasInteiras(q);
		setResolucao(
			"Multiplicamos dividendo e divisor por 10 para eliminar a vírgula do divisor: \\(\\\\\\)" +
			"\\(" + dividendoT + " \\div " + c + " = \\mathbf{" + q + "}\\)"
		);
	}

	// (a centésimos ÷ inteiro) → centésimos exato
	protected void divisaoPorInteiro()
	{
		int q = 25 + rand.nextInt(275); // quociente 0,25..2,99 (centésimos)
		int k = 2 + rand.nextInt(7);    // 2..8
		int dividendoH = q * k;
		addParagrafo("Calcule a divisão do número decimal pelo inteiro:");
		addParagrafo("\\(" + fmtH(dividendoH) + " \\div " + k + " = \\,?\\)");
		gerarAltH(q);
		setResolucao(
			"Dividimos normalmente, mantendo a vírgula no quociente: \\(\\\\\\)" +
			"\\(" + fmtH(dividendoH) + " \\div " + k + " = " + fmtH(q) + "\\)"
		);
	}

	// Qual é o maior decimal? (4 valores em centésimos)
	protected void comparacaoMaior()
	{
		java.util.Set<Integer> vals = new java.util.HashSet<>();
		while(vals.size() < 4) vals.add(10 + rand.nextInt(290)); // 0,10 .. 2,99
		List<Integer> lista = new ArrayList<>(vals);
		java.util.Collections.shuffle(lista, rand);
		int maior = java.util.Collections.max(vals);
		List<String> distratores = new ArrayList<>();
		for(int v : lista) if(v != maior) distratores.add("\\(" + fmtH(v) + "\\)");
		StringBuilder numeros = new StringBuilder();
		for(int i = 0; i < lista.size(); i++)
		{
			if(i > 0) numeros.append(i == lista.size() - 1 ? " e " : ", ");
			numeros.append("\\(").append(fmtH(lista.get(i))).append("\\)");
		}
		addParagrafo("Qual é o maior número decimal entre " + numeros + "?");
		embaralharEAdicionarAlternativas("\\(" + fmtH(maior) + "\\)", distratores);
		setResolucao("Comparando casa a casa (da esquerda para a direita), o maior é \\(\\mathbf{" + fmtH(maior) + "}\\).");
	}

	// Fração de denominador 10 ou 100 → decimal
	protected void fracaoParaDecimal()
	{
		boolean por10 = rand.nextBoolean();
		if(por10)
		{
			int n = 1 + rand.nextInt(9);
			addParagrafo("Qual é o número decimal equivalente à fração abaixo?");
			addParagrafo("\\(\\dfrac{" + n + "}{10}\\)");
			gerarAltT(n);
			setResolucao("\\(\\dfrac{" + n + "}{10} = " + n + " \\div 10 = " + fmtT(n) + "\\)");
		}
		else
		{
			int n = 1 + rand.nextInt(99);
			addParagrafo("Qual é o número decimal equivalente à fração abaixo?");
			addParagrafo("\\(\\dfrac{" + n + "}{100}\\)");
			gerarAltH(n);
			setResolucao("\\(\\dfrac{" + n + "}{100} = " + n + " \\div 100 = " + fmtH(n) + "\\)");
		}
	}

	// a,b + c,d × e (ordem das operações) — décimos
	protected void expressaoDecimal()
	{
		int aT = 11 + rand.nextInt(39); // 1,1 .. 4,9
		int cT = 11 + rand.nextInt(20); // 1,1 .. 3,0
		int e = 2 + rand.nextInt(4);    // 2..5
		int prodT = cT * e;
		int resT = aT + prodT;
		addParagrafo("Calcule, respeitando a ordem das operações:");
		addParagrafo("\\(" + fmtT(aT) + " + " + fmtT(cT) + " \\times " + e + " = \\,?\\)");
		gerarAltT(resT);
		setResolucao(
			"Primeiro a multiplicação: \\(" + fmtT(cT) + " \\times " + e + " = " + fmtT(prodT) + "\\). \\(\\\\\\)" +
			"Depois a soma: \\(" + fmtT(aT) + " + " + fmtT(prodT) + " = \\mathbf{" + fmtT(resT) + "}\\)"
		);
	}
}
