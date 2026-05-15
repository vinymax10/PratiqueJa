package bean.publicacao;

import java.util.List;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.exercicio.ExercicioPadrao;
import modelo.publicacao.ConfigPost;
import modelo.publicacao.ProgramacaoPost;
import modelo.usuario.Usuario;
import service.publicacao.ConteudoPublicacaoService;
import service.publicacao.ProgramacaoPostService;
import util.ColorHolder;

@Named
@Singleton
@Startup
public class EnvioPostBean
{
	private static final Logger logger = Logger.getLogger(EnvioPostBean.class.getName());

	@Inject
	private ConteudoPublicacaoService conteudoPublicacaoService;

	@Inject
	private ProgramacaoPostService programacaoPostService;

	@PostConstruct
	public void init()
	{
		logger.info("----------------Envio Post iniciado----------------");
	}

	@Schedule(hour = "*", minute = "0", second = "0", persistent = false)
	public void enviarProgramacao()
	{
		List<ProgramacaoPost> programacoesPosts = programacaoPostService.listarHoje();
		logger.fine("size: " + programacoesPosts.size());
		for(ProgramacaoPost programacaoPost : programacoesPosts)
		{
			ConfigPost configPost = programacaoPost.getConfigPost();
			if(configPost.podeGerar())
			{
				Usuario usuario = configPost.getUsuario();
				logger.fine("gerando conteudo para " + usuario.getNome()
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

	public void acorda()
	{
		enviarProgramacao();
	}
}
