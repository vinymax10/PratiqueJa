package matematica.basico.resolucaonatural;

import matematica.DefinicaoCores;

public class Adicao 
{
	Numero vaiUm;
	Numero primeiro;
	Numero segundo;
	Numero resultado;
	int numeroTermos,size;
	String simbolo;
	
	public String adicionar(int a, int b, boolean resolucao)
	{
		primeiro=new Numero(a);
		segundo=new Numero(b);
		resultado=new Numero(a+b);
		vaiUm=new Numero(0);
		
		numeroTermos=Math.max(primeiro.size(), segundo.size());
		size=Math.max(numeroTermos, resultado.size());

		int soma;
		for (int i = 0; i < numeroTermos; i++) 
		{
			soma=primeiro.getDigito(i)+segundo.getDigito(i)+vaiUm.getDigito(i);
			if(soma>9)
				vaiUm.add(soma/10);
			else 
				vaiUm.add(0);
		}
		simbolo="+";
		
		return latex(resolucao);
	}
	
	public String latex(boolean resolucao) 
	{
		String resolucaoLatex=DefinicaoCores.irisBabypink();
		resolucaoLatex += "\\begin{array}[t]{";
		for (int i = 0; i < size+1; i++) 
			resolucaoLatex+="r";
		
		resolucaoLatex+="}";
		
		if(resolucao&&vaiUm.contemAlgumValor())
			resolucaoLatex+= vaiUm.latexSomaVaiUm(size+1);
		
		resolucaoLatex+= primeiro.latexDir(size+1);
		resolucaoLatex+=simbolo+ segundo.latexDir(size+1);
		resolucaoLatex+="\\hline";
		
		if(resolucao)
			resolucaoLatex+= resultado.latexDir(size+1);
		
		resolucaoLatex+="\\end{array}";
		
		return resolucaoLatex;
	}
	
	@Override
	public String toString() {
		return "Operacao [vaiUm=" + vaiUm + ", primeiro=" + primeiro + ", segundo=" + segundo + "]";
	}

	public static void main(String[] args) 
	{
	}
}
