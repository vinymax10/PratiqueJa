package pdf.publicacao.estilo;

/**
 * Foto de fundo, com uma faixa opaca na cor de destaque cobrindo o topo do cartão e um corpo
 * translúcido (mesma opacidade do Foto de Fundo) abaixo dela, deixando a foto aparecer um
 * pouco atrás do texto — visual de pôster.
 */
public class FaixaSuperiorVisual implements EstiloVisual
{
	private static final String ALTURA_FAIXA = "46px";

	private final String cor;

	public FaixaSuperiorVisual(String cor)
	{
		this.cor = cor;
	}

	@Override
	public String painel(String margemPx, String raioPt)
	{
		// "PFaixaBase -| PSE" (não "PSE -| PFaixaBase"!): o operador "A -| B" do TikZ usa o X
		// de B e o Y de A — invertido do que parece à primeira vista. Com a ordem errada, o
		// canto vira (x de PFaixaBase, y de PSE) = uma faixa de largura zero (sumida).
		//
		// O corte em faixa+corpo é feito com \clip (não com "rounded corners" nos dois
		// retângulos): arredondar um retângulo de 46px de altura nos 4 cantos faz o raio
		// "comer" a faixa inteira quando 2×raio se aproxima da altura. Com \clip na silhueta
		// arredondada do cartão, os dois \fill internos ficam retos (sem raio próprio) e a
		// costura entre faixa e corpo sai limpa, com só os cantos externos arredondados.
		return "\\node[inner sep=0] at (current page.center){\\includegraphics[width=\\paperwidth,height=\\paperheight]{background.png}};\r\n"
		+ "\\coordinate (PNW) at ([shift={(" + margemPx + ",-" + margemPx + ")}]current page.north west);\r\n"
		+ "\\coordinate (PSE) at ([shift={(-" + margemPx + "," + margemPx + ")}]current page.south east);\r\n"
		+ "\\coordinate (PNE) at ([shift={(-" + margemPx + ",-" + margemPx + ")}]current page.north east);\r\n"
		+ "\\coordinate (PN) at ([yshift=-" + margemPx + "]current page.north);\r\n"
		+ "\\coordinate (PS) at ([yshift=" + margemPx + "]current page.south);\r\n"
		+ "\\coordinate (PFaixaBase) at ([yshift=-" + ALTURA_FAIXA + "]PNW);\r\n"
		+ "\\begin{scope}\r\n"
		+ "  \\clip[rounded corners=" + raioPt + "] (PNW) rectangle (PSE);\r\n"
		+ "  \\fill[" + cor + "] (PNW) rectangle (PFaixaBase -| PSE);\r\n"
		+ "  \\fill[white,opacity=0.82] (PFaixaBase) rectangle (PSE);\r\n"
		+ "\\end{scope}\r\n";
	}

	@Override
	public String corDestaque()
	{
		return cor;
	}
}
