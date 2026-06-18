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
		setResolucao("\\(" + ResolucaoNatural.soma(a, b, true) + "\\)");
	}

	protected void tresParcelas(int min, int range)
	{
		int a = min + rand.nextInt(range);
		int b = min + rand.nextInt(range);
		int c = min + rand.nextInt(range);
		int ab = a + b;
		addParagrafo("Calcule a soma das três parcelas:");
		addParagrafo("\\(" + a + " + " + b + " + " + c + "\\)");
		gerarAlternativasInteiras(ab + c);
		String res = "Somamos as duas primeiras parcelas: \\(\\\\\\)";
		res += "\\(" + ResolucaoNatural.soma(a, b, true) + "\\) \\(\\\\\\)";
		res += "Somamos o resultado \\(" + ab + "\\) com a terceira parcela: \\(\\\\\\)";
		res += "\\(" + ResolucaoNatural.soma(ab, c, true) + "\\)";
		setResolucao(res);
	}

	protected void quatroParcelas(int min, int range)
	{
		int a = min + rand.nextInt(range), b = min + rand.nextInt(range);
		int c = min + rand.nextInt(range), d = min + rand.nextInt(range);
		int s1 = a + b, s2 = s1 + c, s3 = s2 + d;
		addParagrafo("Calcule a soma das quatro parcelas:");
		addParagrafo("\\(" + a + " + " + b + " + " + c + " + " + d + "\\)");
		gerarAlternativasInteiras(s3);
		setResolucao(
			"Somamos passo a passo: \\(\\\\\\)" +
			"\\(" + a + " + " + b + " = " + s1 + "\\); \\(" + s1 + " + " + c + " = " + s2 + "\\); \\(" + s2 + " + " + d + " = \\mathbf{" + s3 + "}\\)"
		);
	}

	protected void tabuada()
	{
		int a = 1 + rand.nextInt(10);
		int b = 1 + rand.nextInt(10);
		addParagrafo("Calcule:");
		addParagrafo("\\(" + a + " + " + b + " = \\,?\\)");
		gerarAlternativasInteiras(a + b);
		setResolucao("\\(" + a + " + " + b + " = \\mathbf{" + (a + b) + "}\\)");
	}

	protected void parcelaMissing(int min, int range)
	{
		int a = min + rand.nextInt(range);
		int x = min + rand.nextInt(range);
		int c = a + x;
		boolean naEsquerda = rand.nextBoolean();
		if(naEsquerda)
			addParagrafo("Qual é o valor de \\(\\square\\) na equação \\(\\square + " + a + " = " + c + "\\)?");
		else
			addParagrafo("Qual é o valor de \\(\\square\\) na equação \\(" + a + " + \\square = " + c + "\\)?");
		gerarAlternativasInteiras(x);
		setResolucao(
			"A parcela desconhecida é a diferença entre a soma e a parcela conhecida: \\(\\\\\\)" +
			"\\(\\square = " + c + " - " + a + " = \\mathbf{" + x + "}\\)"
		);
	}

	protected void verificacao(int min, int range)
	{
		int a = min + rand.nextInt(range);
		int b = min + rand.nextInt(range);
		int c = a + b;
		addParagrafo(Nomes.feminino(rand) + " calculou que \\(" + a + " + " + b + " = " + c + "\\). Para verificar, ela subtraiu \\(" + c + " - " + b + "\\). Qual deve ser o resultado?");
		gerarAlternativasInteiras(a);
		setResolucao(
			"A verificação da adição usa a operação inversa: se \\(a + b = c\\), então \\(c - b = a\\). \\(\\\\\\)" +
			"\\(" + c + " - " + b + " = \\mathbf{" + a + "}\\)"
		);
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
		setResolucao("Somamos as duas quantidades: \\(\\\\\\) \\(" + a + " + " + b + " = \\mathbf{" + soma + "}\\)");
	}
}
