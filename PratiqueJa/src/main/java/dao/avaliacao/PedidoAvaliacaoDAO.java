package dao.avaliacao;

import java.time.LocalDateTime;
import java.util.List;

import dao.DAO;
import jakarta.persistence.TypedQuery;
import modelo.avaliacao.PedidoAvaliacao;
import modelo.avaliacao.PerfilAvaliacao;
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
			"WHERE p.usuario.id = :usuarioId AND p.status <> modelo.avaliacao.StatusPedidoAvaliacao.RASCUNHO " +
			"AND p.dataSolicitacao >= :inicio AND p.dataSolicitacao < :fim",
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
			"SELECT SUM(p.quantidade) FROM PedidoAvaliacao p WHERE p.usuario.id = :usuarioId " +
			"AND p.status <> modelo.avaliacao.StatusPedidoAvaliacao.RASCUNHO",
			Long.class);
		q.setParameter("usuarioId", usuario.getId());
		Long result = q.getSingleResult();
		return result == null ? 0 : result.intValue();
	}

	/** Pedidos ainda não concluídos (fila em memória perdida num restart do servidor), na ordem
	 *  original de chegada, para a fila única de geração poder recuperá-los. */
	public List<PedidoAvaliacao> buscarPendentes()
	{
		return em.createQuery(
			"SELECT p FROM PedidoAvaliacao p WHERE p.status = modelo.avaliacao.StatusPedidoAvaliacao.AGUARDANDO " +
			"OR p.status = modelo.avaliacao.StatusPedidoAvaliacao.GERANDO ORDER BY p.dataSolicitacao ASC",
			PedidoAvaliacao.class)
			.getResultList();
	}

	public List<PedidoAvaliacao> buscarExpirados(LocalDateTime agora)
	{
		TypedQuery<PedidoAvaliacao> q = em.createQuery(
			"SELECT p FROM PedidoAvaliacao p WHERE p.dataExpiracao < :agora AND p.caminhoArquivo IS NOT NULL",
			PedidoAvaliacao.class);
		q.setParameter("agora", agora);
		return q.getResultList();
	}

	/** Retorna o endereco relativo da logo da escola do dono do pedido, ou null se não existe ou o
	 *  plano não permite (restrição aos planos Profissional e Master). */
	public String buscarLogoEscolaEndereco(Long pedidoId)
	{
		List<Object[]> linhas = em.createQuery(
			"SELECT u.perfilAvaliacao, l.endereco FROM PedidoAvaliacao p " +
			"JOIN p.usuario u JOIN u.configAvaliacao c JOIN c.logoEscola l WHERE p.id = :pedidoId",
			Object[].class)
			.setParameter("pedidoId", pedidoId)
			.getResultList();

		if(linhas.isEmpty())
			return null;

		PerfilAvaliacao plano = (PerfilAvaliacao) linhas.get(0)[0];
		if(plano != PerfilAvaliacao.Profissional && plano != PerfilAvaliacao.Master)
			return null;

		return (String) linhas.get(0)[1];
	}
}
