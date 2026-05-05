package DAO.Instagram;

import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import DAO.DAO;
import Modelo.Instagram.Hashtag;

public class HashtagDAO extends DAO<Hashtag>
{
	private static final long serialVersionUID = 1L;

	public HashtagDAO()
	{
		super(Hashtag.class);
	}

	public String getAny(int number)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Hashtag> query = builder.createQuery(Hashtag.class);
		Root<Hashtag> fromHashtag = query.from(Hashtag.class);

		Expression<Double> randExpr = builder.function("RAND", Double.class);
		query.select(fromHashtag);
		query.orderBy(builder.asc(randExpr));
		
		TypedQuery<Hashtag> typedQuery = em.createQuery(query);
		typedQuery.setMaxResults(number);
		List<Hashtag> list = typedQuery.getResultList();
		
		String resultado="";
		for(Hashtag hashtag : list)
			resultado+=hashtag.getNome()+" ";
	
		return resultado;
	}
	
	public List<Hashtag> filtrar(String nome)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Hashtag> query = builder.createQuery(Hashtag.class);
		Root<Hashtag> fromHashtag = query.from(Hashtag.class);

		Predicate predicate = builder.and();

		if(!nome.equals(""))
		{
			predicate = builder.and(predicate, builder.like(fromHashtag.<String>get("nome"), "%" + nome + "%"));
		}

		TypedQuery<Hashtag> typedQuery = em.createQuery(query.select(fromHashtag).where(predicate).distinct(true));
		List<Hashtag> list = typedQuery.getResultList();

		return list;
	}

}
