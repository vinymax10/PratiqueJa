package pdf.publicacao.estilo;

/** Estilo original: foto de fundo em página cheia, com o conteúdo sobre um painel branco translúcido. */
public class FotoFundoVisual implements EstiloVisual
{
	private final String cor;

	public FotoFundoVisual(String cor)
	{
		this.cor = cor;
	}

	@Override
	public String painel(String margemPx, String raioPt)
	{
		return "\\node[inner sep=0] at (current page.center){\\includegraphics[width=\\paperwidth,height=\\paperheight]{background.png}};\r\n"
		+ "\\coordinate (PNW) at ([shift={(" + margemPx + ",-" + margemPx + ")}]current page.north west);\r\n"
		+ "\\coordinate (PSE) at ([shift={(-" + margemPx + "," + margemPx + ")}]current page.south east);\r\n"
		+ "\\coordinate (PNE) at ([shift={(-" + margemPx + ",-" + margemPx + ")}]current page.north east);\r\n"
		+ "\\coordinate (PN) at ([yshift=-" + margemPx + "]current page.north);\r\n"
		+ "\\coordinate (PS) at ([yshift=" + margemPx + "]current page.south);\r\n"
		+ "\\fill[white,opacity=0.82,rounded corners=" + raioPt + "] (PNW) rectangle (PSE);\r\n";
	}

	@Override
	public String corDestaque()
	{
		return cor;
	}
}
