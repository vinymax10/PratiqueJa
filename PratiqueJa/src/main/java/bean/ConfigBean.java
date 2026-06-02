package bean;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.ReorderEvent;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.ClasseAux;
import util.ListAux;
import bean.util.Mensagem;
import dao.DAO;
import exceptions.RelacaoException;
import filtro.configuracao.FiltroConfig;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import modelo.Entidade;
import modelo.auditoria.TipoEvento;
import service.auditoria.AuditoriaService;

@Data
@Named
public abstract class ConfigBean<T extends Entidade, TDAO extends DAO<T>> implements Serializable
{
	private static final Logger LOG = LoggerFactory.getLogger(ConfigBean.class);

	protected String nome;

	protected T entidade;

	@Inject
	protected TDAO entidadeDAO;

	protected List<T> opcoes = new ArrayList<T>();
	protected List<T> opcoesAtivas = new ArrayList<T>();

	protected boolean cadastro = true;

	protected Class<T> classe;
	
	protected boolean mostrarFiltro;
	
	@Inject
	protected FiltroConfig filtroConfig;
	
	@Inject
	protected AuditoriaService auditoriaService;
	
	protected EnumSet<TipoEvento> auditoriasAtivas = EnumSet.noneOf(TipoEvento.class);
	
	public ConfigBean(Class<T> classe, String nome)
	{
		this.classe = classe;
		this.nome=nome;
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
	
	public void onCellEdit(CellEditEvent<T> event) 
	{
    	int index = event.getRowIndex();
    	T entidade = opcoes.get(index);
    	entidade=entidadeDAO.salvar(entidade);
    	
    	Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " salvo(a) com sucesso.");
	}	
	
	public void onRowReorder(ReorderEvent event)
	{
		T entidade;
		for(int i = 0; i < opcoes.size(); i++)
		{
			entidade = opcoes.get(i);
			setOrdem(entidade, i);
			entidadeDAO.salvar(entidade);
		}
	}
	
	public String cadastrar()
	{
		cadastro = true;
		try
		{
			entidade = classe.getConstructor().newInstance();
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
		| NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		return "";
	}
	
	public String adicionar()
	{
		try
		{
			setOrdem(entidade, opcoes.size());
			entidadeDAO.adicionar(entidade);
			if(auditoriasAtivas.contains(TipoEvento.CRIACAO))
				auditoriaService.registrarCriacao(classe, entidade.getId(), entidade);
			
			opcoes.add(entidade);
			entidade=null;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " adicionado(a) com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível adicionar o(a) " + nome);
		}
		return "";
	}
	
	public String salvar()
	{
		try
		{
			if(auditoriasAtivas.contains(TipoEvento.EDICAO))
				auditoriaService.registrarEdicao(classe, entidade.getId(), entidade);
			
			entidade=entidadeDAO.salvar(entidade);
			entidade=null;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " salvo(a) com sucesso.");
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
			if(auditoriasAtivas.contains(TipoEvento.EDICAO))
				auditoriaService.registrarEdicao(classe, entidade.getId(), entidade);
			
			entidade=entidadeDAO.salvar(entidade);
			entidade=null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível salvar o(a) " + nome);
		}
	}
	
	public String remover(T entidade)
	{
		try
		{
			podeRemover(entidade);
			opcoes.remove(entidade);
			if(auditoriasAtivas.contains(TipoEvento.EXCLUSAO))
				auditoriaService.registrarExclusao(classe, entidade.getId(), entidade);
			
			entidadeDAO.remover(entidade);
			onRowReorder(null);
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " removido(a) com sucesso.");
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
	
	public String remover()
	{
		return remover(entidade);
	}
	
	protected void podeRemover(T entidade) throws RelacaoException{}
	
	public void onRowSelect(SelectEvent<T> event)
	{
		cadastro = false;
	}
	
	public void cancelar()
	{
		entidade=null;
	}
	
	public void filtrar()
	{
		this.opcoes = entidadeDAO.buscar(filtroConfig);
	}
	
	@PostConstruct
	public void init()
	{
//		adicionarDefault();
		this.opcoes = entidadeDAO.listarTudo();
		this.opcoesAtivas = entidadeDAO.listarOpcoesAtivas();
	}

	public void adicionarDefault(){}
	
	public void validateNomeRepetido(FacesContext context, UIComponent component, Object object)
	{
		String nome = (String) object;
		if(ClasseAux.possuiAtributo(classe, "nome"))
		{
			if(ListAux.containsNome(nome,opcoes,classe,this.entidade.getId()))
			{
				LOG.debug("Nome já existente: {}", nome);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nome já existente.", "Nome já existente.");
				throw new ValidatorException(msg);
			}
		}
	}
	
}
