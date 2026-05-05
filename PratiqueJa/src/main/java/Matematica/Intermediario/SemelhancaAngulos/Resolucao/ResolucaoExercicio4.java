package Matematica.Intermediario.SemelhancaAngulos.Resolucao;

import java.util.ArrayList;


public class ResolucaoExercicio4 extends Resolucao
{
	public ResolucaoExercicio4(String graph[],String complementos[])
	{
		super(graph,complementos);
	}

	protected void carregarList()
	{
		listNos = new ArrayList<NoGraph>();
		for(char c = 'a'; c <= 'e'; c++)
		{
			if(c != 'k')
				listNos.add(new NoGraph(c + ""));
		}
	}
	
	public static void main(String[] args)
	{
		String graph[] = { "a->c-b" };

		String complementos[] = { "b,d", "c,e"};
		
		boolean facil=true;
		ResolucaoExercicio4 resolucaoExercicio = new ResolucaoExercicio4(graph,complementos);
		System.out.println(resolucaoExercicio.gerarSolucao1Conhecidos(facil));
		System.out.println(resolucaoExercicio.gerarSolucao2Conhecidos());


	}

}
