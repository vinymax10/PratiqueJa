package matematica.avancado.matrizes.nivel1package;

import matematica.avancado.matrizes.AuxMatriz;
import modelo.matematica.Conta;


public class Matrizes2 extends Conta
{
	private static final long serialVersionUID = 1L;

//	Subtração de matrizes
	public Matrizes2(int indice)
	{
		super(indice);
		int lin=2+rand.nextInt(2);
		int col=2+rand.nextInt(2);

		int [][]a=AuxMatriz.contruirMatriz(lin, col, 50);
		int [][]b=AuxMatriz.contruirMatriz(lin, col, 50);
		
		pergunta="Se \\(A-B=C\\), qual a soma dos elementos de \\(C\\)?";
		
		int c[][]=new int [lin][col];
		for(int i = 0; i < lin; i++)
		{
			for(int j = 0; j < col; j++)
			{
				c[i][j]=a[i][j]-b[i][j];
			}
		}
		
		resultadoCorreto = AuxMatriz.soma(c)+"";
		
		resolucaoLatex="C=\\begin{bmatrix}";
		
		for(int i = 0; i < lin; i++)
		{
			for(int j = 0; j < col; j++)
			{
				resolucaoLatex+=a[i][j]+"-"+AuxMatriz.parenteses(b[i][j]);

				if(j<(col-1))
					resolucaoLatex+="&";
			}
			resolucaoLatex+="\\\\";
		}
		resolucaoLatex+="\\end{bmatrix}"+"\\\\ \\\\";
		resolucaoLatex+="C="+AuxMatriz.matrizStr(c)+"\\\\ \\\\";
		resolucaoLatex+=AuxMatriz.somaStr(c);

		if(lin==3)
			sizeFontTextLatex="\\scriptsize";
		
		textLatex="";
		textLatex+="A="+AuxMatriz.matrizStr(a);
		textLatex+=",~B="+AuxMatriz.matrizStr(b);
		
	}

	public static void main(String[] args)
	{
//		for(int i = 0; i < 100; i++)
//		{
			new Matrizes2(1);
//		}
	}

}
