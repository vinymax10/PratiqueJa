package dao.avaliacao;

import java.time.LocalDateTime;
import java.util.List;

import dao.DAO;
import jakarta.persistence.TypedQuery;
import modelo.avaliacao.PedidoAvaliacao;
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

	public List<PedidoAvaliacao> buscarExpirados(LocalDateTime agora)
	{
		TypedQuery<PedidoAvaliacao> q = em.createQuery(
			"SELECT p FROM PedidoAvaliacao p WHERE p.dataExpiracao < :agora AND p.caminhoArquivo IS NOT NULL",
			PedidoAvaliacao.class);
		q.setParameter("agora", agora);
		return q.getResultList();
	}
}
