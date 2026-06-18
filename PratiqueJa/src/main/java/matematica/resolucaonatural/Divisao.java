package matematica.resolucaonatural;

import java.util.ArrayList;
import java.util.List;

import matematica.DefinicaoCores;

public class Divisao 
{
	Numero dividendo;
	Numero divisor;
	Numero resultado;
	List<Numero> subtraendos;
	List<Numero> diferencas;

	int numeroTermos,size;
	String simbolo;
	int posicaoDividendo;
	int intDivisor,intDividendo;
	
	public String dividir(int intDividendo, int intDivisor, boolean resolucao)
	{
		this.intDivisor=intDivisor;
		this.intDividendo=intDividendo;
		dividendo=new Numero(intDividendo);
		divisor=new Numero(intDivisor);
		resultado=new Numero(intDividendo/intDivisor);
		simbolo="-";
		
		subtraendos=new ArrayList<Numero>();
		diferencas=new ArrayList<Numero>();

		numeroTermos=Math.max(dividendo.size(), divisor.size());
		size=Math.max(numeroTermos, resultado.size());
		Numero diferenca=null,subtraendo;
		int parteDividendo,intDiferenca,inicioValido=0;
		
		if(resolucao)
		{
			int intSubtraendo;

			diferenca=parteDividendoNumero(intDivisor);
			parteDividendo=diferenca.inteiro();
			int cont=0;
			for (int i = 0; i < resultado.size(); i++) 
			{
				parteDividendo=diferenca.inteiro();

				intSubtraendo=resultado.getTermos().get(i).digito*intDivisor;
				
				subtraendo=new Numero(intSubtraendo,
				inicioValido,posicaoDividendo,true);
				subtraendos.add(subtraendo);
				
				subtracao(diferenca,subtraendo);
				
				intDiferenca=parteDividendo-intSubtraendo;
				if(i>0)
					inicioValido=diferencas.get(diferencas.size()-1).posicaoPrimeiroDigitoValido();
					
				diferenca=new Numero(intDiferenca, inicioValido, posicaoDividendo, false);
				
				cont=0;
				while(diferenca.inteiro()<intDivisor&&posicaoDividendo<dividendo.size())
				{
					diferenca.addDir(dividendo.termos.get(posicaoDividendo++).digito);
					cont++;
				}
				
				if(cont>1)
					i+=(cont-1);
				
				diferencas.add(diferenca);

				if(diferenca.inteiro()==0)
					break;
			}
		}
		
		return latex(resolucao);
	}
	
	private void subtracao(Numero primeiro, Numero segundo)
	{
		int index;
		for (int i = 0; i < primeiro.size(); i++) 
		{
			if(segundo.getDigito(i)>primeiro.getDigito(i))
			{
				primeiro.add(10,i);
				index=i+1;
				while(!primeiro.emprestaUm(index++));
			}
		}
	}
	
	private Numero parteDividendoNumero(int divisor)
	{
		posicaoDividendo=0;
		Numero numero=new Numero();
		do
		{
			numero.termos.add(dividendo.termos.get(posicaoDividendo++));
		}
		while(numero.inteiro()<divisor);
		
		return numero;
	}
	
	public String latex(boolean resolucao) 
	{
		String resolucaoLatex=DefinicaoCores.irisBabypink();
//		String subtraendo;
		resolucaoLatex += "\\begin{array}[t]{";
		for (int i = 0; i < size+3; i++) 
			resolucaoLatex+="l";
		
		resolucaoLatex+="}";
		
		resolucaoLatex+= dividendo.latexDir(size+1).replaceAll("\\\\\\\\", "")+"& & \\begin{array}[t]{|l}"+intDivisor+" \\\\ \\hline \\end{array} \\\\";
		for (int i = 0; i < subtraendos.size(); i++) 
		{
			resolucaoLatex+=simbolo+subtraendos.get(i).latexEsq(size+1).replaceAll("\\\\\\\\", "");
			
			if(i==0)
				resolucaoLatex+="&&"+(intDividendo/intDivisor);
			
			resolucaoLatex+="\\\\";
				
			resolucaoLatex+="\\hline";
			resolucaoLatex+= diferencas.get(i).latexEsq(size+1);
		}
		
		resolucaoLatex+="\\end{array}";

		return resolucaoLatex;
	}

	public static void main(String[] args)
	{
		Divisao d = new Divisao();
		System.out.println("=== Sem empréstimo: 96 / 3 ===");
		System.out.println(d.dividir(96, 3, true));
		System.out.println();
		System.out.println("=== Com empréstimo: 85 / 5 ===");
		System.out.println(d.dividir(85, 5, true));
		System.out.println();
		System.out.println("=== Empréstimo encadeado: 100 / 4 ===");
		System.out.println(d.dividir(100, 4, true));
		System.out.println();
		System.out.println("=== Empréstimo encadeado: 504 / 7 ===");
		System.out.println(d.dividir(504, 7, true));
	}

}
