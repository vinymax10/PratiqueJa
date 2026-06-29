package service.publicacao;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

import dao.publicacao.PedidoPostDAO;
import dao.usuario.UsuarioDAO;
import modelo.publicacao.PerfilCriador;
import modelo.usuario.Usuario;

/**
 * Processa mensalmente o rollover da cota de posts: no primeiro dia de cada mês, concede a cada
 * criador ativo o crédito da cota não usada no mês anterior (teto de 1× a cota mensal). Espelha o
 * rollover de avaliações: só acumula quem ficou ativo o mês inteiro; plano vencido zera o saldo.
 */
@Singleton
@Startup
public class RolloverPostService
{
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

		for(Usuario usuario : usuarioDAO.listarCriadores())
		{
			PerfilCriador perfil = usuario.getPerfilCriador();
			boolean planoAtivo = usuario.getValidadePlano() == null
				|| !usuario.getValidadePlano().isBefore(hoje);

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

			try
			{
				usuarioDAO.atualizarRolloverPost(usuario.getId(), credito, mesProcessado);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
