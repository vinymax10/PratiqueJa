package bean.usuario;

import java.io.Serializable;

import bean.seguranca.SessaoBean;
import dao.usuario.PagamentoDAO;
import dao.usuario.UsuarioDAO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import modelo.usuario.Pagamento;
import modelo.usuario.Usuario;
import web.session.Sessao;

/**
 * Tela de retorno após o checkout na Hotmart. Como a confirmação do pagamento
 * chega de forma assíncrona pelo webhook, essa página faz polling até o
 * pagamento mais recente do usuário aparecer como pago (ou desistir após um tempo).
 */
@Data
@Named
@ViewScoped
public class RetornoPagamentoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private static final int MAX_TENTATIVAS = 30;

	@Inject
	private PagamentoDAO pagamentoDAO;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private SessaoBean sessaoBean;

	private Usuario usuario;
	private Pagamento ultimoPagamento;
	private int tentativas;
	private boolean sessaoAtualizada;

	@PostConstruct
	public void init()
	{
		usuario = Sessao.getUsuarioLogado();
		carregar();
	}

	public void atualizar()
	{
		tentativas++;
		carregar();
	}

	private void carregar()
	{
		if(usuario == null)
			return;

		var pagamentos = pagamentoDAO.buscarPorUsuario(usuario);
		ultimoPagamento = pagamentos.isEmpty() ? null : pagamentos.get(0);

		// O webhook do Hotmart atualiza o Usuario no banco, mas roda numa requisição
		// à parte (chamada servidor-a-servidor, sem a sessão do navegador do usuário) —
		// por isso a sessão só aparece atualizada com o novo plano/créditos quando essa
		// tela, que já está no ar aguardando a confirmação, força a releitura aqui.
		if(!sessaoAtualizada && ultimoPagamento != null && ultimoPagamento.isPago())
		{
			usuario = usuarioDAO.carrega(usuario.getId());
			sessaoBean.updateSession(usuario);
			sessaoAtualizada = true;
		}
	}

	public boolean isLogado()
	{
		return usuario != null;
	}

	public boolean isConfirmado()
	{
		return ultimoPagamento != null && ultimoPagamento.isPago();
	}

	public boolean isAguardando()
	{
		return isLogado() && !isConfirmado() && tentativas < MAX_TENTATIVAS;
	}

	public boolean isDemorando()
	{
		return isLogado() && !isConfirmado() && tentativas >= MAX_TENTATIVAS;
	}
}
