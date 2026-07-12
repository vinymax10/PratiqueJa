package pdf.publicacao.estilo;

/** Foto de fundo emoldurada por um cartão branco translúcido (mesma opacidade do Foto de Fundo) com moldura dupla (borda externa grossa + interna fina). */
public class MolduraAzulVisual implements EstiloVisual
{
	private final String cor;

	public MolduraAzulVisual(String cor)
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
		+ "\\fill[white,opacity=0.82,rounded corners=" + raioPt + "] (PNW) rectangle (PSE);\r\n"
		+ "\\draw[" + cor + ",line width=2.4pt,rounded corners=" + raioPt + "] (PNW) rectangle (PSE);\r\n"
		+ "\\draw[" + cor + ",line width=0.8pt,rounded corners=" + raioPt + "] ([shift={(6px,-6px)}]PNW) rectangle ([shift={(-6px,6px)}]PSE);\r\n";
	}

	@Override
	public String corDestaque()
	{
		return cor;
	}
}
