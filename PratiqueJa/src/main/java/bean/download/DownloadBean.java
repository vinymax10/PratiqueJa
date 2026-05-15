package bean.download;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import bean.exercicio.ConfigDownload;
import bean.publicacao.ProgramacaoPostBean;
import bean.usuario.ControleAcessoBean;
import bean.util.Mensagem;
import dao.exercicio.ExercicioPadraoDAO;
import dao.questao.QuestaoDAO;
import dao.usuario.UsuarioDAO;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.push.Push;
import jakarta.faces.push.PushContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.academico.AssuntoCurso;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.Nivel;
import modelo.publicacao.ProgramacaoPost;
import modelo.questao.Questao;
import modelo.usuario.Usuario;
import pdf.exercicio.GerarLatexExercicio;
import pdf.questao.GerarLatexQuestao;
import pdf.social.InstagramFeed;
import pdf.social.TikTok;
import util.ColorHolder;
import web.session.Sessao;

@Named
@ViewScoped
public class DownloadBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private SetDownload setDownload;

	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Inject
	private ExercicioPadraoDAO exercicioPadraoDAO;
	
	@Inject
	private PoolNomesBean poolNomesBean;
	
	private Diretorio diretorio;
	
	@Inject 
	@Push(channel = "push")
    private PushContext push;
	
	private double porcentagem;
	private int contPartes=0;
	private int totalPartes;
	private int totalExercicios;
	private int totalQuestoes;
	private int totalPaginas;
	private Document document;
	private PdfContentByte pageContentByte;
	private ConfigDownload configDownloadExercicio;
	private ConfigDownload configDownloadQuestao;
	private PdfWriter pdfWriter;
	
	@Inject
	private QuestaoDAO questaoDAO;
	
	@Inject
	private ControleAcessoBean controleAcessoBean;
	
	@Inject
	private ProgramacaoPostBean programacaoPostBean;
	
	@Inject
	private ProgramacaoPost programacaoPost;

	public StreamedContent download()
	{
		if(controleAcessoBean.verificaEstaLogado())
		{
			if(controleAcessoBean.podeFazerDownloadMassa())
			{
				porcentagem=0;
				System.out.println(setDownload);
				
				configDownloadExercicio = buildConfigDownloadExercicio();		
				configDownloadQuestao = buildConfigDownloadQuestao();
				
				ExercicioPadrao exercicio;
				
				diretorio = poolNomesBean.criarDiretorio();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				document = new Document();
				
				totalPartes=totalPartes();
				totalExercicios=0;
				totalQuestoes=0;
				totalPaginas=0;
				
				if(totalPartes==0)
				{
					if(setDownload.getAssuntosCurso().size()==0)
						Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Escolha pelo menos um Assunto.");
					else
						Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, 
						"Adicione pelo menos uma quantidade Nível 1, 2 ou 3, "
						+ "ou inclua anotação ou inclua questões.");
				}
				else if(totalPartes>1000)
					Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, 
					"Não será possível gerar um pdf com tanta informação. Por favor, escolha menos assuntos"
					+ ", ou diminua a quantidade do número de listas de exercícios.");
				else
				{
					contPartes=0;
					try
					{
						pdfWriter = PdfWriter.getInstance(document, baos);
						document.open();
						pageContentByte = pdfWriter.getDirectContent();
						
						for(AssuntoCurso assuntoCurso : setDownload.getAssuntosCurso())
						{
							System.out.println("assuntoCurso: "+assuntoCurso.getNome());
							if(setDownload.isAnotacao())
								addAnotacao(assuntoCurso);
							
							if(setDownload.getQuantidadeNivel1()>0)
							{
								exercicio= exercicioPadraoDAO.buscar(assuntoCurso,Nivel.Nivel1);
								for(int i = 0; i < setDownload.getQuantidadeNivel1(); i++)
									addExercicio(exercicio);
							}
							
							if(setDownload.getQuantidadeNivel2()>0)
							{
								exercicio= exercicioPadraoDAO.buscar(assuntoCurso,Nivel.Nivel2);
								for(int i = 0; i < setDownload.getQuantidadeNivel2(); i++)
									addExercicio(exercicio);
							}
							
							if(setDownload.getQuantidadeNivel3()>0)
							{
								exercicio= exercicioPadraoDAO.buscar(assuntoCurso,Nivel.Nivel3);
								for(int i = 0; i < setDownload.getQuantidadeNivel3(); i++)
									addExercicio(exercicio);
							}
							
							if(setDownload.isQuestao())
							{
								addQuestao(assuntoCurso);
							}
						}
						
						document.close(); 
						pdfWriter.close();
						
					}
					catch(DocumentException e1)
					{
						e1.printStackTrace();
					}
					
					InputStream inStream = new ByteArrayInputStream(baos.toByteArray());
					
					String nomeFile=setDownload.getNomeArquivo();
					if(nomeFile.equals(""))
					{
						if(setDownload.getAssuntosCurso().size()==1)
							nomeFile=setDownload.getAssuntosCurso().get(0).getChave();
						else
							nomeFile="pratiqueJa";
					}
					
					StreamedContent file = DefaultStreamedContent.builder().name(nomeFile+".pdf")
					.contentType("aplication/pdf").stream(() -> inStream).build();
					
					poolNomesBean.freeDiretorio(diretorio);
					
					controleAcessoBean.registrarDownloadMassa();
					return file;
				}
			}
			else
			{
				controleAcessoBean.showUpgrade("Este recurso está disponível somente para os perfis Prata ou Ouro."
				+ "\nPor favor faça o upgrade de sua conta.");
			}
		}
		
		return null;
	}
	
	public StreamedContent downloadInstagram(boolean feed)
	{
		if(controleAcessoBean.verificaEstaLogado())
		{
			if(controleAcessoBean.podeFazerDownloadMassa())
			{
				programacaoPost=programacaoPostBean.programacaoPostDefault();

				porcentagem=0;
				System.out.println(setDownload);
				
				configDownloadExercicio = buildConfigDownloadExercicio();		
				configDownloadQuestao = buildConfigDownloadQuestao();
				
				ExercicioPadrao exercicio;
				
				ColorHolder.setCOLOR(programacaoPost.getConfigPost().getCorFonte());
				ColorHolder.setFORMULA(programacaoPost.getConfigPost().getCorFormula());
				
				diretorio = poolNomesBean.criarDiretorio();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				
				float larguraPixels = 259f;
		        float alturaPixels;
		        
		        if(feed)
		        	alturaPixels = 323.75f;
		        else
		        	alturaPixels = 460.5f;
		        
		        Rectangle tamanhoPagina = new Rectangle(larguraPixels, alturaPixels);
		        document = new Document(tamanhoPagina);
		        document.setPageSize(tamanhoPagina);
		        
		        totalPartes=2*setDownload.getAssuntosCurso().size()*
				(setDownload.getQuantidadeNivel1()
				+setDownload.getQuantidadeNivel2()
				+setDownload.getQuantidadeNivel3());
				totalExercicios=0;
				totalQuestoes=0;
				totalPaginas=0;
				contPartes=0;
				try
				{
					pdfWriter = PdfWriter.getInstance(document, baos);
					document.open();
					pageContentByte = pdfWriter.getDirectContent();
					
					for(AssuntoCurso assuntoCurso : setDownload.getAssuntosCurso())
					{
						System.out.println("assuntoCurso: "+assuntoCurso.getNome());
						
						if(setDownload.getQuantidadeNivel1()>0)
						{
							exercicio= exercicioPadraoDAO.buscar(assuntoCurso,Nivel.Nivel1);
							for(int i = 0; i < setDownload.getQuantidadeNivel1(); i++)
								addExercicioInstagram(exercicio,feed);
						}
						
						if(setDownload.getQuantidadeNivel2()>0)
						{
							exercicio= exercicioPadraoDAO.buscar(assuntoCurso,Nivel.Nivel2);
							for(int i = 0; i < setDownload.getQuantidadeNivel2(); i++)
								addExercicioInstagram(exercicio,feed);
						}
						
						if(setDownload.getQuantidadeNivel3()>0)
						{
							exercicio= exercicioPadraoDAO.buscar(assuntoCurso,Nivel.Nivel3);
							for(int i = 0; i < setDownload.getQuantidadeNivel3(); i++)
								addExercicioInstagram(exercicio,feed);
						}
						
					}					
					document.close(); 
					pdfWriter.close();
					
				}
				catch(DocumentException e1)
				{
					e1.printStackTrace();
				}
				
				InputStream inStream = new ByteArrayInputStream(baos.toByteArray());
				
				String nomeFile=setDownload.getNomeArquivo();
				if(nomeFile.equals(""))
				{
					if(setDownload.getAssuntosCurso().size()==1)
						nomeFile=setDownload.getAssuntosCurso().get(0).getChave();
					else
						nomeFile="pratiqueJa";
				}
				
				StreamedContent file = DefaultStreamedContent.builder().name(nomeFile+".pdf")
				.contentType("aplication/pdf").stream(() -> inStream).build();
				
				poolNomesBean.freeDiretorio(diretorio);
				ColorHolder.clear();
				
				controleAcessoBean.registrarDownloadMassa();
				return file;
			}
			else
			{
				controleAcessoBean.showUpgrade("Este recurso está disponível somente para os perfis Prata ou Ouro."
				+ "\nPor favor faça o upgrade de sua conta.");
			}
		}
		
		return null;
	}
	
	private int totalPartes()
	{
		int totalPartes=setDownload.getAssuntosCurso().size()*
		(setDownload.getQuantidadeNivel1()
		+setDownload.getQuantidadeNivel2()
		+setDownload.getQuantidadeNivel3());
		
		if(setDownload.isResolucaoExercicio())
			totalPartes*=2;
		
		if(setDownload.isAnotacao())
			totalPartes+=setDownload.getAssuntosCurso().size();
		
		if(setDownload.isQuestao())
		{
			for(AssuntoCurso assuntoCurso : setDownload.getAssuntosCurso())
			{
				if(assuntoCurso.isShowQuestao())
					totalPartes++;
			}
		}
		
		return totalPartes;
	}
	
	public void addAnotacao(AssuntoCurso assuntoCurso)
	{
		PdfImportedPage pdfImportedPage= null;
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        
		try
		{
			File file = new File(externalContext.getRealPath(
			"/pdf/" + assuntoCurso.getModulo().toString().toLowerCase() + "/" + assuntoCurso.getChave() + ".pdf"));
			InputStream inputStream = new FileInputStream(file);
			PdfReader reader = new PdfReader (inputStream);
			totalPaginas+=reader.getNumberOfPages();
			for(int j = 1; j <= reader.getNumberOfPages(); j++)
			{
				document.newPage();
				pdfImportedPage = pdfWriter.getImportedPage(
				reader,j);
				pageContentByte.addTemplate(pdfImportedPage, 0, 0);					
			}
			pdfWriter.freeReader(reader);
			reader.close();
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
		contPartes++;
		porcentagem=(double)contPartes/totalPartes;
		push.send("update");
	}
	
	public void addExercicioInstagram(ExercicioPadrao exercicio, boolean feed)
	{
		PdfImportedPage pdfImportedPage= null;
		System.out.println("add: "+exercicio);

		if(feed)
		{
			InstagramFeed gerarLatex=new InstagramFeed(diretorio);
			gerarLatex.gerarPDFExercicio(exercicio,programacaoPost);
			gerarLatex.gerarPDF();
		}
		else
		{
			TikTok gerarLatex=new TikTok(diretorio);
			gerarLatex.gerarPDFExercicio(exercicio,programacaoPost);
			gerarLatex.gerarPDF();
		}
		
		totalExercicios++;
		try
		{
			File file=new File(diretorio.getEnderecoPdf());
			InputStream inputStream = new FileInputStream(file);
			PdfReader reader = new PdfReader (inputStream);
			totalPaginas+=reader.getNumberOfPages();
			for(int j = 1; j <= reader.getNumberOfPages(); j++)
			{
				document.newPage();
				pdfImportedPage = pdfWriter.getImportedPage(reader,j);
				pageContentByte.addTemplate(pdfImportedPage, 0, 0);					
			}
			pdfWriter.freeReader(reader);
			reader.close();
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
		
		contPartes+=2;
			
		porcentagem=(double)contPartes/totalPartes;
		push.send("update");
	}
	
	public void addExercicio(ExercicioPadrao exercicio)
	{
		PdfImportedPage pdfImportedPage= null;
		System.out.println("add: "+exercicio);
		
		GerarLatexExercicio gerarLatex=new GerarLatexExercicio(diretorio);
		gerarLatex.gerarPDFExercicio(exercicio, configDownloadExercicio);

		gerarLatex.gerar();
		totalExercicios+=exercicio.getQuantidade();
		try
		{
			File file=new File(diretorio.getEnderecoPdf());
			InputStream inputStream = new FileInputStream(file);
			PdfReader reader = new PdfReader (inputStream);
			totalPaginas+=reader.getNumberOfPages();
			for(int j = 1; j <= reader.getNumberOfPages(); j++)
			{
				document.newPage();
				pdfImportedPage = pdfWriter.getImportedPage(reader,j);
				pageContentByte.addTemplate(pdfImportedPage, 0, 0);					
			}
			pdfWriter.freeReader(reader);
			reader.close();
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
		
		contPartes++;
		if(configDownloadExercicio.isResolucao())
			contPartes++;
			
		porcentagem=(double)contPartes/totalPartes;
		push.send("update");
	}
	
	public void addQuestao(AssuntoCurso assuntoCurso)
	{
		PdfImportedPage pdfImportedPage= null;
		List<Questao>  questoes = questaoDAO.buscaAssuntoCurso(assuntoCurso, null, null);
		if(questoes.size()>0)
		{
			GerarLatexQuestao gerarLatex=new GerarLatexQuestao(diretorio);
			gerarLatex.gerarPDFQuestoes(questoes, configDownloadQuestao);
			gerarLatex.gerar();
			totalQuestoes+=questoes.size();

			try
			{
				File file=new File(diretorio.getEnderecoPdf());
				InputStream inputStream = new FileInputStream(file);
				PdfReader reader = new PdfReader (inputStream);
				totalPaginas+=reader.getNumberOfPages();

				for(int j = 1; j <= reader.getNumberOfPages(); j++)
				{
					document.newPage();
					pdfImportedPage = pdfWriter.getImportedPage(reader,j);
					pageContentByte.addTemplate(pdfImportedPage, 0, 0);					
				}
				pdfWriter.freeReader(reader);
				reader.close();
			}
			catch(IOException e1)
			{
				e1.printStackTrace();
			}
			
			contPartes++;
			porcentagem=(double)contPartes/totalPartes;
			push.send("update");
		}
	}
	
	private ConfigDownload buildConfigDownloadExercicio()
	{
		Usuario usuario = Sessao.getUsuarioLogado();
		usuario = usuarioDAO.carrega(usuario.getId());
		setDownload.setUsuario(usuario);
		
		ConfigDownload configDownload=new ConfigDownload();
		configDownload.setIdentificacao(setDownload.isIdentificacaoExercicio());
		configDownload.setResolucao(setDownload.isResolucaoExercicio());
		configDownload.setRespostas(setDownload.isRespostasExercicio());
		configDownload.setUsuario(usuario);
		
		return configDownload;
	}
	
	private ConfigDownload buildConfigDownloadQuestao()
	{
		Usuario usuario = Sessao.getUsuarioLogado();
		usuario = usuarioDAO.carrega(usuario.getId());
		setDownload.setUsuario(usuario);
		
		ConfigDownload configDownload=new ConfigDownload();
		configDownload.setIdentificacao(setDownload.isIdentificacaoQuestao());
		configDownload.setResolucao(setDownload.isResolucaoQuestao());
		configDownload.setRespostas(setDownload.isRespostasQuestao());
		configDownload.setUsuario(usuario);
		
		return configDownload;
	}
	
	public SetDownload getSetDownload()
	{
		return setDownload;
	}

	public void setSetDownload(SetDownload setDownload)
	{
		this.setDownload = setDownload;
	}

	public double getPorcentagem()
	{
		return porcentagem;
	}
	
	public int getPorcentagemInteiro()
	{
		return (int)(porcentagem*100);
	}

	public void setPorcentagem(double porcentagem)
	{
		this.porcentagem = porcentagem;
	}

	public int getTotalExercicios() {
		return totalExercicios;
	}

	public void setTotalExercicios(int totalExercicios) {
		this.totalExercicios = totalExercicios;
	}

	public int getTotalQuestoes() {
		return totalQuestoes;
	}

	public void setTotalQuestoes(int totalQuestoes) {
		this.totalQuestoes = totalQuestoes;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public void setTotalPaginas(int totalPaginas) {
		this.totalPaginas = totalPaginas;
	}
	
}