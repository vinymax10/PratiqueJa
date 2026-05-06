package matematica.intermediario.semelhancaangulos.resolucao;

import java.util.ArrayList;


public class ResolucaoExercicio6 extends Resolucao
{
	public ResolucaoExercicio6(String graph[],String complementos[])
	{
		super(graph,complementos);
	}
	
	protected void carregarList()
	{
		listNos = new ArrayList<NoGraph>();
		for(char c = 'a'; c <= 'o'; c++)
		{
			if(c != 'k')
				listNos.add(new NoGraph(c + ""));
		}
	}

	public static void main(String[] args)
	{
		String graph[] = { "a->d", "b->e,h", "c->f,n", "d->a", "e->b,j", "f->c,l", "g->a-f,i", "h->b,j", 
		"i->c-d,g",	"j->e,h", "l->f,n", "m->a-b,o", "n->c,l", "o->e-d,m" };

		String complementos[] = { "a,b,c", "b,c,d", "c,d,e", "d,e,f", "e,f,a", "f,a,b", "l,m", "m,n", "n,o", "o,l", "g,h",
		"h,i", "i,j", "j,g" };

		boolean facil=false;
		ResolucaoExercicio6 resolucaoExercicio = new ResolucaoExercicio6(graph,complementos);
		System.out.println(resolucaoExercicio.gerarSolucao1Conhecidos(facil));
		System.out.println(resolucaoExercicio.gerarSolucao2Conhecidos());


	}

}
