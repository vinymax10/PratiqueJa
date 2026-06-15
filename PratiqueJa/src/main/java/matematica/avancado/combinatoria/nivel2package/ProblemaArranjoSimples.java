package matematica.avancado.combinatoria.nivel2package;

import java.util.Random;

import util.Algebra;
import matematica.DefinicaoCores;
import matematica.ParCor;
import matematica.avancado.combinatoria.AuxCombinacao;

public class ProblemaArranjoSimples
{
	public String texto;

	public int n,k;

	public ProblemaArranjoSimples(String texto)
	{
		super();
		this.texto = texto;
	}

	public void gerarValores()
	{
		Random rand = new Random();
		n = 4 + rand.nextInt(4);
		k = 2 + rand.nextInt(n-2);
	}

	public String resolucao()
	{
		String res = "O arranjo simples de \\(n\\) elementos tomados \\(k\\) a \\(k\\) é dado por:";
		res += "\\(\\\\\\)";
		res += "\\(" + ParCor.formula("A(n,k) = \\dfrac{n!}{(n-k)!}") + "\\)";
		res += "\\(\\\\\\)";
		res += "Com \\(n = " + n + "\\) e \\(k = " + k + "\\):";
		res += "\\(\\\\\\)";
		res += "\\(A(" + n + "," + k + ") = \\dfrac{" + n + "!}{(" + n + "-" + k + ")!} = \\\\ ";
		res += "\\dfrac{" + AuxCombinacao.fatorialString(n,(n-k)) + "\\cdot " + (n-k) + "!}{" + (n-k) + "!} = \\\\ ";
		res += AuxCombinacao.fatorialString(n,(n-k)) + " = \\mathbf{" + resultado() + "}\\)";

		return res;
	}

	public String resultado()
	{
		return ""+Algebra.fatorial(n)/Algebra.fatorial(n-k);
	}

	public String getPergunta()
	{
		String pergunta = texto;
		if(pergunta.contains("$n"))
			pergunta = pergunta.replace("$n", n+"");
		
		if(pergunta.contains("$k"))
			pergunta = pergunta.replace("$k", k+"");
		
		return pergunta;
	}

	public ProblemaArranjoSimples clone()
	{
		return new ProblemaArranjoSimples(texto);
	}
}
