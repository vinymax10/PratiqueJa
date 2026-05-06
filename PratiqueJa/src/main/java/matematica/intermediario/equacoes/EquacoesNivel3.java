package matematica.intermediario.equacoes;


import jakarta.persistence.Entity;

import matematica.Racional;
import matematica.expressao.MyExpression;
import modelo.matematica.Conta;

@Entity
public class EquacoesNivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public EquacoesNivel3(int index)
	{
		super(index);
		
		int numerosTermos=5+rand.nextInt(3);
		Racional numeros[]=new Racional [numerosTermos];
		String termos[]=new String[numerosTermos+1];
		String operadores[]=new String[numerosTermos];
		
		for(int i = 0; i < numeros.length; i++)
		{
			numeros[i]=new Racional(1+rand.nextInt(10));
			if(rand.nextBoolean())
				numeros[i].denominador=1+rand.nextInt(10);
			
			termos[i]=numeros[i].toString()+"";
			if(i<numeros.length-1)
				operadores[i]=AuxEquacoes.getOperador();
		}
		String icognita=""+(char)(97+rand.nextInt(122-97));
		int numero=1+rand.nextInt(10);
		String termoIcognita="";
		
		if(numero>1)
			termoIcognita=""+numero+icognita;
		else
			termoIcognita=""+icognita;
		
		termos[numerosTermos]=termoIcognita;

		operadores[numerosTermos-1]="=";
		
		AuxEquacoes.mudaOrdem(termos);
		AuxEquacoes.mudaOrdem(operadores);
		int indexIgual = AuxEquacoes.indexIgual(operadores,"=");
		int indexIncognita = AuxEquacoes.indexIgual(termos,termoIcognita);

		int posParenteses=rand.nextInt(numerosTermos-1);
		while(posParenteses==indexIgual
			||posParenteses==indexIncognita
			||posParenteses==(indexIncognita-1))
			posParenteses=rand.nextInt(numerosTermos-1);
		
		String expressao="";
		for(int i = 0; i < termos.length; i++)
		{
			if(i==posParenteses)
				expressao+="(";
			
			expressao+=termos[i];
			if(i==(posParenteses+1))
				expressao+=")";

			if(i<termos.length-1)
				expressao+=operadores[i];
		}
		
		MyExpression myExpression = new MyExpression(expressao);
		textLatex = myExpression.imprimir();
		textLatex = textLatex.replace("(", "\\left(").replace(")", "\\right)");

		resolucaoLatex=myExpression.resolverLatex();
		resolucaoLatex = resolucaoLatex.replace("(", "\\left(").replace(")", "\\right)");

		resultadoCorreto = "" + myExpression.getResultado().toString();
		pergunta="Encontre \\("+icognita+"\\)";
	}

	public EquacoesNivel3()
	{
	}

	public static void main(String[] args)
	{
		new EquacoesNivel3(1);
	}

}
