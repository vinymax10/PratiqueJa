package DAO.Instagram;

import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import DAO.DAO;
import Modelo.Instagram.ConfigPost;
import Modelo.Instagram.Cta;
import Modelo.Instagram.FinalidadeCta;

public class CtaDAO extends DAO<Cta>
{
	private static final long serialVersionUID = 1L;

	public CtaDAO()
	{
		super(Cta.class);
	}

	public String getAnyCta(FinalidadeCta finalidadeCta)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Cta> query = builder.createQuery(Cta.class);
		Root<Cta> fromCta = query.from(Cta.class);
		Predicate predicate = builder.and();
		predicate = builder.and(predicate, builder.isNull(fromCta.<String>get("configPost")));
		predicate = builder.and(predicate, builder.equal(fromCta.get("finalidadeCta"),finalidadeCta));

	    Expression<Double> randExpr = builder.function("RAND", Double.class);
		query.select(fromCta);
		query.where(predicate);
		query.orderBy(builder.asc(randExpr));
		
		TypedQuery<Cta> typedQuery = em.createQuery(query);
		typedQuery.setMaxResults(1);
		List <Cta> ctas = typedQuery.getResultList();

		if(ctas.size()>0)
			return ctas.get(0).getNome();
		else
			return "";
	}
	
	
	public String getAnyCta(ConfigPost configPost)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Cta> query = builder.createQuery(Cta.class);
		Root<Cta> fromCta = query.from(Cta.class);
		
		Predicate predicate = builder.and();
		Join<Cta, ConfigPost> configPostJoin = fromCta.join("configPost");

		predicate = builder.and(predicate, builder.equal(configPostJoin.get("id"),configPost.getId()));
		predicate = builder.and(predicate, builder.equal(fromCta.get("finalidadeCta"),configPost.getFinalidadeCta()));

	    Expression<Double> randExpr = builder.function("RAND", Double.class);
		query.select(fromCta);
		query.where(predicate);
		query.orderBy(builder.asc(randExpr));
		
		TypedQuery<Cta> typedQuery = em.createQuery(query);
		typedQuery.setMaxResults(1);
		List <Cta> ctas = typedQuery.getResultList();

		if(ctas.size()>0)
			return ctas.get(0).getNome();
		else
			return "";
	}
	
	public List<Cta> filtrar(String nome)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Cta> query = builder.createQuery(Cta.class);
		Root<Cta> fromCta = query.from(Cta.class);

		Predicate predicate = builder.and();

		if(!nome.equals(""))
		{
			predicate = builder.and(predicate, builder.like(fromCta.<String>get("nome"), "%" + nome + "%"));
		}

		TypedQuery<Cta> typedQuery = em.createQuery(query.select(fromCta).where(predicate).distinct(true));
		List<Cta> list = typedQuery.getResultList();

		return list;
	}
	
	public List<Cta> listarGenericas()
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Cta> query = builder.createQuery(Cta.class);
		Root<Cta> fromCta = query.from(Cta.class);

		Predicate predicate = builder.and();
		predicate = builder.and(predicate, builder.isNull(fromCta.<String>get("configPost")));

		TypedQuery<Cta> typedQuery = em.createQuery(query.select(fromCta).where(predicate));
		List<Cta> list = typedQuery.getResultList();

		return list;
	}

}
