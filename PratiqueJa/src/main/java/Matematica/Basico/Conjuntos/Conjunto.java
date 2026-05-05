package Matematica.Basico.Conjuntos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Conjunto
{
	List <Integer> list=new ArrayList<Integer>();
	
	public Conjunto()
	{
	}
	
	public Conjunto(Conjunto x)
	{
		for(Integer element : x.list)
		{
			list.add(element);
		}
	}

	public Conjunto(int size, int limit)
	{
		int element;
		Random rand=new Random();
		for(int i = 0; i < size; i++)
		{
			element=rand.nextInt(limit);
			while(list.contains(element))
				element=rand.nextInt(limit);
			
			list.add(element);
		}
		ordenar();
	}
	
	public void ordenar()
	{
		Collections.sort(list);
	}
	
	public Conjunto menos(Conjunto x)
	{
		Conjunto novo=new Conjunto();
		
		for(Integer element : list)
		{
			if(!x.list.contains(element))
				novo.list.add(element);
		}
		novo.ordenar();
		return novo;
	}
	
	public Conjunto uniao(Conjunto x)
	{
		Conjunto novo=new Conjunto(this);
		
		for(Integer element : x.list)
		{
			if(!novo.list.contains(element))
				novo.list.add(element);
		}
		novo.ordenar();
		return novo;
	}
	
	public Conjunto interseccao(Conjunto x)
	{
		Conjunto novo=new Conjunto();
		
		for(Integer element : x.list)
		{
			if(list.contains(element)&&x.list.contains(element))
				novo.list.add(element);
		}
		novo.ordenar();
		return novo;
	}
	
	public int soma()
	{
		int soma=0;
		for(Integer element : list)
			soma+=element;

		return soma;
	}
	
	public String somaStr()
	{
		String soma="";
		int element;

		for(int i = 0; i < list.size(); i++)
		{
			element=list.get(i);
			if(i<(list.size()-1))
				soma+=element+"+";
			else
				soma+=element+"";
		}

		return soma;
	}
	
	public int maior()
	{
		int max=Integer.MIN_VALUE;
		for(Integer element : list)
		{
			if(element>max)
				max=element;
		}
		return max;
	}
	
	public int menor()
	{
		int min=Integer.MAX_VALUE;
		for(Integer element : list)
		{
			if(element<min)
				min=element;
		}
		return min;
	}
	
	public int size()
	{
		return list.size();
	}
	
	public String toString()
	{
		String str="\\{";
		int element;
		for(int i = 0; i < list.size(); i++)
		{
			element=list.get(i);
			if(i<(list.size()-1))
				str+=element+",";
			else
				str+=element+"";
		}
		str+="\\}";
		return str;
	}
	
	public int getAle()
	{
		Random rand=new Random();
		return list.get(rand.nextInt(list.size()));
	}
	
	
	public void contruirInterseccao(Conjunto b)
	{
		int element;
		Conjunto c=interseccao(b);
		if(c.size()<2)
		{
			if(size()>b.size())
			{
				for(int i = 0; i < 3; i++)
				{
					element=getAle();
					if(!b.list.contains(element))
						b.list.add(element);
				}
			}
			else
			{
				for(int i = 0; i < 3; i++)
				{
					element=b.getAle();
					if(!list.contains(element))
						list.add(element);
				}
			}
		}
		ordenar();
		b.ordenar();
	}
}
