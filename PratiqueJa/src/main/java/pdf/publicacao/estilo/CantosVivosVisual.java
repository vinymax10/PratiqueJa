package pdf.publicacao.estilo;

/** Foto de fundo com uma película branca translúcida por cima (mesma opacidade do Foto de Fundo) para o conteúdo ficar legível, só com cantos na cor de destaque em "L" (estilo mira de câmera), minimalista. */
public class CantosVivosVisual implements EstiloVisual
{
	private static final String COMPRIMENTO_CANTO = "30px";

	private final String cor;

	public CantosVivosVisual(String cor)
	{
		this.cor = cor;
	}

	@Override
	public String painel(String margemPx, String raioPt)
	{
		return "\\node[inner sep=0] at (current page.center){\\includegraphics[width=\\paperwidth,height=\\paperheight]{background.png}};\r\n"
		+ "\\fill[white,opacity=0.82] (current page.south west) rectangle (current page.north east);\r\n"
		+ "\\coordinate (PNW) at ([shift={(" + margemPx + ",-" + margemPx + ")}]current page.north west);\r\n"
		+ "\\coordinate (PSE) at ([shift={(-" + margemPx + "," + margemPx + ")}]current page.south east);\r\n"
		+ "\\coordinate (PNE) at ([shift={(-" + margemPx + ",-" + margemPx + ")}]current page.north east);\r\n"
		+ "\\coordinate (PSW) at ([shift={(" + margemPx + "," + margemPx + ")}]current page.south west);\r\n"
		+ "\\coordinate (PN) at ([yshift=-" + margemPx + "]current page.north);\r\n"
		+ "\\coordinate (PS) at ([yshift=" + margemPx + "]current page.south);\r\n"
		+ "\\draw[" + cor + ",line width=3.4pt,line cap=round] (PNW) -- ++(" + COMPRIMENTO_CANTO + ",0) (PNW) -- ++(0,-" + COMPRIMENTO_CANTO + ");\r\n"
		+ "\\draw[" + cor + ",line width=3.4pt,line cap=round] (PNE) -- ++(-" + COMPRIMENTO_CANTO + ",0) (PNE) -- ++(0,-" + COMPRIMENTO_CANTO + ");\r\n"
		+ "\\draw[" + cor + ",line width=3.4pt,line cap=round] (PSW) -- ++(" + COMPRIMENTO_CANTO + ",0) (PSW) -- ++(0," + COMPRIMENTO_CANTO + ");\r\n"
		+ "\\draw[" + cor + ",line width=3.4pt,line cap=round] (PSE) -- ++(-" + COMPRIMENTO_CANTO + ",0) (PSE) -- ++(0," + COMPRIMENTO_CANTO + ");\r\n";
	}

	@Override
	public String corDestaque()
	{
		return cor;
	}
}
