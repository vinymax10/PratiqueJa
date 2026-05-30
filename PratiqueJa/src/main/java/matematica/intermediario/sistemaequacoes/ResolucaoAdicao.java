package matematica.intermediario.sistemaequacoes;

import matematica.expressao.MyExpression;

public class ResolucaoAdicao
{
	public static String adicaoY(SistemaEquacoes sistema)
	{
		if(sistema.um.coeficienteX==1)
			return adicaoX1(sistema);
		
		if(sistema.dois.coeficienteX==1)
			return adicaoX2(sistema);
		return "";
	}
	
	public static String adicaoX2(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		String resolucaoLatex="";
		EquacaoSE outra=sistema.um;
		EquacaoSE terceira=sistema.dois.multiplica((-outra.coeficienteX));
		
		if(outra.coeficienteX!=-1)
		{
			resolucaoLatex+="\\text{Multiplicando a equação (2) por } "+(-outra.coeficienteX)+"\\\\";
			resolucaoLatex+=terceira.latex()+"\\quad (3) \\\\";
			resolucaoLatex+="\\text{Somando as equações (1) e (3)} \\\\";
		}
		else
		{
			resolucaoLatex+="\\text{Somando as equações (1) e (2)} \\\\";
		}
		
		expressao = new MyExpression(outra.coeficienteX+"x +"+terceira.coeficienteX+"x +"+outra.coeficienteY+"y +"+terceira.coeficienteY+"y="+outra.valor+" +"+terceira.valor );
		resolucaoLatex+=expressao.imprimir()+"\\\\";
		
		expressao = new MyExpression(outra.coeficienteY+"y +"+terceira.coeficienteY+"y="+outra.valor+" +"+terceira.valor );
		resolucaoLatex+=expressao.resolverLatex();
		
		if(sistema.tres!=null)
		{
			resolucaoLatex+="\\\\"+resolvendoX(sistema,2);
			resolucaoLatex+=ResolucaoSubtituicao.resolvendoZ(sistema);
		}
		
		return resolucaoLatex;
	}
	
	public static String adicaoX1(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		String resolucaoLatex="";
		EquacaoSE outra=sistema.dois;
		EquacaoSE terceira=sistema.um.multiplica((-outra.coeficienteX));

		if(outra.coeficienteX!=-1)
		{
			resolucaoLatex+="\\text{Multiplicando a equação (1) por } "+(-outra.coeficienteX)+"\\\\";
			resolucaoLatex+=terceira.latex()+"\\quad (3) \\\\";
			resolucaoLatex+="\\text{Somando as equações (2) e (3)} \\\\";
		}
		else
		{
			resolucaoLatex+="\\text{Somando as equações (1) e (2)} \\\\";
		}
		
		expressao = new MyExpression(outra.coeficienteX+"x +"+terceira.coeficienteX+"x +"+outra.coeficienteY+"y +"+terceira.coeficienteY+"y="+outra.valor+" +"+terceira.valor );
		resolucaoLatex+=expressao.imprimir()+"\\\\";
		
		expressao = new MyExpression(outra.coeficienteY+"y +"+terceira.coeficienteY+"y="+outra.valor+" +"+terceira.valor );
		resolucaoLatex+=expressao.resolverLatex();
		
		if(sistema.tres!=null)
		{
			resolucaoLatex+="\\\\"+resolvendoX(sistema,1);
			resolucaoLatex+=ResolucaoSubtituicao.resolvendoZ(sistema);
		}
		
		return resolucaoLatex;
	}
	
	public static String adicaoX(SistemaEquacoes sistema)
	{
		if(sistema.um.coeficienteY==1)
			return adicaoY1(sistema);
		
		if(sistema.dois.coeficienteY==1)
			return adicaoY2(sistema);
		return "";
	}
	
	public static String adicaoY2(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		String resolucaoLatex="";
		EquacaoSE outra=sistema.um;
		EquacaoSE terceira=sistema.dois.multiplica((-outra.coeficienteY));

		if(outra.coeficienteY!=-1)
		{
			resolucaoLatex+="\\text{Multiplicando a equação (2) por } "+(-outra.coeficienteY)+"\\\\";
			resolucaoLatex+=terceira.latex()+"\\quad (3) \\\\";
			resolucaoLatex+="\\text{Somando as equações (1) e (3)} \\\\";
		}
		else
		{
			resolucaoLatex+="\\text{Somando as equações (1) e (2)} \\\\";
		}
		
		expressao = new MyExpression(outra.coeficienteX+"x +"+terceira.coeficienteX+"x +"+outra.coeficienteY+"y +"+terceira.coeficienteY+"y="+outra.valor+" +"+terceira.valor );
		resolucaoLatex+=expressao.imprimir()+"\\\\";
		
		expressao = new MyExpression(outra.coeficienteX+"x +"+terceira.coeficienteX+"x="+outra.valor+" +"+terceira.valor );
		resolucaoLatex+=expressao.resolverLatex();
		
		if(sistema.tres!=null)
		{
			resolucaoLatex+="\\\\"+resolvendoY(sistema,2);
			resolucaoLatex+=ResolucaoSubtituicao.resolvendoZ(sistema);
		}
		
		return resolucaoLatex;
	}
	
	public static String adicaoY1(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		String resolucaoLatex="";
		EquacaoSE outra=sistema.dois;
		EquacaoSE terceira=sistema.um.multiplica((-outra.coeficienteY));

		if(outra.coeficienteY!=-1)
		{
			resolucaoLatex+="\\text{Multiplicando a equação (1) por } "+(-outra.coeficienteY)+"\\\\";
			resolucaoLatex+=terceira.latex()+"\\quad (3) \\\\";
			resolucaoLatex+="\\text{Somando as equações (2) e (3)} \\\\";
		}
		else
		{
			resolucaoLatex+="\\text{Somando as equações (1) e (2)} \\\\";
		}
		
		expressao = new MyExpression(outra.coeficienteX+"x +"+terceira.coeficienteX+"x +"+outra.coeficienteY+"y +"+terceira.coeficienteY+"y="+outra.valor+" +"+terceira.valor );
		resolucaoLatex+=expressao.imprimir()+"\\\\";
		
		expressao = new MyExpression(outra.coeficienteX+"x +"+terceira.coeficienteX+"x="+outra.valor+" +"+terceira.valor );
		resolucaoLatex+=expressao.resolverLatex();
		
		if(sistema.tres!=null)
		{
			resolucaoLatex+="\\\\"+resolvendoY(sistema,1);
			resolucaoLatex+=ResolucaoSubtituicao.resolvendoZ(sistema);
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
		
		if(equacao.coeficienteY==1)
			expressao = new MyExpression(equacao.coeficienteX+"x +"+sistema.y+"="+equacao.valor);
		else
			expressao = new MyExpression(equacao.coeficienteX+"x +"+equacao.coeficienteY+"*"+sistema.y+"="+equacao.valor);

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
		
		if(equacao.coeficienteX==1)
			expressao = new MyExpression(sistema.x+"+"+equacao.coeficienteY+"y="+equacao.valor);
		else
			expressao = new MyExpression(equacao.coeficienteX+"*"+ sistema.x+"+"+equacao.coeficienteY+"y="+equacao.valor);

		resolucaoLatex+=expressao.resolverLatex()+"\\\\";
		
		return resolucaoLatex;
	}
}
