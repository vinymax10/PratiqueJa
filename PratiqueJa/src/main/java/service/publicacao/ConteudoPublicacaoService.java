package service.publicacao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.io.IOUtils;

import bean.download.Diretorio;
import dao.publicacao.CtaDAO;
import dao.publicacao.HashtagDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import modelo.DocumentoFile;
import modelo.exercicio.ExercicioPadrao;
import modelo.publicacao.ConfigPost;
import modelo.publicacao.FinalidadeCta;
import modelo.publicacao.PerfilCriador;
import modelo.publicacao.ProgramacaoPost;
import pdf.social.InstagramFeed;
import pdf.social.TikTok;
import service.configuracao.DiretorioService;
import service.email.EmailService;

@ApplicationScoped
public class ConteudoPublicacaoService
{
	@Inject
	private DiretorioService diretorioService;

	@Inject
	private CtaDAO ctaDAO;

	@Inject
	private HashtagDAO hashtagDAO;

	@Inject
	private EmailService emailService;

	public void gerarConteudoFeed(ExercicioPadrao exercicioPadrao, ProgramacaoPost programacaoPost)
	{
		Diretorio diretorio = diretorioService.criarDiretorio();
		InstagramFeed gerarLatex = new InstagramFeed(diretorio);

		gerarLatex.gerarPDFExercicio(exercicioPadrao, programacaoPost);
		gerarLatex.gerarPDF();
		gerarLatex.convertPNG();

		enviarEmail("Instagram Feed", diretorio, "feed", exercicioPadrao, programacaoPost);

		diretorioService.freeDiretorio(diretorio);
	}

	public void gerarConteudoReel(ExercicioPadrao exercicioPadrao, ProgramacaoPost programacaoPost)
	{
		Diretorio diretorio = diretorioService.criarDiretorio();
		TikTok gerarLatex = new TikTok(diretorio);

		gerarLatex.gerarPDFExercicio(exercicioPadrao, programacaoPost);
		gerarLatex.gerarPDF();
		gerarLatex.convertPNG();

		enviarEmail("Instagram Reel", diretorio, "reel", exercicioPadrao, programacaoPost);

		diretorioService.freeDiretorio(diretorio);
	}

	private void enviarEmail(String assuntoEmail, Diretorio diretorio, String feed,
	ExercicioPadrao exercicioPadrao, ProgramacaoPost programacaoPost)
	{
		ConfigPost configPost = programacaoPost.getConfigPost();
		String email = configPost.getUsuario().getEmail();
		String nome = configPost.getUsuario().getFirstNome();

		List<DocumentoFile> documentosFile = new ArrayList<>();
		documentosFile.add(buildDocumentoFile(diretorio.getEnderecoExercicioPNG(), "Exercicio.png"));
		documentosFile.add(buildDocumentoFile(diretorio.getEnderecoResolucaoPNG(), "Resolucao.png"));

		String mensagem = "Olá, " + nome + ".\r\n\r\n";
		mensagem += "Segue em anexo o material preparado para sua próxima postagem no Instagram:\r\n";
		mensagem += "📌 Carrossel de imagens já ajustado para o " + feed + ".\r\n";
		mensagem += "Assunto: " + exercicioPadrao.getAssunto().getNome() + "\r\n";
		mensagem += "Nível: " + exercicioPadrao.getNivelRomano() + "\r\n\r\n";
		mensagem += "✍️ Texto sugestivo para a legenda, que você pode usar como está ou adaptar ao seu estilo de comunicação.\r\n\r\n";
		mensagem += "---------------------------------------------\r\n";
		mensagem += exercicioPadrao.getEnunciadoSingular() + "\r\n\r\n";

		if(configPost.getPerfilCriador() == PerfilCriador.Master)
			mensagem += ctaDAO.getAnyCta(configPost) + "\r\n\r\n";
		else
			mensagem += ctaDAO.getAnyCta(FinalidadeCta.Ensino) + "\r\n\r\n";

		if(configPost.getPerfilCriador() != PerfilCriador.Basico)
		{
			mensagem += exercicioPadrao.getAssunto().getHashtag() + " ";
			mensagem += hashtagDAO.getAny(3) + "\r\n";
		}

		mensagem += "---------------------------------------------\r\n\r\n";
		mensagem += "O objetivo deste conteúdo é reforçar seu posicionamento, engajar sua audiência e trazer valor para seus seguidores de forma simples e estratégica.\r\n\r\n";
		mensagem += "Bons posts e até a próxima entrega!\r\n\r\n";
		mensagem += "Abraço,\r\n\r\n";
		mensagem += "Equipe do Pratique Já";

		emailService.adicionar(email, assuntoEmail, mensagem, documentosFile);
	}

	private DocumentoFile buildDocumentoFile(String endFile, String nomeFile)
	{
		File initialFile = new File(endFile);
		DocumentoFile documentoFile = new DocumentoFile();
		try
		{
			InputStream inStream = new FileInputStream(initialFile);
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
}
