package matematica.basico.conjuntos.nivel3package;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import matematica.basico.conjuntos.ResolucaoConjuntos;
import matematica.basico.conjuntos.nivel2package.DadosAB;

public class OperacoesMenos
{
	public String texto;
	String descricaoA, descricaoB;
	DadosAB dados;
	public TipoOperacaoMenos tipoOperacao;
	
	public OperacoesMenos(String texto, String descricaoA, String descricaoB, TipoOperacaoMenos tipoOperacao)
	{
		super();
		this.texto = texto;
		this.descricaoA=descricaoA;
		this.descricaoB=descricaoB;
		this.tipoOperacao=tipoOperacao;
	}

	public void gerarValores()
	{
		this.dados=new DadosAB();
	}

	public String[] resolucao()
	{
		List<String> passos = new ArrayList<>();
		passos.add("\\(A =\\) " + descricaoA);
		passos.add("\\(B =\\) " + descricaoB);

		switch(tipoOperacao)
		{
			case A:
				passos.addAll(Arrays.asList(ResolucaoConjuntos.menosA(dados)));
				break;

			case AintersecB:
				passos.addAll(Arrays.asList(ResolucaoConjuntos.menosAIntersecB(dados)));
				break;
			case AMenosB:
				passos.addAll(Arrays.asList(ResolucaoConjuntos.menosAMenosB(dados)));
				break;
		}

		return passos.toArray(new String[0]);
	}

	public String resultado()
	{
		switch(tipoOperacao)
		{
			case A: return dados.aStr;
			case AintersecB: return dados.aIbStr;
			case AMenosB: return dados.aMbStr;
		}
		return "";
	}

	public String getPergunta()
	{
		String pergunta = texto;
		
		if(pergunta.contains("$aMb"))
			pergunta = pergunta.replace("$aMb", dados.aMbStr);
		
		if(pergunta.contains("$a"))
			pergunta = pergunta.replace("$a", dados.aStr);
		
		if(pergunta.contains("$i"))
			pergunta = pergunta.replace("$i", dados.aIbStr);
		
		return pergunta;
	}

	public OperacoesMenos clone()
	{
		return new OperacoesMenos(texto,descricaoA,descricaoB,tipoOperacao);
	}
}
