package pdf.base;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

public class PdfStyle
{
	public final BaseColor corTexto = new BaseColor(102, 102, 102);
    public final BaseColor corAzul = new BaseColor(38, 135, 233);
    public final BaseColor bgLinhaPar = new BaseColor(241, 247, 255);
    public final BaseColor bgHeader = new BaseColor(218, 236, 255);
    public final BaseColor bgLinhaImpar = BaseColor.WHITE;
    
    public final Font tituloFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, corTexto);
    public final Font destaqueFont = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD, corAzul);
    public final Font filtroFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, corTexto);
    public final Font textoFont = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, corTexto);
    public final Font headerFont = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, corTexto);

}
