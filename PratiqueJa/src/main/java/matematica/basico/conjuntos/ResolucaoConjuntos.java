package matematica.basico.conjuntos;

import matematica.ParCor;
import matematica.basico.conjuntos.nivel2package.DadosAB;

public class ResolucaoConjuntos
{
	public static String menosA(DadosAB dados)
	{
		String resolucaoLatex="";
		resolucaoLatex+=formulaMenos()+"\\\\";
		resolucaoLatex+="|A-B|="+dados.aMb+", \\quad |A \\cap B| = "+dados.aIb+"\\\\";
		resolucaoLatex+="|A| - "+dados.aIb+"="+dados.aMb+"\\\\";
		resolucaoLatex+="|A|="+dados.aMb+"+"+dados.aIb+"="+dados.a;		
		return resolucaoLatex;
	}
	
	public static String menosAIntersecB(DadosAB dados)
	{
		String resolucaoLatex="";
		resolucaoLatex+=formulaMenos()+"\\\\";
		resolucaoLatex+="|A-B|="+dados.aMb+", \\quad |A| = "+dados.a+"\\\\";
		resolucaoLatex+=dados.a+"-|A \\cap B|="+dados.aMb+"\\\\";
		resolucaoLatex+="-|A \\cap B|="+dados.aMb+"-"+dados.a+"\\\\";
		resolucaoLatex+="|A \\cap B|="+dados.a+"-"+dados.aMb+"="+dados.aIb;	
		return resolucaoLatex;
	}
	
	public static String menosAMenosB(DadosAB dados)
	{
		String resolucaoLatex="";
		resolucaoLatex+=formulaMenos()+"\\\\";
		resolucaoLatex+="|A \\cap B|="+dados.aIb+", \\quad |A| = "+dados.a+"\\\\";
		resolucaoLatex+="|A - B|="+dados.a+"-"+dados.aIb+"="+dados.aMb;	
		return resolucaoLatex;
	}
	
	public static String uniaoA(DadosAB dados)
	{
		String resolucaoLatex="";
		resolucaoLatex+=formulaUniao()+"\\\\";
		resolucaoLatex+="|B|="+dados.b+", \\quad |A \\cup B|="+dados.aUb+", \\quad |A \\cap B| = "+dados.aIb+"\\\\";
		resolucaoLatex+="|A| + "+dados.b+" - "+dados.aIb+"="+dados.aUb+"\\\\";
		resolucaoLatex+="|A|="+dados.aUb+"+"+dados.aIb+"-"+dados.b+"="+dados.a;
		return resolucaoLatex;
	}

	public static String uniaoB(DadosAB dados)
	{
		String resolucaoLatex="";
		resolucaoLatex+=formulaUniao()+"\\\\";
		resolucaoLatex+="|A|="+dados.a+", \\quad |A \\cup B|="+dados.aUb+", \\quad |A \\cap B| = "+dados.aIb+"\\\\";
		resolucaoLatex+="|B| + "+dados.a+" - "+dados.aIb+"="+dados.aUb+"\\\\";
		resolucaoLatex+="|B|="+dados.aUb+"+"+dados.aIb+"-"+dados.a+"="+dados.b;
		return resolucaoLatex;
	}
	
	public static String uniaoAIntersecB(DadosAB dados)
	{
		String resolucaoLatex="";
		resolucaoLatex+=formulaUniao()+"\\\\";
		resolucaoLatex+="|A \\cup B|="+dados.aUb+", \\quad |A| = "+dados.a+", \\quad |B| = "+dados.b+"\\\\";
		resolucaoLatex+=dados.a+"+"+dados.b+" - |A \\cap B|="+dados.aUb+"\\\\";
		resolucaoLatex+=" - |A \\cap B|="+dados.aUb+"-"+dados.a+"-"+dados.b+"\\\\";
		resolucaoLatex+="|A \\cap B|=-"+dados.aUb+"+"+dados.a+"+"+dados.b+" = "+dados.aIb;
		return resolucaoLatex;
	}
	
	public static String uniaoAIntersecB2(DadosAB dados)
	{
		String resolucaoLatex="";
		resolucaoLatex+=formulaUniao2()+"\\\\";
		resolucaoLatex+="|A \\cup B|="+dados.aUb+", \\quad |A-B| = "+dados.aMb+", \\quad |B-A| = "+dados.bMa+"\\\\";
		resolucaoLatex+=dados.aMb+"+"+dados.bMa+" + |A \\cap B|="+dados.aUb+"\\\\";
		resolucaoLatex+="|A \\cap B|="+dados.aUb+"-"+dados.aMb+"-"+dados.bMa+" = "+dados.aIb;
		return resolucaoLatex;
	}
	
	public static String uniaoAUniaoB(DadosAB dados)
	{
		String resolucaoLatex="";
		resolucaoLatex+=formulaUniao()+"\\\\";
		resolucaoLatex+="|A \\cap B|="+dados.aIb+", \\quad |A| = "+dados.a+", \\quad |B| = "+dados.b+"\\\\";
		resolucaoLatex+="|A \\cup B|="+dados.a+"+"+dados.b+"-"+dados.aIb+"="+dados.aUb;
		return resolucaoLatex;
	}
	
	public static String uniaoAUniaoB2(DadosAB dados)
	{
		String resolucaoLatex="";
		resolucaoLatex+=formulaUniao2()+"\\\\";
		resolucaoLatex+="|A \\cap B|="+dados.aIb+", \\quad |A-B| = "+dados.aMb+", \\quad |B-A| = "+dados.bMa+"\\\\";
		resolucaoLatex+="|A \\cup B|="+dados.aMb+"+"+dados.bMa+"+"+dados.aIb+"="+dados.aUb;
		return resolucaoLatex;
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
