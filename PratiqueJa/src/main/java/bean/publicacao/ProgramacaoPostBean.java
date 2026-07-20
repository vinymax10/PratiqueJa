package bean.publicacao;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.primefaces.event.ReorderEvent;

import lombok.Data;
import modelo.academico.Assunto;
import modelo.publicacao.ConfigPost;
import modelo.publicacao.ProgramacaoPost;
import bean.usuario.ControleAcessoBean;
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

	@Inject
	private ControleAcessoBean controleAcessoBean;

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
		if(!controleAcessoBean.verificaEstaLogado())
			return "";

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

	public void subir(ProgramacaoPost programacaoPost)
	{
		List<ProgramacaoPost> programacoesPost = configPostBean.getConfigPost().getProgramacoesPost();
		int indice = programacoesPost.indexOf(programacaoPost);
		if(indice > 0)
		{
			Collections.swap(programacoesPost, indice, indice - 1);
			programacaoPostService.organizarOrdem(configPostBean.getConfigPost());
		}
	}

	public void descer(ProgramacaoPost programacaoPost)
	{
		List<ProgramacaoPost> programacoesPost = configPostBean.getConfigPost().getProgramacoesPost();
		int indice = programacoesPost.indexOf(programacaoPost);
		if(indice >= 0 && indice < programacoesPost.size() - 1)
		{
			Collections.swap(programacoesPost, indice, indice + 1);
			programacaoPostService.organizarOrdem(configPostBean.getConfigPost());
		}
	}

	public void salvar(ProgramacaoPost programacaoPost)
	{
		if(!controleAcessoBean.verificaEstaLogado())
			return;

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
		if(!controleAcessoBean.verificaEstaLogado())
			return "";

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
		if(!controleAcessoBean.verificaEstaLogado())
			return "";

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
		if(!controleAcessoBean.verificaEstaLogado())
			return "";

		programacaoPostService.programacaoDefault(configPostBean.getConfigPost());
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Programação Default realizada com sucesso.");

		return "";
	}

	public String enviarAgora()
	{
		if(!controleAcessoBean.verificaEstaLogado())
			return "";

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

	/** Horário do envio diário automático (mesmo do @Schedule em EnvioPostService). */
	private static final LocalTime HORARIO_ENVIO = LocalTime.of(6, 0);

	/**
	 * Liga/desliga a programação (chamado ao alternar o seletor). Ao ativar, reancorar a agenda para
	 * começar hoje (a 1ª publicação cai em hoje) e, se já passou das 6h, dispara a publicação de hoje
	 * na hora. Ao pausar, apenas persiste o estado.
	 */
	public void alternarAtivacao()
	{
		if(!controleAcessoBean.verificaEstaLogado())
			return;

		ConfigPost configPost = configPostBean.getConfigPost();

		if(!configPost.isAtivo())
		{
			programacaoPostService.salvarConfigPost(configPost);
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO,
			"Programação pausada. Nenhuma publicação automática será enviada.");
			return;
		}

		// Ativou: recomeça a agenda a partir de hoje.
		programacaoPostService.reprogramarParaHoje(configPost);

		// Sem logo/e-mail, nada é publicado — avisa e não tenta disparar.
		if(!configPost.podeGerar())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_WARN,
			"Programação ativada, mas nada será publicado até você cadastrar logomarca e e-mail em Configurações.");
			return;
		}

		// Antes das 6h: o agendador diário cuida hoje. Depois das 6h: dispara a de hoje agora.
		if(!LocalTime.now().isAfter(HORARIO_ENVIO))
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO,
			"Programação ativada! A primeira publicação de hoje sai às 6h.");
			return;
		}

		try
		{
			int enviados = envioPostService.enviarConfig(configPost);
			if(enviados == EnvioPostService.OCUPADO)
				Mensagem.send("growl", FacesMessage.SEVERITY_INFO,
				"Programação ativada! Há um envio em andamento; a publicação de hoje sai em instantes.");
			else if(enviados > 0)
				Mensagem.send("growl", FacesMessage.SEVERITY_INFO,
				"Programação ativada! A primeira publicação de hoje já foi gerada e enviada para o seu e-mail.");
			else
				Mensagem.send("growl", FacesMessage.SEVERITY_INFO,
				"Programação ativada! As publicações começam a partir de hoje.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO,
			"Programação ativada! As publicações começam a partir de hoje.");
		}
	}

	/**
	 * Salva quantos dias cada assunto permanece ativo (publicando diariamente) antes de o ciclo
	 * rotacionar para o próximo. Reinicia o ciclo do assunto atual para a nova duração valer já.
	 * Mínimo de 1 dia.
	 */
	public void salvarQtdDias()
	{
		if(!controleAcessoBean.verificaEstaLogado())
			return;

		ConfigPost configPost = configPostBean.getConfigPost();
		if(configPost.getQtdDias() < 1)
			configPost.setQtdDias(1);

		try
		{
			configPost.setDiasNoCiclo(0);
			programacaoPostService.salvarConfigPost(configPost);
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO,
			"Atualizado: cada assunto publica por " + configPost.getQtdDias() + " dia(s) antes de trocar.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível salvar a duração.");
		}
	}

	public String removerTodosAcao()
	{
		if(!controleAcessoBean.verificaEstaLogado())
			return "";

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
