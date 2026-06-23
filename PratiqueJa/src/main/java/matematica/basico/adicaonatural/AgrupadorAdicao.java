package matematica.basico.adicaonatural;

import matematica.GeradorExercicio;
import matematica.Nomes;
import matematica.resolucaonatural.ResolucaoNatural;

// Base com helpers de exercício para adição de naturais (Fase 2).
public abstract class AgrupadorAdicao extends GeradorExercicio
{
	protected void coluna(int min, int range)
	{
		int a = min + rand.nextInt(range);
		int b = min + rand.nextInt(range);
		addParagrafo("Calcule a seguinte adição:");
		addParagrafo("\\(" + ResolucaoNatural.soma(a, b, false) + "\\)");
		gerarAlternativasInteiras(a + b);
		addResolucao("\\(" + ResolucaoNatural.soma(a, b, true) + "\\)");
	}
	protected void quatroParcelas(int min, int range)
	{
		int a = min + rand.nextInt(range), b = min + rand.nextInt(range);
		int c = min + rand.nextInt(range), d = min + rand.nextInt(range);
		int s1 = a + b, s2 = s1 + c, s3 = s2 + d;
		addParagrafo("Calcule a soma das quatro parcelas:");
		addParagrafo("\\(" + a + " + " + b + " + " + c + " + " + d + "\\)");
		gerarAlternativasInteiras(s3);
		addResolucao("Somamos passo a passo:");
		addResolucao("\\(" + a + " + " + b + " = " + s1 + "\\); \\(" + s1 + " + " + c + " = " + s2 + "\\); \\(" + s2 + " + " + d + " = \\mathbf{" + s3 + "}\\)");
	}

	protected void tabuada()
	{
		int a = 1 + rand.nextInt(10);
		int b = 1 + rand.nextInt(10);
		addParagrafo("Calcule:");
		addParagrafo("\\(" + a + " + " + b + " = \\,?\\)");
		gerarAlternativasInteiras(a + b);
		addResolucao("\\(" + a + " + " + b + " = \\mathbf{" + (a + b) + "}\\)");
	}
	protected void problema(int min, int range)
	{
		int a = min + rand.nextInt(range);
		int b = min + rand.nextInt(range);
		int soma = a + b;
		int tipo = rand.nextInt(3);
		if(tipo == 0)
			addParagrafo("Uma loja vendeu \\(" + a + "\\) produtos pela manhã e \\(" + b + "\\) à tarde. Quantos produtos foram vendidos no dia?");
		else if(tipo == 1)
			addParagrafo("Um estádio tem \\(" + a + "\\) torcedores no setor A e \\(" + b + "\\) no setor B. Quantos torcedores há ao todo?");
		else
			addParagrafo("Uma biblioteca recebeu \\(" + a + "\\) livros de ficção e \\(" + b + "\\) de não-ficção. Quantos livros recebeu no total?");
		gerarAlternativasInteiras(soma);
		addResolucao("Somamos as duas quantidades:");
		addResolucao("\\(" + a + " + " + b + " = \\mathbf{" + soma + "}\\)");
	}
}
