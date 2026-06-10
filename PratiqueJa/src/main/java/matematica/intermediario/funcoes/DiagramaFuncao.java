package matematica.intermediario.funcoes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DiagramaFuncao
{
	public int[] dominio;
	public int[] contradominio;
	public int[][] mapeamento;   // mapeamento[i] = índices dentro de contradominio
	public boolean eFuncao;
	public int idxBiSeta  = -1;  // índice no domínio do elemento com 2 setas (não-função)
	public boolean eInjetora;
	public int idxColisao1 = -1; // índice em domínio do 1º elemento com imagem duplicada (não-injetora)
	public int idxColisao2 = -1; // índice em domínio do 2º elemento com mesma imagem

	private DiagramaFuncao() {}

	/** Gera um diagrama que representa uma função válida. */
	public static DiagramaFuncao gerarFuncao(Random rand)
	{
		DiagramaFuncao d = new DiagramaFuncao();
		int sizeA = 3 + rand.nextInt(2);          // 3 ou 4
		int sizeB = sizeA + rand.nextInt(2);       // sizeA ou sizeA+1
		d.dominio      = sortearValores(sizeA, rand);
		d.contradominio = sortearValores(sizeB, rand);
		d.mapeamento   = new int[sizeA][];
		for(int i = 0; i < sizeA; i++)
			d.mapeamento[i] = new int[]{rand.nextInt(sizeB)};
		d.eFuncao = true;
		return d;
	}

	/** Gera um diagrama que NÃO representa função: um elemento do domínio tem 2 setas. */
	public static DiagramaFuncao gerarNaoFuncao(Random rand)
	{
		DiagramaFuncao d = new DiagramaFuncao();
		int sizeA = 3 + rand.nextInt(2);
		int sizeB = sizeA;
		d.dominio      = sortearValores(sizeA, rand);
		d.contradominio = sortearValores(sizeB, rand);
		d.mapeamento   = new int[sizeA][];
		for(int i = 0; i < sizeA; i++)
			d.mapeamento[i] = new int[]{rand.nextInt(sizeB)};

		int idx = rand.nextInt(sizeA);
		int extra;
		do { extra = rand.nextInt(sizeB); } while(extra == d.mapeamento[idx][0]);
		d.mapeamento[idx] = new int[]{d.mapeamento[idx][0], extra};
		d.eFuncao  = false;
		d.idxBiSeta = idx;
		return d;
	}

	/** Gera função injetora: todos mapeamentos apontam para índices distintos em B. */
	public static DiagramaFuncao gerarFuncaoInjetora(Random rand)
	{
		DiagramaFuncao d = new DiagramaFuncao();
		int sizeA = 3 + rand.nextInt(2);         // 3 ou 4
		int sizeB = sizeA + 1 + rand.nextInt(2); // sizeA+1 ou sizeA+2
		d.dominio      = sortearValores(sizeA, rand);
		d.contradominio = sortearValores(sizeB, rand);
		d.mapeamento   = new int[sizeA][];
		List<Integer> indices = new ArrayList<>();
		for(int j = 0; j < sizeB; j++) indices.add(j);
		Collections.shuffle(indices, rand);
		for(int i = 0; i < sizeA; i++)
			d.mapeamento[i] = new int[]{indices.get(i)};
		d.eFuncao   = true;
		d.eInjetora = true;
		return d;
	}

	/** Gera função NÃO injetora: dois elementos de A mapeiam para o mesmo elemento de B. */
	public static DiagramaFuncao gerarFuncaoNaoInjetora(Random rand)
	{
		DiagramaFuncao d = new DiagramaFuncao();
		int sizeA = 3 + rand.nextInt(2); // 3 ou 4
		int sizeB = sizeA;
		d.dominio      = sortearValores(sizeA, rand);
		d.contradominio = sortearValores(sizeB, rand);
		d.mapeamento   = new int[sizeA][];
		List<Integer> indices = new ArrayList<>();
		for(int j = 0; j < sizeB; j++) indices.add(j);
		Collections.shuffle(indices, rand);
		for(int i = 0; i < sizeA; i++)
			d.mapeamento[i] = new int[]{indices.get(i)};
		// Força colisão: col2 passa a ter a mesma imagem que col1
		int col1 = rand.nextInt(sizeA);
		int col2;
		do { col2 = rand.nextInt(sizeA); } while(col2 == col1);
		d.mapeamento[col2] = new int[]{d.mapeamento[col1][0]};
		d.eFuncao     = true;
		d.eInjetora   = false;
		d.idxColisao1 = col1;
		d.idxColisao2 = col2;
		return d;
	}

	/** Retorna contradominio[mapeamento[i][0]] — só válido para funções. */
	public int imagemDe(int i)
	{
		return contradominio[mapeamento[i][0]];
	}

	private static int[] sortearValores(int size, Random rand)
	{
		List<Integer> pool = new ArrayList<>();
		for(int v = 1; v <= 15; v++) pool.add(v);
		Collections.shuffle(pool, rand);
		int[] result = new int[size];
		for(int i = 0; i < size; i++) result[i] = pool.get(i);
		return result;
	}
}
