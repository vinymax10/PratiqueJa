package matematica.avancado.matrizes.nivel2package;

import matematica.ParCor;
import matematica.avancado.matrizes.AuxMatriz;
import modelo.matematica.Conta;


public class Matrizes1 extends Conta
{
	private static final long serialVersionUID = 1L;

//	multiplicação parcial
	public Matrizes1(int indice)
	{
		super(indice);
		int size=2+rand.nextInt(2);

		int [][]a=AuxMatriz.contruirMatriz(size, size, 50);
		int [][]b=AuxMatriz.contruirMatriz(size, size, 50);
		int posI=rand.nextInt(size);
		int posJ=rand.nextInt(size);
		
		pergunta="Se \\(A \\cdot B=C\\), qual o valor de \\(c_{"+(posI+1)+","+(posJ+1)+"}\\)?";
		
		int c[][]=new int [size][size];
		for(int i = 0; i < c.length; i++)
		{
			for(int j = 0; j < c.length; j++)
			{
				c[i][j]=0;
				for(int k = 0; k < size; k++)
				{
					c[i][j]+=a[i][k]*b[k][j];
				}
			}
		}
		
		resultadoCorreto = c[posI][posJ]+"";
		
		resolucaoLatex=ParCor.formula("c_{i,j}= \\sum_{1}^{k} A_{i,k} \\cdot B_{k,j}")+"\\\\";
		resolucaoLatex+="i="+(posI+1)+", \\quad j="+(posJ+1)+", \\quad k="+size+"\\\\";
		
		resolucaoLatex+="c_{"+(posI+1)+","+(posJ+1)+"}=";
		for(int i = 0; i < size; i++)
		{
			resolucaoLatex+=AuxMatriz.parenteses(a[posI][i])+"\\cdot"+AuxMatriz.parenteses(b[i][posJ]);
			if(i<(size-1))
				resolucaoLatex+="+";
		}
		resolucaoLatex+="\\\\";
		resolucaoLatex+="c_{"+(posI+1)+","+(posJ+1)+"}=";
		
		
		for(int i = 0; i < size; i++)
		{
			if(i>0&&(a[posI][i]*b[i][posJ])>=0)
				resolucaoLatex+="+";

			resolucaoLatex+=a[posI][i]*b[i][posJ];
		}
		resolucaoLatex+="="+resultadoCorreto;
//		resolucaoLatex+="C="+AuxMatriz.matrizStr(c,posI,posJ);
//		resolucaoLatex+="\\\\ \\\\ c_{"+(posI+1)+","+(posJ+1)+"}="+resultadoCorreto;

		if(size==3)
			sizeFontTextLatex="\\scriptsize";
		
		textLatex="";
		textLatex+="A="+AuxMatriz.matrizStr(a);
		textLatex+=",~B="+AuxMatriz.matrizStr(b);
		
	}

	public static void main(String[] args)
	{
//		for(int i = 0; i < 100; i++)
//		{
			new Matrizes1(1);
//		}
	}

}
