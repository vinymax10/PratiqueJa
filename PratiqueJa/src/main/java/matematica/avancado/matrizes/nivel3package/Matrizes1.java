package matematica.avancado.matrizes.nivel3package;

import matematica.ParCor;
import matematica.avancado.matrizes.AuxMatriz;
import modelo.matematica.Conta;


public class Matrizes1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Matrizes1(int indice)
	{
		super(indice);
		int size=2;

		int [][]a=AuxMatriz.contruirMatriz(size, size, 50);
		int [][]b=AuxMatriz.contruirMatriz(size, size, 50);
		
		pergunta="Se \\(A \\cdot B=C\\), qual a soma dos elementos de \\(C\\)?";
		
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
		
		resultadoCorreto = AuxMatriz.soma(c)+"";
		
		resolucaoLatex=ParCor.formula("c_{i,j}= \\sum_{1}^{k} A_{i,k} \\cdot B_{k,j}")+"\\\\";
		resolucaoLatex+="i, j \\in \\{1,2\\}, \\quad k="+size+"\\\\";

		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < c.length; j++)
			{
				resolucaoLatex+=AuxMatriz.parcialMult(a,b,i,j)+"\\\\";
			}
		}
		
		resolucaoLatex+=AuxMatriz.somaStr(c);
		
//		resolucaoLatex+="C="+AuxMatriz.matrizStr(c,posI,posJ);
//		resolucaoLatex+="\\\\ \\\\ c_{"+(posI+1)+","+(posJ+1)+"}="+resultadoCorreto;

//		System.out.println(pergunta);
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
