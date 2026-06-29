package service.avaliacao;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import dao.avaliacao.PedidoAvaliacaoDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import modelo.avaliacao.PerfilAvaliacao;
import modelo.usuario.Usuario;

/**
 * Fonte única de verdade para créditos de avaliação disponíveis.
 * Espelha CreditoPostService para o produto de avaliações.
 */
@ApplicationScoped
public class CreditoAvaliacaoService implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoAvaliacaoDAO pedidoAvaliacaoDAO;

	/** Créditos de avaliação disponíveis no período vigente.
	 *  Teste (não renovável): total acumulado contra o limite fixo.
	 *  Renovável: uso do mês corrente contra (cota + rollover). */
	public int creditosRestantes(Usuario usuario)
	{
		if(usuario == null) return 0;
		PerfilAvaliacao plano = usuario.getPerfilAvaliacao() != null
			? usuario.getPerfilAvaliacao() : PerfilAvaliacao.Teste;
		if(!plano.isRenovavel())
			return Math.max(0, plano.getLimiteMensal() - pedidoAvaliacaoDAO.somarAvaliacoesTotal(usuario));
		int cota = plano.getLimiteMensal() + usuario.getCreditoRollover();
		LocalDateTime inicioMes = LocalDate.now().withDayOfMonth(1).atStartOfDay();
		return Math.max(0, cota - pedidoAvaliacaoDAO.somarAvaliacoesNoMes(usuario, inicioMes, inicioMes.plusMonths(1)));
	}

	/** Avaliações já geradas no período vigente.
	 *  Teste: total acumulado. Renovável: só o mês corrente. */
	public int avaliacoesUsadas(Usuario usuario)
	{
		if(usuario == null) return 0;
		PerfilAvaliacao plano = usuario.getPerfilAvaliacao() != null
			? usuario.getPerfilAvaliacao() : PerfilAvaliacao.Teste;
		if(!plano.isRenovavel())
			return pedidoAvaliacaoDAO.somarAvaliacoesTotal(usuario);
		LocalDateTime inicioMes = LocalDate.now().withDayOfMonth(1).atStartOfDay();
		return pedidoAvaliacaoDAO.somarAvaliacoesNoMes(usuario, inicioMes, inicioMes.plusMonths(1));
	}
}
