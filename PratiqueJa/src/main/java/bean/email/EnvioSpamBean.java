package bean.email;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.io.IOUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import bean.download.Diretorio;
import bean.download.PoolNomesBean;
import bean.ebook.HeaderEBook;
import bean.exercicio.ConfigDownload;
import dao.exercicio.ExercicioPadraoDAO;
import dao.publicacao.CtaDAO;
import dao.email.ConfigSpamDAO;
import dao.email.ProgramacaoSpamDAO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletContext;
import modelo.DocumentoFile;
import modelo.academico.AssuntoCurso;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.Nivel;
import modelo.publicacao.FinalidadeCta;
import modelo.email.ConfigSpam;
import modelo.email.ProgramacaoSpam;
import modelo.usuario.Usuario;
import pdf.exercicio.GerarLatexExercicio;
import pdf.ebook.ItemSumario;
import service.EmailService;

@Named
@ApplicationScoped
public class EnvioSpamBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private ProgramacaoSpamDAO programacaoSpamDAO;

	@Inject
	private ConfigSpamDAO configSpamDAO;

	private List<ProgramacaoSpam> programacoesSpams = new ArrayList<ProgramacaoSpam>();

	@Inject
	private ProgramacaoSpam programacaoSpam;

	@Inject
	private EmailService emailService;

	private static Thread thread;

	private ConfigSpam configSpam;

	@Inject
	private CtaDAO ctaDAO;

	@Inject
	private ExercicioPadraoDAO exercicioPadraoDAO;

	@Inject
	private PoolNomesBean poolNomesBean;

	@Inject
    private ServletContext servletContext;

	private Diretorio diretorio;

	private Document document;
	private PdfContentByte pageContentByte;
	private PdfWriter pdfWriter;

	List<AssuntoCurso> assuntosCurso;
	List<ItemSumario> itens;

	ByteArrayOutputStream baos;
	PdfReader readerOriginal, reader;
	HeaderEBook header;
	PdfImportedPage page;

	public void enviarProgramacao()
	{
		programacoesSpams=programacaoSpamDAO.hoje();
		System.out.println("size: "+programacoesSpams.size());
		for(ProgramacaoSpam programacaoSpam : programacoesSpams)
		{
			this.programacaoSpam=programacaoSpam;
			configSpam=programacaoSpam.getConfigSpam();
			if(configSpam.podeGerar())
			{
				System.out.println("gerando conteudo para Spam");
				gerarListaExercicio();

				configSpam.setUltimoEnvio(LocalDate.now());
				configSpam = configSpamDAO.salvar(configSpam);
				programacaoSpam.setConfigSpam(configSpam);

				programacaoSpam.setOrdem(configSpam.getProgramacoesSpam().size()-1);
				salvar(programacaoSpam);
			}
		}
	}

	public void gerarListaExercicio()
	{
		System.out.println("gerarListaExercicio");
		AssuntoCurso assuntoCurso = programacaoSpam.getAssuntoCurso();

		ByteArrayOutputStream exercicioOutputStream= construirListasExercicios(assuntoCurso,configSpam);
		InputStream inStream = new ByteArrayInputStream(exercicioOutputStream.toByteArray());

		DocumentoFile documentoFile = new DocumentoFile();
		try
		{
			SerialBlob serialBlob = new SerialBlob(IOUtils.toByteArray(inStream));
			documentoFile.setFile(serialBlob);
			documentoFile.setEndDocumentacao((assuntoCurso.getOrdem()+1)+"_"+assuntoCurso.getNome()+".pdf");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		enviarEmail("Lista de exercícios do Pratique Já",documentoFile,assuntoCurso.getNome());
	}

	private void enviarEmail(String assuntoEmail, DocumentoFile documentoFile, String assunto)
	{
		List<Usuario> usuarios=configSpamDAO.usuarioSpam();
		for(Usuario usuario : usuarios)
		{
			List<DocumentoFile> documentosFile = new ArrayList<DocumentoFile>();
			documentosFile.add(documentoFile.clone());

			String mensagem="Olá, "+usuario.getFirstNome()+".\r\n\r\n";
			mensagem+="Segue em anexo a nova lista de exercícios sobre o assunto: "+assunto+".\r\n\r\n";
			mensagem+="📝 Baixe agora e comece a estudar:\r\n\r\n";
			mensagem+=ctaDAO.getAnyCta(FinalidadeCta.Spam)+"\r\n\r\n";
			mensagem+="Lembre-se: você receberá uma nova lista a cada 3 dias, para manter o ritmo e evoluir de forma constante.\r\n\r\n";
			mensagem+="Bons estudos!\r\n";
			mensagem+="Equipe do Pratique Já\r\n";
			mensagem+="📚 pratiqueja.com";

			emailService.adicionar(usuario.getEmail(), assuntoEmail, mensagem, documentosFile);
		}
	}

	public ByteArrayOutputStream construirListasExercicios(AssuntoCurso assuntoCurso, ConfigSpam configSpam)
	{
		System.out.println("construirListasExercicios");

		ExercicioPadrao exercicio;

		diretorio = poolNomesBean.criarDiretorio();
		baos = new ByteArrayOutputStream();
		document = new Document();

		try
		{
			pdfWriter = PdfWriter.getInstance(document, baos);
			document.open();
			pageContentByte = pdfWriter.getDirectContent();

			addDocumento("/pdf/" + assuntoCurso.getModulo().toString().toLowerCase() + "/" + assuntoCurso.getChave() + ".pdf",false);
			List<Nivel> niveis=Arrays.asList(Nivel.values());
			for(Nivel nivel : niveis)
			{
				exercicio = exercicioPadraoDAO.buscar(assuntoCurso, nivel);
				for(int i = 0; i < configSpam.qtn(nivel); i++)
					addExercicioLista(exercicio);
			}

			document.close();
			pdfWriter.close();

			poolNomesBean.freeDiretorio(diretorio);

			return baos;
		}
		catch(DocumentException e1)
		{
			e1.printStackTrace();
		}
		return null;
	}

	public void addDocumento(String endereco, boolean addPagePar)
	{
		File file = new File(servletContext.getRealPath(endereco));
		addDocumento(file,addPagePar);
	}

	public void addDocumento(File file, boolean addPagePar)
	{
		try
		{
			InputStream inputStream = new FileInputStream(file);
			reader = new PdfReader(inputStream);
			for(int j = 1; j <= reader.getNumberOfPages(); j++)
			{
				document.newPage();
				page = pdfWriter.getImportedPage(reader, j);
				pageContentByte.addTemplate(page, 0, 0);
			}
			if(addPagePar&&reader.getNumberOfPages() % 2 == 1)
			{
				document.newPage();
				document.add(new Paragraph("     "));
			}

			pdfWriter.freeReader(reader);
			reader.close();
			inputStream.close();
		}
		catch(IOException | DocumentException e1)
		{
			e1.printStackTrace();
		}
	}

	public void addExercicioLista(ExercicioPadrao exercicio)
	{
		GerarLatexExercicio gerarLatex = new GerarLatexExercicio(diretorio);
		ConfigDownload configDownload=new ConfigDownload();
		configDownload.setIdentificacao(false);

		gerarLatex.setServletContext(servletContext);
		gerarLatex.gerarPDFExercicio(exercicio,configDownload);
		gerarLatex.gerar();

		File file = new File(diretorio.getEnderecoPdf());
		addDocumento(file,false);
	}

	public void salvar(ProgramacaoSpam programacaoSpam)
	{
		ConfigSpam configSpam=programacaoSpam.getConfigSpam();
		try
		{
			programacaoSpam=programacaoSpamDAO.salvar(programacaoSpam);
			configSpam.getProgramacoesSpam().remove(programacaoSpam);
			configSpam.getProgramacoesSpam().add(programacaoSpam.getOrdem(),programacaoSpam);

			organizarOrdem();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void organizarOrdem()
	{
		ConfigSpam configSpam=programacaoSpam.getConfigSpam();
		List<ProgramacaoSpam> programacoesSpam=configSpam.getProgramacoesSpam();
		ProgramacaoSpam programacaoSpam;
		for(int i = 0; i < programacoesSpam.size(); i++)
		{
			programacaoSpam=programacoesSpam.get(i);
			programacaoSpam.setOrdem(i);
			programacaoSpam.updateData();
			programacaoSpamDAO.salvar(programacaoSpam);
		}
	}

	@PostConstruct
	public void init()
	{
		System.out.println("----------------Envio Spam iniciado----------------");
		EnvioSpam envioSpam=new EnvioSpam(this);
		thread = getInstance(envioSpam);
		if(!thread.isAlive())
			thread.start();
	}

	public static Thread getInstance(EnvioSpam envioSpam)
	{
		if(thread == null)
			thread = new Thread(envioSpam);

		return thread;
	}

	public void acorda()
	{
		thread.interrupt();
	}

	public ServletContext getServletContext()
	{
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext)
	{
		this.servletContext = servletContext;
	}

}
