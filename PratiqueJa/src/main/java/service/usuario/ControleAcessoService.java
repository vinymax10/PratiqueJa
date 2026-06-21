package service.usuario;

import java.io.IOException;
import java.util.List;

import com.itextpdf.text.pdf.PdfReader;

import dao.configuracao.ConfigControleAcessoDAO;
import dao.usuario.ControleAcessoDAO;
import filtro.usuario.FiltroControleAcesso;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import modelo.configuracao.ConfigControleAcesso;
import modelo.usuario.ControleAcesso;
import modelo.usuario.PerfilUsuario;
import modelo.usuario.Usuario;

@ApplicationScoped
public class ControleAcessoService
{
	@Inject
	private ControleAcessoDAO controleAcessoDAO;

	@Inject
	private ConfigControleAcessoDAO configControleAcessoDAO;

	private ConfigControleAcesso getConfig()
	{
		return configControleAcessoDAO.buscar();
	}

	public List<ControleAcesso> buscar(FiltroControleAcesso filtro)
	{
		return controleAcessoDAO.buscar(filtro);
	}

	public ControleAcesso carregarOuCriar(Usuario usuario)
	{
		if(usuario == null)
			return null;

		ControleAcesso controleAcesso = controleAcessoDAO.controleAcessoMes(usuario);
		if(controleAcesso == null)
		{
			controleAcesso = new ControleAcesso();
			controleAcesso.setUsuario(usuario);
			controleAcesso.limpar();
			controleAcessoDAO.salvar(controleAcesso);
		}
		return controleAcesso;
	}

	// --- regras de autorização ---

	/** Conteúdo Premium exige plano pago (qualquer perfil acima do Básico). */
	public boolean podeAcessarPremium(Usuario usuario)
	{
		return usuario != null && usuario.getPerfil() != PerfilUsuario.Basico;
	}

	public boolean podeFazerDownload(Usuario usuario, ControleAcesso controleAcesso)
	{
		if(usuario == null || controleAcesso == null)
			return false;

		if(usuario.getPerfil() != PerfilUsuario.Basico)
			return true;

		ConfigControleAcesso config = getConfig();
		int limite = config != null ? config.getLimitePaginasBaixadas() : 100;
		return controleAcesso.getNumPaginasBaixadas() < limite;
	}

	public boolean podeResolucaoExercicio(Usuario usuario, ControleAcesso controleAcesso)
	{
		if(usuario == null || controleAcesso == null)
			return false;

		if(usuario.getPerfil() != PerfilUsuario.Basico)
			return true;

		ConfigControleAcesso config = getConfig();
		int limite = config != null ? config.getLimiteResolucaoExercicio() : 50;
		return controleAcesso.getNumResolucaoExercicio() < limite;
	}

	public boolean podeResolucaoQuestao(Usuario usuario, ControleAcesso controleAcesso)
	{
		if(usuario == null || controleAcesso == null)
			return false;

		if(usuario.getPerfil() != PerfilUsuario.Basico)
			return true;

		ConfigControleAcesso config = getConfig();
		int limite = config != null ? config.getLimiteResolucaoQuestao() : 50;
		return controleAcesso.getNumResolucaoQuestao() < limite;
	}

	public boolean podeFazerQuestao(Usuario usuario, ControleAcesso controleAcesso)
	{
		if(usuario == null || controleAcesso == null)
			return false;

		if(usuario.getPerfil() != PerfilUsuario.Basico)
			return true;

		ConfigControleAcesso config = getConfig();
		int limite = config != null ? config.getLimiteQuestaoFeita() : 50;
		return controleAcesso.getNumQuestaoFeita() < limite;
	}

	// --- registro de eventos ---

	public void registrarDownload(ControleAcesso controleAcesso, String pdfPath)
	{
		if(controleAcesso == null)
			return;

		int paginas = contarPaginas(pdfPath);
		registrarDownload(controleAcesso, paginas);
	}

	public void registrarDownload(ControleAcesso controleAcesso, int paginas)
	{
		if(controleAcesso == null)
			return;

		controleAcesso.incrementaPaginasBaixadas(paginas);
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

	private int contarPaginas(String pdfPath)
	{
		try
		{
			PdfReader reader = new PdfReader(pdfPath);
			int paginas = reader.getNumberOfPages();
			reader.close();
			return paginas;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
}
