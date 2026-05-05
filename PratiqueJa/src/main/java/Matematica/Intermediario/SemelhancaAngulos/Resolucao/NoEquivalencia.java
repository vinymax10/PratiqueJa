package Matematica.Intermediario.SemelhancaAngulos.Resolucao;


public class NoEquivalencia
{
	NoGraph um;
	NoGraph dois;

	public NoEquivalencia(NoGraph um)
	{
		super();
		this.um = um;
	}
	
	public NoEquivalencia(NoGraph um, NoGraph dois)
	{
		super();
		this.um = um;
		this.dois = dois;
	}
	
	public int size()
	{
		if(dois!=null)
			return 2;
		else
			return 1;
	}
	
	public boolean contem(NoGraph no)
	{
		if(no.equals(um)||no.equals(dois))
			return true;
		
		return false;
	}
	
	public NoGraph getOutro(NoGraph no)
	{
		if(no.equals(um))
			return dois;
		
		if(no.equals(dois))
			return um;
		
		return null;
	}
	
	@Override
	public String toString()
	{
		return (um != null ? um.no : "") + (dois != null ? "-"+dois.no : "");
	}

}
