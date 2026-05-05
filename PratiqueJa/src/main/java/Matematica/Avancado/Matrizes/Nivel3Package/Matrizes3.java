package Matematica.Avancado.Matrizes.Nivel3Package;

import Matematica.ParCor;
import Matematica.Racional;
import Matematica.Avancado.Matrizes.AuxMatriz;
import Modelo.Matematica.Conta;


public class Matrizes3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Matrizes3(int indice)
	{
		super(indice);
		int size=2;

		int [][]a=AuxMatriz.contruirMatriz(size, size, 20);
		int determinanteA = AuxMatriz.determinante(a);
		
		while(determinanteA==0)
		{
			a=AuxMatriz.contruirMatriz(size, size, 20);
			determinanteA = AuxMatriz.determinante(a);
		}
		
		int [][]parcialInversa= {{a[1][1],-a[0][1]},
								 {-a[1][0],a[0][0]}};
		
		pergunta="Qual a soma dos elementos de \\( A^{-1} \\)?";

		Racional inversaA[][]=new Racional [size][size];
		Racional fator=new Racional(1,determinanteA);
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				inversaA[i][j]=fator.mult(new Racional(parcialInversa[i][j]));
				inversaA[i][j].fatoracao(2);
			}
		}
		
		resultadoCorreto = AuxMatriz.somaRacional(inversaA)+"";
		
		resolucaoLatex="";
		resolucaoLatex+=ParCor.formula(AuxMatriz.formulaInversa())+" \\\\";
		resolucaoLatex+=ParCor.formula("\\det~ A = a_{1,1} \\cdot a_{2,2} - a_{1,2} \\cdot a_{2,1}")+"\\\\";

		resolucaoLatex+="\\det~ A = "+a[0][0]+" \\cdot "+ AuxMatriz.parenteses(a[1][1])+ 
		"-"+ AuxMatriz.parenteses(a[0][1]) +" \\cdot "+ AuxMatriz.parenteses(a[1][0]) +"\\\\";
		resolucaoLatex+="\\det~ A ="+(a[0][0] * a[1][1]) +"-"+ AuxMatriz.parenteses(a[0][1] * a[1][0])+"="+determinanteA+"\\\\";

		resolucaoLatex+="A^{-1} ="+ fator.showDfrac() +"\\cdot "+ AuxMatriz.matrizStr(parcialInversa)+"\\\\";
		
		resolucaoLatex+="A^{-1}=\\begin{bmatrix}";
		
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				resolucaoLatex+=fator.showDfrac()+" \\cdot "+AuxMatriz.parenteses(parcialInversa[i][j]);

				if(j<(size-1))
					resolucaoLatex+="&";
			}
			resolucaoLatex+="\\\\";
		}
		
		resolucaoLatex+="\\end{bmatrix}"+"\\\\ \\\\";
		
		resolucaoLatex+="A^{-1}="+AuxMatriz.matrizStrRacional(inversaA)+"\\\\ \\\\";
		resolucaoLatex+=AuxMatriz.somaStrRacional(inversaA);

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
