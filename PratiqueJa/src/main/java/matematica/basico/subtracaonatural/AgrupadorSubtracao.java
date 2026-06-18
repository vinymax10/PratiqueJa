package matematica.basico.subtracaonatural;

import matematica.GeradorExercicio;
import matematica.Nomes;
import matematica.basico.resolucaonatural.ResolucaoNatural;

// Base com helpers de exercício para subtração de naturais (Fase 2).
public abstract class AgrupadorSubtracao extends GeradorExercicio
{
	// Subtração em coluna (a >= b), com tableau de resolução.
	protected void coluna(int min, int range)
	{
		int a = min + rand.nextInt(range);
		int b = 1 + rand.nextInt(range);
		if(a < b) { int t = a; a = b; b = t; }
		if(a == b) a += 1 + rand.nextInt(9);

		addParagrafo("Calcule a seguinte subtração:");
		addParagrafo("\\(" + ResolucaoNatural.subtracao(a, b, false) + "\\)");
		gerarAlternativasInteiras(a - b);
		setResolucao("\\(" + ResolucaoNatural.subtracao(a, b, true) + "\\)");
	}

	// Empréstimo com zeros: minuendo terminado em 00.
	protected void emprestimoZeros()
	{
		int a = (2 + rand.nextInt(8)) * 100; // 200..900
		int b = 11 + rand.nextInt(180);      // 11..190
		addParagrafo("Calcule a seguinte subtração (atenção ao empréstimo):");
		addParagrafo("\\(" + ResolucaoNatural.subtracao(a, b, false) + "\\)");
		gerarAlternativasInteiras(a - b);
		setResolucao("\\(" + ResolucaoNatural.subtracao(a, b, true) + "\\)");
	}

	protected void diferenca(int min, int range)
	{
		int b = min + rand.nextInt(range);
		int a = b + 5 + rand.nextInt(range);
		int dif = a - b;
		String nomeM = Nomes.masculino(rand), nomeF = Nomes.feminino(rand);
		int tipo = rand.nextInt(4);
		switch(tipo)
		{
			case 0: addParagrafo(nomeM + " tem \\(" + a + "\\) figurinhas e " + nomeF + " tem \\(" + b + "\\). Quantas figurinhas a mais " + nomeM + " tem?"); break;
			case 1: addParagrafo("Uma loja tinha \\(" + a + "\\) produtos e vendeu até restarem \\(" + b + "\\). Quantos produtos foram vendidos?"); break;
			case 2: addParagrafo(nomeF + " tirou \\(" + a + "\\) pontos e " + nomeM + " \\(" + b + "\\). Qual foi a diferença de pontuação?"); break;
			default: addParagrafo("Um reservatório tinha \\(" + a + "\\) litros e agora tem \\(" + b + "\\). Quantos litros foram consumidos?"); break;
		}
		gerarAlternativasInteiras(dif);
		setResolucao("Subtraímos a quantidade menor da maior: \\(\\\\\\) \\(" + a + " - " + b + " = \\mathbf{" + dif + "}\\)");
	}

	protected void provaReal(int min, int range)
	{
		int b = 5 + rand.nextInt(range);
		int a = min + b + rand.nextInt(range);
		int c = a - b;
		addParagrafo(Nomes.masculino(rand) + " calculou que \\(" + a + " - " + b + " = " + c + "\\). Para verificar pela prova real, ele somou \\(" + c + " + " + b + "\\). Qual deve ser o resultado?");
		gerarAlternativasInteiras(a);
		setResolucao(
			"A prova real usa a operação inversa: se \\(a - b = c\\), então \\(c + b = a\\). \\(\\\\\\)" +
			"\\(" + c + " + " + b + " = \\mathbf{" + a + "}\\)"
		);
	}

	protected void missingMinuendo(int min, int range)
	{
		int b = min + rand.nextInt(range);
		int c = min + rand.nextInt(range);
		int x = b + c; // x - b = c
		addParagrafo("Qual é o valor de \\(\\square\\) na equação \\(\\square - " + b + " = " + c + "\\)?");
		gerarAlternativasInteiras(x);
		setResolucao(
			"O minuendo desconhecido é a soma da diferença com o subtraendo: \\(\\\\\\)" +
			"\\(\\square = " + c + " + " + b + " = \\mathbf{" + x + "}\\)"
		);
	}

	protected void missingSubtraendo(int min, int range)
	{
		int x = min + rand.nextInt(range);
		int c = min + rand.nextInt(range);
		int a = x + c; // a - x = c
		addParagrafo("Qual é o valor de \\(\\square\\) na equação \\(" + a + " - \\square = " + c + "\\)?");
		gerarAlternativasInteiras(x);
		setResolucao(
			"O subtraendo desconhecido é a diferença entre o minuendo e o resultado: \\(\\\\\\)" +
			"\\(\\square = " + a + " - " + c + " = \\mathbf{" + x + "}\\)"
		);
	}

	protected void tabuada()
	{
		int b = 1 + rand.nextInt(10);   // 1..10
		int dif = 1 + rand.nextInt(10); // 1..10
		int a = b + dif;
		addParagrafo("Calcule:");
		addParagrafo("\\(" + a + " - " + b + " = \\,?\\)");
		gerarAlternativasInteiras(dif);
		setResolucao("\\(" + a + " - " + b + " = \\mathbf{" + dif + "}\\)");
	}

	protected void tresEtapas(int min, int range)
	{
		int c = 1 + rand.nextInt(range);
		int b = 1 + rand.nextInt(range);
		int a = min + b + c + rand.nextInt(range);
		int p1 = a - b;
		int res = p1 - c;
		addParagrafo("Calcule o valor da expressão:");
		addParagrafo("\\(" + a + " - " + b + " - " + c + " = \\,?\\)");
		gerarAlternativasInteiras(res);
		setResolucao(
			"Subtraímos da esquerda para a direita: \\(\\\\\\)" +
			"\\(" + a + " - " + b + " = " + p1 + "\\). \\(\\\\\\)" +
			"\\(" + p1 + " - " + c + " = \\mathbf{" + res + "}\\)"
		);
	}

	protected void quatroEtapas(int min, int range)
	{
		int b = 1 + rand.nextInt(range), c = 1 + rand.nextInt(range), d = 1 + rand.nextInt(range);
		int a = min + b + c + d + rand.nextInt(range);
		int p1 = a - b, p2 = p1 - c, res = p2 - d;
		addParagrafo("Calcule o valor da expressão:");
		addParagrafo("\\(" + a + " - " + b + " - " + c + " - " + d + " = \\,?\\)");
		gerarAlternativasInteiras(res);
		setResolucao(
			"Subtraímos da esquerda para a direita: \\(\\\\\\)" +
			"\\(" + a + " - " + b + " = " + p1 + "\\); \\(" + p1 + " - " + c + " = " + p2 + "\\); \\(" + p2 + " - " + d + " = \\mathbf{" + res + "}\\)"
		);
	}

	protected void problema(int min, int range)
	{
		int b = min + rand.nextInt(range);
		int a = b + 10 + rand.nextInt(range);
		int dif = a - b;
		int tipo = rand.nextInt(3);
		if(tipo == 0)
			addParagrafo("Uma biblioteca tinha \\(" + a + "\\) livros e emprestou \\(" + b + "\\). Quantos livros restaram?");
		else if(tipo == 1)
			addParagrafo("Um ônibus partiu com \\(" + a + "\\) passageiros e \\(" + b + "\\) desceram ao longo do trajeto. Quantos continuaram?");
		else
			addParagrafo("Uma fábrica produziu \\(" + a + "\\) peças e \\(" + b + "\\) foram reprovadas. Quantas peças foram aprovadas?");
		gerarAlternativasInteiras(dif);
		setResolucao("Subtraímos o que saiu do total: \\(\\\\\\) \\(" + a + " - " + b + " = \\mathbf{" + dif + "}\\)");
	}
}
