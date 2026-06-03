package bean.publicacao;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.primefaces.event.ReorderEvent;

import lombok.Data;
import modelo.academico.Assunto;
import modelo.publicacao.ConfigPost;
import modelo.publicacao.ProgramacaoPost;
import bean.util.Mensagem;
import service.publicacao.EnvioPostService;
import service.publicacao.ProgramacaoPostService;

@Data
@Named
@ViewScoped
public class ProgramacaoPostBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private ConfigPostBean configPostBean;

	private ProgramacaoPost programacaoPost;

	private String nome = "Programação de Post";

	private int quantidade = 1;

	private int ordem;

	@Inject
	private EnvioPostService envioPostService;

	@Inject
	private ProgramacaoPostService programacaoPostService;

	public String cadastrar()
	{
		programacaoPost = new ProgramacaoPost();
		return "";
	}

	public String adicionar()
	{
		for(int i = 0; i < quantidade; i++)
		{
			ProgramacaoPost novo = programacaoPost.clone();
			novo.setConfigPost(configPostBean.getConfigPost());
			novo.setOrdem(ordem + i);
			programacaoPostService.adicionar(novo);
		}

		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " adicionado com sucesso.");

		return "";
	}

	public void onRowReorder(ReorderEvent event)
	{
		programacaoPostService.organizarOrdem(configPostBean.getConfigPost());
	}

	public void salvar(ProgramacaoPost programacaoPost)
	{
		try
		{
			programacaoPostService.setImagemPost(programacaoPost);
			programacaoPostService.salvar(programacaoPost);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível salvar o " + nome);
		}
	}

	public String salvar()
	{
		try
		{
			programacaoPostService.setImagemPost(programacaoPost);
			programacaoPost = programacaoPostService.salvar(programacaoPost);
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " salvo com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível salvar o " + nome);
		}
		return "";
	}

	public String remover(ProgramacaoPost programacaoPost)
	{
		try
		{
			programacaoPostService.remover(programacaoPost);
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " removido com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível remover o " + nome);
		}
		return "";
	}

	public String programacaoDefault()
	{
		programacaoPostService.programacaoDefault(configPostBean.getConfigPost());
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Programação Default realizada com sucesso.");

		return "";
	}

	public String enviarAgora()
	{
		ConfigPost configPost = configPostBean.getConfigPost();

		if(!configPost.podeGerar())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_WARN,
			"Não é possível enviar: verifique se a configuração está ativa e possui nome, logomarca e e-mail cadastrados.");
			return "";
		}

		try
		{
			int enviados = envioPostService.enviarConfig(configPost);

			if(enviados == EnvioPostService.OCUPADO)
				Mensagem.send("growl", FacesMessage.SEVERITY_WARN,
				"Já existe um envio em andamento. Aguarde alguns instantes e tente novamente.");
			else if(enviados == 0)
				Mensagem.send("growl", FacesMessage.SEVERITY_INFO,
				"Nenhuma programação com data para hoje (ou anterior). Nada foi enviado.");
			else
				Mensagem.send("growl", FacesMessage.SEVERITY_INFO,
				enviados + " publicação(ões) gerada(s) e enviada(s) para o seu e-mail.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível enviar a publicação.");
		}

		return "";
	}

	public String removerTodosAcao()
	{
		programacaoPostService.removerTodos(configPostBean.getConfigPost());
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Remoção de todas as Programações realizada com sucesso.");

		return "";
	}

	public ProgramacaoPost programacaoPostDefault()
	{
		ProgramacaoPost programacaoPost = new ProgramacaoPost();
		programacaoPost.setConfigPost(configPostBean.getConfigPost());
		programacaoPost.setData(LocalDate.now());
		programacaoPostService.setImagemPost(programacaoPost);
		return programacaoPost;
	}

	public void gerarConteudo(Assunto assunto)
	{
		ProgramacaoPost programacaoPost = programacaoPostDefault();
		programacaoPost.setAssunto(assunto);
		programacaoPost.setAvulsa(true);
		programacaoPostService.persistir(programacaoPost);
		envioPostService.acorda();
	}

	public ConfigPost getConfigPost()
	{
		return configPostBean.getConfigPost();
	}
}
