package matematica.basico.divisibilidade;

import java.util.ArrayList;
import java.util.List;

import matematica.DefinicaoCores;

public class ResolucaoDivisores
{
	List<Integer> lista = new ArrayList<Integer>();

	public String[] numerosDividores(int number)
	{
		List<String> passos = new ArrayList<String>();
		passos.add(fatoresPrimos(number));
		String listDivisores = "";
		for(int i = 0; i < lista.size(); i++)
		{
			Integer integer = lista.get(i);
			listDivisores += integer + "";
			if(i < lista.size() - 1)
				listDivisores += ",~";
		}
		passos.add("Divisores: \\(" + listDivisores + "\\)");
		passos.add("Número de divisores: \\(" + lista.size() + "\\)");
		return passos.toArray(new String[0]);
	}

	public String[] somaDivisores(int number)
	{
		List<String> passos = new ArrayList<String>();
		passos.add(fatoresPrimos(number));
		passos.add("Soma dos divisores:");
		int soma = 0;
		String listDivisores = "";
		for(int i = 0; i < lista.size(); i++)
		{
			Integer integer = lista.get(i);
			soma += integer;
			listDivisores += integer + "";
			if(i < lista.size() - 1)
				listDivisores += "+";
		}
		passos.add("\\(" + listDivisores + " = " + soma + "\\)");
		return passos.toArray(new String[0]);
	}

	public String[] numerosDividoresParesImpares(int number, boolean par)
	{
		String tipo = "pares";
		int resto = 0;
		if(!par)
		{
			resto = 1;
			tipo = "ímpares";
		}

		List<String> passos = new ArrayList<String>();
		passos.add(fatoresPrimosPares(number, par));
		int cont = 0;
		String listDivisores = "";
		for(int i = 0; i < lista.size(); i++)
		{
			Integer integer = lista.get(i);
			if(integer % 2 == resto)
			{
				cont++;
				if(cont > 1)
					listDivisores += ",~";
				listDivisores += "\\textcolor{iris}{" + integer + "}";
			}
		}

		if(cont > 0)
		{
			passos.add("Divisores " + tipo + ":");
			passos.add("\\(" + listDivisores + "\\)");
		}
		passos.add("Número de divisores " + tipo + ": \\(" + cont + "\\)");
		return passos.toArray(new String[0]);
	}

	public int numerosDividoresResultado(int number)
	{
		lista = new ArrayList<Integer>();
		lista.add(1);
		int divisor = 2;
		int novoDivisor;
		Integer integer;
		int size;
		while(number != 1)
		{
			if(number % divisor == 0)
			{
				size = lista.size();
				for(int i = 0; i < size; i++)
				{
					integer = lista.get(i);
					novoDivisor = integer * divisor;
					if(!lista.contains(novoDivisor))
						lista.add(novoDivisor);
				}
				if(number % divisor == 0)
					number /= divisor;
			}
			else
				divisor++;
		}
		return lista.size();
	}

	public int somaDividoresResultado(int number)
	{
		lista = new ArrayList<Integer>();
		lista.add(1);
		int divisor = 2;
		int novoDivisor;
		Integer integer;
		int size;
		int soma = 1;
		while(number != 1)
		{
			if(number % divisor == 0)
			{
				size = lista.size();
				for(int i = 0; i < size; i++)
				{
					integer = lista.get(i);
					novoDivisor = integer * divisor;
					if(!lista.contains(novoDivisor))
					{
						lista.add(novoDivisor);
						soma += novoDivisor;
					}
				}
				if(number % divisor == 0)
					number /= divisor;
			}
			else
				divisor++;
		}
		return soma;
	}

	public int numerosDividoresResultadoPares(int number, boolean par)
	{
		int resto = 0;
		if(!par)
			resto = 1;

		numerosDividoresResultado(number);
		int cont = 0;
		Integer integer;
		for(int i = 0; i < lista.size(); i++)
		{
			integer = lista.get(i);
			if(integer % 2 == resto)
				cont++;
		}
		return cont;
	}

	public String fatoresPrimos(int number)
	{
		String resolucaoLatex = "\\(";
		int divisor = 2;
		lista = new ArrayList<Integer>();
		lista.add(1);

		resolucaoLatex += DefinicaoCores.irisBabypink();
		resolucaoLatex += "\\begin{array}[t]{r |l|l}";
		resolucaoLatex += "&&1\\\\";

		String resultado = "";
		int cont = 0;
		int novoDivisor;
		Integer integer;
		int size;
		while(number != 1)
		{
			if(number % divisor == 0)
			{
				resultado = "";
				size = lista.size();
				cont = 0;
				for(int i = 0; i < size; i++)
				{
					integer = lista.get(i);
					novoDivisor = integer * divisor;
					if(!lista.contains(novoDivisor))
					{
						cont++;
						lista.add(novoDivisor);
						if(cont > 1)
							resultado += ",~";
						resultado += "" + novoDivisor;
					}
				}
				resolucaoLatex += number + ",&" + divisor + "&" + resultado + "\\\\";
				if(number % divisor == 0)
					number /= divisor;
			}
			else
				divisor++;
		}
		resolucaoLatex += "\\hline";
		resolucaoLatex += number + ",&\\\\";
		resolucaoLatex += "\\end{array}\\)";
		return resolucaoLatex;
	}

	public String fatoresPrimosPares(int number, boolean par)
	{
		int resto = 0;
		if(!par)
			resto = 1;

		String resolucaoLatex = "\\(";
		int divisor = 2;
		lista = new ArrayList<Integer>();
		lista.add(1);

		resolucaoLatex += DefinicaoCores.irisBabypink();
		resolucaoLatex += "\\begin{array}[t]{r |l|l}";
		if(par)
			resolucaoLatex += "&&1\\\\";
		else
			resolucaoLatex += "&&\\textcolor{iris}{1}\\\\";

		String resultado = "";
		int cont = 0;
		int novoDivisor;
		Integer integer;
		int size;
		while(number != 1)
		{
			if(number % divisor == 0)
			{
				resultado = "";
				size = lista.size();
				cont = 0;
				for(int i = 0; i < size; i++)
				{
					integer = lista.get(i);
					novoDivisor = integer * divisor;
					if(!lista.contains(novoDivisor))
					{
						cont++;
						lista.add(novoDivisor);
						if(cont > 1)
							resultado += ",~";
						if(novoDivisor % 2 == resto)
							resultado += "\\textcolor{iris}{" + novoDivisor + "}";
						else
							resultado += "" + novoDivisor;
					}
				}
				resolucaoLatex += number + ",&" + divisor + "&" + resultado + "\\\\";
				if(number % divisor == 0)
					number /= divisor;
			}
			else
				divisor++;
		}
		resolucaoLatex += "\\hline";
		resolucaoLatex += number + ",&\\\\";
		resolucaoLatex += "\\end{array}\\)";
		return resolucaoLatex;
	}
}
