package matematica.intermediario.radiciacao;

public class ResolucaoRadiciacao
{
	public static FatoresPrimos fatoresPrimos(int a)
	{
		int divisor=2;
		int numeroOcorrencias=0;
		FatoresPrimos fatoresPrimos=new FatoresPrimos();
		if(a==1)
		{
			fatoresPrimos.add(1,1);
		}
		else
		{
			while(a!=1)
			{
				if(a%divisor==0)
				{
					numeroOcorrencias++;
					a/=divisor;
				}
				else
				{
					if(numeroOcorrencias!=0)
						fatoresPrimos.add(divisor,numeroOcorrencias);
						
					divisor++;
					numeroOcorrencias=0;
				}
			}
			
			fatoresPrimos.add(divisor,numeroOcorrencias);
		}

		return fatoresPrimos;
	}
	
	public static String resolucao(FatoresPrimos fatoresPrimos, int potencia)
	{
		String resolucaoLatex="";
		
		if(potencia>2)
			resolucaoLatex+="\\sqrt["+potencia+"]{"+fatoresPrimos.latex()+"}=";
		else
			resolucaoLatex+="\\sqrt{"+fatoresPrimos.latex()+"}=";
		
		fatoresPrimos.dividirPotencias(potencia);

		if(!fatoresPrimos.precisaDesenvolver())
		{
			// Já reduzido a um único fator de potência 1 — esse valor já É o resultado final.
			resolucaoLatex+="\\mathbf{"+fatoresPrimos.latex()+"}";
			return resolucaoLatex;
		}

		resolucaoLatex+=""+fatoresPrimos.latex();

		if(fatoresPrimos.possuiPotencias())
		{
			fatoresPrimos.removerPotencias();
			resolucaoLatex+="="+fatoresPrimos.latex();
		}

		resolucaoLatex+="=\\mathbf{"+fatoresPrimos.resultado()+"}";

		return resolucaoLatex;
	}
	
	public static String resolucao(int valor, int potencia)
	{
		String resolucaoLatex;
		if(potencia>2)
			resolucaoLatex="\\sqrt["+potencia+"]{"+valor+"}=";
		else
			resolucaoLatex="\\sqrt{"+valor+"}=";
		
		if(valor==1)
		{
			resolucaoLatex+="\\mathbf{"+valor+"}";
		}
		else
		{
			FatoresPrimos fatoresPrimos=fatoresPrimos(valor);
			resolucaoLatex+=resolucao(fatoresPrimos,potencia);
		}
		return resolucaoLatex;
	}
	
}