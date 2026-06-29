package service.publicacao;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import dao.publicacao.PedidoPostDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import modelo.publicacao.ConfigPost;
import modelo.publicacao.PedidoPost;
import modelo.publicacao.PerfilCriador;
import modelo.publicacao.StatusPedidoPost;
import modelo.usuario.Usuario;

/**
 * Cota de créditos de post: tudo que é gerado (lote sob demanda ou programação) conta como um
 * {@link PedidoPost} do mês — fonte única da contagem. A programação registra o consumo via
 * {@link #registrarConsumo}.
 */
@ApplicationScoped
public class CreditoPostService implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoPostDAO pedidoPostDAO;

	/** Créditos ainda disponíveis: para planos renováveis conta só o mês atual; para Teste conta o total acumulado. */
	public int creditosRestantes(Usuario usuario, PerfilCriador perfil)
	{
		if(usuario == null || perfil == null)
			return 0;

		if(!perfil.isRenovavel())
			return perfil.getCreditosMensais() - pedidoPostDAO.somarPostsTotal(usuario);

		int cota = perfil.getCreditosMensais() + usuario.getCreditoRolloverPost();
		LocalDateTime inicioMes = LocalDate.now().withDayOfMonth(1).atStartOfDay();
		int usados = pedidoPostDAO.somarPostsNoMes(usuario, inicioMes, inicioMes.plusMonths(1));
		return cota - usados;
	}

	/** Registra o consumo de {@code quantidade} posts pela programação (não baixável). */
	public void registrarConsumo(Usuario usuario, ConfigPost configPost, int quantidade)
	{
		PedidoPost pedido = new PedidoPost();
		pedido.setUsuario(usuario);
		pedido.setConfigPost(configPost);
		pedido.setProgramado(true);
		pedido.setQuantidade(quantidade);
		pedido.setStatus(StatusPedidoPost.CONCLUIDO);
		pedido.setProgresso(100);
		pedido.setDataSolicitacao(LocalDateTime.now());
		pedido.setCodigoBatch(MontadorPostService.gerarCodigoBatch());
		pedido.setNome("Programação");
		pedidoPostDAO.salvar(pedido);
	}
}
