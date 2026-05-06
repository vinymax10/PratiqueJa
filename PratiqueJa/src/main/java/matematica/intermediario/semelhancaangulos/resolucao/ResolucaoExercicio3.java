package matematica.intermediario.semelhancaangulos.resolucao;

import java.util.ArrayList;


public class ResolucaoExercicio3 extends Resolucao
{
	public ResolucaoExercicio3(String graph[],String complementos[])
	{
		super(graph,complementos);
	}

	protected void carregarList()
	{
		listNos = new ArrayList<NoGraph>();
		for(char c = 'a'; c <= 'h'; c++)
		{
			if(c != 'k')
				listNos.add(new NoGraph(c + ""));
		}
	}
	
	public static void main(String[] args)
	{
		String graph[] = { "a->b,e", "b->a,f", "c->d,g", "d->c,h", "e->a,f", "f->b,e", "g->c,h", "h->d,g" };

		String complementos[] = { "a,c", "c,b", "b,d", "d,a", "g,f", "f,h", "h,e", "e,g"};
		
		boolean facil=true;
		ResolucaoExercicio3 resolucaoExercicio = new ResolucaoExercicio3(graph,complementos);
		System.out.println(resolucaoExercicio.gerarSolucao1Conhecidos(facil));
		System.out.println(resolucaoExercicio.gerarSolucao2Conhecidos());


	}

}
