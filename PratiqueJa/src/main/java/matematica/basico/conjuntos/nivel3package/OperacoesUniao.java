package matematica.basico.conjuntos.nivel3package;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import matematica.basico.conjuntos.ResolucaoConjuntos;
import matematica.basico.conjuntos.nivel2package.DadosAB;

public class OperacoesUniao
{
	public String texto;
	String descricaoA, descricaoB;
	DadosAB dados;

	public TipoOperacaoUniao tipoOperacao;
	
	public OperacoesUniao(String texto, String descricaoA, String descricaoB, TipoOperacaoUniao tipoOperacao)
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
				passos.addAll(Arrays.asList(ResolucaoConjuntos.uniaoA(dados)));
				break;

			case AintersecB:
				passos.addAll(Arrays.asList(ResolucaoConjuntos.uniaoAIntersecB(dados)));
				break;
			case AUniaoB:
				passos.addAll(Arrays.asList(ResolucaoConjuntos.uniaoAUniaoB(dados)));
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
			case AUniaoB: return dados.aUbStr;
		}
		return "";
	}

	public String getPergunta()
	{
		String pergunta = texto;
		if(pergunta.contains("$a"))
			pergunta = pergunta.replace("$a", dados.aStr);
		
		if(pergunta.contains("$b"))
			pergunta = pergunta.replace("$b", dados.bStr);
		
		if(pergunta.contains("$i"))
			pergunta = pergunta.replace("$i", dados.aIbStr);
		
		if(pergunta.contains("$u"))
			pergunta = pergunta.replace("$u", dados.aUbStr);
		
		return pergunta;
	}

	public OperacoesUniao clone()
	{
		return new OperacoesUniao(texto,descricaoA,descricaoB,tipoOperacao);
	}
}
