package bean.usuario;

import java.time.LocalDate;
import java.util.EnumSet;

import bean.PaiBean;
import bean.seguranca.AutenticacaoBean;
import dao.usuario.PagamentoDAO;
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
import modelo.seguranca.PermissaoPadrao;
import modelo.publicacao.PerfilCriador;
import modelo.usuario.Pagamento;
import modelo.usuario.PerfilUsuario;
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

		urlCadastro = "/administracao/pagamento/form.xhtml";
		urlLista = "/administracao/pagamento/list.xhtml";

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

	public String adicionarFinalizar()
	{
		try
		{
			TipoPagamento tipoPagamento = TipoPagamento.valueOf(this.tipoPagamentoStr);
			PerfilUsuario plano = PerfilUsuario.valueOf(this.planoStr);

			Usuario usuario = Sessao.getUsuarioLogado();

			entidade = new Pagamento();
			entidade.setUsuario(usuario);

			LocalDate hoje = LocalDate.now();
			entidade.setData(hoje);
			entidade.setTipoPagamento(tipoPagamento);
			entidade.setPlano(plano);
			entidade.calcularValor();

			if(!entidadeDAO.contem(entidade))
			{
				LocalDate validade = null;
				if(tipoPagamento == TipoPagamento.Mensal)
					validade = hoje.plusMonths(1);
				else if(tipoPagamento == TipoPagamento.Anual)
					validade = hoje.plusYears(1);

				usuario.setPerfil(plano);
				usuario.setValidadePlano(validade);
				usuario = usuarioBean.getEntidadeDAO().salvar(usuario);
				SessionContext.getInstance().setAttribute("UsuarioLogado", usuario);
				autenticacaoBean.setUsuario(usuario);

				String assunto = "Pagamento realizado";
				String mensagem = "Pagamento realizado\n\n"
				+ "Nome: " + entidade.getUsuario().getNome() + "\n"
				+ "Dia: " + StringAux.getDataStr(entidade.getData()) + "\n"
				+ "Valor: " + entidade.getValor() + "\n"
				+ "Plano: " + entidade.getPlano() + "\n"
				+ "TipoPagamento: " + entidade.getTipoPagamento() + "\n";

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
			switch(plano)
			{
				case Prata:
					if(tipoPagamento == TipoPagamento.Mensal)
						Navegacao.redirect("https://www.mercadopago.com.br/subscriptions/checkout?preapproval_plan_id=2c9380848eccfa81018ede87a667092d");
					else
						Navegacao.redirect("https://www.mercadopago.com.br/subscriptions/checkout?preapproval_plan_id=2c9380848eccfa7b018ede9e43dc0955");
					break;

				case Ouro:
					Navegacao.redirect("https://www.mercadopago.com.br/subscriptions/checkout?preapproval_plan_id=a93b47df34f74d178082d08651253cb0");
					break;

				default: break;
			}
		}
		return "";
	}

	public String pagamentoCriador(PerfilCriador perfilCriador)
	{
		if(controleAcessoBean.verificaEstaLogado())
		{
			switch(perfilCriador)
			{
				case Basico:
					Navegacao.redirect("https://www.mercadopago.com.br/subscriptions/checkout?preapproval_plan_id=d311f39e2ead490bbf268c99afec68cc");
					break;
				case Premium:
					Navegacao.redirect("https://www.mercadopago.com.br/subscriptions/checkout?preapproval_plan_id=2227a4e613f34be8978b6cdd63990d04");
					break;
				case Master:
					Navegacao.redirect("https://www.mercadopago.com.br/subscriptions/checkout?preapproval_plan_id=4af587d1c8b045c2acdbe0112b5c93c4");
					break;

				default: break;
			}
		}
		return "";
	}

}
