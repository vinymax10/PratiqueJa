package service.avaliacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

import dao.avaliacao.PedidoAvaliacaoDAO;
import dao.usuario.UsuarioDAO;
import modelo.avaliacao.PlanoAvaliacao;
import modelo.usuario.Usuario;

/**
 * Processa mensalmente o rollover da cota de avaliações: no primeiro dia de cada mês, concede a
 * cada assinante ativo o crédito da cota não usada no mês anterior (teto de 1× a cota mensal,
 * validade de um mês). Regras:
 * <ul>
 *   <li>Só acumula quem manteve o plano ativo o mês inteiro (continuidade via mesRolloverProcessado).</li>
 *   <li>Plano vencido (validadePlano no passado) zera o crédito — parar de pagar = perder o saldo.</li>
 *   <li>Novo assinante só começa a acumular após um mês completo de assinatura.</li>
 * </ul>
 */
@Singleton
@Startup
public class RolloverAvaliacaoService
{
	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private PedidoAvaliacaoDAO pedidoAvaliacaoDAO;

	@Schedule(dayOfMonth = "1", hour = "0", minute = "5", second = "0", persistent = false)
	public void processarRollover()
	{
		LocalDate hoje = LocalDate.now();
		LocalDate mesAtual = hoje.withDayOfMonth(1);
		LocalDate mesAnterior = mesAtual.minusMonths(1);
		LocalDateTime inicioAnterior = mesAnterior.atStartOfDay();
		LocalDateTime inicioAtual = mesAtual.atStartOfDay();

		for (Usuario usuario : usuarioDAO.listarComPlanoAvaliacao())
		{
			PlanoAvaliacao plano = usuario.getPlanoAvaliacao();
			boolean planoAtivo = usuario.getValidadePlano() == null
				|| !usuario.getValidadePlano().isBefore(hoje);

			int credito;
			LocalDate mesProcessado;

			if (!planoAtivo)
			{
				// Plano vencido: perde o crédito e zera a continuidade (recomeça se reassinar).
				credito = 0;
				mesProcessado = null;
			}
			else if (mesAnterior.equals(usuario.getMesRolloverProcessado()))
			{
				// Esteve ativo no mês anterior: acumula o não usado, com teto de 1× a cota mensal.
				int usadasAnterior = pedidoAvaliacaoDAO.somarAvaliacoesNoMes(usuario, inicioAnterior, inicioAtual);
				int naoUsado = Math.max(0, plano.getLimiteMensal() - usadasAnterior);
				credito = Math.min(plano.getLimiteMensal(), naoUsado);
				mesProcessado = mesAtual;
			}
			else
			{
				// Primeiro mês de assinatura (ou houve lacuna): ainda não acumula, só marca o início.
				credito = 0;
				mesProcessado = mesAtual;
			}

			try
			{
				usuarioDAO.atualizarRollover(usuario.getId(), credito, mesProcessado);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
