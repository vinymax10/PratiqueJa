package bean.usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import auxiliar.StringAux;
import bean.seguranca.AutenticacaoBean;
import bean.usuario.filtro.FiltroPagamento;
import bean.util.Mensagem;
import dao.usuario.PagamentoDAO;
import infra.Navegacao;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.instagram.PerfilCriador;
import modelo.usuario.Pagamento;
import modelo.usuario.PerfilUsuario;
import modelo.usuario.TipoPagamento;
import modelo.usuario.Usuario;
import service.EmailService;
import session.SessionContext;

@Named
@ViewScoped
public class PagamentoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nome = "Pagamento";

	@Inject
	private Pagamento pagamento;

	private boolean lista = true;
	private boolean cadastro = false;
	private int activeIndex;

	@Inject
	private PagamentoDAO pagamentoDAO;

	private List<Pagamento> pagamentos = new ArrayList<Pagamento>();

	@Inject
	private FiltroPagamento filtroPagamento;

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
	
	public String adicionarFinalizar()
	{
		try
		{
			TipoPagamento tipoPagamento=TipoPagamento.valueOf(this.tipoPagamentoStr);
			PerfilUsuario plano = PerfilUsuario.valueOf(this.planoStr);
			
			Usuario usuario=autenticacaoBean.getUsuario();
			
			pagamento = new Pagamento();
			pagamento.setUsuario(usuario);
			
			LocalDate hoje=LocalDate.now();
			pagamento.setData(hoje);
			pagamento.setTipoPagamento(tipoPagamento);
			pagamento.setPlano(plano);
			pagamento.calcularValor();
			
			if(!pagamentoDAO.contem(pagamento))
			{
				LocalDate validade=null;
				if(tipoPagamento==TipoPagamento.Mensal)
					validade=hoje.plusMonths(1);
				else if(tipoPagamento==TipoPagamento.Anual)
					validade=hoje.plusYears(1);
				
				usuario.setPerfil(plano);
				usuario.setValidadePlano(validade);
				usuario=usuarioBean.getUsuarioDAO().salvar(usuario);
				SessionContext.getInstance().setAttribute("UsuarioLogado", usuario);
				autenticacaoBean.setUsuario(usuario);
				
				String assunto = "Pagamento realizado";
				String mensagem = "Pagamento realizado\n\n"
				+ "Nome: "+pagamento.getUsuario().getNome()+"\n"
				+ "Dia: "+StringAux.getDataStr(pagamento.getData())+"\n"
				+ "Valor: "+pagamento.getValor()+"\n"
				+ "Plano: "+pagamento.getPlano()+"\n"
				+ "TipoPagamento: "+pagamento.getTipoPagamento()+"\n";

				emailService.adicionar("vinymax10@gmail.com", assunto, mensagem);
		
				pagamentoDAO.salvar(pagamento);
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
	
//	https://pratiqueja.com/usuario/pagamento/finalizado?plano=Prata&tipo=Mensal
//	https://pratiqueja.com/usuario/pagamento/finalizado?plano=Prata&tipo=Anual
//	https://pratiqueja.com/usuario/pagamento/finalizado?plano=Ouro&tipo=Mensal
	
	public String pagamento(PerfilUsuario plano, TipoPagamento tipoPagamento)
	{
		if(controleAcessoBean.verificaEstaLogado())
		{
			switch(plano)
			{
				case Prata: 
					if(tipoPagamento==TipoPagamento.Mensal)
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
	
	public String pagoToggle(Pagamento pagamento)
	{
		pagamento.pagoToggle();
		pagamentoDAO.salvar(pagamento);
		return "";
	}
	
	public String cadastrar()
	{
		cadastro = true;
		lista = false;
		pagamento = new Pagamento();
		return "";
	}

	public String adicionar()
	{
		try
		{
			pagamentoDAO.salvar(pagamento);
			lista = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " adicionado com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível adicionar o " + nome);
		}
		return "";
	}

	public String salvar()
	{
		try
		{
			pagamento=pagamentoDAO.salvar(pagamento);
			lista = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " salvo com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível salvar o " + nome);
		}
		return "";
	}

	public String remover()
	{
		try
		{
			if(pagamentos.contains(pagamento))
				pagamentos.remove(pagamento);
			
			pagamentoDAO.remover(pagamento);

			lista = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " removido com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível remover o " + nome);
		}
		return "";
	}

	public String cancelar()
	{
		lista = true;
		return "";
	}

	public void filtrar()
	{
		this.pagamentos = pagamentoDAO.buscar(filtroPagamento);
	}

	public void onSelected()
	{
		cadastro = false;
		lista = false;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public Pagamento getPagamento()
	{
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento)
	{
		this.pagamento = pagamento;
	}

	public boolean isLista()
	{
		return lista;
	}

	public void setLista(boolean lista)
	{
		this.lista = lista;
	}

	public boolean isCadastro()
	{
		return cadastro;
	}

	public void setCadastro(boolean cadastro)
	{
		this.cadastro = cadastro;
	}

	public List<Pagamento> getPagamentos()
	{
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos)
	{
		this.pagamentos = pagamentos;
	}

	public FiltroPagamento getFiltroPagamento()
	{
		return filtroPagamento;
	}

	public void setFiltroPagamento(FiltroPagamento filtroPagamento)
	{
		this.filtroPagamento = filtroPagamento;
	}

	public int getActiveIndex()
	{
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex)
	{
		this.activeIndex = activeIndex;
	}

	public String getPlanoStr()
	{
		return planoStr;
	}

	public void setPlanoStr(String planoStr)
	{
		this.planoStr = planoStr;
	}

	public String getTipoPagamentoStr()
	{
		return tipoPagamentoStr;
	}

	public void setTipoPagamentoStr(String tipoPagamentoStr)
	{
		this.tipoPagamentoStr = tipoPagamentoStr;
	}

}