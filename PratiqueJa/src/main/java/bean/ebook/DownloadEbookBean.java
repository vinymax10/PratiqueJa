package bean.ebook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import bean.download.Diretorio;
import bean.download.PoolNomesBean;
import bean.exercicio.ConfigDownload;
import dao.AssuntoCursoDAO;
import dao.exercicio.ExercicioPadraoDAO;
import dao.questao.QuestaoDAO;
import infra.Mensagem;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.push.Push;
import jakarta.faces.push.PushContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.assuntocurso.AssuntoCurso;
import modelo.assuntocurso.Modulo;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.Nivel;
import modelo.questao.Questao;
import modelo.spam.ConfigSpam;
import pdf.latex.ExercicioEBook;
import pdf.latex.FolhaRosto;
import pdf.latex.GerarLatexExercicio;
import pdf.latex.ItemSumario;
import pdf.latex.QuestaoEBook;
import pdf.latex.Resumo;
import pdf.latex.Sumario;

@Named
@ViewScoped
public class DownloadEbookBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Modulo modulo = Modulo.Basico;

	private int numListas = 1;
	
	private int numEBook = 1;

	private boolean capa = false;

	@Inject
	private EdicaoBean edicaoBean;

	@Inject
	private ExercicioPadraoDAO exercicioPadraoDAO;

	@Inject
	private PoolNomesBean poolNomesBean;

	private Diretorio diretorio;
	
	@Inject
	private AssuntoCursoDAO assuntoCursoDAO;

	@Inject
	@Push(channel = "push")
	private PushContext push;

	private double porcentagem;
	private int contPartes = 0;
	private int totalPartes;
	private int numExercicios;
	private int numQuestoes;
	private int numEBookAtual;
	private int totalPaginas,numPaginas;
	
	private Document document;
	private PdfContentByte pageContentByte;
	private PdfWriter pdfWriter;
	@Inject
	private QuestaoDAO questaoDAO;

	List<AssuntoCurso> assuntosCurso;
	List<ItemSumario> itens;
	
	ByteArrayOutputStream finalOutputStream;
	ByteArrayOutputStream baos;
	PdfReader readerOriginal, reader;
	HeaderEBook header;
	PdfImportedPage page;
	InputStream inputStream;
	
	public StreamedContent download()
	{
		finalOutputStream= construirEBook();
		InputStream inStream = new ByteArrayInputStream(finalOutputStream.toByteArray());
		String nomeFile = modulo.getNome() + "_" + edicaoBean.getEdicao().edicaoModulo(modulo);

		StreamedContent file = DefaultStreamedContent.builder().name(nomeFile + ".pdf").contentType("aplication/pdf").stream(() -> inStream).build();
		return file;
	}
	
	private ByteArrayOutputStream construirEBook()
	{
		edicaoBean.salvar();
		assuntosCurso = assuntoCursoDAO.buscar(modulo);
		itens = new ArrayList<ItemSumario>();
		ExercicioPadrao exercicio;
		finalOutputStream = new ByteArrayOutputStream();
		
		diretorio = poolNomesBean.criarDiretorio();
		baos = new ByteArrayOutputStream();
		document = new Document();

		iniciarPorcentagem();
		
		try
		{
			pdfWriter = PdfWriter.getInstance(document, baos);
			document.open();
			pageContentByte = pdfWriter.getDirectContent();
			
			int passoPage=3;
			
			if(capa)
				addDocumento("/pdf/ebook/capa" + modulo + ".pdf",true);
			else
				passoPage+=2;
			
			addFolhaRosto();
			
			int cont=1;
			for(AssuntoCurso assuntoCurso : assuntosCurso)
			{
				ItemSumario itemSumario = new ItemSumario(assuntoCurso.getNome(), "" + cont++, "" + (pdfWriter.getPageNumber()+passoPage));
				System.out.println("itemSumario: " + itemSumario);
				itens.add(itemSumario);
				
				addDocumento("/pdf/" + assuntoCurso.getModulo().toString().toLowerCase() + "/" + assuntoCurso.getChave() + ".pdf",true);
				
				exercicio = exercicioPadraoDAO.buscar(assuntoCurso, Nivel.Nivel1);
				for(int i = 0; i < numListas; i++)
					addExercicioEBook(exercicio);

				exercicio = exercicioPadraoDAO.buscar(assuntoCurso, Nivel.Nivel2);
				for(int i = 0; i < numListas; i++)
					addExercicioEBook(exercicio);

				exercicio = exercicioPadraoDAO.buscar(assuntoCurso, Nivel.Nivel3);
				for(int i = 0; i < numListas; i++)
					addExercicioEBook(exercicio);

				addQuestao(assuntoCurso);
//						break;
			}
			
			if(capa)
				addDocumento("/pdf/ebook/contraCapa" + modulo + ".pdf",true);

			totalPaginas=(pdfWriter.getPageNumber()+2);
			if(!capa)
				totalPaginas+=4;
			
			document.close();
			pdfWriter.close();

			readerOriginal = new PdfReader(new ByteArrayInputStream(baos.toByteArray()));
			
	        document = new Document();
	        pdfWriter = PdfWriter.getInstance(document, finalOutputStream);
	        document.open();
	        
	        header = new HeaderEBook();
	        pdfWriter.setPageEvent(header);
	        
	        pageContentByte = pdfWriter.getDirectContent();

	        int posicaoSumario=2;
	        if(capa)
	        	posicaoSumario+=2;
	        else
	        	header.pagInicial+=2;
	        
	        for (int i = 1; i <= posicaoSumario; i++)
	        {
	        	document.newPage();
	            page = pdfWriter.getImportedPage(readerOriginal, i);
	            pageContentByte.addTemplate(page,0,0);
	        }
			
	        header.addPage=true;
	        
			addSumario();
			int indexAssunto=0;
			
			for (int i = posicaoSumario+1; i <= readerOriginal.getNumberOfPages(); i++) 
			{
				if(itens.size()>(indexAssunto+1)&&itens.get(indexAssunto+1).pagina.equals(""+(i+passoPage-1)))
					indexAssunto++;
				
				AssuntoCurso assuntoCurso=assuntosCurso.get(indexAssunto);
				if(capa&&i>(readerOriginal.getNumberOfPages()-2))
				{
					header.assunto = "";
					header.addPage=false;
				}
				else
					header.assunto = assuntoCurso.getNome();
				
				document.newPage();
	            page = pdfWriter.getImportedPage(readerOriginal, i);
	            pageContentByte.addTemplate(page,0,0);
	        }
			
			document.close();
			pdfWriter.close();
			readerOriginal.close();
			
			poolNomesBean.freeDiretorio(diretorio);
			edicaoBean.incrementa(modulo);

			return finalOutputStream;
		}
		catch(DocumentException|IOException e1)
		{
			e1.printStackTrace();
		}
		return null;
	}
	
	public String gerarEBook()
	{
		numEBookAtual=1;
		try
		{
			for(int i = 0; i < numEBook; i++)
			{
				finalOutputStream= construirEBook();
				InputStream inStream = new ByteArrayInputStream(finalOutputStream.toByteArray());
				
				String nomeFile = modulo.getNome() + "_" + (edicaoBean.getEdicao().edicaoModulo(modulo)-1);
				File file=new File(diretorio.getConfigLatex().getEnderecoEBook()+"/"+nomeFile + ".pdf");
				Files.copy(inStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
				
				numEBookAtual++;
			}
			
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "E-book criado com sucesso.");

		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
		
		return "";
	}
	
	private void iniciarPorcentagem()
	{
		int numeroNiveis=3;
		totalPartes = assuntosCurso.size()+2+(assuntosCurso.size() * numeroNiveis * numListas);
		if(capa)
			totalPartes+=2;
		
		for(AssuntoCurso assuntoCurso : assuntosCurso)
		{
			if(assuntoCurso.isShowQuestao())
				totalPartes++;	
		}
		numExercicios = 0;
		numQuestoes = 0;
		numPaginas = 0;
		contPartes = 0;
		porcentagem = 0;
	}

	public void addDocumento(String endereco, boolean addPagePar)
	{
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		File file = new File(externalContext.getRealPath(endereco));
		addDocumento(file,addPagePar);
	}

	private Resumo montarResumo()
	{
		Resumo resumo=new Resumo();
		resumo.setAssuntos(assuntosCurso.size()+"");
		resumo.setPaginas(totalPaginas+"");
		resumo.setExecicios(numExercicios+"");
		resumo.setQuestoes(numQuestoes+"");
		resumo.setListas(assuntosCurso.size()*numListas*3+"");

		return resumo;
	}
	
	public void addDocumento(File file, boolean addPagePar)
	{
		try
		{
			inputStream = new FileInputStream(file);
			reader = new PdfReader(inputStream);
			numPaginas += reader.getNumberOfPages();
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
				numPaginas++;
			}
			
			pdfWriter.freeReader(reader);
			reader.close();
			inputStream.close();
		}
		catch(IOException | DocumentException e1)
		{
			e1.printStackTrace();
		}
		contPartes++;
		porcentagem = (double) contPartes / totalPartes;
		push.send("update");
		System.out.println(contPartes+"/"+totalPartes);
	}
	
	public void addFolhaRosto()
	{
		FolhaRosto gerarLatex = new FolhaRosto(diretorio);
		gerarLatex.gerarPDFExercicio(modulo,edicaoBean.getEdicao().edicaoModulo(modulo));
		gerarLatex.gerar();

		File file = new File(diretorio.getEnderecoPdf());
		addDocumento(file,false);
	}
	
	public void addSumario()
	{
		Resumo resumo=montarResumo();
		Sumario gerarLatex = new Sumario(diretorio);
		gerarLatex.gerarPDFExercicio(itens,resumo);
		gerarLatex.gerar();

		File file = new File(diretorio.getEnderecoPdf());
		addDocumento(file,true);
	}
	
	public void addExercicioEBook(ExercicioPadrao exercicio)
	{
		ExercicioEBook gerarLatex = new ExercicioEBook(diretorio);
		gerarLatex.gerarPDFExercicio(exercicio);
		gerarLatex.gerar();
		numExercicios += exercicio.getQuantidade();

		File file = new File(diretorio.getEnderecoPdf());
		addDocumento(file,false);
	}

	public void addQuestao(AssuntoCurso assuntoCurso)
	{
		List<Questao>  questoes = questaoDAO.buscaAssuntoCurso(assuntoCurso, null, null);
		if(questoes.size()>0)
		{
			QuestaoEBook gerarLatex=new QuestaoEBook(diretorio);
			gerarLatex.gerarPDFQuestoes(questoes);
			gerarLatex.gerar();
			numQuestoes+=questoes.size();
			File file=new File(diretorio.getEnderecoPdf());
			addDocumento(file,true);
		}
	}
	
	public String gerarListasExercicios()
	{
		assuntosCurso = assuntoCursoDAO.buscar(modulo);
		List<Nivel> niveis=Arrays.asList(Nivel.values());

		totalPartes = (niveis.size()*assuntosCurso.size())+(assuntosCurso.size() * niveis.size() * numListas);
		
		numExercicios = 0;
		numPaginas = 0;
		contPartes = 0;
		porcentagem = 0;
		
		try
		{
			for(AssuntoCurso assuntoCurso : assuntosCurso)
			{
				for(Nivel nivel : niveis)
				{
					finalOutputStream= construirListasExercicios(assuntoCurso,nivel);
					InputStream inStream = new ByteArrayInputStream(finalOutputStream.toByteArray());
					
					String nomeFile = (assuntoCurso.getIndice()+1)+"_"+assuntoCurso.getNome() + "_" + nivel.getNome();
					File file=new File(diretorio.getConfigLatex().getEnderecoEBook()+"/"+nomeFile + ".pdf");
					Files.copy(inStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
				}
			}
			
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Listas de exercícios criada com sucesso.");

		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
		
		return "";
	}
	
	public ByteArrayOutputStream construirListasExercicios(AssuntoCurso assuntoCurso, Nivel nivel)
	{
		ExercicioPadrao exercicio;
		finalOutputStream = new ByteArrayOutputStream();
		
		diretorio = poolNomesBean.criarDiretorio();
		baos = new ByteArrayOutputStream();
		document = new Document();

		try
		{
			pdfWriter = PdfWriter.getInstance(document, baos);
			document.open();
			pageContentByte = pdfWriter.getDirectContent();

			addDocumento("/pdf/" + assuntoCurso.getModulo().toString().toLowerCase() + "/" + assuntoCurso.getChave() + ".pdf",false);
			
			exercicio = exercicioPadraoDAO.buscar(assuntoCurso, nivel);
			for(int i = 0; i < numListas; i++)
				addExercicioLista(exercicio);

			totalPaginas=(pdfWriter.getPageNumber());
			
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
	
	public ByteArrayOutputStream construirListasExercicios(AssuntoCurso assuntoCurso, ConfigSpam configSpam)
	{
		ExercicioPadrao exercicio;
		finalOutputStream = new ByteArrayOutputStream();
		
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

			totalPaginas=(pdfWriter.getPageNumber());
			
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
	
	public void addExercicioLista(ExercicioPadrao exercicio)
	{
		GerarLatexExercicio gerarLatex = new GerarLatexExercicio(diretorio);
		ConfigDownload configDownload=new ConfigDownload();
		configDownload.setIdentificacao(false);
		
		gerarLatex.gerarPDFExercicio(exercicio,configDownload);
		gerarLatex.gerar();
		numExercicios += exercicio.getQuantidade();

		File file = new File(diretorio.getEnderecoPdf());
		addDocumento(file,false);
	}
	

	public double getPorcentagem()
	{
		return porcentagem;
	}

	public int getPorcentagemInteiro()
	{
		return (int) (porcentagem * 100);
	}

	public void setPorcentagem(double porcentagem)
	{
		this.porcentagem = porcentagem;
	}

	public int getTotalPaginas()
	{
		return totalPaginas;
	}

	public void setTotalPaginas(int totalPaginas)
	{
		this.totalPaginas = totalPaginas;
	}

	public Modulo getModulo()
	{
		return modulo;
	}

	public void setModulo(Modulo modulo)
	{
		this.modulo = modulo;
	}

	public int getNumListas()
	{
		return numListas;
	}

	public void setNumListas(int numListas)
	{
		this.numListas = numListas;
	}

	public boolean isCapa()
	{
		return capa;
	}

	public void setCapa(boolean capa)
	{
		this.capa = capa;
	}

	public int getNumExercicios()
	{
		return numExercicios;
	}

	public void setNumExercicios(int numExercicios)
	{
		this.numExercicios = numExercicios;
	}

	public int getNumPaginas()
	{
		return numPaginas;
	}

	public void setNumPaginas(int numPaginas)
	{
		this.numPaginas = numPaginas;
	}

	public int getNumQuestoes()
	{
		return numQuestoes;
	}

	public void setNumQuestoes(int numQuestoes)
	{
		this.numQuestoes = numQuestoes;
	}

	public int getNumEBook()
	{
		return numEBook;
	}

	public void setNumEBook(int numEBook)
	{
		this.numEBook = numEBook;
	}

	public int getNumEBookAtual()
	{
		return numEBookAtual;
	}

	public void setNumEBookAtual(int numEBookAtual)
	{
		this.numEBookAtual = numEBookAtual;
	}

}