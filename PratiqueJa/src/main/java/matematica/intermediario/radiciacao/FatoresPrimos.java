package matematica.intermediario.radiciacao;

import java.util.ArrayList;
import java.util.List;

public class FatoresPrimos
{
	List<Fator>fatores=new ArrayList<Fator>();
	
	public void add(int termo, int potencia)
	{
		Fator fator=new Fator(termo, potencia);
		fatores.add(fator);
	}
	
	public void dividirPotencias(int potencia)
	{
		for(Fator fator : fatores)
		{
			fator.potencia/=potencia;
		}
	}
	
	public void multiplicarPotencias(int potencia)
	{
		for(Fator fator : fatores)
		{
			fator.potencia*=potencia;
		}
	}
	
	public void removerPotencias()
	{
		for(Fator fator : fatores)
		{
			if(fator.potencia>1)
			{
				fator.termo=(int)Math.pow(fator.termo,fator.potencia);
				fator.potencia=1;
			}
		}
	}
	
	public void juntar(FatoresPrimos x)
	{
		Fator fatorX;
		for(Fator fator : fatores)
		{
			fatorX=x.getFator(fator);
			if(fatorX!=null)
			{
				fator.potencia+=fatorX.potencia;
				x.fatores.remove(fatorX);
			}
		}
		
		for(Fator fator : x.fatores)
		{
			fatores.add(fator);
		}
	}
	
	private Fator getFator(Fator fatorX)
	{
		for(Fator fator : fatores)
		{
			if(fator.termo==fatorX.termo)
				return fator;
		}
		return null;
	}

	public String latex()
	{
		String latex="";
		
		int cont=0;
		for(Fator fator : fatores)
		{
			if(cont>0)
				latex+=" \\cdot ";
			
			latex+=fator.toString();
			cont++;
		}
		return latex;
	}
	
	public int resultado()
	{
		int total=1;
		for(Fator fator : fatores)
		{
			total*=Math.pow(fator.termo,fator.potencia);
		}
		return total;
	}
	
	
	public boolean possuiPotencias()
	{
		for(Fator fator : fatores)
		{
			if(fator.potencia>1)
				return true;
		}
		
		return false;
	}
	
	public boolean precisaDesenvolver()
	{
		if(fatores.size()==1&&fatores.get(0).potencia==1)
			return false;
		
		return true;
	}
}
