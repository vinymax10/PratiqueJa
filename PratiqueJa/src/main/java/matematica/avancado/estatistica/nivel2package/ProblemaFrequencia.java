package matematica.avancado.estatistica.nivel2package;

import java.util.Random;

public class ProblemaFrequencia
{
	public String texto;

	public int[] freq;   // freq[k] = frequência do valor k
	public int alvo;
	public TipoFrequencia tipo;

	public ProblemaFrequencia(String texto, TipoFrequencia tipo)
	{
		super();
		this.texto = texto;
		this.tipo = tipo;
	}

	public void gerarValores()
	{
		Random rand = new Random();
		int num = 4 + rand.nextInt(2); // 4 ou 5 categorias (valores 0..num-1)
		freq = new int[num];
		for(int i = 0; i < num; i++)
			freq[i] = 1 + rand.nextInt(8);
		alvo = 2 + rand.nextInt(num - 3); // entre 2 e num-2 (acumulado não-trivial, evita "1 ... s")
	}

	private String distribuicaoStr()
	{
		StringBuilder sb = new StringBuilder();
		for(int k = 0; k < freq.length; k++)
		{
			sb.append(k).append(" → ").append(freq[k]);
			if(k < freq.length - 1)
				sb.append(", ");
		}
		return sb.toString();
	}

	public String[] resolucao()
	{
		StringBuilder soma = new StringBuilder();
		int total = 0;
		String faixa;

		if(tipo == TipoFrequencia.AteNoMaximo)
		{
			for(int k = 0; k <= alvo; k++)
			{
				soma.append(freq[k]);
				total += freq[k];
				if(k < alvo) soma.append(" + ");
			}
			faixa = "Os valores de \\(0\\) até \\(" + alvo + "\\) atendem à condição. Somamos suas frequências:";
		}
		else
		{
			for(int k = alvo; k < freq.length; k++)
			{
				soma.append(freq[k]);
				total += freq[k];
				if(k < freq.length - 1) soma.append(" + ");
			}
			faixa = "Os valores de \\(" + alvo + "\\) até \\(" + (freq.length-1) + "\\) atendem à condição. Somamos suas frequências:";
		}

		return new String[]
		{
			"A frequência acumulada soma as frequências absolutas dos valores que satisfazem a condição.",
			"Distribuição (valor → frequência): " + distribuicaoStr() + ".",
			faixa,
			"\\(" + soma + " = \\mathbf{" + total + "}\\)"
		};
	}

	public String resultado()
	{
		int total = 0;
		if(tipo == TipoFrequencia.AteNoMaximo)
			for(int k = 0; k <= alvo; k++) total += freq[k];
		else
			for(int k = alvo; k < freq.length; k++) total += freq[k];
		return "" + total;
	}

	public String getPergunta()
	{
		String pergunta = texto;
		if(pergunta.contains("$d"))
			pergunta = pergunta.replace("$d", distribuicaoStr());
		if(pergunta.contains("$k"))
			pergunta = pergunta.replace("$k", alvo+"");

		return pergunta;
	}

	public ProblemaFrequencia clone()
	{
		return new ProblemaFrequencia(texto,tipo);
	}
}
