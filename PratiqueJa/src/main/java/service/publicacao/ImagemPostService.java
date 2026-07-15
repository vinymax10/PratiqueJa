package service.publicacao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import dao.publicacao.ConfigPostDAO;
import dao.publicacao.ImagemPostDAO;
import exceptions.RelacaoException;
import infra.Graphics;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import modelo.publicacao.ConfigPost;
import modelo.publicacao.ImagemPost;
import org.primefaces.model.file.UploadedFile;
import util.FileAux;
import bean.download.Diretorio;

@ApplicationScoped
public class ImagemPostService
{
	@Inject
	private ImagemPostDAO imagemPostDAO;

	@Inject
	private ConfigPostDAO configPostDAO;

	public List<ImagemPost> listar(ConfigPost configPost, boolean feed)
	{
		return imagemPostDAO.carrega(configPost, feed);
	}

	public void remover(ImagemPost imagem, String endBase) throws RelacaoException
	{
		if(!imagem.getProgramacoes().isEmpty())
			throw new RelacaoException("Não foi possível remover a imagem. Existem programações relacionadas.");

		if(imagem.getEndImagem() != null)
		{
			File file = new File(endBase + imagem.getEndImagem());
			if(file.exists())
				file.delete();
			File thumb = Graphics.thumbFile(file);
			if(thumb.exists())
				thumb.delete();
		}
		imagemPostDAO.remover(imagem);
	}

	public ImagemPost salvar(UploadedFile uploadedFile, ImagemPost imagem, String endBase, String endRel, int width, int height) throws IOException
	{
		String nome = uploadedFile.getFileName();
		if(imagem.getEndImagem() != null)
		{
			File antigo = new File(endBase + imagem.getEndImagem());
			if(antigo.exists())
				antigo.delete();
			File antigoThumb = Graphics.thumbFile(antigo);
			if(antigoThumb.exists())
				antigoThumb.delete();
		}
		byte[] bytes = Graphics.shapeImage(uploadedFile, width, height);
		FileAux.gravarFile(endBase + endRel, nome, bytes);
		// hook: gera o thumbnail (10%) do fundo recém-enviado, sem quebrar o upload se falhar.
		try { Graphics.gerarThumb(new File(endBase + endRel, nome), 0.1); }
		catch(Exception e) { e.printStackTrace(); }
		imagem.setEndImagem(endRel + nome);
		imagem.setNome(nome);
		return imagemPostDAO.salvar(imagem);
	}

	public void gravarTeste(boolean feed, Diretorio diretorio, ConfigPost configPost) throws IOException
	{
		long id = configPost.getUsuario().getId();
		String endBase = diretorio.getConfig().getEndereco();
		String endRel = feed
			? "/images/background/" + id + "/teste/feed"
			: "/images/background/" + id + "/teste/reel";

		File theDir = new File(endBase + endRel);
		if(!theDir.exists())
			theDir.mkdirs();

		ImagemPost imgExe = feed ? configPost.getTesteExeFeed() : configPost.getTesteExeReel();
		if(imgExe == null) imgExe = new ImagemPost();
		imgExe = copiarImagem(diretorio.getEnderecoExercicioPNG(), endBase, endRel, "/Exercicio.png", feed, imgExe);
		if(feed) configPost.setTesteExeFeed(imgExe); else configPost.setTesteExeReel(imgExe);

		ImagemPost imgRes = feed ? configPost.getTesteResFeed() : configPost.getTesteResReel();
		if(imgRes == null) imgRes = new ImagemPost();
		imgRes = copiarImagem(diretorio.getEnderecoResolucaoPNG(), endBase, endRel, "/Resolucao.png", true, imgRes);
		if(feed) configPost.setTesteResFeed(imgRes); else configPost.setTesteResReel(imgRes);

		configPostDAO.salvar(configPost);
	}

	private ImagemPost copiarImagem(String origemPath, String endBase, String endRel, String nomeFile, boolean feed, ImagemPost imagem) throws IOException
	{
		File destino = new File(endBase + endRel + nomeFile);
		if(destino.exists())
			destino.delete();
		Files.copy(new File(origemPath).toPath(), destino.toPath());

		imagem.setEndImagem(endRel + nomeFile);
		imagem.setNome(nomeFile.substring(1));
		imagem.setFeed(feed);
		return imagemPostDAO.salvar(imagem);
	}
}
