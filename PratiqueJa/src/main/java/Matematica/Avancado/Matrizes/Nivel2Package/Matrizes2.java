package Matematica.Avancado.Matrizes.Nivel2Package;

import Matematica.Avancado.Matrizes.AuxMatriz;
import Modelo.Matematica.Conta;


public class Matrizes2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Matrizes2(int indice)
	{
		super(indice);
		int size=2+rand.nextInt(2);

		int [][]a=AuxMatriz.contruirMatriz(1, size, 50);
		int [][]aT=AuxMatriz.contruirTransposta(a);

		pergunta="Qual o resultado de \\(A \\cdot A^T\\)?";
		
		resolucaoLatex="";
		resolucaoLatex+="A \\cdot A^T=";
		resolucaoLatex+=AuxMatriz.matrizStr(a)+"\\cdot"+AuxMatriz.matrizStr(aT)+"=\\\\";
		int resultado=0;
		for(int i = 0; i < size; i++)
		{
			if(a[0][i]>=0)
				resolucaoLatex+=a[0][i]+"^2";
			else
				resolucaoLatex+="("+a[0][i]+")^2";
			if(i<(size-1))
				resolucaoLatex+="+";
		}
		resolucaoLatex+="=";

		for(int i = 0; i < size; i++)
		{
			resolucaoLatex+=a[0][i]*a[0][i];
			resultado+=a[0][i]*a[0][i];
			if(i<(size-1))
				resolucaoLatex+="+";
		}
		resolucaoLatex+="="+resultado;

		resultadoCorreto = resultado+"";

		textLatex="";
		textLatex+="A="+AuxMatriz.matrizStr(a);
		
	}

	public static void main(String[] args)
	{
//		for(int i = 0; i < 100; i++)
//		{
			new Matrizes2(1);
//		}
	}

}
