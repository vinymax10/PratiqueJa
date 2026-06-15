package matematica.avancado.estatistica.nivel1package;

import java.util.Random;

import matematica.avancado.estatistica.AuxEstatistica;

public class ProblemaModa
{
	public String texto;

	public int[] valores;
	public int moda;

	public ProblemaModa(String texto)
	{
		super();
		this.texto = texto;
	}

	public void gerarValores()
	{
		Random rand = new Random();
		moda = 2 + rand.nextInt(8);

		// 4 valores distintos, diferentes da moda (frequência 1 cada → moda única)
		int[] outros = new int[4];
		int count = 0;
		while(count < 4)
		{
			int cand = 2 + rand.nextInt(14);
			if(cand == moda) continue;
			boolean repetido = false;
			for(int i = 0; i < count; i++)
				if(outros[i] == cand) { repetido = true; break; }
			if(!repetido)
				outros[count++] = cand;
		}

		int[] base = { moda, moda, moda, outros[0], outros[1], outros[2], outros[3] };
		// embaralha
		for(int i = base.length - 1; i > 0; i--)
		{
			int j = rand.nextInt(i + 1);
			int tmp = base[i]; base[i] = base[j]; base[j] = tmp;
		}
		valores = base;
	}

	public String resolucao()
	{
		int freq = 0;
		for(int x : valores) if(x == moda) freq++;

		String res = "A moda (Mo) é o valor que aparece com maior frequência no conjunto.";
		res += "\\(\\\\\\)";
		res += "Contando as ocorrências em " + AuxEstatistica.listaStr(AuxEstatistica.ordenar(valores)) + ":";
		res += "\\(\\\\\\)";
		res += "O valor " + moda + " aparece " + freq + " vezes — mais que qualquer outro, que aparecem 1 vez.";
		res += "\\(\\\\\\)";
		res += "\\(\\text{Mo} = \\mathbf{" + moda + "}\\)";

		return res;
	}

	public String resultado()
	{
		return "" + moda;
	}

	public String getPergunta()
	{
		String pergunta = texto;
		if(pergunta.contains("$v"))
			pergunta = pergunta.replace("$v", AuxEstatistica.listaStr(valores));

		return pergunta;
	}

	public ProblemaModa clone()
	{
		return new ProblemaModa(texto);
	}
}
