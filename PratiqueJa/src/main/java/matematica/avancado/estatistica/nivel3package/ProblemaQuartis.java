package matematica.avancado.estatistica.nivel3package;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import matematica.avancado.estatistica.AuxEstatistica;

public class ProblemaQuartis
{
	public String texto;

	public int[] valores;   // 7 valores em ordem crescente
	public TipoQuartil tipo;

	public ProblemaQuartis(String texto, TipoQuartil tipo)
	{
		super();
		this.texto = texto;
		this.tipo = tipo;
	}

	public void gerarValores()
	{
		Random rand = new Random();
		valores = new int[7];
		int atual = 2 + rand.nextInt(6);
		for(int i = 0; i < 7; i++)
		{
			valores[i] = atual;
			atual += 1 + rand.nextInt(4);
		}
	}

	private int q1() { return valores[1]; }
	private int q2() { return valores[3]; }
	private int q3() { return valores[5]; }

	public String[] resolucao()
	{
		int q1 = q1(), q2 = q2(), q3 = q3();

		List<String> passos = new ArrayList<>();
		passos.add("Os quartis dividem os dados ordenados em quatro partes iguais (base do boxplot).");
		passos.add("Dados em ordem crescente: " + AuxEstatistica.listaStr(valores) + " \\((n = 7)\\).");
		passos.add("A mediana \\(Q_2\\) ocupa a posição central (4ª): " + bold(TipoQuartil.Q2, "\\(Q_2 = ", q2, "\\)"));
		passos.add("Na metade inferior \\(\\{" + valores[0] + ", " + valores[1] + ", " + valores[2] + "\\}\\), o central é " + bold(TipoQuartil.Q1, "\\(Q_1 = ", q1, "\\)"));
		passos.add("Na metade superior \\(\\{" + valores[4] + ", " + valores[5] + ", " + valores[6] + "\\}\\), o central é " + bold(TipoQuartil.Q3, "\\(Q_3 = ", q3, "\\)"));

		if(tipo == TipoQuartil.IQR)
		{
			passos.add("A amplitude interquartílica é \\(IQR = Q_3 - Q_1\\):");
			passos.add("\\(IQR = " + q3 + " - " + q1 + " = \\mathbf{" + (q3 - q1) + "}\\)");
		}

		return passos.toArray(new String[0]);
	}

	/** Monta "\(Q = valor\)" destacando em \mathbf quando é o quartil perguntado. */
	private String bold(TipoQuartil alvo, String prefixo, int valor, String sufixo)
	{
		if(tipo == alvo)
			return prefixo + "\\mathbf{" + valor + "}" + sufixo;
		return prefixo + valor + sufixo;
	}

	public String resultado()
	{
		switch(tipo)
		{
			case Q1: return "" + q1();
			case Q2: return "" + q2();
			case Q3: return "" + q3();
			case IQR: return "" + (q3() - q1());
		}
		return "";
	}

	public String getPergunta()
	{
		String pergunta = texto;
		if(pergunta.contains("$v"))
			pergunta = pergunta.replace("$v", AuxEstatistica.listaStr(valores));

		return pergunta;
	}

	public ProblemaQuartis clone()
	{
		return new ProblemaQuartis(texto,tipo);
	}
}
