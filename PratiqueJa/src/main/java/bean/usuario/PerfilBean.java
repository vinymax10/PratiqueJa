package bean.usuario;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import bean.download.Diretorio;
import bean.seguranca.SessaoBean;
import bean.util.Mensagem;
import dao.usuario.PagamentoDAO;
import dao.usuario.UsuarioDAO;
import infra.Graphics;
import service.avaliacao.CreditoAvaliacaoService;
import service.configuracao.DiretorioService;
import service.publicacao.CreditoPostService;
import util.FileAux;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import modelo.usuario.Imagem;
import modelo.usuario.Pagamento;
import modelo.usuario.Usuario;
import service.pagamento.HotmartApiClient;
import service.usuario.UsuarioService;
import util.StringAux;
import web.session.Sessao;

@Data
@Named
@ViewScoped
public class PerfilBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private SessaoBean sessaoBean;

	@Inject
	private CreditoPostService creditoPostService;

	@Inject
	private CreditoAvaliacaoService creditoAvaliacaoService;

	@Inject
	private PagamentoDAO pagamentoDAO;

	@Inject
	private DiretorioService diretorioService;

	@Inject
	private HotmartApiClient hotmartApiClient;

	private Diretorio diretorio;
	private Usuario entidade;
	private String senha;
	private String confirmaSenha;
	private List<Pagamento> pagamentos;

	@PostConstruct
	public void init()
	{
		diretorio = diretorioService.criarDiretorioSemReserva();
		Usuario logado = Sessao.getUsuarioLogado();
		if(logado != null)
		{
			entidade = usuarioDAO.carrega(logado.getId());
			pagamentos = pagamentoDAO.buscarPorUsuario(entidade);
		}
		else
		{
			entidade = new Usuario();
			pagamentos = new ArrayList<>();
		}
	}

	public boolean isEditando()
	{
		return entidade != null && entidade.getId() != null;
	}

	public String registrar()
	{
		try
		{
			entidade.setSenha(usuarioService.hashPassword(senha));
			entidade = usuarioDAO.salvar(entidade);
			sessaoBean.iniciarSession(entidade);
			Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO, "Cadastro realizado! Seja bem-vindo(a).");
			return "/inicio.xhtml?faces-redirect=true";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível realizar o cadastro.");
			return null;
		}
	}

	public String salvar()
	{
		try
		{
			if(senha != null && !senha.isBlank())
				entidade.setSenha(usuarioService.hashPassword(senha));
			entidade = usuarioDAO.salvar(entidade);
			sessaoBean.updateSession(entidade);
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Perfil atualizado com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível salvar o perfil.");
		}
		return null;
	}

	public void uploadFoto(FileUploadEvent event)
	{
		UploadedFile arquivo = event.getFile();
		try
		{
			String endBase = diretorio.getConfig().getEndereco();
			String endRel = "/images/usuario/" + entidade.getId() + "/";

			Imagem foto = entidade.getFoto() != null ? entidade.getFoto() : new Imagem();
			if(foto.getEndereco() != null)
			{
				File antigo = new File(endBase + foto.getEndereco());
				if(antigo.exists())
					antigo.delete();
			}
			byte[] bytes = Graphics.shapeImage(arquivo, 400, 400);
			FileAux.gravarFile(endBase + endRel, arquivo.getFileName(), bytes);
			foto.setEndereco(endRel + arquivo.getFileName());
			entidade.setFoto(foto);

			if(isEditando())
			{
				entidade = usuarioDAO.salvar(entidade);
				sessaoBean.updateSession(entidade);
			}
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Foto atualizada.");
		}
		catch(IOException e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível processar a imagem.");
		}
	}

	public int getCreditosRestantesPost()
	{
		if(entidade == null || entidade.getPerfilCriador() == null) return 0;
		return creditoPostService.creditosRestantes(entidade, entidade.getPerfilCriador());
	}

	public int getPostsUsados()
	{
		if(entidade == null || entidade.getPerfilCriador() == null) return 0;
		return creditoPostService.postsUsados(entidade, entidade.getPerfilCriador());
	}

	public int getCreditosRestantesAvaliacao()
	{
		if(entidade == null) return 0;
		return creditoAvaliacaoService.creditosRestantes(entidade);
	}

	public int getAvaliacoesUsadas()
	{
		if(entidade == null) return 0;
		return creditoAvaliacaoService.avaliacoesUsadas(entidade);
	}

	public void validateEmail(FacesContext context, UIComponent component, Object value)
	{
		String email = (String) value;
		Usuario existente = usuarioDAO.getUsuario(email, "");
		if(existente != null && !existente.getId().equals(entidade.getId()))
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "E-mail já cadastrado.");
			throw new ValidatorException(msg);
		}
	}

	public String statusValidade(LocalDate validade)
	{
		return StringAux.statusValidade(validade);
	}

	public void cancelarAssinaturaPremium()
	{
		cancelarAssinatura(entidade.getSubscriberCodeHotmart());
	}

	public void cancelarAssinaturaCriador()
	{
		cancelarAssinatura(entidade.getSubscriberCodeHotmartCriador());
	}

	public void cancelarAssinaturaAvaliacao()
	{
		cancelarAssinatura(entidade.getSubscriberCodeHotmartAvaliacao());
	}

	private void cancelarAssinatura(String subscriberCode)
	{
		if(subscriberCode == null || subscriberCode.isBlank())
			return;

		boolean solicitado = hotmartApiClient.cancelarAssinatura(subscriberCode);
		if(solicitado)
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO,
				"Cancelamento solicitado. Pode levar alguns minutos até confirmar — você receberá um aviso por e-mail.");
		else
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR,
				"Não foi possível cancelar automaticamente. Cancele diretamente na sua conta Hotmart.");
	}
}
