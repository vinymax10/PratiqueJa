package Matematica.Intermediario.SemelhancaAngulos.Resolucao;

import java.util.ArrayList;
import java.util.List;


public class NoComplemento
{
	NoGraph um;
	NoGraph dois;
	NoGraph tres;

	public NoComplemento(NoGraph um)
	{
		super();
		this.um = um;
	}

	public NoComplemento(NoGraph um, NoGraph dois)
	{
		super();
		this.um = um;
		this.dois = dois;
	}

	public NoComplemento(NoGraph um, NoGraph dois, NoGraph tres)
	{
		super();
		this.um = um;
		this.dois = dois;
		this.tres = tres;
	}
	
	public int size()
	{
		if(tres!=null)
			return 3;
		else
			return 2;
	}
	
	public boolean contem(NoGraph no)
	{
		if(no.equals(um) || no.equals(dois) || no.equals(tres))
		{
			return true;
		}
		return false;
	}
	
	public boolean contemUm(List<NoGraph> nosConhecidos)
	{
		for(NoGraph noGraph : nosConhecidos)
		{
			if(noGraph.equals(um) || noGraph.equals(dois) || noGraph.equals(tres))
			{
				return true;
			}
		}

		return false;
	}
	
	public boolean faltaSomenteUm(NoGraph noDesconhecido, List<NoGraph> nosConhecidos)
	{
		if(noDesconhecido.equals(um) || noDesconhecido.equals(dois) || noDesconhecido.equals(tres))
		{
			List<NoGraph> nos = nosFaltam(noDesconhecido,nosConhecidos);
			if(nos.size()==1)
				return true;
		}

		return false;
	}
	
	public List<NoGraph> nosFaltam(NoGraph noDesconhecido, List<NoGraph> nosConhecidos)
	{
		List<NoGraph> nos = new ArrayList<NoGraph>();
		nos.add(um);
		nos.add(dois);
		if(tres!=null)
			nos.add(tres);
		
		if(noDesconhecido.equals(um))
			nos.remove(um);
		
		if(noDesconhecido.equals(dois))
			nos.remove(dois);
		
		if(noDesconhecido.equals(tres))
			nos.remove(tres);
		
		for(NoGraph noGraph : nosConhecidos)
			nos.remove(noGraph);
		
		return nos;
	}
	
	public List<NoGraph> nosFaltam(List<NoGraph> nosConhecidos)
	{
		List<NoGraph> nos = new ArrayList<NoGraph>();
		nos.add(um);
		nos.add(dois);
		if(tres!=null)
			nos.add(tres);
		
		for(NoGraph noGraph : nosConhecidos)
			nos.remove(noGraph);
		
		return nos;
	}
	
	@Override
	public String toString()
	{
		return (um != null ? um.no : "") + (dois != null ? "+" + dois.no : "") + (tres != null ? "+" + tres.no : "");
	}

}
