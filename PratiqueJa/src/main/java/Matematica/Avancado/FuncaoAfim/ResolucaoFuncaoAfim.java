package Matematica.Avancado.FuncaoAfim;

import Matematica.Racional;

public class ResolucaoFuncaoAfim
{

	public static String formulaA(String A, String B)
	{
		return "a=\\dfrac{\\Delta y}{\\Delta x} = \\dfrac{y_"+B+" - y_"+A+"}{x_"+B+"-x_"+A+"}";
	}
	
	public static String resolucao( int pontoAx, int pontoAy, int pontoBx, int pontoBy)
	{
		String resolucaoLatex=formulaA("A","B")+"\\\\";
		String strPontoAy="";
		String strPontoAx="";
		
		if(pontoAy<0)
			strPontoAy="("+pontoAy+")";
		else
			strPontoAy=""+pontoAy;
		
		if(pontoAx<0)
			strPontoAx="("+pontoAx+")";
		else
			strPontoAx=""+pontoAx;

		resolucaoLatex+="a= \\dfrac{"+pontoBy+" - "+strPontoAy+"}{"+pontoBx+"-"+strPontoAx+"}";
		
		if(pontoAy<0||pontoAx<0)
		{
			if(pontoAy<0)
				strPontoAy="+"+pontoAy*-1;
			else
				strPontoAy="-"+pontoAy;
			
			if(pontoAx<0)
				strPontoAx="+"+pontoAx*-1;
			else
				strPontoAx="-"+pontoAx;

			resolucaoLatex+="= \\dfrac{"+pontoBy+strPontoAy+"}{"+pontoBx+strPontoAx+"}";
		}

		Racional racional=new Racional((pontoBy-pontoAy),(pontoBx-pontoAx));
		resolucaoLatex+="="+ racional.toStringLatex();
		racional.fatoracao(2);
		if(racional.isSimplificou())
			resolucaoLatex+="="+ racional.toStringLatex();

		return resolucaoLatex;
	}
	
}