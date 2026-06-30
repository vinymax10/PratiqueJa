package matematica.avancado.matrizes;

import java.util.Random;

import matematica.DefinicaoCores;
import matematica.Racional;

public class AuxMatriz
{
	public static String parenteses(int x)
	{
		if(x>=0)
			return ""+x;
		
		return "("+x+")";
	}
	
	public static String sinalPos(int x)
	{
		if(x>=0)
			return "+"+x;
		
		return ""+x;
	}
	
	public static String sinalNeg(int x)
	{
		if(x>=0)
			return "-"+x;
		
		return "+"+Math.abs(x);
	}
	
	public static int[][] contruirMatriz(int lin,int col, int limite)
	{
		int [][]matriz=new int[lin][col];
		Random rand=new Random();
		for(int i = 0; i < lin; i++)
		{
			for(int j = 0; j < col; j++)
			{
				matriz[i][j]=rand.nextInt(limite);
				if(rand.nextBoolean())
					matriz[i][j]*=-1;
			}
		}
		return matriz;
	}
	
	public static int[][] contruirTransposta(int[][] matriz)
	{
		int [][]transposta=new int[matriz[0].length][matriz.length];
		for(int i = 0; i < transposta.length; i++)
		{
			for(int j = 0; j < transposta[0].length; j++)
			{
				transposta[i][j]=matriz[j][i];
			}
		}
		return transposta;
	}
	
	public static String formulaInversa()
	{
		String str="A^{-1} = \\dfrac{1}{\\det A} \\cdot ";
		str+="\\begin{bmatrix}";
		str+="a_{2,2}& -a_{1,2}\\\\";
		str+="-a_{2,1}& a_{1,1}";
		str+="\\end{bmatrix}";
		return str;
	}
    
	public static String matrizStr(int [][] matriz)
	{
		String str="\\begin{bmatrix}";
		int lin=matriz.length;
		int col=matriz[0].length;
		
		for(int i = 0; i < lin; i++)
		{
			for(int j = 0; j < col; j++)
			{
				str+=matriz[i][j];
				if(j<(col-1))
					str+="&";
			}
			str+="\\\\";
		}
		str+="\\end{bmatrix}";
		return str;
	}
	
	public static String matrizStrRacional(Racional [][] matriz)
	{
		String str="\\begin{bmatrix}";
		int lin=matriz.length;
		int col=matriz[0].length;
		
		for(int i = 0; i < lin; i++)
		{
			for(int j = 0; j < col; j++)
			{
				str+=matriz[i][j].showDfrac();
				if(j<(col-1))
					str+="&";
			}
			str+="\\\\";
		}
		str+="\\end{bmatrix}";
		return str;
	}
	
	public static String matrizStr(int [][] matriz, int posI, int posJ)
	{
		String str=DefinicaoCores.irisBabypink();
		str+="\\begin{bmatrix}";
		int lin=matriz.length;
		int col=matriz[0].length;
		
		for(int i = 0; i < lin; i++)
		{
			for(int j = 0; j < col; j++)
			{
				if(i==posI&&j==posJ)
					str+="\\textcolor{iris}{"+matriz[i][j]+"}";
				else
					str+=matriz[i][j];
				if(j<(col-1))
					str+="&";
			}
			str+="\\\\";
		}
		str+="\\end{bmatrix}";
		return str;
	}
	
	public static int soma(int [][]matriz)
	{
		int soma=0;
		int lin=matriz.length;
		int col=matriz[0].length;
		
		for(int i = 0; i < lin; i++)
		{
			for(int j = 0; j < col; j++)
			{
				soma+=matriz[i][j];
			}
		}
		return soma;
	}
	
	public static Racional somaRacional(Racional [][]matriz)
	{
		Racional somaRacional=new Racional(0);
		int lin=matriz.length;
		int col=matriz[0].length;
		
		for(int i = 0; i < lin; i++)
		{
			for(int j = 0; j < col; j++)
			{
				somaRacional=somaRacional.add(matriz[i][j]);
				somaRacional.fatoracao(2);
			}
		}
		return somaRacional;
	}
	
	public static String parcialMult(int [][] a, int [][] b, int posI, int posJ)
	{
		String str="c_{"+(posI+1)+","+(posJ+1)+"}=";
		int size=a.length;
		int resultado=0;
		for(int i = 0; i < size; i++)
		{
			String aStr = (i>0 && a[posI][i]<0) ? "("+a[posI][i]+")" : ""+a[posI][i];
			if(b[i][posJ]<0)
				str+=aStr+" \\cdot ("+b[i][posJ]+")";
			else
				str+=aStr+" \\cdot "+b[i][posJ];

			resultado+=a[posI][i]*b[i][posJ];
			if(i<(size-1))
				str+="+";
		}
		str+="\\\\c_{"+(posI+1)+","+(posJ+1)+"}=";
		for(int i = 0; i < size; i++)
		{
			if(i>0&&(a[posI][i]*b[i][posJ])>=0)
				str+="+";

			str+=(a[posI][i]*b[i][posJ])+"";
		}
		str+="="+resultado;
		return str;
	}
	
	public static String somaStr(int [][]matriz)
	{
		String str="";
		int lin=matriz.length;
		int col=matriz[0].length;
		
		str+="\\text{Soma}=";
		int cont=0;
		int total=lin*col;
		for(int i = 0; i < lin; i++)
		{
			for(int j = 0; j < col; j++)
			{
				if((i!=0||j!=0)&&matriz[i][j]>=0)
					str+="+";
				
				str+=matriz[i][j];
				cont++;
				if((total==9&&cont==5)||(total==6&&cont==3))
					str+="\\\\";
			}
		}
		str+="=\\mathbf{"+soma(matriz)+"}";
		return str;
	}
	
	public static String somaStrRacional(Racional [][]matriz)
	{
		String str="";
		int lin=matriz.length;
		int col=matriz[0].length;
		
		str+="\\text{Soma}=";
		int cont=0;
		int total=lin*col;
		for(int i = 0; i < lin; i++)
		{
			for(int j = 0; j < col; j++)
			{
				str+=matriz[i][j].sinal((i==0&&j==0))+matriz[i][j].showDfracSS();
				
				cont++;
				if((total==9&&cont==5)||(total==6&&cont==3))
					str+="\\\\";
			}
		}
		str+="=\\mathbf{"+somaRacional(matriz).showDfrac()+"}";
		return str;
	}
	
	public static int determinante(int [][]matriz)
	{
		if(matriz.length==2)
			return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
		else
			return  matriz[0][0] * (matriz[1][1] * matriz[2][2] - matriz[1][2] * matriz[2][1])
            - matriz[0][1] * (matriz[1][0] * matriz[2][2] - matriz[1][2] * matriz[2][0])
            + matriz[0][2] * (matriz[1][0] * matriz[2][1] - matriz[1][1] * matriz[2][0]);
	}
}
