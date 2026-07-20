package bean.usuario;

import java.util.EnumSet;

import bean.PaiBean;
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
import modelo.avaliacao.PerfilAvaliacao;
import modelo.publicacao.PerfilCriador;
import modelo.seguranca.PermissaoPadrao;
import modelo.usuario.Pagamento;
import modelo.usuario.PerfilUsuario;
import modelo.usuario.Produto;
import modelo.usuario.TipoPagamento;
import bean.util.Mensagem;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class PagamentoBean extends PaiBean<Pagamento, PagamentoDAO, PermissaoPadrao<Pagamento>>
{
	@Inject
	private FiltroPagamento filtro;

	@Inject
	private ProdutoDAO produtoDAO;

	@Inject
	private ControleAcessoBean controleAcessoBean;

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

	public String pagamentoHotmart(PerfilUsuario plano, TipoPagamento tipoPagamento)
	{
		if(controleAcessoBean.verificaEstaLogado())
		{
			Produto produto = produtoDAO.buscarPorPerfilUsuario(plano, tipoPagamento);
			if(produto != null && produto.getLinkHotmart() != null && !produto.getLinkHotmart().isBlank())
				Navegacao.redirect(produto.getLinkHotmart());
		}
		return "";
	}

	public String pagamentoCriadorHotmart(PerfilCriador perfilCriador, TipoPagamento tipoPagamento)
	{
		if(controleAcessoBean.verificaEstaLogado())
		{
			Produto produto = produtoDAO.buscarPorPerfilCriador(perfilCriador, tipoPagamento);
			if(produto != null && produto.getLinkHotmart() != null && !produto.getLinkHotmart().isBlank())
				Navegacao.redirect(produto.getLinkHotmart());
		}
		return "";
	}

	public boolean temHotmart(PerfilUsuario plano, TipoPagamento tipoPagamento)
	{
		Produto produto = produtoDAO.buscarPorPerfilUsuario(plano, tipoPagamento);
		return produto != null && produto.getLinkHotmart() != null && !produto.getLinkHotmart().isBlank();
	}

	public boolean temHotmartCriador(PerfilCriador perfilCriador, TipoPagamento tipoPagamento)
	{
		Produto produto = produtoDAO.buscarPorPerfilCriador(perfilCriador, tipoPagamento);
		return produto != null && produto.getLinkHotmart() != null && !produto.getLinkHotmart().isBlank();
	}

	public String pagamentoAvaliacaoHotmart(PerfilAvaliacao perfilAvaliacao, TipoPagamento tipoPagamento)
	{
		if(controleAcessoBean.verificaEstaLogado())
		{
			Produto produto = produtoDAO.buscarPorPerfilAvaliacao(perfilAvaliacao, tipoPagamento);
			if(produto != null && produto.getLinkHotmart() != null && !produto.getLinkHotmart().isBlank())
				Navegacao.redirect(produto.getLinkHotmart());
		}
		return "";
	}

	public boolean temHotmartAvaliacao(PerfilAvaliacao perfilAvaliacao, TipoPagamento tipoPagamento)
	{
		Produto produto = produtoDAO.buscarPorPerfilAvaliacao(perfilAvaliacao, tipoPagamento);
		return produto != null && produto.getLinkHotmart() != null && !produto.getLinkHotmart().isBlank();
	}

}
