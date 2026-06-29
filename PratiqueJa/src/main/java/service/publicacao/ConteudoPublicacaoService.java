package service.publicacao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.io.IOUtils;

import bean.download.Diretorio;
import dao.publicacao.CtaDAO;
import pdf.publicacao.InstagramFeed2;
import pdf.publicacao.TikTok2;
import dao.publicacao.HashtagDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import modelo.DocumentoFile;
import modelo.exercicio.ExercicioPadrao;
import modelo.publicacao.ConfigPost;
import modelo.publicacao.FinalidadeCta;
import modelo.publicacao.PerfilCriador;
import modelo.publicacao.ProgramacaoPost;
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
		InstagramFeed2 gerarLatex = new InstagramFeed2(diretorio);

		gerarLatex.gerarPDFExercicio(exercicioPadrao, programacaoPost);
		gerarLatex.gerarPDF();
		gerarLatex.convertPNG();

		enviarEmail("Instagram Feed", diretorio, "feed", exercicioPadrao, programacaoPost);

		diretorioService.freeDiretorio(diretorio);
	}

	public void gerarConteudoReel(ExercicioPadrao exercicioPadrao, ProgramacaoPost programacaoPost)
	{
		Diretorio diretorio = diretorioService.criarDiretorio();
		TikTok2 gerarLatex = new TikTok2(diretorio);

		gerarLatex.gerarPDFExercicio(exercicioPadrao, programacaoPost);
		gerarLatex.gerarPDF();
		gerarLatex.convertPNG();

		enviarEmail("Instagram Reel", diretorio, "reel", exercicioPadrao, programacaoPost);

		diretorioService.freeDiretorio(diretorio);
	}

	/**
	 * Gera um post de feed e devolve os dois PNGs (exercício e resolução), sem enviar e-mail.
	 * Usado pela geração de lote sob demanda, que empacota tudo num ZIP.
	 */
	public byte[][] gerarImagensFeed(ExercicioPadrao exercicioPadrao, ProgramacaoPost programacaoPost)
	{
		Diretorio diretorio = diretorioService.criarDiretorio();
		InstagramFeed2 gerarLatex = new InstagramFeed2(diretorio);

		gerarLatex.gerarPDFExercicio(exercicioPadrao, programacaoPost);
		gerarLatex.gerarPDF();
		gerarLatex.convertPNG();

		byte[][] imagens = lerImagens(diretorio);
		diretorioService.freeDiretorio(diretorio);
		return imagens;
	}

	/** Gera um post de reel e devolve os dois PNGs (exercício e resolução), sem enviar e-mail. */
	public byte[][] gerarImagensReel(ExercicioPadrao exercicioPadrao, ProgramacaoPost programacaoPost)
	{
		Diretorio diretorio = diretorioService.criarDiretorio();
		TikTok2 gerarLatex = new TikTok2(diretorio);

		gerarLatex.gerarPDFExercicio(exercicioPadrao, programacaoPost);
		gerarLatex.gerarPDF();
		gerarLatex.convertPNG();

		byte[][] imagens = lerImagens(diretorio);
		diretorioService.freeDiretorio(diretorio);
		return imagens;
	}

	/**
	 * Legenda pronta para o usuário copiar e colar na publicação: CTA + (para planos pagos) hashtags.
	 * Mesma regra usada no e-mail; reaproveitada no ZIP do lote sob demanda.
	 */
	public String montarLegenda(ExercicioPadrao exercicioPadrao, ConfigPost configPost)
	{
		String cta = configPost.getUsuario().getPerfilCriador() == PerfilCriador.Master
			? ctaDAO.getAnyCta(configPost)
			: ctaDAO.getAnyCta(FinalidadeCta.Ensino);

		String legenda = cta;
		if(configPost.getUsuario().getPerfilCriador() != PerfilCriador.Basico)
			legenda += "\n\n" + exercicioPadrao.getAssunto().getHashtag() + " " + hashtagDAO.getAny(3);

		return legenda;
	}

	/** Lê do diretório os dois PNGs gerados: índice 0 = exercício, 1 = resolução. */
	private byte[][] lerImagens(Diretorio diretorio)
	{
		return new byte[][]{
			lerArquivo(diretorio.getEnderecoExercicioPNG()),
			lerArquivo(diretorio.getEnderecoResolucaoPNG())
		};
	}

	private byte[] lerArquivo(String caminho)
	{
		try
		{
			return Files.readAllBytes(Path.of(caminho));
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return new byte[0];
		}
	}

	private void enviarEmail(String assuntoEmail, Diretorio diretorio, String feed,
	ExercicioPadrao exercicioPadrao, ProgramacaoPost programacaoPost)
	{
		ConfigPost configPost = programacaoPost.getConfigPost();
		String email = configPost.getUsuario().getEmail();
		String nome = configPost.getUsuario().getFirstNome();

		// Nome (e, por consequência, Content-ID) único por e-mail. O cliente de
		// e-mail associa as imagens inline pelo Content-ID; reutilizar o mesmo cid
		// em todo o lote faz o Gmail embaralhar/omitir as imagens aleatoriamente.
		String token = UUID.randomUUID().toString().replace("-", "").substring(0, 12);
		String nomeExercicio = "Exercicio-" + token + ".png";
		String nomeResolucao = "Resolucao-" + token + ".png";

		List<DocumentoFile> documentosFile = new ArrayList<>();
		documentosFile.add(buildDocumentoFile(diretorio.getEnderecoExercicioPNG(), nomeExercicio));
		documentosFile.add(buildDocumentoFile(diretorio.getEnderecoResolucaoPNG(), nomeResolucao));

		// Legenda pronta para o usuário copiar e colar na publicação: CTA + hashtags.
		String legenda = montarLegenda(exercicioPadrao, configPost);

		String html = montarHtml(nome, feed, exercicioPadrao.getAssunto().getNome(),
			exercicioPadrao.getNivelRomano(), legenda, nomeExercicio, nomeResolucao);

		emailService.adicionar(email, assuntoEmail, html, documentosFile);
	}

	/**
	 * Monta o corpo HTML do e-mail: identidade do Pratique Já, prévia das duas
	 * imagens (embutidas via cid, com identificadores únicos por e-mail) e a
	 * legenda pronta para copiar. O corpo inicia com "&lt;" para o
	 * {@code CommonsEmail} reconhecê-lo como HTML.
	 */
	private String montarHtml(String nome, String feed, String assunto, String nivel, String legenda,
		String cidExercicio, String cidResolucao)
	{
		String legendaHtml = escapeHtml(legenda).replace("\r\n", "\n").replace("\n", "<br>");
		nome = escapeHtml(nome);
		assunto = escapeHtml(assunto);
		nivel = escapeHtml(nivel);

		return "<!DOCTYPE html><html><body style=\"margin:0;padding:0;background:#eef1f8;\">"
		+ "<table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"background:#eef1f8;padding:24px 12px;font-family:Arial,Helvetica,sans-serif;\"><tr><td align=\"center\">"
		+ "<table role=\"presentation\" width=\"600\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;width:100%;background:#ffffff;border-radius:16px;overflow:hidden;border:1px solid #e2e8f4;\">"
		+ "<tr><td style=\"padding:20px 28px;border-bottom:3px solid #3b5bdb;\">"
		+ "<span style=\"font-size:22px;font-weight:bold;color:#3b5bdb;\">Pratique<span style=\"color:#de7b40;\">Já</span></span>"
		+ "<span style=\"font-size:12px;color:#8a93a6;float:right;padding-top:9px;\">Material para Instagram</span>"
		+ "</td></tr>"
		+ "<tr><td style=\"padding:26px 28px 6px;\">"
		+ "<p style=\"margin:0 0 6px;font-size:17px;color:#2b3445;\">Olá, <b>" + nome + "</b>! 👋</p>"
		+ "<p style=\"margin:0 0 16px;font-size:14px;color:#6b7689;line-height:1.5;\">Seu material para a próxima publicação (" + feed + ") está pronto. É só baixar as imagens e copiar a legenda abaixo.</p>"
		+ "<span style=\"display:inline-block;background:#eaeefb;color:#3b5bdb;border-radius:8px;padding:5px 12px;font-size:13px;font-weight:bold;margin:0 6px 6px 0;\">" + assunto + "</span>"
		+ "<span style=\"display:inline-block;background:#fceee4;color:#de7b40;border-radius:8px;padding:5px 12px;font-size:13px;font-weight:bold;margin:0 0 6px;\">Nível " + nivel + "</span>"
		+ "</td></tr>"
		+ "<tr><td align=\"center\" style=\"padding:12px 20px 6px;\">"
		+ "<img src=\"cid:" + cidExercicio + "\" alt=\"Exercício\" width=\"250\" style=\"width:250px;max-width:46%;border-radius:12px;border:1px solid #e2e8f4;margin:4px;\">"
		+ "<img src=\"cid:" + cidResolucao + "\" alt=\"Resolução\" width=\"250\" style=\"width:250px;max-width:46%;border-radius:12px;border:1px solid #e2e8f4;margin:4px;\">"
		+ "</td></tr>"
		+ "<tr><td style=\"padding:14px 28px 4px;\">"
		+ "<p style=\"margin:0 0 8px;font-size:14px;font-weight:bold;color:#2b3445;\">✍️ Legenda pronta para postar <span style=\"font-weight:normal;color:#8a93a6;\">(copie e cole)</span></p>"
		+ "<div style=\"background:#f6f8fc;border:1px dashed #c2cce0;border-radius:12px;padding:16px;font-size:14px;color:#2b3445;line-height:1.6;\">" + legendaHtml + "</div>"
		+ "</td></tr>"
		+ "<tr><td style=\"padding:18px 28px 8px;\">"
		+ "<p style=\"margin:0;font-size:13px;color:#6b7689;line-height:1.5;\">📎 As duas imagens também estão anexadas para download. Bons posts! 🚀</p>"
		+ "</td></tr>"
		+ "<tr><td style=\"padding:8px 28px 24px;border-top:1px solid #eef1f8;\">"
		+ "<p style=\"margin:14px 0 0;font-size:13px;color:#3b5bdb;font-weight:bold;\">Equipe do Pratique Já</p>"
		+ "<p style=\"margin:2px 0 0;font-size:12px;color:#8a93a6;\">pratiqueja.com</p>"
		+ "</td></tr>"
		+ "</table></td></tr></table></body></html>";
	}

	/** Escapa os caracteres que quebrariam o HTML (conteúdo vem do banco/usuário). */
	private String escapeHtml(String texto)
	{
		if(texto == null)
			return "";
		return texto.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
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
