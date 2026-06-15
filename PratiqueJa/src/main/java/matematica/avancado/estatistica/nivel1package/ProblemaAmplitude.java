package matematica.avancado.estatistica.nivel1package;

import java.util.Random;

import matematica.ParCor;
import matematica.avancado.estatistica.AuxEstatistica;

public class ProblemaAmplitude
{
	public String texto;

	public int[] valores;

	public ProblemaAmplitude(String texto)
	{
		super();
		this.texto = texto;
	}

	public void gerarValores()
	{
		Random rand = new Random();
		int n = 6;
		valores = new int[n];
		for(int i = 0; i < n; i++)
			valores[i] = 3 + rand.nextInt(40);
	}

	public String resolucao()
	{
		int max = AuxEstatistica.maximo(valores);
		int min = AuxEstatistica.minimo(valores);

		String res = "A amplitude total é a diferença entre o maior e o menor valor do conjunto:";
		res += "\\(\\\\\\)";
		res += "\\(" + ParCor.formula("A = x_{\\max} - x_{\\min}") + "\\)";
		res += "\\(\\\\\\)";
		res += "No conjunto " + AuxEstatistica.listaStr(valores) + ", o maior valor é \\(" + max + "\\) e o menor é \\(" + min + "\\):";
		res += "\\(\\\\\\)";
		res += "\\(A = " + max + " - " + min + " = \\mathbf{" + (max - min) + "}\\)";

		return res;
	}

	public String resultado()
	{
		return "" + (AuxEstatistica.maximo(valores) - AuxEstatistica.minimo(valores));
	}

	public String getPergunta()
	{
		String pergunta = texto;
		if(pergunta.contains("$v"))
			pergunta = pergunta.replace("$v", AuxEstatistica.listaStr(valores));

		return pergunta;
	}

	public ProblemaAmplitude clone()
	{
		return new ProblemaAmplitude(texto);
	}
}
