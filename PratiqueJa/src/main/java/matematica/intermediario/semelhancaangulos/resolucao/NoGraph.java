package matematica.intermediario.semelhancaangulos.resolucao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class NoGraph
{
	String no;
	List<NoEquivalencia> equivalencia = new ArrayList<NoEquivalencia>();

	public NoGraph(String no)
	{
		super();
		this.no = no;
	}

	public boolean contemEquivalencia(NoGraph no)
	{
		for(NoEquivalencia noEquivalencia : equivalencia)
		{
			if(noEquivalencia.contem(no))
				return true;
		}
		
		return false;
	}
	
	@Override
	public String toString()
	{
		return "NoGraph: " + (no != null ? no+"->" : "")
		+ (equivalencia != null ? equivalencia : "");
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(no);
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		NoGraph other = (NoGraph) obj;
		return Objects.equals(no, other.no);
	}
	
}
