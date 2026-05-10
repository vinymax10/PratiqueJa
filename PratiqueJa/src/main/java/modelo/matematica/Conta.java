package modelo.matematica;

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
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.javers.core.metamodel.annotation.DiffIgnore;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import util.StringAux;
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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import matematica.Correcao;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;
import modelo.exercicio.Exercicio;
import modelo.exercicio.TipoExercicio;
import modelo.teste.EtapaTeste;
import pdf.util.Convert;

@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "exercicio", "etapaTeste", "file", "imagemResolucao", "baos", "baosResolucao", "rand" })
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Conta", length = 255)
public abstract class Conta implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@DiffIgnore
	@ManyToOne
	private Exercicio exercicio;

	@DiffIgnore
	@ManyToOne
	private EtapaTeste etapaTeste;

	@AuditLabel(value = "tipo de exercício")
	private TipoExercicio tipoExercicio;

	@AuditLabel(value = "índice")
	protected int indice;

	@Column(length = 1023)
	@Size(max = 1023)
	@AuditLabel(value = "texto LaTeX")
	protected String textLatex;

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "tamanho da fonte LaTeX")
	protected String sizeFontTextLatex;

	@Column(length = 1023)
	@Size(max = 1023)
	@AuditLabel(value = "resolução LaTeX", genero = GeneroGramatical.FEMININO)
	protected String resolucaoLatex;

	@Transient
	protected boolean showResolucao = false;

	@Transient
	protected boolean jaMostrouResolucao = false;

	@Column(length = 1023)
	@Size(max = 1023)
	@AuditLabel(value = "pergunta", genero = GeneroGramatical.FEMININO)
	protected String pergunta;

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "resultado correto")
	protected String resultadoCorreto = "";

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "resultado correto LaTeX")
	protected String resultadoCorretoLatex = "";

	@AuditLabel(value = "resposta do aluno", genero = GeneroGramatical.FEMININO)
	protected String respostaAluno = "";

	@AuditLabel(value = "respondida", genero = GeneroGramatical.FEMININO)
	protected boolean respondida = false;

	protected boolean respostaAlunoBol;

	protected boolean resultadoCorretoBol;

	@Lob
	protected Blob file;

	@Lob
	protected Blob imagemResolucao;

	@Transient
	protected ByteArrayOutputStream baos;

	@Transient
	protected ByteArrayOutputStream baosResolucao;

	@Transient
	protected Random rand = new Random();

	public Conta(int index)
	{
		this.indice = index;
	}

	public boolean possuiResolucao()
	{
		return (resolucaoLatex != null && !resolucaoLatex.equals("")) || baosResolucao != null;
	}

	public boolean possuiResolucaoLatex()
	{
		return resolucaoLatex != null && !resolucaoLatex.equals("");
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

	public boolean isCorreta()
	{
		return Correcao.isCorreta(resultadoCorreto, respostaAluno);
	}

	public void clone(Conta conta)
	{
		this.indice = conta.indice;
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
		this.sizeFontTextLatex = conta.sizeFontTextLatex;
	}

	public void toogleShowResolucao()
	{
		showResolucao = !showResolucao;
	}

	public int getIndex()
	{
		return indice;
	}

	public void setIndex(int index)
	{
		this.indice = index;
	}

	public String getRespostaAluno()
	{
		if(respostaAluno.contains("%"))
			respostaAluno = respostaAluno.replace("\\", "").replace("%", "\\%");
		return respostaAluno;
	}

	public void setRespostaAluno(String respostaAluno)
	{
		this.respostaAluno = respostaAluno.replaceAll("\\.", ",").replaceAll(" ", "");
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

	public StreamedContent getImageStream()
	{
		if(baos == null)
		{
			try
			{
				InputStream inStream = new ByteArrayInputStream(file.getBytes(1, (int) (file.length())));
				StreamedContent image = DefaultStreamedContent.builder().name("image" + indice + ".png").contentType("aplication/pdf").stream(() -> inStream).build();
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
			StreamedContent image2 = DefaultStreamedContent.builder().name("image" + indice + ".png").contentType("aplication/pdf").stream(() -> inStream2).build();
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
				StreamedContent image = DefaultStreamedContent.builder().name("image" + indice + ".png").contentType("aplication/pdf").stream(() -> inStream).build();
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
			StreamedContent image2 = DefaultStreamedContent.builder().name("image" + indice + ".png").contentType("aplication/pdf").stream(() -> inStream2).build();
			return image2;
		}
		return null;
	}

	public void createImage()
	{
		TeXFormula formula = new TeXFormula(textLatex);
		formula.createPNG(TeXConstants.STYLE_DISPLAY, 15, "out.png", Color.white, Color.black);
	}

	public void createImagePNG(String nome)
	{
		TeXFormula formula = new TeXFormula(textLatex);
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
	}

	public ByteArrayOutputStream getBaos()
	{
		if(baos == null && file != null)
		{
			try
			{
				baos = new ByteArrayOutputStream();
				baos.write(file.getBytes(1, (int) file.length()));
			}
			catch(SQLException | IOException e)
			{
				e.printStackTrace();
			}
		}
		return baos;
	}

	public ByteArrayOutputStream getBaosResolucao()
	{
		if(baosResolucao == null && imagemResolucao != null)
		{
			try
			{
				baosResolucao = new ByteArrayOutputStream();
				baosResolucao.write(file.getBytes(1, (int) file.length()));
			}
			catch(SQLException | IOException e)
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

	public String getFileTipoExercicio()
	{
		String prefixo = "/matematica/tipoExercicio/";
		String sufixo = ".xhtml";
		return prefixo + StringAux.toLowerCaseFirst(tipoExercicio.toString()) + sufixo;
	}
}
