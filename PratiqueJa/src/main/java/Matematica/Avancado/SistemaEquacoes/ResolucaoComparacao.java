package Matematica.Avancado.SistemaEquacoes;

import Matematica.Expressao.MyExpression;

public class ResolucaoComparacao
{
	
	public static String comparacaoY(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		String resolucaoLatex="";
		resolucaoLatex+="\\text{Isolando} ~x~ \\text{na equação (1)}\\\\";
		EquacaoSE equacao=sistema.um;
		EquacaoSE outra=sistema.dois;
		expressao = new MyExpression(equacao.valor+"+"+(-equacao.coeficienteY)+"y");
		String parteIsolada=expressao.imprimir();
		resolucaoLatex+="x ="+parteIsolada+"\\\\";
		
		resolucaoLatex+="\\text{Isolando} ~x~ \\text{na equação (2)}\\\\";
		expressao = new MyExpression(outra.valor+"+"+(-outra.coeficienteY)+"y");
		String parteIsolada2=expressao.imprimir();
		resolucaoLatex+="x ="+parteIsolada2+"\\\\";
		
		resolucaoLatex+="\\text{Iqualando as duas equações}\\\\";

		expressao = new MyExpression(parteIsolada+"="+parteIsolada2);
		resolucaoLatex+=expressao.resolverLatex();
		
		if(sistema.tres!=null)
		{
			resolucaoLatex+="\\\\"+ResolucaoAdicao.resolvendoX(sistema,2);
			resolucaoLatex+=ResolucaoSubtituicao.resolvendoZ(sistema);
		}
		
		return resolucaoLatex;
	}
	
	public static String comparacaoX(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		String resolucaoLatex="";
		EquacaoSE equacao=sistema.um;
		EquacaoSE outra=sistema.dois;

		resolucaoLatex+="\\text{Isolando} ~y~ \\text{na equação (1)}\\\\";
		expressao = new MyExpression(equacao.valor+"+"+(-equacao.coeficienteX)+"x");
		String parteIsolada=expressao.imprimir();
		resolucaoLatex+="y ="+parteIsolada+"\\\\";
		
		resolucaoLatex+="\\text{Isolando} ~y~ \\text{na equação (2)}\\\\";
		expressao = new MyExpression(outra.valor+"+"+(-outra.coeficienteX)+"x");
		String parteIsolada2=expressao.imprimir();
		resolucaoLatex+="y ="+parteIsolada2+"\\\\";
		
		resolucaoLatex+="\\text{Iqualando as duas equações}\\\\";

		expressao = new MyExpression(parteIsolada+"="+parteIsolada2);
		resolucaoLatex+=expressao.resolverLatex();
		
		if(sistema.tres!=null)
		{
			resolucaoLatex+="\\\\"+ResolucaoAdicao.resolvendoY(sistema,2);
			resolucaoLatex+=ResolucaoSubtituicao.resolvendoZ(sistema);
		}
		
		return resolucaoLatex;
	}
	
}
