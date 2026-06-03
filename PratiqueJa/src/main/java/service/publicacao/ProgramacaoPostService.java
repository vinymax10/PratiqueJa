package service.publicacao;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import dao.academico.AssuntoDAO;
import dao.publicacao.BackgroundDAO;
import dao.publicacao.ConfigPostDAO;
import dao.publicacao.ImagemPostDAO;
import dao.publicacao.ProgramacaoPostDAO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import modelo.academico.Assunto;
import modelo.publicacao.Background;
import modelo.publicacao.ConfigPost;
import modelo.publicacao.ImagemPost;
import modelo.publicacao.ProgramacaoPost;

@ApplicationScoped
public class ProgramacaoPostService
{
	@Inject
	private ProgramacaoPostDAO programacaoPostDAO;

	@Inject
	private BackgroundDAO backgroundDAO;

	@Inject
	private ImagemPostDAO imagemPostDAO;

	@Inject
	private AssuntoDAO assuntoDAO;

	@Inject
	private ConfigPostDAO configPostDAO;

	private List<Background> imagensFeed;
	private List<Background> imagensReel;
	private final Random random = new Random();

	@PostConstruct
	public void init()
	{
		imagensFeed = backgroundDAO.carregar(true);
		imagensReel = backgroundDAO.carregar(false);
	}

	public void setImagemPost(ProgramacaoPost programacaoPost)
	{
		if(programacaoPost.isBackgroundAleatorioFeed())
		{
			if(programacaoPost.isBasePadraoFeed())
				programacaoPost.setPadraoFeed(imagensFeed.get(random.nextInt(imagensFeed.size())));
			else
				programacaoPost.setBackgroundFeed(aleatorioImagemPost(programacaoPost.getConfigPost(), true));
		}

		if(programacaoPost.isBackgroundAleatorioReel())
		{
			if(programacaoPost.isBasePadraoReel())
				programacaoPost.setPadraoReel(imagensReel.get(random.nextInt(imagensReel.size())));
			else
				programacaoPost.setBackgroundReel(aleatorioImagemPost(programacaoPost.getConfigPost(), false));
		}
	}

	public void organizarOrdem(ConfigPost configPost)
	{
		List<ProgramacaoPost> programacoesPost = configPost.getProgramacoesPost();
		for(int i = 0; i < programacoesPost.size(); i++)
		{
			ProgramacaoPost p = programacoesPost.get(i);
			p.setOrdem(i);
			p.updateData();
			setImagemPost(p);
			programacaoPostDAO.salvar(p);
		}
	}

	public ProgramacaoPost salvar(ProgramacaoPost programacaoPost)
	{
		ConfigPost configPost = programacaoPost.getConfigPost();
		programacaoPost = programacaoPostDAO.salvar(programacaoPost);
		configPost.getProgramacoesPost().remove(programacaoPost);
		configPost.getProgramacoesPost().add(programacaoPost.getOrdem(), programacaoPost);
		organizarOrdem(configPost);
		return programacaoPost;
	}

	public ProgramacaoPost adicionar(ProgramacaoPost programacaoPost)
	{
		ConfigPost configPost = programacaoPost.getConfigPost();
		int ordem = programacaoPost.getOrdem();
		setImagemPost(programacaoPost);
		programacaoPost = programacaoPostDAO.salvar(programacaoPost);
		configPost.getProgramacoesPost().add(ordem, programacaoPost);
		organizarOrdem(configPost);
		return programacaoPost;
	}

	public void remover(ProgramacaoPost programacaoPost)
	{
		ConfigPost configPost = programacaoPost.getConfigPost();
		configPost.getProgramacoesPost().remove(programacaoPost);
		programacaoPostDAO.remover(programacaoPost);
		organizarOrdem(configPost);
	}

	public void removerTodos(ConfigPost configPost)
	{
		while(!configPost.getProgramacoesPost().isEmpty())
		{
			ProgramacaoPost p = configPost.getProgramacoesPost().get(0);
			programacaoPostDAO.remover(p);
			configPost.getProgramacoesPost().remove(0);
		}
	}

	public ProgramacaoPost persistir(ProgramacaoPost programacaoPost)
	{
		return programacaoPostDAO.salvar(programacaoPost);
	}

	public void programacaoDefault(ConfigPost configPost)
	{
		removerTodos(configPost);
		List<Assunto> assuntos = assuntoDAO.listarOpcoesAtivas();
		for(int i = 0; i < assuntos.size(); i++)
		{
			ProgramacaoPost p = new ProgramacaoPost();
			p.setOrdem(i);
			p.setConfigPost(configPost);
			p.updateData();
			p.setAssunto(assuntos.get(i));
			setImagemPost(p);
			programacaoPostDAO.salvar(p);
			configPost.getProgramacoesPost().add(i, p);
		}
	}

	public void registrarEnvio(ProgramacaoPost programacaoPost)
	{
		ConfigPost configPost = programacaoPost.getConfigPost();
		configPost.setUltimoEnvio(LocalDate.now());
		configPost = configPostDAO.salvar(configPost);
		programacaoPost.setConfigPost(configPost);
		programacaoPost.setOrdem(configPost.getProgramacoesPost().size() - 1);
		salvar(programacaoPost);
	}

	public List<ProgramacaoPost> listarHoje()
	{
		return programacaoPostDAO.hoje();
	}

	public List<Background> getImagensFeed()
	{
		return imagensFeed;
	}

	public List<Background> getImagensReel()
	{
		return imagensReel;
	}

	private ImagemPost aleatorioImagemPost(ConfigPost configPost, boolean feed)
	{
		List<ImagemPost> imagens = imagemPostDAO.carrega(configPost, feed);
		return imagens.get(random.nextInt(imagens.size()));
	}
}
