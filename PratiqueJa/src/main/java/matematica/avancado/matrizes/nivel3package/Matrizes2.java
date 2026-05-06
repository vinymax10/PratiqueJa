package matematica.avancado.matrizes.nivel3package;

import matematica.ParCor;
import matematica.avancado.matrizes.AuxMatriz;
import modelo.matematica.Conta;


public class Matrizes2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Matrizes2(int indice)
	{
		super(indice);
		int size=2+rand.nextInt(2);

		int [][]a=AuxMatriz.contruirMatriz(size, size, 20);
		
		pergunta="Qual o \\( \\det~ A \\)?";
		
		resultadoCorreto = AuxMatriz.determinante(a)+"";
		
		resolucaoLatex="";
		if(size==2)
		{
			resolucaoLatex+=ParCor.formula("\\det~ A = a_{1,1} \\cdot a_{2,2} - a_{1,2} \\cdot a_{2,1}")+"\\\\";
			resolucaoLatex+="\\det~ A = "+a[0][0]+" \\cdot "+ AuxMatriz.parenteses(a[1][1])+ 
			"-"+ AuxMatriz.parenteses(a[0][1]) +" \\cdot "+ AuxMatriz.parenteses(a[1][0]) +"\\\\";
			
			resolucaoLatex+="\\det~ A ="+(a[0][0] * a[1][1]) +"-"+ AuxMatriz.parenteses(a[0][1] * a[1][0])+"="+resultadoCorreto;
		}
		else
		{
			resolucaoLatex+=ParCor.formula("\\det~ A = sd_p - sd_s")+"\\\\";

			resolucaoLatex+="\\text{Soma das diagonais principais } sd_p: \\\\";
			
			resolucaoLatex+="sd_p="+
			a[0][0]+" \\cdot "+ AuxMatriz.parenteses(a[1][1])+ " \\cdot "+ AuxMatriz.parenteses(a[2][2])+"+"+
			AuxMatriz.parenteses(a[0][1])+" \\cdot "+ AuxMatriz.parenteses(a[1][2])+ " \\cdot "+ AuxMatriz.parenteses(a[2][0])+"+"+
			AuxMatriz.parenteses(a[0][2])+" \\cdot "+ AuxMatriz.parenteses(a[1][0])+ " \\cdot "+ AuxMatriz.parenteses(a[2][1])+"\\\\";
			
			int sdp=((a[0][0]*a[1][1]*a[2][2])+(a[0][1]*a[1][2]*a[2][0])+(a[0][2]*a[1][0]*a[2][1]));
			
			resolucaoLatex+="sd_p="+
			(a[0][0]*a[1][1]*a[2][2])+ 
			AuxMatriz.sinalPos(a[0][1]*a[1][2]*a[2][0])+
			AuxMatriz.sinalPos(a[0][2]*a[1][0]*a[2][1])+
			"="+sdp+"\\\\";
			
			resolucaoLatex+="\\text{Soma das diagonais secundárias } sd_s:\\\\";
			resolucaoLatex+="sd_s="+

			a[0][2]+" \\cdot "+ AuxMatriz.parenteses(a[1][1])+ " \\cdot "+ AuxMatriz.parenteses(a[2][0])
			+"+"+AuxMatriz.parenteses(a[0][0])+" \\cdot "+ AuxMatriz.parenteses(a[1][2])+ " \\cdot "+ AuxMatriz.parenteses(a[2][1])
			+"+"+AuxMatriz.parenteses(a[0][1])+" \\cdot "+ AuxMatriz.parenteses(a[1][0])+ " \\cdot "+ AuxMatriz.parenteses(a[2][2])+"\\\\";
			
			int sds=((a[0][2]*a[1][1]*a[2][0])+(a[0][0]*a[1][2]*a[2][1])+(a[0][1]*a[1][0]*a[2][2]));
			
			resolucaoLatex+="sd_s="+
			(a[0][2]*a[1][1]*a[2][0])+
			AuxMatriz.sinalPos(a[0][0]*a[1][2]*a[2][1])+
			AuxMatriz.sinalPos(a[0][1]*a[1][0]*a[2][2])+
			"="+sds+"\\\\";
			
			resolucaoLatex+="\\det~ A = "+sdp+"-"+AuxMatriz.parenteses(sds)+"="+resultadoCorreto;
	
		}
		
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
