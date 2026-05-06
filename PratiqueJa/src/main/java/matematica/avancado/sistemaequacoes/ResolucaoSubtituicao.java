package matematica.avancado.sistemaequacoes;

import matematica.expressao.MyExpression;

public class ResolucaoSubtituicao
{
	
	public static String substituicaoX1(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		String resolucaoLatex="";
		resolucaoLatex+="\\text{Isolando} ~x~ \\text{na equação (1)}\\\\";
		EquacaoSE equacao=sistema.um;
		EquacaoSE outra=sistema.dois;
		expressao = new MyExpression(equacao.valor+"+"+(-equacao.coeficienteY)+"y");
		String parteIsolada=expressao.imprimir();
		resolucaoLatex+="x ="+parteIsolada+"\\\\";
		
		resolucaoLatex+="\\text{Substituindo} ~x~ \\text{na equação (2)}\\\\";

		expressao = new MyExpression(outra.coeficienteX+"*("+parteIsolada+")+"+outra.coeficienteY+"y ="+outra.valor);
		resolucaoLatex+=expressao.resolverLatex();
		
		if(sistema.tres!=null)
		{
			resolucaoLatex+="\\\\"+resolvendoX(sistema,1);
			resolucaoLatex+=resolvendoZ(sistema);
		}
		
		return resolucaoLatex;
	}
	
	public static String substituicaoY1(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		String resolucaoLatex="";
		resolucaoLatex+="\\text{Isolando} ~y~ \\text{na equação (1)}\\\\";
		EquacaoSE equacao=sistema.um;
		EquacaoSE outra=sistema.dois;
		expressao = new MyExpression(equacao.valor+"+"+(-equacao.coeficienteX)+"x");
		String parteIsolada=expressao.imprimir();
		resolucaoLatex+="y ="+parteIsolada+"\\\\";
		
		resolucaoLatex+="\\text{Substituindo} ~y~ \\text{na equação (2)}\\\\";

		expressao = new MyExpression(outra.coeficienteX+"x +"+outra.coeficienteY+"*("+parteIsolada+")="+outra.valor);
		resolucaoLatex+=expressao.resolverLatex();
		
		if(sistema.tres!=null)
		{
			resolucaoLatex+="\\\\"+resolvendoY(sistema,1);
			resolucaoLatex+=resolvendoZ(sistema);
		}

		return resolucaoLatex;
	}
	
	public static String substituicaoX2(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		String resolucaoLatex="";
		resolucaoLatex+="\\text{Isolando} ~x~ \\text{na equação (2)}\\\\";
		EquacaoSE equacao=sistema.dois;
		EquacaoSE outra=sistema.um;
		expressao = new MyExpression(equacao.valor+"+"+(-equacao.coeficienteY)+"y");
		String parteIsolada=expressao.imprimir();
		resolucaoLatex+="x ="+parteIsolada+"\\\\";
		
		resolucaoLatex+="\\text{Substituindo} ~x~ \\text{na equação (1)}\\\\";

		expressao = new MyExpression(outra.coeficienteX+"*("+parteIsolada+")+"+outra.coeficienteY+"y ="+outra.valor);
		resolucaoLatex+=expressao.resolverLatex();
		
		if(sistema.tres!=null)
		{
			resolucaoLatex+="\\\\"+resolvendoX(sistema,2);
			resolucaoLatex+=resolvendoZ(sistema);
		}

		return resolucaoLatex;
	}
	
	public static String substituicaoY2(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		String resolucaoLatex="";
		resolucaoLatex+="\\text{Isolando} ~y~ \\text{na equação (2)}\\\\";
		EquacaoSE equacao=sistema.dois;
		EquacaoSE outra=sistema.um;
		expressao = new MyExpression(equacao.valor+"+"+(-equacao.coeficienteX)+"x");
		String parteIsolada=expressao.imprimir();
		resolucaoLatex+="y ="+parteIsolada+"\\\\";
		
		resolucaoLatex+="\\text{Substituindo} ~y~ \\text{na equação (1)}\\\\";

		expressao = new MyExpression(outra.coeficienteX+"x+"+outra.coeficienteY+"*("+parteIsolada+") ="+outra.valor);
		resolucaoLatex+=expressao.resolverLatex();
		
		if(sistema.tres!=null)
		{
			resolucaoLatex+="\\\\"+resolvendoY(sistema,2);
			resolucaoLatex+=resolvendoZ(sistema);
		}

		return resolucaoLatex;
	}
	
	public static String resolvendoX(SistemaEquacoes sistema, int numEq)
	{
		String resolucaoLatex="";
		MyExpression expressao;
		EquacaoSE equacao;
		if(numEq==1)
			equacao=sistema.um;
		else
			equacao=sistema.dois;

		resolucaoLatex+="\\text{Substituindo} ~y~ \\text{na equação ("+numEq+")}\\\\";
		
		if((-equacao.coeficienteY)==1)
			expressao = new MyExpression("x ="+equacao.valor+"+"+sistema.y);
		else
			expressao = new MyExpression("x ="+equacao.valor+"+"+(-equacao.coeficienteY)+"*"+sistema.y);

		resolucaoLatex+=expressao.resolverLatex()+"\\\\";
		
		return resolucaoLatex;
	}
	
	public static String resolvendoY(SistemaEquacoes sistema, int numEq)
	{
		String resolucaoLatex="";
		MyExpression expressao;
		EquacaoSE equacao;
		if(numEq==1)
			equacao=sistema.um;
		else
			equacao=sistema.dois;

		resolucaoLatex+="\\text{Substituindo} ~x~ \\text{na equação ("+numEq+")}\\\\";
		
		if((-equacao.coeficienteY)==1)
			expressao = new MyExpression("y ="+equacao.valor+"+"+sistema.x);
		else
			expressao = new MyExpression("y ="+equacao.valor+"+"+(-equacao.coeficienteX)+"*"+sistema.x);

		resolucaoLatex+=expressao.resolverLatex()+"\\\\";
		
		return resolucaoLatex;
	}
	
	public static String resolvendoZ(SistemaEquacoes sistema)
	{
		String resolucaoLatex="";
		EquacaoSE tres=sistema.tres;
		MyExpression expressao;
		String strExpressao="z=";
		if(tres.coeficienteX!=1)
			strExpressao+=tres.coeficienteX+"*";
			
		strExpressao+=sistema.x+"+";
		if(tres.coeficienteY!=1)
			strExpressao+=tres.coeficienteY+"*";
		
		strExpressao+=sistema.y;

		expressao = new MyExpression(strExpressao);
		resolucaoLatex+=expressao.resolverLatex();
		
		return resolucaoLatex;
	}
	
	public static String substituicaoY(SistemaEquacoes sistema)
	{
		if(sistema.um.coeficienteX==1)
			return substituicaoX1(sistema);
		
		if(sistema.dois.coeficienteX==1)
			return substituicaoX2(sistema);
		return "";
	}
	
	public static String substituicaoX(SistemaEquacoes sistema)
	{
		if(sistema.um.coeficienteY==1)
			return substituicaoY1(sistema);
		
		if(sistema.dois.coeficienteY==1)
			return substituicaoY2(sistema);
		return "";
	}
	
	
}
