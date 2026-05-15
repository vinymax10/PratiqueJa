package bean.academico;

import java.util.EnumSet;
import java.util.List;

import bean.PaiBean;
import dao.academico.AssuntoCursoDAO;
import dao.teste.ResultadoTesteDAO;
import exceptions.RelacaoException;
import filtro.academico.FiltroAssuntoCurso;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.academico.AssuntoCurso;
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
public class AssuntoCursoBean extends PaiBean<AssuntoCurso,AssuntoCursoDAO,PermissaoPadrao<AssuntoCurso>>
{
	@Inject
	private FiltroAssuntoCurso filtro;

	@Inject
	private ResultadoTesteDAO resultadoTesteDAO;

	public AssuntoCursoBean()
	{
		super(AssuntoCurso.class, "Assunto do Curso");

		urlCadastro="/administracao/assunto-curso/form.xhtml";
		urlLista="/administracao/assunto-curso/list.xhtml";

		auditoriasAtivas=EnumSet.allOf(TipoEvento.class);
	}

	protected void podeRemover(AssuntoCurso entidade) throws RelacaoException
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
		if(tabState.hasState(FiltroAssuntoCurso.class))
			filtro = tabState.getState(FiltroAssuntoCurso.class);
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

	private int numStar(AssuntoCurso assuntoCurso)
	{
		Usuario usuario = Sessao.getUsuarioLogado();
		if(usuario==null)
			return 0;

		double resultado = resultadoTesteDAO.melhorResultado(assuntoCurso, usuario);

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

	public double porcentagemConcluida(AssuntoCurso assuntoCurso)
	{
		Usuario usuario = Sessao.getUsuarioLogado();
		if(usuario!=null)
			return resultadoTesteDAO.melhorResultado(assuntoCurso, usuario);
		else
			return 0;
	}

	public List<AssuntoCurso> getTodosAssuntoCursos(Modulo modulo)
	{
		List<AssuntoCurso> assuntosCurso = entidadeDAO.buscar(modulo);
		for(AssuntoCurso assuntoCurso : assuntosCurso)
			assuntoCurso.setNumStar(numStar(assuntoCurso));

		return assuntosCurso;
	}

}
