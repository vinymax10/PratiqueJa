package matematica.basico.conjuntos.nivel3package;

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

	public String resolucao()
	{
		String resolucaoLatex = "";
		resolucaoLatex += "\\(A =\\) " + descricaoA;
		resolucaoLatex += "\\(\\\\\\)";
		resolucaoLatex += "\\(B =\\) " + descricaoB;
		resolucaoLatex += "\\(\\\\\\)";

		switch(tipoOperacao)
		{
			case A: 
				resolucaoLatex+=ResolucaoConjuntos.menosA(dados);
				break;
				
			case AintersecB: 
				resolucaoLatex+=ResolucaoConjuntos.menosAIntersecB(dados);
				break;
			case AMenosB: 
				resolucaoLatex+=ResolucaoConjuntos.menosAMenosB(dados);
				break;
		}

		return resolucaoLatex;
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
