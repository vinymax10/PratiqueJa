package bean.publicacao;

import java.io.Serializable;
import java.util.List;

import org.omnifaces.cdi.Startup;

import util.ColorHolder;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.exercicio.ExercicioPadrao;
import modelo.publicacao.ConfigPost;
import modelo.publicacao.ProgramacaoPost;
import modelo.usuario.Usuario;
import service.ConteudoPublicacaoService;
import service.ProgramacaoPostService;

@Startup
@Named
@ApplicationScoped
public class EnvioPostBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private ConteudoPublicacaoService conteudoPublicacaoService;

	@Inject
	private ProgramacaoPostService programacaoPostService;

	private static Thread thread;

	public void enviarProgramacao()
	{
		List<ProgramacaoPost> programacoesPosts = programacaoPostService.listarHoje();
		System.out.println("size: " + programacoesPosts.size());
		for(ProgramacaoPost programacaoPost : programacoesPosts)
		{
			ConfigPost configPost = programacaoPost.getConfigPost();
			if(configPost.podeGerar())
			{
				Usuario usuario = configPost.getUsuario();
				System.out.println("gerando conteudo para " + usuario.getNome()
				+ "\n" + programacaoPost);

				ColorHolder.setCOLOR(configPost.getCorFonte());
				ColorHolder.setFORMULA(configPost.getCorFormula());

				for(ExercicioPadrao exercicioPadrao : programacaoPost.getAssuntoCurso().getExerciciosPadrao())
				{
					conteudoPublicacaoService.gerarConteudoFeed(exercicioPadrao, programacaoPost);
					conteudoPublicacaoService.gerarConteudoReel(exercicioPadrao, programacaoPost);
				}

				ColorHolder.clear();

				if(programacaoPost.isAvulsa())
					programacaoPostService.remover(programacaoPost);
				else
					programacaoPostService.registrarEnvio(programacaoPost);
			}
		}
	}

	@PostConstruct
	public void init()
	{
		System.out.println("----------------Envio Post iniciado----------------");
		EnvioPost envioPost = new EnvioPost(this);
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
