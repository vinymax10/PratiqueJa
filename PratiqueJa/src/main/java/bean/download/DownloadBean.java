package bean.download;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Logger;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import lombok.Data;
import bean.exercicio.ConfigDownload;
import bean.publicacao.ProgramacaoPostBean;
import bean.usuario.ControleAcessoBean;
import bean.util.Mensagem;
import service.configuracao.DiretorioService;
import service.download.MontadorPdfService;
import dao.usuario.UsuarioDAO;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.push.Push;
import jakarta.faces.push.PushContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.publicacao.ProgramacaoPost;
import modelo.usuario.Usuario;
import util.ColorHolder;
import web.session.Sessao;

@Data
@Named
@ViewScoped
public class DownloadBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DownloadBean.class.getName());

	@Inject
	private SetDownload setDownload;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private DiretorioService diretorioService;

	@Inject
	private MontadorPdfService montadorPdfService;

	@Inject
	@Push(channel = "push")
	private PushContext push;

	@Inject
	private ControleAcessoBean controleAcessoBean;

	@Inject
	private ProgramacaoPostBean programacaoPostBean;

	private double porcentagem;

	public StreamedContent download()
	{
		if (!controleAcessoBean.verificaEstaLogado())
			return null;

		if (!controleAcessoBean.podeFazerDownloadMassa())
		{
			controleAcessoBean.showUpgrade("Este recurso está disponível somente para os perfis Prata ou Ouro."
					+ "\nPor favor faça o upgrade de sua conta.");
			return null;
		}

		logger.fine(setDownload.toString());

		int totalPartes = montadorPdfService.calcularTotalPartes(setDownload);
		if (totalPartes == 0)
		{
			if (setDownload.getAssuntosCurso().isEmpty())
				Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Escolha pelo menos um Assunto.");
			else
				Mensagem.send("growl", FacesMessage.SEVERITY_ERROR,
						"Adicione pelo menos uma quantidade Nível 1, 2 ou 3, "
								+ "ou inclua anotação ou inclua questões.");
			return null;
		}

		if (totalPartes > 1000)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR,
					"Não será possível gerar um pdf com tanta informação. Por favor, escolha menos assuntos"
							+ ", ou diminua a quantidade do número de listas de exercícios.");
			return null;
		}

		porcentagem = 0;
		Usuario usuario = usuarioDAO.carrega(Sessao.getUsuarioLogado().getId());
		setDownload.setUsuario(usuario);
		ConfigDownload configExercicio = buildConfigDownload(usuario, true);
		ConfigDownload configQuestao = buildConfigDownload(usuario, false);
		Diretorio diretorio = diretorioService.criarDiretorio();
		String basePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");

		byte[] bytes = montadorPdfService.montar(setDownload, configExercicio, configQuestao,
				diretorio, basePath, p -> { porcentagem = p; push.send("update"); });

		InputStream inStream = new ByteArrayInputStream(bytes);
		String nomeFile = resolverNomeArquivo();

		StreamedContent file = DefaultStreamedContent.builder().name(nomeFile + ".pdf")
				.contentType("aplication/pdf").stream(() -> inStream).build();

		diretorioService.freeDiretorio(diretorio);
		controleAcessoBean.registrarDownloadMassa();
		return file;
	}

	public StreamedContent downloadInstagram(boolean feed)
	{
		if (!controleAcessoBean.verificaEstaLogado())
			return null;

		if (!controleAcessoBean.podeFazerDownloadMassa())
		{
			controleAcessoBean.showUpgrade("Este recurso está disponível somente para os perfis Prata ou Ouro."
					+ "\nPor favor faça o upgrade de sua conta.");
			return null;
		}

		ProgramacaoPost programacaoPost = programacaoPostBean.programacaoPostDefault();
		logger.fine(setDownload.toString());

		Usuario usuario = usuarioDAO.carrega(Sessao.getUsuarioLogado().getId());
		setDownload.setUsuario(usuario);
		ConfigDownload configExercicio = buildConfigDownload(usuario, true);

		ColorHolder.setCOLOR(programacaoPost.getConfigPost().getCorFonte());
		ColorHolder.setFORMULA(programacaoPost.getConfigPost().getCorFormula());

		porcentagem = 0;
		Diretorio diretorio = diretorioService.criarDiretorio();

		byte[] bytes = montadorPdfService.montarInstagram(setDownload, configExercicio,
				diretorio, programacaoPost, feed, p -> { porcentagem = p; push.send("update"); });

		InputStream inStream = new ByteArrayInputStream(bytes);
		String nomeFile = resolverNomeArquivo();

		StreamedContent file = DefaultStreamedContent.builder().name(nomeFile + ".pdf")
				.contentType("aplication/pdf").stream(() -> inStream).build();

		diretorioService.freeDiretorio(diretorio);
		ColorHolder.clear();
		controleAcessoBean.registrarDownloadMassa();
		return file;
	}

	private ConfigDownload buildConfigDownload(Usuario usuario, boolean exercicio)
	{
		ConfigDownload configDownload = new ConfigDownload();
		configDownload.setIdentificacao(exercicio ? setDownload.isIdentificacaoExercicio() : setDownload.isIdentificacaoQuestao());
		configDownload.setResolucao(exercicio ? setDownload.isResolucaoExercicio() : setDownload.isResolucaoQuestao());
		configDownload.setRespostas(exercicio ? setDownload.isRespostasExercicio() : setDownload.isRespostasQuestao());
		configDownload.setUsuario(usuario);

		return configDownload;
	}

	private String resolverNomeArquivo()
	{
		String nome = setDownload.getNomeArquivo();
		if (nome.isEmpty())
			return setDownload.getAssuntosCurso().size() == 1
					? setDownload.getAssuntosCurso().get(0).getChave()
					: "pratiqueJa";
		return nome;
	}

	public int getPorcentagemInteiro()
	{
		return (int)(porcentagem * 100);
	}

	public int getTotalExercicios() { return montadorPdfService.getTotalExercicios(); }
	public int getTotalQuestoes()   { return montadorPdfService.getTotalQuestoes(); }
	public int getTotalPaginas()    { return montadorPdfService.getTotalPaginas(); }
}
