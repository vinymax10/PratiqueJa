package bean.questao;

import java.io.Serializable;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import bean.util.Mensagem;
import dao.questao.ParagrafoDAO;
import modelo.questao.ImagemFile;
import modelo.questao.Paragrafo;
import modelo.questao.Questao;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class ParagrafoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	String nome = "Paragrafo";

	private Paragrafo paragrafo;

	private boolean lista = true;
	private boolean cadastro = false;
	private UploadedFile uploadedFile;

	@Inject
	private GestaoQuestaoBean gestaoQuestaoBean;

	@Inject
	private ParagrafoDAO paragrafoDAO;

	public String cadastrar()
	{
		cadastro = true;
		lista = false;
		paragrafo = new Paragrafo();
		return "";
	}

	public String adicionar()
	{
		try
		{
			Questao questao = gestaoQuestaoBean.getQuestao();
			paragrafo.setQuestao(questao);
			paragrafo.setOrdem(questao.getParagrafos().size());
			questao.getParagrafos().add(paragrafo);
			paragrafoDAO.salvar(paragrafo);
			lista = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " adicionado com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível adicionar o " + nome);
		}
		return "";
	}

	public String salvar()
	{
		try
		{
			paragrafo=paragrafoDAO.salvar(paragrafo);
			lista = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " salvo com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível salvar o " + nome);
		}
		return "";
	}

	public String remover()
	{
		try
		{
			Questao questao = gestaoQuestaoBean.getQuestao();
			questao.getParagrafos().remove(paragrafo);
			paragrafoDAO.remover(paragrafo);
			lista = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " removido com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível remover o " + nome);
		}
		return "";
	}

	public String cancelar()
	{
		lista = true;
		return "";
	}

	public void onSelected()
	{
		cadastro = false;
		lista = false;
	}

	public String uploadNovo(FileUploadEvent event)
	{
		uploadedFile = event.getFile();
		try
		{
			SerialBlob serialBlob = new SerialBlob(uploadedFile.getContent());
			ImagemFile imagemFile = new ImagemFile();

			imagemFile.setFile(serialBlob);
			imagemFile.setEndImagem(uploadedFile.getFileName());
			paragrafo.setImagemFile(imagemFile);

			FacesContext.getCurrentInstance().addMessage("growl",
			new FacesMessage(FacesMessage.SEVERITY_INFO, "Upload do arquivo " + uploadedFile.getFileName() + " realizado com sucesso.", ""));

		}
		catch(SerialException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return "";
	}

	public String removerImagem()
	{
		paragrafo.setImagemFile(null);
		paragrafoDAO.salvar(paragrafo);

		FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Imagem removida com sucesso.", ""));
		return "";
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public Paragrafo getParagrafo()
	{
		return paragrafo;
	}

	public void setParagrafo(Paragrafo paragrafo)
	{
		this.paragrafo = paragrafo;
	}

	public boolean isLista()
	{
		return lista;
	}

	public void setLista(boolean lista)
	{
		this.lista = lista;
	}

	public boolean isCadastro()
	{
		return cadastro;
	}

	public void setCadastro(boolean cadastro)
	{
		this.cadastro = cadastro;
	}

}