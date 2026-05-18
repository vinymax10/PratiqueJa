package bean.usuario;

import java.time.LocalDate;
import java.util.EnumSet;

import bean.PaiBean;
import dao.usuario.ContatoDAO;
import filtro.usuario.FiltroContato;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.auditoria.TipoEvento;
import modelo.seguranca.PermissaoPadrao;
import modelo.usuario.Contato;
import modelo.usuario.Usuario;
import service.email.EmailService;
import util.StringAux;
import web.session.Sessao;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class ContatoBean extends PaiBean<Contato, ContatoDAO, PermissaoPadrao<Contato>>
{
	@Inject
	private FiltroContato filtro;

	@Inject
	private EmailService emailService;

	public ContatoBean()
	{
		super(Contato.class, "Contato");

		urlCadastro = "/administracao/usuarios/contato/form.xhtml";
		urlLista = "/administracao/usuarios/contato/list.xhtml";

		auditoriasAtivas = EnumSet.allOf(TipoEvento.class);
	}

	@Override
	public void personalizarAdicionar()
	{
		Usuario usuario = Sessao.getUsuarioLogado();
		entidade.setUsuario(usuario);
		if(usuario != null)
		{
			entidade.setNomeUsuario(usuario.getNome());
			entidade.setEmail(usuario.getEmail());
		}
		entidade.setData(LocalDate.now());
		avisarAddContato();
	}

	private void avisarAddContato()
	{
		String assunto = "Contato recebido";
		String mensagem = "Contato recebido\n\n"
		+ "Nome: " + entidade.getNomeUsuario() + "\n"
		+ "Assunto: " + entidade.getAssunto() + "\n"
		+ "Mensagem: " + entidade.getMensagem() + "\n"
		+ "Data: " + StringAux.getDataStr(entidade.getData()) + "\n"
		+ "Email: " + entidade.getEmail() + "\n";

		emailService.adicionar("vinymax10@gmail.com", assunto, mensagem);
	}

	public void filtrar()
	{
		this.lista = entidadeDAO.buscar(filtro);
		tabState.putState(filtro);
	}

	public void filtrarInit()
	{
		filtro.limpar();
		filtrar();
	}

	@PostConstruct
	public void init()
	{
		if(tabState.hasState(FiltroContato.class))
			filtro = tabState.getState(FiltroContato.class);
	}
}
