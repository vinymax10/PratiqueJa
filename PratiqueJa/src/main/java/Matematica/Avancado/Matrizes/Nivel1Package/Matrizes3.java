package Matematica.Avancado.Matrizes.Nivel1Package;

import Matematica.Avancado.Matrizes.AuxMatriz;
import Modelo.Matematica.Conta;


public class Matrizes3 extends Conta
{
	private static final long serialVersionUID = 1L;

//	Multiplicação por escalar
	public Matrizes3(int indice)
	{
		super(indice);
		int lin=2+rand.nextInt(2);
		int col=2+rand.nextInt(2);

		int escalar=2+rand.nextInt(10);
		if(rand.nextBoolean())
			escalar*=-1;
		
		int [][]a=AuxMatriz.contruirMatriz(lin, col, 50);
		
		pergunta="Se \\("+escalar+" \\cdot A=B\\), qual a soma dos elementos de \\(B\\)?";
		
		int b[][]=new int [lin][col];
		for(int i = 0; i < lin; i++)
		{
			for(int j = 0; j < col; j++)
			{
				b[i][j]=a[i][j]*escalar;
			}
		}
		
		resultadoCorreto = AuxMatriz.soma(b)+"";
		
		resolucaoLatex="B=\\begin{bmatrix}";
		
		for(int i = 0; i < lin; i++)
		{
			for(int j = 0; j < col; j++)
			{
				resolucaoLatex+=escalar+" \\cdot "+AuxMatriz.parenteses(a[i][j]);

				if(j<(col-1))
					resolucaoLatex+="&";
			}
			resolucaoLatex+="\\\\";
		}
		
		resolucaoLatex+="\\end{bmatrix}"+"\\\\ \\\\";
		resolucaoLatex+="B="+AuxMatriz.matrizStr(b)+"\\\\ \\\\";
		resolucaoLatex+=AuxMatriz.somaStr(b);

//		System.out.println(pergunta);
		
		textLatex="";
		textLatex+="A="+AuxMatriz.matrizStr(a);
		
	}

	public static void main(String[] args)
	{
//		for(int i = 0; i < 100; i++)
//		{
			new Matrizes3(1);
//		}
	}

}
