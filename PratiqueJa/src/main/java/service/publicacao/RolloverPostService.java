package service.publicacao;

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

import dao.publicacao.PedidoPostDAO;
import dao.usuario.UsuarioDAO;
import modelo.publicacao.PerfilCriador;
import modelo.usuario.Usuario;

/**
 * Processa mensalmente o rollover da cota de posts: no primeiro dia de cada mês, concede a cada
 * criador ativo o crédito da cota não usada no mês anterior (teto de 1× a cota mensal). Espelha o
 * rollover de avaliações: só acumula quem ficou ativo o mês inteiro; plano vencido zera o saldo.
 *
 * <p><b>Um usuário, uma transação</b> — pelo mesmo motivo detalhado em
 * {@code RolloverAvaliacaoService}: sem o {@code NOT_SUPPORTED} da classe a rodada inteira virava
 * um commit só, e um único erro (ou os 300s do WildFly) desfazia o crédito de todo mundo em
 * silêncio, num trabalho que só roda uma vez por mês.</p>
 */
@Singleton
@Startup
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RolloverPostService
{
	private static final Logger logger = Logger.getLogger(RolloverPostService.class.getName());

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private PedidoPostDAO pedidoPostDAO;

	@Schedule(dayOfMonth = "1", hour = "0", minute = "10", second = "0", persistent = false)
	public void processarRollover()
	{
		LocalDate hoje = LocalDate.now();
		LocalDate mesAtual = hoje.withDayOfMonth(1);
		LocalDate mesAnterior = mesAtual.minusMonths(1);
		LocalDateTime inicioAnterior = mesAnterior.atStartOfDay();
		LocalDateTime inicioAtual = mesAtual.atStartOfDay();

		int processados = 0;
		int falhas = 0;

		for(Usuario usuario : usuarioDAO.listarCriadores())
		{
			// O laço inteiro do usuário entra no try: somarPostsNoMes também vai ao banco, e uma
			// falha dele não pode interromper o rollover de quem vem depois.
			try
			{
				processar(usuario, hoje, mesAtual, mesAnterior, inicioAnterior, inicioAtual);
				processados++;
			}
			catch(Exception e)
			{
				falhas++;
				logger.log(Level.SEVERE, "Falha no rollover de posts do usuário id="
					+ usuario.getId() + "; os demais seguem.", e);
			}
		}

		logger.info("Rollover de posts concluído: " + processados + " usuário(s) processado(s), "
			+ falhas + " com falha.");
	}

	/** Calcula e grava o crédito de um usuário — o {@code atualizarRolloverPost} é a transação dele. */
	private void processar(Usuario usuario, LocalDate hoje, LocalDate mesAtual, LocalDate mesAnterior,
		LocalDateTime inicioAnterior, LocalDateTime inicioAtual)
	{
		PerfilCriador perfil = usuario.getPerfilCriador();
		boolean planoAtivo = usuario.getValidadePlanoCriador() == null
			|| !usuario.getValidadePlanoCriador().isBefore(hoje);

		int credito;
		LocalDate mesProcessado;

		if(!planoAtivo)
		{
			// Plano vencido: perde o crédito e zera a continuidade (recomeça se reassinar).
			credito = 0;
			mesProcessado = null;
		}
		else if(mesAnterior.equals(usuario.getMesRolloverPostProcessado()))
		{
			// Esteve ativo no mês anterior: acumula o não usado, com teto de 1× a cota mensal.
			int usadosAnterior = pedidoPostDAO.somarPostsNoMes(usuario, inicioAnterior, inicioAtual);
			int naoUsado = Math.max(0, perfil.getCreditosMensais() - usadosAnterior);
			credito = Math.min(perfil.getCreditosMensais(), naoUsado);
			mesProcessado = mesAtual;
		}
		else
		{
			// Primeiro mês de assinatura (ou houve lacuna): ainda não acumula, só marca o início.
			credito = 0;
			mesProcessado = mesAtual;
		}

		usuarioDAO.atualizarRolloverPost(usuario.getId(), credito, mesProcessado);
	}
}
