package Pdf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.swing.JLabel;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.fop.render.ps.EPSTranscoder;
import org.apache.fop.render.ps.PSTranscoder;
import org.apache.fop.svg.AbstractFOPTranscoder;
import org.apache.fop.svg.PDFTranscoder;
import org.scilab.forge.jlatexmath.DefaultTeXFont;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;
import org.scilab.forge.jlatexmath.cyrillic.CyrillicRegistration;
import org.scilab.forge.jlatexmath.greek.GreekRegistration;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.ImgTemplate;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class Convert
{

	public static final int PDF = 0;
	public static final int PS = 1;
	public static final int EPS = 2;

	public static void toSVG(String latex, String file, boolean fontAsShapes) throws IOException
	{
		DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
		String svgNS = "http://www.w3.org/2000/svg";
		Document document = domImpl.createDocument(svgNS, "svg", null);
		SVGGeneratorContext ctx = SVGGeneratorContext.createDefault(document);

		SVGGraphics2D g2 = new SVGGraphics2D(ctx, fontAsShapes);

		DefaultTeXFont.registerAlphabet(new CyrillicRegistration());
		DefaultTeXFont.registerAlphabet(new GreekRegistration());

		TeXFormula formula = new TeXFormula(latex);
		TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 12);
		icon.setInsets(new Insets(5, 5, 5, 5));
		g2.setSVGCanvasSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
		g2.setColor(Color.white);
		g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());

		JLabel jl = new JLabel();
		jl.setForeground(new Color(0, 0, 0));
		icon.paintIcon(jl, g2, 0, 0);

		boolean useCSS = true;
		FileOutputStream svgs = new FileOutputStream(file);
		Writer out = new OutputStreamWriter(svgs, "UTF-8");
		g2.stream(out, useCSS);
		svgs.flush();
		svgs.close();
	}

	public static String includeLineBreak(String latex, int widthLimit)
	{
		if(!latex.contains("array"))
		{
			String newLatex = "";
			String lista[] = latex.split("=");
			TeXFormula formula,formulaProx;
			TeXIcon icon,iconProx;
			String parcial = "",restante="";
			
			for(int i = 0; i < lista.length; i++)
			{
				
				parcial += lista[i] + "=";
				formula = new TeXFormula(parcial);
				icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 11);
			
				restante="";
				for(int j = i; j < lista.length; j++)
					restante+=lista[i] + "=";
				
				formulaProx= new TeXFormula(restante);
				iconProx=formulaProx.createTeXIcon(TeXConstants.STYLE_DISPLAY, 11);
				
				if(icon.getIconWidth() > widthLimit&&iconProx.getIconWidth() > 40)
				{
					parcial = lista[i] + "=";
					lista[i] = "\\\\" + lista[i];
				}
				if(i < lista.length - 1)
					newLatex += lista[i] + "=";
				else
					newLatex += lista[i];
			}
			
//			newLatex = includeVSpace(newLatex);
			return newLatex;
		}
		else
			return latex;
	}

	private static String includeVSpace(String latex)
	{
		String newLatex = "";
		String lista[] = latex.split("\\\\\\\\");
		TeXFormula formula;
		TeXIcon icon;
		for(int i = 0; i < lista.length - 1; i++)
		{
			formula = new TeXFormula(lista[i + 1]);

			icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 11);
//			float width = (float) icon.getIconWidth();
			float height = (float) icon.getIconHeight();

			lista[i] += "\\\\ \\vspace{" + 12 + "px}";
			newLatex += lista[i];
		}
		newLatex += lista[lista.length - 1];

		return newLatex;
	}

	public static ImgTemplate toSVGImgTemplate(String latex, PdfWriter pdfWriter, int widthLimit, boolean resolucao) throws IOException
	{
		if(resolucao)
			latex = includeLineBreak(latex, widthLimit);

		TeXFormula formula = new TeXFormula(latex);
		formula.setColor(new Color(102, 102, 102));

		TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 11);
//        TeXIcon icon = formula.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(20).build();
//        TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 12,TeXConstants.UNIT_CM,4,0);
		icon.setInsets(new Insets(0, 0, 0, 0));
		icon.setForeground(new Color(0, 0, 0));
		
		float width = (float) icon.getIconWidth();
		float height = (float) icon.getIconHeight();

		PdfContentByte cb = pdfWriter.getDirectContent();
		PdfTemplate template = cb.createTemplate(width, height);
		Graphics2D g2 = new PdfGraphics2D(template, width, height, true);
		icon.paintIcon(null, g2, 0, 0);
		g2.dispose();

		ImgTemplate imgTemplate = null;
		try
		{
			imgTemplate = new ImgTemplate(template);
		}
		catch(BadElementException e)
		{
			e.printStackTrace();
		}
//        scaleToFit(imgTemplate, context.getDocumentArtBox());
		return imgTemplate;
	}

	public static Image toImage(String latex) throws IOException
	{
		TeXFormula formula = new TeXFormula(latex);
		TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 12);
		icon.setInsets(new Insets(5, 5, 5, 5));
		icon.setForeground(new Color(0, 0, 0));
		int width = icon.getIconWidth();
		int height = icon.getIconHeight();

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		g2.setColor(Color.white);
		g2.fillRect(0, 0, width, height);
		icon.paintIcon(null, g2, 0, 0);
		g2.dispose();

		return image;
	}

	public static void SVGTo(String inSVG, String out, int type)
	{
		AbstractFOPTranscoder trans;
		switch(type) {
		case PDF:
			trans = new PDFTranscoder();
			break;
		case PS:
			trans = new PSTranscoder();
			break;
		case EPS:
			trans = new EPSTranscoder();
			break;
		default:
			trans = null;
		}

		try
		{
			String filename = "target/" + inSVG;
			TranscoderInput input = new TranscoderInput(new FileInputStream(filename));
			OutputStream os = new FileOutputStream("target/" + out);
			TranscoderOutput output = new TranscoderOutput(os);
			trans.transcode(input, output);
			os.flush();
			os.close();
		}
		catch(Exception e)
		{
			System.out.println("Problem when exporting " + inSVG + " to " + out + "!\n" + e.toString());
			throw new RuntimeException(e);
		}
	}
	
	

	public static void main(String[] args)
	{
		String latex = "\\sqrt{490} \\times \\sqrt{10}=\\sqrt{490\\times10}=\\sqrt{4900}=\\sqrt{2^{2} \\times 5^{2} \\times 7^{2}}= 2 \\times 5 \\times 7=70";
		String resultado = includeLineBreak(latex,180);
		
		System.out.println(resultado);

	}
}