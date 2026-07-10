package matematica.intermediario.funcaoafim;

import matematica.Racional;

public class ResolucaoFuncaoAfim
{

	public static String formulaA(String A, String B)
	{
		return "a=\\dfrac{\\Delta y}{\\Delta x} = \\dfrac{y_"+B+" - y_"+A+"}{x_"+B+"-x_"+A+"}";
	}
	
	// Retorna os passos da resolução (corpos matemáticos, SEM o "\(...\)"); cada elemento
	// é um parágrafo. O chamador envolve cada passo em "\(...\)" e chama addResolucao por passo.
	public static String[] resolucao( int pontoAx, int pontoAy, int pontoBx, int pontoBy)
	{
		String passoFormula = formulaA("A","B");

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

		String passoCalculo="a= \\dfrac{"+pontoBy+" - "+strPontoAy+"}{"+pontoBx+"-"+strPontoAx+"}";

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

			passoCalculo+="= \\dfrac{"+pontoBy+strPontoAy+"}{"+pontoBx+strPontoAx+"}";
		}

		Racional racional=new Racional((pontoBy-pontoAy),(pontoBx-pontoAx));
		String semSimplificar = racional.toStringLatex();
		racional.fatoracao(2);
		if(racional.isSimplificou())
			passoCalculo+="="+ semSimplificar +"=\\mathbf{"+ racional.toStringLatex() +"}";
		else
			passoCalculo+="=\\mathbf{"+ semSimplificar +"}";

		return new String[] { passoFormula, passoCalculo };
	}

}