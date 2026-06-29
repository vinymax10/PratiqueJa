package bean.usuario;

import java.util.EnumSet;

import bean.PaiBean;
import dao.usuario.ProdutoDAO;
import filtro.usuario.FiltroProduto;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.auditoria.TipoEvento;
import modelo.avaliacao.PerfilAvaliacao;
import modelo.publicacao.PerfilCriador;
import modelo.seguranca.PermissaoPadrao;
import modelo.usuario.PerfilUsuario;
import modelo.usuario.Produto;
import modelo.usuario.TipoPagamento;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class ProdutoBean extends PaiBean<Produto, ProdutoDAO, PermissaoPadrao<Produto>>
{
	@Inject
	private FiltroProduto filtro;

	public ProdutoBean()
	{
		super(Produto.class, "Produto");

		urlCadastro = "/administracao/usuarios/produto/form.xhtml";
		urlLista = "/administracao/usuarios/produto/list.xhtml";

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
		if(tabState.hasState(FiltroProduto.class))
			filtro = tabState.getState(FiltroProduto.class);
	}

	public PerfilUsuario[] getOpcoesPerfilUsuario()
	{
		return PerfilUsuario.values();
	}

	public PerfilCriador[] getOpcoesPerfilCriador()
	{
		return PerfilCriador.values();
	}

	public TipoPagamento[] getOpcoesTipoPagamento()
	{
		return TipoPagamento.values();
	}

	public PerfilAvaliacao[] getOpcoesPerfilAvaliacao()
	{
		return PerfilAvaliacao.values();
	}
}
