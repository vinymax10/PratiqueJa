package matematica.basico.conjuntos;

import matematica.ParCor;
import matematica.basico.conjuntos.nivel2package.DadosAB;

public class ResolucaoConjuntos
{
	public static String[] menosA(DadosAB dados)
	{
		return new String[]{
			"\\(" + formulaMenos() + "\\)",
			"\\(|A-B| = " + dados.aMb + ", \\quad |A \\cap B| = " + dados.aIb + "\\)",
			"\\(|A| - " + dados.aIb + " = " + dados.aMb + "\\)",
			"\\(|A| = " + dados.aMb + " + " + dados.aIb + " = \\mathbf{" + dados.a + "}\\)"
		};
	}

	public static String[] menosAIntersecB(DadosAB dados)
	{
		return new String[]{
			"\\(" + formulaMenos() + "\\)",
			"\\(|A-B| = " + dados.aMb + ", \\quad |A| = " + dados.a + "\\)",
			"\\(" + dados.a + " - |A \\cap B| = " + dados.aMb + "\\)",
			"\\(-|A \\cap B| = " + dados.aMb + " - " + dados.a + "\\)",
			"\\(|A \\cap B| = " + dados.a + " - " + dados.aMb + " = \\mathbf{" + dados.aIb + "}\\)"
		};
	}

	public static String[] menosAMenosB(DadosAB dados)
	{
		return new String[]{
			"\\(" + formulaMenos() + "\\)",
			"\\(|A \\cap B| = " + dados.aIb + ", \\quad |A| = " + dados.a + "\\)",
			"\\(|A - B| = " + dados.a + " - " + dados.aIb + " = \\mathbf{" + dados.aMb + "}\\)"
		};
	}

	public static String[] uniaoA(DadosAB dados)
	{
		return new String[]{
			"\\(" + formulaUniao() + "\\)",
			"\\(|B| = " + dados.b + ", \\quad |A \\cup B| = " + dados.aUb + ", \\quad |A \\cap B| = " + dados.aIb + "\\)",
			"\\(|A| + " + dados.b + " - " + dados.aIb + " = " + dados.aUb + "\\)",
			"\\(|A| = " + dados.aUb + " + " + dados.aIb + " - " + dados.b + " = \\mathbf{" + dados.a + "}\\)"
		};
	}

	public static String[] uniaoB(DadosAB dados)
	{
		return new String[]{
			"\\(" + formulaUniao() + "\\)",
			"\\(|A| = " + dados.a + ", \\quad |A \\cup B| = " + dados.aUb + ", \\quad |A \\cap B| = " + dados.aIb + "\\)",
			"\\(|B| + " + dados.a + " - " + dados.aIb + " = " + dados.aUb + "\\)",
			"\\(|B| = " + dados.aUb + " + " + dados.aIb + " - " + dados.a + " = \\mathbf{" + dados.b + "}\\)"
		};
	}

	public static String[] uniaoAIntersecB(DadosAB dados)
	{
		return new String[]{
			"\\(" + formulaUniao() + "\\)",
			"\\(|A \\cup B| = " + dados.aUb + ", \\quad |A| = " + dados.a + ", \\quad |B| = " + dados.b + "\\)",
			"\\(" + dados.a + " + " + dados.b + " - |A \\cap B| = " + dados.aUb + "\\)",
			"\\(-|A \\cap B| = " + dados.aUb + " - " + dados.a + " - " + dados.b + "\\)",
			"\\(|A \\cap B| = -" + dados.aUb + " + " + dados.a + " + " + dados.b + " = \\mathbf{" + dados.aIb + "}\\)"
		};
	}

	public static String[] uniaoAIntersecB2(DadosAB dados)
	{
		return new String[]{
			"\\(" + formulaUniao2() + "\\)",
			"\\(|A \\cup B| = " + dados.aUb + ", \\quad |A-B| = " + dados.aMb + ", \\quad |B-A| = " + dados.bMa + "\\)",
			"\\(" + dados.aMb + " + " + dados.bMa + " + |A \\cap B| = " + dados.aUb + "\\)",
			"\\(|A \\cap B| = " + dados.aUb + " - " + dados.aMb + " - " + dados.bMa + " = \\mathbf{" + dados.aIb + "}\\)"
		};
	}

	public static String[] uniaoAUniaoB(DadosAB dados)
	{
		return new String[]{
			"\\(" + formulaUniao() + "\\)",
			"\\(|A \\cap B| = " + dados.aIb + ", \\quad |A| = " + dados.a + ", \\quad |B| = " + dados.b + "\\)",
			"\\(|A \\cup B| = " + dados.a + " + " + dados.b + " - " + dados.aIb + " = \\mathbf{" + dados.aUb + "}\\)"
		};
	}

	public static String[] uniaoAUniaoB2(DadosAB dados)
	{
		return new String[]{
			"\\(" + formulaUniao2() + "\\)",
			"\\(|A \\cap B| = " + dados.aIb + ", \\quad |A-B| = " + dados.aMb + ", \\quad |B-A| = " + dados.bMa + "\\)",
			"\\(|A \\cup B| = " + dados.aMb + " + " + dados.bMa + " + " + dados.aIb + " = \\mathbf{" + dados.aUb + "}\\)"
		};
	}

	public static String formulaMenos()
	{
		return ParCor.formula("|A-B| = |A| - |A \\cap B|");
	}

	public static String formulaUniao()
	{
		return ParCor.formula("|A \\cup B| = |A| + |B| - |A \\cap B|");
	}

	public static String formulaUniao2()
	{
		return ParCor.formula("|A \\cup B| = |A-B| + |B-A| + |A \\cap B|");
	}

}
