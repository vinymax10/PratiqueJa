package matematica.intermediario.semelhancaangulos.resolucao;

import java.util.ArrayList;


public class ResolucaoExercicio1 extends Resolucao
{
	public ResolucaoExercicio1(String graph[],String complementos[])
	{
		super(graph,complementos);
	}

	protected void carregarList()
	{
		listNos = new ArrayList<NoGraph>();
		for(char c = 'a'; c <= 'd'; c++)
		{
			if(c != 'k')
				listNos.add(new NoGraph(c + ""));
		}
	}
	
	public static void main(String[] args)
	{
		String graph[] = { "a->b", "b->a", "c->d", "d->c"};

		String complementos[] = { "a,c", "c,b", "b,d", "d,a"};
		
//		4- SemelhancaAngulos4 A, c, d | b
		boolean facil=true;
		ResolucaoExercicio1 resolucaoExercicio = new ResolucaoExercicio1(graph,complementos);
	}

}
