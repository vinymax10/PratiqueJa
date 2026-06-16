package bean;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EnumSet;
import java.util.List;

import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.ClasseAux;
import bean.util.Mensagem;
import dao.DAO;
import exceptions.RelacaoException;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import modelo.BaseMapper;
import modelo.Entidade;
import modelo.GenericMapper;
import modelo.auditoria.TipoEvento;
import service.auditoria.AuditoriaService;

@Data
@Named
public abstract class FilhoBean<T extends Entidade, TDAO extends DAO<T>> implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(FilhoBean.class);

	protected String nome;

	protected T entidade;

	protected T entidadeOriginal;

	@Inject
	protected TDAO entidadeDAO;

	protected boolean cadastro = true;

	protected Class<T> classe;

	protected BaseMapper<T> mapper = new GenericMapper<>();

	@Inject
	protected AuditoriaService auditoriaService;

	protected EnumSet<TipoEvento> auditoriasAtivas = EnumSet.noneOf(TipoEvento.class);

	public FilhoBean(Class<T> classe, String nome)
	{
		this.classe = classe;
		this.nome = nome;
	}

	public String adicionar(Runnable setStatus)
	{
		try
		{
			podeAdicionar();
			if(setStatus != null)
				setStatus.run();

			if(auditoriasAtivas.contains(TipoEvento.CRIACAO))
				auditoriaService.registrarCriacao(classe, entidade.getId(), entidade);

			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " adicionado(a) com sucesso.");
		}
		catch(RelacaoException e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, e.getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível realizar esta ação.");
		}

		return "";
	}

	public void podeAdicionar() throws RelacaoException
	{
	}

	public String salvar(Runnable antesSalvar, Runnable salvar)
	{
		try
		{
			podeEditar(entidade);
			if(antesSalvar != null)
				antesSalvar.run();

			if(auditoriasAtivas.contains(TipoEvento.EDICAO))
				auditoriaService.registrarEdicao(classe, entidadeOriginal.getId(), entidadeOriginal);

			if(salvar != null)
				salvar.run();

			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " salvo com sucesso.");
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

	public void podeEditar(T entidade) throws RelacaoException
	{
	}

	public String remover(Runnable setStatus)
	{
		try
		{
			podeRemover(entidade);
			if(auditoriasAtivas.contains(TipoEvento.EXCLUSAO))
				auditoriaService.registrarExclusao(classe, entidade.getId(), entidade);

			if(setStatus != null)
				setStatus.run();

			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " salvo com sucesso.");
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

	public void podeRemover(T entidade) throws RelacaoException
	{
	}

	public void cancelar()
	{
		entidade = null;
	}

	protected void removeListaPai()
	{
	}

	public void onRowSelect(SelectEvent<T> event)
	{
		cadastro = false;
		LOG.debug("entidadeOriginal: {}", entidadeOriginal);
		this.entidade = mapper.clone(entidadeOriginal); // clone
	}

	public void onRowReorder(List<T> list)
	{
		for(int i = 0; i < list.size(); i++)
		{
			entidade = list.get(i);
			setOrdem(entidade, i);
			entidadeDAO.salvar(entidade);
		}
	}

	private void setOrdem(T entidade, int ordem)
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

	public String alterarStatus(Validacao validacao, Runnable setStatus, Runnable salvar, String msgSucesso)
	{
		try
		{
			validacao.validar();
			setStatus.run();

			if(auditoriasAtivas.contains(TipoEvento.EDICAO))
				auditoriaService.registrarEdicao(classe, entidade.getId(), entidade);

			salvar.run();

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
