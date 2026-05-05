package Matematica.Expressao;

import java.util.ArrayList;
import java.util.List;

import Matematica.Racional;


public class MyExpression
{
	String expressoes;
	List<NoExpression> lista = new ArrayList<NoExpression>();
	boolean juntarParenteses;
	boolean fezDistributiva;
	String resolucaoLatex;
	NoExpression resultado;
	boolean auditoria=false;
	
	public MyExpression(String expressoes)
	{
		this.expressoes = expressoes;
		parser();
	}

	public void parser()
	{
		expressoes = expressoes.replaceAll(" ", "");
		expressoes = expressoes.replaceAll("\\+-", "-");

		String string = "";
		for(int i = 0; i < expressoes.length(); i++)
		{
			char caracter = expressoes.charAt(i);

			if(i > 0 && 
			(caracter == '+' || caracter == '-' || caracter == '*' ||string.equals("*")
			|| caracter == '=' || string.equals("=")
			|| caracter == '(' || string.equals("(")
			|| caracter == ')' || string.equals(")")))
			{
				lista.add(new NoExpression(string));
				string = "";
			}
			string += caracter;
		}
		lista.add(new NoExpression(string));
	}

	private int indexIgual()
	{
		for(int i = 0; i < lista.size(); i++)
		{
			if(lista.get(i).tipoNo == TipoNo.Igual)
				return i;
		}
		return -1;
	}

	public boolean multiplicacao()
	{
		NoExpression no,ant,prox;
		boolean multiplicou = false;
		for(int i = 0; i < lista.size(); i++)
		{
			no = lista.get(i);
			if(no.literal.equals("*")&&i>0&&i<lista.size()-1)
			{
				ant=lista.get(i-1);
				prox=lista.get(i+1);
				ant.multiplica(prox);
				lista.remove(no);
				lista.remove(prox);
				i--;
				multiplicou = true;
			}
		}
		return multiplicou;
	}
	
	private void removerParenteses(Posicao posicao)
	{
		NoExpression no;
		
		for(int i = posicao.inicio; i < lista.size(); i++)
		{
			no = lista.get(i);
			if(no.literal.equals("("))
			{
				lista.remove(no);
				i--;
			}
			
			if(no.literal.equals(")"))
			{
				lista.remove(no);
				break;
			}
		}
		posicao.fim-=2;
	}
	
	private boolean distributiva(Posicao posicao)
	{
		boolean fez=false;
		
		if(posicao.inicio>1
		&&lista.get(posicao.inicio-1).tipoNo==TipoNo.Operador
		&&lista.get(posicao.inicio-1).literal.equals("*")
		&&lista.get(posicao.inicio-2).tipoNo==TipoNo.Numero)
		{
			NoExpression noMultiplica=lista.get(posicao.inicio-2);
			int iteracoes=posicao.numeroElementos;
			NoExpression no;
			for(int i = 0; i < iteracoes; i++)
			{
				no = lista.get(posicao.inicio+i+1);
	
				no.multiplica(noMultiplica);
				fez = true;
			}
			
			lista.remove(posicao.inicio-2);
			lista.remove(posicao.inicio-2);
			
			posicao.inicio-=2;
			posicao.fim-=2;
			
			removerParenteses(posicao);
		}
		
		if(!fez&&posicao.fim+2<lista.size()
		&&lista.get(posicao.fim+1).tipoNo==TipoNo.Operador
		&&lista.get(posicao.fim+1).literal.equals("*")
		&&lista.get(posicao.fim+2).tipoNo==TipoNo.Numero)
		{
			NoExpression noMultiplica=lista.get(posicao.fim+2);
			int iteracoes=posicao.numeroElementos;
			NoExpression no;
			for(int i = 0; i < iteracoes; i++)
			{
				no = lista.get(posicao.inicio+i+1);
	
				no.multiplica(noMultiplica);
				fez = true;
			}
			
			lista.remove(posicao.fim+1);
			lista.remove(posicao.fim+1);
			removerParenteses(posicao);
		}
		return fez;
	}
	
	public boolean parenteses()
	{
		Posicao posicao=new Posicao();
		juntarParenteses=false;
		fezDistributiva=false;
			
		NoExpression no;
		boolean possuiAbertura = false;
		boolean possuiFechamento = false;
		for(int i = 0; i < lista.size()&&!juntarParenteses&&!fezDistributiva; i++)
		{
//			Procurando parenteses--------------
			no = lista.get(i);
			if(no.literal.equals("("))
			{
				posicao.setInicio(i);
				possuiAbertura=true;
			}
			
			if(no.literal.equals(")"))
			{
				posicao.setFim(i);
				possuiFechamento=true;
			}
//			-----------------------------------
			
			if(possuiAbertura&&possuiFechamento)
			{
				possuiAbertura=false;
				possuiFechamento=false;
				
				if(posicao.numeroElementos>1)
					juntarParenteses=juntar(posicao);
				
				if(juntarParenteses&&posicao.numeroElementos<2)
				{
					if(posicao.inicio>0)
					{
						NoExpression node=lista.get(posicao.inicio-1);
						if(node.tipoNo==TipoNo.Operador&&
						(node.literal.equals("-")))
							resolucaoLatex +="\\\\"+(auditoria? "inversao sinal ":"")+ imprimir();
					}
					removerParenteses(posicao);
					juntarSinalForaParenteses(posicao);
				}
				
				if(!juntarParenteses)
					fezDistributiva=distributiva(posicao);

			}
		}
		
		return juntarParenteses||fezDistributiva;
	}
	
	public void juntarSinalForaParenteses(Posicao posicao)
	{
		if(posicao.inicio>0)
		{
			NoExpression no=lista.get(posicao.inicio-1);
			if(no.tipoNo==TipoNo.Operador&&no.literal.equals("+"))
			{
				lista.remove(no);
			}
			else if(no.tipoNo==TipoNo.Operador&&no.literal.equals("-"))
			{
				lista.remove(no);
				no=lista.get(posicao.inicio-1);
				no.trocaSinal();
			}
		}
	}
	
	public boolean organizar()
	{
		NoExpression no;
		int indexIgual;
		boolean organizou = false;
		for(int i = 0; i < lista.size(); i++)
		{
			no = lista.get(i);
			indexIgual = indexIgual();
			if(no.tipoNo == TipoNo.Literal && i > indexIgual)
			{
				lista.remove(no);
				no.trocaSinal();
				lista.add(indexIgual, no);
				organizou = true;
			}

			if(no.tipoNo == TipoNo.Numero && i < indexIgual)
			{
				lista.remove(no);
				no.trocaSinal();
				lista.add(lista.size(), no);
				i--;
				organizou = true;
			}
		}
		
		if(organizou&&lista.size()==3)
			inverter();
		
		return organizou;
	}
	
	
	public boolean multiplicacao(Posicao posicao)
	{
		NoExpression no,ant,prox;
		boolean multiplicou = false;
		for(int i = posicao.inicio; i < posicao.fim; i++)
		{
			no = lista.get(i);
			if(no.literal.equals("*")&&i>0&&i<lista.size()-1)
			{
				ant=lista.get(i-1);
				prox=lista.get(i+1);
				ant.multiplica(prox);
				lista.remove(no);
				lista.remove(prox);
				posicao.numeroElementos-=2;
				posicao.fim-=2;
				i-=2;
				multiplicou = true;
			}
		}
		return multiplicou;
	}
	
	public boolean juntar(Posicao posicao)
	{
		NoExpression no1, no2;
		boolean juntou = false;
		
		juntou=multiplicacao(posicao);
		
		for(int i = 0; i < posicao.numeroElementos; i++)
		{
			for(int j =i+1; j < posicao.numeroElementos; j++)
			{
				no1 = lista.get(posicao.inicio+i+1);
				no2 = lista.get(posicao.inicio +j+1);
				
				if(no1.igual(no2))
				{
					no1.juntar(no2);
					lista.remove(no2);
					posicao.numeroElementos--;
					posicao.fim--;
//					i--;
					j--;
					juntou = true;
				}
				
			}
		}
		return juntou;
	}
	
	public boolean juntar()
	{
		NoExpression no, prox;
		boolean juntou = false;
		for(int i = 0; i < lista.size() - 1; i++)
		{
			no = lista.get(i);
			prox = lista.get(i + 1);

			if(no.igual(prox))
			{
				no.juntar(prox);
				lista.remove(prox);
				i--;
				juntou = true;
			}
		}
		return juntou;
	}

	public boolean inverter()
	{
		NoExpression literal = lista.get(0);
		NoExpression numero = lista.get(2);
		if(literal.coeficiente.numerador < 0)
		{
			literal.trocaSinal();
			numero.trocaSinal();
			return true;
		}
		return false;
	}

	public boolean isolarLiteral()
	{
		NoExpression literal = lista.get(0);
		NoExpression numero = lista.get(2);
		if(literal.coeficiente.numerador == 1&&literal.coeficiente.denominador==1)
		{
			return false;
		}
		else
		{
			if(literal.coeficiente.denominador==1&&numero.coeficiente.denominador==1)
			{
				numero.coeficiente = numero.coeficiente.div(literal.coeficiente);
				literal.coeficiente.numerador = 1;
				literal.coeficiente.denominador = 1;
				resolucaoLatex +="\\\\" + imprimir();
				
			}
			else
			{
				resolucaoLatex +=
				"\\\\"+literal.literal+" ="+numero.texto(false)+" \\cdot "+literal.coeficiente.inverter().showDfrac();
				numero.coeficiente = numero.coeficiente.div(literal.coeficiente);
				literal.coeficiente.numerador = 1;
				literal.coeficiente.denominador = 1;
				resolucaoLatex +="=" + numero.texto(false);

			}

			return true;
		}
	}

	public String imprimir()
	{
		String string = "";
		NoExpression no;
		int indexIgual = indexIgual();
		boolean comSinal;
		for(int i = 0; i < lista.size(); i++)
		{
			no = lista.get(i);
			comSinal = (i != indexIgual + 1 && i != 0
			&&!lista.get(i-1).literal.equals("*")
			&&!lista.get(i-1).literal.equals("("));
			string += no.texto(comSinal) + " ";
		}
		return string;
	}
	
//	private boolean literalIsolado()
//	{
//		Racional racional=new Racional(1,1);
//		if(lista.get(0).coeficiente.equals(racional))
//			return true;
//		
//		return false;
//	}
	
	private boolean podeSimplificar()
	{
		if(lista.size()==3)
			return true;
		
		return false;
	}
	
	public String imprimirDireita()
	{
		String string = "";
		NoExpression no;
		int indexIgual = indexIgual();
		boolean comSinal;
		for(int i = indexIgual; i < lista.size(); i++)
		{
			no = lista.get(i);
			comSinal = (i != indexIgual + 1 && i != 0
			&&!lista.get(i-1).literal.equals("*")
			&&!lista.get(i-1).literal.equals("("));
			string += no.texto(comSinal) + " ";
		}
		return string;
	}
	
	public String resolverLatex()
	{
		int indexIgual;
		resolucaoLatex = "";

		resolucaoLatex += imprimir();
		
		while(parenteses())
			resolucaoLatex +="\\\\"+(auditoria? "parenteses ":"") + imprimir();
		
		indexIgual = indexIgual();

		if(multiplicacao())
		{
			if(indexIgual==1&&podeSimplificar())
				resolucaoLatex +=""+(auditoria? "multiplicacao ":"") + imprimirDireita();
			else
				resolucaoLatex +="\\\\"+(auditoria? "multiplicacao ":"") + imprimir();
		}
		
		if(organizar())
			resolucaoLatex +="\\\\"+(auditoria? "organizar ":"") + imprimir();

		indexIgual = indexIgual();

		if(juntar())
		{
			if(indexIgual==1&&podeSimplificar())
				resolucaoLatex +=""+(auditoria? "juntar ":"") + imprimirDireita();
			else
				resolucaoLatex +="\\\\"+(auditoria? "juntar ":"") + imprimir();
		}

		if(inverter())
			resolucaoLatex +="\\\\"+(auditoria? "inverter ":"") + imprimir() ;

		isolarLiteral();
//		if(isolarLiteral(graus))
//			resolucaoLatex +="\\\\"+(auditoria? "inverter ":"") + imprimir(graus);

		resultado = lista.get(2);
		resultado.coeficiente.setSimplificou(false);
		resultado.coeficiente.fatoracao(2);
		if(resultado.coeficiente.isSimplificou())
			resolucaoLatex += "=" + resultado.texto(false);

		return resolucaoLatex;
	}
	

	public Racional getResultado()
	{
		return resultado.coeficiente;
	}

	public void setResultado(NoExpression resultado)
	{
		this.resultado = resultado;
	}

	public static void main(String[] args)
	{
		MyExpression expressao = new MyExpression("2/2x=5");
		System.out.println(expressao.imprimir());
		String resolucaoLatex = expressao.resolverLatex();
		System.out.println("-------------");
		System.out.println(resolucaoLatex.replaceAll("\\\\\\\\", "\n"));
	}
}
