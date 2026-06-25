package dao.avaliacao;

import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import dao.DAO;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import modelo.avaliacao.PedidoAvaliacao;
import modelo.avaliacao.PlanoAvaliacao;
import modelo.usuario.Usuario;

public class PedidoAvaliacaoDAO extends DAO<PedidoAvaliacao>
{
	private static final long serialVersionUID = 1L;

	public PedidoAvaliacaoDAO()
	{
		super(PedidoAvaliacao.class);
	}

	public List<PedidoAvaliacao> buscarPorUsuario(Usuario usuario)
	{
		if(usuario == null || usuario.getId() == null)
			return List.of();

		return em.createQuery(
			"SELECT p FROM PedidoAvaliacao p WHERE p.usuario.id = :usuarioId ORDER BY p.dataSolicitacao DESC",
			PedidoAvaliacao.class)
			.setParameter("usuarioId", usuario.getId())
			.getResultList();
	}

	public int somarAvaliacoesNoMes(Usuario usuario, LocalDateTime inicio, LocalDateTime fim)
	{
		if(usuario == null || usuario.getId() == null)
			return 0;

		TypedQuery<Long> q = em.createQuery(
			"SELECT SUM(p.quantidade) FROM PedidoAvaliacao p " +
			"WHERE p.usuario.id = :usuarioId AND p.dataSolicitacao >= :inicio AND p.dataSolicitacao < :fim",
			Long.class);
		q.setParameter("usuarioId", usuario.getId());
		q.setParameter("inicio", inicio);
		q.setParameter("fim", fim);
		Long result = q.getSingleResult();
		return result == null ? 0 : result.intValue();
	}

	/** Total de avaliações já geradas pelo usuário (vitalício) — base da cota grátis de teste. */
	public int somarAvaliacoesTotal(Usuario usuario)
	{
		if(usuario == null || usuario.getId() == null)
			return 0;

		TypedQuery<Long> q = em.createQuery(
			"SELECT SUM(p.quantidade) FROM PedidoAvaliacao p WHERE p.usuario.id = :usuarioId",
			Long.class);
		q.setParameter("usuarioId", usuario.getId());
		Long result = q.getSingleResult();
		return result == null ? 0 : result.intValue();
	}

	public List<PedidoAvaliacao> buscarExpirados(LocalDateTime agora)
	{
		TypedQuery<PedidoAvaliacao> q = em.createQuery(
			"SELECT p FROM PedidoAvaliacao p WHERE p.dataExpiracao < :agora AND p.caminhoArquivo IS NOT NULL",
			PedidoAvaliacao.class);
		q.setParameter("agora", agora);
		return q.getResultList();
	}

	/**
	 * Lê os bytes da logo da escola do dono do pedido, dentro de uma transação (o LOB só pode ser
	 * lido com a conexão aberta). Devolve null se o usuário não é Profissional/Master ou não tem logo.
	 * Usado pela geração assíncrona do PDF, que roda sem transação.
	 */
	@Transactional
	public byte[] buscarLogoEscolaBytes(Long pedidoId)
	{
		List<Object[]> linhas = em.createQuery(
			"SELECT u.planoAvaliacao, l.file FROM PedidoAvaliacao p " +
			"JOIN p.usuario u JOIN u.configAvaliacao c JOIN c.logoEscola l WHERE p.id = :pedidoId",
			Object[].class)
			.setParameter("pedidoId", pedidoId)
			.getResultList();

		if(linhas.isEmpty())
			return null;

		PlanoAvaliacao plano = (PlanoAvaliacao) linhas.get(0)[0];
		if(plano != PlanoAvaliacao.PROFISSIONAL && plano != PlanoAvaliacao.MASTER)
			return null;

		Blob blob = (Blob) linhas.get(0)[1];
		if(blob == null)
			return null;

		try
		{
			return blob.getBytes(1, (int) blob.length());
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
