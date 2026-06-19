package matematica.basico.divisaonatural;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;
import matematica.Nomes;
import matematica.resolucaonatural.ResolucaoNatural;

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
