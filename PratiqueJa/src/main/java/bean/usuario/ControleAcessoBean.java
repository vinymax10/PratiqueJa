package bean.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.PrimeFaces;

import filtro.usuario.FiltroControleAcesso;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import modelo.usuario.ControleAcesso;
import modelo.usuario.Usuario;
import service.usuario.ControleAcessoService;
import web.session.Sessao;
import web.session.SessionContext;

@Data
@Named
@SessionScoped
public class ControleAcessoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private ControleAcessoService controleAcessoService;

	@Inject
	private FiltroControleAcesso filtro;

	private Usuario usuario;

	private ControleAcesso controleAcesso;

	private String mensagem;

	private List<ControleAcesso> controlesAcessos = new ArrayList<>();

	// --- admin: lista ---

	public void filtrar()
	{
		this.controlesAcessos = controleAcessoService.buscar(filtro);
	}

	public void filtrarInit()
	{
		filtro.limpar();
		filtrar();
	}

	// --- UI / sessão ---

	public void carrega()
	{
		usuario = Sessao.getUsuarioLogado();
		controleAcesso = (ControleAcesso) SessionContext.getInstance().getAttribute("controleAcesso");
	}

	public boolean verificaEstaLogado()
	{
		if(Sessao.estaLogado())
			return true;

		PrimeFaces.current().executeScript("PF('loginWidget').show()");
		return false;
	}

	public void showUpgrade(String mensagem)
	{
		this.mensagem = mensagem;
		PrimeFaces.current().executeScript("PF('upgradeWidget').show()");
	}

	// --- delegação ao service ---

	public boolean podeFazerDownloadMassa()
	{
		return controleAcessoService.podeFazerDownloadMassa(usuario);
	}

	public boolean podeFazerDownloadExercicio()
	{
		return controleAcessoService.podeFazerDownloadExercicio(usuario, controleAcesso);
	}

	public boolean podeFazerDownloadQuestao()
	{
		return controleAcessoService.podeFazerDownloadQuestao(usuario, controleAcesso);
	}

	public boolean podeResolucaoExercicio()
	{
		return controleAcessoService.podeResolucaoExercicio(usuario, controleAcesso);
	}

	public boolean podeResolucaoQuestao()
	{
		return controleAcessoService.podeResolucaoQuestao(usuario, controleAcesso);
	}

	public boolean podeFazerQuestao()
	{
		return controleAcessoService.podeFazerQuestao(usuario, controleAcesso);
	}

	public void registrarDownloadExercicio()
	{
		controleAcessoService.registrarDownloadExercicio(controleAcesso);
	}

	public void registrarDownloadQuestao(boolean massa)
	{
		controleAcessoService.registrarDownloadQuestao(controleAcesso, massa);
	}

	public void registrarDownloadMassa()
	{
		controleAcessoService.registrarDownloadMassa(controleAcesso);
	}

	public void registrarResolucaoExercicio()
	{
		controleAcessoService.registrarResolucaoExercicio(controleAcesso);
	}

	public void registrarResolucaoQuestao()
	{
		controleAcessoService.registrarResolucaoQuestao(controleAcesso);
	}

	public void registrarQuestaoFeita()
	{
		controleAcessoService.registrarQuestaoFeita(controleAcesso);
	}
}
