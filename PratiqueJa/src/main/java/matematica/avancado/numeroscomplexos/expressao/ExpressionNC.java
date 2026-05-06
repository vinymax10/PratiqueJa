package matematica.avancado.numeroscomplexos.expressao;

import java.util.ArrayList;
import java.util.List;

import matematica.Racional;


public class ExpressionNC
{
	String expressoes;
	List<NoExpressionNC> lista = new ArrayList<NoExpressionNC>();
	boolean juntarParenteses;
	boolean fezDistributiva;
	String resolucaoLatex;
	NoExpressionNC resultado;
	boolean auditoria=false;
	
	public ExpressionNC(String expressoes)
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
				lista.add(new NoExpressionNC(string));
				string = "";
			}
			string += caracter;
		}
		lista.add(new NoExpressionNC(string));
	}

	public boolean multiplicacao()
	{
		NoExpressionNC no,ant,prox;
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
	
	public boolean convertI2()
	{
		NoExpressionNC no;
		boolean converteu = false;
		for(int i = 0; i < lista.size(); i++)
		{
			no = lista.get(i);
			if(no.literal.equals("i^2"))
			{
				no.literal="";
				no.tipoNo=TipoNo.Numero;
				lista.add(new NoExpressionNC("*"));
				NoExpressionNC menos1= new NoExpressionNC("-1");
				lista.add(menos1);
//				lista.add(new NoExpressionNC("1"));
				converteu=true;
			}
		}
		return converteu;
	}
	
	private void removerParenteses(Posicao posicao)
	{
		NoExpressionNC no;
		
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
		&&(lista.get(posicao.inicio-2).tipoNo==TipoNo.Numero||lista.get(posicao.inicio-2).tipoNo==TipoNo.Literal))
		{
			NoExpressionNC noMultiplica=lista.get(posicao.inicio-2);
			int iteracoes=posicao.numeroElementos;
			NoExpressionNC no;
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
			NoExpressionNC noMultiplica=lista.get(posicao.fim+2);
			int iteracoes=posicao.numeroElementos;
			NoExpressionNC no;
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
			
		NoExpressionNC no;
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
						NoExpressionNC node=lista.get(posicao.inicio-1);
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
			NoExpressionNC no=lista.get(posicao.inicio-1);
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
		NoExpressionNC no;
		boolean organizou = false;
		int limite=lista.size();
		
		for(int i = 0; i < limite; i++)
		{
			no = lista.get(i);
			if(no.tipoNo == TipoNo.Literal )
			{
				lista.remove(no);
				lista.add(lista.size(), no);
				organizou = true;
				i--;
				limite--;
			}
		}
		
		return organizou;
	}
	
	
	public boolean multiplicacao(Posicao posicao)
	{
		NoExpressionNC no,ant,prox;
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
		NoExpressionNC no1, no2;
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
		NoExpressionNC no, prox;
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
		removerZeros();
		return juntou;
	}
	
	public void removerZeros()
	{
		NoExpressionNC no;
		for(int i = 0; i < lista.size(); i++)
		{
			no = lista.get(i);
			if(no.coeficiente.isZero())
			{
				lista.remove(no);
				i--;
			}
		}
	}

	public boolean inverter()
	{
		NoExpressionNC literal = lista.get(0);
		NoExpressionNC numero = lista.get(2);
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
		NoExpressionNC literal = lista.get(0);
		NoExpressionNC numero = lista.get(2);
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
		NoExpressionNC no;
		boolean comSinal;
		for(int i = 0; i < lista.size(); i++)
		{
			no = lista.get(i);
			comSinal = (i != 0
			&&!lista.get(i-1).literal.equals("*")
			&&!lista.get(i-1).literal.equals("("));
			string += no.texto(comSinal) + " ";
		}
		
		return string;
	}
	
	public void imprimirNos()
	{
		String string = "";
		NoExpressionNC no;
		for(int i = 0; i < lista.size(); i++)
		{
			no = lista.get(i);
			string += no.toString() + "\n";
		}
	}
	
//	private boolean literalIsolado()
//	{
//		Racional racional=new Racional(1,1);
//		if(lista.get(0).coeficiente.equals(racional))
//			return true;
//		
//		return false;
//	}
	
	public String resolverLatex()
	{
		resolucaoLatex = "";
		removerZeros();
		
		resolucaoLatex += imprimir();
		
		while(parenteses())
			resolucaoLatex +="=\\\\"+(auditoria? "parenteses ":"") + imprimir();
		
		if(convertI2())
			resolucaoLatex +="=\\\\"+(auditoria? "convertI2 ":"") + imprimir();
		
		if(multiplicacao())
			resolucaoLatex +="=\\\\"+(auditoria? "multiplicacao ":"") + imprimir();
		
		if(organizar())
		{
			if(lista.size()<=2)
				resolucaoLatex +="="+(auditoria? "organizar ":"") + imprimir();
			else
				resolucaoLatex +="=\\\\"+(auditoria? "organizar ":"") + imprimir();
		}

		if(juntar())
			resolucaoLatex +="="+(auditoria? "juntar ":"") + imprimir();

		return resolucaoLatex;
	}
	

	public Racional getResultado()
	{
		return resultado.coeficiente;
	}

	public void setResultado(NoExpressionNC resultado)
	{
		this.resultado = resultado;
	}

	public static void main(String[] args)
	{
		ExpressionNC expressao = new ExpressionNC("1/2+6/8-5i");
		System.out.println(expressao.imprimir());
		String resolucaoLatex = expressao.resolverLatex();
		System.out.println("-------------");
		System.out.println(resolucaoLatex.replaceAll("\\\\\\\\", "\n"));
	}
}
