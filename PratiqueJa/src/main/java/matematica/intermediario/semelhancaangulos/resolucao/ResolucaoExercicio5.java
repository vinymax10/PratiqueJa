package matematica.intermediario.semelhancaangulos.resolucao;

import java.util.ArrayList;


public class ResolucaoExercicio5 extends Resolucao
{
	public ResolucaoExercicio5(String graph[],String complementos[])
	{
		super(graph,complementos);
	}

	protected void carregarList()
	{
		listNos = new ArrayList<NoGraph>();
		for(char c = 'a'; c <= 'j'; c++)
		{
			if(c != 'k')
				listNos.add(new NoGraph(c + ""));
		}
	}
	
	public static void main(String[] args)
	{
		String graph[] = { "a->d", "b->e,h", "c->f", "d->a", "e->b,j", "f->c", "g->a-f,i", 
		"h->b,j", "i->c-d,g","j->e,h" };
		
		String complementos[] = { "a,b,c", "b,c,d", "c,d,e", "d,e,f", "e,f,a", "f,a,b", "g,h",
		"h,i", "i,j", "j,g" };
		boolean facil=false;
		ResolucaoExercicio5 resolucaoExercicio = new ResolucaoExercicio5(graph,complementos);
		System.out.println(resolucaoExercicio.gerarSolucao1Conhecidos(facil));
		System.out.println(resolucaoExercicio.gerarSolucao2Conhecidos());


	}

}
