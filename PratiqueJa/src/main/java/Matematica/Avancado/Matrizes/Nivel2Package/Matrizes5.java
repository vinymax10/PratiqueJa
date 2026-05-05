package Matematica.Avancado.Matrizes.Nivel2Package;

import Matematica.Avancado.Matrizes.AuxMatriz;
import Modelo.Matematica.Conta;


public class Matrizes5 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Matrizes5(int indice)
	{
		super(indice);
		int size=2+rand.nextInt(2);

		int [][]a=AuxMatriz.contruirMatriz(size, size, 50);
		int [][]b=AuxMatriz.contruirMatriz(size, size, 50);
		int [][]bT=AuxMatriz.contruirTransposta(b);

		pergunta="Se \\(A-B^T=C\\), qual a soma dos elementos de \\(C\\)?";
		
		int c[][]=new int [size][size];
		for(int i = 0; i < c.length; i++)
		{
			for(int j = 0; j < c.length; j++)
			{
				c[i][j]=a[i][j]-bT[i][j];
			}
		}
		
		resultadoCorreto = AuxMatriz.soma(c)+"";
		resolucaoLatex="";
		resolucaoLatex+="B^T="+AuxMatriz.matrizStr(bT)+"\\\\";
		
		resolucaoLatex+="C=\\begin{bmatrix}";
		int lin=size;
		int col=size;
		
		for(int i = 0; i < lin; i++)
		{
			for(int j = 0; j < col; j++)
			{
				resolucaoLatex+=a[i][j]+"-"+AuxMatriz.parenteses(bT[i][j]);

				if(j<(col-1))
					resolucaoLatex+="&";
			}
			resolucaoLatex+="\\\\";
		}
		resolucaoLatex+="\\end{bmatrix}"+"\\\\ \\\\";
		resolucaoLatex+="C="+AuxMatriz.matrizStr(c)+"\\\\ \\\\";
		resolucaoLatex+=AuxMatriz.somaStr(c);

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
			new Matrizes5(1);
//		}
	}

}
