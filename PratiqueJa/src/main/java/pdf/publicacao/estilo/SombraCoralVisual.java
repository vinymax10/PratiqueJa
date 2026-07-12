package pdf.publicacao.estilo;

/** Foto de fundo e cartão branco translúcido (mesma opacidade do Foto de Fundo) com uma sombra na cor de destaque deslocada por trás, contornando o cartão em cantos arredondados. */
public class SombraCoralVisual implements EstiloVisual
{
	private static final String DESLOCAMENTO = "6px";

	private final String cor;

	public SombraCoralVisual(String cor)
	{
		this.cor = cor;
	}

	@Override
	public String painel(String margemPx, String raioPt)
	{
		// A sombra é um CONTORNO (\draw, não \fill) do cartão inteiro deslocado 6px: como é só
		// uma linha (não uma área cheia), não "lava" o interior do cartão translúcido como um
		// \fill do tamanho do cartão faria — e por ser um retângulo contínuo com rounded
		// corners, os cantos saem arredondados de verdade (acompanhando o contorno do cartão),
		// em vez da tira em "L" de cantos retos usada antes.
		return "\\node[inner sep=0] at (current page.center){\\includegraphics[width=\\paperwidth,height=\\paperheight]{background.png}};\r\n"
		+ "\\coordinate (PNW) at ([shift={(" + margemPx + ",-" + margemPx + ")}]current page.north west);\r\n"
		+ "\\coordinate (PSE) at ([shift={(-" + margemPx + "," + margemPx + ")}]current page.south east);\r\n"
		+ "\\coordinate (PNE) at ([shift={(-" + margemPx + ",-" + margemPx + ")}]current page.north east);\r\n"
		+ "\\coordinate (PN) at ([yshift=-" + margemPx + "]current page.north);\r\n"
		+ "\\coordinate (PS) at ([yshift=" + margemPx + "]current page.south);\r\n"
		+ "\\draw[" + cor + ",line width=6pt,rounded corners=" + raioPt + "]"
		+ " ([shift={(" + DESLOCAMENTO + ",-" + DESLOCAMENTO + ")}]PNW) rectangle ([shift={(" + DESLOCAMENTO + ",-" + DESLOCAMENTO + ")}]PSE);\r\n"
		+ "\\fill[white,opacity=0.82,rounded corners=" + raioPt + "] (PNW) rectangle (PSE);\r\n";
	}

	@Override
	public String corDestaque()
	{
		return cor;
	}
}
