package bean.instagram;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.io.IOUtils;
import org.omnifaces.cdi.Startup;

import util.ColorHolder;
import bean.download.Diretorio;
import bean.download.PoolNomesBean;
import dao.instagram.ConfigPostDAO;
import dao.instagram.CtaDAO;
import dao.instagram.HashtagDAO;
import dao.instagram.ProgramacaoPostDAO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.DocumentoFile;
import modelo.exercicio.ExercicioPadrao;
import modelo.instagram.ConfigPost;
import modelo.instagram.FinalidadeCta;
import modelo.instagram.PerfilCriador;
import modelo.instagram.ProgramacaoPost;
import modelo.usuario.Usuario;
import pdf.social.InstagramFeed;
import pdf.social.TikTok;
import service.EmailService;

@Startup
@Named
@ApplicationScoped
public class EnvioPostBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private ProgramacaoPostDAO programacaoPostDAO;

	@Inject
	private ConfigPostDAO configPostDAO;
	
	private List<ProgramacaoPost> programacoesPosts = new ArrayList<ProgramacaoPost>();
	
	@Inject 
	private ProgramacaoPost programacaoPost;
	
	@Inject
	private PoolNomesBean poolNomesBean;
	
	@Inject
	private CtaDAO ctaDAO;
	
	@Inject
	private HashtagDAO hashtagDAO;
	
	@Inject
	private EmailService emailService;
	
	private String email, nome;
	
	private static Thread thread;

	public void enviarProgramacao()
	{
		ConfigPost configPost;
		Usuario usuario;
		programacoesPosts=programacaoPostDAO.hoje();
		System.out.println("size: "+programacoesPosts.size());
		for(ProgramacaoPost programacaoPost : programacoesPosts)
		{
			this.programacaoPost=programacaoPost;
			configPost=programacaoPost.getConfigPost();
			if(configPost.podeGerar())
			{
				usuario=configPost.getUsuario();
				System.out.println("gerando conteudo para "+usuario.getNome()
				+"\n"+programacaoPost);
				email=configPost.getUsuario().getEmail();
				nome=configPost.getUsuario().getFirstNome();
				ColorHolder.setCOLOR(configPost.getCorFonte());
				ColorHolder.setFORMULA(configPost.getCorFormula());
				
				for(ExercicioPadrao exercicioPadrao : programacaoPost.getAssuntoCurso().getExerciciosPadrao())
				{
					gerarConteudoFeed(exercicioPadrao);
					gerarConteudoReel(exercicioPadrao);
				}
				
				ColorHolder.clear();
				
				if(programacaoPost.isAvulsa())
				{
					configPost.getProgramacoesPost().remove(programacaoPost);
					programacaoPostDAO.remover(programacaoPost);
					organizarOrdem();
				}
				else
				{
					configPost.setUltimoEnvio(LocalDate.now());
					configPost = configPostDAO.salvar(configPost);
					programacaoPost.setConfigPost(configPost);
					programacaoPost.setOrdem(configPost.getProgramacoesPost().size()-1);
					salvar(programacaoPost);
				}
			}
		}
	}
	
	public void salvar(ProgramacaoPost programacaoPost)
	{
		ConfigPost configPost=programacaoPost.getConfigPost();
		try
		{
			programacaoPost=programacaoPostDAO.salvar(programacaoPost);
			configPost.getProgramacoesPost().remove(programacaoPost);
			configPost.getProgramacoesPost().add(programacaoPost.getOrdem(),programacaoPost);

			organizarOrdem();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void organizarOrdem()
	{
		ConfigPost configPost=programacaoPost.getConfigPost();
		List<ProgramacaoPost> programacoesPost=configPost.getProgramacoesPost();
		ProgramacaoPost programacaoPost;
		for(int i = 0; i < programacoesPost.size(); i++)
		{
			programacaoPost=programacoesPost.get(i);
			programacaoPost.setOrdem(i);
			programacaoPost.updateData();
			programacaoPostDAO.salvar(programacaoPost);
		}
	}
	
	public void gerarConteudoFeed(ExercicioPadrao exercicioPadrao)
	{
		Diretorio diretorio = poolNomesBean.criarDiretorio();
		InstagramFeed gerarLatex=new InstagramFeed(diretorio);

		gerarLatex.gerarPDFExercicio(exercicioPadrao,programacaoPost);
		gerarLatex.gerarPDF();
		gerarLatex.convertPNG();

		enviarEmail("Instagram Feed",diretorio,"feed",exercicioPadrao);
		
		poolNomesBean.freeDiretorio(diretorio);
	}
	
	public void gerarConteudoReel(ExercicioPadrao exercicioPadrao)
	{
		Diretorio diretorio = poolNomesBean.criarDiretorio();
		TikTok gerarLatex=new TikTok(diretorio);
		
		gerarLatex.gerarPDFExercicio(exercicioPadrao,programacaoPost);
		gerarLatex.gerarPDF();
		gerarLatex.convertPNG();

		enviarEmail("Instagram Reel",diretorio,"reel",exercicioPadrao);
		
		poolNomesBean.freeDiretorio(diretorio);
	}
	
	private void enviarEmail(String assuntoEmail, Diretorio diretorio,
	String feed, ExercicioPadrao exercicioPadrao)
	{
		List<DocumentoFile> documentosFile = new ArrayList<DocumentoFile>();
		DocumentoFile docExercicio = buildDocumentoFile(diretorio.getEnderecoExercicioPNG(),"Exercicio.png");
		DocumentoFile docResolucao = buildDocumentoFile(diretorio.getEnderecoResolucaoPNG(),"Resolucao.png");
		
		documentosFile.add(docExercicio);
		documentosFile.add(docResolucao);
		
		String mensagem="Olá, "+nome+".\r\n\r\n";
		mensagem+="Segue em anexo o material preparado para sua próxima postagem no Instagram:\r\n";
		mensagem+="📌 Carrossel de imagens já ajustado para o "+feed+".\r\n";
		mensagem+="Assunto: "+exercicioPadrao.getAssuntoCurso().getNome()+"\r\n";
		mensagem+="Nível: "+exercicioPadrao.getNivelRomano()+"\r\n\r\n";
		mensagem+="✍️ Texto sugestivo para a legenda, que você pode usar como está ou adaptar ao seu estilo de comunicação.\r\n\r\n";
		mensagem+="---------------------------------------------\r\n";
		mensagem+=exercicioPadrao.getEnunciadoSingular()+"\r\n\r\n";
		ConfigPost configPost=programacaoPost.getConfigPost();

		if(configPost.getPerfilCriador()==PerfilCriador.Master)
			mensagem+=ctaDAO.getAnyCta(configPost)+"\r\n\r\n";
		else
			mensagem+=ctaDAO.getAnyCta(FinalidadeCta.Ensino)+"\r\n\r\n";
		
		if(configPost.getPerfilCriador()!=PerfilCriador.Basico)
		{
			mensagem+=exercicioPadrao.getAssuntoCurso().getHashtag()+" ";
			mensagem+=hashtagDAO.getAny(3)+"\r\n";
		}
		
		mensagem+="---------------------------------------------\r\n\r\n";
		mensagem+="O objetivo deste conteúdo é reforçar seu posicionamento, engajar sua audiência e trazer valor para seus seguidores de forma simples e estratégica.\r\n\r\n";
		mensagem+="Bons posts e até a próxima entrega!\r\n\r\n";
		mensagem+="Abraço,\r\n\r\n";
		mensagem+="Equipe do Pratique Já";
		
		emailService.adicionar(email, assuntoEmail, mensagem, documentosFile);
	}
	
	private DocumentoFile buildDocumentoFile(String endFile, String nomeFile)
	{
		File initialFile = new File(endFile);
		DocumentoFile documentoFile = new DocumentoFile();
		InputStream inStream;
		try
		{
			inStream = new FileInputStream(initialFile);
			SerialBlob serialBlob = new SerialBlob(IOUtils.toByteArray(inStream));
			documentoFile.setFile(serialBlob);
			documentoFile.setEndDocumentacao(nomeFile);
		}
		catch(IOException | SQLException e)
		{
			e.printStackTrace();
		}
		
		return documentoFile;
	}
	
	@PostConstruct
	public void init()
	{
		System.out.println("----------------Envio Post iniciado----------------");
		EnvioPost envioPost=new EnvioPost(this);
		thread = getInstance(envioPost);
		if(!thread.isAlive())
			thread.start();
	}
	
	public static Thread getInstance(EnvioPost envioPost)
	{
		if(thread == null)
			thread = new Thread(envioPost);

		return thread;
	}
	
	public void acorda()
	{
		thread.interrupt();
	}

}
