package Modelo.Matematica;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import Auxiliar.StringAux;
import Matematica.Correcao;
import Modelo.Entidade;
import Modelo.Exercicio.Exercicio;
import Modelo.Exercicio.Enum.TipoExercicio;
import Modelo.Teste.EtapaTeste;
import Pdf.Convert;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Conta")
public abstract class Conta implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Exercicio exercicio;
	
	@ManyToOne
	private EtapaTeste etapaTeste;
	
	private TipoExercicio tipoExercicio;

	protected int indice;

	@Column(length = 1023)
	@Size(max = 1023)
	protected String textLatex;

	@Column(length = 255)
	@Size(max = 255)
	protected String sizeFontTextLatex;
	
	@Column(length = 1023)
	@Size(max = 1023)
	protected String resolucaoLatex;

	@Transient
	protected boolean showResolucao = false;
	
	@Transient
	protected boolean jaMostrouResolucao = false;

	@Column(length = 1023)
	@Size(max = 1023)
	protected String pergunta;

	@Column(length = 255)
	@Size(max = 255)
	protected String resultadoCorreto = "";
	
	@Column(length = 255)
	@Size(max = 255)
	protected String resultadoCorretoLatex = "";

//	TODO colocar "" aqui
	protected String respostaAluno = "";
//	protected String respostaAluno = ""+3;

//	TODO colocar false aqui
	protected boolean respondida = false;

	protected boolean respostaAlunoBol;

	protected boolean resultadoCorretoBol;

	@Lob
	protected Blob file;

	@Lob
	protected Blob imagemResolucao;

	@Transient
	protected String textGroovy;

	@Transient
	protected ByteArrayOutputStream baos;

	@Transient
	protected ByteArrayOutputStream baosResolucao;

	@Transient
	protected Random rand = new Random();

	@Transient
	protected Binding binding;

	@Transient
	protected GroovyShell shell;

	public Conta()
	{
	}

	public Conta(int index)
	{
		this.indice = index;
	}
	
	public boolean possuiResolucao()
	{
		return (resolucaoLatex!=null&&!resolucaoLatex.equals("")) || baosResolucao!=null;
	}
	public boolean possuiResolucaoLatex()
	{
		return resolucaoLatex!=null&&!resolucaoLatex.equals("");
	}
	
	protected void carregarBlob()
	{
		try
		{
			if(baos != null)
				file = new SerialBlob(baos.toByteArray());

			if(baosResolucao != null)
				imagemResolucao = new SerialBlob(baosResolucao.toByteArray());
		}
		catch(SerialException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	public Long getId()
	{
		return id;
	}
	
	public boolean isCorreta()
	{
		return Correcao.isCorreta(resultadoCorreto,respostaAluno);
	}

	public void clone(Conta conta)
	{
		this.indice = conta.indice;
		this.textGroovy = conta.textGroovy;
		this.textLatex = conta.textLatex;
		this.resultadoCorreto = conta.resultadoCorreto;
		this.respostaAlunoBol = conta.respostaAlunoBol;
		this.resultadoCorretoBol = conta.resultadoCorretoBol;
		this.baos = conta.baos;
		this.baosResolucao = conta.baosResolucao;
		this.imagemResolucao = conta.imagemResolucao;
		this.file = conta.file;
		this.pergunta = conta.pergunta;
		this.resolucaoLatex = conta.resolucaoLatex;
		this.sizeFontTextLatex=conta.sizeFontTextLatex;
	}

	public void toogleShowResolucao()
	{
		showResolucao = !showResolucao;
	}

	@Override
	public String toString()
	{
		return (id != null ? "id=" + id + ", " : "")
		+ (tipoExercicio != null ? "tipoExercicio=" + tipoExercicio + ", " : "") + "indice=" + indice + ", "
		+ (textLatex != null ? "textLatex=" + textLatex + ", " : "")
		+ (resolucaoLatex != null ? "resolucaoLatex=" + resolucaoLatex + ", " : "") + "showResolucao=" + showResolucao
		+ ", jaMostrouResolucao=" + jaMostrouResolucao + ", " + (pergunta != null ? "pergunta=" + pergunta + ", " : "")
		+ (resultadoCorreto != null ? "resultadoCorreto=" + resultadoCorreto + ", " : "")
		+ (respostaAluno != null ? "respostaAluno=" + respostaAluno + ", " : "") + "respondida=" + respondida
		+ ", respostaAlunoBol=" + respostaAlunoBol + ", resultadoCorretoBol=" + resultadoCorretoBol;
	}

	public int getIndex()
	{
		return indice;
	}

	public void setIndex(int index)
	{
		this.indice = index;
	}

	public String getTextGroovy()
	{
		return textGroovy;
	}

	public void setTextGroovy(String textGroovy)
	{
		this.textGroovy = textGroovy;
	}

	public String getTextLatex()
	{
		return textLatex;
	}

	public void setTextLatex(String textLatex)
	{
		this.textLatex = textLatex;
	}

	public String getResultadoCorreto()
	{
		return resultadoCorreto;
	}

	public void setResultadoCorreto(String resultadoCorreto)
	{
		this.resultadoCorreto = resultadoCorreto;
	}

	public String getRespostaAluno()
	{
		if(respostaAluno.contains("%"))
			respostaAluno=respostaAluno.replace("\\", "").replace("%", "\\%");

		return respostaAluno;
	}

	public void setRespostaAluno(String respostaAluno)
	{
		this.respostaAluno = respostaAluno.replaceAll("\\.", ",").replaceAll(" ", "");
	}

	public boolean isRespondida()
	{
		return respondida;
	}

	public void setRespondida(boolean respondida)
	{
		this.respondida = respondida;
	}

	public boolean isRespostaAlunoBol()
	{
		return respostaAlunoBol;
	}

	public String respostaAlunoBolTexto()
	{
		if(respostaAlunoBol)
			return "Sim";
		else
			return "Não";
	}

	public String resultadoCorretoBolTexto()
	{
		if(resultadoCorretoBol)
			return "Sim";
		else
			return "Não";
	}

	public void setRespostaAlunoBol(boolean respostaAlunoBol)
	{
		this.respostaAlunoBol = respostaAlunoBol;
	}

	public boolean isResultadoCorretoBol()
	{
		return resultadoCorretoBol;
	}

	public void setResultadoCorretoBol(boolean resultadoCorretoBol)
	{
		this.resultadoCorretoBol = resultadoCorretoBol;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pergunta, textLatex);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return Objects.equals(pergunta, other.pergunta) && Objects.equals(textLatex, other.textLatex);
	}

	public StreamedContent getImageStream()
	{
		if(baos == null)
		{
			try
			{
				InputStream inStream = new ByteArrayInputStream(file.getBytes(1, (int) (file.length())));

				StreamedContent image = DefaultStreamedContent.builder().name("image" + indice + ".png").contentType("aplication/pdf").stream(() -> inStream)
				.build();

				return image;
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			InputStream inStream2 = new ByteArrayInputStream(baos.toByteArray());
			StreamedContent image2 = DefaultStreamedContent.builder().name("image" + indice + ".png").contentType("aplication/pdf").stream(() -> inStream2)
			.build();

			return image2;
		}

		return null;
	}

	public boolean temImageResolucao()
	{
		return baosResolucao != null || imagemResolucao != null;
	}
	
	public StreamedContent getImageResolucaoStream()
	{
		if(baosResolucao == null && imagemResolucao != null)
		{
			try
			{
				InputStream inStream = new ByteArrayInputStream(imagemResolucao.getBytes(1, (int) (imagemResolucao.length())));

				StreamedContent image = DefaultStreamedContent.builder().name("image" + indice + ".png").contentType("aplication/pdf").stream(() -> inStream)
				.build();

				return image;
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		else if(baosResolucao != null)
		{
			InputStream inStream2 = new ByteArrayInputStream(baosResolucao.toByteArray());
			StreamedContent image2 = DefaultStreamedContent.builder().name("image" + indice + ".png").contentType("aplication/pdf").stream(() -> inStream2)
			.build();

			return image2;
		}

		return null;
	}

	public void createImage()
	{
		TeXFormula formula = new TeXFormula(textLatex);
		formula.createPNG(TeXConstants.STYLE_DISPLAY, 15, "out.png", Color.white, Color.black);
//		Image image= formula.createBufferedImage(TeXConstants.STYLE_DISPLAY, 14, Color.black, Color.white);

//		getLaTeXImage();
//		ImageIcon icon = new ImageIcon(image);
//		Image a=new Image(image);
//		image.getGraphics().drawImage(a, 0, 0, null);
//		return image;
	}

	public void createImagePNG(String nome)
	{
		TeXFormula formula = new TeXFormula(textLatex);
//		Image image= formula.createBufferedImage(TeXConstants.STYLE_DISPLAY, 14, Color.black, Color.white);
//		formula.createPNG(TeXConstants.STYLE_DISPLAY, 15, "out.png",  Color.white,Color.black);
		TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 14);
		int w = icon.getIconWidth();
		int h = icon.getIconHeight();

		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		g2.setColor(Color.white);
		g2.fillRect(0, 0, w, h);
		icon.paintIcon(null, g2, 0, 0);
		g2.dispose();

		File outputfile = new File(nome);
		try
		{
			ImageIO.write(image, "PNG", outputfile);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

//		getLaTeXImage();
//		ImageIcon icon = new ImageIcon(image);
//		Image a=new Image(image);
//		image.getGraphics().drawImage(a, 0, 0, null);
//		return image;
	}

	public ByteArrayOutputStream getBaos()
	{
		if(baos==null&&file!=null)
		{
   	        try
			{
   	        	baos = new ByteArrayOutputStream();
				baos.write(file.getBytes(1, (int) file.length()));
			}
			catch(SQLException|IOException e)
			{
				e.printStackTrace();
			}
		}
		return baos;
	}

	public ByteArrayOutputStream getBaosResolucao()
	{
		if(baosResolucao==null&&imagemResolucao!=null)
		{
   	        try
			{
   	        	baosResolucao = new ByteArrayOutputStream();
   	        	baosResolucao.write(file.getBytes(1, (int) file.length()));
			}
			catch(SQLException|IOException e)
			{
				e.printStackTrace();
			}
		}
		return baosResolucao;
	}

	public void getLaTeXImage()
	{
		try
		{
			Convert.toSVG(textLatex, "Example1.svg", false);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public Exercicio getExercicio()
	{
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio)
	{
		this.exercicio = exercicio;
	}

	public String getPergunta()
	{
		return pergunta;
	}

	public void setPergunta(String pergunta)
	{
		this.pergunta = pergunta;
	}

	public String getResolucaoLatex()
	{
		return resolucaoLatex;
	}

	public void setResolucaoLatex(String resolucaoLatex)
	{
		this.resolucaoLatex = resolucaoLatex;
	}

	public boolean isShowResolucao()
	{
		return showResolucao;
	}

	public void setShowResolucao(boolean showResolucao)
	{
		this.showResolucao = showResolucao;
	}

	public Blob getImagemResolucao()
	{
		return imagemResolucao;
	}

	public void setImagemResolucao(Blob imagemResolucao)
	{
		this.imagemResolucao = imagemResolucao;
	}

	public boolean isJaMostrouResolucao()
	{
		return jaMostrouResolucao;
	}

	public void setJaMostrouResolucao(boolean jaMostrouResolucao)
	{
		this.jaMostrouResolucao = jaMostrouResolucao;
	}

	public EtapaTeste getEtapaTeste()
	{
		return etapaTeste;
	}

	public void setEtapaTeste(EtapaTeste etapaTeste)
	{
		this.etapaTeste = etapaTeste;
	}

	public TipoExercicio getTipoExercicio()
	{
		return tipoExercicio;
	}

	public void setTipoExercicio(TipoExercicio tipoExercicio)
	{
		this.tipoExercicio = tipoExercicio;
	}
	
	public String getSizeFontTextLatex()
	{
		return sizeFontTextLatex;
	}

	public void setSizeFontTextLatex(String sizeFontTextLatex)
	{
		this.sizeFontTextLatex = sizeFontTextLatex;
	}

	public String getFileTipoExercicio()
	{
		String prefixo = "/matematica/tipoExercicio/";
		String sufixo = ".xhtml";
		return prefixo + StringAux.toLowerCaseFirst(tipoExercicio.toString()) + sufixo;
	}
	
	
}