package DAO.Instagram;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import DAO.DAO;
import Modelo.Instagram.ConfigPost;

public class ConfigPostDAO extends DAO<ConfigPost>
{
	private static final long serialVersionUID = 1L;

	public ConfigPostDAO()
	{
		super(ConfigPost.class);
	}
	
	public ConfigPost getConfigPost(Long id)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ConfigPost> query = builder.createQuery(ConfigPost.class);
		Root<ConfigPost> fromConfigPost = query.from(ConfigPost.class);

		Predicate predicate = builder.and();
		predicate = builder.and(predicate, builder.equal(fromConfigPost.get("id"), id));
		
		TypedQuery<ConfigPost> typedQuery = em.createQuery(query.where(predicate));
		typedQuery.setMaxResults(1);
		List<ConfigPost> list=typedQuery.getResultList();
		if(list.size()>0)
			return list.get(0);
		
		return null;
	}

}
