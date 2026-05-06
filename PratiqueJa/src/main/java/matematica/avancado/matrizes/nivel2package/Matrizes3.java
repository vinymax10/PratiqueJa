package matematica.avancado.matrizes.nivel2package;

import matematica.avancado.matrizes.AuxMatriz;
import modelo.matematica.Conta;


public class Matrizes3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Matrizes3(int indice)
	{
		super(indice);
		int size=2+rand.nextInt(2);

		int [][]a=AuxMatriz.contruirMatriz(1, size, 50);
		int [][]b=AuxMatriz.contruirMatriz(size, 1, 50);

		pergunta="Qual o resultado de \\(A \\cdot B\\)?";
		
		resolucaoLatex="";
		resolucaoLatex+="A \\cdot B=";
		resolucaoLatex+=AuxMatriz.matrizStr(a)+"\\cdot"+AuxMatriz.matrizStr(b)+"=\\\\ \\\\";
		int resultado=0;
		for(int i = 0; i < size; i++)
		{
			resolucaoLatex+=AuxMatriz.parenteses(a[0][i])+"\\cdot"+AuxMatriz.parenteses(b[i][0]);
			
			if(i<(size-1))
				resolucaoLatex+="+";
		}
		resolucaoLatex+="=\\\\";

		for(int i = 0; i < size; i++)
		{
			if(i>0&&a[0][i]*b[i][0]>=0)
				resolucaoLatex+="+";

			resolucaoLatex+=a[0][i]*b[i][0];
			resultado+=a[0][i]*b[i][0];
		}
		resolucaoLatex+="="+resultado;

		resultadoCorreto = resultado+"";

		textLatex="";
		textLatex+="A="+AuxMatriz.matrizStr(a);
		textLatex+=",~B="+AuxMatriz.matrizStr(b);

	}

	public static void main(String[] args)
	{
//		for(int i = 0; i < 100; i++)
//		{
			new Matrizes3(1);
//		}
	}

}
