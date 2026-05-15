package service.usuario;

import java.util.List;

import dao.usuario.ControleAcessoDAO;
import filtro.usuario.FiltroControleAcesso;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import modelo.usuario.ControleAcesso;
import modelo.usuario.PerfilUsuario;
import modelo.usuario.Usuario;

@ApplicationScoped
public class ControleAcessoService
{
	static final int LIMITE_DOWNLOAD_EXERCICIO  = 5;
	static final int LIMITE_DOWNLOAD_QUESTAO    = 5;
	static final int LIMITE_RESOLUCAO_EXERCICIO = 50;
	static final int LIMITE_RESOLUCAO_QUESTAO   = 50;
	static final int LIMITE_QUESTAO_FEITA       = 50;

	@Inject
	private ControleAcessoDAO controleAcessoDAO;

	public List<ControleAcesso> buscar(FiltroControleAcesso filtro)
	{
		return controleAcessoDAO.buscar(filtro);
	}

	// --- regras de autorização ---

	public boolean podeFazerDownloadMassa(Usuario usuario)
	{
		if(usuario == null)
			return false;

		return usuario.getPerfil() != PerfilUsuario.Bronze;
	}

	public boolean podeFazerDownloadExercicio(Usuario usuario, ControleAcesso controleAcesso)
	{
		if(usuario == null || controleAcesso == null)
			return false;

		if(usuario.getPerfil() != PerfilUsuario.Bronze)
			return true;

		return controleAcesso.getNumDownloadExercicio() < LIMITE_DOWNLOAD_EXERCICIO;
	}

	public boolean podeFazerDownloadQuestao(Usuario usuario, ControleAcesso controleAcesso)
	{
		if(usuario == null || controleAcesso == null)
			return false;

		if(usuario.getPerfil() != PerfilUsuario.Bronze)
			return true;

		return controleAcesso.getNumDownloadQuestao() < LIMITE_DOWNLOAD_QUESTAO;
	}

	public boolean podeResolucaoExercicio(Usuario usuario, ControleAcesso controleAcesso)
	{
		if(usuario == null || controleAcesso == null)
			return false;

		if(usuario.getPerfil() != PerfilUsuario.Bronze)
			return true;

		return controleAcesso.getNumResolucaoExercicio() < LIMITE_RESOLUCAO_EXERCICIO;
	}

	public boolean podeResolucaoQuestao(Usuario usuario, ControleAcesso controleAcesso)
	{
		if(usuario == null || controleAcesso == null)
			return false;

		if(usuario.getPerfil() != PerfilUsuario.Bronze)
			return true;

		return controleAcesso.getNumResolucaoQuestao() < LIMITE_RESOLUCAO_QUESTAO;
	}

	public boolean podeFazerQuestao(Usuario usuario, ControleAcesso controleAcesso)
	{
		if(usuario == null || controleAcesso == null)
			return false;

		if(usuario.getPerfil() != PerfilUsuario.Bronze)
			return true;

		return controleAcesso.getNumQuestaoFeita() < LIMITE_QUESTAO_FEITA;
	}

	// --- registro de eventos ---

	public void registrarDownloadExercicio(ControleAcesso controleAcesso)
	{
		if(controleAcesso == null)
			return;

		controleAcesso.incrementaDownloadExercicio();
		controleAcessoDAO.salvar(controleAcesso);
	}

	public void registrarDownloadQuestao(ControleAcesso controleAcesso, boolean massa)
	{
		if(controleAcesso == null)
			return;

		if(massa)
			controleAcesso.incrementaDownloadQuestaoMassa();
		else
			controleAcesso.incrementaDownloadQuestao();

		controleAcessoDAO.salvar(controleAcesso);
	}

	public void registrarDownloadMassa(ControleAcesso controleAcesso)
	{
		if(controleAcesso == null)
			return;

		controleAcesso.incrementaDownloadMassa();
		controleAcessoDAO.salvar(controleAcesso);
	}

	public void registrarResolucaoExercicio(ControleAcesso controleAcesso)
	{
		if(controleAcesso == null)
			return;

		controleAcesso.incrementaResolucaoExercicio();
		controleAcessoDAO.salvar(controleAcesso);
	}

	public void registrarResolucaoQuestao(ControleAcesso controleAcesso)
	{
		if(controleAcesso == null)
			return;

		controleAcesso.incrementaResolucaoQuestao();
		controleAcessoDAO.salvar(controleAcesso);
	}

	public void registrarQuestaoFeita(ControleAcesso controleAcesso)
	{
		if(controleAcesso == null)
			return;

		controleAcesso.incrementaQuestaoFeita();
		controleAcessoDAO.salvar(controleAcesso);
	}
}
