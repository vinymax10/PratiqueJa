package service.avaliacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;

import dao.avaliacao.PedidoAvaliacaoDAO;
import dao.usuario.UsuarioDAO;
import modelo.avaliacao.PerfilAvaliacao;
import modelo.usuario.Usuario;

/**
 * Processa mensalmente o rollover da cota de avaliações: no primeiro dia de cada mês, concede a
 * cada assinante ativo o crédito da cota não usada no mês anterior (teto de 1× a cota mensal,
 * validade de um mês). Regras:
 * <ul>
 *   <li>Só acumula quem manteve o plano ativo o mês inteiro (continuidade via mesRolloverProcessado).</li>
 *   <li>Plano vencido (validadePlanoAvaliacao no passado) zera o crédito — parar de pagar = perder o saldo.</li>
 *   <li>Novo assinante só começa a acumular após um mês completo de assinatura.</li>
 * </ul>
 *
 * <p><b>Um usuário, uma transação.</b> A classe é {@code NOT_SUPPORTED} de propósito: sem isso o
 * método do timer roda no default {@code REQUIRED}, os {@code @Transactional} do DAO entram nessa
 * mesma transação e a rodada inteira vira um único commit no fim. Aí duas coisas quebravam. Com a
 * base crescendo, os 300s do WildFly estouram e <b>todos</b> os créditos voltam atrás; e bastava um
 * usuário dar erro para a transação ficar marcada para rollback — o {@code catch} de dentro do laço
 * engolia a exceção, os demais seguiam "com sucesso" e o commit final descartava tudo.</p>
 *
 * <p>O estrago não parava no mês: como o {@code mesRolloverProcessado} não avançava, no mês seguinte
 * o assinante caía no ramo "houve lacuna" e perdia o acúmulo de vez. E não há retentativa — isto
 * roda uma vez por mês.</p>
 */
@Singleton
@Startup
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RolloverAvaliacaoService
{
	private static final Logger logger = Logger.getLogger(RolloverAvaliacaoService.class.getName());

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

		int processados = 0;
		int falhas = 0;

		for (Usuario usuario : usuarioDAO.listarComPerfilAvaliacao())
		{
			// O laço inteiro do usuário entra no try: somarAvaliacoesNoMes também vai ao banco, e
			// uma falha dele não pode interromper o rollover de quem vem depois.
			try
			{
				processar(usuario, hoje, mesAtual, mesAnterior, inicioAnterior, inicioAtual);
				processados++;
			}
			catch (Exception e)
			{
				falhas++;
				logger.log(Level.SEVERE, "Falha no rollover de avaliações do usuário id="
					+ usuario.getId() + "; os demais seguem.", e);
			}
		}

		logger.info("Rollover de avaliações concluído: " + processados + " usuário(s) processado(s), "
			+ falhas + " com falha.");
	}

	/** Calcula e grava o crédito de um usuário — o {@code atualizarRollover} é a transação dele. */
	private void processar(Usuario usuario, LocalDate hoje, LocalDate mesAtual, LocalDate mesAnterior,
		LocalDateTime inicioAnterior, LocalDateTime inicioAtual)
	{
		PerfilAvaliacao plano = usuario.getPerfilAvaliacao();
		boolean planoAtivo = usuario.getValidadePlanoAvaliacao() == null
			|| !usuario.getValidadePlanoAvaliacao().isBefore(hoje);

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

		usuarioDAO.atualizarRollover(usuario.getId(), credito, mesProcessado);
	}
}
