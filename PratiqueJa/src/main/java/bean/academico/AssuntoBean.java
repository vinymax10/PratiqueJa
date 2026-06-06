package bean.academico;

import java.util.EnumSet;
import java.util.List;

import bean.util.Mensagem;
import jakarta.faces.application.FacesMessage;
import bean.PaiBean;
import dao.academico.AssuntoDAO;
import dao.exercicio.ExercicioDAO;
import dao.teste.ResultadoTesteDAO;
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
	private ResultadoTesteDAO resultadoTesteDAO;

	@Inject
	private ExercicioDAO exercicioDAO;
	
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

	public String pdfAnotacao()
	{
		String url = "/pdf/" + entidade.getModulo().toString().toLowerCase() + "/" + entidade.getChave() + ".pdf";
		return url;
	}

	public String getKeyPdfEmbedAdobe()
	{
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String serverName = request.getServerName();

		if(serverName.equals("pratiqueja.com"))
			return "ffa6fcd96c444c23a7a33890f2cc3191";

		if(serverName.equals("www.pratiqueja.com"))
			return "f9531c1d598a4add8119f9fc90092f80";

		if(serverName.equals("pratiqueja.com.br"))
			return "d564d4ff38e949cf84d43cd17ddfed7d";

		if(serverName.equals("www.pratiqueja.com.br"))
			return "71f1532cf63f42d6b493cb75073e5b8d";

		if(serverName.equals("localhost"))
			return "f6fdcab0872641c5bd7c6963fbce62f4";
		return "";
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

	private int numStar(Assunto assunto)
	{
		Usuario usuario = Sessao.getUsuarioLogado();
		if(usuario==null)
			return 0;

		double resultado = resultadoTesteDAO.melhorResultado(assunto, usuario);

		if(resultado>=100)
			return 5;
		if(resultado>=80)
			return 4;
		if(resultado>=60)
			return 3;
		if(resultado>=40)
			return 2;
		if(resultado>=20)
			return 1;

		return 0;
	}

	public double porcentagemConcluida(Assunto assunto)
	{
		Usuario usuario = Sessao.getUsuarioLogado();
		if(usuario!=null)
			return resultadoTesteDAO.melhorResultado(assunto, usuario);
		else
			return 0;
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
			entidadeDAO.salvar(a);
		}
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, todos.size() + " assunto(s) atualizados.");
	}

	public List<Assunto> getTodosAssuntos(Modulo modulo)
	{
		List<Assunto> assuntos = entidadeDAO.buscar(modulo);
		for(Assunto assunto : assuntos)
			assunto.setNumStar(numStar(assunto));

		return assuntos;
	}

}
