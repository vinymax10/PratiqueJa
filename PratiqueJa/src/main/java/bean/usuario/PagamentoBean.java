package bean.usuario;

import java.time.LocalDate;
import java.util.EnumSet;

import bean.PaiBean;
import bean.seguranca.AutenticacaoBean;
import dao.usuario.PagamentoDAO;
import dao.usuario.ProdutoDAO;
import filtro.usuario.FiltroPagamento;
import infra.Navegacao;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.auditoria.TipoEvento;
import modelo.publicacao.PerfilCriador;
import modelo.seguranca.PermissaoPadrao;
import modelo.usuario.Pagamento;
import modelo.usuario.PerfilUsuario;
import modelo.usuario.Produto;
import modelo.usuario.TipoPagamento;
import modelo.usuario.Usuario;
import service.email.EmailService;
import util.StringAux;
import bean.util.Mensagem;
import web.session.SessionContext;
import web.session.Sessao;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class PagamentoBean extends PaiBean<Pagamento, PagamentoDAO, PermissaoPadrao<Pagamento>>
{
	@Inject
	private FiltroPagamento filtro;

	private String planoStr;
	private String tipoPagamentoStr;

	@Inject
	private ProdutoDAO produtoDAO;

	@Inject
	private AutenticacaoBean autenticacaoBean;

	@Inject
	private UsuarioBean usuarioBean;

	@Inject
	private ControleAcessoBean controleAcessoBean;

	@Inject
	private EmailService emailService;

	public PagamentoBean()
	{
		super(Pagamento.class, "Pagamento");

		urlCadastro = "/administracao/usuarios/pagamento/form.xhtml";
		urlLista = "/administracao/usuarios/pagamento/list.xhtml";

		auditoriasAtivas = EnumSet.allOf(TipoEvento.class);
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
		if(tabState.hasState(FiltroPagamento.class))
			filtro = tabState.getState(FiltroPagamento.class);
	}

	public void atualizarValor()
	{
		if(entidade != null && entidade.getProduto() != null)
			entidade.setValor(entidade.getProduto().getValor());
	}

	public String adicionarFinalizar()
	{
		try
		{
			TipoPagamento tipoPagamento = TipoPagamento.valueOf(this.tipoPagamentoStr);
			PerfilUsuario plano = PerfilUsuario.valueOf(this.planoStr);

			Produto produto = produtoDAO.buscarPorPerfilUsuario(plano, tipoPagamento);

			Usuario usuario = Sessao.getUsuarioLogado();

			entidade = new Pagamento();
			entidade.setUsuario(usuario);
			entidade.setProduto(produto);
			entidade.setValor(produto != null ? produto.getValor() : 0);

			LocalDate hoje = LocalDate.now();
			entidade.setData(hoje);

			if(!entidadeDAO.contem(entidade))
			{
				LocalDate validade = null;
				if(produto != null && produto.getDiasValididade() > 0)
					validade = hoje.plusDays(produto.getDiasValididade());

				if(produto != null && produto.getPerfilUsuario() != null)
					usuario.setPerfil(produto.getPerfilUsuario());

				usuario.setValidadePlano(validade);
				usuario = usuarioBean.getEntidadeDAO().salvar(usuario);
				SessionContext.getInstance().setAttribute("UsuarioLogado", usuario);
				autenticacaoBean.setUsuario(usuario);

				String nomeProduto = produto != null ? produto.getNome() : plano.getNome();
				String assunto = "Pagamento realizado";
				String mensagem = "Pagamento realizado\n\n"
				+ "Nome: " + entidade.getUsuario().getNome() + "\n"
				+ "Dia: " + StringAux.getDataStr(entidade.getData()) + "\n"
				+ "Valor: " + entidade.getValor() + "\n"
				+ "Produto: " + nomeProduto + "\n";

				emailService.adicionar("vinymax10@gmail.com", assunto, mensagem);

				entidadeDAO.salvar(entidade);
			}

			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " efetuado com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível constatar qual pagamento foi efetuado.");
		}
		return "";
	}

	public String pagamento(PerfilUsuario plano, TipoPagamento tipoPagamento)
	{
		if(controleAcessoBean.verificaEstaLogado())
		{
			Produto produto = produtoDAO.buscarPorPerfilUsuario(plano, tipoPagamento);
			if(produto != null && produto.getIdMercadoPago() != null)
				Navegacao.redirect("https://www.mercadopago.com.br/subscriptions/checkout?preapproval_plan_id=" + produto.getIdMercadoPago());
		}
		return "";
	}

	public String pagamentoCriador(PerfilCriador perfilCriador)
	{
		if(controleAcessoBean.verificaEstaLogado())
		{
			Produto produto = produtoDAO.buscarPorPerfilCriador(perfilCriador);
			if(produto != null && produto.getIdMercadoPago() != null)
				Navegacao.redirect("https://www.mercadopago.com.br/subscriptions/checkout?preapproval_plan_id=" + produto.getIdMercadoPago());
		}
		return "";
	}

}
