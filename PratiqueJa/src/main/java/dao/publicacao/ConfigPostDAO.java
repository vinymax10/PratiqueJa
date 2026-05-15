package dao.publicacao;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.publicacao.ConfigPost;
import dao.DAO;

public class ConfigPostDAO extends DAO<ConfigPost>
{
	private static final long serialVersionUID = 1L;

	public ConfigPostDAO()
	{
		super(ConfigPost.class);
	}

	public ConfigPost getConfigPost(Long id)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ConfigPost> query = builder.createQuery(ConfigPost.class);
		Root<ConfigPost> fromConfigPost = query.from(ConfigPost.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(fromConfigPost.get("id"), id));

		TypedQuery<ConfigPost> typedQuery = em.createQuery(query.where(predicates.toArray(new Predicate[0])));
		typedQuery.setMaxResults(1);
		List<ConfigPost> list=typedQuery.getResultList();
		if(list.size()>0)
			return list.get(0);

		return null;
	}

}
