package service;

import java.time.Duration;
import java.time.LocalDateTime;

import dao.seguranca.AcessoDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import modelo.seguranca.Acesso;
import modelo.seguranca.StatusAcesso;
import modelo.usuario.Usuario;
import web.session.Sessao;

@ApplicationScoped
public class AcessoService
{
	@Inject
	private AcessoDAO acessoDAO;

	public void registrarLogin(Usuario usuario)
	{
		Acesso acesso = new Acesso();
		acesso.setUsuario(usuario);
		acesso.setIdSessao(Sessao.id());
		acesso.setInicio(LocalDateTime.now());
		acesso.setStatus(StatusAcesso.ATIVO);
		acessoDAO.adicionar(acesso);
	}

	public void registrarLogout(String sessao)
	{
		Acesso acesso = acessoDAO.lastAcesso(sessao);
		if(acesso == null)
			return;

		LocalDateTime fim = LocalDateTime.now();
		acesso.setTermino(fim);
		acesso.setDuracao(Duration.between(acesso.getInicio(), fim).getSeconds());
		acesso.setStatus(StatusAcesso.EXPIRADO);
		acessoDAO.salvar(acesso);
	}

	public boolean estaLogado()
	{
	    Acesso acesso = acessoDAO.lastAcesso(Sessao.id());
	    return acesso != null && acesso.getStatus() == StatusAcesso.ATIVO;
	}


	public Acesso getAcessoAtual()
	{
		return acessoDAO.lastAcesso(Sessao.id());
	}
}
