package bean;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EnumSet;
import java.util.List;

import org.primefaces.event.ReorderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.ClasseAux;
import infra.Cripto;
import bean.util.Mensagem;
import dao.DAO;
import exceptions.RelacaoException;
import infra.Navegacao;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import modelo.Entidade;
import modelo.auditoria.TipoEvento;
import modelo.seguranca.Permissao;
import modelo.usuario.Usuario;
import service.auditoria.AuditoriaService;
import web.session.Sessao;
import web.session.TabStateManager;

@Data
@Named
public abstract class PaiBean<T extends Entidade, TDAO extends DAO<T>,P extends Permissao<T>> implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(PaiBean.class);

	protected String nome;

	protected T entidade;

	@Inject
	protected TDAO entidadeDAO;

	protected List<T> lista;

	protected List<T> listaTudo;
	protected List<T> listaAtivas;

	protected boolean cadastro = true;

	protected Class<T> classe;

	protected String chave;

	protected Long id;

	protected String urlCadastro;
	protected String urlLista;

	private boolean mostrarFiltro = false;

	@Inject
	protected AuditoriaService auditoriaService;

	protected EnumSet<TipoEvento> auditoriasAtivas = EnumSet.noneOf(TipoEvento.class);
	
	@Inject
	protected TabStateManager tabState;
	
	@Inject
	protected P permissao;
	
	public PaiBean(Class<T> classe, String nome)
	{
		this.classe = classe;
		this.nome = nome;
	}
	
	public void mostrarFiltroToggle()
	{
		mostrarFiltro=!mostrarFiltro;
		LOG.debug("mostrarFiltroToggle: {}", mostrarFiltro);
	}
	
	public void onSelected()
	{
		cadastro = false;
		Navegacao.redirect(getUrlEdicao());
	}

	public String getUrlEdicao()
	{
		return getUrlEdicao(entidade);
	}
	
	public String getUrlEdicao(T entidade)
	{
		return urlCadastro + "?chave=" + Cripto.criptografar(String.valueOf(entidade.getId()));
	}

	public void setOrdem(T entidade, int ordem)
	{
		if(ClasseAux.possuiAtributo(classe, "ordem"))
		{
			try
			{
				Method setter = classe.getMethod("setOrdem", int.class);
				setter.invoke(entidade, ordem);
			}
			catch(NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void personalizarCadastrar(){}

	public String cadastrar()
	{
		cadastro = true;
		try
		{
			entidade = classe.getConstructor().newInstance();
			personalizarCadastrar();
			carregarPermissao();
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
		| SecurityException e)
		{
			e.printStackTrace();
		}
		return "";
	}

	public void personalizarAdicionar()
	{
	}

	public String adicionar(boolean fica)
	{
		try
		{
			validar(!permissao.isPodeAdicionar(),Mensagem.messagePermissaoNegada());
			personalizarAdicionar();
			setOrdem(entidade, getListaTudo().size());
			entidadeDAO.adicionar(entidade);
			carregarPermissao();
			
			if(auditoriasAtivas.contains(TipoEvento.CRIACAO))
				auditoriaService.registrarCriacao(classe, entidade.getId(), entidade);
			
			getListaTudo().add(entidade);
			Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO, nome + " adicionado(a) com sucesso.");

			if(fica)
				Navegacao.redirect(urlCadastro + "?chave=" + Cripto.criptografar(String.valueOf(entidade.getId())));
			else
				Navegacao.redirect(urlLista);
		}
		catch(RelacaoException e)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, e.getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível adicionar o(a) " + nome);
		}
		return "";
	}
	
	public T somenteSalvar()
	{
		try
		{
			if(auditoriasAtivas.contains(TipoEvento.EDICAO))
				auditoriaService.registrarEdicao(classe, entidade.getId(), entidade);
			
			entidade = entidadeDAO.salvar(entidade);
			
			carregarPermissao();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível salvar.");
		}
		return entidade;
	}
	
	public void personalizarSalvar(){}

	public String salvar(boolean fica)
	{
		try
		{
			validar(!permissao.isPodeEditar(),Mensagem.messagePermissaoNegada());
			personalizarSalvar();
			if(auditoriasAtivas.contains(TipoEvento.EDICAO))
				auditoriaService.registrarEdicao(classe, entidade.getId(), entidade);
			
			entidade = entidadeDAO.salvar(entidade);
			
			carregarPermissao();
			Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO, nome + " salvo(a) com sucesso.");
			if(fica)
				Navegacao.redirect(urlCadastro + "?chave=" + Cripto.criptografar(String.valueOf(entidade.getId())));
			else
				Navegacao.redirect(urlLista);
		}
		catch(RelacaoException e)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, e.getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível salvar o(a) " + nome);
		}
		return "";
	}
	
	public void salvar(T entidade)
	{
		try
		{
			this.entidade=entidade;
			personalizarSalvar();
			if(auditoriasAtivas.contains(TipoEvento.EDICAO))
				auditoriaService.registrarEdicao(classe, entidade.getId(), entidade);
			
			entidade = entidadeDAO.salvar(entidade);
			
			carregarPermissao(entidade);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível salvar o(a) " + nome);
		}
	}

	public String remover()
	{
		try
		{
			validar(!permissao.isPodeRemover(),Mensagem.messagePermissaoNegada());
			podeRemover(entidade);
			if(auditoriasAtivas.contains(TipoEvento.EXCLUSAO))
				auditoriaService.registrarExclusao(classe, entidade.getId(), entidade);
			
			getListaTudo().remove(entidade);
			entidadeDAO.remover(entidade);
			if(ClasseAux.possuiAtributo(classe, "ordem"))
				onRowReorder(null);
			
			personalizarRemover();
			
			Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO, nome + " removido(a) com sucesso.");
			Navegacao.redirect(urlLista);
		}
		catch(RelacaoException e)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, e.getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível remover o(a) " + nome);
		}
		return "";
	}
	
	protected void podeRemover(T entidade) throws RelacaoException{}
	
	public void personalizarRemover(){}

	public void onRowReorder(ReorderEvent event)
	{
		T entidade;
		for(int i = 0; i < listaTudo.size(); i++)
		{
			entidade = listaTudo.get(i);
			setOrdem(entidade, i);
			entidadeDAO.salvar(entidade);
		}
	}

//	public void personalizarCarregar(){}

	public void carregar() 
	{
		LOG.debug("carregar: {}", classe.getSimpleName());
		if(chave != null)
		{
			id = Long.valueOf(Cripto.descriptografar(chave));
			entidade = entidadeDAO.carrega(id);
			cadastro = false;
			
			carregarPermissao();
			
			if(!permissao.isPodeCarregar())
			{
				Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_ERROR, Mensagem.messagePermissaoNegada());
				Navegacao.redirect("/acesso/acesso-negado.xhtml");
			}
		}
		else if(cadastro)
		{
			cadastrar();
			
			if(!permissao.isPodeAdicionar())
			{
				Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_ERROR, Mensagem.messagePermissaoNegada());
				Navegacao.redirect("/acesso/acesso-negado.xhtml");
			}
		}
	}
	
	public List<T> getListaTudo()
	{
		if(listaTudo == null )
			this.listaTudo = entidadeDAO.listarTudo();
		
		return listaTudo;
	}

	public void carregarPermissao(T entidade)
	{
		Usuario usuario = Sessao.getUsuarioLogado();
		permissao.update(entidade,usuario);
	}
	
	public void carregarPermissao()
	{
		carregarPermissao(entidade);
	}
	
	public List<T> getListaAtivas()
	{
		if(ClasseAux.possuiAtributo(classe, "ativo") && listaAtivas == null)
			this.listaAtivas = entidadeDAO.listarOpcoesAtivas();

		return listaAtivas;
	}

	public String alterarStatus(Validacao validacao, 
	Runnable setStatus, String msgSucesso)
	{
		try
		{
			validacao.validar();
			setStatus.run();

			if(auditoriasAtivas.contains(TipoEvento.EDICAO))
				auditoriaService.registrarEdicao(classe, entidade.getId(), entidade);

			entidade = entidadeDAO.salvar(entidade);

			carregarPermissao();

			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, msgSucesso);
		}
		catch(RelacaoException e)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, e.getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível realizar esta ação.");
		}

		return "";
	}

	@FunctionalInterface
	public interface Validacao
	{
		void validar() throws RelacaoException;
	}
	
	public void validar(boolean condicao, String mensagem) throws RelacaoException
	{
	    if(condicao)
	        throw new RelacaoException(mensagem);
	}
}
