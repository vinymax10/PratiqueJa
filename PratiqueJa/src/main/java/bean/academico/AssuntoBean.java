package bean.academico;

import java.util.EnumSet;
import java.util.List;

import bean.util.Mensagem;
import jakarta.faces.application.FacesMessage;
import bean.PaiBean;
import dao.academico.AssuntoDAO;
import dao.exercicio.ExercicioDAO;
import dao.exercicio.ResultadoExercicioDAO;
import dao.pdf.PdfDAO;
import dao.questao.ResultadoQuestaoDAO;
import exceptions.RelacaoException;
import filtro.academico.FiltroAssunto;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.academico.Assunto;
import modelo.academico.Modulo;
import modelo.auditoria.TipoEvento;
import modelo.exercicio.ExercicioPadrao;
import modelo.seguranca.PermissaoPadrao;
import modelo.usuario.Usuario;
import web.session.Sessao;


@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class AssuntoBean extends PaiBean<Assunto,AssuntoDAO,PermissaoPadrao<Assunto>>
{
	@Inject
	private FiltroAssunto filtro;

	@Inject
	private ExercicioDAO exercicioDAO;

	@Inject
	private PdfDAO pdfDAO;

	@Inject
	private ResultadoExercicioDAO resultadoExercicioDAO;

	@Inject
	private ResultadoQuestaoDAO resultadoQuestaoDAO;
	
	private String assunto;

	public AssuntoBean()
	{
		super(Assunto.class, "Assunto");

		urlCadastro="/administracao/conteudo/assunto/form.xhtml";
		urlLista="/administracao/conteudo/assunto/list.xhtml";

		auditoriasAtivas=EnumSet.allOf(TipoEvento.class);
	}

	protected void podeRemover(Assunto entidade) throws RelacaoException
	{
		if (entidade.getExerciciosPadrao().size() > 0)
			throw new RelacaoException("Não foi possível remover o(a) " + nome + ". "
			+ "Existem Exercícios Padrão relacionados.");
	}

	public void filtrar()
	{
		this.lista = entidadeDAO.buscar(filtro);
		tabState.putState(filtro);
	}

	public void filtrarInit()
	{
		filtro.limpar();
		filtrar();
	}

	@PostConstruct
	public void init()
	{
		if(tabState.hasState(FiltroAssunto.class))
			filtro = tabState.getState(FiltroAssunto.class);
		
		if(assunto != null)
		{
			this.entidade = entidadeDAO.assunto(assunto);
		}
	}

	public List<ExercicioPadrao> getExerciciosPadrao()
	{
		if(entidade != null)
			return entidadeDAO.getExerciciosPadrao(entidade);

		return null;
	}

	/** Assuntos marcados para servir de padrão na tela de teste/preview de configuração de Post. */
	private List<Assunto> opcoesTeste;

	public List<Assunto> getOpcoesTeste()
	{
		if(opcoesTeste == null)
			opcoesTeste = entidadeDAO.mostrarTesteConteudo();

		return opcoesTeste;
	}

	public String pdfAnotacao()
	{
		String url = "/pdf/" + entidade.getModulo().toString().toLowerCase() + "/" + entidade.getChave() + ".pdf";
		return url;
	}

	public String pdfName()
	{
		String url = entidade.getChave() + ".pdf";
		return url;
	}

	public String teoriaHtmlUrl()
	{
		if (entidade == null) return null;
		String path = "/matematica/" + entidade.getModulo().toString().toLowerCase()
				+ "/" + entidade.getChaveImage() + "/teoria.xhtml";
		try
		{
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
					.getExternalContext().getRequest();
			String realPath = request.getServletContext().getRealPath(path);
			if (realPath != null && new java.io.File(realPath).exists())
				return path + "?assunto=" + assunto;
		}
		catch (Exception e) { }
		return null;
	}

	/** Exercícios do assunto que o usuário logado já acertou. */
	public int exerciciosCorretos(Assunto assunto)
	{
		Usuario usuario = Sessao.getUsuarioLogado();
		if(usuario == null || assunto == null)
			return 0;
		return resultadoExercicioDAO.contarCorretos(assunto, usuario);
	}

	/** Questões do assunto que o usuário logado já acertou. */
	public int questoesCorretas(Assunto assunto)
	{
		Usuario usuario = Sessao.getUsuarioLogado();
		if(usuario == null || assunto == null)
			return 0;
		return resultadoQuestaoDAO.contarCorretas(assunto, usuario);
	}

	/**
	 * Progresso do usuário logado no assunto: exercícios corretos / total de exercícios do
	 * assunto. As questões ficam de fora do denominador — são o banco de provas do assunto
	 * (centenas por assunto), não uma lista de prática do aluno; usá-las junto inflava o total
	 * a ponto do progresso nunca sair de 0% mesmo depois de responder várias.
	 */
	public int progresso(Assunto assunto)
	{
		if(assunto == null)
			return 0;

		int total = assunto.getQtdExercicios();
		if(total == 0)
			return 0;

		return (int) Math.round(100d * exerciciosCorretos(assunto) / total);
	}

	public void atualziar()
	{
		int qtdQuestoes = entidadeDAO.contarQuestoes(entidade);
		int qtdExercicios = (int) exercicioDAO.contarExercicios(entidade);
		entidade.setQtdQuestoes(qtdQuestoes);
		entidade.setQtdExercicios(qtdExercicios);
		entidadeDAO.salvar(entidade);
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO,
		"Contadores atualizados: " + qtdQuestoes + " questão(ões), " + qtdExercicios + " exercício(s).");
	}

	public void atualziarAll()
	{
		List<Assunto> todos = entidadeDAO.listarTudo();
		for(Assunto a : todos)
		{
			a.setQtdQuestoes(entidadeDAO.contarQuestoes(a));
			a.setQtdExercicios((int) exercicioDAO.contarExercicios(a));
			a.setQtdPdf(pdfDAO.contarPdfs(a));
			entidadeDAO.salvar(a);
		}
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, todos.size() + " assunto(s) atualizados.");
	}

	public List<Assunto> getTodosAssuntos(Modulo modulo)
	{
		return entidadeDAO.buscar(modulo);
	}

}
