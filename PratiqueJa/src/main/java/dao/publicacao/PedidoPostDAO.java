package dao.publicacao;

import java.time.LocalDateTime;
import java.util.List;

import dao.DAO;
import jakarta.persistence.TypedQuery;
import modelo.publicacao.PedidoPost;
import modelo.usuario.Usuario;

public class PedidoPostDAO extends DAO<PedidoPost>
{
	private static final long serialVersionUID = 1L;

	public PedidoPostDAO()
	{
		super(PedidoPost.class);
	}

	public List<PedidoPost> buscarPorUsuario(Usuario usuario)
	{
		if(usuario == null || usuario.getId() == null)
			return List.of();

		// Mostra os lotes sob demanda (programado = false) e também os posts programados que viraram
		// pedido baixável (têm data de expiração). Os antigos registros de consumo "não baixáveis"
		// da programação (sem dataExpiracao) continuam ocultos.
		return em.createQuery(
			"SELECT p FROM PedidoPost p WHERE p.usuario.id = :usuarioId " +
			"AND (p.programado = false OR p.dataExpiracao IS NOT NULL) " +
			"ORDER BY p.dataSolicitacao DESC",
			PedidoPost.class)
			.setParameter("usuarioId", usuario.getId())
			.getResultList();
	}

	public int somarPostsNoMes(Usuario usuario, LocalDateTime inicio, LocalDateTime fim)
	{
		if(usuario == null || usuario.getId() == null)
			return 0;

		TypedQuery<Long> q = em.createQuery(
			"SELECT SUM(p.quantidade) FROM PedidoPost p " +
			"WHERE p.usuario.id = :usuarioId AND p.status <> modelo.publicacao.StatusPedidoPost.RASCUNHO " +
			"AND p.dataSolicitacao >= :inicio AND p.dataSolicitacao < :fim",
			Long.class);
		q.setParameter("usuarioId", usuario.getId());
		q.setParameter("inicio", inicio);
		q.setParameter("fim", fim);
		Long result = q.getSingleResult();
		return result == null ? 0 : result.intValue();
	}

	/** Total de posts já gerados pelo usuário (vitalício) — base da cota grátis de teste. */
	public int somarPostsTotal(Usuario usuario)
	{
		if(usuario == null || usuario.getId() == null)
			return 0;

		TypedQuery<Long> q = em.createQuery(
			"SELECT SUM(p.quantidade) FROM PedidoPost p WHERE p.usuario.id = :usuarioId " +
			"AND p.status <> modelo.publicacao.StatusPedidoPost.RASCUNHO",
			Long.class);
		q.setParameter("usuarioId", usuario.getId());
		Long result = q.getSingleResult();
		return result == null ? 0 : result.intValue();
	}

	/** Pedidos ainda não concluídos (fila em memória perdida num restart do servidor), na ordem
	 *  original de chegada, para a fila única de geração poder recuperá-los. */
	public List<PedidoPost> buscarPendentes()
	{
		return em.createQuery(
			"SELECT p FROM PedidoPost p WHERE p.status = modelo.publicacao.StatusPedidoPost.AGUARDANDO " +
			"OR p.status = modelo.publicacao.StatusPedidoPost.GERANDO ORDER BY p.dataSolicitacao ASC",
			PedidoPost.class)
			.getResultList();
	}

	public List<PedidoPost> buscarExpirados(LocalDateTime agora)
	{
		TypedQuery<PedidoPost> q = em.createQuery(
			"SELECT p FROM PedidoPost p WHERE p.dataExpiracao < :agora AND p.caminhoArquivo IS NOT NULL",
			PedidoPost.class);
		q.setParameter("agora", agora);
		return q.getResultList();
	}
}
