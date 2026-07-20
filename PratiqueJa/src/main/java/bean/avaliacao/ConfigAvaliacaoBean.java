package bean.avaliacao;

import java.io.IOException;
import java.io.Serializable;

import org.primefaces.event.FileUploadEvent;

import bean.download.Diretorio;
import bean.util.Mensagem;
import dao.avaliacao.ConfigAvaliacaoDAO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import modelo.avaliacao.ConfigAvaliacao;
import modelo.avaliacao.PedidoAvaliacao;
import modelo.avaliacao.PerfilAvaliacao;
import modelo.usuario.Usuario;
import service.avaliacao.AvaliacaoFormService;
import service.configuracao.DiretorioService;
import web.session.Sessao;

/**
 * Gerencia a entidade {@link ConfigAvaliacao} (a "config de avaliação" do usuário: cabeçalho padrão,
 * logo da escola), no mesmo molde do {@code ConfigPostBean}. É {@code @SessionScoped} para segurar o
 * "config atual" — o do próprio logado ou, no modo suporte do admin ({@code ?configAvaliacao=ID}), o
 * de outro usuário — atravessando as telas de Avaliação. A partir da config chega-se ao dono por
 * {@link ConfigAvaliacao#getUsuario()}. A persistência é delegada ao {@link AvaliacaoFormService}.
 */
@Data
@Named
@SessionScoped
public class ConfigAvaliacaoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	/** Id do config em edição, vindo de {@code ?configAvaliacao=ID} (só respeitado para admin). */
	private Long idConfigAvaliacao;

	/** Config atual (própria ou, no modo suporte, a de outro usuário). */
	private ConfigAvaliacao configAvaliacao;

	@Inject
	private ConfigAvaliacaoDAO configAvaliacaoDAO;

	@Inject
	private AvaliacaoFormService avaliacaoService;

	@Inject
	private DiretorioService diretorioService;

	private Diretorio diretorio;

	@PostConstruct
	public void init()
	{
		diretorio = diretorioService.criarDiretorioSemReserva();
		sincronizar();
	}

	/**
	 * Sincroniza lendo o {@code ?configAvaliacao} DIRETO do request: presente → aquela config (admin,
	 * modo suporte); ausente → volta para a config do próprio logado. Reseta de fato (diferente do
	 * {@code f:viewParam}, que manteria o valor antigo na sessão) — usado ao entrar em Avaliação pelo
	 * menu lateral para sair do modo suporte.
	 */
	public void sincronizarPeloRequest()
	{
		String p = FacesContext.getCurrentInstance().getExternalContext()
			.getRequestParameterMap().get("configAvaliacao");

		if(p == null || p.isBlank())
			idConfigAvaliacao = null;
		else
			try { idConfigAvaliacao = Long.valueOf(p); }
			catch(NumberFormatException e) { idConfigAvaliacao = null; }

		sincronizar();
	}

	/**
	 * Alinha o config em edição com o usuário da sessão. Sem login, um objeto vazio só para exibição.
	 * Admin pode atender o config de OUTRO usuário via {@code ?configAvaliacao=ID}; os demais usam
	 * sempre o próprio.
	 */
	private void sincronizar()
	{
		Usuario usuario = Sessao.getUsuarioLogado();
		if(usuario == null)
		{
			configAvaliacao = new ConfigAvaliacao();
			return;
		}
		if(idConfigAvaliacao != null && usuario.isAdmin())
			configAvaliacao = configAvaliacaoDAO.carrega(idConfigAvaliacao);
		else
			configAvaliacao = usuario.getConfigAvaliacao();
	}

	/** Dono do config em edição quando é OUTRO usuário (admin no modo suporte). null caso contrário — para o chip. */
	public Usuario getUsuarioVisualizado()
	{
		if(configAvaliacao == null || configAvaliacao.getUsuario() == null)
			return null;

		Usuario logado = Sessao.getUsuarioLogado();
		if(logado != null && !configAvaliacao.getUsuario().getId().equals(logado.getId()))
			return configAvaliacao.getUsuario();

		return null;
	}

	/** Dono do config-alvo (plano, histórico): sai da config quando em modo suporte, senão o logado. */
	public Usuario getUsuarioAlvo()
	{
		return configAvaliacao != null && configAvaliacao.getUsuario() != null
			? configAvaliacao.getUsuario() : Sessao.getUsuarioLogado();
	}

	// ── Salvar padrão (cabeçalho) ──

	/** Grava os dados atuais do formulário (cabeçalho e formato) como o padrão do usuário atendido. */
	public void salvarComoPadrao(PedidoAvaliacao dados)
	{
		Usuario usuario = getUsuarioAlvo();
		if(usuario == null)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_WARN, "Faça login para salvar um padrão.");
			return;
		}

		avaliacaoService.salvarPadrao(usuario, dados);
		sincronizar();
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Padrão salvo! As próximas avaliações já virão com esses dados.");
	}

	// ── Logo da escola (Profissional/Master) ──

	/** A logo da escola no cabeçalho é exclusiva dos planos Profissional e Master. */
	public boolean isPodeUsarLogoEscola()
	{
		Usuario usuario = getUsuarioAlvo();
		PerfilAvaliacao plano = usuario != null ? usuario.getPerfilAvaliacao() : null;
		return plano == PerfilAvaliacao.Profissional || plano == PerfilAvaliacao.Master;
	}

	public boolean isTemLogoEscola()
	{
		return configAvaliacao != null && configAvaliacao.getLogoEscola() != null
			&& configAvaliacao.getLogoEscola().getEndereco() != null;
	}

	public String getLogoEscolaEndereco()
	{
		if(configAvaliacao == null || configAvaliacao.getLogoEscola() == null)
			return null;
		return configAvaliacao.getLogoEscola().getEndereco();
	}

	public void uploadLogoEscola(FileUploadEvent event)
	{
		Usuario usuario = getUsuarioAlvo();
		if(!isPodeUsarLogoEscola())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_WARN, "A logo da escola está disponível apenas nos planos Profissional e Master.");
			return;
		}

		try
		{
			avaliacaoService.salvarLogo(usuario, event.getFile(), diretorio.getConfig().getEndereco());
			sincronizar();
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Logo da escola atualizada com sucesso.");
		}
		catch(IOException e)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível processar a imagem enviada.");
		}
	}

	public void removerLogoEscola()
	{
		Usuario usuario = getUsuarioAlvo();
		if(usuario == null || usuario.getId() == null)
			return;

		avaliacaoService.removerLogo(usuario, diretorio.getConfig().getEndereco());
		sincronizar();
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Logo da escola removida.");
	}
}
