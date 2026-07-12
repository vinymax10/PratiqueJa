package dao.usuario;

import java.util.ArrayList;
import java.util.List;

import dao.DAO;
import filtro.usuario.FiltroAssinatura;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.usuario.Assinatura;
import modelo.usuario.Produto;
import modelo.usuario.StatusAssinatura;
import modelo.usuario.Usuario;

public class AssinaturaDAO extends DAO<Assinatura>
{
	private static final long serialVersionUID = 1L;

	public AssinaturaDAO()
	{
		super(Assinatura.class);
	}

	public List<Assinatura> buscar(FiltroAssinatura filtro)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Assinatura> query = builder.createQuery(Assinatura.class);
		Root<Assinatura> fromAssinatura = query.from(Assinatura.class);

		List<Predicate> predicates = new ArrayList<>();

		if(filtro.getNomeUsuario() != null && !filtro.getNomeUsuario().isBlank())
			predicates.add(builder.like(fromAssinatura.get("usuario").get("nome"), "%" + filtro.getNomeUsuario() + "%"));

		if(filtro.getId() != null)
			predicates.add(builder.equal(fromAssinatura.get("id"), filtro.getId()));

		if(filtro.getPeriodo() != null && !filtro.getPeriodo().isEmpty())
		{
			predicates.add(builder.greaterThanOrEqualTo(
				fromAssinatura.get("dataInicio"), filtro.getPeriodo().get(0)));

			if(filtro.getPeriodo().size() > 1)
				predicates.add(builder.lessThanOrEqualTo(
					fromAssinatura.get("dataInicio"), filtro.getPeriodo().get(1)));
		}

		if(filtro.getProduto() != null)
			predicates.add(builder.equal(fromAssinatura.get("produto"), filtro.getProduto()));

		if(filtro.getStatus() != null)
			predicates.add(builder.equal(fromAssinatura.get("status"), filtro.getStatus()));

		TypedQuery<Assinatura> typedQuery = em.createQuery(query.select(fromAssinatura).where(predicates.toArray(new Predicate[0])).distinct(true));
		return typedQuery.getResultList();
	}

	public List<Assinatura> buscarPorUsuario(Usuario usuario)
	{
		return em.createQuery(
			"SELECT a FROM Assinatura a WHERE a.usuario.id = :id ORDER BY a.dataInicio DESC",
			Assinatura.class)
			.setParameter("id", usuario.getId())
			.getResultList();
	}

	/**
	 * A Assinatura ativa mais recente com esse subscriber_code. Uma troca de plano/oferta
	 * sob o mesmo subscriber_code fecha a antiga e cria outra, então mais de uma linha pode
	 * compartilhar o mesmo código ao longo do tempo — só a ativa importa aqui.
	 */
	public Assinatura buscarAtivaPorSubscriberCode(String subscriberCodeHotmart)
	{
		if(subscriberCodeHotmart == null || subscriberCodeHotmart.isBlank())
			return null;

		List<Assinatura> resultado = em.createQuery(
			"SELECT a FROM Assinatura a WHERE a.subscriberCodeHotmart = :codigo AND a.status = :status "
			+ "ORDER BY a.dataInicio DESC",
			Assinatura.class)
			.setParameter("codigo", subscriberCodeHotmart)
			.setParameter("status", StatusAssinatura.Ativa)
			.setMaxResults(1)
			.getResultList();

		return resultado.isEmpty() ? null : resultado.get(0);
	}

	public Assinatura buscarAtivaPorUsuarioEProduto(Usuario usuario, Produto produto)
	{
		List<Assinatura> resultado = em.createQuery(
			"SELECT a FROM Assinatura a WHERE a.usuario.id = :usuarioId AND a.produto.id = :produtoId "
			+ "AND a.status = :status ORDER BY a.dataInicio DESC",
			Assinatura.class)
			.setParameter("usuarioId", usuario.getId())
			.setParameter("produtoId", produto.getId())
			.setParameter("status", StatusAssinatura.Ativa)
			.setMaxResults(1)
			.getResultList();

		return resultado.isEmpty() ? null : resultado.get(0);
	}
}
